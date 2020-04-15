package com.bootdo.Check.domain;

public class Enter extends Checker {
    //企业ID
    private int eid;
    //渠道
    private String channel;
    //渠道管理员ID
    private String cadminid;
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
    //信审记录ID
    private int chackrecordid;
    //信审编号
    private String chackno;
    //授信额度
    private String creditline;
    //审核人ID
    private String chackid;
    //审核人
    private String chackname;
    //审核结果
    private String result;
    //审核意见
    private String opinion;
    //审核类型
    private String type;
    //审核时间
    private String chacktime;
    //判断
    private int choose;
    //文件地址
    private String addr;
    //账户编号
    private String accountno;


    //有效时间
    private String losedate;
    //授信限制审核金额
    private String chacklimit;
    //用户失效时间
    private String overmonth;



    public String getChacklimit() {
        return chacklimit;
    }

    public void setChacklimit(String chacklimit) {
        this.chacklimit = chacklimit;
    }

    public String getOvermonth() {
        return overmonth;
    }

    public void setOvermonth(String overmonth) {
        this.overmonth = overmonth;
    }

    public String getLosedate() {
        return losedate;
    }

    public void setLosedate(String losedate) {
        this.losedate = losedate;
    }


    @Override
    public String toString() {
        return "Enter{" +
                "eid=" + eid +
                ", channel='" + channel + '\'' +
                ", cadminid='" + cadminid + '\'' +
                ", ceid='" + ceid + '\'' +
                ", ename='" + ename + '\'' +
                ", creditcode='" + creditcode + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", linkman='" + linkman + '\'' +
                ", linkduties='" + linkduties + '\'' +
                ", linkphone='" + linkphone + '\'' +
                ", guarant='" + guarant + '\'' +
                ", guarantduties='" + guarantduties + '\'' +
                ", guarantphone='" + guarantphone + '\'' +
                ", guarantide='" + guarantide + '\'' +
                ", state='" + state + '\'' +
                ", createtime='" + createtime + '\'' +
                ", chackrecordid=" + chackrecordid +
                ", chackno='" + chackno + '\'' +
                ", creditline='" + creditline + '\'' +
                ", chackid='" + chackid + '\'' +
                ", chackname='" + chackname + '\'' +
                ", result='" + result + '\'' +
                ", opinion='" + opinion + '\'' +
                ", type='" + type + '\'' +
                ", chacktime='" + chacktime + '\'' +
                ", choose=" + choose +
                ", addr='" + addr + '\'' +
                '}';
    }

    @Override
    public int getEid() {
        return eid;
    }

    @Override
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

    public int getChackrecordid() {
        return chackrecordid;
    }

    public void setChackrecordid(int chackrecordid) {
        this.chackrecordid = chackrecordid;
    }

    @Override
    public String getChackno() {
        return chackno;
    }

    @Override
    public void setChackno(String chackno) {
        this.chackno = chackno;
    }

    public String getCreditline() {
        return creditline;
    }

    public void setCreditline(String creditline) {
        this.creditline = creditline;
    }

    public String getChackid() {
        return chackid;
    }

    public void setChackid(String chackid) {
        this.chackid = chackid;
    }

    public String getChackname() {
        return chackname;
    }

    public void setChackname(String chackname) {
        this.chackname = chackname;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChacktime() {
        return chacktime;
    }

    public void setChacktime(String chacktime) {
        this.chacktime = chacktime;
    }

    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }
}
