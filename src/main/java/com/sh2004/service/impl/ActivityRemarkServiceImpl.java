package com.sh2004.service.impl;

import com.sh2004.base.constants.CrmConstants;
import com.sh2004.base.constants.CrmExceptionEnum;
import com.sh2004.base.exception.CrmException;
import com.sh2004.base.util.DateTimeUtil;
import com.sh2004.base.util.UUIDUtil;
import com.sh2004.bean.ActivityRemark;
import com.sh2004.bean.User;
import com.sh2004.mapper.ActivityRemarkMapper;
import com.sh2004.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service.impl
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/19 19:13
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("activityRemarkService")
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public void updateActivityRemark(ActivityRemark activityRemark) {
        activityRemark.setEditTime(DateTimeUtil.getSysTime());
        //以后再加修改者
        int i = activityRemarkMapper.updateByPrimaryKeySelective(activityRemark);
        if (i==0){
            throw new CrmException(CrmExceptionEnum.ACTIVITY_UPDATE_REMARK);
        }
    }

    @Override
    public void addActivityRemark(String remark, String activityId,User user) {
        ActivityRemark activityRemark = new ActivityRemark();
        activityRemark.setActivityId(activityId);
        activityRemark.setNoteContent(remark);
        activityRemark.setCreateTime(DateTimeUtil.getSysTime());
        activityRemark.setCreateBy(user.getName());
        activityRemark.setId(UUIDUtil.getUUID());
        int insert = activityRemarkMapper.insert(activityRemark);
        if (insert==0){
            throw  new CrmException(CrmExceptionEnum.LOGIN_ACCOUNT_FORBID);
        }

    }

    @Override
    public void deleteActivityRemark(String id) {
        int i = activityRemarkMapper.deleteByPrimaryKey(id);
        if (i==0){
            throw new CrmException(CrmExceptionEnum.LOGIN_ACCOUNT_FORBID);
        }
    }
}
