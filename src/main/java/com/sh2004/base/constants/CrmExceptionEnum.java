package com.sh2004.base.constants;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.base.constants
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/16 18:58
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public enum CrmExceptionEnum {
    LOGIN_ACCOUNT_ERROR ("1","账号或者密码错误"),
    LOGIN_ACCOUNT_EXPIRE("1","账号已经过期了"),
    LOGIN_ACCOUNT_FORBID("1","账户被禁用"),

    ACTIVITY_ADD_FALSE("2","添加失败");

    private String code;
    private String mess;

    CrmExceptionEnum(String code, String mess) {
        this.code = code;
        this.mess = mess;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }}
