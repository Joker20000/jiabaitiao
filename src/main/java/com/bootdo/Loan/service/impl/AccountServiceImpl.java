package com.bootdo.Loan.service.impl;

import com.bootdo.Loan.dao.AccountDao;
import com.bootdo.Loan.domain.AmountEntity;
import com.bootdo.Loan.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Random;
import java.util.UUID;


/**
 * @Description: TODO
 * @author HWJ
 * @date 2018年4月25日
 */
@Service
public class AccountServiceImpl implements AccountService {

    private Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDao accountDao;

    private Random random = new Random(UUID.randomUUID().hashCode());


    @Override
    public boolean open(String eid) {
        // 账号格式 2位随机数+8位毫秒数+8位序列(前补0)=18位
        String sequence = (System.currentTimeMillis()/100)+"";
        String time = System.currentTimeMillis() % 99999999 + "";
        String r = 10 + (int) (random.nextInt(90)) + "";
        String accountNo = new StringBuilder().append(r).append(time).append(sequence).toString();
        // 创建用户账号信息
        int state = accountDao.insertAccount(accountNo,eid);
        if (state > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean changeAmount(AmountEntity amount) {
        String accountNo = amount.getAccountNo();
        String account = amount.getAmount();
        int type = 0;
        // 判断变动类型('01'.增加,'02'.减少,'03'.更新额度)
        if ("01".equals(amount.getAddOrSub())){
            type = accountDao.addUsableLimit(accountNo, account);
        }else if ("02".equals(amount.getAddOrSub())){
            type = accountDao.subtractUsableLimit(accountNo, account);
        }else if ("03".equals(amount.getAddOrSub())){
            type = accountDao.updateUsableLimit(accountNo, account);
        }else {
            LOG.info("没有对应的变动类型, 业务处理失败");
            return false;
        }
        //查询交易后账户额度情况
        Map<String,String> accountLimit = accountDao.queryAccountLimit(accountNo);
        if(type>0){
            //可用授信额度扣减成功，添加交易流水记录
            createFlow(amount.getRelevantOrder(),amount.getAccountNo(),amount.getAmount(),amount.getAddOrSub(),amount.getBusType(),accountLimit.get("creditLimit"),accountLimit.get("usableLimit"),"1");
        }else {
            createFlow(amount.getRelevantOrder(),amount.getAccountNo(),amount.getAmount(),amount.getAddOrSub(),amount.getBusType(),accountLimit.get("creditLimit"),accountLimit.get("usableLimit"),"0");
            LOG.info("扣减可用授信额度失败！可用授信余额不能小于0或者系统异常。关联订单号：[{}]、变动类型：[{}]",amount.getRelevantOrder(),amount.getAddOrSub());
            return false;
        }
        return true;
    }

    /**
     * 添加交易流水记录
     * @param relevantOrder     关联订单号
     * @param accountNo          账户编号
     * @param amount             交易金额
     * @param addOrSub           变动类型('01'.增加,'02'.减少)
     * @param busType            业务类型('01'.借款,'02'.还款,'03'.授信)
     * @param creditLimit        交易后授信额度
     * @param usableLimit        交易后可用授信余额
     * @param state               交易状态('0'.失败,'1'.成功)
     */
    public void createFlow(String relevantOrder,String accountNo,String amount,String addOrSub,String busType,String creditLimit,String usableLimit,String state) {
        accountDao.insertFlow(relevantOrder,accountNo,amount,addOrSub,busType,creditLimit,usableLimit,state);
    }

}
