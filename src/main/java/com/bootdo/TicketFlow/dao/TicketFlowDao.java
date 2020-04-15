package com.bootdo.TicketFlow.dao;

import com.bootdo.TicketFlow.domain.Ticket;
import com.bootdo.TicketFlow.domain.TicketFlow;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface TicketFlowDao {
    /**
     * 查询
     * @param map
     * @return
     */
    public List<TicketFlow> findTicketFlow(Map<String,Object> map);

    /**
     * 查询返回list<Map>集合
     * @param map
     * @return
     */
    public List<HashMap<String, Object>> findTicketFlowListMap(Map<String,Object> map);
    /**
     * 查询记录数
     * @param map
     * @return
     */
    public Long findTicketFlowCount(Map<String,Object> map);
    /**
     * 查询券详情
     * @param map
     * @return
     */

    public List<Ticket> findTicket(Map<String,Object> map);
    /**
     * 查询券详情记录数
     * @param map
     * @return
     */

    public Long findTicketCount(Map<String,Object> map);
}
