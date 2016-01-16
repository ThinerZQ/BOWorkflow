package com.sysu.workflow.database;

import com.sysu.workflow.service.indentityservice.IdentityService;
import com.sysu.workflow.service.indentityservice.UserEntity;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/1/16
 * Time: 16:52
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 */
public class TableTest {

    private IdentityService identityService =null;

    @Before
    public void before(){
        identityService = new IdentityService();
    }

    @Test
    public void testCreateTable() {

        Configuration config = new Configuration().configure();
        SchemaExport schema = new SchemaExport(config);
        schema.setFormat(true).create(true, true);

    }
    @Test
    public void testAddUser1() {
        UserEntity userEntity = identityService.newUser("zhengqiang");

        userEntity.setUserPassword("193746")
                .setUserName("ThinerZQ")
                .setUserAge("23")
                .setUserEmail("601097836@qq.com")
                .setUserGender("man")
                .setUserStatus("1")
                .setUserRegisterDate(new Date());
        identityService.saveUser(userEntity);
    }
    @Test
    public void testCheckUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmail("601097836@qq.com");
        userEntity.setUserPassword("193746");


        Assert.assertEquals(true, identityService.checkUser(userEntity));
    }

    public void testAddUser2() {

    }

}
