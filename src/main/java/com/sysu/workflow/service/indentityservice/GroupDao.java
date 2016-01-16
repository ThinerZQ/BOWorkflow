package com.sysu.workflow.service.indentityservice;

import com.sysu.workflow.database.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by zhengshouzi on 2015/12/16.
 */
public class GroupDao {

    public boolean addGroup(GroupEntity groupEntity) {
        int i=0;
        try {

            Connection connection = DBUtils.getMysqlConnection();
            String sql = "INSERT INTO  t_group VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2,groupEntity.getGroupName());

            i = preparedStatement.executeUpdate();

            DBUtils.closeAll(connection, preparedStatement, null);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return i==1?true:false;
    }

    public GroupEntity findGroup(GroupEntity group) {
       /* ArrayList<User> arrayList = new ArrayList<User>();
        try {
            Connection connection = DBUtils.getMysqlConnection();
            String sql = "SELECT * from user where realname=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1,user.getRealName());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setRealName(rs.getString("realname"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setAge(rs.getInt("age"));
                u.setGender(rs.getString("gender"));
                u.setEmail(rs.getString("email"));
                arrayList.add(user);
            }
            DBUtils.closeAll(connection, preparedStatement, null);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;*/
        return null;
    }


}
