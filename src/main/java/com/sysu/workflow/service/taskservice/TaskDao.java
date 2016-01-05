package com.sysu.workflow.service.taskservice;

import com.sysu.workflow.database.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by zhengshouzi on 2015/12/16.
 */
public class TaskDao {

    public boolean saveWorkItem(Task task) {
        int i = 0;

        try {
            Connection connection = DBUtils.getMysqlConnection();
            String sql = "INSERT INTO workitem VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, task.getName());
            preparedStatement.setString(3, task.getCreateDate());
            preparedStatement.setString(4, task.getDueDate());
            preparedStatement.setInt(5, task.getAssigneeId());
            preparedStatement.setString(6, task.getAssignee());
            preparedStatement.setString(7, task.getProcessId());
            preparedStatement.setString(8, task.getStateId());

            i = preparedStatement.executeUpdate();

            DBUtils.closeAll(connection, preparedStatement, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i == 1 ? true : false;
    }
}
