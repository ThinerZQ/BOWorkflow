package com.sysu.workflow.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/1/18
 * Time: 20:53
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 */


@Entity
@Table(name = "t_processinstance")
public class ProcessInstanceEntity implements WorkflowEntity {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "identity")
    private long id;
    @Basic
    private String processinstanceId;
    @Basic
    private String processinstanceName;
    @Basic
    private String processinstanceCreateTime;
    @Basic
    private String processinstanceCurrentState;


    public ProcessInstanceEntity(String processinstanceId, String processinstanceName, String processinstanceCreateTime) {
        this.processinstanceId = processinstanceId;
        this.processinstanceName = processinstanceName;
        this.processinstanceCreateTime = processinstanceCreateTime;
    }

    public ProcessInstanceEntity() {
    }

    public String getProcessinstanceName() {
        return processinstanceName;
    }

    public void setProcessinstanceName(String processinstanceName) {
        this.processinstanceName = processinstanceName;
    }

    public String getProcessinstanceCreateTime() {
        return processinstanceCreateTime;
    }

    public void setProcessinstanceCreateTime(String processinstanceCreateTime) {
        this.processinstanceCreateTime = processinstanceCreateTime;
    }

    public String getProcessinstanceCurrentState() {
        return processinstanceCurrentState;
    }

    public void setProcessinstanceCurrentState(String processinstanceCurrentState) {
        this.processinstanceCurrentState = processinstanceCurrentState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProcessinstanceId() {
        return processinstanceId;
    }

    public void setProcessinstanceId(String processinstanceId) {
        this.processinstanceId = processinstanceId;
    }

    public Map<String, Object> getNotNullPropertyMap() {
        return null;
    }
}
