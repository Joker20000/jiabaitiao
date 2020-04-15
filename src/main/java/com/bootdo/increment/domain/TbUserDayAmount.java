package com.bootdo.increment.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (TbUserDayAmount)实体类
 *
 * @author makejava
 * @since 2020-04-12 20:41:08
 */
@Data
public class TbUserDayAmount implements Serializable {
    private static final long serialVersionUID = -50608874299929266L;
    /**
    * 记录编号
    */
    private Integer userRecordId;
    /**
    * 账户编号
    */
    private Integer accountId;
    /**
    * 嘉薪企业编号
    */
    private String jiaxinEntId;
    /**
    * 嘉薪企业名称
    */
    private String jiaxinEntName;
    /**
    * 在每个企业下分配的持有金额
    */
    private String incrementAmount;
    /**
    * 在每个企业下的权益额度
    */
    private String rightAmount;
    /**
    * 数据有效性
    */
    private String dataState;
    /**
    * 统计日期
    */
    private Date statisticsDate;
    /**
    * 创建日期
    */
    private Date createDate;
    /**
    * 更新日期
    */
    private Date updateDate;




}