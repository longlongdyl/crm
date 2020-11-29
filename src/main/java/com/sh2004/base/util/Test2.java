package com.sh2004.base.util;

import com.sh2004.bean.Tran;

import java.lang.reflect.Field;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.base.util
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/28 18:58
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class Test2 {
    public static void main(String[] args) {
        Field[] declaredFields = Tran.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            System.out.println(name);
        }
             /*String[] tableName = {"owner","money","name","expectedDate","customerId",
                    "stage","type","source","activityId","contactsId","createBy","createTime"
                    ,"editBy","editTime","description","contactSummary","nextContactTime"};*/
    }
}
