package com.bootdo.RepayPalan.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bootdo.Cdomain.domain.OverFlow;
import com.bootdo.RepayFlow.domain.RepayFlow;
import com.bootdo.RepayPalan.domain.RepayPlan;
import com.bootdo.ULoan.Service.LoanCService;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * 还款计划管理Controller类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/repayPlanManageController")
public class RepayPlanManageController {

	@Resource
	private LoanCService loanCService;
	@GetMapping("/repayplan")
	String repayplan() {
		return "PepayPlanManage/RepayPlanManage";
	}
	@GetMapping("/repayplanlist")
	ModelAndView repayplanlist(@RequestParam("repayPlanNo")String repayPlanNo) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("repayPlanNo",repayPlanNo);
		modelAndView.setViewName("PepayPlanManage/repayplanlist");
		return modelAndView;
	}
	@GetMapping("/overdue")
	ModelAndView overdue(@RequestParam("repayPlanNo")String repayPlanNo) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("repayPlanNo",repayPlanNo);
		modelAndView.setViewName("PepayPlanManage/overflow");
		return modelAndView;
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}
	//查询
	@ResponseBody
	@RequestMapping("/repaylist")
	public PageUtils list(@RequestParam Map<String, Object> params) {

		if(!"".equals(params.get("repayPlanNo")) && params.get("repayPlanNo") != null){
			params.put("repayPlanNo",StringUtil.formatLike(String.valueOf(params.get("repayPlanNo"))));
		}
		if(!"".equals(params.get("loanOrderNo")) && params.get("loanOrderNo") != null){
			params.put("loanOrderNo",StringUtil.formatLike(String.valueOf(params.get("loanOrderNo"))));
		}
		if(!"".equals(params.get("realname")) && params.get("realname") != null){
			params.put("realname",StringUtil.formatLike(String.valueOf(params.get("realname"))));
		}
		Query query = new Query(params);
		List<RepayPlan> saleChanceList= loanCService.findRepayPlanList(query);
		Long total1= loanCService.getTotalRepayPlanList(query);
		int total= new Long(total1).intValue();
		PageUtils pageUtils = new PageUtils(saleChanceList, total);
		return pageUtils;
	}
	/**
	 * 还款流水列表
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/repayplanlistone")
	public PageUtils repayplanlist(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<RepayFlow> saleChanceList= loanCService.findRepayFlowList(query);
		Long total1= loanCService.getTotalRepayFlowList(query);
		int total= new Long(total1).intValue();
		PageUtils pageUtils = new PageUtils(saleChanceList, total);
		return pageUtils;
	}
	/**
	 * 逾期费用列表
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/overlist")
	public PageUtils overlist(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<OverFlow> saleChanceList= loanCService.findRepayOverList(query);
		Long total1= loanCService.getTotalRepayOverList(query);
		int total= new Long(total1).intValue();
		PageUtils pageUtils = new PageUtils(saleChanceList, total);
		return pageUtils;
	}

}