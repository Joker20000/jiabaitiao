package com.bootdo.Loan.service;


import com.alibaba.fastjson.JSONObject;

public interface JXInterService {
    /*
     *短信通知
     * @param phone         手机号
     * @param msg           发送内容
     * @param sign_name    平台名称
     */
    void sendMessages(String phone, String msg, String sign_name);

}

