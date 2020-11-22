package com.sh2004.bean;

import com.sh2004.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

public class ClueQueryVo {
    private String name;
    private String owner;
    private String startTime;
    private String endTime;

    @Override
    public String toString() {
        return "ActivityQueryVo{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
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
