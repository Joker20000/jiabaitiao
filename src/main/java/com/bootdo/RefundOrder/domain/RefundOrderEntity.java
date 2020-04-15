package com.bootdo.RefundOrder.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class RefundOrderEntity {

    private String companyName;
    /*姓名*/
    private String realName;
    /*手机号*/
    private String mobile;

    private String  refundOrderNo;

    private String loanOrderNo;

    private String extRefOrderId;

    private String accountNo;

    private String refundAmount;

    private String offsetAmount;

    private String rechargeAmount;

    private String totalRefundAmount;

    private String rechargeOrderId;

    private String rechargeOrderTid;

    private String refundState;

    private String dataState;

    private String createDate;

    private String updateDate;

}
