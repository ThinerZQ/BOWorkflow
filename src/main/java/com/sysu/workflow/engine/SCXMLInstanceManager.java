package com.sysu.workflow.engine;

import com.sysu.workflow.SCXMLExecutor;
import com.sysu.workflow.SCXMLSystemContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理系统中所有的所有的SCXML实例
 * Created by zhengshouzi on 2015/12/26.
 */
public class SCXMLInstanceManager {


    private static Map<String, SCXMLExecutor> SCXMLInstanceExecutorMap = new HashMap<String, SCXMLExecutor>();
    private static Map<String ,SCXMLInstanceTree> SCXMLInstanceTreeMap   = new HashMap<String, SCXMLInstanceTree>();


    public static void setSCXMLInstance(SCXMLExecutor scxmlExecutor) {
        if (scxmlExecutor != null) {
            SCXMLInstanceExecutorMap.put((String) scxmlExecutor.getGlobalContext().getSystemContext().get(SCXMLSystemContext.SESSIONID_KEY), scxmlExecutor);
        }
    }
    public static SCXMLExecutor getSCXMLInstanceExecutor(String sessionId){
        return SCXMLInstanceExecutorMap.get(sessionId);
    }


    public static Map<String,SCXMLExecutor> getRunningSCXMLInstanceExecutorMap(){
        return SCXMLInstanceExecutorMap;
    }





    public static void setSCXMLInstanceTreeMap(SCXMLInstanceTree scxmlInstanceTree){
        if (scxmlInstanceTree!=null){
            SCXMLInstanceTreeMap.put(scxmlInstanceTree.getRootSessionId(),scxmlInstanceTree);
        }
    }
    public static SCXMLInstanceTree getSCXMLInstanceTreeMap(String rootSessionId){
        return SCXMLInstanceTreeMap.get(rootSessionId);
    }

}
