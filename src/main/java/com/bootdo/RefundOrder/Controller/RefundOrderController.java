package com.bootdo.RefundOrder.Controller;

import com.bootdo.AccounTtrading.domain.AccounTtrading;
import com.bootdo.RefundOrder.Service.RefundOrderService;
import com.bootdo.RefundOrder.domain.RefundOrderEntity;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/refundOrderController")
public class RefundOrderController {
    @Autowired
    private RefundOrderService  refundOrderService;

    @GetMapping("/refund")
    String refund() {
        return "RefundManage/RefundQuery";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:���������ֵ��false:����Ϊ��ֵ
    }
    //退款查询
    @ResponseBody
    @RequestMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {

        if(!"".equals(params.get("refundOrderNo")) && params.get("refundOrderNo") != null){
            params.put("refundOrderNo",StringUtil.formatLike(String.valueOf(params.get("refundOrderNo"))));
        }
        if(!"".equals(params.get("loanOrderNo")) && params.get("loanOrderNo") != null){
            params.put("loanOrderNo",StringUtil.formatLike(String.valueOf(params.get("loanOrderNo"))));
        }
        if(!"".equals(params.get("extRefOrderId")) && params.get("extRefOrderId") != null){
            params.put("extRefOrderId",StringUtil.formatLike(String.valueOf(params.get("extRefOrderId"))));
        }
        if (!"".equals(params.get("createTimeStart")) && params.get("createTimeStart") != null) {
            params.put("createTimeStart", String.valueOf(params.get("createTimeStart")));
        }
        if (!"".equals(params.get("createTimeEnd")) && params.get("createTimeEnd") != null) {
            params.put("createTimeEnd", String.valueOf(params.get("createTimeEnd")));
        }

        Query query = new Query(params);
        List<RefundOrderEntity> saleChanceList= refundOrderService.findRefundOrderList(query);
        int total= refundOrderService.countRefundOrder(query);
        PageUtils pageUtils = new PageUtils(saleChanceList, total);
        return pageUtils;
    }

}
