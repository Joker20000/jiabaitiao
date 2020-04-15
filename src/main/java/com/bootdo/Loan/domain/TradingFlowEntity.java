package com.bootdo.Loan.domain;

import java.util.Date;

/**
 * Created by zc on 2017/12/28.
 */
public class TradingFlowEntity {
    /*系统流水编号*/
    private String  flowId;
    /*渠道*/
    private String  channel;
    /*关联系统流水编号*/
    private String  relatedFlowId; //RELATED_FLOW_ID
    /*业务订单号*/
    private String  bizid; //BIZID
    /*渠道订单号*/
    private String  channelBizid; //CHANNEL_BIZID
    /*交易日期*/
    private String  transDate;//TRANS_DATE;
    /*交易时间*/
    private String  transTime;//TRANS_TIME;
    /*交易类型*/
    private String  transType;//TRANS_TYPE;
    /*用户编号*/
    private String  userId;//USER_ID
    /*账号*/
    private String  accountId;//ACCOUNT_ID
    /*账户类型*/
    private String  type;
    /*账户*/
    private String  accountNo;//ACCOUNT_NO
    /*交易金额*/
    private String  amount;//AMOUNT;
    /*变动类型('01'.增加,'02'.减少)*/
    private String  addAndSubtract;
    /*交易后余额*/
    private String  balance;//BALANCE
    /*当前授信额度*/
    private String  creditLimit;
    /*交易后可用授信余额*/
    private String  usableLimit;
    /*交易状态*/
    private String  tradingState;//TRADING_STATE  交易状态('0'.失败,'1'.成功)
   /*退款金额*/
    private String  refundAmount;//REFUND_AMOUNT
   /*接收时间*/
    private Date  receiveTime;//RECEIVE_TIME
    /*最后更新时间*/
    private Date lastTime;//LAST_TIME

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getRelatedFlowId() {
        return relatedFlowId;
    }

    public void setRelatedFlowId(String relatedFlowId) {
        this.relatedFlowId = relatedFlowId;
    }

    public String getBizid() {
        return bizid;
    }

    public void setBizid(String bizid) {
        this.bizid = bizid;
    }

    public String getChannelBizid() {
        return channelBizid;
    }

    public void setChannelBizid(String channelBizid) {
        this.channelBizid = channelBizid;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAddAndSubtract() {
        return addAndSubtract;
    }

    public void setAddAndSubtract(String addAndSubtract) {
        this.addAndSubtract = addAndSubtract;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getUsableLimit() {
        return usableLimit;
    }

    public void setUsableLimit(String usableLimit) {
        this.usableLimit = usableLimit;
    }

    public String getTradingState() {
        return tradingState;
    }

    public void setTradingState(String tradingState) {
        this.tradingState = tradingState;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}

