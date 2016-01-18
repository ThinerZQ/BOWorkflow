package com.sysu.workflow.service.indentityservice;

import com.sysu.workflow.service.taskservice.WorkItemEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/1/16
 * Time: 16:09
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 */
@Entity
@Table(name = "t_group")
public class GroupEntity {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "identity")
    public int groupId;

    @Basic
    public String groupName;

    @ManyToMany
    @JoinTable(name="t_user_group",joinColumns={@JoinColumn(name="group_id")},
            inverseJoinColumns={@JoinColumn(name="user_id")})
    private Set<UserEntity> userEntitySet = new HashSet<UserEntity>();


    @OneToMany(mappedBy="itemCandidateCroupEntity",fetch=FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.REMOVE})
    public Set<WorkItemEntity> workItemEntitySet = new HashSet<WorkItemEntity>();

    public GroupEntity(String name) {
        this.groupName = name;
    }

    public GroupEntity() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<UserEntity> getUserEntitySet() {
        return userEntitySet;
    }

    public void setUserEntitySet(Set<UserEntity> userEntitySet) {
        this.userEntitySet = userEntitySet;
    }


}
