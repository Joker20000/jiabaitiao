/*   
 * Copyright © 2014-2016 jia-fu.cn All Rights Reserved.
 *   
 * This software is the confidential and proprietary information of   
 * TAOLUE Co.,Ltd. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with TAOLUE Co.,Ltd.    
 *   
 */

package com.bootdo.Utils;

import java.util.UUID;

/**
 * @Description: TODO(说明)
 * @author LHG
 * @date 2016年6月26日
 */
public class CodeUtil {
	/**
	 * 生成6位随机数
	 * 
	 * @return
	 */
	public static String get6Code() {
		int code = (int) ((Math.random() * 9 + 1) * 100000);
		return code + "";
	}

	/**
	 * 生成4位随机数
	 * 
	 * @return
	 */
	public static String get4Code() {
		int code = (int) ((Math.random() * 9 + 1) * 1000);
		return code + "";
	}

	/**
	 * 获取uuid
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}


}
