package com.sysu.workflow.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by zhengshouzi on 2015/12/11.
 */
public class InitDatabase {

    public static void main(String[] args) {
        try {
            initDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTables(){
        createUserTable();
        createWorkItemTable();
    }
    public static void createWorkItemTable(){
        try {
            Connection connection = DBUtils.getMysqlConnection();
            String sql = "DROP TABLE if EXISTS workitem;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.executeUpdate();
            DBUtils.closeAll(null, preparedStatement, null);

            sql ="CREATE TABLE workitem(id INT PRIMARY KEY auto_increment ,name VARCHAR(255),createtime VARCHAR (255) ,duetime VARCHAR(20),assigineeId INT ,assignee VARCHAR(255))";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);

            DBUtils.closeAll(connection,preparedStatement,null);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void createUserTable(){
        try {
            Connection connection = DBUtils.getMysqlConnection();
            String sql = "DROP TABLE if EXISTS user;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.executeUpdate();
            DBUtils.closeAll(null, preparedStatement, null);

            sql ="CREATE TABLE user(id INT PRIMARY KEY auto_increment ,username VARCHAR(255),realname VARCHAR(255) ,password VARCHAR(20),age INT ,gender VARCHAR(10),email VARCHAR (50))";
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);

            DBUtils.closeAll(connection,preparedStatement,null);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addUsers(){
      /*  try {

            Connection connection = DBUtils.getMysqlConnection();
            String sql = "INSERT INTO user VALUE(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "zhengshouzi");
            preparedStatement.setString(3, "zhengqiang");
            preparedStatement.setString(4, "zhengqiang");
            preparedStatement.setInt(5, 23);
            preparedStatement.setString(6, "man");
            preparedStatement.setString(7, "601097836@qq.com");


            preparedStatement.executeUpdate();
            DBUtils.closeAll(connection, preparedStatement, null);
        }catch (Exception e) {
            e.printStackTrace();
        }*/
    }
    public static void printlnUsers(){
        try {
            Connection connection = DBUtils.getMysqlConnection();
            String sql = "SELECT * from user";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                System.out.println(resultSet.getString(2));
            }
            DBUtils.closeAll(connection, preparedStatement, resultSet);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void initDatabase() throws Exception{

        //createTables();
        createWorkItemTable();
        //addUsers();
    }
}
