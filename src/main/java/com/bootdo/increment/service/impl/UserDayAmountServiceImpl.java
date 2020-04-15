package com.bootdo.increment.service.impl;

import com.bootdo.Utils.BigDecimalUtil;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.increment.dao.UserDayAmountDao;
import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.dto.UserDTO;
import com.bootdo.increment.service.UserDayAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户增值日报表实现类
 *
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-15 11:11
 **/
@Service
public class UserDayAmountServiceImpl implements UserDayAmountService {
    @Autowired
    private UserDayAmountDao userDayAmountDao;

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
    @Override
    public List<TbIncrementUser> selectUserDayAmountList(UserDTO userDTO) {
        return userDayAmountDao.selectUserDayAmountList(userDTO);
    }

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
    @Override
    public int selectUserDayAmountCount(UserDTO userDTO) {
        return userDayAmountDao.selectUserDayAmountCount(userDTO);
    }

    /**
     * 用户增值日报表查询Excel导出
     *
     * @param excelUserList selectUserDayAmountList查询出的集合
     * @return
     */
    @Override
    public List<List<String>> excelUserDayAmountList(List<TbIncrementUser> excelUserList) {
        //       将查询的结果放入userLists
        List<List<String>> userLists = new ArrayList<List<String>>();
//        遍历结果放入userList集合
        for (int i = 0; i < excelUserList.size(); i++) {
            List<String> userList = new ArrayList<String>();
            TbIncrementUser userNew = excelUserList.get(i);
            if (userNew.getIncrementUserAccount().getUserDayAmount().getStatisticsDate() == null
                    || userNew.getIncrementUserAccount().getUserDayAmount().getStatisticsDate().equals("")) {
                userList.add("-");
            } else {
                userList.add(DateUtils.format(userNew.getIncrementUserAccount().getUserDayAmount().getStatisticsDate()));
            }
            userList.add(userNew.getUserName());
            userList.add(userNew.getUserMobile());
            userList.add(userNew.getUserEmail());
            userList.add(userNew.getIncrementUserAccount().getUserDayAmount().getIncrementAmount());
//            将数据库参数与实际显示文字做对应
            if ("01".equals(userNew.getUserChannelId())) {
                userList.add("嘉福");
            } else {
                userList.add("嘉薪");
            }
            userList.add(userNew.getIncrementUserAccount().getUserDayAmount().getJiaxinEntId());
            userList.add(userNew.getIncrementUserAccount().getUserDayAmount().getJiaxinEntName());
            userList.add(BigDecimalUtil.BigDecimalDivide(userNew.getIncrementUserAccount().getUserDayAmount().getRightAmount(),"100",2));
            if (userNew.getIncrementUserAccount().getUserDayAmount().getCreateDate() == null || userNew.getIncrementUserAccount().getUserDayAmount().getCreateDate().equals("")) {
                userList.add("-");
            } else {
                userList.add(
                        DateUtils.yyyyMMddHHmmssformat(userNew.getIncrementUserAccount().getUserDayAmount().getCreateDate())
                );
            }
            userLists.add(userList);
        }
        return userLists;
    }
}
