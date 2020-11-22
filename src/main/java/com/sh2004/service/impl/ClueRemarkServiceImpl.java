package com.sh2004.service.impl;

import com.sh2004.base.constants.CrmExceptionEnum;
import com.sh2004.base.exception.CrmException;
import com.sh2004.base.util.DateTimeUtil;
import com.sh2004.base.util.UUIDUtil;
import com.sh2004.bean.ClueRemark;
import com.sh2004.mapper.ClueRemarkMapper;
import com.sh2004.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service.impl
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/21 21:04
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("clueRemarkService")
public class ClueRemarkServiceImpl implements ClueRemarkService {
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;
    @Override
    public void updateClueRemark(ClueRemark clueRemark) {
        int i = clueRemarkMapper.updateByPrimaryKeySelective(clueRemark);
        if (i==0){
            throw new CrmException(CrmExceptionEnum.CLUEREMARK_UPDATE);
        }
    }

    @Override
    public void insertClueRemark(ClueRemark clueRemark) {
        clueRemark.setId(UUIDUtil.getUUID());
        clueRemark.setCreateTime(DateTimeUtil.getSysTime());
        int i = clueRemarkMapper.insert(clueRemark);
        if (i==0){
            throw new CrmException(CrmExceptionEnum.CLUEREMARK_UPDATE);
        }
    }
}
