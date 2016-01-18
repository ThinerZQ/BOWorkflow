package com.sysu.workflow.database;

import com.sysu.workflow.service.indentityservice.GroupEntity;
import com.sysu.workflow.service.indentityservice.IdentityService;
import com.sysu.workflow.service.indentityservice.UserEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/1/18
 * Time: 16:47
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 */
public class DataTest{
    private IdentityService identityService =null;

    @Before
    public void before(){
        identityService = new IdentityService();
    }
    @Test
    public void testAddUserAndGroup() {
        //添加管理员
        UserEntity userEntity = identityService.newUser("admin");
        userEntity.setUserPassword("123456")
                .setUserName("admin")
                .setUserAge("23")
                .setUserEmail("admin@qq.com")
                .setUserGender("man")
                .setUserStatus("1")
                .setUserRegisterDate(new Date());
        identityService.saveUser(userEntity);

        //添加用户对应的组
        GroupEntity judgerGroupEntity = identityService.newGroup("Judger");
        GroupEntity decomposerGroupEntity1 = identityService.newGroup("Decomposer");
        GroupEntity decomposeVoterGroupEntity2 = identityService.newGroup("DecomposeVoter");
        GroupEntity solverGroupEntity3 = identityService.newGroup("Solver");
        GroupEntity solveVoterGroupEntity4 = identityService.newGroup("SolveVoter");

        identityService.saveGroup(judgerGroupEntity);
        identityService.saveGroup(decomposerGroupEntity1);
        identityService.saveGroup(decomposeVoterGroupEntity2);
        identityService.saveGroup(solverGroupEntity3);
        identityService.saveGroup(solveVoterGroupEntity4);


        //添加判断人3个
        for (int i = 1; i <=3; i++) {
            Set groupSet = new HashSet<GroupEntity>();
            groupSet.add(judgerGroupEntity);
            userEntity = identityService.newUser();
            userEntity.setUserRealName("judger"+i)
                    .setUserPassword("judger"+i)
                    .setUserName("judger"+i)
                    .setUserAge("23")
                    .setUserEmail("judger"+i+"@qq.com")
                    .setUserGender("man")
                    .setUserStatus("1")
                    .setUserRegisterDate(new Date())
                    .setGroupEntitySet(groupSet);
            identityService.saveUser(userEntity);
        }
        //添加分解者2个
        for (int i = 1; i <= 2; i++) {
            Set groupSet = new HashSet<GroupEntity>();
            groupSet.add(decomposerGroupEntity1);
            userEntity = identityService.newUser();
            userEntity.setUserRealName("decomposer"+i)
                    .setUserPassword("decomposer"+i)
                    .setUserName("decomposer"+i)
                    .setUserAge("23")
                    .setUserEmail("decomposer"+i+"@qq.com")
                    .setUserGender("man")
                    .setUserStatus("1")
                    .setUserRegisterDate(new Date())
                    .setGroupEntitySet(groupSet);
            identityService.saveUser(userEntity);
        }
        //添加分解投票者3个
        for (int i = 1; i <= 3; i++) {
            Set groupSet = new HashSet<GroupEntity>();
            groupSet.add(decomposeVoterGroupEntity2);
            userEntity = identityService.newUser();
            userEntity.setUserRealName("decomposeVoter"+i)
                    .setUserPassword("decomposeVoter"+i)
                    .setUserName("decomposeVoter"+i)
                    .setUserAge("23")
                    .setUserEmail("decomposeVoter"+i+"@qq.com")
                    .setUserGender("man")
                    .setUserStatus("1")
                    .setUserRegisterDate(new Date())
                    .setGroupEntitySet(groupSet);
            identityService.saveUser(userEntity);
        }
        //添加解决者2个
        for (int i = 1; i <= 2; i++) {
            Set groupSet = new HashSet<GroupEntity>();
            groupSet.add(solverGroupEntity3);
            userEntity = identityService.newUser();
            userEntity.setUserRealName("solver"+i)
                    .setUserPassword("solver"+i)
                    .setUserName("solver"+i)
                    .setUserAge("23")
                    .setUserEmail("solver"+i+"@qq.com")
                    .setUserGender("man")
                    .setUserStatus("1")
                    .setUserRegisterDate(new Date())
                    .setGroupEntitySet(groupSet);
            identityService.saveUser(userEntity);
        }
        //添加解决投票者3个
        for (int i = 1; i <= 3; i++) {
            Set groupSet = new HashSet<GroupEntity>();
            groupSet.add(solveVoterGroupEntity4);
            userEntity = identityService.newUser();
            userEntity.setUserRealName("solveVoter"+i)
                    .setUserPassword("solveVoter"+i)
                    .setUserName("solveVoter"+i)
                    .setUserAge("23")
                    .setUserEmail("solveVoter"+i+"@qq.com")
                    .setUserGender("man")
                    .setUserStatus("1")
                    .setUserRegisterDate(new Date())
                    .setGroupEntitySet(groupSet);
            identityService.saveUser(userEntity);
        }
    }
    @Test
    public void testAddGroup() {

    }
}
