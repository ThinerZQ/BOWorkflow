package com.sysu.workflow.service.indentityservice;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * Created by zhengshouzi on 2015/9/17.
 */
@Entity
@Table(name = "t_user")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "identity")
    public int userId;

    @Basic
    public String userName;
    @Basic
    public String userRealName;
    @Basic
    public String userPassword;
    @Basic
    public String userAge;
    @Basic
    public String userGender;
    @Basic
    public String userEmail;
    @Basic
    public String userActivateCode;
    @Basic
    public String userStatus;
    @Temporal(TemporalType.TIMESTAMP)
    public Date userRegisterDate;

    @ManyToMany(mappedBy = "userEntitySet")
    public Set<GroupEntity> groupEntitySet;

    public UserEntity(String realname) {
        this.userRealName = realname;
    }

    public UserEntity() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public UserEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public UserEntity setUserRealName(String userRealName) {
        this.userRealName = userRealName;
        return this;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public UserEntity setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public String getUserAge() {
        return userAge;
    }

    public UserEntity setUserAge(String userAge) {
        this.userAge = userAge;
        return this;
    }

    public String getUserGender() {
        return userGender;
    }

    public UserEntity setUserGender(String userGender) {
        this.userGender = userGender;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserEntity setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getUserActivateCode() {
        return userActivateCode;
    }

    public UserEntity setUserActivateCode(String userActivateCode) {
        this.userActivateCode = userActivateCode;
        return this;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public UserEntity setUserStatus(String userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public Date getUserRegisterDate() {
        return userRegisterDate;
    }

    public void setUserRegisterDate(Date userRegisterDate) {
        this.userRegisterDate = userRegisterDate;
    }

    public Set<GroupEntity> getGroupEntitySet() {
        return groupEntitySet;
    }

    public void setGroupEntitySet(Set<GroupEntity> groupEntitySet) {
        this.groupEntitySet = groupEntitySet;
    }
}
