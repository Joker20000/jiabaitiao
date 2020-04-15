package com.bootdo.increment.dao;

import com.bootdo.increment.domain.TbEntUserDayAmount;

import java.util.List;

/**
 * 企业用户增值日报表（不只针对于嘉薪用户）
 *
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-15 11:53
 **/
public interface EntUserDayAmountDao {
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
}
