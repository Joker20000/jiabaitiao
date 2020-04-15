package com.bootdo.Check.domain;

import java.util.Date;

public class TbChack {

    //判断
    private int choose;
    // 用户编号
    private String  chackNo;
    // 账户 ACCOUNT_NO
    private String  accountNo;
    // 用户编号 USER_ID
    private String userId;
    // 姓名
    private String  realname;
    // 手机号
    private String mobile;
    // 认证类型(''01''.小额自动授信,''02''.联系信息,''03''.手机认证) CHACK_TYPE
    private String chackType;
    // 授信额度 CREDIT_LINE
    private String creditLine;
    // 信审状态(''0''.待初审,''1''.初审挂起,''2''.待复审,''3''.复审挂起,''4''.复审回退,''5''.已完成)
    private String chackState;
    // 申请时间 APPLY_TIME
    private String applyTime;
    // 结束时间
    private String lastTime;
    // 信审结果(''0''.审核中,''1''.通过,''2''.拒绝)
    private String chackResult;
    // 是否有效(''0''.无效,''1''.有效)
    private String valid;

    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }

    public String getChackNo() {
        return chackNo;
    }

    public void setChackNo(String chackNo) {
        this.chackNo = chackNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getChackType() {
        return chackType;
    }

    public void setChackType(String chackType) {
        this.chackType = chackType;
    }

    public String getChackState() {
        return chackState;
    }

    public void setChackState(String chackState) {
        this.chackState = chackState;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getChackResult() {
        return chackResult;
    }

    public void setChackResult(String chackResult) {
        this.chackResult = chackResult;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(String creditLine) {
        this.creditLine = creditLine;
    }
}


