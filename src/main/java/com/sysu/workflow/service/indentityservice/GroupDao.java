package com.sysu.workflow.service.indentityservice;

import com.sysu.workflow.database.DBUtils;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by zhengshouzi on 2015/12/16.
 */
public class GroupDao {

    public boolean addGroup(GroupEntity groupEntity) {
        Session session = null;
        try {
            session =DBUtils.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            session.save(groupEntity);

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeSession(session);
        }
        return true;
    }

    public GroupEntity findGroup(GroupEntity group) {

        return null;
    }


}
