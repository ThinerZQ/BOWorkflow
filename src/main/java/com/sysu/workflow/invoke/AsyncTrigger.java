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
package com.sysu.workflow.invoke;

import com.sysu.workflow.SCXMLIOProcessor;
import com.sysu.workflow.TriggerEvent;

/**
 * 一个在给定的状态机的引擎里面添加执行事件的  类，是异步执行的。
 * Trigger the given {@link TriggerEvent} on the given
 * state machine executor asynchronously, once.
 */
class AsyncTrigger implements Runnable {

    /**
     * SCXML 状态机I/O processor，需要传递到这个状态机的i/o processor
     * The SCXML state machine I/O Processor to deliver the event to.
     */
    private final SCXMLIOProcessor ioProcessor;
    /**
     * The event to be triggered.
     * 需要触发的事件
     */
    private final TriggerEvent event;

    /**
     * Constructor.
     * 构造函数
     *
     * @param ioProcessor The {@link SCXMLIOProcessor} to trigger the event on.
     * @param event       The {@link TriggerEvent}.
     */
    AsyncTrigger(final SCXMLIOProcessor ioProcessor, final TriggerEvent event) {
        this.ioProcessor = ioProcessor;
        this.event = event;
    }

    /**
     * Fire the trigger asynchronously.
     * 异步触发
     */
    public void start() {
        new Thread(this).start();
    }

    /**
     * Fire the event(s).
     * 触发
     */
    public void run() {
        ioProcessor.addEvent(event);
    }

}

