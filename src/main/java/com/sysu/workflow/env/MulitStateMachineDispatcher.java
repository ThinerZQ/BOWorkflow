package com.sysu.workflow.env;

import com.sysu.workflow.EventDispatcher;
import com.sysu.workflow.SCXMLExecutor;
import com.sysu.workflow.SCXMLIOProcessor;
import com.sysu.workflow.TriggerEvent;
import com.sysu.workflow.engine.SCXMLInstanceManager;
import com.sysu.workflow.engine.SCXMLInstanceTree;
import com.sysu.workflow.model.ModelException;
import com.sysu.workflow.model.extend.MessageMode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/1/4
 * Time: 20:29
 * User: zhengshouzi
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 * <p/>
 * 这是工作流里面多个状态机之间通信的 的消息派发器，
 * 事件派发器，能够调度一般事件和延时事件，事件类型只能是scxml类型，在实现中是用了 J2SE 的 Timer
 * 其他类型不能被处理，
 */
public class MulitStateMachineDispatcher extends SimpleDispatcher implements Serializable {


    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;
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

    @Override
    public void send(Map<String, SCXMLIOProcessor> ioProcessors, String id, String target, String type, String event, Object data, Object hints, long delay) {
        super.send(ioProcessors, id, target, type, event, data, hints, delay);
    }

    @Override
    public void send(String currentSessionId, SCXMLInstanceTree scxmlInstanceTree, String id, String target, MessageMode messageMode, String targetName, String targetState, String type, String event, Object data, Object hints, long delay) {

        //记录日志
        if (log.isInfoEnabled()) {
            StringBuilder buf = new StringBuilder();
            buf.append("send ( id: ").append(id);
            buf.append(", target: ").append(target);
            buf.append(", messageMode: ").append(messageMode);
            buf.append(", targetName: ").append(targetName);
            buf.append(", targetState: ").append(targetState);
            buf.append(", type: ").append(type);
            buf.append(", event: ").append(event);
            buf.append(", data: ").append(String.valueOf(data));
            buf.append(", hints: ").append(String.valueOf(hints));
            buf.append(", delay: ").append(delay);
            buf.append(')');
            log.info(buf.toString());
        }

        //只处理 type ="scxml" 类型的事件

        if (type == null || type.equalsIgnoreCase(SCXMLIOProcessor.SCXML_EVENT_PROCESSOR) ||
                type.equals(SCXMLIOProcessor.DEFAULT_EVENT_PROCESSOR)) {


            boolean internal = false;


            SCXMLInstanceTree.TreeNode currentTreeNode = scxmlInstanceTree.getNode(currentSessionId);

            SCXMLIOProcessor ioProcessor = SCXMLInstanceManager.getSCXMLInstanceExecutor(currentSessionId);


            if (event == null) {
                if (log.isWarnEnabled()) {
                    log.warn("<send>: Cannot send without event name");
                }
                ioProcessor.addEvent(new TriggerEvent(TriggerEvent.ERROR_EXECUTION, TriggerEvent.ERROR_EVENT));

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
                //ioProcessor.addEvent(new TriggerEvent(event, TriggerEvent.SIGNAL_EVENT, data));
            }


            //根据发送模式选择

            switch (messageMode) {
                case BROADCAST:
                    sendBroadCast(scxmlInstanceTree, currentSessionId, targetName, targetState, event, data, hints, delay);
                    break;
                case MULTICAST:
                    //多播，和target有关

                    // sendMulticast(scxmlInstanceTree,currentSessionId,target,targetName)
                    // TODO:
                    break;
                case TO_ANCESTOR:
                    sendToAncestor(scxmlInstanceTree, currentSessionId, targetName, targetState, event, data, hints, delay);
                    break;
                case TO_CHILD:
                    sendToChild(scxmlInstanceTree, currentSessionId, targetName, targetState, event, data, hints, delay);
                    break;
                case TO_OFFSPRING:
                    sendToOffSpring(scxmlInstanceTree, currentSessionId, targetName, targetState, event, data, hints, delay);
                    break;
                case TO_PARENT:
                    sendToParent(scxmlInstanceTree, currentSessionId, targetName, targetState, event, data, hints, delay);
                    break;
                case TO_SIBLING:
                    //单播，和target格式有关系
                    // TODO:
                    break;
                default:
                    //默认
                    System.out.println("不支持的消息模式");
                    break;
            }


        } else {
            //处理不支持的类型的 I/O 处理器
          /*  if (log.isWarnEnabled()) {
                log.warn("<send>: Unsupported type - " + type);
            }
            ioProcessors.get(SCXMLIOProcessor.INTERNAL_EVENT_PROCESSOR).
                    addEvent(new TriggerEvent(TriggerEvent.ERROR_EXECUTION, TriggerEvent.ERROR_EVENT));*/
        }
    }

    private boolean sendToParent(SCXMLInstanceTree scxmlInstanceTree, String currentSessionId, String targetName, String targetState, String event, Object data, Object hints, long delay) {
        ArrayList<SCXMLInstanceTree.TreeNode> treeNodeArrayList;
        SCXMLInstanceTree.TreeNode currentTreeNode = scxmlInstanceTree.getNode(currentSessionId);

        treeNodeArrayList = scxmlInstanceTree.getParentTreeNode(currentTreeNode);


        sendToTarget(treeNodeArrayList, targetState, event, data);

        return true;
    }

    private boolean sendToOffSpring(SCXMLInstanceTree scxmlInstanceTree, String currentSessionId, String targetName, String targetState, String event, Object data, Object hints, long delay) {

        ArrayList<SCXMLInstanceTree.TreeNode> treeNodeArrayList;
        SCXMLInstanceTree.TreeNode currentTreeNode = scxmlInstanceTree.getNode(currentSessionId);
        if (targetName != null && !"".equals(targetName)) {
            treeNodeArrayList = scxmlInstanceTree.getOffSpringTreeNodeByTargetName(currentTreeNode, targetName);

        } else {
            treeNodeArrayList = scxmlInstanceTree.getOffspringTreeNode(currentTreeNode);

        }
        sendToTarget(treeNodeArrayList, targetState, event, data);

        return true;
    }

    private boolean sendToChild(SCXMLInstanceTree scxmlInstanceTree, String currentSessionId, String targetName, String targetState, String event, Object data, Object hints, long delay) {


        ArrayList<SCXMLInstanceTree.TreeNode> treeNodeArrayList;
        SCXMLInstanceTree.TreeNode currentTreeNode = scxmlInstanceTree.getNode(currentSessionId);
        if (targetName != null && !"".equals(targetName)) {
            treeNodeArrayList = scxmlInstanceTree.getChildTreeNodeByTargetName(currentTreeNode, targetName);

        } else {
            treeNodeArrayList = scxmlInstanceTree.getChildTreeNode(currentTreeNode);

        }
        sendToTarget(treeNodeArrayList, targetState, event, data);

        return true;
    }

    private boolean sendToAncestor(SCXMLInstanceTree scxmlInstanceTree, String currentSessionId, String targetName, String targetState, String event, Object data, Object hints, long delay) {

        //得到树中当前节点的所有祖先所有实例
        ArrayList<SCXMLInstanceTree.TreeNode> treeNodeArrayList;
        SCXMLInstanceTree.TreeNode currentTreeNode = scxmlInstanceTree.getNode(currentSessionId);
        if (targetName != null && !"".equals(targetName)) {
            treeNodeArrayList = scxmlInstanceTree.getAllAncestorTreeNodeByTargetName(currentTreeNode, targetName);

        } else {
            treeNodeArrayList = scxmlInstanceTree.getAllAncestorTreeNode(currentTreeNode);

        }
        sendToTarget(treeNodeArrayList, targetState, event, data);

        return true;
    }

    private boolean sendBroadCast(SCXMLInstanceTree scxmlInstanceTree, String currentSessionId, String targetName, String targetState, String event, Object data, Object hints, long delay) {

        //得到树中所有实例
        ArrayList<SCXMLInstanceTree.TreeNode> treeNodeArrayList;
        if (targetName != null && !"".equals(targetName)) {
            treeNodeArrayList = scxmlInstanceTree.getAllTreeNodeByTargetName(scxmlInstanceTree.getRoot(), targetName);

        } else {
            treeNodeArrayList = scxmlInstanceTree.getAllTreeNode(scxmlInstanceTree.getRoot());

        }

        sendToTarget(treeNodeArrayList, targetState, event, data);

        return true;
    }

    private void sendToTarget(ArrayList<SCXMLInstanceTree.TreeNode> treeNodeArrayList, String targetState, String event, Object data) {
        if (targetState != null && !"".equals(targetState)) {
            //遍历所有的实例
            for (SCXMLInstanceTree.TreeNode treeNode : treeNodeArrayList) {
                //根据当前实例sessionId，求的实例的执行器
                SCXMLExecutor scxmlExecutor = SCXMLInstanceManager.getSCXMLInstanceExecutor(treeNode.getSessionId());
                if (scxmlExecutor != null) {
                    if (scxmlExecutor.getStatus().isInState(targetState)) {
                        try {
                            scxmlExecutor.triggerEvent(new TriggerEvent(event, TriggerEvent.SIGNAL_EVENT, data));
                        } catch (ModelException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("no this  scxml executor");
                }

            }
        } else {
            for (SCXMLInstanceTree.TreeNode treeNode : treeNodeArrayList) {
                SCXMLExecutor scxmlExecutor = SCXMLInstanceManager.getSCXMLInstanceExecutor(treeNode.getSessionId());
                if (scxmlExecutor != null) {
                    try {
                        scxmlExecutor.triggerEvent(new TriggerEvent(event, TriggerEvent.SIGNAL_EVENT, data));
                    } catch (ModelException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("no this  scxml executor");
                }
            }
        }
    }

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
}
