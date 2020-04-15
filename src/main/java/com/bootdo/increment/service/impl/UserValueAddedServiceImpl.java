package com.bootdo.increment.service.impl;

import com.bootdo.Utils.BigDecimalUtil;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.increment.dao.UserValueAddedDao;
import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.TbIncrementUserAccount;
import com.bootdo.increment.domain.dto.UserDTO;
import com.bootdo.increment.service.UserValueAddedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工增值持有业务:
 * selectUserValueAddList
 * selectUserValueAddCount
 * excelUserValueAddList
 * 增值奖励查询业务：
 * selectValueAddRewardList
 * selectValueAddRewardCount
 * excelValueAddRewardList
 * 奖励发放订单业务
 * selectAwardingOrderList
 * selectAwardingOrderCount
 * excelAwardingOrderList
 *
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-12 14:55
 **/
@Service
public class UserValueAddedServiceImpl implements UserValueAddedService {
    @Autowired
    UserValueAddedDao userValueAddedDao;

    /**
     * 员工增值持有业务
     */

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
     *                userPageon当前页
     * @return
     */
    @Override
    public List<TbIncrementUser> selectUserValueAddList(UserDTO userDTO) {
        return userValueAddedDao.selectUserValueAddList(userDTO);
    }

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
    @Override
    public int selectUserValueAddCount(UserDTO userDTO) {
        return userValueAddedDao.selectUserValueAddCount(userDTO);
    }

    /**
     * 员工增值持有数据Excel导出
     *
     * @param excelUserList 根据selectUserValueAddList方法查到的用户数据集合
     * @return
     */
    @Override
    public List<List<String>> excelUserValueAddList(List<TbIncrementUser> excelUserList) {
        //       将查询的结果放入userLists
        List<List<String>> userLists = new ArrayList<List<String>>();
//        遍历结果放入userList集合
        for (int i = 0; i < excelUserList.size(); i++) {
            List<String> userList = new ArrayList<String>();
            TbIncrementUser userNew = excelUserList.get(i);
//            姓名
            userList.add(userNew.getUserName());
//            手机号
            userList.add(userNew.getUserMobile());
//            邮箱
            userList.add(userNew.getUserEmail());
//            将数据库参数与实际显示文字做对应   用户渠道
            if ("01".equals(userNew.getUserChannelId())) {
                userList.add("嘉福");
            } else {
                userList.add("嘉薪");
            }
//            企业名称
            userList.add(userNew.getEntName());
            for (TbIncrementUserAccount tbIncrementUserAccount : userNew.getUserAccountList()) {
//                持有金额
                userList.add(tbIncrementUserAccount.getIncrementAmount());
                //01 工资账户 02 福利账户 03福豆账户 04 现金账户
                switch (tbIncrementUserAccount.getAccountType()) {
                    case "01":
                        userList.add("工资账户");
                        break;
                    case "02":
                        userList.add("福利账户");
                        break;
                    case "03":
                        userList.add("福豆账户");
                        break;
                    case "04":
                        userList.add("现金账户");
                        break;
                }
//                更新时间
                userList.add(DateUtils.yyyyMMddHHmmssformat(tbIncrementUserAccount.getUpdateDate()));
//                创建时间
                userList.add(DateUtils.yyyyMMddHHmmssformat(tbIncrementUserAccount.getCreateDate()));
            }
            userLists.add(userList);
        }
        return userLists;
    }


/**
 * 增值奖励业务
 */


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
    @Override
    public List<TbIncrementUser> selectValueAddRewardList(UserDTO userDTO) {
        return userValueAddedDao.selectValueAddRewardList(userDTO);
    }

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
    @Override
    public int selectValueAddRewardCount(UserDTO userDTO) {
        return userValueAddedDao.selectValueAddRewardCount(userDTO);
    }


    /**
     * 增值奖励查询 excel导出
     *
     * @param excelUserList
     * @return
     */
    @Override
    public List<List<String>> excelValueAddRewardList(List<TbIncrementUser> excelUserList) {
        //       将查询的结果放入userLists
        List<List<String>> userLists = new ArrayList<List<String>>();
//        遍历结果放入userList集合
        for (int i = 0; i < excelUserList.size(); i++) {
            List<String> userList = new ArrayList<String>();
            TbIncrementUser userNew = excelUserList.get(i);
//            统计日期
            if (userNew.getIncrementOrderList().get(0).getIncrementRewardList().get(0).getStatisticsDate()==null
            ||userNew.getIncrementOrderList().get(0).getIncrementRewardList().get(0).getStatisticsDate().equals("")){
                userList.add("-");
            }else {
                userList.add(DateUtils.format(userNew.getIncrementOrderList().get(0).getIncrementRewardList().get(0).getStatisticsDate()));
            }
//            姓名
            userList.add(userNew.getUserName());
//            手机号
            userList.add(userNew.getUserMobile());
//            邮箱
            userList.add(userNew.getUserEmail());
//            将数据库参数与实际显示文字做对应   用户渠道
            if ("01".equals(userNew.getUserChannelId())) {
                userList.add("嘉福");
            } else {
                userList.add("嘉薪");
            }
//            企业名称
            userList.add(userNew.getEntName());
            //01 工资账户 02 福利账户 03福豆账户 04 现金账户
            switch (userNew.getUserAccountList().get(0).getAccountType()) {
                case "01":
                    userList.add("工资账户");
                    break;
                case "02":
                    userList.add("福利账户");
                    break;
                case "03":
                    userList.add("福豆账户");
                    break;
                case "04":
                    userList.add("现金账户");
                    break;
            }
//                  当日转入金额
            userList.add(BigDecimalUtil.BigDecimalDivide(userNew.getIncrementOrderList().get(0).getIncrementRewardList().get(0).getAddAmount(), "100", 2));
//            持有金额
            userList.add(BigDecimalUtil.BigDecimalDivide(userNew.getUserAccountList().get(0).getIncrementAmount(), "100", 2));
//                    年化率
            userList.add(userNew.getIncrementOrderList().get(0).getIncrementRewardList().get(0).getAnnualizedRate() + "%");
//                    当日奖励
            userList.add(BigDecimalUtil.BigDecimalDivide(userNew.getIncrementOrderList().get(0).getIncrementRewardList().get(0).getRewardAmount(), "100", 2));
//            累计奖励
            userList.add(BigDecimalUtil.BigDecimalDivide(userNew.getUserAccountList().get(0).getRewardAmount(),"100",2));
//            关联订单号码
            userList.add(userNew.getIncrementOrderList().get(0).getOrderId().toString());
            switch (userNew.getIncrementOrderList().get(0).getOrderState()) {
                case "0":
                    userList.add("待发放");
                    break;
                case "1":
                    userList.add("发放成功");
                    break;
                case "2":
                    userList.add("发放中");
                    break;
                case "3":
                    userList.add("发放失败");
                    break;
            }
            if (userNew.getIncrementOrderList().get(0).getIncrementRewardList().get(0).getCreateDate()==null
            ||userNew.getIncrementOrderList().get(0).getIncrementRewardList().get(0).getCreateDate().equals("")){
                userList.add("-");
            }else {
                userList.add(DateUtils.yyyyMMddHHmmssformat(userNew.getIncrementOrderList().get(0).getIncrementRewardList().get(0).getCreateDate()));
            }
            userLists.add(userList);
        }
        return userLists;
    }


    /**
     * 奖励发放业务
     */

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
     * @return
     */
    @Override
    public List<TbIncrementUser> selectAwardingOrderList(UserDTO userDTO) {
        return userValueAddedDao.selectAwardingOrderList(userDTO);
    }

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
     * @return
     */
    @Override
    public int selectAwardingOrderCount(UserDTO userDTO) {
        return userValueAddedDao.selectAwardingOrderCount(userDTO);
    }

    @Override
    public List<List<String>> excelAwardingOrderList(List<TbIncrementUser> excelUserList) {
        //       将查询的结果放入userLists
        List<List<String>> userLists = new ArrayList<List<String>>();
//        遍历结果放入userList集合
        for (int i = 0; i < excelUserList.size(); i++) {
            List<String> userList = new ArrayList<String>();
            TbIncrementUser userNew = excelUserList.get(i);
//            发放订单号
            userList.add(userNew.getIncrementOrderList().get(0).getOrderId().toString());
//            嘉福流水号
            userList.add(userNew.getIncrementOrderList().get(0).getExtOrderId());
//            姓名
            userList.add(userNew.getUserName());
//            手机号
            userList.add(userNew.getUserMobile());
//            邮箱
            userList.add(userNew.getUserEmail());
//            将数据库参数与实际显示文字做对应   用户渠道
            if ("01".equals(userNew.getUserChannelId())) {
                userList.add("嘉福");
            } else {
                userList.add("嘉薪");
            }
//            企业名称
            userList.add(userNew.getEntName());
//            工资增值奖励
            userList.add(BigDecimalUtil.BigDecimalDivide(userNew.getIncrementOrderList().get(0).getSalaryRewardAmount(), "100", 2));
//            福利增值奖励
            userList.add(BigDecimalUtil.BigDecimalDivide(userNew.getIncrementOrderList().get(0).getBenefitRewardAmount(), "100", 2));
//            现金增值奖励
            userList.add(BigDecimalUtil.BigDecimalDivide(userNew.getIncrementOrderList().get(0).getCashRewardAmount(), "100", 2));
//            总奖励
            userList.add(BigDecimalUtil.BigDecimalDivide(userNew.getIncrementOrderList().get(0).getTotalRewardAmount(), "100", 2));
//              fudou
            userList.add(userNew.getIncrementOrderList().get(0).getTotalRewardAmount());
//            fudou发放状态
            switch (userNew.getIncrementOrderList().get(0).getOrderState()){
                case "0":
                    userList.add("待发放");
                    break;
                case "1":
                    userList.add("发放成功");
                    break;
                case "2":
                    userList.add("发放中");
                    break;
                case "3":
                    userList.add("发放失败");
                    break;
            }
//            统计日期
            if (userNew.getIncrementOrderList().get(0).getStatisticsDate() == null || userNew.getIncrementOrderList().get(0).getStatisticsDate().equals("")) {
                userList.add("-");
            } else {
                userList.add(DateUtils.format(userNew.getIncrementOrderList().get(0).getStatisticsDate()));
            }
//              订单创建时间
            if (userNew.getIncrementOrderList().get(0).getCreateDate() == null || userNew.getIncrementOrderList().get(0).getCreateDate().equals("")) {
                userList.add("-");
            } else {
                userList.add(DateUtils.yyyyMMddHHmmssformat(userNew.getIncrementOrderList().get(0).getCreateDate()));
            }
//            发放时间
            if (userNew.getIncrementOrderList().get(0).getUpdateDate() == null || userNew.getIncrementOrderList().get(0).getUpdateDate().equals("")) {
                userList.add("-");
            } else {
                userList.add(DateUtils.yyyyMMddHHmmssformat(userNew.getIncrementOrderList().get(0).getUpdateDate()));
            }
//            完成时间
            if (userNew.getIncrementOrderList().get(0).getComplateDate() == null || userNew.getIncrementOrderList().get(0).getComplateDate().equals("")) {
                userList.add("-");
            } else {
                userList.add(DateUtils.yyyyMMddHHmmssformat(userNew.getIncrementOrderList().get(0).getComplateDate()));
            }
            userLists.add(userList);
        }
        return userLists;
    }


}
