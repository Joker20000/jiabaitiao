package com.bootdo.AfterLoanAccount.dao;

import com.bootdo.AfterLoanAccount.domain.SmsLoanOrder;
import com.bootdo.RepayPalan.domain.RepayPlan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AfterLoanAccountDao {
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
     * 下载
     * @param map
     * @return
     */
    List<HashMap<String,Object>> BacExcel(Map<String, Object> map);


    /**
     * 查询记录数
     * @param acccountNo
     * @return
     */
    public List<Map<String,String>> getList(@Param("acccountNo")String acccountNo);

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
    public String getMobile(@Param("accountNo")String acccountNo);

    //查询用户还款记录，用户费用减免、发送短信。
    Map<String, Object> searchUserRemaRepayByNo(@Param("accountNo") String acccountNo, @Param("repayPlanNo") String repayPlanNo);

    //查询用户当期数据，用于费用减免
    Map<String,Object> searchUserNowFeeByRepayNo(@Param("repayPlanNo")String repayPlanNo);

    //插入发送信息记录
    @Insert("INSERT INTO TB_SMS_LOAN_RECORD(PHONE,SEND_CONTENT,OPERATOR,OPERATOR_ID,CREATE_TIME,Loan_name,Loan_Phone) VALUES (#{phone},#{sendContent},#{operator},#{operatorId},#{createTime},#{loanName},#{loanPhone})")
    int insertSmsLoanOrder(SmsLoanOrder smsLoanOrder);
    //插入发送信息记录
    public int SmsLoanOrder(SmsLoanOrder smsLoanOrder);

    //查询发送信息记录
    public List<SmsLoanOrder> findAfterSmsLoanOrder(Map<String, Object> map);

    /**
     * 查询发送信息记录数
     * @param map
     * @return
     */
    public Long getTotaAfterSmsLoanOrder(Map<String,Object> map);

    @Insert("INSERT INTO TB_LOAN_INFO_RECORD(OPERATOR_ID,OPERATOR,CONTENT,ACCOUNT_NO) VALUES (#{userId},#{userName},#{contents},#{accountNo})")
    int saveLoanInfoRecord(@Param("userId") Long userId,@Param("userName") String userName,@Param("contents") String contents,@Param("accountNo") String accountNo);

    @Select("SELECT t.CONTENT content,t.OPERATOR operator,DATE_FORMAT(t.CREATE_TIME, '%Y-%m-%d %H:%i:%s') createTime FROM TB_LOAN_INFO_RECORD t WHERE t.ACCOUNT_NO = #{accountNo} ORDER BY t.CREATE_TIME DESC")
    List<Map<String,String>> getLoanAfterInfo(@Param("accountNo") String accountNo);
}
