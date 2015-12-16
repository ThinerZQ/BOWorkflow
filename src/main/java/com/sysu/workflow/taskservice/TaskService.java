package com.sysu.workflow.taskservice;


import com.sysu.workflow.identityservice.Group;
import com.sysu.workflow.identityservice.User;

import java.util.ArrayList;

/**
 * Created by zhengshouzi on 2015/12/16.
 */
public class TaskService {



   public boolean insertInToWorkItem(User user,Task task){

        return false;
   }
    public boolean insertIntoWorkItem(Group group ,Task task){
return false;
    }
    public boolean insertIntoWorkItem(ArrayList<Group> groupArrayList ,Task task){
return false;
    }
    public boolean insertInToWorkItem(ArrayList<User> userArrayList,Task task){

return false;
    }

    public Task newTask(String taskname){
        return new Task(taskname);
    }

}
