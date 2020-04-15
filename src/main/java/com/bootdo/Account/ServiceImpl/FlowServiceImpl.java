package com.bootdo.Account.ServiceImpl;

import com.bootdo.Account.Service.FlowService;
import com.bootdo.Account.dao.IFlowDao;
import com.bootdo.Account.domain.Accounter;
import com.bootdo.Account.domain.FlowExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlowServiceImpl implements FlowService {
    @Autowired
    IFlowDao iFlowDao;
    @Override
    public List<Accounter> list(Map<String, Object> map) {
        System.out.println("*****************************************"+map);
        return iFlowDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return iFlowDao.count(map);
    }

    @Override
    public List<HashMap<String,Object>> FloExcel(Map<String, Object> map) {
        return iFlowDao.FloExcel(map);
    }
}
