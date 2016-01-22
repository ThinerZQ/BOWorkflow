package com.sysu.workflow.service.taskservice;

import com.sysu.workflow.entity.WorkflowEntity;
import com.sysu.workflow.model.GroupException;
import com.sysu.workflow.model.UserException;
import com.sysu.workflow.service.indentityservice.GroupEntity;
import com.sysu.workflow.service.indentityservice.UserEntity;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/1/18
 * Time: 20:53
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 */
public class TaskDispatcher {

    private static TaskDispatcher taskDispatcher;
    private TaskService taskService = null;


    public static TaskDispatcher newInstance() {
        return taskDispatcher == null ? new TaskDispatcher() : taskDispatcher;
    }


    public long dispatchUserTask(UserWorkItemEntity userWorkItemEntity, UserEntity userEntity) throws UserException {
        long id = -1;

        userWorkItemEntity.setItemAssigneeEntity(userEntity);
        id = taskService.saveUserWorkItem(userWorkItemEntity);

        return id;
    }

    public long dispatchGroupTask(GroupWorkItemEntity groupWorkItemEntity, GroupEntity groupEntity) throws GroupException {

        long id = -1;
        groupWorkItemEntity.setItemCandidateGroupEntity(groupEntity);
        id = taskService.saveGroupWorkItem(groupWorkItemEntity);

        return id;
    }

    public long dispatchTask(WorkflowEntity workItemEntity, WorkflowEntity resourceEntity) {
        long id = -1;
        if (taskService == null) {
            taskService = new TaskService();
        }

        try {
            if (resourceEntity == null) {
                throw new GroupException("no resource: " + resourceEntity.getNotNullPropertyMap());
            }
            if (workItemEntity instanceof GroupWorkItemEntity && resourceEntity instanceof GroupEntity) {
                return dispatchGroupTask((GroupWorkItemEntity) workItemEntity, (GroupEntity) resourceEntity);
            } else if (workItemEntity instanceof UserWorkItemEntity && resourceEntity instanceof UserEntity) {
                return dispatchUserTask((UserWorkItemEntity) workItemEntity, (UserEntity) resourceEntity);
            } else {
                System.out.println("the dispatch type is not support");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

}
