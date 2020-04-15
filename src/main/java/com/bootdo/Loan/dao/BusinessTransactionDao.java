package com.bootdo.Loan.dao;

import com.bootdo.Loan.domain.TradingFlowEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;


/**
 * @Description: TODO
 * @author HWJ
 * @date 2017年12月29日
 * 业务交易接口
 */
public interface BusinessTransactionDao {

	/**
	 * 锁定账户, 并返回当前可用授信余额
	 *
	 * @param accountNo
	 * @return
	 */
	@Select("SELECT ifnull(CREDIT_LIMIT,0) AS creditLimit,ifnull(USABLE_LIMIT,0) AS usableLimit FROM TB_ACT_ACCOUNT WHERE ACCOUNT_NO = #{accountNo}")
	Map<String,Object> queryUsableLimitForLock(@Param("accountNo") String accountNo);


	/**
	 * 添加当前可用授信余额
	 *
	 * @param accountNo
	 * @param account
	 * @return
	 */
	@Update("UPDATE TB_ACT_ACCOUNT SET USABLE_LIMIT = (CAST(USABLE_LIMIT as DECIMAL(30,2)) + CAST(#{account} as DECIMAL(30,2))) WHERE CAST(CREDIT_LIMIT as DECIMAL(30,2)) >= (CAST(USABLE_LIMIT as DECIMAL(30,2)) + CAST(#{account} as DECIMAL(30,2))) AND ACCOUNT_NO = #{accountNo}")
	int addUsableLimit(@Param("accountNo") String accountNo, @Param("account") String account);

	/**
	 * 减少当前可用授信余额
	 *
	 * @param accountNo
	 * @param account
	 * @return
	 */
	@Update("UPDATE TB_ACT_ACCOUNT SET USABLE_LIMIT = (CAST(USABLE_LIMIT as DECIMAL(30,2)) - CAST(#{account} as DECIMAL(30,2))) WHERE (CAST(USABLE_LIMIT as DECIMAL(30,2)) - CAST(#{account} as DECIMAL(30,2))) >= 0 AND ACCOUNT_NO = #{accountNo}")
	int subtractUsableLimit(@Param("accountNo") String accountNo, @Param("account") String account);

	/**
	 * 添加交易流水记录
	 * @param tradingFlowEntity
	 * @return
	 */
	int insertFlow(TradingFlowEntity tradingFlowEntity);

}
