package com.sysu.workflow.identityservice;

/**
 * Created by zhengshouzi on 2015/12/14.
 */
public class UserQuery {

    private User user =null;

    public UserQuery userRealName(String realname){
        user.setRealName(realname);
        return this;
    }
    public UserQuery userId(int id){
        user.setId(id);
        return this;
    }

    public UserQuery(User user) {
        this.user = user;
    }

    public UserQuery() {
        user= new User();
    }
}
