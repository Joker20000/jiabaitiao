package com.bootdo.BackerUser.dao;

import com.bootdo.BackerUser.domain.BackExcel;
import com.bootdo.BackerUser.domain.Enterprise;
import com.bootdo.Loan.domain.LoanExcelEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IEnterPriseDao {
    /**
     * lzp查询信息
     * @param
     * @return
     * cz
     */
    List<Enterprise> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    /**
     * 下载
     * @param map
     * @return
     */
    List<HashMap<String,Object>> BacExcel(Map<String,Object> map);


}
