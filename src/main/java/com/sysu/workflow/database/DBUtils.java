package com.sysu.workflow.database;

import java.sql.*;

/**
 * Created by zhengshouzi on 2015/12/12.
 */
public class DBUtils {


    public static Connection getH2InMemeConnection(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            try {
                connection = DriverManager.getConnection("jdbc:h2:mem:workflow", "sa", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static Connection getMysqlConnection(){

        String driver = "com.mysql.jdbc.Driver";

        String url = "jdbc:mysql://127.0.0.1:3306/DBWorkFlow";

        String user = "root";

        String password = "zhengqiang";

        Connection connection = null;
        try {
            Class.forName(driver);
            try {
                connection = DriverManager.getConnection(url,user,password);
                if (connection!=null){
                    System.out.println("连接数据库成功");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }




    public static void closeAll(Connection connection ,PreparedStatement ps,ResultSet rs){

        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (ps != null) {
                ps.close();
                ps = null;
            }
            if (connection != null) {
                connection.close();
                connection = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
