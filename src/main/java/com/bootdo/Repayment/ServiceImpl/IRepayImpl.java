package com.bootdo.Repayment.ServiceImpl;
import com.bootdo.Repayment.Service.IRepayService;
import com.bootdo.Repayment.dao.IRepayDao;
import com.bootdo.Repayment.domain.RepExcel;
import com.bootdo.Repayment.domain.Repay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IRepayImpl implements IRepayService {
    @Autowired
    IRepayDao IPD;

    /**
     * 按条件查询
     * */
    @Override
    public List<Repay> list(Map<String, Object> map) {
        System.out.println("*****************************************"+map);
        return IPD.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return IPD.count(map);
    }

    @Override
    public List<HashMap<String,Object>> ReExcel(Map<String, Object> map) {
        return IPD.ReExcel(map);
    }
}
