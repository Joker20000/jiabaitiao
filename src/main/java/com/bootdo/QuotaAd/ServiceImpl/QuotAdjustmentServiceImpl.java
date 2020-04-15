package com.bootdo.QuotaAd.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.Cdomain.domain.CreditOrder;
import com.bootdo.Cdomain.domain.TradingFlowEntity;
import com.bootdo.QuotaAd.Service.QuotaAdjustmentService;
import com.bootdo.QuotaAd.dao.QuotaDao;
import com.bootdo.QuotaAd.domain.QuotaAdjustment;
import com.bootdo.QuotaAd.domain.QuotaRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("QuotaAdjustmentService")
public class QuotAdjustmentServiceImpl  implements QuotaAdjustmentService {
	@Autowired
	private QuotaDao quotaDao;

	@Override
	public List<QuotaAdjustment> findQuotaAdjustmentByCondition(Map<String, Object> map) {
		return quotaDao.findQuotaAdjustmentByCondition(map);
	}

	@Override
	public Long findQuotaAdjustmentCountByCondition(Map<String, Object> map) {
		return quotaDao.findQuotaAdjustmentCountByCondition(map);
	}

	@Override
	public QuotaAdjustment getInfoByUserId(String userId) {
		return quotaDao.getInfoByUserId(userId);
	}

	@Override
	public Integer isExistByPwdAndUname(String pwd, String uname) {
		return quotaDao.isExistByPwdAndUname(pwd, uname);
	}

	@Override
	public int insertFlow(TradingFlowEntity tradingFlowEntity) {
		return quotaDao.insertFlow(tradingFlowEntity);
	}

	@Override
	public String getAccountNoByUserId(String userId) {
		return quotaDao.getAccountNoByUserId(userId);
	}

	@Override
	public int updateGrantAccountInf(String creditLimit, String usableLimit, String lastTime, String accountNo) {
		return quotaDao.updateGrantAccountInf(creditLimit, usableLimit, lastTime, accountNo);
	}

	@Override
	public Map<String, String> findUserInfoByUsertId(String userId) {
		return quotaDao.findUserInfoByUsertId(userId);
	}

	@Override
	public String getAccountIdByUserId(int userId) {
		return quotaDao.getAccountIdByUserId(userId);
	}

	@Override
	public void addCreditOrder(CreditOrder creditOrder) {
		quotaDao.addCreditOrder(creditOrder);
	}

	@Override
	public List<QuotaRecord> findQuotaRecordByCondition(Map<String, Object> map) {
		return quotaDao.findQuotaRecordByCondition(map);
	}

	@Override
	public List<HashMap<String, Object>> findQuotaRecordByConditionN(Map<String, Object> map) {
		return quotaDao.findQuotaRecordByConditionN(map);
	}

	@Override
	public Long findQuotaRecordCountByCondition(Map<String, Object> map) {
		return quotaDao.findQuotaRecordCountByCondition(map);
	}

	@Override
	public Integer findIdByPwdAndUname(String pwd, String uname) {
		return quotaDao.findIdByPwdAndUname(pwd, uname);
	}



}
