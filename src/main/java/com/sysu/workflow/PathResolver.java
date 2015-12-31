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

/**
 * Interface for a component that may be used by the SCXML engines
 * to resolve context sensitive paths.
 * SCXML引擎使用的路径解析组件的借口，
 * 解析上下文的相对路径
 */
public interface PathResolver {

    /**
     * Resolve this context sensitive path to an absolute URL.
     * 将上下文中相对敏感的路径解析成一个绝对的URL
     *
     * @param ctxPath Context sensitive path, can be a relative URL
     * @return Resolved path (an absolute URL) or <code>null</code>
     */
    String resolvePath(String ctxPath);

    /**
     * Get a PathResolver rooted at this context sensitive path.
     * 返回PathResolver
     *
     * @param ctxPath Context sensitive path, can be a relative URL
     * @return Returns a new resolver rooted at ctxPath
     */
    PathResolver getResolver(String ctxPath);

}

