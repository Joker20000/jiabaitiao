package com.bootdo.RepayRecord.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.bootdo.RepayPalan.domain.RepayPlan;
import com.bootdo.RepayRecord.domain.RepayRecord;
import com.bootdo.ULoan.Service.LoanCService;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 还款记录Controller类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/repayRecordController")
public class RepayRecordController {

	@Resource
	private LoanCService loanCService;
	@GetMapping("/repayRecord")
	String account() {
		return "repayRecord/repayRecord";
	}
	//查询还款记录
	@ResponseBody
	@RequestMapping("/repayList")
	public PageUtils list(@RequestParam Map<String, Object> params) {

		if(!"".equals(params.get("mobile")) && params.get("mobile") != null){
			params.put("mobile",StringUtil.formatLike(String.valueOf(params.get("mobile"))));
		}
		if(!"".equals(params.get("email")) && params.get("email") != null){
			params.put("email",StringUtil.formatLike(String.valueOf(params.get("email"))));
		}
		if(!"".equals(params.get("realname")) && params.get("realname") != null){
			params.put("realname",StringUtil.formatLike(String.valueOf(params.get("realname"))));
		}
		if(!"".equals(params.get("jfRepayNo")) && params.get("jfRepayNo") != null){
			params.put("jfRepayNo",StringUtil.formatLike(String.valueOf(params.get("jfRepayNo"))));
		}
		Query query = new Query(params);
		List<RepayRecord> saleChanceList= loanCService.findRepayRecordList(query);
		Long total1= loanCService.getTotalRepayRecordList(query);
		int total= new Long(total1).intValue();
		PageUtils pageUtils = new PageUtils(saleChanceList, total);
		return pageUtils;
	}
}
