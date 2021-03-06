package com.sh2004.service;

import com.sh2004.bean.User;

import java.util.List;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/16 18:49
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface UserService {
    User dologin(User user);

    List<User> queryAllUser();
}
