package com.bootdo.QuotaAd.domain;


//调额记录
public class QuotaRecord {

	//调额订单号
	private String creditNum;
	//姓名
	private String  realName;
	//手机
	private String  mobile;
	//身份证
	private String  cardId;
	//调额方式
	private String  addAndSubstract;
	//调整额度
	private String  amount;
	//调整后授信额度、
	private String   creditLimit;
	//调整后可用授信额度
	private String   usableLimit;
	//调整原因
	private String   reason;
	//调整人
	private String    uname;
	//调整时间
	private String   operateTime;
	//调整前会员等级
	private String beforeVipLevel;
	//调整后会员等级
	private String afterVipLevel;

	public String getBeforeVipLevel() {
		return beforeVipLevel;
	}

	public void setBeforeVipLevel(String beforeVipLevel) {
		this.beforeVipLevel = beforeVipLevel;
	}

	public String getAfterVipLevel() {
		return afterVipLevel;
	}

	public void setAfterVipLevel(String afterVipLevel) {
		this.afterVipLevel = afterVipLevel;
	}

	public String getCreditNum() {
		return creditNum;
	}
	public void setCreditNum(String creditNum) {
		this.creditNum = creditNum;
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
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getAddAndSubstract() {
		return addAndSubstract;
	}
	public void setAddAndSubstract(String addAndSubstract) {
		this.addAndSubstract = addAndSubstract;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}



}
