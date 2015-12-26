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
package com.sysu.workflow.env;

import com.sysu.workflow.SCXMLListener;
import com.sysu.workflow.model.EnterableState;
import com.sysu.workflow.model.Transition;
import com.sysu.workflow.model.TransitionTarget;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;


/**
 * Simple SCXML Listener that logs execution.
 */
public class SimpleSCXMLListener implements SCXMLListener, Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Log.
     */
    private Log log = LogFactory.getLog(getClass());


    /**
     * @see SCXMLListener#onEntry(EnterableState)
     */
    public void onEntry(final EnterableState state) {
        if (log.isInfoEnabled()) {
            log.info("enter " + LogUtils.getTTPath(state));
        }
    }

    /**
     * @see SCXMLListener#onExit(EnterableState)
     */
    public void onExit(final EnterableState state) {
        if (log.isInfoEnabled()) {
            log.info("exit " + LogUtils.getTTPath(state));
        }
    }

    /**
     * @see SCXMLListener#onTransition(TransitionTarget, TransitionTarget, Transition, String)
     */
    public void onTransition(final TransitionTarget from,
                             final TransitionTarget to, final Transition transition, String event) {
        if (log.isInfoEnabled()) {
            log.info("transition " + LogUtils.transToString(from, to, transition, event));
        }
    }

}

