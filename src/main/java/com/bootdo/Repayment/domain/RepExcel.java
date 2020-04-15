package com.bootdo.Repayment.domain;


public class RepExcel {

    //还款订单号
    private String repaynum;
    //用户渠道
    private String channel;
    //渠道企业ID
    private String ceid;
    //企业名称
    private String ename;
    //本金还款
    private String ramount;
    //手续费还款
    private String rproccost;
    //逾期费用还款
    private String rovercost;
    //还总额
    private String stayamountsum;
    //还款时间
    private  String repaydate;
    //还款方式
    private String repaytype;
    //借款订单号
    private String loannum;
    //渠道流水号
    private String cflow;

    public String getRepaynum() {
        return repaynum;
    }

    public void setRepaynum(String repaynum) {
        this.repaynum = repaynum;
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

    public String getRamount() {
        return ramount;
    }

    public void setRamount(String ramount) {
        this.ramount = ramount;
    }

    public String getRproccost() {
        return rproccost;
    }

    public void setRproccost(String rproccost) {
        this.rproccost = rproccost;
    }

    public String getRovercost() {
        return rovercost;
    }

    public void setRovercost(String rovercost) {
        this.rovercost = rovercost;
    }

    public String getStayamountsum() {
        return stayamountsum;
    }

    public void setStayamountsum(String stayamountsum) {
        this.stayamountsum = stayamountsum;
    }

    public String getRepaydate() {
        return repaydate;
    }

    public void setRepaydate(String repaydate) {
        this.repaydate = repaydate;
    }

    public String getRepaytype() {
        return repaytype;
    }

    public void setRepaytype(String repaytype) {
        this.repaytype = repaytype;
    }

    public String getLoannum() {
        return loannum;
    }

    public void setLoannum(String loannum) {
        this.loannum = loannum;
    }

    public String getCflow() {
        return cflow;
    }

    public void setCflow(String cflow) {
        this.cflow = cflow;
    }
}
