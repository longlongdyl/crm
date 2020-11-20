package com.sh2004.base.service.impl;

import com.sh2004.base.bean.CaChe;
import com.sh2004.base.bean.CaCheValue;
import com.sh2004.base.mapper.CaCheValueMapper;
import com.sh2004.base.mapper.CaCheMapper;
import com.sh2004.base.service.CaCheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.base.service.impl
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/20 15:41
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("caCheService")
public class CaCheServiceImpl implements CaCheService {
    @Autowired
    private CaCheMapper caCheMapper;
    @Autowired
    private CaCheValueMapper caCheValueMapper;
    @Override
    public List<CaChe> queryAll() {
        List<CaChe> caChes = caCheMapper.selectAll();
        for (CaChe caCh : caChes) {
            List<CaCheValue> caCheValues = caCheValueMapper.queryList(caCh.getCode());

            caCh.setCaCheValueList(caCheValues);
        }
        return caChes;
    }
}
