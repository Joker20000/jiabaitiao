package com.bootdo.increment.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * (TbIncrementParameter)实体类
 *
 * @author makejava
 * @since 2020-04-12 20:41:42
 */
@Data
public class TbIncrementParameter implements Serializable {
    private static final long serialVersionUID = 301204968679787460L;
    /**
    * 个人增值持有限额
    */
    private String userValurMax;
    /**
    * 增值产品总限额
    */
    private String productesMax;
    /**
    * 奖励间隔天数
    */
    private String rewardIntervalDays;



}