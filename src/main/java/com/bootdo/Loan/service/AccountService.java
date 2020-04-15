package com.bootdo.Loan.service;


import com.bootdo.Loan.domain.AmountEntity;

/**
 * @Description: TODO
 * @author HWJ
 * @date 2018年4月25日
 * 开户接口
 */
public interface AccountService {

    /**
     * 开户
     *
     * @param eid   企业ID
     * @return
     */
    boolean open(String eid);

    /**
     * 账户业务交易
     *
     * @param amount	账户业务交易
     * @return
     */
    boolean changeAmount(AmountEntity amount);

}
