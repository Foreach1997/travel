package com.bs.travel.vo;

import java.io.Serializable;

public class CustomizedInfoVO implements Serializable {

    private String id;

    private String customizedInfo;

//    private String customizedId;
//
//    public String getCustomizedId() {
//        return customizedId;
//    }
//
//    public void setCustomizedId(String customizedId) {
//        this.customizedId = customizedId;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomizedInfo() {
        return customizedInfo;
    }

    public void setCustomizedInfo(String customizedInfo) {
        this.customizedInfo = customizedInfo;
    }
}
