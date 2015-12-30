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
package com.sysu.workflow.model;

/**
 * SCXML对象模型中对应于 onexit元素，
 * 是一个可选的属性
 * The class in this SCXML object model that corresponds to the
 * &lt;onexit&gt; SCXML element, which is an optional property
 * holding executable content to be run upon exiting the parent
 * State or Parallel.
 */
public class OnExit extends Executable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 一个表示器，在执行完这个OnExit之后是否要出了一个exit.state.id内部事件
     * An indicator whether to raise the non-standard "exit.state.id" internal event after executing this OnExit
     */
    private Boolean raiseEvent;

    /**
     * Constructor.
     */
    public OnExit() {
        super();
    }

    /**
     * Set the EnterableState parent.
     *
     * @param parent The parent to set.
     */
    @Override
    public final void setParent(final EnterableState parent) {
        super.setParent(parent);
    }

    /**
     * @return Returns true if the non-standard internal "exit.state.id" event will be raised after executing this OnExit
     */
    public final boolean isRaiseEvent() {
        return raiseEvent != null && raiseEvent;
    }

    /**
     * @return Returns the indicator whether to raise the non-standard "exit.state.id" internal event after executing
     * this OnExit. When null no event will be raised
     */
    public final Boolean getRaiseEvent() {
        return raiseEvent;
    }

    /**
     * Set the indicator whether to raise the non-standard "exit.state.id" internal event after executing this OnExit.
     *
     * @param raiseEvent The indicator, when null no event will be raised
     */
    public final void setRaiseEvent(final Boolean raiseEvent) {
        this.raiseEvent = raiseEvent;
    }
}

