package com.bootdo.increment.service.impl;

import com.bootdo.Utils.BigDecimalUtil;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.increment.dao.UserTransactionDao;
import com.bootdo.increment.domain.TbIncrementTransferClearing;
import com.bootdo.increment.domain.TbIncrementUser;
import com.bootdo.increment.domain.TbIncrementUserAccount;
import com.bootdo.increment.domain.dto.UserDTO;
import com.bootdo.increment.service.UserTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户转入转出交易记录实现类
 *
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-13 13:06
 **/
@Service
public class UserTransactionServcieImpl implements UserTransactionService {
    @Autowired
    private UserTransactionDao userTransactionDao;

    /**
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
     * @return List<TbIncrementUser>
     */
    @Override
    public List<TbIncrementUser> selectUserTransactionList(UserDTO userDTO) {
        return userTransactionDao.selectUserTransactionList(userDTO);
    }

    /**
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
     * @return 多少条数据 计数
     */
    @Override
    public int selectUserTransactionCount(UserDTO userDTO) {
        return userTransactionDao.selectUserTransactionCount(userDTO);
    }


    /**
     * @param excelUserList 用户交易记录查询得到的 List<TbIncrementUser>
     * @return
     */
    @Override
    public List<List<String>> excelUserTransactionList(List<TbIncrementUser> excelUserList) {
        //       将查询的结果放入userLists
        List<List<String>> userLists = new ArrayList<List<String>>();
//        遍历结果放入userList集合
        for (int i = 0; i < excelUserList.size(); i++) {
            List<String> userList = new ArrayList<String>();
            TbIncrementUser userNew = excelUserList.get(i);
//            遍历拿交易流水号
            for (TbIncrementUserAccount tbIncrementUserAccount : userNew.getUserAccountList()) {
                for (TbIncrementTransferClearing transferClearing : tbIncrementUserAccount.getTransferClearingList()) {
                    userList.add(transferClearing.getClearingId().toString());
                }
            }
            userList.add(userNew.getUserName());
            userList.add(userNew.getUserMobile());
            userList.add(userNew.getUserEmail());
//            将数据库参数与实际显示文字做对应
            if ("01".equals(userNew.getUserChannelId())) {
                userList.add("嘉福");
            } else {
                userList.add("嘉薪");
            }
            userList.add(userNew.getEntName());

            for (TbIncrementUserAccount tbIncrementUserAccount : userNew.getUserAccountList()) {
                for (TbIncrementTransferClearing transferClearing : tbIncrementUserAccount.getTransferClearingList()) {
//            遍历拿交易金额
                    userList.add(BigDecimalUtil.BigDecimalDivide(transferClearing.getTransferAmount(), "100", 2));
//                    交易类型
                    if ("1".equals(transferClearing.getTransferType())) {
                        userList.add("转入（冻结）");
                    } else {
                        userList.add("转出（解冻）");
                    }
                }
//                账户类型
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
                for (TbIncrementTransferClearing transferClearing : tbIncrementUserAccount.getTransferClearingList()) {
//                   交易状态
                    switch (transferClearing.getTransferState()) {
                        case "1":
                            userList.add("成功");
                            break;
                        case "2":
                            userList.add("处理中");
                            break;
                        case "3":
                            userList.add("失败");
                            break;
                    }
//                    交易时间
                    if (transferClearing.getCreateDate() == null || transferClearing.getCreateDate().equals("")) {
                        userList.add("-");
                    } else {
                        userList.add(DateUtils.yyyyMMddHHmmssformat(transferClearing.getCreateDate()));
                    }
//                    交易完成时间
                    if (transferClearing.getComplateDate() == null || transferClearing.getComplateDate().equals("")) {
                        userList.add("-");
                    } else {
                        userList.add(DateUtils.yyyyMMddHHmmssformat(transferClearing.getComplateDate()));
                    }
                }
            }
            userLists.add(userList);
        }
        return userLists;
    }
}
