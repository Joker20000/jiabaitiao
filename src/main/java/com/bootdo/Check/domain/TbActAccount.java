package com.bootdo.Check.domain;

import java.util.Date;

public class TbActAccount {
    // 账户
    private String accountNo;
    // 账户类型
    private String type;
    // 账户状态
    private String state;
    // 余额
    private String balance;
    // 授信额度
    private String creditLimit;
    // 可用授信余额
    private String usableLimit;
    // 单笔最大金额
    private String maxAmt;
    // 当日最大金额
    private String dayMaxAmount;
    // 当月最大金额
    private String monthMaxAmount;
    // 当年最大金额
    private String yearMaxAmount;
    // 累计最大金额
    private String accMaxAmount;
    // 首次交易时间
    private Date firstTransactionDate;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date lastTime;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance == null ? null : balance.trim();
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit == null ? null : creditLimit.trim();
    }

    public String getUsableLimit() {
        return usableLimit;
    }

    public void setUsableLimit(String usableLimit) {
        this.usableLimit = usableLimit == null ? null : usableLimit.trim();
    }

    public String getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(String maxAmt) {
        this.maxAmt = maxAmt == null ? null : maxAmt.trim();
    }

    public String getDayMaxAmount() {
        return dayMaxAmount;
    }

    public void setDayMaxAmount(String dayMaxAmount) {
        this.dayMaxAmount = dayMaxAmount == null ? null : dayMaxAmount.trim();
    }

    public String getMonthMaxAmount() {
        return monthMaxAmount;
    }

    public void setMonthMaxAmount(String monthMaxAmount) {
        this.monthMaxAmount = monthMaxAmount == null ? null : monthMaxAmount.trim();
    }

    public String getYearMaxAmount() {
        return yearMaxAmount;
    }

    public void setYearMaxAmount(String yearMaxAmount) {
        this.yearMaxAmount = yearMaxAmount == null ? null : yearMaxAmount.trim();
    }

    public String getAccMaxAmount() {
        return accMaxAmount;
    }

    public void setAccMaxAmount(String accMaxAmount) {
        this.accMaxAmount = accMaxAmount == null ? null : accMaxAmount.trim();
    }

    public Date getFirstTransactionDate() {
        return firstTransactionDate;
    }

    public void setFirstTransactionDate(Date firstTransactionDate) {
        this.firstTransactionDate = firstTransactionDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}