package com.bootdo.Account.domain;


public class FlowExcel {
    //交易流水号
    private int tradeflow;
    //用户渠道
    private String channel;
    //渠道企业ID
    private String ceid;
    //企业名称
    private String ename;
    //交易金额
    private String amount;
    //交易后授信额度
    private String creditlimit;
    //交易后可用授信余额
    private String usablelimit;
    //业务类型
    private String bustype;
    //白条订单号
    private String accountno;
    //交易时间
    private String createtime;

    public int getTradeflow() {
        return tradeflow;
    }

    public void setTradeflow(int tradeflow) {
        this.tradeflow = tradeflow;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
