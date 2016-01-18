package com.sysu.workflow.service.taskservice;

import com.sysu.workflow.model.UserException;
import com.sysu.workflow.service.indentityservice.GroupEntity;
import com.sysu.workflow.service.indentityservice.UserEntity;

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
    public void dispatchUserTask(WorkItemEntity workItemEntity,UserEntity userEntity) throws UserException {

        WorkItemDao workItemDao = new WorkItemDao();

        boolean flag = false;
        try {

            if (userEntity!=null){
                workItemEntity.setItemAssigneeEntity(userEntity);
                flag = workItemDao.insertIntoWorkItem(workItemEntity);
            }else{
                //抛出异常
                throw new UserException("没有用户："+userEntity.getUserRealName());
            }

            //如果flag不是true，表示插入工作项表失败，抛出 error.execute异常

            //TODO:抛出异常
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dispatchGroupTask(WorkItemEntity workItemEntity,GroupEntity groupEntity) {

        WorkItemDao workItemDao = new WorkItemDao();

        boolean flag = false;
        try {

            if (groupEntity!=null){
                workItemEntity.setItemCandidateCroupEntity(groupEntity);
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
