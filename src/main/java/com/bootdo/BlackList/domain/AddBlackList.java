package com.bootdo.BlackList.domain;


public class AddBlackList {
	//用户id
	private  int userId;
	//操作人
	private String operateMan;
	//操作时间
	private String operateTime;
	//状态
	private String state;
	//创建时间
	private String createTime;
	//更新时间
	private String lastTime;

	//原因
	private String reason;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOperateMan() {
		return operateMan;
	}
	public void setOperateMan(String operateMan) {
		this.operateMan = operateMan;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}




}

