package com.sysu.workflow.service.processservice;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/1/18
 * Time: 21:09
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 */
public class ProcessInstanceQuery {
    private ProcessInstanceEntity processInstanceEntity = null;
    private ProcessInstanceDao processInstanceDao = null;

    public ProcessInstanceQuery processInstanceId(String  id) {
        processInstanceEntity.setProcessinstanceId(id);
        return this;
    }

    public ProcessInstanceQuery userId(String processInstanceName) {
        processInstanceEntity.setProcessinstanceName(processInstanceName);
        return this;
    }

    public ProcessInstanceEntity SingleResult() {
        if (processInstanceDao == null) {
            processInstanceDao = new ProcessInstanceDao();
        }
        ArrayList<ProcessInstanceEntity> arrayList = processInstanceDao.findProcessInstance(processInstanceEntity);
        int size = arrayList.size();
        return size >= 1 ? arrayList.get(0) : null;
    }

    public ProcessInstanceQuery(ProcessInstanceEntity processInstanceEntity) {
        this.processInstanceEntity = processInstanceEntity;
    }

    public ProcessInstanceQuery() {
        processInstanceEntity = new ProcessInstanceEntity();
    }

}

