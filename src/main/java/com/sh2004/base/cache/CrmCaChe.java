package com.sh2004.base.cache;

import com.sh2004.base.bean.CaChe;

import com.sh2004.base.mapper.CaCheMapper;
import com.sh2004.base.service.CaCheService;
import com.sh2004.base.service.impl.CaCheServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import java.util.*;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.base.cache
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/20 16:18
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class CrmCaChe {

    @Autowired
    private CaCheService caCheService;

    public CrmCaChe(ServletContext servletContext, CaCheService caCheService) {
        List<CaChe> caChes = caCheService.queryAll();
        servletContext.setAttribute("caChes",caChes);
        ResourceBundle bundle =
                ResourceBundle.getBundle("properties.Stage2Possibility");
        Enumeration<String> keys = bundle.getKeys();
        Map<String,String> map = new HashMap<>();
        while (keys.hasMoreElements()){
            String key = keys.nextElement();
            String value = bundle.getString(key);
            map.put(key,value);
        }
        servletContext.setAttribute("StageMap",map);
    }
}
