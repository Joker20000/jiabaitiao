package com.bootdo.AfterLoanAccount.ServiceImpl;

import com.bootdo.AfterLoanAccount.Service.FeeReductionService;
import com.bootdo.AfterLoanAccount.dao.FeeReductionDao;
import com.bootdo.AfterLoanAccount.domain.FeeReduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FeeReductionServiceImpl implements FeeReductionService {

    @Autowired
    private FeeReductionDao frDao;

    @Override
    public Integer addFeeReductionRecord(Map<String, Object> parm) {
        return frDao.addFeeReductionRecord(parm);
    }

    @Override
    public Map<String, Object> searchFeeReductionRecord(Map<String, Object> parm) {
        return frDao.searchFeeReductionRecord(parm);
    }

    @Override
    public Integer modifyRepayLastTimeByRepayPlanNo(Map<String, Object> parm) {
        return frDao.modifyRepayLastTimeByRepayPlanNo(parm);
    }

    @Override
    public Integer modifyFeeReductionByappNo(Map<String, Object> param) {
        return frDao.modifyFeeReductionByappNo(param);
    }

    @Override
    public List<FeeReduction> getFeeReductionList(Map<String, Object> param) {
        return frDao.getFeeReductionList(param);
    }

    @Override
    public Integer getFeeReductionTotalPage(Map<String, Object> param) {
        return frDao.getFeeReductionTotalPage(param);
    }

    @Override
    public Map<String, Object> getFeeRuctionDetail(String appNo) {
        return frDao.getFeeRuctionDetail(appNo);
    }
}
