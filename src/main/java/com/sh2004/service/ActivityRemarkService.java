package com.sh2004.service;

import com.sh2004.bean.ActivityRemark;
import com.sh2004.bean.User;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/19 19:13
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */

public interface ActivityRemarkService {


    void updateActivityRemark(ActivityRemark activityRemark);

    void addActivityRemark(String remark, String activityId, User user);

    void deleteActivityRemark(String id);
}
