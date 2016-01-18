package com.sysu.workflow.service.indentityservice;

/**
 * Created by zhengshouzi on 2015/12/14.
 */
public class IdentityService {

    UserDao userDao = null;
    GroupDao groupDao = null;

    public UserEntity newUser(String realname) {
        return new UserEntity(realname);
    }
    public UserEntity newUser(){
        return new UserEntity();
    }

    public boolean saveUser(UserEntity userEntity) {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao.addUsers(userEntity);
    }
    public boolean checkUser(UserEntity userEntity) {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao.checkUser(userEntity.userEmail,userEntity.userPassword);
    }


    public boolean delete() {
        return false;
    }

    public boolean update() {
        return false;
    }

    public static UserQuery createUserQuery() {

        return new UserQuery();
    }
    public static GroupQuery createGroupQuery() {

        return new GroupQuery();
    }
    public GroupEntity newGroup(String name) {
        return new GroupEntity(name);
    }

    public boolean saveGroup(GroupEntity group) {
        if (groupDao == null) {
            groupDao = new GroupDao();
        }
        return groupDao.addGroup(group);
    }
}
