package com.bootdo.RepayPalan.domain;

/**
 * 还款计划
 * @author Administrator
 *
 */
public class RepayPlan {

	/*还款计划编号*/
	private String repayPlanNo;//REPAY_PLAN_NO
	/*借款订单编号*/
	private String loanOrderNo;//LOAN_ORDER_NO
	/*公司ID*/
	private String companyId;//ORDER_TYPE
	/*公司名称*/
	private String companyName;//ORDER_TYPE
	/*真实姓名*/
	private String realname;//ORDER_TYPE
	/*手机号*/
	private String mobile;//ORDER_TYPE
	/*订单类型*/
	private String orderType;//ORDER_TYPE
	/*当前期数*/
	private String nowPeriod;//NOW_PERIOD
	/*总期数*/
	private String periodSum;//PERIOD_SUM
	/*当期应还本金*/
	private String shouldAmount;
	/*当期应还订单费用*/
	private String shouldOrderInterest;
	/*当期应还服务费*/
	private String shouldInterest;
	/*当期逾期费用*/
	private String nowOverdue;//NOW_OVERDUE
	/*当期应还总额*/
	private String repaymentSum;
	/*逾期管理费%*/
	private String transferCost;//TRANSFER_COST
	/*逾期日费用%*/
	private String transferRate;//TRANSFER_RATE
	/*最迟还清日*/
	private String latestTime;//LATEST_TIME
	/*当期累计还款*/
	private String  alreadyRepaidSum;
	/*退款抵充*/
	private String  refundAmount;//REFUND_AMOUNT
	/*当期剩余待还*/
	private String  nowSurplusAmountSum;
	/*还款状态*/
	private String repayState;//REPAY_STATE 还款状态('01'.待还款,'02'.还款中,'03'.已还清)
	/*是否逾期*/
	private String overdueState;//OVERDUE_STATE; 是否逾期('00'未逾期,'01'已逾期)
	/*还清时间*/
	private String fullRepaymentTime;//FULL_REPAYMENT_TIME
	/*开始时间*/
	private String latestTimeStart;
	/*结束时间*/
	private String latestTimeEnd;
	//期费率
	private String periodRate;
	//逾期管理费率
	private String overdueManageRate;
	//逾期日费率
	private String overdueDayRate;
	//该用户第几笔订单
	private String manyReturn;

	private String vipLevel; //会员等级

	private String userChannelId;

	public String getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	//该用户賬戶
	private String accountNo;

	public String getOverdueDayRate() {
		return overdueDayRate;
	}

	public void setOverdueDayRate(String overdueDayRate) {
		this.overdueDayRate = overdueDayRate;
	}

	public String getOverdueManageRate() {return overdueManageRate;}
	public void setOverdueManageRate(String overdueManageRate) {this.overdueManageRate = overdueManageRate;}
	public String getPeriodRate() {return periodRate;}
	public void setPeriodRate(String periodRate) {this.periodRate = periodRate;}
	public String getRepayPlanNo() {
		return repayPlanNo;
	}
	public void setRepayPlanNo(String repayPlanNo) {
		this.repayPlanNo = repayPlanNo;
	}
	public String getLoanOrderNo() {
		return loanOrderNo;
	}
	public void setLoanOrderNo(String loanOrderNo) {
		this.loanOrderNo = loanOrderNo;
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
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
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
	public String getNowPeriod() {
		return nowPeriod;
	}
	public void setNowPeriod(String nowPeriod) {
		this.nowPeriod = nowPeriod;
	}
	public String getPeriodSum() {
		return periodSum;
	}
	public void setPeriodSum(String periodSum) {
		this.periodSum = periodSum;
	}
	public String getShouldAmount() {
		return shouldAmount;
	}
	public void setShouldAmount(String shouldAmount) {
		this.shouldAmount = shouldAmount;
	}
	public String getShouldOrderInterest() {
		return shouldOrderInterest;
	}
	public void setShouldOrderInterest(String shouldOrderInterest) {
		this.shouldOrderInterest = shouldOrderInterest;
	}
	public String getShouldInterest() {
		return shouldInterest;
	}
	public void setShouldInterest(String shouldInterest) {
		this.shouldInterest = shouldInterest;
	}
	public String getNowOverdue() {
		return nowOverdue;
	}
	public void setNowOverdue(String nowOverdue) {
		this.nowOverdue = nowOverdue;
	}
	public String getRepaymentSum() {
		return repaymentSum;
	}
	public void setRepaymentSum(String repaymentSum) {
		this.repaymentSum = repaymentSum;
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
	public String getLatestTime() {
		return latestTime;
	}
	public void setLatestTime(String latestTime) {
		this.latestTime = latestTime;
	}
	public String getAlreadyRepaidSum() {
		return alreadyRepaidSum;
	}
	public void setAlreadyRepaidSum(String alreadyRepaidSum) {
		this.alreadyRepaidSum = alreadyRepaidSum;
	}
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getNowSurplusAmountSum() {
		return nowSurplusAmountSum;
	}
	public void setNowSurplusAmountSum(String nowSurplusAmountSum) {
		this.nowSurplusAmountSum = nowSurplusAmountSum;
	}
	public String getRepayState() {
		return repayState;
	}
	public void setRepayState(String repayState) {
		this.repayState = repayState;
	}
	public String getOverdueState() {
		return overdueState;
	}
	public void setOverdueState(String overdueState) {
		this.overdueState = overdueState;
	}
	public String getFullRepaymentTime() {
		return fullRepaymentTime;
	}
	public void setFullRepaymentTime(String fullRepaymentTime) {
		this.fullRepaymentTime = fullRepaymentTime;
	}
	public String getLatestTimeStart() {
		return latestTimeStart;
	}
	public void setLatestTimeStart(String latestTimeStart) {
		this.latestTimeStart = latestTimeStart;
	}
	public String getLatestTimeEnd() {
		return latestTimeEnd;
	}
	public void setLatestTimeEnd(String latestTimeEnd) {
		this.latestTimeEnd = latestTimeEnd;
	}

	public String getManyReturn() {
		return manyReturn;
	}

	public void setManyReturn(String manyReturn) {
		this.manyReturn = manyReturn;
	}

	public String getUserChannelId() {
		return userChannelId;
	}

	public void setUserChannelId(String userChannelId) {
		this.userChannelId = userChannelId;
	}
}
