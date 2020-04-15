package com.bootdo.Repayment.domain;

public class Act extends Repay {
    private int eid;
    private String accountno;
    private String ceid;
    private String ename;
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

    @Override
    public String getAccountno() {
        return accountno;
    }

    @Override
    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }
}
