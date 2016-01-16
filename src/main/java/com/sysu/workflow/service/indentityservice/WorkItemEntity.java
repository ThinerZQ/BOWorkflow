package com.sysu.workflow.service.indentityservice;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/1/16
 * Time: 15:45
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 */

@Entity
@Table(name = "t_workitem")
public class WorkItemEntity {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "identity")
    public long itemId;

    @Basic
    public String itemName;
    @Basic
    public String itemCreateTimee;
    @Basic
    public String itemDueTime;
    @Basic
    public String itemAssigineeId;
    @Basic
    public String itemAssigneeName;
    @Basic
    public String itemStateId;
    @Basic
    public String itemProcessId;

    public long getItemId() {
        return itemId;
    }

    public WorkItemEntity setItemId(long itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public WorkItemEntity setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public String getItemCreateTimee() {
        return itemCreateTimee;
    }

    public WorkItemEntity setItemCreateTimee(String itemCreateTimee) {
        this.itemCreateTimee = itemCreateTimee;
        return this;
    }

    public String getItemDueTime() {
        return itemDueTime;
    }

    public WorkItemEntity setItemDueTime(String itemDueTime) {
        this.itemDueTime = itemDueTime;
        return this;
    }

    public String getItemAssigineeId() {
        return itemAssigineeId;
    }

    public WorkItemEntity setItemAssigineeId(String itemAssigineeId) {
        this.itemAssigineeId = itemAssigineeId;
        return this;
    }

    public String getItemAssigneeName() {
        return itemAssigneeName;
    }

    public WorkItemEntity setItemAssigneeName(String itemAssigneeName) {
        this.itemAssigneeName = itemAssigneeName;
        return this;
    }

    public String getItemStateId() {
        return itemStateId;
    }

    public WorkItemEntity setItemStateId(String itemStateId) {
        this.itemStateId = itemStateId;
        return this;
    }

    public String getItemProcessId() {
        return itemProcessId;
    }

    public WorkItemEntity setItemProcessId(String itemProcessId) {
        this.itemProcessId = itemProcessId;
        return this;
    }
}
