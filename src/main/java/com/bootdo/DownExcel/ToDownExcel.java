package com.bootdo.DownExcel;

import java.io.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bootdo.Account.Service.FlowService;
import com.bootdo.BackerUser.Service.IEnterPriseService;
import com.bootdo.Check.Service.ITbChackService;
import com.bootdo.Check.domain.TbChack;
import com.bootdo.Check.domain.TbChackRecord;
import com.bootdo.Loan.service.LoanService;
import com.bootdo.Repayment.ServiceImpl.IRepayImpl;
import com.bootdo.Utils.*;
import com.bootdo.common.utils.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 导出Excel
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/toDownExcel")
public class ToDownExcel {
	private Logger LOG = LoggerFactory.getLogger(ToDownExcel.class);
	@Resource
	private LoanService loanService;
	@Resource
	private IEnterPriseService iEnterPriseService;
	@Resource
	private ITbChackService checkService;
	@Resource
	private FlowService flowService;
	@Resource
	private IRepayImpl iRepay;

	/**
	 * 借款订单导出Excel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	/*@RequestMapping("/loanOrderDownExcel")
	@ResponseBody
	public void loanOrderDownExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> map=new HashMap<>();
		map.put("loanNum",StringUtil.formatLike(request.getParameter("loanNum")));
		map.put("eName", StringUtil.formatLike(request.getParameter("eName")));
		map.put("orderState",request.getParameter("orderState"));
		map.put("overState", request.getParameter("overState"));
		map.put("repayState", request.getParameter("repayState"));
		map.put("qBeginTime", request.getParameter("qBeginTime"));
		map.put("qEndTime", request.getParameter("qEndTime"));
		map.put("chackState", request.getParameter("chackState"));
		ArrayList<HashMap<String, Object>> loanLists = new ArrayList<>();
		int offset = 0;
		int limit = 1000;
		while(true){
			map.put("offset", offset*limit);
			map.put("limit", limit);
			List<HashMap<String,Object>> loanList = loanService.queryLoanLists(map);
			if(loanList.size() > 0){
				for (int i = 0;i<loanList.size();i++){
					if (("0".equals(loanList.get(i).get("procCost")) || "".equals(loanList.get(i).get("procCost"))) && "2".equals(loanList.get(i).get("procCost"))){
						//计算还款日与借款日相隔的天数
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						ParsePosition pos = new ParsePosition(0);
						Date date = new Date();
						int dayOffset = calcDayOffset(sdf.parse(String.valueOf(loanList.get(i).get("loanDate")), pos), date) + 1;
						//成功订单当前手续费=max（最低手续费，借款金额X（当前日期-订单完成日期+1）X年化服务费率/360）
						Double fee = Double.parseDouble(String.valueOf(loanList.get(i).get("amount"))) * dayOffset * Double.parseDouble(String.valueOf(loanList.get(i).get("yearRate")))/100/ 360;
						//手续费
						String procCost = String.valueOf(fee - Double.parseDouble(String.valueOf(loanList.get(i).get("minProcCost"))) > 0 ? fee : Double.parseDouble(String.valueOf(loanList.get(i).get("minProcCost"))));
						loanList.get(i).put("procCost",procCost);
					}
					//累计已还总金额
					String repAmountSum = BigDecimalUtil.BigDecimalAdd(BigDecimalUtil.BigDecimalAdd(String.valueOf(loanList.get(i).get("repAmount")),String.valueOf(loanList.get(i).get("repProcCost"))),String.valueOf(loanList.get(i).get("repOverCost")));
					loanList.get(i).put("repAmountSum",repAmountSum);
					//剩余待还总金额
					String needAmountSum = BigDecimalUtil.BigDecimalSubtract(BigDecimalUtil.BigDecimalAdd(BigDecimalUtil.BigDecimalAdd(String.valueOf(loanList.get(i).get("amount")),String.valueOf(loanList.get(i).get("procCost"))),String.valueOf(loanList.get(i).get("overCost"))),repAmountSum);
					loanList.get(i).put("needAmountSum",needAmountSum);
					//渠道
					if ("01".equals(loanList.get(i).get("channel"))){
						loanList.get(i).put("channel","嘉薪");
					}else if ("02".equals(loanList.get(i).get("channel"))){
						loanList.get(i).put("channel","嘉福");
					}
					//订单状态('0'.待处理,'1'.处理中,'2'.成功,'3'.失败,'4'.充值金额异常,'5'.恢复额度异常)
					if ("0".equals(loanList.get(i).get("orderState"))){
						loanList.get(i).put("orderState","待处理");
					}else if ("1".equals(loanList.get(i).get("orderState"))){
						loanList.get(i).put("orderState","处理中");
					}else if ("2".equals(loanList.get(i).get("orderState"))){
						loanList.get(i).put("orderState","成功");
					}else if ("3".equals(loanList.get(i).get("orderState"))){
						loanList.get(i).put("orderState","失败");
					}else if ("4".equals(loanList.get(i).get("orderState"))){
						loanList.get(i).put("orderState","充值金额异常");
					}else if ("5".equals(loanList.get(i).get("orderState"))){
						loanList.get(i).put("orderState","恢复额度异常");
					}
					//审批状态('0'.待审批,'1'.审批通过,'2'.审批驳回)
					if ("0".equals(loanList.get(i).get("chackState"))){
						loanList.get(i).put("chackState","待审批");
					}else if ("1".equals(loanList.get(i).get("chackState"))){
						loanList.get(i).put("chackState","审批通过");
					}else if ("2".equals(loanList.get(i).get("chackState"))){
						loanList.get(i).put("chackState","审批驳回");
					}
					//逾期状态('0'.未逾期,'1'.已逾期)
					if ("0".equals(loanList.get(i).get("overState"))){
						loanList.get(i).put("overState","未逾期");
					}else if ("1".equals(loanList.get(i).get("overState"))){
						loanList.get(i).put("overState","已逾期");
					}
					//还款状态('0'.待还款,'1'.还款中,'2'.已还清)
					if ("0".equals(loanList.get(i).get("repayState"))){
						loanList.get(i).put("repayState","待还款");
					}else if ("1".equals(loanList.get(i).get("repayState"))){
						loanList.get(i).put("repayState","还款中");
					}else if ("2".equals(loanList.get(i).get("repayState"))){
						loanList.get(i).put("repayState","已还清");
					}
					//还清方式('0'.正常还清,'1'.提前还清,'2'.逾期还清)
					if ("0".equals(loanList.get(i).get("payoffType"))){
						loanList.get(i).put("payoffType","正常还清");
					}else if ("1".equals(loanList.get(i).get("payoffType"))){
						loanList.get(i).put("payoffType","提前还清");
					}else if ("2".equals(loanList.get(i).get("payoffType"))){
						loanList.get(i).put("payoffType","逾期还清");
					}
				}
				loanLists.addAll(loanList);
			}else{
				break;
			}
			offset++;
		}
		String filename = "借款订单"+DateUtil.getCurTimestampStr()+CodeUtil.get4Code()+".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(),"iso-8859-1"));
		OutputStream out = response.getOutputStream();
		ExcelExportUtil4DIY.exportToFile("借款订单",loanLists,
				new String[]{"借款订单号","用户渠道","渠道企业ID","企业名称","联系人","联系人手机","担保人","担保人手机","借款金额","年化服务费率","借款天数","最低手续费","逾期日费率","当前手续费","当前逾期费用","最迟还款日","累计已还本金","累计已还手续费","累计已还逾期费用","累计已还总金额","剩余待还总金额","订单状态","审批状态","审批人","逾期状态","还款状态","还清时间","还清方式","订单生成时间","借款日","订单完成时间"},
				new String[]{"loanNum","channel","cEid","eName","linkman","linkPhone","guarant","guarantPhone","amount","yearRate","loanDay","minProcCost","overRate","procCost","overCost","repayDate","repAmount","repProcCost","repOverCost","repAmountSum","needAmountSum","orderState","chackState","chackName","overState","repayState","payoffDate","payoffType","createDate","loanDate","chackDate"},out);
	}*/

	/*
	 * date2比date1多的天数
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int calcDayOffset(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);


		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) {  //同一年
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {  //闰年
					timeDistance += 366;
				} else {  //不是闰年

					timeDistance += 365;
				}
			}
			return timeDistance + (day2 - day1);
		} else { //不同年
			return day2 - day1;
		}
	}

	/**
	 * 用户管理下载
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	/*@RequestMapping("/DownBackerExcel")
	public void DownBackExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> map=new HashMap<>();
		map.put("ename",StringUtil.formatLike(request.getParameter("ename")));
		map.put("creditcode", request.getParameter("creditcode"));
		map.put("state", request.getParameter("state"));
		map.put("starTime",request.getParameter(" starTime"));
		map.put("endTime", request.getParameter("endTime"));
		ArrayList<HashMap<String, Object>> loanLists = new ArrayList<> ();
		int offset = 0;
		int limit = 1000;
		while(true){
			map.put("offset", offset*limit);
			map.put("limit", limit);

			List<HashMap<String,Object>> loanList = iEnterPriseService.BacExcel(map);
			if(loanList.size() > 0){
				for (int i = 0;i<loanList.size();i++){
					if("0".equals(loanList.get(i).get("state"))){
						loanList.get(i).put("state","未认证");
					} else if ("1".equals(loanList.get(i).get("state"))){
						loanList.get(i).put("state","已认证");
					} else if ("2".equals(loanList.get(i).get("state"))){
						loanList.get(i).put("state","认证中");
					} else if ("3".equals(loanList.get(i).get("state"))){
						loanList.get(i).put("state","审核中");
					} else if ("4".equals(loanList.get(i).get("state"))){
						loanList.get(i).put("state","未通过");
					}
				}
				loanLists.addAll(loanList);
			}else{
				break;
			}
			offset++;
		}

		String filename = "用户管理"+DateUtil.getCurTimestampStr()+CodeUtil.get4Code()+".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(),"iso-8859-1"));
		OutputStream out = response.getOutputStream();
		ExcelExportUtil4DIY.exportToFile("用户管理",loanLists,
				new String[]{"企业ID","渠道","渠道企业ID","企业名称","统一信用代码","联系人","联系人手机","担保人","担保人手机","状态","申请时间","终审时间"},
				new String[]{"eid","channel","ceid","ename","creditcode","linkman","linkphone","guarant","guarantphone","state","applytime","lastinstancetime"},out);
	}*/

	/*审核总览下载*/
	@RequestMapping("/DownCheExcel")
	public void CheExcel(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String, Object> params) throws Exception {
		Map<String,Object> map=new HashMap<>();
		map.put("eid",StringUtil.formatLike(request.getParameter("eid")));
		map.put("ename", StringUtil.formatLike(request.getParameter("ename")));

		if (!"".equals(params.get("chackNo")) && params.get("chackNo") != null) {
			map.put("chackNo", StringUtil.formatLike(String.valueOf(params.get("chackNo"))));
		}
		if (!"".equals(params.get("realname")) && params.get("realname") != null) {
			map.put("realname", StringUtil.formatLike(String.valueOf(params.get("realname"))));
		}
		if (!"".equals(params.get("mobile")) && params.get("mobile") != null) {
			map.put("mobile", String.valueOf(params.get("mobile")));
		}
		if (!"".equals(params.get("chackType")) && params.get("chackType") != null) {
			map.put("chackType", String.valueOf(params.get("chackType")));
		}
		if (!"".equals(params.get("chackState")) && params.get("chackState") != null) {
			String chackState = params.get("chackState").toString();
			if (chackState.equals("101") || chackState.equals("102")) {
				map.remove("chackState");
				if (chackState.equals("101")) {
					map.put("chackState1", "chackState1");
				}
				if (chackState.equals("102")) {
					map.put("chackState2", "chackState2");
				}
			} else {
				map.put("chackState", String.valueOf(params.get("chackState")));
			}
		}
		if (!"".equals(params.get("applyTimeStart")) && params.get("applyTimeStart") != null) {
			map.put("applyTimeStart", String.valueOf(params.get("applyTimeStart")));
		}
		if (!"".equals(params.get("applyTimeEnd")) && params.get("applyTimeEnd") != null) {
			map.put("applyTimeEnd", String.valueOf(params.get("applyTimeEnd")));
		}
		if (!"".equals(params.get("lastTimeStart")) && params.get("lastTimeStart") != null) {
			map.put("lastTimeStart", String.valueOf(params.get("lastTimeStart")));
		}
		if (!"".equals(params.get("lastTimeEnd")) && params.get("lastTimeEnd") != null) {
			map.put("lastTimeEnd", String.valueOf(params.get("lastTimeEnd")));
		}
		if (!"".equals(params.get("chackResult")) && params.get("chackResult") != null) {
			map.put("chackResult", String.valueOf(params.get("chackResult")));
		}
		if (!"".equals(params.get("valid")) && params.get("valid") != null) {
			map.put("valid", String.valueOf(params.get("valid")));
		}


		ArrayList<HashMap<String, Object>> loanLists = new ArrayList<> ();
		int offset = 0;
		int limit = 1000;
		while(true){
			map.put("offset", offset*limit);
			map.put("limit", limit);
			Query query = new Query(map);
			List<HashMap<String,Object>> loanList = checkService.CheExcelOnes(query);
			if(loanList.size() <= 0){
				break;
			}else {
				loanList = this.fromTable(loanList);

				for (HashMap loanListe:loanList) {
					String chackState = loanListe.get("chackState").toString();
					switch (chackState){
						case "0":
							loanListe.remove("chackState");
							loanListe.put("chackState","待初审");
							break;
						case "1":
							loanListe.remove("chackState");
							loanListe.put("chackState","初审挂起");
							break;
						case "2":
							loanListe.remove("chackState");
							loanListe.put("chackState","待复审");
							break;
						case "3":
							loanListe.remove("chackState");
							loanListe.put("chackState","复审挂起");
							break;
						case "4":
							loanListe.remove("chackState");
							loanListe.put("chackState","复审退回");
							break;
						case "5":
							loanListe.remove("chackState");
							loanListe.put("chackState","已完成");
							break;
						default:
							loanListe.put("chackState","");
							break;
					}
					String chackType = loanListe.get("chackType").toString();
					switch (chackType){
						// 认证类型
						case "01":
							loanListe.remove("chackType");
							loanListe.put("chackType","自动认证");
							break;
						case "02":
							loanListe.remove("chackType");
							loanListe.put("chackType","联系信息");
							break;
						case "03":
							loanListe.remove("chackType");
							loanListe.put("chackType","手机认证");
							break;
						case "04":
							loanListe.remove("chackType");
							loanListe.put("chackType","淘宝认证");
							break;
						case "05":
							loanListe.remove("chackType");
							loanListe.put("chackType","社保认证");
							break;
						default:
							loanListe.put("chackType","");
							break;
					}
					String result = loanListe.get("result").toString();
					switch (result){
						// 初审核结果
						case "0":
							loanListe.remove("result");
							loanListe.put("result","拒绝");
							break;
						case "1":
							loanListe.remove("result");
							loanListe.put("result","通过");
							break;
						case "2":
							loanListe.remove("result");
							loanListe.put("result","拒绝");
							break;
						case "3":
							loanListe.remove("result");
							loanListe.put("result","初审挂起");
							break;
						default:
							loanListe.put("result","");
							break;
					}
					String results = loanListe.get("results").toString();
					switch (results){
						// 复审核结果
						case "0":
							loanListe.remove("results");
							loanListe.put("results","拒绝");
							break;
						case "1":
							loanListe.remove("results");
							loanListe.put("results","通过");
							break;
						case "2":
							loanListe.remove("results");
							loanListe.put("results","复审退回");
							break;
						case "3":
							loanListe.remove("results");
							loanListe.put("results","复审挂起");
							break;
						default:
							loanListe.put("results","");
							break;
					}
					String chackResult = loanListe.get("chackResult").toString();
					switch (chackResult){
						// 审核结果
						case "0":
							loanListe.remove("chackResult");
							loanListe.put("chackResult","审核中");
							break;
						case "1":
							loanListe.remove("chackResult");
							loanListe.put("chackResult","通过");
							break;
						case "2":
							loanListe.remove("chackResult");
							loanListe.put("chackResult","拒绝");
							break;
						default:
							loanListe.put("chackResult","");
							break;
					}

					String applyTime = loanListe.get("applyTime").toString();
					String lastTime = loanListe.get("lastTime").toString();
					if ("".equals(lastTime) || lastTime == null){
						loanListe.put("valid","");
					}else {
						SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						try{
							Date applyDate = sdf.parse(applyTime);
							Date lastDate = sdf.parse(lastTime);
							String valid = getDatePoor(lastDate,applyDate);
							loanListe.put("valid",valid);
						}catch (Exception e){
							e.printStackTrace();
						}
					}
					if ("".equals(loanListe.get("isSystems")) || loanListe.get("isSystems") == null){
                        loanListe.put("names","");
                    }else {
                        String isSystems = loanListe.get("isSystems").toString();
                        if ("1".equals(isSystems)){
                            loanListe.put("names","系统审核");
                        }else{
                            loanListe.put("names",loanListe.get("names").toString());
                        }
                    }
                    if ("".equals(loanListe.get("isSystem")) || loanListe.get("isSystem") == null){
                        loanListe.put("name","");
                    }else {
                        String isSystem = loanListe.get("isSystem").toString();
                        if ("1".equals(isSystem)){
                            loanListe.put("name","系统审核");
                        }else{
                            loanListe.put("name",loanListe.get("name").toString());
                        }
                    }
					loanLists.add(loanListe);
				}
			}
			offset++;
		}
		String filename = "信审总览"+DateUtil.getCurTimestampStr()+CodeUtil.get4Code()+".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(),"iso-8859-1"));
		OutputStream out = response.getOutputStream();
		/*StringBuffer filenameBuffer = new StringBuffer().append("审核总览")
				.append(DateUtil.getCurTimestampStr() + CodeUtil.get4Code())
				.append(".xls");*/
		/*String filename = filenameBuffer.toString();
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(),"utf-8"));
		OutputStream out = response.getOutputStream();*/
		ExcelExportUtil4DIY.exportToFile("审核总览",loanLists,
				new String[]{"信审编号","姓名","手机","认证类型","审核状态","申请时间","完成时间","审核结果","审核时效(h)","初审时间","初审结果","初审人","复审时间","复审人","复审结果"},
				new String[]{"chackNo","realname","mobile","chackType","chackState","applyTime","lastTime","chackResult","valid","time","result","name","times","names","results"},out);
	}

	private List<HashMap<String,Object>> fromTable(List<HashMap<String,Object>> hashMaps) {
		for (Map map : hashMaps) {
			if (map.get("chackNo") == null){map.put("chackNo", "");}else{map.put("chackNo", map.get("chackNo"));}
			if (map.get("realname") == null){map.put("realname", "");}else{map.put("realname", map.get("realname"));}
			if (map.get("mobile") == null){map.put("mobile", "");}else{map.put("mobile", map.get("mobile"));}
			if (map.get("chackType") == null){map.put("chackType", "");}else{map.put("chackType", map.get("chackType"));}
			if (map.get("chackState") == null){map.put("chackState", "");}else{map.put("chackState", map.get("chackState"));}
			if (map.get("applyTime") == null){map.put("applyTime", "");}else{map.put("applyTime", map.get("applyTime"));}
			if (map.get("lastTime") == null){map.put("lastTime", "");}else{map.put("lastTime", map.get("lastTime"));}
			if (map.get("chackResult") == null){map.put("chackResult", "");}else{map.put("chackResult", map.get("chackResult"));}
			if (map.get("valid") == null){map.put("valid", "");}else{map.put("valid", map.get("valid"));}
			if (map.get("time") == null){map.put("time", "");}else{map.put("time", map.get("time"));}
			if (map.get("result") == null){map.put("result", "");}else{map.put("result", map.get("result"));}
			if (map.get("name") == null){map.put("name", "");}else{map.put("name", map.get("name"));}
			if (map.get("times") == null){map.put("times", "");}else{map.put("times", map.get("times"));}
			if (map.get("results") == null){map.put("results", "");}else{map.put("results", map.get("results"));}
			if (map.get("names") == null){map.put("names", "");}else{map.put("names", map.get("names"));}
		}

		return hashMaps;
	}
	public static String getDatePoor(Date endDate, Date nowDate) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		long n0 = 1000;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		String timeStr = "";
		if (diff<=nm){
			timeStr = "0.02";
		}else if (nm<diff){
			// 计算差多少小时
			timeStr= BigDecimalUtil.BigDecimalDivide(diff+"",1000 * 60 * 60 + "",2);
			// 计算差多少分钟
			/*timeStr= BigDecimalUtil.BigDecimalDivide(diff+"",1000 * 60 + "",2);*/
		}
		return timeStr;
	}
	/**
	 * 账户流水下载
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	/*@RequestMapping("/DownFlowExcel")
	@ResponseBody
	public void DownFlowExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> map=new HashMap<>();
		map.put("ceid",StringUtil.formatLike(request.getParameter("ceid")));
		map.put("ename", StringUtil.formatLike(request.getParameter("ename")));
		map.put("relevantorder",StringUtil.formatLike(request.getParameter("relevantorder")));
		map.put("bustype",request.getParameter("bustype"));
		map.put("starTime", request.getParameter("starTime"));
		map.put("endTime", request.getParameter("endTime"));
		ArrayList<HashMap<String, Object>> loanLists = new ArrayList<> ();
		int offset = 0;
		int limit = 1000;
		while(true){
			map.put("offset", offset*limit);
			map.put("limit", limit);
			List<HashMap<String,Object>> loanList = flowService.FloExcel(map);
			if(loanList.size() > 0){
				for (int i = 0;i<loanList.size();i++){
					if("01".equals(loanList.get(i).get("bustype"))){
						loanList.get(i).put("bustype","借款");
					}
					else if ("02".equals(loanList.get(i).get("bustype"))){
						loanList.get(i).put("bustype","还款");
					}
					else if ("03".equals(loanList.get(i).get("bustype"))){
						loanList.get(i).put("bustype","授信");
					}

					if("01".equals(loanList.get(i).get("channel"))){
						loanList.get(i).put("channel","嘉薪平台");
					}
					else if("02".equals(loanList.get(i).get("channel"))){
						loanList.get(i).put("channel","嘉福平台");
					}
				}
				loanLists.addAll(loanList);
			}else{
				break;
			}
			offset++;
		}

		String filename = "账户流水"+DateUtil.getCurTimestampStr()+CodeUtil.get4Code()+".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(),"iso-8859-1"));
		OutputStream out = response.getOutputStream();
		ExcelExportUtil4DIY.exportToFile("账户流水",loanLists,
				new String[]{"交易流水号","用户渠道","渠道企业ID","企业名称","交易金额","交易后授信额度","交易后可用授信余额","业务类型","白条订单号","交易时间"},
				new String[]{"tradef发送内容low","channel","ceid","ename","amount","creditlimit","usablelimit","bustype","accountno","createtime"},out);
	}


	*//**
	 * 还款下载
	 * @param request
	 * @param response
	 * @throws Exception
	 *//*
	@RequestMapping("/DownRepExcel")
	public void RepExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> map=new HashMap<>();
		map.put("cflow",StringUtil.formatLike(request.getParameter("cflow")));
		map.put("repaynum", StringUtil.formatLike(request.getParameter("repaynum")));
		map.put("loannum",StringUtil.formatLike(request.getParameter("loannum")));
		map.put("starTime", request.getParameter("starTime"));
		map.put("endTime", request.getParameter("endTime"));
		ArrayList<HashMap<String, Object>> loanLists = new ArrayList<> ();
		int offset = 0;
		int limit = 1000;
		while(true){
			map.put("offset", offset*limit);
			map.put("limit", limit);
			List<HashMap<String,Object>> loanList = iRepay.ReExcel(map);
			if(loanList.size() > 0){
				for (int i = 0;i<loanList.size();i++){
					if("0".equals(loanList.get(i).get("repaytype"))){
						loanList.get(i).put("repaytype","系统代扣");
					}
					else if ("1".equals(loanList.get(i).get("repaytype"))){
						loanList.get(i).put("repaytype","用户主动");
					}

					if("01".equals(loanList.get(i).get("channel"))){
						loanList.get(i).put("channel","嘉薪平台'");
					}
					else if ("02".equals(loanList.get(i).get("repaytype"))){
						loanList.get(i).put("channel","嘉福平台");
					}
				}
				loanLists.addAll(loanList);
			}else{
				break;
			}
			offset++;
		}

		String filename = "还款订单"+DateUtil.getCurTimestampStr()+CodeUtil.get4Code()+".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(),"iso-8859-1"));
		OutputStream out = response.getOutputStream();
		ExcelExportUtil4DIY.exportToFile("账户流水",loanLists,
				new String[]{"还款订单号","用户渠道","渠道企业ID","企业名称","本金还款","手续费还款","逾期费用还款","还总额","还款时间","还款方式","借款订单号","渠道流水号"},
				new String[]{"repaynum","channel","ceid","ename","ramount","rproccost","rovercost","stayamountsum","repaydate","repaytype","loannum","cflow"},out);
	}*/

}
