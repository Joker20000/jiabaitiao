package com.bootdo.TicketFlow.domain;

import java.util.Date;

public class TicketFlow {

    //券交易流水号
    private String ticketFlowId;
    //交易类别
    private String ticketFlowType;
    //白条订单号
    private String btOrderNo;
    //交易状态
    private String ticketTransactionState;
    //渠道流水号
    private String ticketTransactionFlow;
    //券号
    private String ticketNo;
    //交易时间
    private Date transactionTime;
    //交易开始时间
    private String finishTimeStart;
    //交易结束时间
    private String finishTimeEnd;
    //订单状态
    private String orderState;

    public String getOrderState() {
        return orderState;
    }
    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
    public String getTicketFlowId() {
        return ticketFlowId;
    }
    public void setTicketFlowId(String ticketFlowId) {
        this.ticketFlowId = ticketFlowId;
    }
    public String getTicketFlowType() {
        return ticketFlowType;
    }
    public void setTicketFlowType(String ticketFlowType) {
        this.ticketFlowType = ticketFlowType;
    }
    public String getBtOrderNo() {
        return btOrderNo;
    }
    public void setBtOrderNo(String btOrderNo) {
        this.btOrderNo = btOrderNo;
    }
    public String getTicketTransactionState() {
        return ticketTransactionState;
    }
    public void setTicketTransactionState(String ticketTransactionState) {
        this.ticketTransactionState = ticketTransactionState;
    }
    public String getTicketTransactionFlow() {
        return ticketTransactionFlow;
    }
    public void setTicketTransactionFlow(String ticketTransactionFlow) {
        this.ticketTransactionFlow = ticketTransactionFlow;
    }
    public String getTicketNo() {
        return ticketNo;
    }
    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }
    public Date getTransactionTime() {
        return transactionTime;
    }
    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }
    public String getFinishTimeStart() {
        return finishTimeStart;
    }
    public void setFinishTimeStart(String finishTimeStart) {
        this.finishTimeStart = finishTimeStart;
    }
    public String getFinishTimeEnd() {
        return finishTimeEnd;
    }
    public void setFinishTimeEnd(String finishTimeEnd) {
        this.finishTimeEnd = finishTimeEnd;
    }

}
