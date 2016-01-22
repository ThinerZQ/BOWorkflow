package com.sysu.workflow.service.taskservice;

import com.sysu.workflow.database.DBUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Date: 2015/12/16
 * Time: 13:16
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 *
 */
public class WorkItemDao {

    public long insertIntoWorkItem(UserWorkItemEntity workItemEntity) {

        Session session = null;
        long id = -1;
        try {
            session =DBUtils.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            id = (Long) session.save(workItemEntity);

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeSession(session);
        }
        return id;
    }

    public long insertIntoWorkItem(GroupWorkItemEntity groupWorkItemEntity) {

        Session session = null;
        long id = -1;
        try {
            session = DBUtils.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            id = (Long) session.save(groupWorkItemEntity);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeSession(session);
        }
        return id;
    }

    public ArrayList<UserWorkItemEntity> findUserWorkItem(UserWorkItemEntity userWorkItemEntity) {
        Session session = null;
        ArrayList<UserWorkItemEntity> workItemEntityArrayList = new ArrayList<UserWorkItemEntity>();
        try {
            session = DBUtils.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(UserWorkItemEntity.class);


            Map<String, Object> propertyNameValue = userWorkItemEntity.getNotNullPropertyMap();

            System.out.println("Query Condition£º" + propertyNameValue.toString());
            Criterion allCriterion = Restrictions.allEq(propertyNameValue);

            criteria.add(allCriterion);

            workItemEntityArrayList = (ArrayList<UserWorkItemEntity>) criteria.list();
            //System.out.println(workItemEntityArrayList.size());

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeSession(session);
        }
        return workItemEntityArrayList;
    }

    public ArrayList<GroupWorkItemEntity> findGroupWorkItem(GroupWorkItemEntity groupWorkItemEntity) {
        Session session = null;
        ArrayList<GroupWorkItemEntity> groupWorkItemEntityArrayList = new ArrayList<GroupWorkItemEntity>();
        try {
            session = DBUtils.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(GroupWorkItemEntity.class);


            Map<String, Object> propertyNameValue = groupWorkItemEntity.getNotNullPropertyMap();

            System.out.println("Query Condition£º" + propertyNameValue.toString());
            Criterion allCriterion = Restrictions.allEq(propertyNameValue);

            criteria.add(allCriterion);

            groupWorkItemEntityArrayList = (ArrayList<GroupWorkItemEntity>) criteria.list();
            //System.out.println(workItemEntityArrayList.size());

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeSession(session);
        }
        return groupWorkItemEntityArrayList;
    }
}
