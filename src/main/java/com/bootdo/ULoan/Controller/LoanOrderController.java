package com.bootdo.ULoan.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bootdo.BlackList.domain.Blacklist;
import com.bootdo.Cdomain.domain.OverdueFlow;
import com.bootdo.RepayPalan.domain.RepayPlan;
import com.bootdo.ULoan.Service.LoanOrderService;
import com.bootdo.ULoan.domain.ConsumeOrder;
import com.bootdo.ULoan.domain.LoanOrder;
import com.bootdo.UserManage.Controller.DateJsonValueProcessor;
import com.bootdo.Utils.PageBean;
import com.bootdo.Utils.ResponseUtil;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.oa.domain.NotifyDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 订单管理Controller类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/loanOrderController")
public class LoanOrderController {
	
	@Autowired
	private LoanOrderService loanOrderService;
	@GetMapping("/trading")
	String trading() {
		return "LoanOrderManage/LoanOrderManage";
	}
	//查询订单集合
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		if(!"".equals(params.get("realName")) && params.get("realName") != null){
			params.put("realName",StringUtil.formatLike(String.valueOf(params.get("realName"))));
		}
		if(!"".equals(params.get("email")) && params.get("email") != null){
			params.put("email",StringUtil.formatLike(String.valueOf(params.get("email"))));
		}
		if(!"".equals(params.get("loanOrderNo")) && params.get("loanOrderNo") != null){
			params.put("loanOrderNo",StringUtil.formatLike(String.valueOf(params.get("loanOrderNo"))));
		}
		if(!"".equals(params.get("mobile")) && params.get("mobile") != null){
			params.put("mobile",StringUtil.formatLike(String.valueOf(params.get("mobile"))));
		}
		Query query = new Query(params);
		List<LoanOrder> loanOrderList=loanOrderService.findLoanOrderByCondition(query);
		//List<LoanOrder> repayPlans = loanOrderService.findLoanOrderAndLoanPepayPlan(query);
		/*for (int i=0;i < repayPlans.size(); i++){
			loanOrderList.get(i).setNowAmountSum(repayPlans.get(i).getNowAmountSum());
			loanOrderList.get(i).setNowInterestSum(repayPlans.get(i).getNowInterestSum());
			loanOrderList.get(i).setNowOrderInterestSum(repayPlans.get(i).getNowOrderInterestSum());
			loanOrderList.get(i).setReadyAmountSum(repayPlans.get(i).getReadyAmountSum());
			loanOrderList.get(i).setRepaymentAmountSum(repayPlans.get(i).getRepaymentAmountSum());
			loanOrderList.get(i).setNowOverdueSum(repayPlans.get(i).getNowOverdueSum());
			loanOrderList.get(i).setPeriodRate(repayPlans.get(i).getPeriodRate());
			loanOrderList.get(i).setTransferCost(repayPlans.get(i).getTransferCost());
			loanOrderList.get(i).setTransferRate(repayPlans.get(i).getTransferRate());
			loanOrderList.get(i).setOverdueManageRate(repayPlans.get(i).getOverdueManageRate());
			loanOrderList.get(i).setOverdueDayRate(repayPlans.get(i).getOverdueDayRate());
			loanOrderList.get(i).setLatestTime(repayPlans.get(i).getLatestTime());
		}*/
		Long total1=loanOrderService.findLoanOrderCountByCondition(query);
		int total= new Long(total1).intValue();
		PageUtils pageUtils = new PageUtils(loanOrderList, total);
		return pageUtils;
	}

	//通过借款编号获得所有还款计划编号下的逾期费用流水
	//逾期费用
	@RequestMapping("/getOverDueCostByLoanOrderNo")
		public String getOverDueCostByLoanOrderNo(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,String loanOrderNo,HttpServletRequest request ,HttpServletResponse response)throws Exception{
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("loanOrderNo",StringUtil.formatLike(loanOrderNo));
		    System.out.println("借款订单号为"+loanOrderNo);
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
			List<OverdueFlow> OverdueFlowList=loanOrderService.getOverDueFlowByRepayPlanNo(map);
			Long total=loanOrderService.getOverDueFlowByRepayPlanNoCount(map);
			JSONObject result=new JSONObject();
			JsonConfig jsonConfig=new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm"));
			JSONArray jsonArray=JSONArray.fromObject(OverdueFlowList,jsonConfig);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
			return null;
		};

	@GetMapping("/getConsumeDetail/{id}")
	@RequiresPermissions("LoanOrderManage:ConsumeDetail")
	String getConsumeDetail(@PathVariable("id") String id, Model model) {
		ConsumeOrder consumeOrder = loanOrderService.getConsumeDetail(id);
		model.addAttribute("consumeOrder",consumeOrder);
		return "LoanOrderManage/ConsumeDetail";
	}

}
