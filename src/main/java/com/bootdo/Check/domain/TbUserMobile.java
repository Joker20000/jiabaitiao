package com.bootdo.Check.domain;

public class TbUserMobile {
    // 账户编号
    private String accountNo;
    private String mobile;
    private String chackNo;
    // 本地名称
    private String fileName;
    // 用户报告保存地址 FILE_PATH
    private String filePath;

    //身份证
    private String cardId;

    //用户和联系人（01用户报告、02联系人用户报告）
    private String dataType;
    /*请求地址('0'失效,'1'有效)*/
    private String  requesetUrl;

    public String getRequesetUrl() {
        return requesetUrl;
    }

    public void setRequesetUrl(String requesetUrl) {
        this.requesetUrl = requesetUrl;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getChackNo() {
        return chackNo;
    }

    public void setChackNo(String chackNo) {
        this.chackNo = chackNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

}