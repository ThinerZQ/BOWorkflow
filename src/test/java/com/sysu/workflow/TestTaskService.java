package com.sysu.workflow;

import com.sysu.workflow.entity.GroupEntity;
import com.sysu.workflow.entity.GroupWorkItemEntity;
import com.sysu.workflow.entity.UserEntity;
import com.sysu.workflow.entity.UserWorkItemEntity;
import com.sysu.workflow.service.indentityservice.IdentityService;
import com.sysu.workflow.service.taskservice.TaskService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/1/20
 * Time: 16:58
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 */
public class TestTaskService {

    TaskService taskService = null;

    @Before
    public void before() {
        taskService = new TaskService();
    }

    @Test
    public void testFindTask() {
      /*  GroupEntity groupEntity = IdentityService.createGroupQuery().groupName("Judger").SingleResult();
        ArrayList<GroupWorkItemEntity> groupWorkItemEntityArrayList = TaskService.createGroupTaskQuery().taskCandidateGroup(groupEntity).list();



        System.out.println(groupWorkItemEntityArrayList.get(0).getItemFormEntity().getFormItemEntityLinkedHashSet().size());
    */
    }
    @Test
    public void findGroupTaskByUser(){

       /* UserEntity currentUserEntity  = IdentityService.createUserQuery().userRealName("judger1").SingleResult();

        ArrayList<UserWorkItemEntity> userWorkItemEntityList = TaskService.createUserTaskQuery().taskAssignee(currentUserEntity).list();

        Map<GroupEntity, ArrayList<GroupWorkItemEntity>> groupWorkItemArrayListMap = new LinkedHashMap<GroupEntity, ArrayList<GroupWorkItemEntity>>();
        //得到当前用户所在组的所有工作项
        for (GroupEntity groupEntity : currentUserEntity.getGroupEntitySet()) {
            ArrayList<GroupWorkItemEntity> groupWorkItemEntityArrayList = TaskService.createGroupTaskQuery().taskCandidateGroup(groupEntity).list();
            //当前组有任务，就加入到map里面
            if (groupWorkItemEntityArrayList.size() != 0) {
                groupWorkItemArrayListMap.put(groupEntity, groupWorkItemEntityArrayList);
            }
        }*/



    }
    @Test
    public void findUserTaskByUser(){

        UserEntity currentUserEntity  = IdentityService.createUserQuery().userRealName("judger1").SingleResult();

        ArrayList<UserWorkItemEntity> userWorkItemEntityList = TaskService.createUserTaskQuery().taskAssignee(currentUserEntity).list();


        for (UserWorkItemEntity userWorkItemEntity : userWorkItemEntityList){

            System.out.println(userWorkItemEntity.getItemFormEntity());
        }



    }

}
