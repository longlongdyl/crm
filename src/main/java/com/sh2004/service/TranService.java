package com.sh2004.service;

import com.sh2004.bean.Tran;
import com.sh2004.bean.User;

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
}
