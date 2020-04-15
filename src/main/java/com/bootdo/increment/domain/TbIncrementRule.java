package com.bootdo.increment.domain;

import com.bootdo.increment.domain.dto.PageDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (TbIncrementRule)实体类
 *
 * @author makejava
 * @since 2020-04-12 20:41:44
 */
@Data
public class TbIncrementRule extends PageDTO implements Serializable{
    private static final long serialVersionUID = 448557081342551686L;
    /**
     * 规则编号
     */
    private Integer ruleId;
    /**
     * 01-嘉福平台 02-嘉薪
     */
    private String channalId;
    /**
     * 名称
     */
    private String ruleName;
    /**
     * 首页返回地址
     */
    private String url;
    /**
     * 首页名字
     */
    private String urlName;
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
     * TbAccountTypeRule账户类型规则集合
     */
    private List<TbAccountTypeRule> accountTypeRuleList;


}