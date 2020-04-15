package com.bootdo.increment.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * (TbAccountTypeRule)实体类
 *
 * @author makejava
 * @since 2020-04-12 20:41:46
 */
@Data
public class TbAccountTypeRule implements Serializable {
    private static final long serialVersionUID = 953900343308041688L;
    /**
    * 01 工资账户 02 福利账户 03福豆账户 04 现金账户
    */
    private Integer typeId;
    /**
    * 年化率
    */
    private String annualizedRate;
    /**
    * 数据有效性
    */
    private String dataState;
    
    private Integer ruleId;



}