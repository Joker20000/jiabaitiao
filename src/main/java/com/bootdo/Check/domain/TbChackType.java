package com.bootdo.Check.domain;

public class TbChackType {
    // 用户编号
    private String  chackNo;
    // 类型ID
    private Integer id;
    // 认证类型
    private String chackTyep;
    // 授信额度
    private String creditLine;
    // 是否有效
    private String valid;

    public String getChackNo() {
        return chackNo;
    }

    public void setChackNo(String chackNo) {
        this.chackNo = chackNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChackTyep() {
        return chackTyep;
    }

    public void setChackTyep(String chackTyep) {
        this.chackTyep = chackTyep == null ? null : chackTyep.trim();
    }

    public String getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(String creditLine) {
        this.creditLine = creditLine == null ? null : creditLine.trim();
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }
}