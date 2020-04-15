package com.bootdo.Loan.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.Loan.service.JXInterService;
import com.bootdo.Utils.ConfigConsts;
import com.bootdo.Utils.DateUtil;
import com.bootdo.http.HttpClient;
import com.bootdo.http.HttpRequest;
import com.bootdo.http.HttpResponse;
import com.taolue.platform.open.sdk.api.OpenApiSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * 嘉薪对应接口
 */

@Service
public class JXInterServiceImpl implements JXInterService {

    private Logger LOG = LoggerFactory.getLogger(JXInterServiceImpl.class);


    public void sendMessages(String phone, String msg, String sign_name) {
        String msg_id = DateUtil.getCurTimestampStr();
        Map<String,String> msgMap = new HashMap<>();
        msgMap.put("msg",msg);
        msgMap.put("phone",phone);
        msgMap.put("sign_name",sign_name);

        JSONObject content = new JSONObject();
        content.put("msg_body",msgMap);
        content.put("msg_id",msg_id);
        //信息通知
        OpenApiSDK.init(ConfigConsts.KFPT_IDENT,ConfigConsts.KFPT_SECRET,ConfigConsts.KFPT_APIURL);
        String url = OpenApiSDK.build("pf.msg.sms.send",content.toJSONString());
        LOG.info("信通知地址:[{}]",url);
        HttpResponse response = null;
        try {
            response = HttpClient.post(new HttpRequest(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == response) {
            LOG.info("远端访问无返回");
        }
        // 解析返回值, code='0000'代表成功，其他均为失败
        JSONObject jsonObject = JSON.parseObject(response.getStringResult());
        LOG.info("real name result -> {}", jsonObject);
    }
}

