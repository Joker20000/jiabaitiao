/*   
 * Copyright © 2014-2017 jia-fu.cn All Rights Reserved.
 *   
 * This software is the confidential and proprietary information of   
 * TAOLUE Co.,Ltd. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with TAOLUE Co.,Ltd.    
 *   
 */

package com.bootdo.Loan.domain;

/**
 * @Description: TODO
 * @author HWJ
 * @date 2017年12月27日
 * 业务参数
 */
public class AmountsEntity {

	/*渠道*/
	private String  channel;
	/*关联系统流水编号*/
	private String  relatedFlowId;
	/*业务订单号*/
	private String  bizid;
	/*渠道订单号*/
	private String  channelBizid;
	/*交易类型('11'.手动还款,'12'.自动还款,'21'.消费分期,'31'.取现分期,'32'.信用卡分期,'41'.取现退款,'42'.信用卡退款,'51'.授信,'52'.小额自动授信,'53'.联系信息授信,'54'.手机认证授信,'61'.预支还款)*/
	private String  transType;
	/*用户编号*/
	private String  userId;
	/*账号*/
	private String  accountId;
	/*账户类型*/
	private String  type;
	/*账户*/
	private String  accountNo;
	/*交易金额*/
	private String  amount;
	/*变动类型('01'.增加,'02'.减少)*/
	private String  addAndSubtract;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getRelatedFlowId() {
		return relatedFlowId;
	}

	public void setRelatedFlowId(String relatedFlowId) {
		this.relatedFlowId = relatedFlowId;
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

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getAddAndSubtract() {
		return addAndSubtract;
	}

	public void setAddAndSubtract(String addAndSubtract) {
		this.addAndSubtract = addAndSubtract;
	}
}
