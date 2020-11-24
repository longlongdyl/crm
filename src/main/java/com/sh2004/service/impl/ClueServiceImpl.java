package com.sh2004.service.impl;

import com.sh2004.base.constants.CrmExceptionEnum;
import com.sh2004.base.exception.CrmException;
import com.sh2004.base.mapper.ClueActivityRelationMapper;
import com.sh2004.bean.*;
import com.sh2004.mapper.ActivityMapper;
import com.sh2004.mapper.ClueMapper;
import com.sh2004.mapper.ClueRemarkMapper;
import com.sh2004.mapper.UserMapper;
import com.sh2004.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service.impl
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/21 20:11
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("clueService")
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Override
    public List<Map<String, String>> queryActivity(ClueQueryVo clueQueryVo) {
        List<Map<String, String>> list = clueMapper.queryActivity(clueQueryVo);
        for (Map<String, String> clue : list) {
            String owner = clue.get("owner");
            User user = new User();
            user.setId(owner);
            clue.put("owner",userMapper.selectOne(user).getName());
        }
      return list;
    }

    @Override
    public Clue clueRemark(String id) {
        Clue clue = clueMapper.selectByPrimaryKey(id);
        User user = new User();
        user.setId(clue.getOwner());
        clue.setOwner(userMapper.selectOne(user).getName());
        List<ClueRemark> list = clueRemarkMapper.queryByElueId(id);
        //List<Activity>  activities = activityMapper.queryBy();
        List<ClueActivityRelation> cars =clueActivityRelationMapper.queryByClueId(id);
        List<Activity> activityList = new ArrayList<>();
        for (ClueActivityRelation car : cars) {
            Activity activity = activityMapper.selectByPrimaryKey(car.getActivityId());
            activity.setOwner(userMapper.selectByPrimaryKey(activity.getOwner()).getName());
            activityList.add(activity);
        }
        clue.setClueRemarks(list);
        clue.setActivities(activityList);

        return  clue;
    }

    @Override
    public void deleteActivity(String id) {
        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
        clueActivityRelation.setActivityId(id);
        int delete = clueActivityRelationMapper.delete(clueActivityRelation);
        if (delete==0){
            throw new CrmException(CrmExceptionEnum.CLUEREMARK_DELETE);
        }
    }


}
