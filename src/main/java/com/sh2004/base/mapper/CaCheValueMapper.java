package com.sh2004.base.mapper;

import com.sh2004.base.bean.CaCheValue;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.base.mapper
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/20 15:39
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface CaCheValueMapper extends Mapper<CaCheValue> {
    List<CaCheValue> queryList(String code);
}
