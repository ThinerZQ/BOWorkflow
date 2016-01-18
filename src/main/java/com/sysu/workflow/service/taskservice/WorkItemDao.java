package com.sysu.workflow.service.taskservice;

import com.sysu.workflow.database.DBUtils;
import org.hibernate.Session;

/**
 * Created by zhengshouzi on 2015/12/16.
 */
public class WorkItemDao {

    public boolean insertIntoWorkItem(WorkItemEntity workItemEntity) {

        Session session = null;
        try {
            session =DBUtils.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            session.save(workItemEntity);

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeSession(session);
        }
        return true;


    }
}
