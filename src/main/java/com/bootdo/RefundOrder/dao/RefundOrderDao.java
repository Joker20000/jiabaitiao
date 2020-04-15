package com.bootdo.RefundOrder.dao;

import com.bootdo.RefundOrder.domain.RefundOrderEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface RefundOrderDao {

    public List<RefundOrderEntity> findRefundOrderList(Map<String, Object> map);

    int countRefundOrder(Map<String, Object> map);

    public ArrayList<HashMap<String, Object>> findRefundOrderListN(Map<String, Object> map);

}