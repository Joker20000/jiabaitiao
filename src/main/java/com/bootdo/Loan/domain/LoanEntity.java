package com.bootdo.Loan.domain;

/**
 * Created by zc on 2018/4/26.
 * 借款实体类
 */
public class LoanEntity {
    //借款订单号
    private String loanNum;
    //企业名称
    private String eName;
    //借款金额
    private String amount;
    //最迟还款日
    private String repayDate;
    //订单状态
    private String orderState;
    //审批状态
    private String chackState;
    //逾期状态
    private String overState;
    //还款状态
    private String repayState;
    //订单生成时间
    private String createDate;

    public String getLoanNum() {
        return loanNum;
    }

    public void setLoanNum(String loanNum) {
        this.loanNum = loanNum;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getChackState() {
        return chackState;
    }

    public void setChackState(String chackState) {
        this.chackState = chackState;
    }

    public String getOverState() {
        return overState;
    }

    public void setOverState(String overState) {
        this.overState = overState;
    }

    public String getRepayState() {
        return repayState;
    }

    public void setRepayState(String repayState) {
        this.repayState = repayState;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
