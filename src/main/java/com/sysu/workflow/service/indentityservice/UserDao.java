package com.sysu.workflow.service.indentityservice;

import com.sysu.workflow.database.DBUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengshouzi on 2015/12/15.
 */
public class UserDao{

    /**
     * ≤È’“”√ªß
     * @param userEntity
     * @return
     */
    public boolean addUsers(UserEntity userEntity) {

        Session session = null;
        try {
            session =DBUtils.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            session.save(userEntity);

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeSession(session);
        }
        return true;
    }
    /**
     *
     * @param userEmail
     * @param userPassword
     * @return
     */
    public boolean checkUser(String userEmail,String userPassword) {

        Session session = null;
        UserEntity userEntity = null;
        try {
            session =DBUtils.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(UserEntity.class);

            Criterion userEmailCriterion = Restrictions.eq("userEmail", userEmail);
            Criterion userPasswordCriterion = Restrictions.eq("userPassword", userPassword);
            criteria.add(userEmailCriterion);
            criteria.add(userPasswordCriterion);

            List<UserEntity> userEntityList = criteria.list();

            if (userEntityList.size() == 1) {
                userEntity = userEntityList.get(0);
            }
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeSession(session);
        }
        return userEntity!=null? true:false;

    }
    public ArrayList<UserEntity> findUser(UserEntity userEntity) {
        return null;
    }
    public ArrayList<UserEntity> findUserByUserName(String userName) {
     return null;
    }
    public ArrayList<UserEntity> findUserByUserId(String userId) {
       return null;
    }
    public ArrayList<UserEntity> findUserByUserEmail(String userEmail) {
        return null;
    }
    public ArrayList<UserEntity> findUserByUserRealName(String userRealName) {
        return null;
    }
    public boolean updateUser(UserEntity userEntity){
        return false;
    }
    public boolean deleteUser(UserEntity userEntity){
        return false;
    }
    public boolean deleteUserById(String userId){
        return false;
    }
}
