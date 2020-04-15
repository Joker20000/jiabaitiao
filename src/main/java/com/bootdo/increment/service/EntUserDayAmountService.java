package com.bootdo.increment.service;

import com.bootdo.increment.domain.TbEntUserDayAmount;
import com.bootdo.increment.domain.TbIncrementUser;

import java.util.List;

/**
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-15 12:01
 **/
public interface EntUserDayAmountService {
    /**
     * 企业用户增值日报表查询
     *
     * @param entUserDayAmount 查询条件
     *                         entId
     *                         entName 企业名称
     *                         channelId    渠道编号 ：01嘉福 02嘉薪
     *                         统计日期
     *                         statisticsDateStart
     *                         statisticsDateEnd
     *                         获取数据时间
     *                         createDateStart
     *                         createDateEnd
     * @return
     */
    List<TbEntUserDayAmount> selectEntUserDayAmountList(TbEntUserDayAmount entUserDayAmount);

    /**
     * 企业用户增值日报表查询count
     *
     * @param entUserDayAmount 查询条件
     *                         entId
     *                         entName 企业名称
     *                         channelId    渠道编号 ：01嘉福 02嘉薪
     *                         统计日期
     *                         statisticsDateStart
     *                         statisticsDateEnd
     *                         获取数据时间
     *                         createDateStart
     *                         createDateEnd
     * @return
     */
    int selectEntUserDayAmountCount(TbEntUserDayAmount entUserDayAmount);

    /**
     * 企业用户增值日报表查询Excel导出
     *
     * @param excelUserList selectEntUserDayAmountList查询出的集合
     * @return
     */
    List<List<String>> excelAwardingOrderList(List<TbEntUserDayAmount> excelUserList);
}
