package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 财务统计表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-02-18 16:22:07
 */
public class FinanceCountDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//统计ID
	private Integer countId;
	//统计日期
	private String countDate;
	//放款本金
	private String loanAmount;
	//已还本金
	private String readyAmount;
	//待还本金
	private String waitAmount;
	//预计收入
	private String estimatedRevenue;
	//实际收入
	private String realIncome;
	//新增放款
	private String addLoanAmount;
	//新增还款
	private String addReadyAmount;
	//新增预计收入
	private String addEstimatedRevenue;
	//新增实际收入
	private String addRealIncome;
	//创建时间
	private Date createTime;

	/**
	 * 设置：统计ID
	 */
	public void setCountId(Integer countId) {
		this.countId = countId;
	}
	/**
	 * 获取：统计ID
	 */
	public Integer getCountId() {
		return countId;
	}
	/**
	 * 设置：统计日期
	 */
	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}
	/**
	 * 获取：统计日期
	 */
	public String getCountDate() {
		return countDate;
	}
	/**
	 * 设置：放款本金
	 */
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	/**
	 * 获取：放款本金
	 */
	public String getLoanAmount() {
		return loanAmount;
	}
	/**
	 * 设置：已还本金
	 */
	public void setReadyAmount(String readyAmount) {
		this.readyAmount = readyAmount;
	}
	/**
	 * 获取：已还本金
	 */
	public String getReadyAmount() {
		return readyAmount;
	}
	/**
	 * 设置：待还本金
	 */
	public void setWaitAmount(String waitAmount) {
		this.waitAmount = waitAmount;
	}
	/**
	 * 获取：待还本金
	 */
	public String getWaitAmount() {
		return waitAmount;
	}
	/**
	 * 设置：预计收入
	 */
	public void setEstimatedRevenue(String estimatedRevenue) {
		this.estimatedRevenue = estimatedRevenue;
	}
	/**
	 * 获取：预计收入
	 */
	public String getEstimatedRevenue() {
		return estimatedRevenue;
	}
	/**
	 * 设置：实际收入
	 */
	public void setRealIncome(String realIncome) {
		this.realIncome = realIncome;
	}
	/**
	 * 获取：实际收入
	 */
	public String getRealIncome() {
		return realIncome;
	}
	/**
	 * 设置：新增放款
	 */
	public void setAddLoanAmount(String addLoanAmount) {
		this.addLoanAmount = addLoanAmount;
	}
	/**
	 * 获取：新增放款
	 */
	public String getAddLoanAmount() {
		return addLoanAmount;
	}
	/**
	 * 设置：新增还款
	 */
	public void setAddReadyAmount(String addReadyAmount) {
		this.addReadyAmount = addReadyAmount;
	}
	/**
	 * 获取：新增还款
	 */
	public String getAddReadyAmount() {
		return addReadyAmount;
	}
	/**
	 * 设置：新增预计收入
	 */
	public void setAddEstimatedRevenue(String addEstimatedRevenue) {
		this.addEstimatedRevenue = addEstimatedRevenue;
	}
	/**
	 * 获取：新增预计收入
	 */
	public String getAddEstimatedRevenue() {
		return addEstimatedRevenue;
	}
	/**
	 * 设置：新增实际收入
	 */
	public void setAddRealIncome(String addRealIncome) {
		this.addRealIncome = addRealIncome;
	}
	/**
	 * 获取：新增实际收入
	 */
	public String getAddRealIncome() {
		return addRealIncome;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
