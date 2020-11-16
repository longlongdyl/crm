package com.sh2004.base.exception;

import com.sh2004.base.constants.CrmExceptionEnum;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.base.exception
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/16 19:02
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class CrmException extends RuntimeException {


    private CrmExceptionEnum exceptionEnum;

    public CrmException(CrmExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMess());//将异常信息放在堆栈信息中
        this.exceptionEnum = exceptionEnum;
    }

    public CrmExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(CrmExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}
