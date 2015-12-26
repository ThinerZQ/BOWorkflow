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

import java.util.Map;

/**
 *
 * 名称前缀持有器，保存了名称前缀信息，为了Xpath 表达式求值使用
 */
public interface NamespacePrefixesHolder {

    /**
     * 设置名称前缀持有器
     *
     * @param namespaces The namespaces prefix map.
     */
    void setNamespaces(Map<String, String> namespaces);

    /**
     * 得到名称前缀持有器
     *
     * @return The namespaces prefix map.
     */
    Map<String, String> getNamespaces();

}

