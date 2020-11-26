package com.sh2004.bean;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.bean
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/21 20:01
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */

public class TranQueryVo {
    private String name;
    private String owner;
    private String startTime;
    private String endTime;
    private String ownerName;
    private String activityName;
    private String contactsFullName;
    private String customerName;

    @Override
    public String toString() {
        return "TranQueryVo{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", activityName='" + activityName + '\'' +
                ", contactsFullName='" + contactsFullName + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getContactsFullName() {
        return contactsFullName;
    }

    public void setContactsFullName(String contactsFullName) {
        this.contactsFullName = contactsFullName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
