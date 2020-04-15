package com.bootdo.Loan.dao;

import com.bootdo.Loan.domain.LoanEntity;
import com.bootdo.Loan.domain.LoanExcelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.*;

/**
 * 通知通告
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-05 17:11:16
 */
@Mapper
public interface LoanDao {

	List<LoanEntity> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int updateChackState(@Param("loanNum") String loanNum,@Param("state") String state);

	@Update("UPDATE t_loan_order SET order_state = #{s},repay_state = #{s1},over_state = #{s2},loan_date = #{loanDate},repay_date = #{repayDate},chack_date = #{loanDate},chack_id = #{chackId},chack_name = #{chackName} WHERE loan_num = #{loanNum}")
    void updateLoanOrderState(@Param("loanNum") String loanNum,@Param("s") String s,@Param("s1") String s1,@Param("s2") String s2,@Param("loanDate") Date loanDate,@Param("repayDate") Date repayDate,@Param("chackId") String chackId,@Param("chackName") String chackName);

    @Select("SELECT t.account_no as accountNo,t.loan_day as loanDay,t.amount as amount,t.link_phone as linkPhone,t.guarant_phone as guarantPhone FROM t_loan_order t WHERE t.loan_num = #{loanNum}")
    Map<String,String> getLoanOrderDesc(@Param("loanNum") String loanNum);

    @Update("UPDATE t_loan_order SET order_state = #{orderState},chack_date = #{nowDate},chack_id = #{chackId},chack_name = #{chackName} WHERE loan_num = #{loanNum}")
    void updateLoanOrder(@Param("loanNum") String loanNum,@Param("orderState") String orderState,@Param("nowDate") Date nowDate,@Param("chackId") String chackId,@Param("chackName") String chackName);

    List<HashMap<String,Object>> queryLoanLists(Map<String,Object> map);
}
