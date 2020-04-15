package com.bootdo.increment.dao;

import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.dto.UserDTO;

import java.util.List;

/**
 * 员工增值持有业务
 *
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-12 14:47
 **/
public interface UserValueAddedDao {


    /**
     * 员工增值持有数据查询
     *
     * @param userDTO
     * @return
     */
    List<TbIncrementUser> selectUserValueAddList(UserDTO userDTO);

    /**
     * 员工增值持有数据查询count
     *
     * @param userDTO
     * @return
     */
    int selectUserValueAddCount(UserDTO userDTO);


    /**
     * 增值奖励查询
     *
     * @param userDTO 要查询的用户信息
     *                userName
     *                userMobile
     *                userEmail
     *                userChannelId 渠道 01嘉福 02嘉薪
     *                entName 企业名称
     *                accountType 账户类型 01 工资账户 02 福利账户 03福豆账户 04 现金账户
     *                orderId 奖励发放订单号
     *                orderState 发放状态  0-待发放 1-发放成功 2-发放中 3-发放失败
     *                统计日期
     *                statisticsDateStart
     *                statisticsDateEnd
     * @return
     */
    List<TbIncrementUser> selectValueAddRewardList(UserDTO userDTO);

    /**
     * 增值奖励查询 count
     *
     * @param userDTO 要查询的用户信息
     *                userName
     *                userMobile
     *                userEmail
     *                userChannelId 渠道 01嘉福 02嘉薪
     *                entName 企业名称
     *                accountType 账户类型 01 工资账户 02 福利账户 03福豆账户 04 现金账户
     *                orderId 奖励发放订单号
     *                orderState 发放状态  0-待发放 1-发放成功 2-发放中 3-发放失败
     *                统计日期
     *                statisticsDateStart
     *                statisticsDateEnd
     * @return
     */
    int selectValueAddRewardCount(UserDTO userDTO);



    /**
     * 奖励发放订单查询
     *
     * @param userDTO 查询条件
     *                orderId奖励发放订单号
     *                extOrderId嘉福流水号
     *                userName
     *                userMobile
     *                userEmail
     *                entName 企业名称
     *                userChannelId用户渠道： 01嘉福 02嘉薪
     *                orderState发放状态：0-待发放 1-发放成功 2-发放中 3-发放失败
     *                创建时间
     *                createDateStart
     *                createDateEnd
     *                交易完成时间
     *                complateDateStart
     *                complateDateEnd
     *                统计日期
     *                statisticsDateStart
     *                statisticsDateEnd
     *                userPageon当前页
     * @return 根据条件查询出的奖励发放订单集合List<TbIncrementUser>
     */
    List<TbIncrementUser> selectAwardingOrderList(UserDTO userDTO);

    /**
     * 奖励发放订单查询count
     *
     * @param userDTO 查询条件
     *                orderId奖励发放订单号
     *                extOrderId嘉福流水号
     *                userName
     *                userMobile
     *                userEmail
     *                entName 企业名称
     *                userChannelId用户渠道： 01嘉福 02嘉薪
     *                orderState发放状态：0-待发放 1-发放成功 2-发放中 3-发放失败
     *                创建时间
     *                createDateStart
     *                createDateEnd
     *                交易完成时间
     *                complateDateStart
     *                complateDateEnd
     *                统计日期
     *                statisticsDateStart
     *                statisticsDateEnd
     * @return 根据条件查询出的条数int
     */
    int selectAwardingOrderCount(UserDTO userDTO);
}
