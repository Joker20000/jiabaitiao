package com.bootdo.Check.domain.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
public class TbChackPojo {
    // 信审编号
    private String chackNo;
    private String accountNo;
    private String userId;
    // 姓名
    private String realname;
    // 手机号
    private String mobile;
    // 认证类型
    private String chackType;
    // 审核状态
    private String chackState;
    // 申请时间 （时间格式待定）
    /*@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")*/
    private Date applyTime;
    private String applyTimeStart;
    private String applyTimeEnd;
    // 完成时间
    private String lastTime;
    private String lastTimeStart;
    private String lastTimeEnd;
    // 审核结果
    private String chackResult;
    // 审核时效(h)
    private String valid;
    public String getChackNo() {
        return chackNo;
    }

    public void setChackNo(String chackNo) {
        this.chackNo = chackNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getChackType() {
        return chackType;
    }

    public void setChackType(String chackType) {
        this.chackType = chackType;
    }

    public String getChackState() {
        return chackState;
    }

    public void setChackState(String chackState) {
        this.chackState = chackState;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyTimeStart() {
        return applyTimeStart;
    }

    public void setApplyTimeStart(String applyTimeStart) {
        this.applyTimeStart = applyTimeStart;
    }

    public String getApplyTimeEnd() {
        return applyTimeEnd;
    }

    public void setApplyTimeEnd(String applyTimeEnd) {
        this.applyTimeEnd = applyTimeEnd;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastTimeStart() {
        return lastTimeStart;
    }

    public void setLastTimeStart(String lastTimeStart) {
        this.lastTimeStart = lastTimeStart;
    }

    public String getLastTimeEnd() {
        return lastTimeEnd;
    }

    public void setLastTimeEnd(String lastTimeEnd) {
        this.lastTimeEnd = lastTimeEnd;
    }

    public String getChackResult() {
        return chackResult;
    }

    public void setChackResult(String chackResult) {
        this.chackResult = chackResult;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
