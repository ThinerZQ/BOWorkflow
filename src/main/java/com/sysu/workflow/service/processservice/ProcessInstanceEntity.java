package com.sysu.workflow.service.processservice;

import com.sysu.workflow.service.indentityservice.GroupEntity;
import com.sysu.workflow.service.indentityservice.UserEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
public class ProcessInstanceEntity {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "identity")
    public long id;
    @Basic
    public String processinstanceId;
    @Basic
    public String processinstanceName;
    @Basic
    public String processinstanceCreateTime;
    @Basic
    public String processinstanceCurrentState;


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

    public ProcessInstanceEntity(String processinstanceId, String processinstanceName, String processinstanceCreateTime) {
        this.processinstanceId = processinstanceId;
        this.processinstanceName = processinstanceName;
        this.processinstanceCreateTime = processinstanceCreateTime;
    }

    public ProcessInstanceEntity() {
    }

}
