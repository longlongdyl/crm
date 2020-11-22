package com.sh2004.mapper;

import com.sh2004.bean.Clue;
import com.sh2004.bean.ClueQueryVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.mapper
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/21 20:11
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface ClueMapper extends Mapper<Clue> {
    List<Map<String, String>> queryActivity(ClueQueryVo clueQueryVo);
}
