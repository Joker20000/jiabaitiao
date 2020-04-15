package com.bootdo.increment.service;

import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.dto.UserDTO;

import java.util.List;

/**
 * 用户增值日报表Service
 *
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-15 11:10
 **/
public interface UserDayAmountService {
    /**
     * 用户增值日报表查询
     *
     * @param userDTO userName
     *                userMobile
     *                userEmail
     *                entName
     *                统计日期 前一天的时间
     *                statisticsDateStart
     *                statisticsDateEnd
     *                获取数据日期
     *                createDateStart
     *                createDateEnd
     * @return
     */
    List<TbIncrementUser> selectUserDayAmountList(UserDTO userDTO);

    /**
     * 用户增值日报表查询count
     *
     * @param userDTO userName
     *                userMobile
     *                userEmail
     *                entName
     *                统计日期 前一天的时间
     *                statisticsDateStart
     *                statisticsDateEnd
     *                获取数据日期
     *                createDateStart
     *                createDateEnd
     * @return
     */
    int selectUserDayAmountCount(UserDTO userDTO);

    /**
     * 用户增值日报表查询 excel导出
     *
     * @param excelUserList selectUserDayAmountList查询出的集合
     * @return
     */
    List<List<String>> excelUserDayAmountList(List<TbIncrementUser> excelUserList);
}
