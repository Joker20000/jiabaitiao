package com.bootdo.AfterLoanAccount.ServiceImpl;
import com.bootdo.AfterLoanAccount.Service.AfterLoanAccountService;
import com.bootdo.AfterLoanAccount.dao.AfterLoanAccountDao;
import com.bootdo.AfterLoanAccount.domain.SmsLoanOrder;
import com.bootdo.RepayPalan.domain.RepayPlan;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AfterLoanAccountServiceImpl implements AfterLoanAccountService {
    @Autowired
    AfterLoanAccountDao afa;


    @Override
    public List<RepayPlan> findAfterLoanAccountList(Map<String, Object> map) {
        return afa.findAfterLoanAccountList(map);
    }

    @Override
    public Long getTotaAfterLoanAccountList(Map<String, Object> map) {
        return afa.getTotaAfterLoanAccountList(map);
    }

    @Override
    public List<Map<String,String>> getList(String acccountNo) {
        return afa.getList(acccountNo);
    }

    @Override
    public List<Map<String, String>> getMessageList() {
        return afa.getMessageList();
    }

    @Override
    public String getMobile(String acccountNo) {
        return afa.getMobile(acccountNo);
    }

    @Override
    public Map<String, Object> searchUserRemaRepayByNo(String acccountNo, String repayPlanNo){
        return afa.searchUserRemaRepayByNo(acccountNo, repayPlanNo);
    }

    @Override
    public Map<String, Object> searchUserNowFeeByRepayNo(String repayPlanNo) {
        return afa.searchUserNowFeeByRepayNo(repayPlanNo);
    }

    @Override
    public int SmsLoanOrder(SmsLoanOrder smsLoanOrder) {
        return afa.insertSmsLoanOrder(smsLoanOrder);
    }

    @Override
    public List<SmsLoanOrder> findAfterSmsLoanOrder(Map<String, Object> map) {
        return afa.findAfterSmsLoanOrder(map);
    }

    @Override
    public Long getTotaAfterSmsLoanOrder(Map<String, Object> map) {
        return afa.getTotaAfterSmsLoanOrder(map);
    }

    @Override
    public void saveLoanInfoRecord(Long userId, String userName, String contents, String accountNo) {
        afa.saveLoanInfoRecord(userId,userName,contents,accountNo);
    }

    @Override
    public List<Map<String, String>> getLoanAfterInfo(String accountNo) {
        return afa.getLoanAfterInfo(accountNo);
    }
}
