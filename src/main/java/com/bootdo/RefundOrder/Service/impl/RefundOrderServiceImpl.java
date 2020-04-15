package com.bootdo.RefundOrder.Service.impl;

import com.bootdo.RefundOrder.Service.RefundOrderService;
import com.bootdo.RefundOrder.dao.RefundOrderDao;
import com.bootdo.RefundOrder.domain.RefundOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RefundOrderServiceImpl implements RefundOrderService {

    @Autowired
    private RefundOrderDao  refundOrderDao;

    @Override
    public List<RefundOrderEntity> findRefundOrderList(Map<String, Object> map) {
        return refundOrderDao.findRefundOrderList(map);
    }

    @Override
    public int countRefundOrder(Map<String, Object> map) {
        return refundOrderDao.countRefundOrder(map);
    }

    @Override
    public ArrayList<HashMap<String, Object>> findRefundOrderListN(Map<String, Object> map) {
        return refundOrderDao.findRefundOrderListN(map);
    }
}
