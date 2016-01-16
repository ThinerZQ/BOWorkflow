package com.sysu.workflow.service.indentityservice;

/**
 * Created by zhengshouzi on 2015/12/16.
 */
public class Group {
    private String groupName;


    public Group(String name) {
        this.groupName = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
