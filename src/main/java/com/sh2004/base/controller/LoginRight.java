package com.sh2004.base.controller;

import com.sh2004.base.constants.CrmConstants;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.base
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/26 18:52
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Aspect
@Component
public class LoginRight {

    /*@Before("execution(* com.sh2004.service.*.*(..))")
    @RequestMapping
    public String sout() {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        Object user = session.getAttribute(CrmConstants.LOGIN_USER);
        if (null == user) {
             return "redirect:/index";
        }
        return "/workbench/index";
    }*/
}
