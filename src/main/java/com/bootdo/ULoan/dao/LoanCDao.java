package com.bootdo.ULoan.dao;

import com.bootdo.AccounTtrading.domain.AccounTtrading;
import com.bootdo.Cdomain.domain.OverFlow;
import com.bootdo.RepayFlow.domain.RepayFlow;
import com.bootdo.RepayPalan.domain.RepayPlan;
import com.bootdo.RepayRecord.domain.RepayRecord;
import com.bootdo.UserManage.domain.UserJBT;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public interface LoanCDao {

	public List<UserJBT> findUserList(Map<String, Object> map);
	
	public Long getTotalUserList(Map<String, Object> map);

	public List<RepayPlan> findRepayPlanList(Map<String, Object> map);
	
	public Long getTotalRepayPlanList(Map<String, Object> map);

	public List<AccounTtrading> findAccounTtradingList(Map<String, Object> map);

	public ArrayList<HashMap<String, Object>> findAccounTtradingListN(Map<String, Object> map);
	
	public Long getTotalAccounTtradingList(Map<String, Object> map);

	public List<RepayFlow> findRepayFlowList(Map<String, Object> map);
	
	public Long getTotalRepayFlowList(Map<String, Object> map);
	
	public List<OverFlow> findRepayOverList(Map<String, Object> map);
	
	public Long getTotalRepayOverList(Map<String, Object> map);

	public List<RepayRecord> findRepayRecordList(Map<String, Object> map);

	public Long getTotalRepayRecordList(Map<String, Object> map);

	public ArrayList<HashMap<String, Object>> findUserListN(Map<String,Object> map);

	public ArrayList<HashMap<String, Object>> findRepayPlanListN(Map<String, Object> map);

	public List<HashMap<String,Object>> findRepayRecordListN(Map<String, Object> map);

	@Select("SELECT(SUM(CAST(tb.OFFSET_AMOUNT AS DECIMAL (30, 2)))) AS realRefundAmount FROM TB_CONSUME_REFUND_ORDER tb WHERE LOAN_ORDER_NO = #{loanOrderNo} and REFUND_STATE !='03'")
	String  findRefundOrderAmountByLoanOrderNo(@Param("loanOrderNo") String loanOrderNo);

}
