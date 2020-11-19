package com.sh2004.service.impl;

import com.sh2004.base.constants.CrmExceptionEnum;
import com.sh2004.base.exception.CrmException;
import com.sh2004.bean.Activity;
import com.sh2004.bean.ActivityQueryVo;
import com.sh2004.bean.ActivityRemark;
import com.sh2004.bean.User;
import com.sh2004.mapper.ActivityMapper;
import com.sh2004.mapper.ActivityRemarkMapper;
import com.sh2004.mapper.UserMapper;
import com.sh2004.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service.impl
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/17 16:08
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<String, String>> queryActivity(ActivityQueryVo activity) {
        return activityMapper.queryActivity(activity);
    }

    @Override
    public int addActivity(Activity activity) {
        Activity activityNew = new Activity();
        activityNew.setName(activity.getName());
        int i1 = activityMapper.selectCount(activityNew);
        if (i1>0){
            throw new CrmException(CrmExceptionEnum.ACTIVITY_ADD_FALSE);
        }else{
            int  i = activityMapper.insertSelective(activity);
            if (i>0){
                return i;
            }else {
                throw new CrmException(CrmExceptionEnum.ACTIVITY_ADD_FALSE);
            }
        }
    }

    @Override
    public Activity forActivityId(Activity activity) {
        return activityMapper.selectOne(activity);
    }

    @Override
    public void updateActivity(Activity activity) {
        int i = activityMapper.updateByPrimaryKey(activity);
        if (i<1){
            throw new CrmException(CrmExceptionEnum.ACTIVITY_ADD_FALSE);
        }
    }

    @Override
    public void deleteActivity(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            Activity activity = new Activity();
            activity.setId(s);
            int i = activityMapper.deleteByPrimaryKey(activity);
            if (i<1){
                throw new CrmException(CrmExceptionEnum.ACTIVITY_ADD_FALSE);
            }
        }
    }

    @Override
    public Activity activityRemark(String id) {
        Activity activity = activityMapper.selectByPrimaryKey(id);
        User user = new User();
        user.setId(activity.getOwner());
        activity.setOwner(userMapper.selectOne(user).getName());
        List<ActivityRemark> list = activityRemarkMapper.queryByActivityId(id);
        System.out.println(list.size());
        activity.setActivityRemark(list);
        return  activity;
    }

}
