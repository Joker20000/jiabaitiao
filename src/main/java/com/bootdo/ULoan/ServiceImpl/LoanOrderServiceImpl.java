package com.bootdo.ULoan.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.Cdomain.domain.OverdueFlow;
import com.bootdo.RepayPalan.domain.RepayPlan;
import com.bootdo.ULoan.Service.LoanOrderService;
import com.bootdo.ULoan.dao.LoanOrderDao;
import com.bootdo.ULoan.domain.ConsumeOrder;
import com.bootdo.ULoan.domain.LoanOrder;
import com.bootdo.Utils.BigDecimalUtil;
import com.bootdo.Utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("LoanOrderService")
public class LoanOrderServiceImpl implements LoanOrderService {
  
	@Autowired
	private LoanOrderDao loanOrderDao;

	@Override
	public List<LoanOrder> findLoanOrderAndLoanPepayPlan(Map<String, Object> map) {
		return loanOrderDao.findLoanOrderAndLoanPepayPlan(map);
	}

	@Override
	public List<LoanOrder> findLoanOrderByCondition(Map<String, Object> map) {
		List<LoanOrder> loanOrderList = loanOrderDao.findLoanOrderByCondition(map);
		for (LoanOrder loanOrder:loanOrderList){
			String loanOrderNo = loanOrder.getLoanOrderNo();
			Map<String,Object> repayPlan = loanOrderDao.queryRepayPlan(loanOrderNo);
			Map<String,Object> repayPlanSum = loanOrderDao.queryRepayPlanSum(loanOrderNo);
			if(repayPlan!=null){
				loanOrder.setPeriodRate(String.valueOf(repayPlan.get("PERIOD_RATE")));
				loanOrder.setTransferCost(String.valueOf(repayPlan.get("TRANSFER_COST")));
				loanOrder.setTransferRate(String.valueOf(repayPlan.get("TRANSFER_RATE")));
				loanOrder.setOverdueManageRate(String.valueOf(repayPlan.get("OVERDUE_MANAGE_RATE")));
				loanOrder.setOverdueDayRate(String.valueOf(repayPlan.get("OVERDUE_DAY_RATE")));
				//loanOrder.setNowAmountSum(String.valueOf(repayPlan.get("nowAmountSum")));
			}else{
				loanOrder.setPeriodRate("");
				loanOrder.setTransferCost("");
				loanOrder.setTransferRate("");
				loanOrder.setOverdueManageRate("");
				loanOrder.setOverdueDayRate("");
				//loanOrder.setNowAmountSum("");
			}
			if(repayPlanSum!=null){
				loanOrder.setNowOrderInterestSum(String.valueOf(repayPlanSum.get("nowOrderInterestSum")));
				loanOrder.setNowInterestSum(String.valueOf(repayPlanSum.get("nowInterestSum")));
				loanOrder.setNowOverdueSum(String.valueOf(repayPlanSum.get("nowOverdueSum")));
				loanOrder.setReadyAmountSum(String.valueOf(repayPlanSum.get("readyAmountSum")));
				loanOrder.setRepaymentAmountSum(String.valueOf(repayPlanSum.get("repaymentAmountSum")));
			}else{
				loanOrder.setNowOrderInterestSum("");
				loanOrder.setNowInterestSum("");
				loanOrder.setNowOverdueSum("");
				loanOrder.setReadyAmountSum("");
				loanOrder.setRepaymentAmountSum("");
			}
		}
		return loanOrderList;
	}

	@Override
	public ArrayList<HashMap<String, Object>> findLoanOrderByConditionN(Map<String, Object> map) {
		ArrayList<HashMap<String, Object>> dateList = loanOrderDao.findLoanOrderByConditionN(map);
		for (int i = 0;i < dateList.size();i++){
			String loanOrderNo = String.valueOf(dateList.get(i).get("loanOrderNo"));
			Map<String,Object> repayPlan = loanOrderDao.queryRepayPlan(loanOrderNo);
			Map<String,Object> repayPlanSum = loanOrderDao.queryRepayPlanSum(loanOrderNo);
			String amount = loanOrderDao.findRefundOrderListByLoanOrderNo(loanOrderNo);
			dateList.get(i).put("refundDiAmountSum",amount);
			if(repayPlan!=null){
				//dateList.get(i).put("nowAmountSum",String.valueOf(repayPlan.get("nowAmountSum")));
				dateList.get(i).put("periodRate",String.valueOf(repayPlan.get("PERIOD_RATE")));
				dateList.get(i).put("transferCost",String.valueOf(repayPlan.get("TRANSFER_COST")));
				dateList.get(i).put("transferRate",String.valueOf(repayPlan.get("TRANSFER_RATE")));
				dateList.get(i).put("overdueManageRate",String.valueOf(repayPlan.get("OVERDUE_MANAGE_RATE")));
				dateList.get(i).put("overdueDayRate",String.valueOf(repayPlan.get("OVERDUE_DAY_RATE")));
				dateList.get(i).put("latestTime",String.valueOf(repayPlan.get("LATEST_TIME")));

			}else{
				//dateList.get(i).put("nowAmountSum","");
				dateList.get(i).put("periodRate","");
				dateList.get(i).put("transferCost","");
				dateList.get(i).put("transferRate","");
				dateList.get(i).put("overdueManageRate","");
				dateList.get(i).put("overdueDayRate","");
				dateList.get(i).put("latestTime","");
			}
			if(repayPlanSum!=null){
				dateList.get(i).put("nowInterestSum",String.valueOf(repayPlanSum.get("nowInterestSum")));
				dateList.get(i).put("nowOrderInterestSum",String.valueOf(repayPlanSum.get("nowOrderInterestSum")));
				dateList.get(i).put("readyAmountSum",String.valueOf(repayPlanSum.get("readyAmountSum")));
				dateList.get(i).put("repaymentAmountSum",String.valueOf(repayPlanSum.get("repaymentAmountSum")));
				dateList.get(i).put("nowOverdueSum",String.valueOf(repayPlanSum.get("nowOverdueSum")));
				dateList.get(i).put("paymentChannelAmount", String.valueOf(repayPlanSum.get("nowOrderInterestSum")));

			}else {
				dateList.get(i).put("nowInterestSum","");
				dateList.get(i).put("nowOrderInterestSum","");
				dateList.get(i).put("readyAmountSum","");
				dateList.get(i).put("repaymentAmountSum","");
				dateList.get(i).put("nowOverdueSum","");
				dateList.get(i).put("paymentChannelAmount","");

			}
		}
		return dateList;
	}

	@Override
	public Long findLoanOrderCountByCondition(Map<String, Object> map) {
		return loanOrderDao.findLoanOrderCountByCondition(map);
	}

	@Override
	public String findRefundOrderListByLoanOrderNo(String loanOrderNo) {
		return loanOrderDao.findRefundOrderListByLoanOrderNo(loanOrderNo);
	}

	@Override
	public List<OverdueFlow> getOverDueFlowByRepayPlanNo(Map<String, Object> map) {
		return loanOrderDao.getOverDueFlowByRepayPlanNo(map);
	}

	@Override
	public Long getOverDueFlowByRepayPlanNoCount(Map<String, Object> map) {
		return loanOrderDao.getOverDueFlowByRepayPlanNoCount(map);
	}

	@Override
	public ConsumeOrder getConsumeDetail(String id) {
		return loanOrderDao.getConsumeDetail(id);
	}
}
