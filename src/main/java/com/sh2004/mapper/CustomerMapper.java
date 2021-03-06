package com.sh2004.mapper;

import com.sh2004.bean.Customer;
import com.sh2004.bean.CustomerQueryVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.mapper
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/23 19:20
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface CustomerMapper extends Mapper<Customer> {
    List<Map<String, String>> queryCustomer(CustomerQueryVo customerQueryVo);
}
