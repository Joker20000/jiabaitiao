package com.bootdo.Repayment.domain;

public class Repay {
    //还款订单号
    private String repaynum;
    //渠道流水号
    private String cflow;
    //借款订单号
    private String loannum;
    //本金还款
    private String ramount;
    //手续费还款
    private String rproccost;
    //待还总额
    private String stayamountsum;
    //逾期费用还款
    private String rovercost;
    //账户编号
    private String accountno;
    //还款方式
    private String repaytype;
    //还款时间
    private  String repaydate;
    //还款状态
    private String repaystate;

    public String getRepaynum() {
        return repaynum;
    }

    public void setRepaynum(String repaynum) {
        this.repaynum = repaynum;
    }

    public String getCflow() {
        return cflow;
    }

    public void setCflow(String cflow) {
        this.cflow = cflow;
    }

    public String getLoannum() {
        return loannum;
    }

    public void setLoannum(String loannum) {
        this.loannum = loannum;
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

    public String getStayamountsum() {
        return stayamountsum;
    }

    public void setStayamountsum(String stayamountsum) {
        this.stayamountsum = stayamountsum;
    }

    public String getRovercost() {
        return rovercost;
    }

    public void setRovercost(String rovercost) {
        this.rovercost = rovercost;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getRepaytype() {
        return repaytype;
    }

    public void setRepaytype(String repaytype) {
        this.repaytype = repaytype;
    }

    public String getRepaydate() {
        return repaydate;
    }

    public void setRepaydate(String repaydate) {
        this.repaydate = repaydate;
    }

    public String getRepaystate() {
        return repaystate;
    }

    public void setRepaystate(String repaystate) {
        this.repaystate = repaystate;
    }
}
