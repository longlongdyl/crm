package com.sh2004.service.impl;

import com.sh2004.base.constants.CrmExceptionEnum;
import com.sh2004.base.exception.CrmException;
import com.sh2004.base.util.DateTimeUtil;
import com.sh2004.base.util.UUIDUtil;
import com.sh2004.bean.*;
import com.sh2004.mapper.CustomerMapper;
import com.sh2004.mapper.CustomerRemarkMapper;
import com.sh2004.mapper.UserMapper;
import com.sh2004.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service.impl
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/28 20:51
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;

    @Override
    public List<Map<String, String>> queryCustomer(CustomerQueryVo customerQueryVo) {
        List<Map<String, String>> list = customerMapper.queryCustomer(customerQueryVo);
        for (Map<String, String> clue : list) {
            String owner = clue.get("owner");
            User user = new User();
            user.setId(owner);
            clue.put("owner",userMapper.selectOne(user).getName());
        }
        return list;
    }

    @Override
    public void addCustomer(Customer customer,String name) {
            customer.setId(UUIDUtil.getUUID());
            customer.setCreateBy(name);
            customer.setCreateTime(DateTimeUtil.getSysTime());
            int  i = customerMapper.insertSelective(customer);
            if (i==0){
                throw new CrmException(CrmExceptionEnum.CUSTOMER_INSERT);
            }

    }

    @Override
    public Customer queryCustomerById(String id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        Example example = new Example(CustomerRemark.class);
        example.createCriteria().andEqualTo("customerId",id);
        List<CustomerRemark> customerRemarks = customerRemarkMapper.selectByExample(example);
        customer.setCustomerRemarkList(customerRemarks);
        return customer;
    }

    @Override
    public void updateCustomerRemark(CustomerRemark customerRemark) {
        int i = customerRemarkMapper.updateByPrimaryKeySelective(customerRemark);
        if (i==0){
            throw new CrmException(CrmExceptionEnum.CLUEREMARK_UPDATE);
        }
    }

    @Override
    public String addCustomerRemark(CustomerRemark customerRemark) {
        String uuid = UUIDUtil.getUUID();
        customerRemark.setId(uuid);
        customerRemark.setCreateTime(DateTimeUtil.getSysTime());
        int i = customerRemarkMapper.insertSelective(customerRemark);
        if (i==0){
            throw new CrmException(CrmExceptionEnum.CLUEREMARK_UPDATE);
        }
        return uuid;
    }

    @Override
    public void deleteCustomerRemark(String id) {
        int i = customerRemarkMapper.deleteByPrimaryKey(id);
        if (i==0){
            throw new CrmException(CrmExceptionEnum.ACTIVITY_DELETE_REMARK);
        }
    }
}
