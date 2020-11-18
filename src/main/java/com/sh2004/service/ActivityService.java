package com.sh2004.service;

import com.sh2004.bean.Activity;
import com.sh2004.bean.ActivityQueryVo;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/17 16:08
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface ActivityService {
    List<Map<String, String>> queryActivity(ActivityQueryVo activity);

    int addActivity(Activity activity);

    Activity forActivityId(Activity activity);

    void updateActivity(Activity activity);

    void deleteActivity(String ids);
}
