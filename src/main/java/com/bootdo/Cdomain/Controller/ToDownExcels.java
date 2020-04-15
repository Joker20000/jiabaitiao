package com.bootdo.Cdomain.Controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import com.bootdo.TicketFlow.domain.TicketFlow;
//import com.bootdo.TicketFlow.Service.TicketFlowService;
import com.bootdo.AccounTtrading.domain.AccounTtrading;
import com.bootdo.GuaranteeOrder.Service.GuaranteeOrderService;
import com.bootdo.QuotaAd.Service.QuotaAdjustmentService;
import com.bootdo.QuotaAd.domain.QuotaRecord;
import com.bootdo.RefundOrder.Service.RefundOrderService;
import com.bootdo.RepayPalan.domain.RepayPlan;
import com.bootdo.RepayRecord.domain.RepayRecord;
import com.bootdo.TicketFlow.Service.TicketFlowService;
import com.bootdo.TicketFlow.domain.TicketFlow;
import com.bootdo.ULoan.Service.LoanCService;
import com.bootdo.ULoan.Service.LoanOrderService;
import com.bootdo.ULoan.domain.LoanOrder;
import com.bootdo.UserManage.domain.UserJBT;
import com.bootdo.Utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 导出Excel
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/oldtoDownExcel")
public class ToDownExcels {

	@Resource
	private LoanCService loanCService;

	@Resource
	private TicketFlowService ticketFlowService;

	@Autowired
	private LoanOrderService loanOrderService;
	@Autowired
	private RefundOrderService refundOrderService;
	@Autowired
	private GuaranteeOrderService guaranteeOrderService;


	@Autowired
	private QuotaAdjustmentService quotaAdjustmentService;

	/**
	 * 还款计划导出Excel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/repayPlanDownExcel")
	public void repayPlanDownExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		/*****************************************************************/
		map.put("realname",StringUtil.formatLike(request.getParameter("realname")));
		map.put("repayPlanNo",StringUtil.formatLike(request.getParameter("repayPlanNo")));
		map.put("loanOrderNo",StringUtil.formatLike(request.getParameter("loanOrderNo")));
		map.put("orderType", request.getParameter("orderType"));
		map.put("repayState", request.getParameter("repayState"));
		map.put("overdueState",request.getParameter("overdueState"));
		map.put("latestTimeStart", request.getParameter("latestTimeStart"));
		map.put("latestTimeEnd", request.getParameter("latestTimeEnd"));
		map.put("mobile", request.getParameter("mobile"));
		map.put("fullRepaymentTimeStart", request.getParameter("fullRepaymentTimeStart"));
		map.put("fullRepaymentTimeEnd", request.getParameter("fullRepaymentTimeEnd"));
		/***********************************************************************/
		ArrayList<HashMap<String, Object>> loanLists = loanCService.findRepayPlanListN(map);
		String filename = "还款计划" + DateUtil.getCurTimestampStr() + CodeUtil.get6Code() + ".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "iso-8859-1"));
		OutputStream out = response.getOutputStream();
		ExcelExportUtil4DIY.exportToFile("还款计划", loanLists,
				new String[]{"还款计划ID","用户渠道","白条订单号","企业ID","企业名称","姓名","手机号","邮箱","会员等级","订单类型","当前期数","总期数","当期应还本金","当期应还费用","当期逾期费用","当期应还总额","最迟还清日","当期累计还款","当期退款抵充","当期剩余待还","还款状态","逾期状态","还清时间","逾期减免","备注"},
				new String[]{"repayPlanNo","channel","loanOrderNo","companyId","companyName","realname","mobile","email","vipLevel","orderType","nowPeriod","periodSum","shouldAmount","shouldInterest","nowOverdue","repaymentSum","latestTime","alreadyRepaidSum","refundAmount","nowSurplusAmountSum","repayState","overdueState","fullRepaymentTime","reduction","remark"},out);
	}

	/**
	 * 用户管理导出Excel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/userManageDownExcel")
	public void userManageDownExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		/*****************************************************************/
		map.put("realName",StringUtil.formatLike(request.getParameter("realName")));
		map.put("mobile",StringUtil.formatLike(request.getParameter("mobile")));
		map.put("email",StringUtil.formatLike(request.getParameter("email")));
		map.put("companyName",StringUtil.formatLike(request.getParameter("companyName")));
		map.put("userChannelId", request.getParameter("userChannelId"));
		map.put("proveState", request.getParameter("proveState"));
		map.put("register",request.getParameter("register"));
		map.put("register1", request.getParameter("register1"));
		map.put("proveSubmitTime", request.getParameter("proveSubmitTime"));
		map.put("proveSubmitTime1", request.getParameter("proveSubmitTime1"));
		map.put("proveAuditTime", request.getParameter("proveAuditTime"));
		map.put("proveAuditTime1", request.getParameter("proveAuditTime1"));
		map.put("isGuaranteeCard", request.getParameter("isGuaranteeCard"));
		/***********************************************************************/
		ArrayList<HashMap<String, Object>> loanLists = loanCService.findUserListN(map);
		String filename = "用户管理" + DateUtil.getCurTimestampStr() + CodeUtil.get6Code() + ".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "iso-8859-1"));
		OutputStream out = response.getOutputStream();
		ExcelExportUtil4DIY.exportToFile("用户管理", loanLists,
				new String[]{"用户ID", "姓名", "手机号", "邮箱","用户渠道","企业ID", "企业名称", "身份证号", "认证状态", "担保支付","注册时间", "认证提交时间", "认证完成时间"},
				new String[]{"userId", "realName", "mobile","email", "channel", "companyId", "companyName", "cardid", "proveState","isGuaranteeCard", "register", "proveSubmitTime", "proveAuditTime"}, out);
	}


	/*
	 * 账户交易导出Excel
	 *@param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/accountTradingDownExcel")
	public void accountTradingDownExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{

		Map<String,Object> map=new HashMap<String,Object>();
		map.put("realName",StringUtil.formatLike(request.getParameter("realName")));
		map.put("mobile",StringUtil.formatLike(request.getParameter("mobile")));
		map.put("email",StringUtil.formatLike(request.getParameter("email")));
		map.put("flowId",StringUtil.formatLike(request.getParameter("flowId")));
		map.put("channelBizid",StringUtil.formatLike(request.getParameter("channelBizid")));
		map.put("bizid",StringUtil.formatLike(request.getParameter("bizid")));
		map.put("transType", request.getParameter("transType"));
		map.put("transTimeStart", request.getParameter("transTimeStart"));
		map.put("transTimeEnd", request.getParameter("transTimeEnd"));
		ArrayList<HashMap<String, Object>> saleChanceList = loanCService.findAccounTtradingListN(map);
		String filename = "账户交易" + DateUtil.getCurTimestampStr() + CodeUtil.get6Code() + ".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "iso-8859-1"));
		OutputStream out = response.getOutputStream();
		ExcelExportUtil4DIY.exportToFile("账户交易", saleChanceList,
				new String[]{"交易流水号","用户渠道","企业ID","企业名称","姓名","邮箱","手机","交易金额","交易后授信额度","交易后可用授信额度","业务类型","白条订单号","嘉福流水号","交易时间"},
				new String[]{"flowId","channel","companyId","companyName","realName","email","mobile","amount","creditLimit","usableLimit","transType","bizid","channelBizid","transTime"},out);
	}


	/*
     * 额度记录  quotaRecordDownExcel
     */
	@RequestMapping("/quotaRecordDownExcel")
	public void quotaRecordDownExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{

		Map<String,Object> map=new HashMap<String,Object>();
		map.put("mobile",StringUtil.formatLike(request.getParameter("mobile")));
		map.put("realName",StringUtil.formatLike(request.getParameter("realName")));
		map.put("cardId",StringUtil.formatLike(request.getParameter("cardId")));
		map.put("uname",StringUtil.formatLike(request.getParameter("uname")));
		map.put("startTime", request.getParameter("startTime"));
		map.put("endTime", request.getParameter("endTime"));
		ArrayList<HashMap<String, Object>> quotaRecordList = new ArrayList<>();
		int offset = 0;
		int limit = 1000;
		while(true) {
			map.put("offset", offset * limit);
			map.put("limit", limit);

			List<HashMap<String, Object>> dateList=quotaAdjustmentService.findQuotaRecordByConditionN(map);
			if (dateList.size() > 0) {
				for (int i = 0; i < dateList.size(); i++) {
					if ("01".equals(dateList.get(i).get("addAndSubstract"))) {
						dateList.get(i).put("amount", "+"+dateList.get(i).get("amount"));
					} else if ("02".equals(dateList.get(i).get("addAndSubstract"))) {
						dateList.get(i).put("amount", "-"+dateList.get(i).get("amount"));
					}
				}
				quotaRecordList.addAll(dateList);
			}else{
				break;
			}
			offset++;
		}
		String filename = "额度记录" + DateUtil.getCurTimestampStr() + CodeUtil.get6Code() + ".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "iso-8859-1"));
		OutputStream out = response.getOutputStream();
		ExcelExportUtil4DIY.exportToFile("额度记录", quotaRecordList,
				new String[]{"调额订单号","姓名","手机号","身份证","调整额度","调整后授信额度","调整后可用授信额度","调整原因","调整人","调整时间"},
				new String[]{"creditNum","realName","mobile","cardId","amount","creditLimit","usableLimit","reason","uname","operateTime" },out);
	}

	/*
	 *
	 * 订单管理
	 *
	 *
	 */
	@RequestMapping("/orderManagerDownExcel")
	public void orderManagerDownExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("realName",StringUtil.formatLike(request.getParameter("realName")));
		map.put("mobile",StringUtil.formatLike(request.getParameter("mobile")));
		map.put("email",StringUtil.formatLike(request.getParameter("email")));
		map.put("loanOrderNo",StringUtil.formatLike(request.getParameter("loanOrderNo")));
		map.put("orderType", request.getParameter("orderType"));
		map.put("repayState", request.getParameter("repayState"));
		map.put("orderState", request.getParameter("orderState"));
		map.put("createTimeStart", request.getParameter("createTimeStart"));
		map.put("createTimeEnd", request.getParameter("createTimeEnd"));
		ArrayList<HashMap<String, Object>> saleChanceList = loanOrderService.findLoanOrderByConditionN(map);
		String filename = "分期订单管理" + DateUtil.getCurTimestampStr() + CodeUtil.get6Code() + ".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "iso-8859-1"));
		OutputStream out = response.getOutputStream();
		ExcelExportUtil4DIY.exportToFile("分期订单管理", saleChanceList,
				new String[]{"白条订单号","订单渠道","企业ID","企业名称","姓名","邮箱","手机","用户等级","订单类型","本金","支付通道费（元）","分期服务费","券抵息金额","逾期费用","总期数","退款金额","期费率%","支付通道费%",
						"逾期日费率%","最迟还清日","累计还款","剩余待还","还款状态","订单状态","订单生成时间","订单完成时间","还清时间","开户银行","卡号","订单失败原因"},
				new String[]{"loanOrderNo","channel","companyId","companyName","realName","email","mobile","vipLevel","orderType","nowAmountSum","paymentChannelAmount","nowInterestSum","disCounts","nowOverdueSum","preiodSum","refundDiAmountSum","periodRate","transferRate",
						"overdueDayRate","latestTime","readyAmountSum","repaymentAmountSum","repayState","orderState","createTime","grantTime","fullrepaymentTime","bank","cardNo","errorMsg"}, out);

	}

	/*
	 * 还款记录导出Excel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/repayRecordDownExcel")
	public void repayRecordDownExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("mobile",StringUtil.formatLike(request.getParameter("mobile")));
		map.put("realname",StringUtil.formatLike(request.getParameter("realname")));
		map.put("email",StringUtil.formatLike(request.getParameter("email")));
		map.put("jfRepayNo",StringUtil.formatLike(request.getParameter("jfRepayNo")));
		map.put("finishTimeStart", request.getParameter("finishTimeStart"));
		map.put("finishTimeEnd", request.getParameter("finishTimeEnd"));
		ArrayList<HashMap<String, Object>> saleChanceList = new ArrayList<>();
		int offset = 0;
		int limit = 500;
		while(true) {
			map.put("offset", offset * limit);
			map.put("limit", limit);

			List<HashMap<String, Object>> dateList=loanCService.findRepayRecordListN(map);
			if (dateList.size() > 0) {
				for (int i = 0; i < dateList.size(); i++) {
					if ("01".equals(dateList.get(i).get("repayType"))) {
						dateList.get(i).put("repayType", "福利账户");
					} else if ("02".equals(dateList.get(i).get("repayType"))) {
						dateList.get(i).put("repayType", "工资账户");
					} else if ("03".equals(dateList.get(i).get("repayType"))) {
						dateList.get(i).put("repayType", "支付宝");
					} else if ("04".equals(dateList.get(i).get("repayType"))) {
						dateList.get(i).put("repayType", "微信");
					} else if ("05".equals(dateList.get(i).get("repayType"))) {
						dateList.get(i).put("repayType", "银行卡");
					}else if ("06".equals(dateList.get(i).get("repayType"))) {
						dateList.get(i).put("repayType", "企业支付宝");
					}else if ("07".equals(dateList.get(i).get("repayType"))) {
						dateList.get(i).put("repayType", "人事代扣");
					} else if ("08".equals(dateList.get(i).get("repayType"))) {
						dateList.get(i).put("repayType", "福豆");
					}
					if ("0".equals(dateList.get(i).get("repayFlag"))) {
						dateList.get(i).put("repayFlag", "系统自动");
					} else if ("1".equals(dateList.get(i).get("repayFlag"))) {
						dateList.get(i).put("repayFlag", "用户主动");
					}else if ("3".equals(dateList.get(i).get("repayFlag"))) {
						dateList.get(i).put("repayFlag", "人工处理");
					}
				}
				saleChanceList.addAll(dateList);
			}else{
				break;
			}
			offset++;
		}
		String filename = "还款记录" + DateUtil.getCurTimestampStr() + CodeUtil.get6Code() + ".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "iso-8859-1"));
		OutputStream out = response.getOutputStream();
		ExcelExportUtil4DIY.exportToFile("还款记录", saleChanceList,
				new String[]{"还款订单号","企业名称","姓名","邮箱","手机号","金额","本金还款","费用还款","还款方式","还款账户","嘉福流水号","时间"},
				new String[]{"repaySerianlNo","companyName","realname","email","mobile","repayAmountSum","amount","repayInterestSum","repayFlag","repayType","jfRepayNo","finishTime"},out);
	}

	/*
	 * 券交易管理导出Excel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/ticketFlowServiceController")
	public void ticketManagerDownExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("btOrderNo",StringUtil.formatLike(request.getParameter("btOrderNo")));
		map.put("ticketTransactionFlow", StringUtil.formatLike(request.getParameter("ticketTransactionFlow")));
		map.put("ticketNo", StringUtil.formatLike(request.getParameter("ticketTransactionFlow")));
		map.put("ticketFlowType",request.getParameter("ticketFlowType"));
		map.put("ticketTransactionState", request.getParameter("ticketTransactionState"));
		map.put("finishTimeStart", request.getParameter("finishTimeStart"));
		map.put("finishTimeEnd", request.getParameter("finishTimeEnd"));
		ArrayList<HashMap<String, Object>> saleChanceList = new ArrayList<>();
		int offset = 0;
		int limit = 1000;
		//测试用计数
		/*int icount = 0;*/
		while(true) {
			/*icount +=1;
			if (icount>2){
				break;
			}*/
			map.put("offset", offset * limit);
			map.put("limit", limit);

			List<HashMap<String, Object>> dateList= ticketFlowService.findTicketFlowListMap(map);
			if (dateList.size() > 0) {
				for (int i = 0; i < dateList.size(); i++) {

					if ("01".equals(dateList.get(i).get("ticketFlowType"))) {
						dateList.get(i).put("ticketFlowType", "销券");
					} else if ("02".equals(dateList.get(i).get("ticketFlowType"))) {
						dateList.get(i).put("ticketFlowType", "撤券");
					}
					if ("01".equals(dateList.get(i).get("ticketTransactionState"))) {
						dateList.get(i).put("ticketTransactionState", "交易成功");
					} else if ("02".equals(dateList.get(i).get("ticket TransactionState"))) {
						dateList.get(i).put("ticketTransactionState", "交易成功");
					} else if ("03".equals(dateList.get(i).get("ticketTransactionState"))) {
						dateList.get(i).put("ticketTransactionState", "处理中");
					}
					if ("01".equals(dateList.get(i).get("orderState"))) {
						dateList.get(i).put("orderState", "处理中");
					} else if ("02".equals(dateList.get(i).get("orderState"))) {
						dateList.get(i).put("orderState", "成功");
					} else if ("03".equals(dateList.get(i).get("orderState"))) {
						dateList.get(i).put("orderState", "失败");
					} else if ("04".equals(dateList.get(i).get("orderState"))) {
						dateList.get(i).put("orderState", "部分退款");
					} else if ("05".equals(dateList.get(i).get("orderState"))) {
						dateList.get(i).put("orderState", "全部退款");
					}
				}
				saleChanceList.addAll(dateList);
			}else{
				break;
			}
			offset++;
		}
		String filename = "券交易管理" + DateUtil.getCurTimestampStr() + CodeUtil.get6Code() + ".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "iso-8859-1"));
		OutputStream out = response.getOutputStream();
		ExcelExportUtil4DIY.exportToFile("券交易管理", saleChanceList,
				new String[]{"白条订单号","券交易流水号","交易类别","订单状态","交易状态","渠道流水号","券号","交易时间"},
				new String[]{"btOrderNo","ticketFlowId","ticketFlowType","orderState","ticketTransactionState","ticketTransactionFlow","ticketNo","transactionTime"},out);
	}

	@RequestMapping("/refundOrderDownExcel")
	public void refundOrderDownExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("loanOrderNo",StringUtil.formatLike(request.getParameter("loanOrderNo")));
		map.put("refundOrderNo", StringUtil.formatLike(request.getParameter("refundOrderNo")));
		map.put("extRefOrderId",StringUtil.formatLike(request.getParameter("extRefOrderId")));
		map.put("createTimeStart", request.getParameter("createTimeStart"));
		map.put("createTimeEnd", request.getParameter("createTimeEnd"));
		ArrayList<HashMap<String, Object>> saleChanceList = refundOrderService.findRefundOrderListN(map);
		String filename = "消费退款订单管理" + DateUtil.getCurTimestampStr() + CodeUtil.get6Code() + ".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "iso-8859-1"));
		OutputStream out = response.getOutputStream();
		ExcelExportUtil4DIY.exportToFile("消费退款订单管理", saleChanceList,
				new String[]{"白条退款订单号","企业名称","姓名","手机","退款金额","抵充金额","充值金额","嘉福充值流水号","累计退款金额","状态","白条消费订单号","渠道退款订单号","时间"},
				new String[]{"refundOrderNo","companyName","realName","mobile","refundAmount","offsetAmount","rechargeAmount","rechargeOrderTid","totalRefundAmount","refundState","loanOrderNo","extRefOrderId","createDate"}, out);

	}

	@RequestMapping("/guaranteeOrderDownExcel")
	public void guaranteeOrderDownExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("guaranteeOrderId",StringUtil.formatLike(request.getParameter("guaranteeOrderId")));
		map.put("companyName", StringUtil.formatLike(request.getParameter("companyName")));
		map.put("realName",StringUtil.formatLike(request.getParameter("realName")));
		map.put("mobile",StringUtil.formatLike(request.getParameter("mobile")));
		map.put("repayState",request.getParameter("repayState"));
		map.put("createTimeStart", request.getParameter("createTimeStart"));
		map.put("createTimeEnd", request.getParameter("createTimeEnd"));
		map.put("repayTimeStart", request.getParameter("repayTimeStart"));
		map.put("repayTimeEnd", request.getParameter("repayTimeEnd"));
		ArrayList<HashMap<String, Object>> saleChanceList = guaranteeOrderService.findGuaranteeOrderListN(map);
		String filename = "担保订单管理" + DateUtil.getCurTimestampStr() + CodeUtil.get6Code() + ".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "iso-8859-1"));
		OutputStream out = response.getOutputStream();
		ExcelExportUtil4DIY.exportToFile("消费退款订单管理", saleChanceList,
				new String[]{"白条订单号","企业名称","姓名","邮箱","手机","金额","状态","报销买券订单号","买券时间","白条还款订单号","报销还款订单号","嘉福还款流水号","还款时间"},
				new String[]{"guaranteeOrderId","companyName","realName","email","mobile","amount","repayState","extOrderId","createDate","guaranteeRepayId","extRepayOrderId","jfRepayNo","repayDate"}, out);

	}

}
