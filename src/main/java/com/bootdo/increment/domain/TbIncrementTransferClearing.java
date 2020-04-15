package com.bootdo.increment.domain;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (TbIncrementTransferClearing)实体类
 *
 * @author makejava
 * @since 2020-04-12 20:41:07
 */
@Data
public class TbIncrementTransferClearing implements Serializable {
    private static final long serialVersionUID = -57799215887159796L;
    /**
     * 交易流水号
     */
    private Integer clearingId;
    /**
     * 账户编号
     */
    private Integer accountId;
    /**
     * 交易金额
     */
    private String transferAmount;
    /**
     * 交易类型
     * 1-转入（冻结）、2-转出（解冻）
     */
    private String transferType;
    /**
     * 交易状态
     * 1-成功 2-处理中 3-失败
     */
    private String transferState;
    /**
     * 0未推送 1已推送
     */
    private String clearingIsPush;
    /**
     * 数据有效性
     */
    private String dataState;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 只有成功的交易才有完成时间
     */
    private Date complateDate;


}