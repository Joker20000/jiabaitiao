package com.bootdo.Account.domain;

public class Accounter {
    //交易流水号
    private int tradeflow;
    //关联订单号
    private String relevantorder;
    //账户编号
    private String accountno;
    //交易金额
    private String amount;
    //变动类型
    private String addorsub;
    //业务类型
    private String bustype;
    //交易后授信额度
    private String creditlimit;
    //交易后可用授信余额
    private String usablelimit;
    //交易状态
    private String state;
    //交易时间
    private String createtime;

    public int getTradeflow() {
        return tradeflow;
    }

    public void setTradeflow(int tradeflow) {
        this.tradeflow = tradeflow;
    }

    public String getRelevantorder() {
        return relevantorder;
    }

    public void setRelevantorder(String relevantorder) {
        this.relevantorder = relevantorder;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAddorsub() {
        return addorsub;
    }

    public void setAddorsub(String addorsub) {
        this.addorsub = addorsub;
    }

    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }

    public String getCreditlimit() {
        return creditlimit;
    }

    public void setCreditlimit(String creditlimit) {
        this.creditlimit = creditlimit;
    }

    public String getUsablelimit() {
        return usablelimit;
    }

    public void setUsablelimit(String usablelimit) {
        this.usablelimit = usablelimit;
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
