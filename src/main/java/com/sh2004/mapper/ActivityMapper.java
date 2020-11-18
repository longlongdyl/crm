package com.sh2004.mapper;

import com.sh2004.bean.Activity;
import com.sh2004.bean.ActivityQueryVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.mapper
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/17 16:08
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface ActivityMapper extends Mapper<Activity> {
    List<Map<String, String>> queryActivity(ActivityQueryVo activity);
}
