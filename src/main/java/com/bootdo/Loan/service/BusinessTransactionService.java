package com.bootdo.Loan.service;


import com.bootdo.Loan.domain.AmountsEntity;

/**
 * @Description: TODO
 * @author HWJ
 * @date 2017年12月29日
 * 业务交易接口
 */
public interface BusinessTransactionService {
	/**
	 * 业务交易
	 *
	 * @param amount	业务参数
	 * @return
	 */
	boolean changeAmount(AmountsEntity amount);
}
