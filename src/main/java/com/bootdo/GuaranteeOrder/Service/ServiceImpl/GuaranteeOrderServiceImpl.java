package com.bootdo.GuaranteeOrder.Service.ServiceImpl;

import com.bootdo.GuaranteeOrder.Service.GuaranteeOrderService;
import com.bootdo.GuaranteeOrder.dao.GuaranteeOrderDao;
import com.bootdo.GuaranteeOrder.domain.GuaranteeOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class GuaranteeOrderServiceImpl implements GuaranteeOrderService {
    @Autowired
    private GuaranteeOrderDao  guaranteeOrderDao;

    @Override
    public List<GuaranteeOrderEntity> findGuaranteeOrderList(Map<String, Object> map) {
        return guaranteeOrderDao.findGuaranteeOrderList(map);
    }

    @Override
    public int countGuaranteeOrder(Map<String, Object> map) {
        return guaranteeOrderDao.countGuaranteeOrder(map);
    }

    @Override
    public ArrayList<HashMap<String, Object>> findGuaranteeOrderListN(Map<String, Object> map) {
        return guaranteeOrderDao.findGuaranteeOrderListN(map);
    }
}