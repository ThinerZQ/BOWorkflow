package com.sysu.workflow;

import com.sysu.workflow.entity.UserEntity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/2/2
 * Time: 14:09
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://www.thinerzq.me</a>
 * Email: 601097836@qq.com
 */

public class CrowdSourcingTaskEntity {


    public long taskId;

    public String taskName;

    public String taskDescription;

    public Date taskReleaseTime;

    public Date taskDeadlineTime;

    public Date taskCompleteTime;

    public String taskType;

    public String taskPrice;

    public UserEntity userEntity;

    private String stateMachineId;

    private final int judgeCount=3;
    private final int decomposeCount=2;
    private final int getDecomposeVoteCount=3;
    private final int solveCount=2;
    private final int solveVoteCount=3;


    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getTaskReleaseTime() {
        return taskReleaseTime;
    }

    public void setTaskReleaseTime(Date taskReleaseTime) {
        this.taskReleaseTime = taskReleaseTime;
    }

    public Date getTaskDeadlineTime() {
        return taskDeadlineTime;
    }

    public void setTaskDeadlineTime(Date taskDeadlineTime) {
        this.taskDeadlineTime = taskDeadlineTime;
    }

    public Date getTaskCompleteTime() {
        return taskCompleteTime;
    }

    public void setTaskCompleteTime(Date taskCompleteTime) {
        this.taskCompleteTime = taskCompleteTime;
    }

    public String getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(String taskPrice) {
        this.taskPrice = taskPrice;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getStateMachineId() {
        return stateMachineId;
    }

    public void setStateMachineId(String stateMachineId) {
        this.stateMachineId = stateMachineId;
    }

    public int getJudgeCount() {
        return judgeCount;
    }

    public int getDecomposeCount() {
        return decomposeCount;
    }

    public int getGetDecomposeVoteCount() {
        return getDecomposeVoteCount;
    }

    public int getSolveCount() {
        return solveCount;
    }

    public int getSolveVoteCount() {
        return solveVoteCount;
    }

    @Override
    public String toString() {
        return "CrowdSourcingTask{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskReleaseTime=" + taskReleaseTime +
                ", taskDeadlineTime=" + taskDeadlineTime +
                ", taskCompleteTime=" + taskCompleteTime +
                ", taskType='" + taskType + '\'' +
                ", taskPrice='" + taskPrice + '\'' +
                ", userEntity=" + userEntity +
                ", stateMachineId='" + stateMachineId + '\'' +
                '}';
    }
}
