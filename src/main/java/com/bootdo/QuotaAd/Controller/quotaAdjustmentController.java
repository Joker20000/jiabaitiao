package com.bootdo.QuotaAd.Controller;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bootdo.Cdomain.domain.CreditOrder;
import com.bootdo.Cdomain.domain.TradingFlowEntity;
import com.bootdo.Check.Service.ITbChackService;
import com.bootdo.Check.domain.TbAccountInfo;
import com.bootdo.QuotaAd.Service.QuotaAdjustmentService;
import com.bootdo.QuotaAd.domain.QuotaAdjustment;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * 额度调整Controller类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/quotaAdjustmentController")
public class  quotaAdjustmentController extends BaseController {

	@Autowired
	private QuotaAdjustmentService quotaAdjustmentService;
	@Autowired
	private ITbChackService tbChackService;
	//所有人额度列表
	@GetMapping("/quota")
	String quota() {
		return "QuotaAdjustMent/quotaAdjustment";
	}
	//额度调整弹窗
	@GetMapping("/quotaUp")
	ModelAndView QuotaUp(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params = new HashMap<String,Object>();
		String userId = request.getParameter("userId");
		if(!"".equals(userId) && userId != null){
			params.put("userId",userId );
			params.put("offset",0 );
			params.put("limit",1 );
		}
		Query query = new Query(params);
		List<QuotaAdjustment> QuotaAdjustmentList= quotaAdjustmentService.findQuotaAdjustmentByCondition(query);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("row",QuotaAdjustmentList.get(0));
		modelAndView.setViewName("QuotaAdjustMent/quotaAdjustmentRevise");
		return modelAndView;
	}
	//查询
	@ResponseBody
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<QuotaAdjustment> QuotaAdjustmentList= quotaAdjustmentService.findQuotaAdjustmentByCondition(query);
		Long total1= quotaAdjustmentService.findQuotaAdjustmentCountByCondition(query);
		int total= new Long(total1).intValue();
		PageUtils pageUtils = new PageUtils(QuotaAdjustmentList, total);
		return pageUtils;
	}




	//根据用户id获的用户基本信息

	@RequestMapping("/updateAuota")
	@ResponseBody
	public String updateAuota(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("请求参数为"+ JSON.toJSONString(request.getParameterMap()));
		Date d=new Date();
		SimpleDateFormat  s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastTime= s.format(d);

		String userId=request.getParameter("userId");   //用户id
		String state=request.getParameter("state1");    //0 增加1减少
		String quota=request.getParameter("quota");   //调整度
		String adjustReason=request.getParameter("adjustReason");    //调整原因
		String pwd=request.getParameter("pwd");    //密码
		String before =request.getParameter("beforeVipLevel");    //密码
		String after =request.getParameter("afterVipLevel");    //密码
		String uname= getUsername();  //当前操作人人员姓名
		//将获得密码加密与该用户加密后的密码进行比较，若通过则继续其它操作
		String pwd1= MD5Utils.encrypt(uname,pwd);

		Integer count=quotaAdjustmentService.isExistByPwdAndUname(pwd1, uname);
		Integer id=quotaAdjustmentService.findIdByPwdAndUname(pwd1, uname);
		if(count>0){
			//获得授信额度和可用授信额度
			Map<String,String>  map= quotaAdjustmentService.findUserInfoByUsertId(userId);
			String accountNo=  quotaAdjustmentService.getAccountNoByUserId(userId);
			//更新前授信额度
			String creditLimit=map.get("creditLimit");
			//更新前可用额度
			String  usableLimit=map.get("usableLimit");
			String creditLimit1 = null;
			String usableLimit1 = null;
			String addAndSubtract = null;
			//根据用户id获得账号
			String accountId=quotaAdjustmentService.getAccountIdByUserId(Integer.parseInt(userId));

			String quotaId=getQuota();//生成调额订单号
			if(!"-1".equals(after)&&!"".equals(after)){
				//修改用户等级
				TbAccountInfo tbAccountInfo = new TbAccountInfo();
				tbAccountInfo.setAccountNo(accountNo);
				tbAccountInfo.setVipLevel(after);
				tbChackService.updateLice(tbAccountInfo);
				creditLimit1 = creditLimit;
				usableLimit1 = usableLimit;
				addAndSubtract = "03";
			}
			if("0".equals(state)){   //增加

				//调额后授信额度
				 creditLimit1= BigDecimalAdd(creditLimit.toString(),quota);  System.out.println("creditLimit为"+creditLimit1);
				//调额后可用授信额度
				  usableLimit1= BigDecimalAdd(usableLimit.toString(),quota); System.out.println("usableLimit1为"+usableLimit1);
				//更新授信额度
				quotaAdjustmentService.updateGrantAccountInf(creditLimit1, usableLimit1,lastTime, accountNo) ;
				addAndSubtract = "01";

			}else if("1".equals(state)){
				//计算授信后授信额度及可用授信额度    注意授信额度>=0 否则不予以更新 返回更新失败
				//授信后授信额度
				 creditLimit1 = BigDecimalSubtract(creditLimit.toString(),quota); System.out.println("creditLimit11"+creditLimit1);
				//授信后可用授信额度
			     usableLimit1 = BigDecimalSubtract(usableLimit.toString(),quota); System.out.println("usableLimit11"+usableLimit1);
				if(Double.parseDouble(creditLimit1)<0){
					return "4";  //此处标识授信额度不能为负数，返回前端提示**************************
				}else{
					//更新授信额度
					quotaAdjustmentService.updateGrantAccountInf(creditLimit1, usableLimit1,lastTime, accountNo) ;
					addAndSubtract = "02";
				}

			}
			if("-1".equals(state) && "-1".equals(after) ) {
				return "2"; //2 选择调整额度方式
			}else{
				//添加流水
				createFlow("01","",quotaId,"",
						"51",userId,accountId,"01",accountNo,
						quota,addAndSubtract,"0",creditLimit1+"",usableLimit1,"1","0");
				//添加调额记录
				CreditOrder creditOrder =new CreditOrder();
				creditOrder.setCreditNum(quotaId);
				creditOrder.setDes(adjustReason);
				creditOrder.setOperateManId(id);
				creditOrder.setOperateTime(lastTime);
				creditOrder.setState("1");
				creditOrder.setBeforeVipLevel(before);
				if(!"-1".equals(after)) {
					creditOrder.setAfterVipLevel(after);
				}else
					creditOrder.setAfterVipLevel(before);
				quotaAdjustmentService.addCreditOrder(creditOrder);
			}

		}else{
			return "0";  //0 密码与当前登录用户密码不一致，请重新输入
		}
		return "1";//1 调整额度成功

	}

	/**
	 * BigDecimal加法
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static String BigDecimalAdd(String o1,String o2){
		BigDecimal bigDecimal=new BigDecimal(StringUtils.isBlank(o1)?"0":o1);
		BigDecimal bigDecimalNew=new BigDecimal(StringUtils.isBlank(o2)?"0":o2);
		return bigDecimal.add(bigDecimalNew).toString();
	}
	/**
	 * BigDecimal减法
	 * @param o1
	 * @param o2
	 * @return String
	 */
	public static String BigDecimalSubtract(String o1,String o2){
		BigDecimal bigDecimal=new BigDecimal(StringUtils.isBlank(o1)?"0":o1);
		BigDecimal bigDecimalNew=new BigDecimal(StringUtils.isBlank(o2)?"0":o2);
		return bigDecimal.subtract(bigDecimalNew).toString();
	}


	/**
	 * 由年月日时分秒+3位随机数
	 *
	 * 生成流水号
	 * @return
	 */
	public static String Getnum(){
		String t = getStringDate();
		int x=(int)(Math.random()*900)+100;
		String serial = t + x;
		return serial;
	}


	/**
	 * 由年月日时分秒+3位随机数
	 *
	 * 生成调额订单号
	 * @return
	 */
	public static String getQuota(){
		String t = getStringDate();
		int x=(int)(Math.random()*900)+100;
		String serial ="te"+ t + x;
		return serial;
	}
	/**
	 * 获取现在时间
	 * @return返回字符串格式yyyyMMddHHmmss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 添加交易流水记录
	 * @param channel			渠道('01'.嘉福,'02'.嘉薪)
	 * @param relatedFlowId	关联系统流水编号
	 * @param bizid			业务订单号
	 * @param channelBizid	渠道订单号
	 * @param transType		交易类型('11'.手动还款,'12'.自动还款,'21'.消费分期,'31'.取现分期,'41'.,退款,'51'.授信)
	 * @param userId			用户编号
	 * @param accountId		账号
	 * @param type				账户类型
	 * @param accountNo		账户
	 * @param amount			交易金额
	 * @param addAndSubtract	变动类型('01'.增加,'02'.减少)
	 * @param balance			交易后余额
	 * @param creditLimit		交易后授信额度
	 * @param usableLimit		交易后可用授信余额
	 * @param tradingState       交易状态
	 * @param refundAmount	         退款金额
	 * @return
	 */
	public boolean createFlow(String channel,String relatedFlowId,String bizid,
							  String channelBizid,String transType,String userId,String accountId,
							  String type,String accountNo,String amount,String addAndSubtract,String balance,
							  String creditLimit,String usableLimit,String tradingState,String refundAmount) {
		TradingFlowEntity tradingFlowEntity = new TradingFlowEntity();
		//系统流水编号
		tradingFlowEntity.setFlowId(Getnum());
		//渠道('01'.嘉福,'02'.嘉福白条)
		tradingFlowEntity.setChannel(channel);
		//关联系统流水编号
		tradingFlowEntity.setRelatedFlowId(relatedFlowId);
		//业务订单号
		tradingFlowEntity.setBizid(bizid);
		//渠道订单号
		tradingFlowEntity.setChannelBizid(channelBizid);
		String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		//交易日期
		tradingFlowEntity.setTransDate(date.substring(0, 8));
		//交易时间
		tradingFlowEntity.setTransTime(date);
		//交易类型('11'.还款,'12'.费用减免,'21'.消费,'22'.分期消费,'31'.转让,'41'.,逾期利息,'42'.正常利息,'51'.退款)
		tradingFlowEntity.setTransType(transType);
		//用户编号
		tradingFlowEntity.setUserId(userId);
		//账号
		tradingFlowEntity.setAccountId(accountId);
		//账户类型（'01'.贷记类型）
		tradingFlowEntity.setType(type);
		//账户
		tradingFlowEntity.setAccountNo(accountNo);
		//交易金额
		tradingFlowEntity.setAmount(amount);
		//变动类型('01'.增加,'02'.减少)
		tradingFlowEntity.setAddAndSubtract(addAndSubtract);
		//交易后余额
		tradingFlowEntity.setBalance(balance);
		//交易后可用额度
		tradingFlowEntity.setCreditLimit(creditLimit);
		//交易后可用授信余额
		tradingFlowEntity.setUsableLimit(usableLimit);
		//交易状态('0'.失败,'1'.成功)
		tradingFlowEntity.setTradingState(tradingState);
		//退款金额
		tradingFlowEntity.setRefundAmount(refundAmount);
		//接收时间
		tradingFlowEntity.setReceiveTime(new Date());
		//最后更新时间
		tradingFlowEntity.setLastTime(new Date());

		return quotaAdjustmentService.insertFlow(tradingFlowEntity) > 0;
	}



	/**
	 * 生成32位md5码
	 * @param password
	 * @return
	 */
	public static String md5Password(String password) {

		try {
			// 得到一个信息摘要器
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(password.getBytes());
			StringBuffer buffer = new StringBuffer();
			// 把每一个byte 做一个与运算 0xff;
			for (byte b : result) {
				// 与运算
				int number = b & 0xff;// 加盐
				String str = Integer.toHexString(number);
				if (str.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str);
			}

			// 标准的md5加密后的结果
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}

	}



}

