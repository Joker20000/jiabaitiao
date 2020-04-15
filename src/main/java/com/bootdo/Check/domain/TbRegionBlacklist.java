package com.bootdo.Check.domain;

import java.util.Date;

public class TbRegionBlacklist {
    // id
    private int id;
    // 地区编号
    private String REGION_CODE;
    // 地区名称
    private String REGION_NAME;
    // 创建时间
    private Date CREATE_TIME;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getREGION_CODE() {
        return REGION_CODE;
    }

    public void setREGION_CODE(String REGION_CODE) {
        this.REGION_CODE = REGION_CODE;
    }

    public String getREGION_NAME() {
        return REGION_NAME;
    }

    public void setREGION_NAME(String REGION_NAME) {
        this.REGION_NAME = REGION_NAME;
    }

    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Date CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }
}
