package com.bootdo.Check.domain;


public class CheExcel {
    //信审编号
    private String chackno;
    //用户ID
    private int eid;
    //企业名称
    private String ename;
    //申请时间
    private String applytime;
    //信审状态
    private String chackstate;
    //初审时间
    private String chuTime;
    //初审结果
    private String chuRes;
    //初审人
    private String chuName;
    //复审时间
    private String fuTime;
    //复审结果
    private String fuRes;
    //复审人
    private String fuName;
    //终审时间
    private String zhongTime;
    //终审结果
    private String zhongRes;
    //终审人
    private String zhongName;
    public String getChuTime() {
        return chuTime;
    }

    public void setChuTime(String chuTime) {
        this.chuTime = chuTime;
    }

    public String getChuRes() {
        return chuRes;
    }

    public void setChuRes(String chuRes) {
        this.chuRes = chuRes;
    }

    public String getChuName() {
        return chuName;
    }

    public void setChuName(String chuName) {
        this.chuName = chuName;
    }

    public String getChackno() {
        return chackno;
    }

    public void setChackno(String chackno) {
        this.chackno = chackno;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }

    public String getChackstate() {
        return chackstate;
    }

    public void setChackstate(String chackstate) {
        this.chackstate = chackstate;
    }

    public String getFuTime() {
        return fuTime;
    }

    public void setFuTime(String fuTime) {
        this.fuTime = fuTime;
    }

    public String getFuRes() {
        return fuRes;
    }

    public void setFuRes(String fuRes) {
        this.fuRes = fuRes;
    }

    public String getFuName() {
        return fuName;
    }

    public void setFuName(String fuName) {
        this.fuName = fuName;
    }

    public String getZhongTime() {
        return zhongTime;
    }

    public void setZhongTime(String zhongTime) {
        this.zhongTime = zhongTime;
    }

    public String getZhongRes() {
        return zhongRes;
    }

    public void setZhongRes(String zhongRes) {
        this.zhongRes = zhongRes;
    }

    public String getZhongName() {
        return zhongName;
    }

    public void setZhongName(String zhongName) {
        this.zhongName = zhongName;
    }
}
