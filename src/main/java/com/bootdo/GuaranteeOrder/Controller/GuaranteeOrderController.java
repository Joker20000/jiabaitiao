package com.bootdo.GuaranteeOrder.Controller;

import com.bootdo.GuaranteeOrder.Service.GuaranteeOrderService;
import com.bootdo.GuaranteeOrder.domain.GuaranteeOrderEntity;
import com.bootdo.RefundOrder.domain.RefundOrderEntity;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
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
@RequestMapping("/guaranteeOrderController")
public class GuaranteeOrderController {
    @Autowired
    private GuaranteeOrderService guaranteeOrderService;

    @GetMapping("/guarantee")
    String refund() {
        return "GuaranteeManage/GuaranteeQuery";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:���������ֵ��false:����Ϊ��ֵ
    }
    //担保订单查询
    @ResponseBody
    @RequestMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {

        if(!"".equals(params.get("guaranteeOrderId")) && params.get("guaranteeOrderId") != null){
            params.put("guaranteeOrderId",StringUtil.formatLike(String.valueOf(params.get("guaranteeOrderId"))));
        }
        if(!"".equals(params.get("companyName")) && params.get("companyName") != null){
            params.put("companyName",StringUtil.formatLike(String.valueOf(params.get("companyName"))));
        }
        if(!"".equals(params.get("realName")) && params.get("realName") != null){
            params.put("realName",StringUtil.formatLike(String.valueOf(params.get("realName"))));
        }
        if(!"".equals(params.get("mobile")) && params.get("mobile") != null){
            params.put("mobile",StringUtil.formatLike(String.valueOf(params.get("mobile"))));
        }
        if(!"".equals(params.get("repayState")) && params.get("repayState") != null){
            params.put("repayState",String.valueOf(params.get("repayState")));
        }
        if (!"".equals(params.get("createTimeStart")) && params.get("createTimeStart") != null) {
            params.put("createTimeStart", String.valueOf(params.get("createTimeStart")));
        }
        if (!"".equals(params.get("createTimeEnd")) && params.get("createTimeEnd") != null) {
            params.put("createTimeEnd", String.valueOf(params.get("createTimeEnd")));
        }
        if (!"".equals(params.get("repayTimeStart")) && params.get("repayTimeStart") != null) {
            params.put("repayTimeStart", String.valueOf(params.get("repayTimeStart")));
        }
        if (!"".equals(params.get("repayTimeEnd")) && params.get("repayTimeEnd") != null) {
            params.put("repayTimeEnd", String.valueOf(params.get("repayTimeEnd")));
        }
        Query query = new Query(params);
        List<GuaranteeOrderEntity> guaranteeOrderEntities= guaranteeOrderService.findGuaranteeOrderList(query);
        int total= guaranteeOrderService.countGuaranteeOrder(query);
        PageUtils pageUtils = new PageUtils(guaranteeOrderEntities, total);
        return pageUtils;
    }
}
