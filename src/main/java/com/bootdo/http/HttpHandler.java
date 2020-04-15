/*   
 * Copyright © 2014-2016 jia-fu.cn All Rights Reserved.
 *   
 * This software is the confidential and proprietary information of   
 * TAOLUE Co.,Ltd. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with TAOLUE Co.,Ltd.    
 *   
 */

package com.bootdo.http;

/**
 * @Description: http客户端抽象接口
 * @author LHG
 * @date 2016年3月23日
 */
public interface HttpHandler {

	HttpResponse post(HttpRequest request) throws Exception;

	HttpResponse get(HttpRequest request) throws Exception;

	HttpResponse stream(HttpRequest request) throws Exception;
	
	HttpResponse postJson(HttpRequest request) throws Exception;
	
}
