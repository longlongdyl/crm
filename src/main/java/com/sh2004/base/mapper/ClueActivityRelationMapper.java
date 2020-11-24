package com.sh2004.base.mapper;

import com.sh2004.bean.ClueActivityRelation;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.base.mapper
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/22 15:36
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface ClueActivityRelationMapper extends Mapper<ClueActivityRelation> {
    List<ClueActivityRelation> queryByClueId(String id);
}
