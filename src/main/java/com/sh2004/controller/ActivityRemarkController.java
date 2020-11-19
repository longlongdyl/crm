package com.sh2004.controller;

import com.sh2004.base.bean.ResultVo;
import com.sh2004.base.exception.CrmException;
import com.sh2004.bean.ActivityRemark;
import com.sh2004.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/19 19:13
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ActivityRemarkController {
    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/updateActivityRemark")
    @ResponseBody
    public ResultVo updateActivityRemark(ActivityRemark activityRemark){
        ResultVo resultVo = new ResultVo();
        try{
            activityRemarkService.updateActivityRemark(activityRemark);
            resultVo.setOk(true);
            resultVo.setMessage("修改备注成功");
        }catch (CrmException e){
            resultVo.setOk(false);
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }
}
