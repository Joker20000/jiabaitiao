package com.bootdo.Check.domain;

import java.util.Date;

public class TbTongdunResult {
    // 信审编号
    private String  chackNo;
    // 账户编号
    private String accountNo;
    // 账户编号
    private String usetId;
    // 用户ID
    private String ageState;
    // 年龄认证结果
    private String regionState;
    // 实名认证结果
    private String realNameState;
    // 实名认证时间
    private Date realNameTime;
    // 自然人识别结果
    private String naturalPersonState;
    // 自然人识别时间
    private Date naturalPersonTime;
    // 贷前审核结果
    private String loanReviewState;
    // 贷前审核信用分
    private String loanReviewFraction;
    // 贷前审核时间
    private Date loanReviewTime;
    // 审核时间
    private Date chackTime;
    // 实名认证描述
    private String realNameDsc;
    // 自然人识别描述
    private String naturalPersonDsc;
    // 返回其他认证描述信息
    private String chackOtherDsc;

    public String getChackNo() {
        return chackNo;
    }

    public void setChackNo(String chackNo) {
        this.chackNo = chackNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getUsetId() {
        return usetId;
    }

    public void setUsetId(String usetId) {
        this.usetId = usetId;
    }

    public String getAgeState() {
        return ageState;
    }

    public void setAgeState(String ageState) {
        this.ageState = ageState;
    }

    public String getRegionState() {
        return regionState;
    }

    public void setRegionState(String regionState) {
        this.regionState = regionState;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getRealNameState() {
        return realNameState;
    }

    public void setRealNameState(String realNameState) {
        this.realNameState = realNameState == null ? null : realNameState.trim();
    }

    public Date getRealNameTime() {
        return realNameTime;
    }

    public void setRealNameTime(Date realNameTime) {
        this.realNameTime = realNameTime;
    }

    public String getNaturalPersonState() {
        return naturalPersonState;
    }

    public void setNaturalPersonState(String naturalPersonState) {
        this.naturalPersonState = naturalPersonState == null ? null : naturalPersonState.trim();
    }

    public Date getNaturalPersonTime() {
        return naturalPersonTime;
    }

    public void setNaturalPersonTime(Date naturalPersonTime) {
        this.naturalPersonTime = naturalPersonTime;
    }

    public String getLoanReviewState() {
        return loanReviewState;
    }

    public void setLoanReviewState(String loanReviewState) {
        this.loanReviewState = loanReviewState == null ? null : loanReviewState.trim();
    }

    public String getLoanReviewFraction() {
        return loanReviewFraction;
    }

    public void setLoanReviewFraction(String loanReviewFraction) {
        this.loanReviewFraction = loanReviewFraction == null ? null : loanReviewFraction.trim();
    }

    public Date getLoanReviewTime() {
        return loanReviewTime;
    }

    public void setLoanReviewTime(Date loanReviewTime) {
        this.loanReviewTime = loanReviewTime;
    }

    public Date getChackTime() {
        return chackTime;
    }

    public void setChackTime(Date chackTime) {
        this.chackTime = chackTime;
    }

    public String getRealNameDsc() {
        return realNameDsc;
    }

    public void setRealNameDsc(String realNameDsc) {
        this.realNameDsc = realNameDsc;
    }

    public String getNaturalPersonDsc() {
        return naturalPersonDsc;
    }

    public void setNaturalPersonDsc(String naturalPersonDsc) {
        this.naturalPersonDsc = naturalPersonDsc;
    }

    public String getChackOtherDsc() {
        return chackOtherDsc;
    }

    public void setChackOtherDsc(String chackOtherDsc) {
        this.chackOtherDsc = chackOtherDsc;
    }
}