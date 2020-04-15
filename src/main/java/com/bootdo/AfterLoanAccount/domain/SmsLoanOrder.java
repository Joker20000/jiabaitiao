package com.bootdo.AfterLoanAccount.domain;

public class SmsLoanOrder{
   public String phone;
    public String sendContent;
    public String operator;
    public String operatorId;
    public String createTime;
    public String loanName;
    public String loanPhone;
    public String sendTimeStart;
    public String sendTimeEnd;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public String getLoanPhone() {
        return loanPhone;
    }

    public void setLoanPhone(String loanPhone) {
        this.loanPhone = loanPhone;
    }

    public String getSendTimeStart() {
        return sendTimeStart;
    }

    public void setSendTimeStart(String sendTimeStart) {
        this.sendTimeStart = sendTimeStart;
    }

    public String getSendTimeEnd() {
        return sendTimeEnd;
    }

    public void setSendTimeEnd(String sendTimeEnd) {
        this.sendTimeEnd = sendTimeEnd;
    }
}
