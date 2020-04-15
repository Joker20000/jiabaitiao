package com.bootdo.increment.domain.dto;

import com.bootdo.common.domain.PageDO;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-12 22:16
 **/
@Data
public class UserDTO extends PageDO<T> implements Serializable {
    private Integer userId;
    private String userName;
    private String userMobile;
    private String userEmail;
    /**
     * 用户渠道
     */
    private String userChannelId;
    private String entId;
    private String entName;
    private String guid;
    private String geid;
    private String dataState;
    /**
     * 账户类型
     * 01 工资账户 02 福利账户 03福豆账户 04 现金账户
     */
    private String accountType;
    /**
     * 交易流水号
     */
    private Integer clearingId;
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
     * 奖励发放订单号
     */
    private Integer orderId;
    /**
     * 发放状态
     * 0-待发放 1-发放成功 2-发放中 3-发放失败
     */
    private String orderState;
    /**
     * 嘉福流水号
     */
    private String extOrderId;



    /**
     * 创建时间
     */
    private String createDate;
    private String createDateStart;
    private String createDateEnd;
    /**
     * 更新时间
     */
    private String updateDate;
    private String updateDateStart;
    private String updateDateEnd;
    /**
     * 交易完成时间
     */
    private String complateDate;
    private String complateDateStart;
    private String complateDateEnd;

    /**
     * 统计日期
     */
    private String statisticsDate;
    private String statisticsDateStart;
    private String statisticsDateEnd;


}
