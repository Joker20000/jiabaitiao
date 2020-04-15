package com.bootdo.increment.service;

import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.dto.UserDTO;

import java.util.List;

/**
 * 员工增值持有信息业务
 * 增值奖励信息业务
 * 奖励订单发放信息业务
 *
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-12 14:54
 **/
public interface UserValueAddedService {
    /**
     * 员工增值持有数据查询
     *
     * @param userDTO 查询条件
     *                userName
     *                userMobile
     *                userEmail
     *                userChannelId 用户渠道 01嘉福 02嘉薪
     *                entName   企业名称
     *                accountType 账户类型 01 工资账户 02 福利账户 03福豆账户 04 现金账户
     *                更新时间
     *                updateDateStart
     *                updateDateEnd
     *                创建时间
     *                createDateStart
     *                createDateEnd
     *                userPageon 当前页
     * @return
     */
    List<TbIncrementUser> selectUserValueAddList(UserDTO userDTO);

    /**
     * 员工增值持有数据查询count
     *
     * @param userDTO 查询条件
     *                userName
     *                userMobile
     *                userEmail
     *                userChannelId 用户渠道 01嘉福 02嘉薪
     *                entName   企业名称
     *                accountType 账户类型 01 工资账户 02 福利账户 03福豆账户 04 现金账户
     *                更新时间
     *                updateDateStart
     *                updateDateEnd
     *                创建时间
     *                createDateStart
     *                createDateEnd
     * @return
     */
    int selectUserValueAddCount(UserDTO userDTO);

    /**
     * 员工增值持有数据 Excel导出
     *
     * @param users 根据selectUserValueAddList方法查到的用户数据集合
     * @return
     */
    List<List<String>> excelUserValueAddList(List<TbIncrementUser> users);




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
     * 增值奖励查询Excel导出
     *
     * @param users selectValueAddRewardList查询出的数据
     * @return
     */
    List<List<String>> excelValueAddRewardList(List<TbIncrementUser> users);




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

    /**
     * 奖励发放订单查询结果Excel导出
     *
     * @param excelUserList selectAwardingOrderList方法查询出的List<TbIncrementUser> 集合
     * @return
     */
    List<List<String>> excelAwardingOrderList(List<TbIncrementUser> excelUserList);

}
