package com.sh2004.service;

import com.sh2004.bean.Contacts;

import java.util.List;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/24 19:22
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface ContactsService {
    List<Contacts> queryContactsByName(String name);
}
