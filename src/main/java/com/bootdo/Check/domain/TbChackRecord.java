package com.bootdo.Check.domain;

import java.util.Date;

public class TbChackRecord {

    // 信审记录ID
    private Integer chackRecordId;
    // 信审编号
    private String chackNo;
    // 审核人ID
    private String chackId;
    // 审核人
    private String chackName;
    // 审核结果
    private String result;
    // 审核类型
    private String type;
    // 是否为系统审核
    private String isSystem;
    // 审核时间
    private Date chackTime;
    // 审核意见
    private String opinion;

    public Integer getChackRecordId() {
        return chackRecordId;
    }

    public void setChackRecordId(Integer chackRecordId) {
        this.chackRecordId = chackRecordId;
    }

    public String getChackNo() {
        return chackNo;
    }

    public void setChackNo(String chackNo) {
        this.chackNo = chackNo == null ? null : chackNo.trim();
    }

    public String getChackId() {
        return chackId;
    }

    public void setChackId(String chackId) {
        this.chackId = chackId == null ? null : chackId.trim();
    }

    public String getChackName() {
        return chackName;
    }

    public void setChackName(String chackName) {
        this.chackName = chackName == null ? null : chackName.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(String isSystem) {
        this.isSystem = isSystem == null ? null : isSystem.trim();
    }

    public Date getChackTime() {
        return chackTime;
    }

    public void setChackTime(Date chackTime) {
        this.chackTime = chackTime;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }
}