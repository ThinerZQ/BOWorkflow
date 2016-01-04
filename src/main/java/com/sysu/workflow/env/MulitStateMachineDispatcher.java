package com.sysu.workflow.env;

import com.sysu.workflow.EventDispatcher;
import com.sysu.workflow.SCXMLIOProcessor;
import com.sysu.workflow.TriggerEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.*;

/**
 *
 * Created with IntelliJ IDEA
 * Date: 2016/1/4
 * Time: 20:29
 * User: zhengshouzi
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 *
 * 这是工作流里面多个状态机之间通信的 的消息派发器，
 * 事件派发器，能够调度一般事件和延时事件，事件类型只能是scxml类型，在实现中是用了 J2SE 的 Timer
 * 其他类型不能被处理，
 *
 */
public class MulitStateMachineDispatcher extends SimpleDispatcher implements Serializable {


    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * TimerTask implementation.
     */
    class DelayedEventTask extends TimerTask {

        /**
         * The ID of the &lt;send&gt; element.
         */
        private String id;

        /**
         * The event name.
         */
        private String event;

        /**
         * The event payload, if any.
         */
        private Object payload;

        /**
         * The target io processor
         */
        private SCXMLIOProcessor target;

        /**
         * Constructor for events with payload.
         *
         * @param id      The ID of the send element.
         * @param event   The name of the event to be triggered.
         * @param payload The event payload, if any.
         * @param target  The target io processor
         */
        DelayedEventTask(final String id, final String event, final Object payload, SCXMLIOProcessor target) {
            super();
            this.id = id;
            this.event = event;
            this.payload = payload;
            this.target = target;
        }

        /**
         * What to do when timer expires.
         */
        @Override
        public void run() {
            timers.remove(id);
            target.addEvent(new TriggerEvent(event, TriggerEvent.SIGNAL_EVENT, payload));
            if (log.isDebugEnabled()) {
                log.debug("Fired event '" + event + "' as scheduled by "
                        + "<send> with id '" + id + "'");
            }
        }
    }

    /**
     * Implementation independent log category.
     */
    private Log log = LogFactory.getLog(EventDispatcher.class);

    /**
     * The <code>Map</code> of active <code>Timer</code>s, keyed by
     * &lt;send&gt; element <code>id</code>s.
     */
    private Map<String, Timer> timers = Collections.synchronizedMap(new HashMap<String, Timer>());

    /**
     * Get the log instance.
     *
     * @return The current log instance
     */
    protected Log getLog() {
        return log;
    }

    /**
     * Sets the log instance
     *
     * @param log the new log instance
     */
    protected void setLog(Log log) {
        this.log = log;
    }

    /**
     * Get the current timers.
     *
     * @return The currently scheduled timers
     */
    protected Map<String, Timer> getTimers() {
        return timers;
    }

    /**
     * @see EventDispatcher#cancel(String)
     */
    public void cancel(final String sendId) {
        if (log.isInfoEnabled()) {
            log.info("cancel( sendId: " + sendId + ")");
        }
        if (!timers.containsKey(sendId)) {
            return; // done, we don't track this one or its already expired
        }
        Timer timer = timers.get(sendId);
        if (timer != null) {
            timer.cancel();
            if (log.isDebugEnabled()) {
                log.debug("Cancelled event scheduled by <send> with id '"
                        + sendId + "'");
            }
        }
        timers.remove(sendId);
    }

    /**
     * @see EventDispatcher#send(Map, String, String, String, String, Object, Object, long)
     */
    public void send(final Map<String, SCXMLIOProcessor> ioProcessors, final String id, final String target,
                     final String type, final String event, final Object data, final Object hints, final long delay) {
        if (log.isInfoEnabled()) {
            StringBuilder buf = new StringBuilder();
            buf.append("send ( id: ").append(id);
            buf.append(", target: ").append(target);
            buf.append(", type: ").append(type);
            buf.append(", event: ").append(event);
            buf.append(", data: ").append(String.valueOf(data));
            buf.append(", hints: ").append(String.valueOf(hints));
            buf.append(", delay: ").append(delay);
            buf.append(')');
            log.info(buf.toString());
        }

        // We only handle the "scxml" type (which is the default too) and optionally the #_internal target

        if (type == null || type.equalsIgnoreCase(SCXMLIOProcessor.SCXML_EVENT_PROCESSOR) ||
                type.equals(SCXMLIOProcessor.DEFAULT_EVENT_PROCESSOR)) {

            SCXMLIOProcessor ioProcessor;

            boolean internal = false;

            if (target == null) {
                ioProcessor = ioProcessors.get(SCXMLIOProcessor.SCXML_EVENT_PROCESSOR);
            } else if (ioProcessors.containsKey(target)) {
                ioProcessor = ioProcessors.get(target);
                internal = SCXMLIOProcessor.INTERNAL_EVENT_PROCESSOR.equals(target);
            } else if (SCXMLIOProcessor.INTERNAL_EVENT_PROCESSOR.equals(target)) {
                ioProcessor = ioProcessors.get(SCXMLIOProcessor.INTERNAL_EVENT_PROCESSOR);
                internal = true;
            } else {
                // We know of no other target
                if (log.isWarnEnabled()) {
                    log.warn("<send>: Unavailable target - " + target);
                }
                ioProcessors.get(SCXMLIOProcessor.INTERNAL_EVENT_PROCESSOR).
                        addEvent(new TriggerEvent(TriggerEvent.ERROR_EXECUTION, TriggerEvent.ERROR_EVENT));
                return; // done
            }

            if (event == null) {
                if (log.isWarnEnabled()) {
                    log.warn("<send>: Cannot send without event name");
                }
                ioProcessors.get(SCXMLIOProcessor.INTERNAL_EVENT_PROCESSOR).
                        addEvent(new TriggerEvent(TriggerEvent.ERROR_EXECUTION, TriggerEvent.ERROR_EVENT));
            } else if (!internal && delay > 0L) {
                // Need to schedule this one
                Timer timer = new Timer(true);
                timer.schedule(new DelayedEventTask(id, event, data, ioProcessor), delay);
                timers.put(id, timer);
                if (log.isDebugEnabled()) {
                    log.debug("Scheduled event '" + event + "' with delay "
                            + delay + "ms, as specified by <send> with id '"
                            + id + "'");
                }
            } else {
                ioProcessor.addEvent(new TriggerEvent(event, TriggerEvent.SIGNAL_EVENT, data));
            }
        } else {
            if (log.isWarnEnabled()) {
                log.warn("<send>: Unsupported type - " + type);
            }
            ioProcessors.get(SCXMLIOProcessor.INTERNAL_EVENT_PROCESSOR).
                    addEvent(new TriggerEvent(TriggerEvent.ERROR_EXECUTION, TriggerEvent.ERROR_EVENT));
        }
    }
}
