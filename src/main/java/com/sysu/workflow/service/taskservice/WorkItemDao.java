package com.sysu.workflow.service.taskservice;

import com.sysu.workflow.database.DBUtils;
import com.sysu.workflow.service.indentityservice.WorkItemEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by zhengshouzi on 2015/12/16.
 */
public class WorkItemDao {

    public boolean insertIntoWorkItem(WorkItemEntity workItemEntity) {
        int i = 0;
        try {
            Connection connection = DBUtils.getMysqlConnection();
            String sql = "INSERT INTO workitem VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, workItemEntity.getItemName());
            preparedStatement.setString(3, workItemEntity.getItemCreateTimee());
            preparedStatement.setString(4, workItemEntity.getItemDueTime());
            preparedStatement.setInt(5, Integer.parseInt(workItemEntity.getItemAssigineeId()));
            preparedStatement.setString(6, workItemEntity.getItemAssigneeName());
            preparedStatement.setString(7, workItemEntity.getItemProcessId());
            preparedStatement.setString(8, workItemEntity.getItemStateId());

            i = preparedStatement.executeUpdate();

            DBUtils.closeAll(connection, preparedStatement, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i == 1 ? true : false;
    }
}
