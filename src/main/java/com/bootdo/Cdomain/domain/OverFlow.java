package com.bootdo.Cdomain.domain;


/**
 * 逾期费用
 * @author Administrator
 *
 */
public class OverFlow {
	/*费用类别('01'.管理费,'02'.日费用)*/
    private String costType;
	/*逾期费用*/
    private String overdueCost;
	/*创建时间*/
    private String createTime;
    
    
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	public String getOverdueCost() {
		return overdueCost;
	}
	public void setOverdueCost(String overdueCost) {
		this.overdueCost = overdueCost;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}