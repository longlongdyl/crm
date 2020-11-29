package com.sh2004.service;

import com.sh2004.bean.Clue;
import com.sh2004.bean.ClueQueryVo;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/21 20:11
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface ClueService {
    List<Map<String, String>> queryActivity(ClueQueryVo clueQueryVo);

    Clue clueRemark(String id);

    void deleteActivity(String id);

    List<Map<String, String>> queryClueEcharts();
}
