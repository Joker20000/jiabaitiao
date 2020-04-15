package com.bootdo.BackerUser.domain;

public class BackExcel {
    //企业ID
    private int eid;
    //渠道
    private String channel;
    //渠道企业ID
    private String ceid;
    //企业名称
    private String ename;
    //统一信用代码
    private String creditcode;
    //联系人
    private String linkman;
    //联系人手机
    private String linkphone;
    //担保人
    private String guarant;
    //担保人手机
    private String guarantphone;
    //担保人身份证
    private String guarantide;
    //状态
    private String state;
    //申请时间
    private String applytime;
    //终审时间
    private String lastinstancetime;

    private String chackno;

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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCeid() {
        return ceid;
    }

    public void setCeid(String ceid) {
        this.ceid = ceid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getCreditcode() {
        return creditcode;
    }

    public void setCreditcode(String creditcode) {
        this.creditcode = creditcode;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
    }

    public String getGuarant() {
        return guarant;
    }

    public void setGuarant(String guarant) {
        this.guarant = guarant;
    }

    public String getGuarantphone() {
        return guarantphone;
    }

    public void setGuarantphone(String guarantphone) {
        this.guarantphone = guarantphone;
    }

    public String getGuarantide() {
        return guarantide;
    }

    public void setGuarantide(String guarantide) {
        this.guarantide = guarantide;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }

    public String getLastinstancetime() {
        return lastinstancetime;
    }

    public void setLastinstancetime(String lastinstancetime) {
        this.lastinstancetime = lastinstancetime;
    }
}
