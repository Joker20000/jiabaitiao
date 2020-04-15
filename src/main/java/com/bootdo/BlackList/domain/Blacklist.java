package com.bootdo.BlackList.domain;
/*
 * 黑名单实体类
 * */
public class Blacklist {


	//用户编号
	private   int  userId;
	//企业编号
	private String  companyId;
	//企业名称
	private String companyName;
	//员工姓名
	private String  realname;
	//员工邮箱
	private String email;
	//手机
	private String mobile;
	//身份证
	private String cardid;
	//备注
	private String reason;
	//状态
	private String state; //00无效 01 有效
	//操作人
	private String operateMan;
	//操作时间
	private String operateTime;

	public String getCompanyId() {
		return companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getRealname() {
		return realname;
	}
	public String getEmail() {
		return email;
	}
	public String getMobile() {
		return mobile;
	}
	public String getCardid() {
		return cardid;
	}
	public String getReason() {
		return reason;
	}
	public String getState() {
		return state;
	}
	public String getOperateMan() {
		return operateMan;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setOperateMan(String operateMan) {
		this.operateMan = operateMan;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}



}
