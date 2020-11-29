package com.sh2004.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sh2004.base.bean.PaginationVo;
import com.sh2004.base.bean.ResultVo;
import com.sh2004.base.exception.CrmException;
import com.sh2004.bean.*;
import com.sh2004.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/21 19:58
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ClueController {
    @Autowired
    private ClueService clueService;

    @RequestMapping("/workbench/clue/queryClue")
    @ResponseBody
    public PaginationVo queryClue(ClueQueryVo clueQueryVo, @RequestParam(defaultValue = "1",required = false)
            int page, @RequestParam(defaultValue = "4",required = false) int pageSize){
        PageHelper.startPage(page,pageSize);
        List<Map<String,String>> clues =  clueService.queryActivity(clueQueryVo);
        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(clues);
        PaginationVo paginationVo = new PaginationVo(pageInfo);
        return paginationVo;
    }
    @RequestMapping("/workbench/clue/queryClueById")
    public String queryActivityDetailById(String id, Model model){

        Clue clue = clueService.clueRemark(id);
        model.addAttribute("clue",clue);
        return "forward:/toView/workbench/clue/detail";
    }


    @RequestMapping("/workbench/clue/deleteActivity")
    @ResponseBody
    public ResultVo deleteActivity(String id){
        ResultVo resultVo = new ResultVo();
        try{
            clueService.deleteActivity(id);
            resultVo.setOk(true);
            resultVo.setMessage("删除市场关联成功");
        }catch (CrmException e){
            resultVo.setOk(false);
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }

    @RequestMapping("/workbench/clue/convert")
    public String convert(String clueId,Model model){
        Clue clue = clueService.clueRemark(clueId);
        model.addAttribute("clue",clue);
        return "/workbench/clue/convert";
    }

    @RequestMapping("/workbench/chart/clue/queryClueEcharts")
    @ResponseBody
    public List<Map<String, String>> queryClueEcharts(){
        List<Map<String, String>> list = clueService.queryClueEcharts();
        return list;
    }

}
