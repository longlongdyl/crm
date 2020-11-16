package com.sh2004.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/16 19:29
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ViewController {

    @RequestMapping({"/toView/{modalView}/{view}","/toView/{view}"})
    public String toView(

            @PathVariable(value = "modalView",required = false) String modalView,
            @PathVariable("view") String view){

        if(modalView != null){

            return modalView + File.separator + view;
        }else{
            return view;
        }
    }
}
