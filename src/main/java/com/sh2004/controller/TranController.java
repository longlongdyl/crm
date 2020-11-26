package com.sh2004.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sh2004.base.bean.PaginationVo;
import com.sh2004.base.constants.CrmConstants;
import com.sh2004.base.util.DateTimeUtil;
import com.sh2004.bean.*;
import com.sh2004.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    @RequestMapping("/workbench/tran/queryCustomerName")
    @ResponseBody
    public List<String> queryCustomerName(String customerName){
        List<String> list = tranService.queryCustomerName(customerName);
        return list;
    }
    @RequestMapping("/workbench/tran/queryPossibilityByStage")
    @ResponseBody
    public String queryCustomerName(HttpSession session,String stage){
        String value ="";
        Map<String,String> map = (Map<String, String>) session.getServletContext().getAttribute("StageMap");
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> stringStringEntry : set) {
            if (stringStringEntry.getKey().equals(stage)){
                value = stringStringEntry.getValue();
            }

        }
        return value+"%";
    }

    @RequestMapping("/workbench/transaction/updateTran")
    public String updateTran(Tran tran,HttpSession session,Model model){
        if (null != tran.getId()){
            User user = (User) session.getAttribute(CrmConstants.LOGIN_USER);
            tran.setEditBy(user.getName());
            tranService.updateTran(tran);
            Tran tran2 = tranService.queryTranById(tran.getId());
            model.addAttribute("tran",tran2);
            return "forward:/toView/workbench/transaction/detail";
        }
        User user = (User) session.getAttribute(CrmConstants.LOGIN_USER);
        tran.setCreateBy(user.getName());
        tran.setCreateTime(DateTimeUtil.getSysTime());
        tranService.insertTran(tran);
        return "forward:/toView/workbench/transaction/index";
    }


    @RequestMapping("/workbench/transaction/queryTran")
    @ResponseBody
    public PaginationVo queryTran(TranQueryVo tranQueryVo, @RequestParam(defaultValue = "1",required = false)
            int page, @RequestParam(defaultValue = "4",required = false) int pageSize){
        PageHelper.startPage(page,pageSize);
        List<Map<String,String>> trans =  tranService.queryTran(tranQueryVo);
        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(trans);
        PaginationVo paginationVo = new PaginationVo(pageInfo);
        return paginationVo;
    }

    @RequestMapping("/workbench/transaction/queryTranById")
    public String queryTranById(String id, Model model,String edit){
        Tran tran = tranService.queryTranById(id);
        model.addAttribute("tran",tran);
        if (edit ==null){
            return "forward:/toView/workbench/transaction/detail";
        }
        return "forward:/toView/workbench/transaction/edit";
    }



}
