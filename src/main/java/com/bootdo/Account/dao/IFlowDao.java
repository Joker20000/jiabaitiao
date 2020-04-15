package com.bootdo.Account.dao;

import com.bootdo.Account.domain.Accounter;
import com.bootdo.Account.domain.FlowExcel;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Mapper
public interface IFlowDao {
    List<Accounter> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    List<HashMap<String,Object>> FloExcel(Map<String,Object> map);
}
