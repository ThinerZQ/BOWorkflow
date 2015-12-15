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
package com.sysu.workflow.env.xpath;

import com.sysu.workflow.Context;
import com.sysu.workflow.env.SimpleContext;
import org.apache.commons.jxpath.Variables;

/**
 * A {@link Context} implementation for JXPath environments.
 *
 */
public class XPathContext extends SimpleContext
implements Context, Variables {

    /** Serial version UID. */
    private static final long serialVersionUID = -6803159294612685806L;

    /**
     * No argument constructor.
     *
     */
    public XPathContext() {
        super();
    }

    /**
     * Constructor for cascading contexts.
     *
     * @param parent The parent context. Can be null.
     */
    public XPathContext(final Context parent) {
        super(parent);
    }

    @Override
    public boolean isDeclaredVariable(final String varName) {
        return has(varName);
    }

    @Override
    public Object getVariable(final String varName) {
        return get(varName);
    }

    @Override
    public void declareVariable(final String varName, final Object value) {
        set(varName, value);
    }

    @Override
    public void undeclareVariable(final String varName) {
        if (has(varName)) {
            Context ctx = this;
            while (!ctx.hasLocal(varName)) {
                ctx = ctx.getParent();
                if (ctx == null) {
                    return;
                }
            }
            ctx.getVars().remove(varName);
        }
    }
}
