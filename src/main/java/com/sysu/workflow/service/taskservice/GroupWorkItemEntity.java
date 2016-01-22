package com.sysu.workflow.service.taskservice;

import com.sysu.workflow.service.formservice.FormEntity;
import com.sysu.workflow.service.indentityservice.GroupEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/1/21
 * Time: 15:48
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 */
@Entity
@Table(name = "t_groupworkitem")
public class GroupWorkItemEntity {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "identity")
    private long itemId;

    @Basic
    private String itemName;
    @Basic
    private String itemCreateTime;
    @Basic
    private String itemDueTime;
    @Basic
    private String itemStateId;
    @Basic
    private String itemProcessId;
    @Basic
    private int itemInstances;


    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "groupEntity")
    private GroupEntity itemCandidateGroupEntity;

    @OneToMany(mappedBy = "groupWorkItemEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserWorkItemEntity> itemUserWorkItemEntitySet;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "formEntity")
    private FormEntity itemFormEntity;


    public long getItemId() {
        return itemId;
    }

    public GroupWorkItemEntity setItemId(long itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public GroupWorkItemEntity setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public String getItemCreateTime() {
        return itemCreateTime;
    }

    public GroupWorkItemEntity setItemCreateTime(String itemCreateTime) {
        this.itemCreateTime = itemCreateTime;
        return this;
    }

    public String getItemDueTime() {
        return itemDueTime;
    }

    public GroupWorkItemEntity setItemDueTime(String itemDueTime) {
        this.itemDueTime = itemDueTime;
        return this;
    }

    public String getItemStateId() {
        return itemStateId;
    }

    public GroupWorkItemEntity setItemStateId(String itemStateId) {
        this.itemStateId = itemStateId;
        return this;
    }

    public String getItemProcessId() {
        return itemProcessId;
    }

    public GroupWorkItemEntity setItemProcessId(String itemProcessId) {
        this.itemProcessId = itemProcessId;
        return this;
    }

    public int getItemInstances() {
        return itemInstances;
    }

    public void setItemInstances(int itemInstances) {
        this.itemInstances = itemInstances;
    }

    public GroupEntity getItemCandidateGroupEntity() {
        return itemCandidateGroupEntity;
    }

    public GroupWorkItemEntity setItemCandidateGroupEntity(GroupEntity itemCandidateGroupEntity) {
        this.itemCandidateGroupEntity = itemCandidateGroupEntity;
        return this;
    }

    public Set<UserWorkItemEntity> getItemUserWorkItemEntitySet() {
        return itemUserWorkItemEntitySet;
    }

    public void setItemUserWorkItemEntitySet(Set<UserWorkItemEntity> itemUserWorkItemEntitySet) {
        this.itemUserWorkItemEntitySet = itemUserWorkItemEntitySet;
    }

    public FormEntity getItemFormEntity() {
        return itemFormEntity;
    }

    public void setItemFormEntity(FormEntity itemFormEntity) {
        this.itemFormEntity = itemFormEntity;
    }

    public Map<String, Object> getNotNullPropertyMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (getItemId() != 0) {
            map.put("itemId", getItemId());
        }
        if (getItemName() != null) {
            map.put("itemName", getItemName());
        }
        if (getItemCreateTime() != null) {
            map.put("itemCreateTime", getItemCreateTime());
        }
        return map;
    }
}
