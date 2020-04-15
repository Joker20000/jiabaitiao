package com.bootdo.AfterLoanAccount.Controller;

import com.bootdo.AfterLoanAccount.Service.AfterLoanAccountService;
import com.bootdo.RepayPalan.domain.RepayPlan;
import com.bootdo.ULoan.Service.LoanCService;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 贷后管理Controller类
 * @author cl
 *
 */
@Controller
@RequestMapping("/afterLoanAccounController")
public class AfterLoanAccountController {
	private static Logger logger = LoggerFactory.getLogger(AfterLoanAccountController.class);
	@Resource
	private LoanCService loanCService;
	@Resource
	private AfterLoanAccountService afterLoanAccountService;
	@GetMapping("/afterLoanAccount")
	String account() {
		return "AfterLoanAccount/AfterLoanAccount";
	}


	@GetMapping("/tofeeReduction")
	public String tofeeReduction(HttpServletRequest requst, Model model) {
		String rank = "";
		String accountNo = requst.getParameter("accountNo");
		String rePlanNo = requst.getParameter("repayaccountNo");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> reliefMap = new HashMap<>();
		if(StringUtils.isBlank(accountNo) && StringUtils.isBlank(rePlanNo)){//账户号、还款订单号


		}else{
			List<Map<String,String>> listMap= afterLoanAccountService.getList(requst.getParameter("accountNo"));
			for (int i = 0;i < listMap.size(); i++){
				if(rePlanNo.equals(listMap.get(i).get("repayPlanNo"))){
					DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
					rank = decimalFormat.format(listMap.get(i).get("ranks"));
					break;
				}
			}
			map = afterLoanAccountService.searchUserRemaRepayByNo(requst.getParameter("accountNo"), requst.getParameter("repayaccountNo"));
			reliefMap = afterLoanAccountService.searchUserNowFeeByRepayNo(requst.getParameter("repayaccountNo"));
			map.put("rank", rank);
			map.put("repayPlanNo", requst.getParameter("repayaccountNo"));
			map.put("init", requst.getParameter("init"));
		}
		model.addAttribute("data", map);
		model.addAttribute("repayPlanNo", rePlanNo);
		model.addAttribute("shouldPay", reliefMap);
		return "AfterLoanAccount/FeeReduction";
	}

	//查询
	@ResponseBody
	@RequestMapping("/afterLoanAccountlist")
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
		List<RepayPlan> saleChanceList= afterLoanAccountService.findAfterLoanAccountList(query);
		for (RepayPlan repayPlan:saleChanceList){
            List<Map<String,String>> map= afterLoanAccountService.getList(repayPlan.getAccountNo());
            for (int i = 0;i < map.size(); i++){
            	//System.out.print(""+repayPlan.getRepayPlanNo()+map.get(i).get("repayPlanNo"));
                if(repayPlan.getRepayPlanNo().equals(map.get(i).get("repayPlanNo"))){
					DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
					//System.out.println(decimalFormat.format(map.get(i).get("ranks")));
                	repayPlan.setManyReturn(decimalFormat.format(map.get(i).get("ranks")));
                }
            }

		}
		Long total1= afterLoanAccountService.getTotaAfterLoanAccountList(query);
		int total= new Long(total1).intValue();
		PageUtils pageUtils = new PageUtils(saleChanceList, total);
		return pageUtils;
	}

    @RequestMapping("/sendMessagePage")
    public String sendMessagePage(HttpServletRequest request,Model model) {
        String accountNo = request.getParameter("accountNo");
		String mobile = request.getParameter("mobile");
		String rePlanNo = request.getParameter("repayaccountNo");
		String userChannelId = request.getParameter("userChannelId");
        List<Map<String, String>> messageList = afterLoanAccountService.getMessageList();
		String rank = "";
		if(StringUtils.isBlank(accountNo) || StringUtils.isBlank(rePlanNo)){//账户号、还款订单号
			return "";
		}
		List<Map<String,String>> listMap= afterLoanAccountService.getList(request.getParameter("accountNo"));
		for (int i = 0;i < listMap.size(); i++){
			if(rePlanNo.equals(listMap.get(i).get("repayPlanNo"))){
				DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
				rank = decimalFormat.format(listMap.get(i).get("ranks"));
				break;
			}
		}
		Map<String, Object> map = afterLoanAccountService.searchUserRemaRepayByNo(request.getParameter("accountNo"), request.getParameter("repayaccountNo"));
		Map<String, Object> reliefMap = afterLoanAccountService.searchUserNowFeeByRepayNo(request.getParameter("repayaccountNo"));
		map.put("rank", rank);
		map.put("repayPlanNo", request.getParameter("repayaccountNo"));
		map.put("mobile", mobile);
		map.put("userChannelId", userChannelId);
		map.put("messageOne",messageList.get(0).get("messageOne"));
		map.put("messageTwo",messageList.get(0).get("messageTwo"));
		model.addAttribute("data", map);
		model.addAttribute("shouldPay", reliefMap);
        return "AfterLoanAccount/SendMessage";
    }


	@GetMapping("/toLoanAfterInfo")
	public String toLoanAfterInfo(HttpServletRequest requst, Model model) {
		String accountNo = requst.getParameter("accountNo");
		//查询贷后列表
		List<Map<String,String>> loanAfterInfList = afterLoanAccountService.getLoanAfterInfo(accountNo);
		model.addAttribute("loanAfterInfList", loanAfterInfList);
		model.addAttribute("accountNo", accountNo);
		return "AfterLoanAccount/LoanAfterInfo";
	}
}
