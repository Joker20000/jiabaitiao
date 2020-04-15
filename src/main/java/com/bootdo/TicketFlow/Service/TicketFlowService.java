package com.bootdo.TicketFlow.Service;

import com.bootdo.TicketFlow.domain.TicketFlow;
import com.bootdo.TicketFlow.domain.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 券查询Service
 * @author Administrator
 *
 */
public interface TicketFlowService {

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
