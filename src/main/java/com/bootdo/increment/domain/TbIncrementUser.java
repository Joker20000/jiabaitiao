package com.bootdo.increment.domain;

import lombok.Data;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * (TbIncrementUser)实体类
 *
 * @author makejava
 * @since 2020-04-12 20:36:54
 */
@Data
public class TbIncrementUser implements Serializable {
    private static final long serialVersionUID = 499620149479034756L;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 手机号
     */
    private String userMobile;
    /**
     * 邮箱
     */
    private String userEmail;
    /**
     * 01-嘉福平台 02-嘉薪
     */
    private String userChannelId;
    /**
     * 企业编号
     */
    private String entId;
    /**
     * 企业名称
     */
    private String entName;
    /**
     * GUID
     */
    private String guid;
    /**
     * GEID
     */
    private String geid;
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
     * 修改时间
     */
    private Date updateDate;
    private Date updateDateStart;
    private Date updateDateEnd;
    /**
     * 账户list
     */
    private List<TbIncrementUserAccount> userAccountList;
    /**
     * 奖励发放订单表 list
     */
    private List<TbIncrementOrder> incrementOrderList;
    /**
     * 用于用户增值日报表
     * 嘉薪用户只有一个账户
     */
    private TbIncrementUserAccount incrementUserAccount;

}