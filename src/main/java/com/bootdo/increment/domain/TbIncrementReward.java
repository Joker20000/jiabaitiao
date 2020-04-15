package com.bootdo.increment.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (增值奖励记录表)实体类
 *
 * @author makejava
 * @since 2020-04-12 20:41:43
 */
@Data
public class TbIncrementReward implements Serializable {
    private static final long serialVersionUID = -37774910032000404L;
    /**
    * 奖励编号
    */
    private Integer rewardId;
    /**
    * 账户编号
    */
    private Integer accountId;
    /**
    * 当日转入金额
    */
    private String addAmount;
    /**
    * 福豆
    */
    private String rewardAmount;
    /**
    * 年化率
    */
    private String annualizedRate;
    /**
    * 奖励发放订单号
    */
    private Integer orderId;
    /**
    * 数据有效性
    */
    private String dataState;
    /**
    * 统计日期
    */
    private Date statisticsDate;
    /**
    * 创建时间
    */
    private Date createDate;
    /**
    * 更新日期
    */
    private Date updateDate;



}