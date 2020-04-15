package com.bootdo.Check.ServiceImpl;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.Check.Service.CheckService;
import com.bootdo.Check.dao.ICheckDao;
import com.bootdo.Check.domain.CheExcel;
import com.bootdo.Check.domain.Checker;
import com.bootdo.Check.domain.Enter;
import com.bootdo.Utils.BigDecimalUtil;
import com.bootdo.Utils.ConfigConsts;
import com.bootdo.Utils.Tools;
import com.bootdo.http.HttpClient;
import com.bootdo.http.HttpRequest;
import com.bootdo.http.HttpResponse;
import com.bootdo.system.domain.UserDO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckServiceImpl implements CheckService {
    private static Logger logger = LoggerFactory.getLogger(CheckServiceImpl.class);

    @Autowired
    private ICheckDao iCheckDao;

    @Override
    public List<Checker> list(Map<String, Object> map) {
        return iCheckDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return iCheckDao.count(map);
    }

    @Override
    public Enter get(String chackno) {
        Enter enter = iCheckDao.get(chackno);
        return enter;
    }

    @Override
    public int insert(Enter student) {
        return iCheckDao.insert(student);
    }

    @Override
    public UserDO getUser(Long userId) {
        UserDO user = iCheckDao.getUser(userId);
        return user;
    }

    @Override
    public int update(Enter ente) {
        return iCheckDao.update(ente);
    }

    @Override
    public int insertHistory(Enter history) {
        return iCheckDao.insertHistory(history);
    }

    @Override
    public List<Enter> pictures(String chackno) {
        return iCheckDao.pictures(chackno);
    }

    @Override
    public String selAct(int eid) {
        return iCheckDao.selAct(eid);
    }

    @Override
    public void updateUserState(int eid, String state) {
        iCheckDao.updateUserState(eid, state);
    }

    @Override
    public Enter selFSF(String chackno, String type) {
        Enter selF = iCheckDao.selFSF(chackno, type);
        return selF;
    }

    @Override
    public List<HashMap<String, Object>> CheExcelOne(Map<String, Object> map) {
        return iCheckDao.CheExcelOne(map);
    }

    @Override
    public List<CheExcel> CheExcelTwo(Map<String, Object> map) {
        return iCheckDao.CheExcelTwo(map);
    }

    @Override
    public int addTime(int eid, String losedate) {
        return iCheckDao.addTime(eid, losedate);
    }

    @Override
    public Enter selParam() {
        return iCheckDao.selParam();
    }

    @Override
    public Enter selGroup(String chackno, String type) {
        return iCheckDao.selGroup(chackno, type);
    }

    @Override
    public int updateState(String chackno, String state) {
        return iCheckDao.updateState(chackno, state);
    }

    @Override
    public Map<String, Object> searchUserJFidByUserId(String accountNo) {
        return iCheckDao.searchUserJFidByUserId(accountNo);
    }

    public Map<String, Object> searchJFUserDataByJFid(String jfid) {
        JSONObject json = new JSONObject();
        Map<String, String> map = new HashMap<>();
        Map<String, Object> backMap = new HashMap<>();
        try {
            map.put("ident", ConfigConsts.ident);
            map.put("service", ConfigConsts.service);
            map.put("version", "1.0");
            map.put("format", "json");
            map.put("charset", "utf-8");
            map.put("biz_content", "{\"uid\":\"" + jfid + "\"}");
            map.put("extend_params", "1");
            map.put("timestamp", System.currentTimeMillis() / 1000 + "");
            map.put("sign_type", "md5");

            String content = Tools.sortMapForAcc(map);
            String sign = DigestUtils.md5Hex((content + ConfigConsts.secret).getBytes());
            logger.info("调用嘉福接口[用户充值数据]，排序后参数[{}]，SIGN[{}]", content, sign);
            map.put("biz_content", URLEncoder.encode("{\"uid\":\"" + jfid + "\"}", "utf-8"));
            map.put("sign", sign);
            logger.info("调用嘉福接口[用户充值数据]，请求前参数[{}]", map.toString());

            HttpRequest httpRequest = new HttpRequest(ConfigConsts.ident_url);
            httpRequest.setParameters(map);
            HttpResponse httpResponse = HttpClient.get(httpRequest);
            String result = httpResponse.getStringResult();
            logger.info("调用嘉福接口[用户充值数据]，请求后参数[{}]", result);

            if (StringUtils.isNotBlank(result)) {
                json = JSONObject.parseObject(result);
                if (StringUtils.isNotBlank(json + "")) {
                    if ("0000".equals(json.get("code"))) {
                        String data = json.get("biz_data") + "";
                        if (StringUtils.isNotBlank(data)) {
                            JSONObject datajson = JSONObject.parseObject(data);
                            if ("0000".equals(datajson.get("biz_code"))) {//只有code为0000时才有数据
                                String summaryAmt = datajson.get("recharge_amt") + "";
                                String money = summaryAmt.replaceAll("[\\[\\]]", "");
                                String[] amt = money.split(",");
                                String monthNum1 = Double.valueOf(amt[0].trim())>1 ? BigDecimalUtil.bigDev(Double.valueOf(amt[0].trim()), 100,2)+"" : "0.00";
                                String monthNum2 = Double.valueOf(amt[1].trim())>1 ? BigDecimalUtil.bigDev(Double.valueOf(amt[1].trim()), 100,2)+"" : "0.00";
                                String monthNum3 = Double.valueOf(amt[2].trim())>1 ? BigDecimalUtil.bigDev(Double.valueOf(amt[2].trim()), 100,2)+"" : "0.00";
                                String monthNum4 = Double.valueOf(amt[3].trim())>1 ? BigDecimalUtil.bigDev(Double.valueOf(amt[3].trim()), 100,2)+"" : "0.00";
                                String monthNum5 = Double.valueOf(amt[4].trim())>1 ? BigDecimalUtil.bigDev(Double.valueOf(amt[4].trim()), 100,2)+"" : "0.00";

                                String summary = BigDecimalUtil.BigDecimalAdd(BigDecimalUtil.BigDecimalAdd(monthNum1 , monthNum2 ), BigDecimalUtil.BigDecimalAdd(monthNum3 , monthNum4));
                                String summary1 = BigDecimalUtil.BigDecimalAdd(summary, monthNum5);
                                logger.info("当前月[{}]，前一个月[{}]，前2个月[{}]，前3个月[{}]，前4个月[{}]", monthNum1, monthNum2, monthNum3, monthNum4, monthNum5);
                                logger.info("累计[{}]", summary1);

                                backMap.put("monthNum1", monthNum1);//当前月
                                backMap.put("monthNum2", monthNum2);//前一个月
                                backMap.put("monthNum3", monthNum3);//前2个月
                                backMap.put("monthNum4", monthNum4);//前3个月
                                backMap.put("monthNum5", monthNum5);//前4个月
                                backMap.put("summary", summary1);//合计

                            } else {
                                backMap.put("monthNum1", "0");//当前月
                                backMap.put("monthNum2", "0");//前一个月
                                backMap.put("monthNum3", "0");//前2个月
                                backMap.put("monthNum4", "0");//前3个月
                                backMap.put("monthNum5", "0");//前4个月
                                backMap.put("summary", "0");//合计
                            }
                        } else {
                            backMap.put("monthNum1", "0");//当前月
                            backMap.put("monthNum2", "0");//前一个月
                            backMap.put("monthNum3", "0");//前2个月
                            backMap.put("monthNum4", "0");//前3个月
                            backMap.put("monthNum5", "0");//前4个月
                            backMap.put("summary", "0");//合计
                        }
                    } else {
                        backMap.put("monthNum1", "0");//当前月
                        backMap.put("monthNum2", "0");//前一个月
                        backMap.put("monthNum3", "0");//前2个月
                        backMap.put("monthNum4", "0");//前3个月
                        backMap.put("monthNum5", "0");//前4个月
                        backMap.put("summary", "0");//合计
                    }
                } else {
                    backMap.put("monthNum1", "0");//当前月
                    backMap.put("monthNum2", "0");//前一个月
                    backMap.put("monthNum3", "0");//前2个月
                    backMap.put("monthNum4", "0");//前3个月
                    backMap.put("monthNum5", "0");//前4个月
                    backMap.put("summary", "0");//合计
                }
            }
        } catch (Exception e) {
            logger.error("调用嘉福接口[用户充值数据] Error", e);
            backMap.put("monthNum1", "0");//当前月
            backMap.put("monthNum2", "0");//前一个月
            backMap.put("monthNum3", "0");//前2个月
            backMap.put("monthNum4", "0");//前3个月
            backMap.put("monthNum5", "0");//前4个月
            backMap.put("summary", "0");//合计
        }
        return backMap;
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
    }
}
