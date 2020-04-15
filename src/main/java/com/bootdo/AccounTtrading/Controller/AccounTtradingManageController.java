package com.bootdo.AccounTtrading.Controller;

import com.bootdo.AccounTtrading.domain.AccounTtrading;
import com.bootdo.ULoan.Service.LoanCService;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 账户管理Controller类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/accounTtradingManageController")
public class AccounTtradingManageController {

	@Resource
	private LoanCService loanCService;
	@GetMapping("/account")
	String account() {
		return "AccountTrading/AccountTrading";
	}
	 @InitBinder
	 public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:���������ֵ��false:����Ϊ��ֵ
	}
	//账户交易查询
	@ResponseBody
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {

		if(!"".equals(params.get("realName")) && params.get("realName") != null){
			params.put("realName",StringUtil.formatLike(String.valueOf(params.get("realName"))));
		}
		if(!"".equals(params.get("email")) && params.get("email") != null){
			params.put("email",StringUtil.formatLike(String.valueOf(params.get("email"))));
		}
		if(!"".equals(params.get("flowId")) && params.get("flowId") != null){
			params.put("flowId",StringUtil.formatLike(String.valueOf(params.get("flowId"))));
		}
		if(!"".equals(params.get("channelBizid")) && params.get("channelBizid") != null){
			params.put("channelBizid",StringUtil.formatLike(String.valueOf(params.get("channelBizid"))));
		}
		if(!"".equals(params.get("bizid")) && params.get("bizid") != null){
			params.put("bizid",StringUtil.formatLike(String.valueOf(params.get("bizid"))));
		}
		if(!"".equals(params.get("mobile")) && params.get("mobile") != null){
			params.put("mobile",StringUtil.formatLike(String.valueOf(params.get("mobile"))));
		}
		Query query = new Query(params);
		List<AccounTtrading> saleChanceList= loanCService.findAccounTtradingList(query);
		Long total1= loanCService.getTotalAccounTtradingList(query);
		int total= new Long(total1).intValue();
		PageUtils pageUtils = new PageUtils(saleChanceList, total);
		return pageUtils;
	}
	
	
}
