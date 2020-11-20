package com.sh2004.controller;

import com.sh2004.base.bean.ResultVo;
import com.sh2004.base.constants.CrmConstants;
import com.sh2004.base.exception.CrmException;
import com.sh2004.bean.ActivityRemark;
import com.sh2004.bean.User;
import com.sh2004.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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

    @RequestMapping("/workbench/activity/deleteActivityRemark")
    @ResponseBody
    public ResultVo deleteActivityRemark(String id){
        ResultVo resultVo = new ResultVo();
        try{
            activityRemarkService.deleteActivityRemark(id);
            resultVo.setOk(true);
            resultVo.setMessage("删除备注成功");
        }catch (CrmException e){
            resultVo.setOk(false);
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }

    @RequestMapping("/workbench/activity/addActivityRemark")
    @ResponseBody
    public ResultVo addActivityRemark(String remark, String activityId, HttpSession session){
        User user  = (User) session.getAttribute(CrmConstants.LOGIN_USER);
        ResultVo resultVo = new ResultVo();
        try{
            activityRemarkService.addActivityRemark(remark,activityId,user);
            resultVo.setOk(true);
            resultVo.setMessage("添加备注成功");
        }catch (CrmException e){
            resultVo.setOk(false);
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }
}
