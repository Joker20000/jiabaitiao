package com.bootdo.Cdomain.domain;

/**
 * 逾期费用流水表
 * Created by zc on 2018/1/11.
 */
public class OverdueFlow {
    /*逾期费用流水id*/
    private int  overdueFlowId;//OVERDUE_FLOW_ID
    /*还款计划编号*/
    private String repayPlanNo;//REPAY_PLAN_NO
    /*费用类别*/
    private String costType;//COST_TYPE 费用类别('01'.管理费,'02'.日费用)
    /*逾期费用*/
    private String overdueCost;//OVERDUE_COST
    /*逾期未还利息*/
    private String overdueInterest;
    /*创建时间*/
    private String createTime;

    public int getOverdueFlowId() {
        return overdueFlowId;
    }

    public void setOverdueFlowId(int overdueFlowId) {
        this.overdueFlowId = overdueFlowId;
    }

    public String getRepayPlanNo() {
        return repayPlanNo;
    }

    public void setRepayPlanNo(String repayPlanNo) {
        this.repayPlanNo = repayPlanNo;
    }

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

    public String getOverdueInterest() {
        return overdueInterest;
    }

    public void setOverdueInterest(String overdueInterest) {
        this.overdueInterest = overdueInterest;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OverdueFlowEntity{" +
                "overdueFlowId=" + overdueFlowId +
                ", repayPlanNo='" + repayPlanNo + '\'' +
                ", costType='" + costType + '\'' +
                ", overdueCost='" + overdueCost + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

}

