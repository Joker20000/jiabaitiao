package com.bootdo.Loan.service.impl;

import com.bootdo.Loan.dao.LoanDao;
import com.bootdo.Loan.domain.LoanEntity;
import com.bootdo.Loan.domain.LoanExcelEntity;
import com.bootdo.Loan.service.LoanService;
import com.bootdo.common.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanDao loanDao;
    @Autowired
    private DictService dictService;


    @Override
    public List<LoanEntity> list(Map<String, Object> map) {
        List<LoanEntity> loans = loanDao.list(map);
        /*for (LoanEntity loan : loans) {
            loan.setOrderState(dictService.getName("order_state", loan.getOrderState()));
            loan.setChackState(dictService.getName("chack_state", loan.getChackState()));
            loan.setOverState(dictService.getName("over_state", loan.getOverState()));
            loan.setRepayState(dictService.getName("repay_state", loan.getRepayState()));
        }*/
        return loans;
    }

    @Override
    public int count(Map<String, Object> map) {
        return loanDao.count(map);
    }


    @Override
    public int updateChackState(String loanNum,String state) {
        return loanDao.updateChackState(loanNum,state);
    }

    @Override
    public void updateLoanOrderState(String loanNum, String s, String s1, String s2, Date loanDate, Date repayDate, String chackId, String chackName) {
        loanDao.updateLoanOrderState(loanNum,s,s1,s2,loanDate,repayDate,chackId,chackName);
    }


    @Override
    public Map<String, String> getLoanOrderDesc(String loanNum) {
        return loanDao.getLoanOrderDesc(loanNum);
    }

    @Override
    public void updateLoanOrder(String loanNum, String orderState,Date nowDate, String chackId, String chackName) {
        loanDao.updateLoanOrder(loanNum,orderState,nowDate,chackId,chackName);
    }

    @Override
    public List<HashMap<String,Object>> queryLoanLists(Map<String, Object> map) {
        return loanDao.queryLoanLists(map);
    }


}
