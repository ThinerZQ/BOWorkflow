package com.sysu.workflow.service.indentityservice;

import com.sysu.workflow.database.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by zhengshouzi on 2015/12/15.
 */
public class UserDao {

    /**
     * 查找用户
     * @param userEntity
     * @return
     */
    public boolean addUsers(UserEntity userEntity) {
        int i = 0;
        try {

            Connection connection = DBUtils.getMysqlConnection();
            String sql = "INSERT INTO t_user VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //不知道为什么不能自增，把自增列设置为 （1,0）就能自增了
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2,userEntity.getUserActivateCode());
            preparedStatement.setString(3, userEntity.getUserAge());
            preparedStatement.setString(4, userEntity.getUserEmail());
            preparedStatement.setString(5, userEntity.getUserGender());
            preparedStatement.setString(6, userEntity.getUserName());
            preparedStatement.setString(7, userEntity.getUserPassword());
            preparedStatement.setString(8, userEntity.getUserRealName());
            preparedStatement.setTimestamp(9, new Timestamp(userEntity.getUserRegisterDate().getTime()));
            preparedStatement.setString(10, userEntity.getUserStatus());

            i = preparedStatement.executeUpdate();

            DBUtils.closeAll(connection, preparedStatement, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i == 1 ? true : false;
    }


    /**
     * 检查用户是否存在
     * @param userEntity
     * @return
     */
    public boolean checkUser(UserEntity userEntity) {
        int i = 0;
        try {

            Connection connection = DBUtils.getMysqlConnection();
            String sql = "SELECT * FROM t_user WHERE userEmail=? and userPassword=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //不知道为什么不能自增，把自增列设置为 （1,0）就能自增了
            preparedStatement.setString(1, userEntity.getUserEmail());

            preparedStatement.setString(2, userEntity.getUserPassword());

            ResultSet rs  = preparedStatement.executeQuery();

            while (rs.next()){
                i=1;
            }


            DBUtils.closeAll(connection, preparedStatement, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i == 1 ? true : false;
    }

    public ArrayList<UserEntity> findUser(UserEntity userEntity) {
        ArrayList<UserEntity> arrayList = new ArrayList<UserEntity>();
        try {
            Connection connection = DBUtils.getMysqlConnection();
            String sql = "SELECT * from t_user where userRealName=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, userEntity.getUserRealName());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
              /*  User u = new User();
                u.setId(rs.getInt("userId"));
                u.setRealName(rs.getString("userRealName"));
                u.setUsername(rs.getString("userName"));
                u.setPassword(rs.getString("userPassword"));
                u.setAge(rs.getInt("userAge"));
                u.setGender(rs.getString("userGender"));
                u.setEmail(rs.getString("userEmail"));
                arrayList.add(u);*/
            }
            DBUtils.closeAll(connection, preparedStatement, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
