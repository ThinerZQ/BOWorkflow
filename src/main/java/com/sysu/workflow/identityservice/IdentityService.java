package com.sysu.workflow.identityservice;

/**
 * Created by zhengshouzi on 2015/12/14.
 */
public class IdentityService {

    UserDao userDao = null;
    GroupDao groupDao = null;

    public User newUser(String realname) {
        return new User(realname);
    }

    public boolean saveUser(User user) {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao.addUsers(user);
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

    public Group newGroup(String name) {
        return new Group(name);
    }

    public boolean saveGroup(Group group) {
        if (groupDao == null) {
            groupDao = new GroupDao();
        }
        return groupDao.addGroup(group);
    }
}
