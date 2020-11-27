package com.sh2004.base.cache;

import com.sh2004.base.constants.CrmConstants;
import com.sh2004.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.base.cache
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/27 17:17
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user  = (User) request.getSession().getAttribute(CrmConstants.LOGIN_USER);
        String url = request.getRequestURL().toString();
        if (url.contains("login")){
            return true;
        }
        if (user != null){
            return true;
        }
    //    response.sendRedirect("/crm");
        request.getSession().setAttribute("url",url);
        response.sendRedirect("/crm/login.jsp");
       return false;
    }
}
