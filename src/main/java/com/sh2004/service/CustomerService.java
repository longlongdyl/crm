package com.sh2004.service;

import com.sh2004.bean.Customer;
import com.sh2004.bean.CustomerQueryVo;
import com.sh2004.bean.CustomerRemark;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.service
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/28 20:51
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface CustomerService {
    List<Map<String, String>> queryCustomer(CustomerQueryVo customerQueryVo);

    void addCustomer(Customer customer,String name);

    Customer queryCustomerById(String id);

    void updateCustomerRemark(CustomerRemark customerRemark);

    String addCustomerRemark(CustomerRemark customerRemark);

    void deleteCustomerRemark(String id);
}
