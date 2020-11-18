package com.sh2004.base.bean;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.base.bean
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/17 20:39
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class ResultVo {
    private boolean isOk;
    private String message;

    @Override
    public String toString() {
        return "ResultVo{" +
                "isOk=" + isOk +
                ", message='" + message + '\'' +
                '}';
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
