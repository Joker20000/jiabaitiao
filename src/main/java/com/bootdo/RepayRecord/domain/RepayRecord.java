package com.bootdo.RepayRecord.domain;

/**
 * 还款记录
 * @author Administrator
 *
 */
public class RepayRecord {

	/*还款订单号*/
    private String repaySerianlNo;//REPAY_SERIAL_NO
	/*企业名称*/
    private String companyName;//COMPANY_NAME
	/*姓名*/
    private String realname;//REALNAME
	/*邮箱*/
    private String email;//EMAIL
	/*手机*/
    private String mobile;//MOBILE
	/*金额*/
    private String repayAmountSum;//REPAY_AMOUNT_SUM
	/*本金还款*/
    private String amount;
	/*费用还款*/
    private String repayInterestSum;
	/*还款方式*/
    private String repayFlag;//REPAY_FLAG
	/*还款账户*/
    private String repayType;//REPAY_TYPE
	/*嘉福流水号*/
    private String jfRepayNo;//JF_REPAY_NO
	/*时间*/
    private String finishTime;//FINISH_TIME
    
    private String finishTimeStart;
    
    private String finishTimeEnd;
    
    
    
    
    

	public String getRepaySerianlNo() {
		return repaySerianlNo;
	}

	public void setRepaySerianlNo(String repaySerianlNo) {
		this.repaySerianlNo = repaySerianlNo;
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

	public String getRepayAmountSum() {
		return repayAmountSum;
	}

	public void setRepayAmountSum(String repayAmountSum) {
		this.repayAmountSum = repayAmountSum;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRepayInterestSum() {
		return repayInterestSum;
	}

	public void setRepayInterestSum(String repayInterestSum) {
		this.repayInterestSum = repayInterestSum;
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

	public String getJfRepayNo() {
		return jfRepayNo;
	}

	public void setJfRepayNo(String jfRepayNo) {
		this.jfRepayNo = jfRepayNo;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getFinishTimeStart() {
		return finishTimeStart;
	}

	public void setFinishTimeStart(String finishTimeStart) {
		this.finishTimeStart = finishTimeStart;
	}

	public String getFinishTimeEnd() {
		return finishTimeEnd;
	}

	public void setFinishTimeEnd(String finishTimeEnd) {
		this.finishTimeEnd = finishTimeEnd;
	}
    
	
}
