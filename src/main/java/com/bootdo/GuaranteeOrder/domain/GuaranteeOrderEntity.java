package com.bootdo.GuaranteeOrder.domain;

import lombok.Data;

@Data
public class GuaranteeOrderEntity {
    private String guaranteeOrderId;

    private String guaranteeRepayId;

    private String companyName;

    private String realName;

    private String mobile;

    private String email;

    private String amount;

    private String repayState;

    private String extOrderId;

    private String extRepayOrderId;

    private String jfRepayNo;

    private String createDate;

    private String repayDate;

}