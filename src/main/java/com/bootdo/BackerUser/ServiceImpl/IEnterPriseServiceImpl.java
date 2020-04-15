package com.bootdo.BackerUser.ServiceImpl;
import com.bootdo.BackerUser.Service.IEnterPriseService;
import com.bootdo.BackerUser.dao.IEnterPriseDao;
import com.bootdo.BackerUser.domain.BackExcel;
import com.bootdo.BackerUser.domain.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class IEnterPriseServiceImpl implements IEnterPriseService {
    @Autowired
    IEnterPriseDao IPD;

    /**
     * 按条件查询
     * */
    @Override
    public List<Enterprise> list(Map<String, Object> map) {
        System.out.println("*****************************************"+map);
        return IPD.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return IPD.count(map);
    }

    @Override
    public List<HashMap<String,Object>> BacExcel(Map<String, Object> map) {
        return IPD.BacExcel(map);
    }
}
