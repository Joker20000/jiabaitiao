package com.bootdo.BackerUser.domain;

public class Enterprise {
    //企业ID
    private int eid;
    //渠道
    private String channel;
    //渠道管理员ID
    private String cadminid;
/*    //渠道管理员
    private String cadminname;*/
    //渠道企业ID
    private String ceid;
    //企业名称
    private String ename;
    //统一信用代码
    private String creditcode;
    //企业办公地址
    private String address;
    //企业联系电话
    private String telephone;
    //联系人
    private String linkman;
    //联系人职务
    private String linkduties;
    //联系人手机
    private String linkphone;
    //担保人
    private String guarant;
    //担保人职务
    private String guarantduties;
    //担保人手机
    private String guarantphone;
    //担保人身份证
    private String guarantide;
    //状态
    private String state;
    //创建时间
    private String createtime;
    //申请时间
    private String applytime;

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
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

    public String getCadminid() {
        return cadminid;
    }

    public void setCadminid(String cadminid) {
        this.cadminid = cadminid;
    }

   /* public String getCadminname() {
        return cadminname;
    }

    public void setCadminname(String cadminname) {
        this.cadminname = cadminname;
    }*/

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkduties() {
        return linkduties;
    }

    public void setLinkduties(String linkduties) {
        this.linkduties = linkduties;
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

    public String getGuarantduties() {
        return guarantduties;
    }

    public void setGuarantduties(String guarantduties) {
        this.guarantduties = guarantduties;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
