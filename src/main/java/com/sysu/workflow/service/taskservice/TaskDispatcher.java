package com.sysu.workflow.service.taskservice;

import com.sysu.workflow.service.indentityservice.IdentityService;
import com.sysu.workflow.service.indentityservice.UserEntity;
import com.sysu.workflow.service.indentityservice.WorkItemEntity;

import java.util.ArrayList;

/**
 * 任务分派器
 * Created by zhengshouzi on 2015/12/24.
 */
public class TaskDispatcher {

    public static TaskDispatcher taskDispatcher;

    public static TaskDispatcher newInstance() {
        return taskDispatcher == null ? new TaskDispatcher() : taskDispatcher;
    }



    public void dispatchUserTask(ArrayList<WorkItemEntity> workItemEntityArrayList) {


    }
    public void dispatchUserTask(WorkItemEntity workItemEntity,String assignee) {

        IdentityService identityService = new IdentityService();
        WorkItemDao workItemDao = new WorkItemDao();

        boolean flag = false;
        try {

            //找到这个人
            UserEntity user = identityService.createUserQuery().userRealName(assignee).SingleResult();

            if (user!=null){
                workItemEntity.setItemAssigneeName(user.getUserName()).setItemAssigineeId(String.valueOf(user.getUserId()));
                flag = workItemDao.insertIntoWorkItem(workItemEntity);
            }else{
                //抛出异常
            }

            //如果flag不是true，表示插入工作项表失败，抛出 error.execute异常

            //TODO:抛出异常
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
