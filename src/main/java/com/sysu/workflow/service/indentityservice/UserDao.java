package com.sysu.workflow.service.indentityservice;

import com.sysu.workflow.database.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by zhengshouzi on 2015/12/15.
 */
public class UserDao {

    public boolean addUsers(User user) {
        int i = 0;
        try {

            Connection connection = DBUtils.getMysqlConnection();
            String sql = "INSERT INTO user VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //preparedStatement.setInt(1, 1);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getRealName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getGender());
            preparedStatement.setString(6, user.getEmail());

            i = preparedStatement.executeUpdate();

            DBUtils.closeAll(connection, preparedStatement, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i == 1 ? true : false;
    }

    public ArrayList<User> findUser(User user) {
        ArrayList<User> arrayList = new ArrayList<User>();
        try {
            Connection connection = DBUtils.getMysqlConnection();
            String sql = "SELECT * from user where userRealName=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, user.getRealName());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("userId"));
                u.setRealName(rs.getString("userRealName"));
                u.setUsername(rs.getString("userName"));
                u.setPassword(rs.getString("userPassword"));
                u.setAge(rs.getInt("userAge"));
                u.setGender(rs.getString("userGender"));
                u.setEmail(rs.getString("userEmail"));
                arrayList.add(u);
            }
            DBUtils.closeAll(connection, preparedStatement, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
