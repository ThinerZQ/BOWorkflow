package com.sysu.workflow.identityservice;

import java.util.ArrayList;

/**
 * Created by zhengshouzi on 2015/12/14.
 */
public class UserQuery {

    private User user =null;
    private UserDao userDao = null;

    public UserQuery userRealName(String realname){
        user.setRealName(realname);
        return this;
    }
    public UserQuery userId(int id){
        user.setId(id);
        return this;
    }

    public User SingleResult(){
        if (userDao==null){
            userDao = new UserDao();
        }
        ArrayList<User> arrayList = userDao.findUser(user);
        int size = arrayList.size();
        return size>=1?arrayList.get(0):null;
    }

    public UserQuery(User user) {
        this.user = user;
    }

    public UserQuery() {
        user= new User();
    }
}
