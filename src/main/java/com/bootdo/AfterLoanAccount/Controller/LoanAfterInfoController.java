package com.bootdo.AfterLoanAccount.Controller;

import com.bootdo.AfterLoanAccount.Service.AfterLoanAccountService;
import com.bootdo.Check.Service.ITbChackService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.system.domain.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.bootdo.common.utils.ShiroUtils.getUserId;


/**
 * 贷后信息Controller类
 * @author cl
 *
 */
@Controller
@RequestMapping("/loanAfterInfoController")
public class LoanAfterInfoController  extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(LoanAfterInfoController.class);
	@Resource
	private AfterLoanAccountService afterLoanAccountService;
	@Autowired
	private ITbChackService tbChackService;


	@ResponseBody
	@RequestMapping("/saveLoanInfoRecord")
	public String saveLoanInfoRecord(HttpServletRequest request) {
		String contents = request.getParameter("contents");
		String accountNo = request.getParameter("accountNo");
		String userName= getUser().getName();  //当前操作人人员姓名
		afterLoanAccountService.saveLoanInfoRecord(getUserId(),userName,contents,accountNo);
		return "0000";
	}
	
}
