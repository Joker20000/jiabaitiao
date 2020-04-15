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
 * @Description: TODO(说明)
 * @author LHG
 * @date 2016年4月11日
 */
public class HttpClient {
	private static HttpHandler httpClient = new ApacheHttpHandler();

	public static HttpResponse post(HttpRequest request) throws Exception {
		return httpClient.post(request);
	}

	public static HttpResponse get(HttpRequest request) throws Exception {
		return httpClient.get(request);
	}

	public static HttpResponse stream(HttpRequest request) throws Exception {
		return httpClient.stream(request);
	}
	
	public static HttpResponse postJson(HttpRequest request) throws Exception {
		return httpClient.postJson(request);
	}
}
