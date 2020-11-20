package com.sh2004.base.bean;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.bean
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/20 15:34
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Table(name = "tbl_dic_type")
@NameStyle(Style.normal)
public class CaChe {
    @Id
    private String code;
    private String name;
    private String description;

    private List<CaCheValue> caCheValueList;

    @Override
    public String toString() {
        return "CaChe{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", caCheValueList=" + caCheValueList +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CaCheValue> getCaCheValueList() {
        return caCheValueList;
    }

    public void setCaCheValueList(List<CaCheValue> caCheValueList) {
        this.caCheValueList = caCheValueList;
    }
}
