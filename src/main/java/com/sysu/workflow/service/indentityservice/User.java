package com.sysu.workflow.service.indentityservice;

import com.sysu.workflow.model.IdentityException;
import com.sysu.workflow.service.taskservice.Task;
import com.sysu.workflow.service.taskservice.TaskService;

/**
 * Created by zhengshouzi on 2015/12/14.
 */
public class User {
    private String username;

    private int id;
    private String realName;
    private String email;
    private int age;
    private String password;
    private String gender;


    public boolean addIntoWorkItem(Task task) throws IdentityException{
        if (this.realName!=null){
            TaskService taskService = new TaskService();
            return taskService.insertInToWorkItem(this,task);
        }else {
            throw new IdentityException("没有这个用户");
        }
    }



    public User(String realName) {
        this.realName = realName;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public User setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
