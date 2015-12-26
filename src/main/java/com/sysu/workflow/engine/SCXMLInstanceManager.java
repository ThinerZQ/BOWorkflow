package com.sysu.workflow.engine;

import com.sysu.workflow.SCXMLExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理系统中所有的所有的SCXML实例
 * Created by zhengshouzi on 2015/12/26.
 */
public class SCXMLInstanceManager {


    public Map<String, SCXMLExecutor> SCXMLInstanceMap = new HashMap<String, SCXMLExecutor>();
    public Map<String ,SCXMLInstanceTree> SCXMLInstanceTreeMap   = new HashMap<String, SCXMLInstanceTree>();

    public void setSCXMLInstance(SCXMLExecutor scxmlExecutor) {
        if (scxmlExecutor != null) {
            SCXMLInstanceMap.put((String) scxmlExecutor.getRootContext().get("_sessionid"), scxmlExecutor);
        }
    }
    public SCXMLExecutor getSCXMLExecutor(String sessionId){
        return SCXMLInstanceMap.get(sessionId);
    }

    public void setSCXMLInstanceTreeMap(SCXMLInstanceTree scxmlInstanceTree){
        if (scxmlInstanceTree!=null){
            SCXMLInstanceTreeMap.put(scxmlInstanceTree.getRootSessionId(),scxmlInstanceTree);
        }
    }
    public SCXMLInstanceTree getSCXMLInstanceTreeMap(String rootSessionId){
        return SCXMLInstanceTreeMap.get(rootSessionId);
    }

}
