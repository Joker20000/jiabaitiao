package com.bootdo.increment.service.impl;

import com.bootdo.Utils.BigDecimalUtil;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.increment.dao.EntUserDayAmountDao;
import com.bootdo.increment.domain.TbEntUserDayAmount;
import com.bootdo.increment.service.EntUserDayAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-15 12:02
 **/
@Service
public class EntUserDayAmountServiceImpl implements EntUserDayAmountService {
    @Autowired
    private EntUserDayAmountDao entUserDayAmountDao;

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
    @Override
    public List<TbEntUserDayAmount> selectEntUserDayAmountList(TbEntUserDayAmount entUserDayAmount) {
        return entUserDayAmountDao.selectEntUserDayAmountList(entUserDayAmount);
    }

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
    @Override
    public int selectEntUserDayAmountCount(TbEntUserDayAmount entUserDayAmount) {
        return entUserDayAmountDao.selectEntUserDayAmountCount(entUserDayAmount);
    }

    /**
     * 企业用户增值日报表查询Excel导出
     *
     * @param excelUserList selectEntUserDayAmountList查询出的集合
     * @return
     */
    @Override
    public List<List<String>> excelAwardingOrderList(List<TbEntUserDayAmount> excelUserList) {
        //       将查询的结果放入userLists
        List<List<String>> userLists = new ArrayList<List<String>>();
//        遍历结果放入userList集合
        for (int i = 0; i < excelUserList.size(); i++) {
            List<String> userList = new ArrayList<String>();
            TbEntUserDayAmount userNew = excelUserList.get(i);
            userList.add(DateUtils.format(userNew.getStatisticsDate()));
            userList.add(userNew.getEntId());
            userList.add(userNew.getEntName());
//            将数据库参数与实际显示文字做对应
            if ("01".equals(userNew.getChannelId())) {
                userList.add("嘉福");
            } else {
                userList.add("嘉薪");
            }
            userList.add(BigDecimalUtil.BigDecimalDivide(userNew.getTotalRewardAmount(),"100",2));
            if (userNew.getCreateDate() == null || userNew.getCreateDate().equals("")) {
                userList.add("-");
            } else {
                userList.add(DateUtils.yyyyMMddHHmmssformat(userNew.getCreateDate()));
            }
            userLists.add(userList);
        }
        return userLists;
    }
}

