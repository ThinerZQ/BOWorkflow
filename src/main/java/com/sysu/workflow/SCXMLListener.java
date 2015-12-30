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

import com.sysu.workflow.model.*;

/**
 * 监听接口，监听SCXML中所有可观察的实体
 * 包括：SCXML(订阅所有的entry,exit,transition 通知)
 * State （订阅特别的 entry，exit 通知）
 * Transition （订阅特别的transitions 通知）
 *
 * Listener interface for observable entities in the SCXML model.
 * Observable entities include {@link SCXML}
 * instances (subscribe to all entry, exit and transition notifications),
 * {@link State} instances (subscribe to
 * particular entry and exit notifications) and
 * {@link Transition} instances (subscribe to
 * particular transitions).
 */
public interface SCXMLListener {

    /**
     * Handle the entry into a EnterableState.
     * 处理静茹一个状态
     *
     * @param state The EnterableState entered
     */
    void onEntry(EnterableState state);

    /**
     * Handle the exit out of a EnterableState.
     * 处理退出一个状态
     *
     * @param state The EnterableState exited
     */
    void onExit(EnterableState state);

    /**
     * Handle the transition.
     *
     * 处理一个转移
     *
     * @param from       The source TransitionTarget
     * @param to         The destination TransitionTarget
     * @param transition The Transition taken
     * @param event      The event name triggering the transition
     */
    void onTransition(TransitionTarget from, TransitionTarget to,
                      Transition transition, String event);

}

