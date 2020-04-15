package com.bootdo.Cdomain.domain;

import java.util.Date;

/**
 * Created by zc on 2017/12/28.
 */
public class TradingFlowEntity {
   
    private String  flowId;
   
    private String  channel;
  
    private String  relatedFlowId; //RELATED_FLOW_ID
   
    private String  bizid; //BIZID
   
  
   
    private String  channelBizid; //CHANNEL_BIZID
  
    private String  transDate;//TRANS_DATE;
 
    private String  transTime;//TRANS_TIME;
   
    private String  transType;//TRANS_TYPE;
   
    private String  userId;//USER_ID
  
    private String  accountId;//ACCOUNT_ID
 
    private String  type;
 
    private String  accountNo;//ACCOUNT_NO
 
    private String  amount;//AMOUNT;
  
    private String  addAndSubtract;
 
    private String  balance;//BALANCE
 
    private String  creditLimit;
   
    private String  usableLimit;
   
    private String  tradingState;//TRADING_STATE  浜ゆ槗鐘舵�('0'.澶辫触,'1'.鎴愬姛)
  
    private String  refundAmount;//REFUND_AMOUNT
  
    private Date  receiveTime;//RECEIVE_TIME
  
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

