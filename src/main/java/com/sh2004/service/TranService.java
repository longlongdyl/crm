package com.sh2004.service;

import com.sh2004.bean.Tran;
import com.sh2004.bean.TranQueryVo;
import com.sh2004.bean.User;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/23 18:32
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface TranService {
    void addTran(Tran tran, User user);

    List<String> queryCustomerName(String customerName);

    void insertTran(Tran tran);

    List<Map<String, String>> queryTran(TranQueryVo tranQueryVo);

    Tran queryTranById(String id);

    void updateTran(Tran tran);
}
