package com.bootdo.AccounTtrading.domain;

/**
 * 用户实体
 * @author Administrator
 *
 */
public class AccounTtrading {

	/*系统流水编号*/
	private String flowId;
	/*企业ID*/
	private String companyId;
	/*企业名称*/
	private String companyName;
	/*姓名*/
	private String realName;
	/*邮箱*/
	private String email;
	/*手机号*/
	private String mobile;
	/*交易金额*/
	private String  amount;//AMOUNT;
	/*变动类型('01'.增加,'02'.减少)*/
	private String  addAndSubtract;
	/*当前授信额度*/
	private String  creditLimit;
	/*交易后可用授信余额*/
	private String  usableLimit;
	/*交易类型*/
	private String  transType;//TRANS_TYPE;
	/*业务订单号*/
	private String  bizid;
	/*渠道订单号*/
	private String  channelBizid; //CHANNEL_BIZID
	/*交易时间*/
	private String  transTime;//TRANS_TIME;
	/*交易开始日期*/
	private String  transTimeStart;
	/*交易结束日期*/
	private String  transTimeEnd;
	/*还款方式*/
	private String  repayFlag;
	/*还款支付方式*/
    private String  repayType;
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAddAndSubtract() {
		return addAndSubtract;
	}
	public void setAddAndSubtract(String addAndSubtract) {
		this.addAndSubtract = addAndSubtract;
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
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getBizid() {
		return bizid;
	}
	public void setBizid(String bizid) {
		this.bizid = bizid;
	}
	public String getChannelBizid() {
		return channelBizid;
	}
	public void setChannelBizid(String channelBizid) {
		this.channelBizid = channelBizid;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public String getTransTimeStart() {
		return transTimeStart;
	}
	public void setTransTimeStart(String transTimeStart) {
		this.transTimeStart = transTimeStart;
	}
	public String getTransTimeEnd() {
		return transTimeEnd;
	}
	public void setTransTimeEnd(String transTimeEnd) {
		this.transTimeEnd = transTimeEnd;
	}
	public String getRepayFlag() {
		return repayFlag;
	}
	public void setRepayFlag(String repayFlag) {
		this.repayFlag = repayFlag;
	}
	public String getRepayType() {
		return repayType;
	}
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}
    
    
	
}
