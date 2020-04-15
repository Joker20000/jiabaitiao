package com.bootdo.RefundOrder.Service;

import com.bootdo.AccounTtrading.domain.AccounTtrading;
import com.bootdo.RefundOrder.domain.RefundOrderEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RefundOrderService {

    public List<RefundOrderEntity> findRefundOrderList(Map<String, Object> map);

    int countRefundOrder(Map<String, Object> map);

    public ArrayList<HashMap<String, Object>> findRefundOrderListN(Map<String, Object> map);
}
