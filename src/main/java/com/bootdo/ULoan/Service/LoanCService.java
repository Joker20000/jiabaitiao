package com.bootdo.ULoan.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.AccounTtrading.domain.AccounTtrading;
import com.bootdo.Cdomain.domain.OverFlow;
import com.bootdo.RepayFlow.domain.RepayFlow;
import com.bootdo.RepayPalan.domain.RepayPlan;
import com.bootdo.RepayRecord.domain.RepayRecord;
import com.bootdo.UserManage.domain.UserJBT;

/**
 * 销售机会Service
 * @author Administrator
 *
 */
public interface LoanCService {
	/**
	 * 用户列表查询
	 * @param map
	 * @return
	 */
	public List<UserJBT> findUserList(Map<String, Object> map);
	/**
	 * 查询用户记录数
	 * @param map
	 * @return
	 */
	public Long getTotalUserList(Map<String,Object> map);
	/**
	 * 还款计划列表查询
	 * @param map
	 * @return
	 */
	public List<RepayPlan> findRepayPlanList(Map<String, Object> map);
	/**
	 * 查询还款计划记录数
	 * @param map
	 * @return
	 */
	public Long getTotalRepayPlanList(Map<String,Object> map);
	/**
	 * 账户交易列表查询
	 * @param map
	 * @return
	 */
	public List<AccounTtrading> findAccounTtradingList(Map<String, Object> map);
	/**
	 * 查询账户交易记录数
	 * @param map
	 * @return
	 */
	public Long getTotalAccounTtradingList(Map<String,Object> map);
	/**
	 * 还款流水交易列表查询
	 * @param map
	 * @return
	 */
	public List<RepayFlow> findRepayFlowList(Map<String, Object> map);
	/**
	 * 还款流水交易记录数
	 * @param map
	 * @return
	 */
	public Long getTotalRepayFlowList(Map<String,Object> map);
	/**
	 * 逾期费用列表查询
	 * @param map
	 * @return
	 */
	public List<OverFlow> findRepayOverList(Map<String, Object> map);
	/**
	 * 逾期费用记录数
	 * @param map
	 * @return
	 */
	public Long getTotalRepayOverList(Map<String,Object> map);
	/**
	 * 还款记录列表查询
	 * @param map
	 * @return
	 */
	public List<RepayRecord> findRepayRecordList(Map<String, Object> map);
	/**
	 * 还款记录记录数
	 * @param map
	 * @return
	 */
	public Long getTotalRepayRecordList(Map<String, Object> map);
	/**
	 * 用户管理下载cz
	 * @param map
	 * @return
	 */
	public ArrayList<HashMap<String, Object>> findUserListN(Map<String,Object> map);
	/**
	 * 账户交易下载cz
	 * @param map
	 * @return
	 */
	public ArrayList<HashMap<String, Object>> findAccounTtradingListN(Map<String, Object> map);
	/**
	 * 还款计划列表查询
	 * @param map
	 * @return
	 */
	public ArrayList<HashMap<String, Object>> findRepayPlanListN(Map<String, Object> map);
	/**
	 * 还款记录查询
	 * @param map
	 * @return
	 */
	public List<HashMap<String,Object>> findRepayRecordListN(Map<String, Object> map);
}
