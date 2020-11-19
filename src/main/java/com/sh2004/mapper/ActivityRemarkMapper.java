package com.sh2004.mapper;

import com.sh2004.bean.ActivityRemark;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.mapper
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/19 19:14
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface ActivityRemarkMapper extends Mapper<ActivityRemark> {

    List<ActivityRemark> queryByActivityId(String id);
}
