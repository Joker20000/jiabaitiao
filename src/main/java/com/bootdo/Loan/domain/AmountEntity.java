package com.bootdo.Loan.domain;

/**
 * @Description: TODO
 * @author HWJ
 * @date 2017年12月27日
 * 业务参数
 */
public class AmountEntity {
	/*关联订单号*/
	private String  relevantOrder;
	/*账户编号*/
	private String  accountNo;
	/*交易金额*/
	private String  amount;
	/*变动类型('01'.增加,'02'.减少)*/
	private String  addOrSub;
	/*业务类型('01'.借款,'02'.还款,'03'.授信)*/
	private String  busType;


	public String getRelevantOrder() {
		return relevantOrder;
	}

	public void setRelevantOrder(String relevantOrder) {
		this.relevantOrder = relevantOrder;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAddOrSub() {
		return addOrSub;
	}

	public void setAddOrSub(String addOrSub) {
		this.addOrSub = addOrSub;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}
}
