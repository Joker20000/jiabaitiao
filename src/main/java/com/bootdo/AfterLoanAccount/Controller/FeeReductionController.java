package com.bootdo.AfterLoanAccount.Controller;

import com.alibaba.fastjson.JSON;
import com.bootdo.AfterLoanAccount.Service.AfterLoanAccountService;
import com.bootdo.AfterLoanAccount.Service.FeeReductionService;
import com.bootdo.AfterLoanAccount.domain.FeeReduction;
import com.bootdo.Utils.BigDecimalUtil;
import com.bootdo.Utils.DateUtil;
import com.bootdo.Utils.IdWorker;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/feeReductionController/feeReduction")
public class FeeReductionController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(FeeReductionController.class);

    @Autowired
    private FeeReductionService frService;

    @Autowired
    private AfterLoanAccountService afterLoanAccountService;

    /**
     * 新增费用减免记录
     * @param map
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addRepayLastDayAndOverdue", method = RequestMethod.POST)
    public String addRepayLastDayAndOverdue(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        Map<String, Object> back = new HashMap<>();
        try {
            logger.info("addRepayLastDayAndOverdue 费用减免参数[{}]", map.toString());
            Map<String, Object> param = new HashMap<>();
            map.put("state", "0");//审核中
            Map<String, Object> frMap = frService.searchFeeReductionRecord(map);//查询费用减免记录中是否存在该笔审核中得
            if (null != frMap) {//存在审核中减免记录，返回出去不进行新增
                back.put("statu", "100");
                return JSON.toJSONString(back);
            }
            IdWorker idWorker = new IdWorker(0, 0);
            Long id = idWorker.nextId();
            String subId = id + "";
            String date = DateUtil.formatDate(new Date());
            param.put("appNo", date + subId.substring(subId.length() - 8, subId.length()));//申请编号
            param.put("repayPlanNo", map.get("repayPlanNo"));//还款计划编号
            param.put("originalTime", map.get("lastPayDay"));//原还款日
            param.put("makeTime", map.get("latestTimeEnd"));//约定还款日
            param.put("originalOverdue", map.get("overdueFee"));//（原逾期费用-已还逾期费）
            param.put("waivarAmt", map.get("appmoney"));//减免逾期费用
            param.put("applicant", getUser().getName());//申请人
            param.put("applicantId", getUserId());//申请人ID
            param.put("reason", map.get("reson"));//减免原因
            param.put("approvalState", "0");//审批状态

            String afterFee = BigDecimalUtil.BigDecimalSubtract(map.get("nowoverdueFee") + "", map.get("appmoney") + "");//减免后费用 = 现逾期费用 - 申请减免费用
            param.put("afterFee", afterFee);//当期逾期费用、减免后费用
            frService.addFeeReductionRecord(param);//新增减免记录
            back.put("statu", "200");
            return JSON.toJSONString(back);
        } catch (Exception ex) {
            logger.error("addRepayLastDayAndOverdue Error:", ex);
            back.put("statu", "500");
            return JSON.toJSONString(back);
        }
    }

    /**
     * 跳转到减免记录列表
     * @return
     */
    @RequestMapping(value = "/toFeeReductionList")
    public String toFeeReductionList() {
        return "AfterLoanAccount/FeeReductionList";
    }

    /**
     * 费用减免记录列表
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFeeReductionList")
    public PageUtils getFeeReductionList(@RequestParam Map<String, Object> param) {

        Query query = new Query(param);
        List<FeeReduction> feeReduList = frService.getFeeReductionList(query);
        Integer totalPage = frService.getFeeReductionTotalPage(query);
        PageUtils pageUtils = new PageUtils(feeReduList, totalPage);
        return pageUtils;
    }


    /**
     * 费用减免记录查看详情，还款号、费用减免号
     * @param requst
     * @param model
     * @return
     */
    @RequiresPermissions("loan:loan:check")
    @RequestMapping("/getFeeRuctionDetail")
    public String getFeeRuctionDetail(HttpServletRequest requst, Model model) {
        String rank = "";
        String appNo = requst.getParameter("appNo");
        String rePlanNo = requst.getParameter("repayPlanNo");
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> reliefMap = new HashMap<>();
        if (StringUtils.isBlank(appNo) && StringUtils.isBlank(rePlanNo)) {//账户号、还款订单号


        } else {
            List<Map<String, String>> listMap = afterLoanAccountService.getList(requst.getParameter("accountNo"));
            for (int i = 0; i < listMap.size(); i++) {
                if (rePlanNo.equals(listMap.get(i).get("repayPlanNo"))) {
                    DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
                    rank = decimalFormat.format(listMap.get(i).get("ranks"));
                    break;
                }
            }
            map = afterLoanAccountService.searchUserRemaRepayByNo(requst.getParameter("accountNo"), rePlanNo);
            reliefMap = frService.getFeeRuctionDetail(appNo);
            map.put("rank", rank);
            map.put("repayPlanNo", requst.getParameter("repayaccountNo"));
            map.put("init", requst.getParameter("init"));
        }
        model.addAttribute("data", map);//title
        model.addAttribute("repayPlanNo", rePlanNo);
        model.addAttribute("shouldPay", reliefMap);//当期应还数据
        return "AfterLoanAccount/FeeReduction";
    }

    /**
     * 减免记录审核
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modifyRepayPlanByNo", method = RequestMethod.POST)
    public String modifyRepayPlanByNo(@RequestBody Map<String, Object> param) {
        Map<String, Object> back = new HashMap<>();
        try {
            if ("1".equals(param.get("state"))) {//审核通过
                String afterFee = BigDecimalUtil.BigDecimalSubtract(String.valueOf(param.get("nowoverdueFee")),String.valueOf(param.get("appmoney")));
                param.put("afterFee",afterFee);
                frService.modifyRepayLastTimeByRepayPlanNo(param);//更新还款日、当前逾期费=（原逾期费用-减免逾期费）
            }
            param.put("checkUserName", getUser().getName());
            param.put("checkUserId", getUserId());
            frService.modifyFeeReductionByappNo(param);//审核通过、拒绝都修改该笔减免记录得审核状态
            back.put("statu", "200");
            return JSON.toJSONString(back);
        } catch (Exception ex) {
            logger.error("modifyRepayPlanByNo ERROR：", ex);
            back.put("statu", "500");
            return JSON.toJSONString(back);
        }

    }

}
