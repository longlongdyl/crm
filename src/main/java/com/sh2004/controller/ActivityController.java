package com.sh2004.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sh2004.base.bean.PaginationVo;
import com.sh2004.base.bean.ResultVo;
import com.sh2004.base.exception.CrmException;
import com.sh2004.base.util.UUIDUtil;
import com.sh2004.bean.Activity;
import com.sh2004.bean.ActivityQueryVo;
import com.sh2004.bean.ActivityRemark;
import com.sh2004.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/17 16:08
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping("/workbench/activity/queryActivity")
    @ResponseBody
    public PaginationVo queryActivity(ActivityQueryVo activity,@RequestParam(defaultValue = "1",required = false)
            int page,@RequestParam(defaultValue = "1",required = false) int pageSize){

         PageHelper.startPage(page,pageSize);
        List<Map<String,String>> activitys =  activityService.queryActivity(activity);
        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(activitys);
        PaginationVo paginationVo = new PaginationVo(pageInfo);
        return paginationVo;


    }



    @RequestMapping("/workbench/activity/addActivity")
    @ResponseBody
    public ResultVo addActivity(Activity activity){
        ResultVo resultVo = new ResultVo();
        String uuid = UUIDUtil.getUUID();
        activity.setId(uuid);
        try{
                activityService.addActivity(activity);
                resultVo.setOk(true);
                resultVo.setMessage("添加成功");
            }catch (CrmException e){
                resultVo.setOk(false);
                resultVo.setMessage(e.getMessage());
            }
         return resultVo;
    }

    @RequestMapping("/workbench/activity/deleteActivity")
    @ResponseBody
    public ResultVo deleteActivity(String ids){
        ResultVo resultVo = new ResultVo();
        try{
            activityService.deleteActivity(ids);
            resultVo.setOk(true);
            resultVo.setMessage("删除成功");
        }catch (CrmException e){
            resultVo.setOk(false);
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }

    @RequestMapping("/workbench/activity/updateActivity")
    @ResponseBody
    public ResultVo updateActivity(Activity activity){
        ResultVo resultVo = new ResultVo();
        try{
            activityService.updateActivity(activity);
            resultVo.setOk(true);
            resultVo.setMessage("修改成功");
        }catch (CrmException e){
            resultVo.setOk(false);
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }

    @RequestMapping("/workbench/activity/forActivityId")
    @ResponseBody
    public Activity forActivityId(Activity activity){

        return activityService.forActivityId(activity);
    }

    @RequestMapping("/workbench/activity/queryActivityDetailById")
    public String queryActivityDetailById(String id, Model model){

        Activity activity = activityService.activityRemark(id);

        model.addAttribute("activity",activity);
        return "forward:/toView/activity/detail";
    }
}
