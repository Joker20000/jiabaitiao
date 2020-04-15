package com.bootdo.RepayFlow.domain;

/**
 * 还款流水
 * @author Administrator
 *
 */
public class RepayFlow {
	/*系统流水号*/
	private String repaySerialNo;//REPAY_SERIAL_NO
	/*时间*/
	private String finishTime;//FINISH_TIME
	/*还款金额*/
	private String repayAmountSum;//REPAY_AMOUNT_SUM
	/*当期还款（元）*/
	private  String repayAmount;//REPAY_AMOUNT
	/*嘉福系统流水号*/
	private  String jfRepayNo;//JF_REPAY_NO
	/*还款账户*/
	private String repayType;//ACCOUNT_NO
	/*还款方式*/
	private String  repayFlag;//REPAY_FLAG

	public String getRepaySerialNo() {
		return repaySerialNo;
	}

	public void setRepaySerialNo(String repaySerialNo) {
		this.repaySerialNo = repaySerialNo;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getRepayAmountSum() {
		return repayAmountSum;
	}

	public void setRepayAmountSum(String repayAmountSum) {
		this.repayAmountSum = repayAmountSum;
	}

	public String getRepayAmount() {
		return repayAmount;
	}

	public void setRepayAmount(String repayAmount) {
		this.repayAmount = repayAmount;
	}

	public String getJfRepayNo() {
		return jfRepayNo;
	}

	public void setJfRepayNo(String jfRepayNo) {
		this.jfRepayNo = jfRepayNo;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public String getRepayFlag() {
		return repayFlag;
	}

	public void setRepayFlag(String repayFlag) {
		this.repayFlag = repayFlag;
	}
}