package com.bootdo.AfterLoanAccount.dao;

import com.bootdo.AfterLoanAccount.domain.FeeReduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FeeReductionDao {

    Integer addFeeReductionRecord(Map<String, Object> parm);

    Integer modifyRepayLastTimeByRepayPlanNo(Map<String, Object> parm);

    Map<String, Object> searchFeeReductionRecord(Map<String, Object> parm);

    List<FeeReduction> getFeeReductionList(Map<String, Object> parm);

    Integer getFeeReductionTotalPage(Map<String, Object> parm);

    Map<String,Object> getFeeRuctionDetail(String appNo);

    Integer modifyFeeReductionByappNo(Map<String, Object> param);
}
