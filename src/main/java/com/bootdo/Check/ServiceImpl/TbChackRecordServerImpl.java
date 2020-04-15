package com.bootdo.Check.ServiceImpl;

import com.bootdo.Cdomain.domain.TradingFlowEntity;
import com.bootdo.Check.Service.ITbChackRecordServer;
import com.bootdo.Check.Service.ITbChackService;
import com.bootdo.Check.dao.TbChackRecordDao;
import com.bootdo.Check.domain.TbAccountInfo;
import com.bootdo.Check.domain.TbChack;
import com.bootdo.Check.domain.TbChackRecord;
import com.bootdo.Check.domain.dto.TbChackDto;
import com.bootdo.Loan.service.JXInterService;
import com.bootdo.QuotaAd.Service.QuotaAdjustmentService;
import com.bootdo.Utils.UserChannelType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional(rollbackFor = Exception.class)
public class TbChackRecordServerImpl implements ITbChackRecordServer {

    @Autowired
    private TbChackRecordDao tbChackRecordDao;
    @Autowired
    private ITbChackService tbChackService;
    @Autowired
    private QuotaAdjustmentService quotaAdjustmentService;
    @Autowired
    private JXInterService jxInterService;

    @Override
    public List<TbChackRecord> get(TbChackRecord tbChackRecord) {
        return tbChackRecordDao.get(tbChackRecord);
    }

    @Override
    public TbChackRecord find(String id) {
        return tbChackRecordDao.find(id);
    }

    @Override
    public int insert(TbChackRecord tbChackRecord) throws Exception {
        return tbChackRecordDao.insert(tbChackRecord);
    }

    @Override
    public int update(TbChackRecord tbChackRecord) throws Exception {
        return tbChackRecordDao.update(tbChackRecord);
    }

    @Override
    public int delete(String id) throws Exception{
        return tbChackRecordDao.delete(id);
    }

    @Override
    public TbChackRecord queryTbChackRecord(Map<String, Object> hashMap) {
        return tbChackRecordDao.queryTbChackRecord(hashMap);
    }

    @Override
    @Transactional
    public boolean saveTbChackRecord(TbChackDto tbChackDto) throws Exception {
        Map<String, Object> hashMap = new HashMap<>();
        /* 封装数据 */
        TbChackRecord tbChackRecord = this.sealTbChackRecord(tbChackDto);
        String chackNo = tbChackDto.getTbChack().getChackNo();
        Map<String, String> chackMap = tbChackService.queryChackMap(chackNo);
        /* 1.初审  2.复审 */
        if(tbChackDto.getTbChack().getChoose() == 1){
            hashMap.put("chackNo",tbChackDto.getTbChack().getChackNo());
            hashMap.put("type","01");
            TbChackRecord cTbChackRecord = this.queryTbChackRecord(hashMap);
            if(cTbChackRecord != null){
                tbChackRecord.setChackRecordId(cTbChackRecord.getChackRecordId());
                this.update(tbChackRecord);
            }else {
                this.insert(tbChackRecord);
            }

            /* 封装修改信审表 信审状态 */
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("chackNo",tbChackDto.getTbChack().getChackNo());
            //初审结果通过或者拒绝，tb_chack表中的chack_state的状态都是待复审，如果是挂起，那么chack_state状态也是初审挂起
             //chack_result 审核结果都是审核中
            if (tbChackDto.getResult().equals("0")){
                map.put("userState","2");
            }else if (tbChackDto.getResult().equals("1")){
                map.put("userState","2");
            }else if (tbChackDto.getResult().equals("3")){
                map.put("userState","1");
            }
            map.put("chackResultFlag","falg");
            tbChackService.updateState(map);
        }else{
            hashMap.put("chackNo",tbChackDto.getTbChack().getChackNo());
            hashMap.put("type","02");
            TbChackRecord fTbChackRecord = this.queryTbChackRecord(hashMap);

            if(fTbChackRecord != null){
                tbChackRecord.setChackRecordId(fTbChackRecord.getChackRecordId());
                this.update(tbChackRecord);
            }else {
                this.insert(tbChackRecord);
            }

            /* 封装修改信审表 信审状态 */
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("chackNo",tbChackDto.getTbChack().getChackNo());
            if (tbChackDto.getResult().equals("0")){
                //复审拒绝的话修改信审表中的审核结果为已拒绝，审核状态为已完成，修改账户表认证状态为未通过
                map.put("userState","5");
                map.put("chackResult","2");
                map.put("lastTime","lastTime");//审核完成时间
                // 认证类型
                String chackType = chackMap.get("chackType");
                if ("01".equals(chackType)) {
                    //根据账户编号更改用户状态为未通过4
                    tbChackService.updateAccountNo(chackMap.get("accountNo"), "4");
                }
            }else if (tbChackDto.getResult().equals("1")){
                //复审通过。修改信审表审核状态为已完成，审核结果为通过，
                map.put("userState","5");
                map.put("chackResult","1");
                Date d=new Date();
                SimpleDateFormat  s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String lastTime= s.format(d);
                //chackMap 为空抛出异常
                if (chackMap == null){
                    throw new Exception("chackMap为空");
                }
                Map<String, String> accountMap = tbChackService.queryAccountMap(chackMap.get("accountNo"));
                if (accountMap == null) {
                    throw new Exception("accountMap为空");
                }
                // 认证类型
                String chackType = chackMap.get("chackType");
                Map<String, String> chackTypeMap = tbChackService.queryChackTypeMap(chackType);
                if (chackTypeMap == null) {
                    throw new Exception("chackTypeMap为空");
                }


                String amount = "0";
                //防止chackTypemap为空  ， 出现空指针异常
                if (chackTypeMap != null ){
                    amount = chackTypeMap.get("creditLine");
                }
                String transType = "";
                String vipLevel = "";
                //交易类型('11'.手动还款,'12'.自动还款,'21'.消费分期,'31'.取现分期,'32'.信用卡分期,'41'.取现退款,'42'.信用卡退款,'51'.授信,'52'.小额自动授信,'53'.联系信息授信,'54'.手机认证授信,'61'.预支还款)
                if ("01".equals(chackType)){
                    transType = "52";
                    vipLevel = "1";
                    //更新用户状态及时间
                    tbChackService.updateAccountNo(chackMap.get("accountNo"),"1");
                    //获取手机号
                    Map<String, String> userMobile =tbChackService.getMobile(chackNo);
                    if (userMobile == null) {
                    }else {
                        String mobile = userMobile.get("mobile");
                        String signName = UserChannelType.getName(userMobile.get("userChannelId"));
                        String h5Name = UserChannelType.getName(signName);
                        //发送短信通知
                        jxInterService.sendMessages(mobile, "免费激活成功，可随时使用，信用生活触手可及【"+h5Name+"】。", signName);
                    }
                }else {
                    Map<String, Object> map1 = new HashMap<String, Object>();
                    map1.put("accountNo",chackMap.get("accountNo"));
                    map1.put("chackResult","1");
                    map1.put("chackState","5");
                    map1.put("chackTypes","chackTypes");
                    List<TbChack>  chacks =  tbChackService.list(map1);
                    if(chacks==null ||chacks.size() == 0) {
                        vipLevel = "2";
                    } else if(chacks.size() == 1){
                        vipLevel = "3";
                    } else if(chacks.size() == 2){
                        vipLevel = "4";
                    }


                    if ("02".equals(chackType)) {
                        transType = "53";
                    } else if ("03".equals(chackType)) {
                        transType = "54";
                    } else if ("04".equals(chackType)) {
                        transType = "55";
                    } else if ("05".equals(chackType)) {
                        transType = "56";
                    }
                }
                TbAccountInfo accountInfo = tbChackService.getCardid(tbChackDto.getTbChack().getChackNo());
                if(accountInfo == null){
                    throw new Exception("accountInfo为空");
                }
                if(Integer.parseInt(accountInfo.getVipLevel())<Integer.parseInt(vipLevel)){
                    //增加会员等级
                    TbAccountInfo tbAccountInfo = new TbAccountInfo();
                    tbAccountInfo.setAccountNo(chackMap.get("accountNo"));
                    tbAccountInfo.setVipLevel(vipLevel);
                    tbChackService.updateLice(tbAccountInfo);
                }

                //获得授信额度和可用授信额度
                String creditLimit = "0";
                String usableLimit = "0";
                if (accountMap != null ){
                    //更新前授信额度
                    creditLimit=accountMap.get("creditLimit");
                    //更新前可用额度
                    usableLimit=accountMap.get("usableLimit");
                }

                //调额后授信额度
                String creditLimit1= BigDecimalAdd(creditLimit,amount);  System.out.println("creditLimit为"+creditLimit1);
                //调额后可用授信额度
                String  usableLimit1= BigDecimalAdd(usableLimit,amount); System.out.println("usableLimit1为"+usableLimit1);
                //更新授信额度
                quotaAdjustmentService.updateGrantAccountInf(creditLimit1, usableLimit1,lastTime, chackMap.get("accountNo")) ;
                //添加流水
                createFlow("01","",chackNo,"",
                        transType,chackMap.get("userId"),"","01",chackMap.get("accountNo"),
                        amount,"01","0",creditLimit1+"",usableLimit1,"1","0");
                //添加授信订单
                /*CreditOrder creditOrder =new CreditOrder();
                creditOrder.setCreditNum(chackNo);
                //具体意见
                creditOrder.setDes(tbChackDto.getOpinion());
                // 查询当前用户
                UserDO userDO = tbChackService.getUser(getUserId());
                if (userDO == null) {
                    userDO = new UserDO();
                }
                creditOrder.setOperateManId(userDO.getUserId().intValue());
                creditOrder.setOperateTime(lastTime);
                creditOrder.setState("1");
                quotaAdjustmentService.addCreditOrder(creditOrder);*/
                //添加可用授信额度
                map.put("creditLine",usableLimit1);
                //添加完成时间判断值
                map.put("lastTime","lastTime");
            }else if (tbChackDto.getResult().equals("2")){
                //复审为回退初审 ，修改信审表审核状态为复审回退，审核结果为审核中
                map.put("userState","4");
                map.put("chackResultFlag","flag"); //chackResult = 0
            }else if (tbChackDto.getResult().equals("3")){
                //复审为回退初审 ，修改信审表审核状态为复审挂起，审核结果为审核中
                map.put("userState","3");
                map.put("chackResultFlag","flag");//chackResult = 0
            }
            tbChackService.updateState(map);
        }
        return true;
    }

    /**
     * tbChackRecord数据封装
     * */
    public TbChackRecord sealTbChackRecord(TbChackDto tbChackDto){
        TbChackRecord tbChackRecord = new TbChackRecord();
        tbChackRecord.setChackNo(tbChackDto.getTbChack().getChackNo());
        tbChackRecord.setChackId(tbChackDto.getUserId());
        tbChackRecord.setChackName(tbChackDto.getUserName());
        tbChackRecord.setResult(tbChackDto.getResult());
        tbChackRecord.setOpinion(tbChackDto.getOpinion());
        if (tbChackDto.getTbChack().getChoose()==1){
            tbChackRecord.setType("01");
        }else{
            tbChackRecord.setType("02");
        }
        tbChackRecord.setIsSystem("0");
        return tbChackRecord;
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
     * 获取现在时间
     * @return返回字符串格式yyyyMMddHHmmss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
