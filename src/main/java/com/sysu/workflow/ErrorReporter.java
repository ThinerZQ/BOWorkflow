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
 * An interface for reporting SCXML errors to the host environment,
 * containing the definition of commonly occuring errors while executing
 * SCXML documents.
 *
 * 一个用来给host 环境报告SCXML错误的接口，
 * 错误包括，定义的执行过程的所有错误
 *
 */
public interface ErrorReporter {

    /**
     * Handler for reporting an error.
     *
     * 处理错误报告
     *
     * @param errCode   one of the ErrorReporter's constants
     * @param errDetail human readable description
     * @param errCtx    typically an SCXML element which caused an error,
     *                  may be accompanied by additional information
     */
    void onError(String errCode, String errDetail, Object errCtx);
}
