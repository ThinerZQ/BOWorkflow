package com.sysu.workflow.service.indentityservice;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/1/18
 * Time: 14:59
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 */
public class GroupQuery {
    private GroupEntity group = null;
    private GroupDao groupDao = null;

    public GroupQuery groupName(String groupName) {
        group.setGroupName(groupName);
        return this;
    }

    public GroupQuery groupId(int id) {
        group.setGroupId(id);
        return this;
    }

    public GroupEntity SingleResult() {
        if (groupDao == null) {
            groupDao = new GroupDao();
        }
        GroupEntity groupEntity = groupDao.findGroup(group);

        return  groupEntity!=null ? groupEntity : null;
    }

    public GroupQuery(GroupEntity group) {
        this.group = group;
    }

    public GroupQuery() {
        group = new GroupEntity();
    }
}
