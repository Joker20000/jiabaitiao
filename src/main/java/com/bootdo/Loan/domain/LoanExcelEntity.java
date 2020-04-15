package com.bootdo.Loan.domain;

public class LoanExcelEntity implements java.io.Serializable {
    /**
     * 借款订单号
     */
    private String loanNum;
    /**
     *用户渠道
     */
    private String channel;
    /**
     *渠道企业ID
     */
    private String cEid;
    /**
     *企业名称
     */
    private String eName;
    /**
     *联系人
     */
    private String linkman;
    /**
     *联系人手机
     */
    private String linkPhone;
    /**
     *担保人
     */
    private String guarant;
    /**
     *担保人手机
     */
    private String guarantPhone;
    /**
     *借款金额
     */
    private String amount;
    /**
     *年化服务费率
     */
    private String yearRate;
    /**
     *借款天数
     */
    private String loanDay;
    /**
     *最低手续费
     */
    private String minProcCost;
    /**
     *逾期日费率
     */
    private String overRate;
    /**
     *当前手续费
     */
    private String procCost;
    /**
     *当前逾期费用
     */
    private String overCost;
    /**
     *最迟还款日
     */
    private String repayDate;
    /**
     *累计已还本金
     */
    private String repAmount;
    /**
     *累计已还手续费
     */
    private String repProcCost;
    /**
     *累计已还逾期费用
     */
    private String repOverCost;
    /**
     *累计已还总金额
     */
    private String repAmountSum;
    /**
     *剩余待还总金额
     */
    private String needAmountSum;
    /**
     *订单状态
     */
    private String orderState;
    /**
     *审批状态
     */
    private String chackState;
    /**
     *审批人
     */
    private String chackName;
    /**
     *逾期状态
     */
    private String overState;
    /**
     *还款状态
     */
    private String repayState;
    /**
     *还清时间
     */
    private String payoffDate;
    /**
     *还清方式
     */
    private String payoffType;
    /**
     *订单生成时间
     */
    private String createDate;
    /**
     *借款日
     */
    private String loanDate;
    /**
     *订单完成时间
     */
    private String chackDate;


    public String getLoanNum() {
        return loanNum;
    }

    public void setLoanNum(String loanNum) {
        this.loanNum = loanNum;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getcEid() {
        return cEid;
    }

    public void setcEid(String cEid) {
        this.cEid = cEid;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getGuarant() {
        return guarant;
    }

    public void setGuarant(String guarant) {
        this.guarant = guarant;
    }

    public String getGuarantPhone() {
        return guarantPhone;
    }

    public void setGuarantPhone(String guarantPhone) {
        this.guarantPhone = guarantPhone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getYearRate() {
        return yearRate;
    }

    public void setYearRate(String yearRate) {
        this.yearRate = yearRate;
    }

    public String getLoanDay() {
        return loanDay;
    }

    public void setLoanDay(String loanDay) {
        this.loanDay = loanDay;
    }

    public String getMinProcCost() {
        return minProcCost;
    }

    public void setMinProcCost(String minProcCost) {
        this.minProcCost = minProcCost;
    }

    public String getOverRate() {
        return overRate;
    }

    public void setOverRate(String overRate) {
        this.overRate = overRate;
    }

    public String getProcCost() {
        return procCost;
    }

    public void setProcCost(String procCost) {
        this.procCost = procCost;
    }

    public String getOverCost() {
        return overCost;
    }

    public void setOverCost(String overCost) {
        this.overCost = overCost;
    }

    public String getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate;
    }

    public String getRepAmount() {
        return repAmount;
    }

    public void setRepAmount(String repAmount) {
        this.repAmount = repAmount;
    }

    public String getRepProcCost() {
        return repProcCost;
    }

    public void setRepProcCost(String repProcCost) {
        this.repProcCost = repProcCost;
    }

    public String getRepOverCost() {
        return repOverCost;
    }

    public void setRepOverCost(String repOverCost) {
        this.repOverCost = repOverCost;
    }

    public String getRepAmountSum() {
        return repAmountSum;
    }

    public void setRepAmountSum(String repAmountSum) {
        this.repAmountSum = repAmountSum;
    }

    public String getNeedAmountSum() {
        return needAmountSum;
    }

    public void setNeedAmountSum(String needAmountSum) {
        this.needAmountSum = needAmountSum;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getChackState() {
        return chackState;
    }

    public void setChackState(String chackState) {
        this.chackState = chackState;
    }

    public String getChackName() {
        return chackName;
    }

    public void setChackName(String chackName) {
        this.chackName = chackName;
    }

    public String getOverState() {
        return overState;
    }

    public void setOverState(String overState) {
        this.overState = overState;
    }

    public String getRepayState() {
        return repayState;
    }

    public void setRepayState(String repayState) {
        this.repayState = repayState;
    }

    public String getPayoffDate() {
        return payoffDate;
    }

    public void setPayoffDate(String payoffDate) {
        this.payoffDate = payoffDate;
    }

    public String getPayoffType() {
        return payoffType;
    }

    public void setPayoffType(String payoffType) {
        this.payoffType = payoffType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getChackDate() {
        return chackDate;
    }

    public void setChackDate(String chackDate) {
        this.chackDate = chackDate;
    }
}
