package com.bootdo.Check.domain;

import java.util.Date;

public class TbAccountInfo {
    // 信审编号 USER_ID
    private String chackNo;
    // 账户编号
    private String accountNo;
    // 姓名
    private String realname;
    // 身份证
    private String cardid;
    // 所在省份
    private String liveProvinceName;
    // 所在城市
    private String liveCityName;
    // 所在区
    private String liveDistrictName;
    // 详细地址
    private String liveAddress;
    // 核实地址
    private  String addressSituation;
    // 认证状态
    private String proveState;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;

    private String lastTime;

    private String vipLevel;

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
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
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
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

    public String getProveState() {
        return proveState;
    }

    public void setProveState(String proveState) {
        this.proveState = proveState == null ? null : proveState.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAddressSituation() {
        return addressSituation;
    }

    public void setAddressSituation(String addressSituation) {
        this.addressSituation = addressSituation;
    }
}