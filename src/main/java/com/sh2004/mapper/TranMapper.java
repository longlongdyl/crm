package com.sh2004.mapper;

import com.sh2004.bean.Tran;
import com.sh2004.bean.TranQueryVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.mapper
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/23 18:32
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface TranMapper extends Mapper<Tran> {

    List<Map<String, String>> queryTran(TranQueryVo tranQueryVo);
}
