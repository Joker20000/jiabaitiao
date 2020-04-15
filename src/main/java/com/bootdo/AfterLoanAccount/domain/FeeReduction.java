package com.bootdo.AfterLoanAccount.domain;

import java.io.Serializable;

public class FeeReduction implements Serializable {
    private static final long serialVersionUID = 1L;
    private String realName;//REPAY_PLAN_NO
    private String accountNo;//REPAY_PLAN_NO
    private String tel;//LOAN_ORDER_NO
    private String appNo;//ORDER_TYPE
    private String repayPlanNo;//ORDER_TYPE
    private String oriLastTime;//ORDER_TYPE
    private String makeLastTime;//ORDER_TYPE
    private String oriOverdue;//ORDER_TYPE
    private String waiverAmt;//NOW_PERIOD
    private String agterReduFee;//PERIOD_SUM
    private String applicant;
    private String applicantId;
    private String approver;
    private String approverId;//NOW_OVERDUE
    private String approvalTime;
    private String oriReason;
    private String approvalState;
    private String createTime;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public String getRepayPlanNo() {
        return repayPlanNo;
    }

    public void setRepayPlanNo(String repayPlanNo) {
        this.repayPlanNo = repayPlanNo;
    }

    public String getOriLastTime() {
        return oriLastTime;
    }

    public void setOriLastTime(String oriLastTime) {
        this.oriLastTime = oriLastTime;
    }

    public String getMakeLastTime() {
        return makeLastTime;
    }

    public void setMakeLastTime(String makeLastTime) {
        this.makeLastTime = makeLastTime;
    }

    public String getOriOverdue() {
        return oriOverdue;
    }

    public void setOriOverdue(String oriOverdue) {
        this.oriOverdue = oriOverdue;
    }

    public String getWaiverAmt() {
        return waiverAmt;
    }

    public void setWaiverAmt(String waiverAmt) {
        this.waiverAmt = waiverAmt;
    }

    public String getAgterReduFee() {
        return agterReduFee;
    }

    public void setAgterReduFee(String agterReduFee) {
        this.agterReduFee = agterReduFee;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public String getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(String approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getOriReason() {
        return oriReason;
    }

    public void setOriReason(String oriReason) {
        this.oriReason = oriReason;
    }

    public String getApprovalState() {
        return approvalState;
    }

    public void setApprovalState(String approvalState) {
        this.approvalState = approvalState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
