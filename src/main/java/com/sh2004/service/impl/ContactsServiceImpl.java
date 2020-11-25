package com.sh2004.service.impl;

import com.sh2004.bean.Activity;
import com.sh2004.bean.Contacts;
import com.sh2004.mapper.ContactsMapper;
import com.sh2004.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service.impl
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/24 19:22
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service
public class ContactsServiceImpl implements ContactsService {
    @Autowired
    private ContactsMapper contactsMapper;

    @Override
    public List<Contacts> queryContactsByName(String name) {
        Example example = new Example(Contacts.class);
        example.createCriteria().andLike("fullname","%"+name +"%");
        List<Contacts> contacts = contactsMapper.selectByExample(example);
        return contacts;
    }
}
