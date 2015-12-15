package com.sysu.workflow.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by zhengshouzi on 2015/12/11.
 */
public class H2 {


    public static void createTables(){
        try {
            Connection connection = DBUtils.getMysqlConnection();
            String sql = "DROP TABLE if EXISTS user;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.executeUpdate();
            DBUtils.closeAll(null, preparedStatement, null);

            sql ="CREATE TABLE user(ID INT PRIMARY KEY,NAME VARCHAR(255),password VARCHAR(20),email VARCHAR (50))";
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);

            DBUtils.closeAll(connection,preparedStatement,null);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addUsers(){
        try {

            Connection connection = DBUtils.getMysqlConnection();
            String sql = "INSERT INTO user VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "zhengqiang");
            preparedStatement.setString(3, "zhengshouzi");
            preparedStatement.setString(4, "601097836@qq.com");

            preparedStatement.executeUpdate();
            DBUtils.closeAll(connection, preparedStatement, null);
        }catch (Exception e) {
            e.printStackTrace();
        }
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

        createTables();
        addUsers();
    }
}
