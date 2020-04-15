package com.bootdo.ULoan.dao;

import com.bootdo.Cdomain.domain.OverdueFlow;
import com.bootdo.RepayPalan.domain.RepayPlan;
import com.bootdo.ULoan.domain.ConsumeOrder;
import com.bootdo.ULoan.domain.LoanOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface LoanOrderDao {
	//查还款计划表（优化）
	List<LoanOrder> findLoanOrderAndLoanPepayPlan(Map<String,Object> map);
	//根据条件查询所有
	List<LoanOrder> findLoanOrderByCondition(Map<String,Object> map);
	//根据条件下载
	ArrayList<HashMap<String, Object>> findLoanOrderByConditionN(Map<String,Object> map);
	//根据条件查询数据总条数
	Long findLoanOrderCountByCondition(Map<String,Object> map);
	//根据还款计划获得所有逾期费用
	List<OverdueFlow> getOverDueFlowByRepayPlanNo(Map<String,Object> map);

	Long getOverDueFlowByRepayPlanNoCount(Map<String,Object> map);
	//下面两个查询订单部分  然后放进集合
	@Select("select  tp.PERIOD_RATE,tp.TRANSFER_COST,tp.TRANSFER_RATE,tp.OVERDUE_MANAGE_RATE,tp.OVERDUE_DAY_RATE,tp.LATEST_TIME from  TB_REPAY_PLAN  tp,TB_LOAN_ORDER tl where tp.LOAN_ORDER_NO=#{loanOrderNo} ORDER BY tp.LATEST_TIME DESC LIMIT 0,1")
    Map<String,Object> queryRepayPlan(@Param("loanOrderNo") String loanOrderNo);
	@Select("select sum(CAST(tp.NOW_ORDER_INTEREST as DECIMAL(30,2)))as 'nowOrderInterestSum',sum(CAST(tp.NOW_INTEREST as DECIMAL(30,2)))as  'nowInterestSum',sum(CAST(tp.NOW_OVERDUE as DECIMAL(30,2)))as  'nowOverdueSum',SUM((CAST(tp.NOW_READY_AMOUNT as DECIMAL(30,2))+CAST(tp.NOW_READY_ORDER_INTEREST as DECIMAL(30,2))+CAST(tp.NOW_READY_INTEREST as DECIMAL(30,2))+CAST(tp.NOW_READY_OVERDUE as DECIMAL(30,2))))as  'readyAmountSum',SUM(((CAST(tp.NOW_AMOUNT as DECIMAL(30,2))+CAST(tp.NOW_ORDER_INTEREST as DECIMAL(30,2))+CAST(tp.NOW_INTEREST as DECIMAL(30,2))+CAST(tp.NOW_OVERDUE as DECIMAL(30,2))) - (CAST(tp.NOW_READY_AMOUNT as DECIMAL(30,2))+CAST(tp.NOW_READY_ORDER_INTEREST as DECIMAL(30,2))+CAST(tp.NOW_READY_INTEREST as DECIMAL(30,2))+CAST(tp.NOW_READY_OVERDUE as DECIMAL(30,2))))) as  'repaymentAmountSum'from TB_REPAY_PLAN  tp where tp.LOAN_ORDER_NO=#{loanOrderNo}")
	Map<String,Object> queryRepayPlanSum(@Param("loanOrderNo") String loanOrderNo);

	@Select("SELECT(sum(if(REFUND_STATE = '02',CAST(tb.REFUND_AMOUNT AS DECIMAL (30, 2)),CAST(tb.OFFSET_AMOUNT AS DECIMAL (30, 2))))) AS realRefundAmount FROM TB_CONSUME_REFUND_ORDER tb WHERE LOAN_ORDER_NO = #{loanOrderNo}")
	String  findRefundOrderListByLoanOrderNo(@Param("loanOrderNo") String loanOrderNo);

   @Select("select tlo.CHANNEL_NO, case when tu.USER_CHANNEL_ID = '01' then '嘉福' when tu.USER_CHANNEL_ID = '02' then '嘉薪' else tu.USER_CHANNEL_ID end as channel" +
		   ",tcg.GOODS_NAME as goodName from TB_LOAN_ORDER tlo, TB_CONSUME_GOOD tcg,TB_USER tu WHERE tlo.LOAN_ORDER_NO = tcg.LOAN_ORDER_NO\n" +
		"and tlo.USER_ID=tu.USER_ID and tlo.LOAN_ORDER_NO =#{loanOrderNo}")
	public ConsumeOrder getConsumeDetail(@Param("loanOrderNo") String loanOrderNo);

}
   
