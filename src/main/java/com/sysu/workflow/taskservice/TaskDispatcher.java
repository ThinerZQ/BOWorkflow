package com.sysu.workflow.taskservice;

import com.sysu.workflow.ActionExecutionContext;
import com.sysu.workflow.Context;
import com.sysu.workflow.Evaluator;
import com.sysu.workflow.identityservice.IdentityService;
import com.sysu.workflow.identityservice.User;
import com.sysu.workflow.model.EnterableState;
import com.sysu.workflow.model.UserTask;

import java.util.Date;

/**
 * 任务分派器
 * Created by zhengshouzi on 2015/12/24.
 */
public class TaskDispatcher {

    public static TaskDispatcher taskDispatcher;

    public static TaskDispatcher newInstance() {
        return taskDispatcher == null ? new TaskDispatcher() : taskDispatcher;
    }


    public void dispatchUserTask(Task task) {

        TaskService taskService = new TaskService();
        IdentityService identityService = new IdentityService();

        boolean flag = false;
        try {

            //找到这个人
            User user = identityService.createUserQuery().userRealName(task.getAssignee()).SingleResult();

            flag = user.addIntoWorkItem(task);

            //如果flag不是true，表示插入工作项表失败，抛出 error.execute异常

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
