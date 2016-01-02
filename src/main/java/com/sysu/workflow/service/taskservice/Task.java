package com.sysu.workflow.service.taskservice;

/**
 * Created by zhengshouzi on 2015/12/16.
 */
public class Task {
    private int id;
    private String name;
    private int assigneeId;
    private String assignee;
    private String createDate;
    private String dueDate;
    private String processId;
    private String stateId;


    public String getStateId() {
        return stateId;
    }

    public Task setStateId(String stateId) {
        this.stateId = stateId;
        return this;
    }

    public Task(String name) {
        this.name = name;
    }

    public String getProcessId() {
        return processId;
    }

    public Task setProcessId(String processId) {
        this.processId = processId;
        return this;
    }

    public int getId() {
        return id;
    }

    public Task setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public Task setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
        return this;
    }

    public String getAssignee() {
        return assignee;
    }

    public Task setAssignee(String assignee) {
        this.assignee = assignee;
        return this;
    }

    public String getCreateDate() {
        return createDate;
    }

    public Task setCreateDate(String createDate) {
        this.createDate = createDate;
        return this;
    }

    public String getDueDate() {
        return dueDate;
    }

    public Task setDueDate(String dueDate) {
        this.dueDate = dueDate;
        return this;
    }
}
