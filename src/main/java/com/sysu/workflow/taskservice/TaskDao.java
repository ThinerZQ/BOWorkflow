package com.sysu.workflow.taskservice;

import com.sysu.workflow.database.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by zhengshouzi on 2015/12/16.
 */
public class TaskDao {

    public boolean saveWorkItem(Task task){
        int i=0;
        try {
            Connection connection = DBUtils.getMysqlConnection();
            String sql = "INSERT INTO workitem VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //preparedStatement.setInt(1, 1);
            preparedStatement.setInt(1,task.getId());
            preparedStatement.setString(2,task.getName());
            preparedStatement.setString(3,task.getCreateDate());
            preparedStatement.setString(4, task.getDueDate());
            preparedStatement.setInt(5,task.getAssigneeId());
            preparedStatement.setString(6,task.getAssignee());

            i = preparedStatement.executeUpdate();

            DBUtils.closeAll(connection, preparedStatement, null);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return i==1?true:false;
    }
}
