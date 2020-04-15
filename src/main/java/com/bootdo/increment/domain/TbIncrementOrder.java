package com.bootdo.increment.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (奖励发放订单表)实体类
 *
 * @author makejava
 * @since 2020-04-12 20:41:41
 */
@Data
public class TbIncrementOrder implements Serializable {
    private static final long serialVersionUID = -44636258204886721L;
    /**
     * 发放订单号
     */
    private Integer orderId;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 嘉福流水号
     */
    private String extOrderId;
    /**
     * 工资增值奖励
     */
    private String salaryRewardAmount;
    /**
     * 福利增值奖励
     */
    private String benefitRewardAmount;
    /**
     * 现金增值奖励
     */
    private String cashRewardAmount;
    /**
     * 总奖励
     */
    private String totalRewardAmount;
    /**
     * 发放状态
     * 0-待发放 1-发放成功 2-发放中 3-发放失败
     */
    private String orderState;
    /**
     * 数据有效性
     */
    private String dataState;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 更新日期
     */
    private Date updateDate;
    /**
     * 前一天的日期
     */
    private Date statisticsDate;
    /**
     * 只有发放成功的订单
     */
    private Date complateDate;
    private String fudou;

    /**
     * 增值奖励记录表
     */
    private List<TbIncrementReward> incrementRewardList;


}