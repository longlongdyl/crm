package com.sh2004.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sh2004.base.bean.PaginationVo;
import com.sh2004.base.bean.ResultVo;
import com.sh2004.base.constants.CrmConstants;
import com.sh2004.base.exception.CrmException;
import com.sh2004.base.util.UUIDUtil;
import com.sh2004.bean.*;
import com.sh2004.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/28 20:51
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/workbench/customer/queryCustomer")
    @ResponseBody
    public PaginationVo queryCustomer(CustomerQueryVo customerQueryVo, @RequestParam(defaultValue = "1",required = false)
            int page, @RequestParam(defaultValue = "4",required = false) int pageSize){
        PageHelper.startPage(page,pageSize);
        List<Map<String,String>> clues =  customerService.queryCustomer(customerQueryVo);
        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(clues);
        PaginationVo paginationVo = new PaginationVo(pageInfo);
        return paginationVo;
    }
    @RequestMapping("/workbench/customer/addCustomer")
    @ResponseBody
    public ResultVo addCustomer(Customer customer , HttpSession session){
        ResultVo resultVo = new ResultVo();
        User user = (User) session.getAttribute(CrmConstants.LOGIN_USER);
        String name = user.getName();
        try{
            customerService.addCustomer(customer,name);
            resultVo.setOk(true);
            resultVo.setMessage("添加客户成功");
        }catch (CrmException e){
            resultVo.setOk(false);
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }


    @RequestMapping("/workbench/customer/queryCustomerById")
    public String queryCustomerById(String id, Model model){
        Customer customer = customerService.queryCustomerById(id);
        model.addAttribute("customer",customer);
        return "forward:/toView/workbench/customer/detail";
    }

    @RequestMapping("/workbench/customer/updateCustomerRemark")
    @ResponseBody
    public ResultVo updateCustomerRemark(CustomerRemark customerRemark){
        ResultVo resultVo = new ResultVo();
        try{
            customerService.updateCustomerRemark(customerRemark);
            resultVo.setOk(true);
            resultVo.setMessage("修改备注成功");
        }catch (CrmException e){
            resultVo.setOk(false);
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }

    @RequestMapping("/workbench/customer/addCustomerRemark")
    @ResponseBody
    public ResultVo addCustomerRemark(CustomerRemark customerRemark, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        customerRemark.setCreateBy(user.getName());
        ResultVo resultVo = new ResultVo();
        try{
            String newId = customerService.addCustomerRemark(customerRemark);
            resultVo.setOk(true);
            resultVo.setMessage("添加线索备注成功"+","+newId);
        }catch (CrmException e){
            resultVo.setOk(false);
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }

    @RequestMapping("/workbench/customer/deleteCustomerRemark")
    @ResponseBody
    public ResultVo deleteCustomerRemark(String id){
        ResultVo resultVo = new ResultVo();
        try{
            customerService.deleteCustomerRemark(id);
            resultVo.setOk(true);
            resultVo.setMessage("删除备注成功");
        }catch (CrmException e){
            resultVo.setOk(false);
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }

}
