package com.bootdo.ULoan.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bootdo.AccounTtrading.domain.AccounTtrading;
import com.bootdo.Cdomain.domain.OverFlow;
import com.bootdo.RepayFlow.domain.RepayFlow;
import com.bootdo.RepayPalan.domain.RepayPlan;
import com.bootdo.RepayRecord.domain.RepayRecord;
import com.bootdo.SaleChance.dao.SaleChanceDao;
import com.bootdo.ULoan.Service.LoanCService;
import com.bootdo.ULoan.dao.LoanCDao;
import com.bootdo.UserManage.domain.UserJBT;
import org.springframework.stereotype.Service;



/**
 * 销售机会Service实现类
 * @author Administrator
 *
 */
@Service("loanCService")
public class LoanCServiceImpl implements LoanCService {

	@Resource
	private SaleChanceDao saleChanceDao;
	
	@Resource
	private LoanCDao loanCDao;
	

	@Override
	public List<UserJBT> findUserList(Map<String, Object> map) {
		return loanCDao.findUserList(map);
	}

	@Override
	public Long getTotalUserList(Map<String, Object> map) {
		return loanCDao.getTotalUserList(map);
	}

	@Override
	public List<RepayPlan> findRepayPlanList(Map<String, Object> map) {
		List<RepayPlan> dataList =  loanCDao.findRepayPlanList(map);
		for (RepayPlan repayPlan:dataList){
			String loanOrderNo = repayPlan.getLoanOrderNo();
			String amount = loanCDao.findRefundOrderAmountByLoanOrderNo(loanOrderNo);
			repayPlan.setRefundAmount(amount);
		}
		return dataList;
	}

	@Override
	public Long getTotalRepayPlanList(Map<String, Object> map) {
		return loanCDao.getTotalRepayPlanList(map);
	}

	@Override
	public List<AccounTtrading> findAccounTtradingList(Map<String, Object> map) {
		return loanCDao.findAccounTtradingList(map);
	}

	@Override
	public Long getTotalAccounTtradingList(Map<String, Object> map) {
		return loanCDao.getTotalAccounTtradingList(map);
	}

	@Override
	public List<RepayFlow> findRepayFlowList(Map<String, Object> map) {
		return loanCDao.findRepayFlowList(map);
	}

	@Override
	public Long getTotalRepayFlowList(Map<String, Object> map) {
		return loanCDao.getTotalRepayFlowList(map);
	}
	
	@Override
	public List<OverFlow> findRepayOverList(Map<String, Object> map) {
		return loanCDao.findRepayOverList(map);
	}
	
	@Override
	public Long getTotalRepayOverList(Map<String, Object> map) {
		return loanCDao.getTotalRepayOverList(map);
	}

	@Override
	public List<RepayRecord> findRepayRecordList(Map<String, Object> map) {
		return loanCDao.findRepayRecordList(map);
	}

	@Override
	public Long getTotalRepayRecordList(Map<String, Object> map) {
		return loanCDao.getTotalRepayRecordList(map);
	}

	@Override
	public ArrayList<HashMap<String, Object>> findUserListN(Map<String, Object> map) {
		return loanCDao.findUserListN(map);
	}

	@Override
	public ArrayList<HashMap<String, Object>> findAccounTtradingListN(Map<String, Object> map) {return loanCDao.findAccounTtradingListN(map);}

	@Override
	public ArrayList<HashMap<String, Object>> findRepayPlanListN(Map<String, Object> map) {
		ArrayList<HashMap<String, Object>> dataList =  loanCDao.findRepayPlanListN(map);
		for (int i = 0;i < dataList.size();i++){
			String loanOrderNo = String.valueOf(dataList.get(i).get("loanOrderNo"));
			String amount = loanCDao.findRefundOrderAmountByLoanOrderNo(loanOrderNo);
			dataList.get(i).put("refundAmount",amount);
		}
		return dataList;
	}

	@Override
	public List<HashMap<String, Object>> findRepayRecordListN(Map<String, Object> map) {return loanCDao.findRepayRecordListN(map);}
}
