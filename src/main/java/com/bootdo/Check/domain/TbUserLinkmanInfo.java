package com.bootdo.Check.domain;

import java.util.Date;

public class TbUserLinkmanInfo{
    // 账户编号
    private String accountNo;
    private Integer id;
    // 信审编号 CHACK_NO
    private String chackNo;
    // 关系 RELATION
    private String relation;
    // 姓名 REALNAME
    private String realname;
    // 联系电话 MOBILE
    private String mobile;
    // 联系地址 ADDRESS
    private String address;
    // 核实情况 SITUATION
    private String situation;
    // 数据状态(''0''失效,''1''有效) DATA_STATE
    private String dataState;
    // 创建时间 CREATE_TIME
    private Date createTime;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChackNo() {
        return chackNo;
    }

    public void setChackNo(String chackNo) {
        this.chackNo = chackNo;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}