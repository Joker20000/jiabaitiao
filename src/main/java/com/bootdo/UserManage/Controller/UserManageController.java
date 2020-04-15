package com.bootdo.UserManage.Controller;

import com.bootdo.ULoan.Service.LoanCService;
import com.bootdo.UserManage.domain.UserJBT;
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
 * 用户管理Controller类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/userManageController")
public class UserManageController {

	@Resource
	private LoanCService loanCService;
	@GetMapping("/bc")
	String bc() {
		return "BUser/IEnterPirse";
	}
	 @InitBinder
	 public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:���������ֵ��false:����Ϊ��ֵ
	}
	/**
	 * 用户列表
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {

		if(!"".equals(params.get("realName")) && params.get("realName") != null){
			params.put("realName",StringUtil.formatLike(String.valueOf(params.get("realName"))));
		}
		if(!"".equals(params.get("mobile")) && params.get("mobile") != null){
			params.put("mobile",StringUtil.formatLike(String.valueOf(params.get("mobile"))));
		}
		if(!"".equals(params.get("email")) && params.get("email") != null){
			params.put("email",StringUtil.formatLike(String.valueOf(params.get("email"))));
		}
		if(!"".equals(params.get("companyName")) && params.get("companyName") != null){
			params.put("companyName",StringUtil.formatLike(String.valueOf(params.get("companyName"))));
		}
		Query query = new Query(params);
		List<UserJBT> saleChanceList= loanCService.findUserList(query);
		Long total1 = loanCService.getTotalUserList(query);
			int total= new Long(total1).intValue();
		PageUtils pageUtils = new PageUtils(saleChanceList, total);
		return pageUtils;
	}

}
