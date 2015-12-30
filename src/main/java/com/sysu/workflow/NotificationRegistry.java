/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sysu.workflow;

import com.sysu.workflow.model.EnterableState;
import com.sysu.workflow.model.Observable;
import com.sysu.workflow.model.Transition;
import com.sysu.workflow.model.TransitionTarget;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * The registry where SCXML listeners are recorded for nodes of
 * interest such as the <code>SCXML</code> root,
 * <code>EnterableState</code>s and <code>Transition</code>s.
 * The notification registry keeps track of all
 * <code>SCXMLListener</code>s attached and notifies relevant
 * listeners of the events that interest them.
 */
public final class NotificationRegistry {

    /**
     * The Map of all listeners keyed by {@link Observable#getObservableId()}.
     */
    private final Map<Integer, Set<SCXMLListener>> regs;

    /**
     * Constructor.
     */
    public NotificationRegistry() {
        this.regs = new HashMap<Integer, Set<SCXMLListener>>();
    }

    /**
     * Register this SCXMLListener for this Observable.
     * 注册SCXMLListener 对于 可观察内容
     *
     * @param source The observable this listener wants to listen to
     * @param lst    The listener
     */
    synchronized void addListener(final Observable source, final SCXMLListener lst) {
        if (source != null && source.getObservableId() != null) {
            Set<SCXMLListener> entries = regs.get(source.getObservableId());
            if (entries == null) {
                entries = new LinkedHashSet<SCXMLListener>();
                regs.put(source.getObservableId(), entries);
            }
            entries.add(lst);
        }
    }

    /**
     * Deregister this SCXMLListener for this Observable.
     * 移除SCXMLListener
     *
     * @param source The observable this listener wants to stop listening to
     * @param lst    The listener
     */
    synchronized void removeListener(final Observable source, final SCXMLListener lst) {
        if (source != null && source.getObservableId() != null) {
            Set<SCXMLListener> entries = regs.get(source.getObservableId());
            if (entries != null) {
                entries.remove(lst);
                if (entries.size() == 0) {
                    regs.remove(source.getObservableId());
                }
            }
        }
    }

    /**
     * Inform all relevant listeners that a EnterableState has been
     * entered.
     * 通知所有相关的监听器，一个EnterableState 进入了
     * @param source The Observable
     * @param state  The EnterableState that was entered
     */
    public synchronized void fireOnEntry(final Observable source,
                                         final EnterableState state) {
        if (source != null && source.getObservableId() != null) {
            Set<SCXMLListener> entries = regs.get(source.getObservableId());
            if (entries != null) {
                for (SCXMLListener lst : entries) {
                    lst.onEntry(state);
                }
            }
        }
    }

    /**
     * Inform all relevant listeners that a EnterableState has been
     * exited.
     *
     * @param source The Observable
     * @param state  The EnterableState that was exited
     */
    public synchronized void fireOnExit(final Observable source,
                                        final EnterableState state) {
        if (source != null && source.getObservableId() != null) {
            Set<SCXMLListener> entries = regs.get(source.getObservableId());
            if (entries != null) {
                for (SCXMLListener lst : entries) {
                    lst.onExit(state);
                }
            }
        }
    }

    /**
     * Inform all relevant listeners of a transition that has occured.
     *
     * @param source     The Observable
     * @param from       The source EnterableState
     * @param to         The destination EnterableState
     * @param transition The Transition that was taken
     * @param event      The event name triggering the transition
     */
    public synchronized void fireOnTransition(final Observable source,
                                              final TransitionTarget from, final TransitionTarget to,
                                              final Transition transition, final String event) {
        if (source != null && source.getObservableId() != null) {
            Set<SCXMLListener> entries = regs.get(source.getObservableId());
            if (entries != null) {
                for (SCXMLListener lst : entries) {
                    lst.onTransition(from, to, transition, event);
                }
            }
        }
    }
}

