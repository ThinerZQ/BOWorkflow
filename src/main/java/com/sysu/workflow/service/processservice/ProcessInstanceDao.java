package com.sysu.workflow.service.processservice;

import com.sysu.workflow.database.DBUtils;
import com.sysu.workflow.entity.ProcessInstanceEntity;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/1/18
 * Time: 21:10
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 */
public class ProcessInstanceDao {
    /**
     *
     * @param processInstanceEntity
     * @return
     */
    public boolean addProcessInstance(ProcessInstanceEntity processInstanceEntity) {

        Session session = null;
        try {
            session = DBUtils.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            session.save(processInstanceEntity);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeSession(session);
        }
        return true;
    }



    public ArrayList<ProcessInstanceEntity> findProcessInstanceByProcessName(String processName) {
        return null;
    }
    public ArrayList<ProcessInstanceEntity> findProcessInstance(ProcessInstanceEntity ProcessInstanceEntity) {
        return null;
    }
    public ProcessInstanceEntity findProcessInstanceByProcessInstanceId(String processInstanceId) {
        return null;
    }

    public boolean updateProcessInstance(ProcessInstanceEntity processInstanceEntity){
        return false;
    }
    public boolean deleteProcessInstance(ProcessInstanceEntity processInstanceEntity){
        return false;
    }

    public ArrayList<ProcessInstanceEntity> getAllProcessInstance() {
        Session session = null;
        ArrayList<ProcessInstanceEntity> processInstanceEntities = null;
        try {
            session = DBUtils.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "select processInstance from ProcessInstanceEntity as processInstance";
            processInstanceEntities = (ArrayList<ProcessInstanceEntity>) session.createQuery(hql).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeSession(session);
        }
        return processInstanceEntities;

    }
}
