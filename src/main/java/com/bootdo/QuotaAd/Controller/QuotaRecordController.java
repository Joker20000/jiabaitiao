package com.bootdo.QuotaAd.Controller;

import java.util.List;
import java.util.Map;


import com.bootdo.QuotaAd.Service.QuotaAdjustmentService;
import com.bootdo.QuotaAd.domain.QuotaRecord;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 额度记录Controller类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/quotaRecordController")
public class QuotaRecordController {

	@Autowired
	private QuotaAdjustmentService quotaAdjustmentService;
	@RequestMapping("/quotaRecord")
	String quotaRecord(){
		return "QuotaAdjustMent/quotaRecord";
	}
	//查询额度记录
	@ResponseBody
	@RequestMapping("/quataRecordlist")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		if(!"".equals(params.get("realName")) && params.get("realName") != null){
			params.put("realName",StringUtil.formatLike(String.valueOf(params.get("realName"))));
		}
		if(!"".equals(params.get("mobile")) && params.get("mobile") != null){
			params.put("mobile",StringUtil.formatLike(String.valueOf(params.get("mobile"))));
		}
		if(!"".equals(params.get("cardId")) && params.get("cardId") != null){
			params.put("cardId",StringUtil.formatLike(String.valueOf(params.get("cardId"))));
		}
		if(!"".equals(params.get("uname")) && params.get("uname") != null){
			params.put("uname",StringUtil.formatLike(String.valueOf(params.get("uname"))));
		}
		Query query = new Query(params);
		List<QuotaRecord> QuotaAdjustmentList= quotaAdjustmentService.findQuotaRecordByCondition(query);
		Long total1= quotaAdjustmentService.findQuotaRecordCountByCondition(query);
		int total= new Long(total1).intValue();
		PageUtils pageUtils = new PageUtils(QuotaAdjustmentList, total);
		return pageUtils;
	}

}
