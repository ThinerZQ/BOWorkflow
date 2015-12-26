package com.sysu.workflow.taskservice;


import com.sysu.workflow.identityservice.Group;
import com.sysu.workflow.identityservice.User;

import java.util.ArrayList;

/**
 * Created by zhengshouzi on 2015/12/16.
 */
public class TaskService {


    private TaskDao taskDao = null;

    public boolean insertInToWorkItem(User user, Task task) {
        if (taskDao==null){
            taskDao = new TaskDao();
        }
        task.setAssigneeId(user.getId());
        task.setAssignee(user.getRealName());
        return taskDao.saveWorkItem(task);
    }

    public boolean insertIntoWorkItem(Group group, Task task) {
        return false;
    }

    public boolean insertIntoWorkItem(ArrayList<Group> groupArrayList, Task task) {
        return false;
    }

    public boolean insertInToWorkItem(ArrayList<User> userArrayList, Task task) {

        return false;
    }

    public Task newTask(String taskname) {
        return new Task(taskname);
    }

}
