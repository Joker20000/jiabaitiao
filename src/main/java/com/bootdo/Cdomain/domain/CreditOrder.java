package com.bootdo.Cdomain.domain;




/*
 * 授信订单表
 *
 * */
public class CreditOrder {


	//授信订单号
	private  String creditNum;

	//操作人id
	private int operateManId;
	//操作时间
	private String operateTime;
	//授信描述
	private String des;
	//状态
	private String state;

	private String beforeVipLevel;

	private String afterVipLevel;

	public String getAfterVipLevel() {
		return afterVipLevel;
	}

	public void setAfterVipLevel(String afterVipLevel) {
		this.afterVipLevel = afterVipLevel;
	}

	public String getBeforeVipLevel() {
		return beforeVipLevel;
	}

	public void setBeforeVipLevel(String beforeVipLevel) {
		this.beforeVipLevel = beforeVipLevel;
	}

	public String getCreditNum() {
		return creditNum;
	}
	public void setCreditNum(String creditNum) {
		this.creditNum = creditNum;
	}
	public int getOperateManId() {
		return operateManId;
	}
	public void setOperateManId(int operateManId) {
		this.operateManId = operateManId;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "CreditOrder [creditNum=" + creditNum + ", operateManId=" + operateManId + ", operateTime=" + operateTime
				+ ", des=" + des + ", state=" + state + "]";
	}





}
