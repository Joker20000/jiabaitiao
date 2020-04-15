package com.bootdo.GuaranteeOrder.dao;

import com.bootdo.GuaranteeOrder.domain.GuaranteeOrderEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GuaranteeOrderDao {

    public List<GuaranteeOrderEntity> findGuaranteeOrderList(Map<String, Object> map);

    int countGuaranteeOrder(Map<String, Object> map);

    public ArrayList<HashMap<String, Object>> findGuaranteeOrderListN(Map<String, Object> map);
}
