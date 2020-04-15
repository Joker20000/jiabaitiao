package com.bootdo.ULoan.Service;

import com.bootdo.Cdomain.domain.OverdueFlow;
import com.bootdo.RepayPalan.domain.RepayPlan;
import com.bootdo.ULoan.domain.ConsumeOrder;
import com.bootdo.ULoan.domain.LoanOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface LoanOrderService {
	//查还款计划表（优化）
	List<LoanOrder> findLoanOrderAndLoanPepayPlan(Map<String,Object> map);
	//根据条件查询所有
	List<LoanOrder> findLoanOrderByCondition(Map<String,Object> map);

	//根据条件下载
	ArrayList<HashMap<String, Object>> findLoanOrderByConditionN(Map<String,Object> map);

	//根据条件查询数据总条数
	Long findLoanOrderCountByCondition(Map<String,Object> map);

	String findRefundOrderListByLoanOrderNo(String loanOrderNo);




	//根据还款计划获得所有逾期费用
	List<OverdueFlow> getOverDueFlowByRepayPlanNo(Map<String,Object> map);
	Long getOverDueFlowByRepayPlanNoCount(Map<String,Object> map);

	ConsumeOrder getConsumeDetail(String id);
}
