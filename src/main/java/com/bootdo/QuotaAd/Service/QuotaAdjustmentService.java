package com.bootdo.QuotaAd.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.Cdomain.domain.CreditOrder;
import com.bootdo.Cdomain.domain.TradingFlowEntity;
import com.bootdo.QuotaAd.domain.QuotaAdjustment;
import com.bootdo.QuotaAd.domain.QuotaRecord;
import org.apache.ibatis.annotations.Param;




public interface QuotaAdjustmentService {
	//根据条件查询所有
	List<QuotaAdjustment> findQuotaAdjustmentByCondition(Map<String,Object> map);

	//根据条件查询数据总条数
	Long findQuotaAdjustmentCountByCondition(Map<String,Object> map);


	//根据用户id查询用户基本信息、
	QuotaAdjustment getInfoByUserId(String userId);


	Integer isExistByPwdAndUname(@Param("pwd") String pwd,@Param("uname") String uname);

	Integer findIdByPwdAndUname(@Param("pwd") String pwd,@Param("uname") String uname);

	/**
	 * 添加交易流水记录
	 * @param tradingFlowEntity
	 * @return
	 */
	int insertFlow(TradingFlowEntity tradingFlowEntity);

	//根据用户id获得账号
	String getAccountIdByUserId(int userId);

	/*根据用户id获得账户id*/
	String getAccountNoByUserId(String userId);

	/*更新授信或者可用额度 creditLimit}usableLimit*/
	int updateGrantAccountInf(@Param("creditLimit") String creditLimit,@Param("usableLimit") String usableLimit,@Param("lastTime") String lastTime,@Param("accountNo") String accountNo);

	/*根据用户id获得授信额度和可用授信额度*/
	Map<String,String>  findUserInfoByUsertId(String userId);


	//添加授信订单
	void  addCreditOrder(CreditOrder creditOrder);


	//根据条件查询所有
	List<QuotaRecord> findQuotaRecordByCondition(Map<String,Object> map);
	//下载 根据条件查询
	List<HashMap<String, Object>> findQuotaRecordByConditionN(Map<String,Object> map);
	//根据条件查询数据总条数
	Long findQuotaRecordCountByCondition(Map<String,Object> map);
}



