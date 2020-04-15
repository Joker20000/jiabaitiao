package com.bootdo.increment.domain;

import com.bootdo.common.domain.PageDO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (TbEntUserDayAmount)实体类
 *
 * @author makejava
 * @since 2020-04-12 20:41:08
 */
@Data
public class TbEntUserDayAmount extends PageDO implements Serializable {
    private static final long serialVersionUID = -51089623760380273L;
    /**
    * 记录编号
    */
    private Integer entRecordId;
    /**
    * 企业编号
    */
    private String entId;
    /**
    * 企业名称
    */
    private String entName;
    /**
    * 渠道编号
    */
    private String channelId;
    /**
    * 员工增值总额
    */
    private String totalRewardAmount;
    /**
    * 数据有效性
    */
    private String dataState;
    /**
    * 统计日期
    */
    private Date statisticsDate;
    private String statisticsDateStart;
    private String statisticsDateEnd;
    /**
    * 创建日期
    */
    private Date createDate;
    private String createDateStart;
    private String createDateEnd;
    /**
    * 更新日期
    */
    private Date updateDate;



}