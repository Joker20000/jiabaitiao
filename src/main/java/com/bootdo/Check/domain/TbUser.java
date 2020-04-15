package com.bootdo.Check.domain;

import java.util.Date;

public class TbUser {
    // 账户编号
    private String accountNo;
    // 信审编号 USER_ID
    private String chackNo;
    // 关系
    private String relation;
    // 姓名 REALNAME
    private String realname;
    // 联系电话 MOBILE
    private String mobile;
    // 联系地址
    private String address;
    // 核实情况
    private String dataState;
    // 信审结果
    private String chackResult;
    // 认证类型
    private String chackTyep;
    // 授信额度
    private String creditLine;
    // 是否有效
    private String valid;
    // 判断
    private int choose;
    // 信审状态
    private String chackState;
    // 贷前审核信用分
    private String loanReviewFraction;
    // 贷前审核结果
    private String loanReviewState;
    // 自然人识别结果
    private String naturalPersonState;
    // 实名认证结果
    private String ageState;
    // 用户编号
    private Integer userId;
    // 用户渠道ID(''01''.嘉福平台) USER_CHANNEL_ID
    private String userChannelId;
    // 嘉福用户 JF_USER_ID
    private String jfUserId;
    // 微信OPENID WX_OPENID
    private String wxOpenid;
    // 用户组(''01''.嘉福,''02''.汇卡宝,''03''.中航) GROUP_ID
    private String groupId;
    // 企业ID COMPANY_ID
    private String companyId;
    // 企业名称 COMPANY_NAME
    private String companyName;
    // 昵称 NICKNAME
    private String nickname;
    // 身份证 CARDID
    private String cardid;
    // 邮箱 EMAIL
    private String email;
    // 所在省份 LIVE_PROVINCE_NAME
    private String liveProvinceName;
    // 所在城市
    private String liveCityName;
    // 所在区
    private String liveDistrictName;
    // 详细地址
    private String liveAddress;
    // 注册时间
    private Date registerTime;
    // 最后登录时间
    private String lastLoginTime;
    // 用户状态
    private String userState;
    // 认证状态
    private String proveState;
    // 认证提交时间
    private String proveSubmitTime;
    // 认证审核时间
    private String proveAuditTime;


    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public String getChackResult() {
        return chackResult;
    }

    public void setChackResult(String chackResult) {
        this.chackResult = chackResult;
    }

    public String getChackTyep() {
        return chackTyep;
    }

    public void setChackTyep(String chackTyep) {
        this.chackTyep = chackTyep;
    }

    public String getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(String creditLine) {
        this.creditLine = creditLine;
    }

    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getChackState() {
        return chackState;
    }

    public void setChackState(String chackState) {
        this.chackState = chackState;
    }

    public String getLoanReviewFraction() {
        return loanReviewFraction;
    }

    public void setLoanReviewFraction(String loanReviewFraction) {
        this.loanReviewFraction = loanReviewFraction;
    }

    public String getLoanReviewState() {
        return loanReviewState;
    }

    public void setLoanReviewState(String loanReviewState) {
        this.loanReviewState = loanReviewState;
    }

    public String getNaturalPersonState() {
        return naturalPersonState;
    }

    public void setNaturalPersonState(String naturalPersonState) {
        this.naturalPersonState = naturalPersonState;
    }

    public String getAgeState() {
        return ageState;
    }

    public void setAgeState(String ageState) {
        this.ageState = ageState;
    }

    public String getChackNo() {
        return chackNo;
    }

    public void setChackNo(String chackNo) {
        this.chackNo = chackNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserChannelId() {
        return userChannelId;
    }

    public void setUserChannelId(String userChannelId) {
        this.userChannelId = userChannelId == null ? null : userChannelId.trim();
    }

    public String getJfUserId() {
        return jfUserId;
    }

    public void setJfUserId(String jfUserId) {
        this.jfUserId = jfUserId == null ? null : jfUserId.trim();
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getLiveProvinceName() {
        return liveProvinceName;
    }

    public void setLiveProvinceName(String liveProvinceName) {
        this.liveProvinceName = liveProvinceName == null ? null : liveProvinceName.trim();
    }

    public String getLiveCityName() {
        return liveCityName;
    }

    public void setLiveCityName(String liveCityName) {
        this.liveCityName = liveCityName == null ? null : liveCityName.trim();
    }

    public String getLiveDistrictName() {
        return liveDistrictName;
    }

    public void setLiveDistrictName(String liveDistrictName) {
        this.liveDistrictName = liveDistrictName == null ? null : liveDistrictName.trim();
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress == null ? null : liveAddress.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState == null ? null : userState.trim();
    }

    public String getProveState() {
        return proveState;
    }

    public void setProveState(String proveState) {
        this.proveState = proveState == null ? null : proveState.trim();
    }

    public String getProveSubmitTime() {
        return proveSubmitTime;
    }

    public void setProveSubmitTime(String proveSubmitTime) {
        this.proveSubmitTime = proveSubmitTime;
    }

    public String getProveAuditTime() {
        return proveAuditTime;
    }

    public void setProveAuditTime(String proveAuditTime) {
        this.proveAuditTime = proveAuditTime;
    }
}