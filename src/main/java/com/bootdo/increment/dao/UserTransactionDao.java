package com.bootdo.increment.dao;

import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.dto.UserDTO;

import java.util.List;

/**
 * 用户转入转出交易记录dao
 *
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-13 12:49
 **/
public interface UserTransactionDao {
    /**
     * 用户交易记录查询（转入转出）
     *
     * @param userDTO userName 用户姓名
     *                userMobile 手机号
     *                userEmail 邮箱
     *                userChannelId 用户渠道 01嘉福 02嘉薪
     *                entName 企业名称
     *                transferType 交易类型 1-转入（冻结）、2-转出（解冻）
     *                accountType 账户类型 01 工资账户 02 福利账户 03福豆账户 04 现金账户
     *                transferState 交易状态 1-成功 2-处理中 3-失败
     *                创建时间
     *                createDateStart
     *                createDateEnd
     *                完成时间
     *                complateDateStart
     *                complateDateEnd
     *                userPageon 当前页
     *                userLimit 分页开关
     * @return
     */
    List<TbIncrementUser> selectUserTransactionList(UserDTO userDTO);

    /**
     * 用户交易记录查询（转入转出）count
     *
     * @param userDTO userName 用户姓名
     *                userMobile 手机号
     *                userEmail 邮箱
     *                userChannelId 用户渠道 01嘉福 02嘉薪
     *                entName 企业名称
     *                transferType 交易类型 1-转入（冻结）、2-转出（解冻）
     *                accountType 账户类型 01 工资账户 02 福利账户 03福豆账户 04 现金账户
     *                transferState 交易状态 1-成功 2-处理中 3-失败
     *                创建时间
     *                createDateStart
     *                createDateEnd
     *                完成时间
     *                complateDateStart
     *                complateDateEnd
     * @return 符合条件的条数  几条
     */
    int selectUserTransactionCount(UserDTO userDTO);

}
