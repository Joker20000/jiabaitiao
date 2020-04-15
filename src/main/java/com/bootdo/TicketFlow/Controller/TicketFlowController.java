package com.bootdo.TicketFlow.Controller;

import com.bootdo.TicketFlow.Service.TicketFlowService;
import com.bootdo.TicketFlow.domain.Ticket;
import com.bootdo.TicketFlow.domain.TicketFlow;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 券交易查询Controller类
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/ticketFlowServiceController")
public class TicketFlowController {
    @Resource
    private TicketFlowService ticketFlowService;

    @GetMapping("/ticket")
    String ticket() {
        return "ticketFlow/ticketFlow";
    }


    @GetMapping("/ticketflowlist")
    ModelAndView repayplanlist(@RequestParam("ticketNo")String ticketNo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ticketNo",ticketNo);
        modelAndView.setViewName("ticketFlow/ticketflowlist");
        return modelAndView;
    }


    /**
     * 券交易查询
     *
     * @param params
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) throws Exception {

        if(!"".equals(params.get("btOrderNo")) && params.get("btOrderNo") != null){
            params.put("btOrderNo",StringUtil.formatLike(String.valueOf(params.get("btOrderNo"))));
        }
        if(!"".equals(params.get("ticketTransactionFlow")) && params.get("ticketTransactionFlow") != null){
            params.put("ticketTransactionFlow",StringUtil.formatLike(String.valueOf(params.get("ticketTransactionFlow"))));
        }
        if(!"".equals(params.get("ticketNo")) && params.get("ticketNo") != null){
            params.put("ticketNo",StringUtil.formatLike(String.valueOf(params.get("ticketNo"))));
        }
        if(!"".equals(params.get("ticketFlowType")) && params.get("ticketFlowType") != null){
            params.put("ticketFlowType",String.valueOf(params.get("ticketFlowType")));
        }
        if(!"".equals(params.get("ticketTransactionState")) && params.get("ticketTransactionState") != null){
            params.put("ticketTransactionState",String.valueOf(params.get("ticketTransactionState")));
        }
        if(!"".equals(params.get("finishTimeStart")) && params.get("finishTimeStart") != null){
            params.put("finishTimeStart",String.valueOf(params.get("finishTimeStart")));
        }
        if(!"".equals(params.get("finishTimeEnd")) && params.get("finishTimeEnd") != null){
            params.put("finishTimeEnd",String.valueOf(params.get("finishTimeEnd")));
        }
       /* params.put("limit",10);
        params.put("offset",0);*/
        Query query = new Query(params);
        List<TicketFlow> ticketFlowList = ticketFlowService.findTicketFlow(query);
        Long total1 = ticketFlowService.findTicketFlowCount(query);
        int total= new Long(total1).intValue();
        PageUtils pageUtils = new PageUtils(ticketFlowList, total);
        return pageUtils;
    }


    /**
     * 券详情列表
     *
     * @param params
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/TicketList")
    public PageUtils TicketList(@RequestParam Map<String, Object> params) throws Exception {
        Query query = new Query(params);
        List<Ticket> TicketList = ticketFlowService.findTicket(query);
        Long total1 = ticketFlowService.findTicketCount(query);
        int total=0;
        if (total1!=null){
            total = new Long(total1).intValue();
        }

        PageUtils pageUtils = new PageUtils(TicketList, total);
        return pageUtils;
    }
}

