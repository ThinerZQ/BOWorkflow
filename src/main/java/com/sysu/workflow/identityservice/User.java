package com.sysu.workflow.identityservice;

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


    private UserDao  userDao =null;


    public boolean save(){
        if (userDao==null){
            userDao = new UserDao();
        }
        return  userDao.addUsers(this);
    }
    public boolean delete(){
        return false;
    }
    public boolean update(){
        return false;
    }
    public static UserQuery createUserQuery(){

        return new UserQuery();
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
