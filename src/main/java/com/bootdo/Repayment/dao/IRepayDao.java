package com.bootdo.Repayment.dao;

import com.bootdo.Repayment.domain.RepExcel;
import com.bootdo.Repayment.domain.Repay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface IRepayDao {
    /**
     * lzp查询信息
     * @param
     * @return
     * cz
     */
    List<Repay> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    List<HashMap<String,Object>> ReExcel(Map<String,Object> map);

}
