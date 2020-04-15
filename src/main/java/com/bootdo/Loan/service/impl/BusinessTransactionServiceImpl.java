package com.bootdo.Loan.service.impl;

import com.bootdo.Loan.dao.BusinessTransactionDao;
import com.bootdo.Loan.domain.AmountsEntity;
import com.bootdo.Loan.domain.TradingFlowEntity;
import com.bootdo.Loan.service.BusinessTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * @Description: TODO
 * @author HWJ
 * @date 2017年12月29日
 * 业务交易接口
 */
@Service
public class BusinessTransactionServiceImpl implements BusinessTransactionService {

	private Logger LOG = LoggerFactory.getLogger(BusinessTransactionServiceImpl.class);

	@Autowired
	private BusinessTransactionDao businessTransactionDao;

	@Override
	public boolean changeAmount(AmountsEntity amountDetail) {
		String accountNo = amountDetail.getAccountNo();
		String account = amountDetail.getAmount();
		int type = 0;
		// 判断变动类型('01'.增加,'02'.减少)
		if ("01".equals(amountDetail.getAddAndSubtract())){
			type = businessTransactionDao.addUsableLimit(accountNo, account);
		}else if ("02".equals(amountDetail.getAddAndSubtract())){
			type = businessTransactionDao.subtractUsableLimit(accountNo, account);
		}else {
			LOG.info("没有对应的变动类型, 业务处理失败");
			return false;
		}
		if(type==1){
			//查询交易后账户额度情况
			Map<String,Object> usableLimitStr = businessTransactionDao.queryUsableLimitForLock(accountNo);
			//可用授信额度扣减成功，添加交易流水记录
			createFlow(amountDetail.getChannel(),amountDetail.getRelatedFlowId(),amountDetail.getBizid(),amountDetail.getChannelBizid(),
					amountDetail.getTransType(),amountDetail.getUserId(),amountDetail.getAccountId(),amountDetail.getType(),amountDetail.getAccountNo(),
					amountDetail.getAmount(),amountDetail.getAddAndSubtract(),"0",usableLimitStr.get("creditLimit")+"",usableLimitStr.get("usableLimit")+"","1","0");
		}else {
			LOG.info("扣减可用授信额度失败！可用授信余额不能小于0或者系统异常。业务订单号：[{}]、变动类型：[{}]",amountDetail.getBizid(),amountDetail.getAddAndSubtract());
			return false;
		}
		return true;
	}


	/**
	 * 添加交易流水记录
	 * @param channel			渠道('01'.嘉福,'02'.嘉福白条)
	 * @param relatedFlowId	关联系统流水编号
	 * @param bizid			业务订单号
	 * @param channelBizid	渠道订单号
	 * @param transType		交易类型('11'.手动还款,'12'.自动还款,'21'.消费分期,'31'.取现分期,'41'.退款,'51'.授信)
	 * @param userId			用户编号
	 * @param accountId		账号
	 * @param type				账户类型
	 * @param accountNo		账户
	 * @param amount			交易金额
	 * @param addAndSubtract	变动类型('01'.增加,'02'.减少)
	 * @param balance			交易后余额
	 * @param creditLimit		当前授信额度
	 * @param usableLimit		交易后可用授信余额
	 * @param tradingState    交易状态
	 * @param refundAmount	退款金额
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

		return businessTransactionDao.insertFlow(tradingFlowEntity) > 0;
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
}
