package com.bootdo.UserManage.domain;

/**
 * 用户实体
 * @author Administrator
 *
 */
public class UserJBT {

	private Integer id;
	private String userId;
	private String realName;
	private String mobile;
	private String email;
	private String userChannelId;
	private String companyId;
	private String companyName;
	private String cardid;
	private String proveState;
	private String register;
	private String proveSubmitTime;
	private String proveAuditTime;
	private  String userState;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserChannelId() {
		return userChannelId;
	}

	public void setUserChannelId(String userChannelId) {
		this.userChannelId = userChannelId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getProveState() {
		return proveState;
	}

	public void setProveState(String proveState) {
		this.proveState = proveState;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
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

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	@Override
	public String toString() {
		return "UserJBT [id=" + id + ", userId=" + userId + ", realName=" + realName + ", mobile=" + mobile + ", email="
				+ email + ", userChannelId=" + userChannelId + ", companyId=" + companyId + ", companyName=" + companyName
				+ ", cardid=" + cardid + ", proveState=" + proveState + ", register=" + register + ", proveSubmitTime="
				+ proveSubmitTime + ", proveAuditTime=" + proveAuditTime + ", userState=" + userState + "]";
	}

	
	  
	
	
}
