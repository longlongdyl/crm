package com.sh2004.controller;

import com.sh2004.bean.Clue;
import com.sh2004.bean.Tran;
import com.sh2004.bean.User;
import com.sh2004.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/23 18:31
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class TranController {
    @Autowired
    private TranService tranService;

    @RequestMapping("/workbench/clue/addTran")
    @ResponseBody
    public String addTran(Tran tran, HttpSession session ){
        User user = (User) session.getAttribute("user");
        tranService.addTran(tran,user);
        return "";
    }
}
