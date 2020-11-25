package com.sh2004.controller;

import com.sh2004.bean.Activity;
import com.sh2004.bean.Contacts;
import com.sh2004.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/24 19:21
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ContactsController {
    @Autowired
    private ContactsService contactsService;

    @RequestMapping("/workbench/contacts/queryContactsByName")
    @ResponseBody
    public List<Contacts> queryActivityByName(String name){
        return contactsService.queryContactsByName(name);
    }
}
