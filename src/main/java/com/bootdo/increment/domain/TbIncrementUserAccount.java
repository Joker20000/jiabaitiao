package com.bootdo.increment.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * (TbIncrementUserAccount)实体类
 *
 * @author makejava
 * @since 2020-04-12 20:41:45
 */
@Data
public class TbIncrementUserAccount implements Serializable {
    private static final long serialVersionUID = -37578063079083678L;
    /**
    * 账户编号
    */
    private Integer accountId;
    /**
    * 用户编号
    */
    private Integer userId;
    /**
     * 账户类型
    * 01 工资账户 02 福利账户 03福豆账户 04 现金账户
    */
    private String accountType;
    /**
    * 持有金额
    */
    private String incrementAmount;
    /**
    * 累计奖励金额
    */
    private String rewardAmount;
    /**
    * 数据有效性
    */
    private String dataState;
    /**
    * 创建时间
    */
    private Date createDate;
    private Date createDateStart;
    private Date createDateEnd;
    /**
    * 更新时间
    */
    private Date updateDate;
    private Date updateDateStart;
    private Date updateDateEnd;
    /**
     *增值转账记录表集合
     */
    private List<TbIncrementTransferClearing> transferClearingList;
    /**
     * 增值奖励记录表集合
     */
    private List<TbIncrementReward> incrementRewardList;
    /**
     * 用户增值
     */
    private TbUserDayAmount userDayAmount;


}