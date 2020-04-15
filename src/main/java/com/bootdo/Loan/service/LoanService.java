package com.bootdo.Loan.service;

import com.bootdo.Loan.domain.LoanEntity;
import com.bootdo.Loan.domain.LoanExcelEntity;

import java.util.*;

/**
 * 通知通告
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-05 17:11:16
 */
public interface LoanService {

	List<LoanEntity> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int updateChackState(String loanNum,String state);

    void updateLoanOrderState(String loanNum, String s, String s1, String s2, Date loanDate, Date repayDate, String chackId, String chackName);

    Map<String,String> getLoanOrderDesc(String loanNum);

	void updateLoanOrder(String loanNum, String orderState, Date nowDate, String chackId, String chackName);

	List<HashMap<String,Object>> queryLoanLists(Map<String,Object> map);
}
