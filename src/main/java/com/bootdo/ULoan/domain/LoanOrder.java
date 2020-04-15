package com.bootdo.ULoan.domain;

/**
 * 借款订单
 * @author Administrator
 *
 */
public class LoanOrder {

	/*借款订单编号*/
	private String loanOrderNo;
	/*企业名称*/
	private String companyName;
	/*姓名*/
	private String realName;
	/*邮箱*/
	private String email;
	/*手机号*/
	private String mobile;
	/*订单类型*/
	private String orderType; //订单类型('01'.嘉福商城消费分期,'02'.嘉福白条现金分期)
	/*订单本金*/
	private String nowAmountSum;
	/*订单服务费*/
	private String nowOrderInterestSum;
	/*分期服务费*/
	private String nowInterestSum;
	/*逾期费用*/
	private String nowOverdueSum;
	/*总期数*/
	private String preiodSum;
	/*退款标识*/
	private String refund; //退款标识('00'.无,'01'.有)
	/*累计退款金额*/
	private String refundAmountSum;
	/*待处理退款*/
	private String pendingRefund;//待处理退款('00'.无,'01'.有)
	/*累计抵充金额*/
	private String refundDiAmountSum;
	/*累计还款*/
	private String readyAmountSum;
	/*剩余待还*/
	private String repaymentAmountSum;
	/*还款状态*/
	private String repayState; //还款状态('01'.待还款,'02'.部分还款,'03'.已还清)
	/*订单状态*/
	private String orderState; //订单状态('01'.处理中,'02'.成功,'03'.失败,'04'.部分退款,'05'.全部退款)
	/*渠道订单号*/
	private String channelNo;
	/*放款时间*/
	private String grantTime;
	/*创建时间*/
	private String createTime;
	/*支付通道费用*/
	private String paymentChannelAmount;

	public String getPaymentChannelAmount() {
		return paymentChannelAmount;
	}

	public void setPaymentChannelAmount(String paymentChannelAmount) {
		this.paymentChannelAmount = paymentChannelAmount;
	}

	private String  fullrepaymentTime;     //还清时间
	//借款时间
	private String loantime;



	//新增字段
    /*企业id*/
	private String companyId;
	/*期费率*/
	private String periodRate;
	/*订单固定费用*/
	private String transferCost;
	/*订单比例费用*/
	private String transferRate;
	/*逾期管理费率*/
	private String overdueManageRate;
	/*逾期日费率*/
	private String overdueDayRate;
	/*最迟还清日*/
	private String latestTime;

	private String channel;

	public String getCreateTime() {return createTime;}
	public void setCreateTime(String createTime) {this.createTime = createTime;}
	public String getChannel() {return channel;}
	public void setChannel(String channel) {this.channel = channel;}
	public String getLoantime() {return loantime;}
	public void setLoantime(String loantime) {this.loantime = loantime;}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getPeriodRate() {
		return periodRate;
	}
	public void setPeriodRate(String periodRate) {
		this.periodRate = periodRate;
	}
	public String getTransferCost() {
		return transferCost;
	}
	public void setTransferCost(String transferCost) {
		this.transferCost = transferCost;
	}
	public String getTransferRate() {
		return transferRate;
	}
	public void setTransferRate(String transferRate) {
		this.transferRate = transferRate;
	}
	public String getOverdueManageRate() {
		return overdueManageRate;
	}
	public void setOverdueManageRate(String overdueManageRate) {
		this.overdueManageRate = overdueManageRate;
	}
	public String getOverdueDayRate() {
		return overdueDayRate;
	}
	public void setOverdueDayRate(String overdueDayRate) {
		this.overdueDayRate = overdueDayRate;
	}
	public String getLatestTime() {
		return latestTime;
	}
	public void setLatestTime(String latestTime) {
		this.latestTime = latestTime;
	}
	public String getLoanOrderNo() {
		return loanOrderNo;
	}
	public void setLoanOrderNo(String loanOrderNo) {
		this.loanOrderNo = loanOrderNo;
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
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getNowAmountSum() {
		return nowAmountSum;
	}
	public void setNowAmountSum(String nowAmountSum) {
		this.nowAmountSum = nowAmountSum;
	}
	public String getNowOrderInterestSum() {
		return nowOrderInterestSum;
	}
	public void setNowOrderInterestSum(String nowOrderInterestSum) {
		this.nowOrderInterestSum = nowOrderInterestSum;
	}
	public String getNowInterestSum() {
		return nowInterestSum;
	}
	public void setNowInterestSum(String nowInterestSum) {
		this.nowInterestSum = nowInterestSum;
	}
	public String getNowOverdueSum() {
		return nowOverdueSum;
	}
	public void setNowOverdueSum(String nowOverdueSum) {
		this.nowOverdueSum = nowOverdueSum;
	}
	public String getPreiodSum() {
		return preiodSum;
	}
	public void setPreiodSum(String preiodSum) {
		this.preiodSum = preiodSum;
	}
	public String getRefund() {
		return refund;
	}
	public void setRefund(String refund) {
		this.refund = refund;
	}
	public String getRefundAmountSum() {
		return refundAmountSum;
	}
	public void setRefundAmountSum(String refundAmountSum) {
		this.refundAmountSum = refundAmountSum;
	}
	public String getPendingRefund() {
		return pendingRefund;
	}
	public void setPendingRefund(String pendingRefund) {
		this.pendingRefund = pendingRefund;
	}
	public String getRefundDiAmountSum() {
		return refundDiAmountSum;
	}
	public void setRefundDiAmountSum(String refundDiAmountSum) {
		this.refundDiAmountSum = refundDiAmountSum;
	}
	public String getReadyAmountSum() {
		return readyAmountSum;
	}
	public void setReadyAmountSum(String readyAmountSum) {
		this.readyAmountSum = readyAmountSum;
	}
	public String getRepaymentAmountSum() {
		return repaymentAmountSum;
	}
	public void setRepaymentAmountSum(String repaymentAmountSum) {
		this.repaymentAmountSum = repaymentAmountSum;
	}
	public String getRepayState() {
		return repayState;
	}
	public void setRepayState(String repayState) {
		this.repayState = repayState;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	public String getGrantTime() {
		return grantTime;
	}
	public void setGrantTime(String grantTime) {
		this.grantTime = grantTime;
	}

	public String getFullrepaymentTime() {
		return fullrepaymentTime;
	}
	public void setFullrepaymentTime(String fullrepaymentTime) {
		this.fullrepaymentTime = fullrepaymentTime;
	}

}

