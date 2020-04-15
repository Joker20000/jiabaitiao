package com.bootdo.QuotaAd.domain;


//额度调整
public class QuotaAdjustment {

	//用户id
	private  String userId;
	//企业名称
	private String companyName;
	//姓名
	private String realName;
	//手机号
	private String mobile;
	//身份证
	private String cardId;
	//授信额度
	private String creditLimit;
	//可用授信额度
	private String usableLimit;
	//操作
	private String operation;
	//会员等级
	private String vipLevel;

	public String getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCreditLimit() {
		return creditLimit;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public QuotaAdjustment(){};

	public QuotaAdjustment(String userId, String companyName, String realName, String mobile, String cardId,
						   String creditLimit, String usableLimit,String operation) {
		super();
		this.userId = userId;
		this.companyName = companyName;
		this.realName = realName;
		this.mobile = mobile;
		this.cardId = cardId;
		this.creditLimit = creditLimit;
		this.usableLimit = usableLimit;
		this.operation = operation;
	}
	@Override
	public String toString() {
		return "QuotaAdjustMent [userId=" + userId + ", companyName=" + companyName + ", realName=" + realName
				+ ", mobile=" + mobile + ", cardId=" + cardId + ", creditLimit=" + creditLimit + ", usableLimit="
				+ usableLimit + ", operation=" + operation + "]";
	}








}
