package com.bootdo.AfterLoanAccount.Service;


import com.bootdo.AfterLoanAccount.domain.SmsLoanOrder;
import com.bootdo.RepayPalan.domain.RepayPlan;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AfterLoanAccountService {
    /**
     * 待催任务列表查询
     * @param map
     * @return
     */
    public List<RepayPlan> findAfterLoanAccountList(Map<String, Object> map);
    /**
     * 查询记录数
     * @param map
     * @return
     */
    public Long getTotaAfterLoanAccountList(Map<String,Object> map);
    /**
     * 查询记录数
     * @param acccountNo
     * @return
     */
    public List<Map<String,String>> getList(String acccountNo);

    /**
     * 短信内容 2
     *
     * @return
     */
    public List<Map<String,String>> getMessageList();

    /**
     * 手机号
     *
     * @return
     */
    public String getMobile(String acccountNo);

    //查询用户还款记录，用户费用减免、发送短信。
    Map<String, Object> searchUserRemaRepayByNo(String acccountNo, String repayPlanNo);

    //查询用户当期数据，用于费用减免
    Map<String,Object> searchUserNowFeeByRepayNo(String repayPlanNo);

    //插入消息发送记录
    public int SmsLoanOrder(SmsLoanOrder smsLoanOrder);

    //查询发送信息记录
    public List<SmsLoanOrder> findAfterSmsLoanOrder(Map<String, Object> map);

    /**
     * 查询发送信息记录数
     * @param map
     * @return
     */
    public Long getTotaAfterSmsLoanOrder(Map<String,Object> map);

    void saveLoanInfoRecord(Long userId, String userName, String contents, String accountNo);

    List<Map<String,String>> getLoanAfterInfo(String accountNo);
}
