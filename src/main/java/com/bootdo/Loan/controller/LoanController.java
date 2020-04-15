package com.bootdo.Loan.controller;

import com.bootdo.Loan.domain.AmountEntity;
import com.bootdo.Loan.domain.LoanEntity;
import com.bootdo.Loan.service.AccountService;
import com.bootdo.Loan.service.JXInterService;
import com.bootdo.Loan.service.LoanService;
import com.bootdo.Utils.DateUtil;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.oa.domain.NotifyDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author hewenjian
 * @date 2018-5-17
 */

@Controller
@RequestMapping("/loan/loanController")
public class LoanController extends BaseController {
	@Autowired
	private LoanService loanService;

    @Autowired
    private AccountService accountService;

	@Autowired
	private JXInterService jxInterService;


	@GetMapping()
	@RequiresPermissions("loan:loan:list")
	String loan() {
		return "loan/loan";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("loan:loan:list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		if(!"".equals(params.get("loanNum")) && params.get("loanNum") != null){
			params.put("loanNum",StringUtil.formatLike(String.valueOf(params.get("loanNum"))));
		}
		if(!"".equals(params.get("eName")) && params.get("eName") != null){
			params.put("eName",StringUtil.formatLike(String.valueOf(params.get("eName"))));
		}
		Query query = new Query(params);
		List<LoanEntity> notifyList = loanService.list(query);
		int total = loanService.count(query);
		PageUtils pageUtils = new PageUtils(notifyList, total);
		return pageUtils;
	}


	/**
	 * 审核
	 */
	@RequiresPermissions("loan:loan:batchRemove")
	@PostMapping("/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") String[] ids,@RequestParam("state") String state) {
		for(String loanNum : ids){
			loanService.updateChackState(loanNum,state);
            //查询借款信息
            Map<String,String> loanOrderMap = loanService.getLoanOrderDesc(loanNum);
			String linkPhone = loanOrderMap.get("linkPhone");
			String guarantPhone = loanOrderMap.get("guarantPhone");
			String amountSum = loanOrderMap.get("amount");
			Date nowDate = new Date();
			//审批人ID
			String chackId = String.valueOf(getUserId());
			//审批人
			String chackName = getUsername();
			if ("1".equals(state)){
				//更新借款日及还款日
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(nowDate);
                calendar.add(Calendar.DAY_OF_MONTH, +Integer.parseInt(loanOrderMap.get("loanDay")));
                //审核更新 订单状态  还款状态 逾期状态
                loanService.updateLoanOrderState(loanNum,"2","0","0",nowDate,calendar.getTime(),chackId,chackName);
				//审批通过，短信通知
				if(linkPhone.equals(guarantPhone)){
					jxInterService.sendMessages(linkPhone,"尊敬的嘉薪企业用户，您申请的"+amountSum+"元先发工资后打款已审核通过","嘉薪payroll");

				}else {
					jxInterService.sendMessages(linkPhone,"尊敬的嘉薪企业用户，您申请的"+amountSum+"元先发工资后打款已审核通过","嘉薪payroll");
					jxInterService.sendMessages(guarantPhone,"尊敬的嘉薪企业用户，您申请的"+amountSum+"元先发工资后打款已审核通过","嘉薪payroll");
				}
			}else if ("2".equals(state)){
				loanService.updateLoanOrder(loanNum,"3",nowDate,chackId,chackName);
				//返回用户的额度
                AmountEntity amount = new AmountEntity();
                amount.setAccountNo(loanOrderMap.get("accountNo"));
                amount.setAddOrSub("01");
                amount.setAmount(loanOrderMap.get("amount"));
                amount.setBusType("04");
                amount.setRelevantOrder(loanNum);
                accountService.changeAmount(amount);
				//审批通过，短信通知
				if(linkPhone.equals(guarantPhone)){
					jxInterService.sendMessages(linkPhone,"尊敬的嘉薪企业用户，您申请的"+amountSum+"元薪资垫付审核未通过，可以登录平台重新提交或联系客服","嘉薪payroll");

				}else {
					jxInterService.sendMessages(linkPhone,"尊敬的嘉薪企业用户，您申请的"+amountSum+"元薪资垫付审核未通过，可以登录平台重新提交或联系客服","嘉薪payroll");
					jxInterService.sendMessages(guarantPhone,"尊敬的嘉薪企业用户，您申请的"+amountSum+"元薪资垫付审核未通过，可以登录平台重新提交或联系客服","嘉薪payroll");
				}
			}
		}
		return R.ok();
	}



}
