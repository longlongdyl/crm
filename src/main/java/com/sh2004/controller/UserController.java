package com.sh2004.controller;

import com.sh2004.base.constants.CrmConstants;
import com.sh2004.base.exception.CrmException;
import com.sh2004.base.util.MD5Util;
import com.sh2004.bean.User;
import com.sh2004.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/16 17:28
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping("/settings/user/login")
    public String login(User user, HttpSession session, Model model){
        if (null == user.getLoginAct()){
            return "../../index";
        }
        try {
            user = userService.dologin(user);
            session.setAttribute(CrmConstants.LOGIN_USER,user);
        } catch (CrmException e) {
            String mess = e.getMessage();
            model.addAttribute("mess",mess);
            //转发到登录页面，显示错误信息
            return "../../index";
        }
        String url = (String) session.getAttribute("url");
        if (url != null){
            url = url.replace("http://localhost:8080/crm","");
            url = url.replace(".jsp","");
            System.out.println(url);
            return "forward:"+url;
        }
        return "/workbench/index";
    }
    @RequestMapping("/settings/user/loginForMain")
    public String loginForMain(User user, HttpSession session, Model model){
        try {
            user = userService.dologin(user);
            session.setAttribute(CrmConstants.LOGIN_USER,user);
        } catch (CrmException e) {
            String mess = e.getMessage();
            model.addAttribute("mess",mess);
            return "../../index";
        }

        return "/workbench/index";
    }

    @RequestMapping("/workbench/activity/queryAllUser")
    @ResponseBody
    public List<User>  queryAllUser(){

        List<User> users = userService.queryAllUser();
        return users;
    }


    @RequestMapping("/workbench/main/loginOut")
    public String  loginOut(HttpSession session){
        session.removeAttribute(CrmConstants.LOGIN_USER);
        return "redirect:/index.jsp";
    }
}
