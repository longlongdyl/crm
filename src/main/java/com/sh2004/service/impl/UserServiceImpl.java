package com.sh2004.service.impl;

import com.sh2004.base.constants.CrmConstants;
import com.sh2004.base.constants.CrmExceptionEnum;
import com.sh2004.base.exception.CrmException;
import com.sh2004.base.util.DateTimeUtil;
import com.sh2004.base.util.MD5Util;
import com.sh2004.bean.User;
import com.sh2004.mapper.UserMapper;
import com.sh2004.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service.impl
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/16 18:50
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User dologin(User user) {
        user.setLoginPwd(MD5Util.getMD5(user.getLoginPwd()));
        user = userMapper.selectOne(user);
        if (user == null){
                throw new CrmException(CrmExceptionEnum.LOGIN_ACCOUNT_ERROR);
        }else {
            int i = DateTimeUtil.getSysTime().compareTo(user.getExpireTime());
            if (i > 0){
                throw new CrmException(CrmExceptionEnum.LOGIN_ACCOUNT_EXPIRE);
            }
            if ("0".equals(user.getLockState())){
                throw new CrmException(CrmExceptionEnum.LOGIN_ACCOUNT_FORBID);
            }
        }
        return user;
    }

    @Override
    public List<User> queryAllUser() {
        return userMapper.selectAll();
    }
}
