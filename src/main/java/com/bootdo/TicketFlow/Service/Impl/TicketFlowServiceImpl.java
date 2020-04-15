package com.bootdo.TicketFlow.Service.Impl;

import com.bootdo.TicketFlow.Service.TicketFlowService;
import com.bootdo.TicketFlow.dao.TicketFlowDao;
import com.bootdo.TicketFlow.domain.Ticket;
import com.bootdo.TicketFlow.domain.TicketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class TicketFlowServiceImpl implements TicketFlowService {
    @Autowired
    private TicketFlowDao ticketFlowDao;

    @Override
    public List<TicketFlow> findTicketFlow(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return ticketFlowDao.findTicketFlow(map);
    }

    @Override
    public List<HashMap<String, Object>> findTicketFlowListMap(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return ticketFlowDao.findTicketFlowListMap(map);
    }

    @Override
    public Long findTicketFlowCount(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return ticketFlowDao.findTicketFlowCount(map);
    }

    @Override
    public List<Ticket> findTicket(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return ticketFlowDao.findTicket(map);
    }

    @Override
    public Long findTicketCount(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return ticketFlowDao.findTicketCount(map);
    }
}
