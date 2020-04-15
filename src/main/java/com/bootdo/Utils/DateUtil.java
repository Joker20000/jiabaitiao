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

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 日期工具类
 * @author LHG
 * @date 2016年5月15日
 */
public class DateUtil {
	final static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	final static SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyyMM");
	final static SimpleDateFormat HHmmss = new SimpleDateFormat("HHmmss");
	final static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	final static SimpleDateFormat yyyyMMddDate = new SimpleDateFormat("yyyy-MM-dd");
	final static SimpleDateFormat yyyyMMddHHmmssDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	final static SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyyMMddHHmm");
	
	public static String getHHmmss() {
		return HHmmss.format(new Date());
	}
	
	public static String getyyyyMMdd() {
		return yyyyMMdd.format(new Date());
	}
	public static String getyyyyMMdd(Date  date) throws ParseException {
		return yyyyMMdd.format(date);
	}
	public static String getyyyyMMddHHmmss() {
		return yyyyMMddHHmmss.format(new Date());
	}
	public static String getyyyyMMddHHmmss(Date date) {
		return yyyyMMddHHmmss.format(date);
	}
	public static String getyyyyMMddHHmm() {
		return yyyyMMddHHmm.format(new Date());
	}
	public static String getyyyyMMddDate(Date  date) throws ParseException {
		return yyyyMMddDate.format(date);
	}
	public static String getyyyyMMDate(Date  date) throws ParseException {
		return yyyyMM.format(date);
	}
	public static String getyyyyMMddHHmmssString(Date date) {
		return yyyyMMddHHmmssDate.format(date) ;
	}
	public static Date getyyyyMMddHHmmssDate(String  date) throws ParseException {
		return yyyyMMddHHmmssDate.parse(date) ;
	}
	
	
	
	public static Date getyyyyMMddDate() throws ParseException {
		return yyyyMMddDate.parse(yyyyMMddDate.format(new Date()));
	}

	public static Date getyyyyMMddHHmmssDate(Date updateDate) throws ParseException {
		return yyyyMMddHHmmssDate.parse(yyyyMMddHHmmssDate.format(new Date())) ;
	}
	/**
	 * 根据指定日期格式将给出的日期字符串dateStr转换成一个日期对象
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String dateStr, String pattern) {
		if (dateStr == null || dateStr.length() == 0 || pattern == null
				|| pattern.length() == 0)
			return null;
		DateFormat fmt = new SimpleDateFormat(pattern);
		Date result = null;
		try {
			result = fmt.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据指定日期格式将给出的日期转换成一个指定的日期字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String parseDate(Date date, String pattern) {
		DateFormat fmt = new SimpleDateFormat(pattern);
		String result =fmt.format(date); 
		return result;
	}

	/**
	 * 将特定格式（yyyy-MM-dd）的字符串转换成日期对象//
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 获取当前的日期
	 * 
	 * @return 当前的日期
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static String getCurTimestampStr() {
		DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		return fmt.format(System.currentTimeMillis()); 
	}

	/**
	 * 获取日期字符串
	 * 
	 * @param patten
	 *            格式化字符串
	 * @return
	 */
	public static String getDate(String patten) {
		SimpleDateFormat sf = new SimpleDateFormat(patten);
		return sf.format(new Date());
	}

	
	public static String getCommDateStr(Date date) {
		if (date == null) return "";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(date);
	}

	public static String getCommDateStrs(Date date) {
		if (date == null) return "";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(date);
	}
	
	public static String getCommDateStr(Timestamp ts) {
		if (ts == null) return "";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(ts);
	}

	/**
	 * 取得当前日期的年
	 * 
	 * @param dataStr
	 *            日期字符串
	 * @return 年的字符串
	 */
	public static String getYear(String dataStr) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(parseDate(dataStr));
		return String.valueOf(ca.get(Calendar.YEAR));
	}

	/**
	 * 取得当前日期的月
	 * 
	 * @param dataStr
	 *            日期字符串
	 * @return 月的字符串 注：日期字符串都是2位的,例如: 01,02,03,04,05,06,07,08,09,10,11,12
	 */
	public static String getMonth(String dataStr) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(parseDate(dataStr));
		String tmpMonthStr = "0" + String.valueOf((ca.get(Calendar.MONTH) + 1));
		return tmpMonthStr.substring(tmpMonthStr.length() - 2);
	}

	/**
	 * 取得当前时间的季度
	 * 
	 * @param dataStr
	 *            日期字符串
	 * @return 季度的字符串 注: 只能是 : 1,2,3,4
	 */
	public static String getQuarter(String dataStr) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(parseDate(dataStr));
		return String.valueOf(Math.round(ca.get(Calendar.MONTH) / 3 + 1));
	}

	/**
	 * 处理传入格式为yyyy-mm-dd-hh.mm.ss.ffffff的字符串
	 * @param timestampStr
	 * @return
	 */
	public static Timestamp getTimestamp(String timestampStr) {
		if (timestampStr != null && !timestampStr.equals("")) {
			return Timestamp.valueOf(timestampStr.substring(0, 10) + " " + timestampStr.substring(11,13) + ":" + timestampStr.substring(14,16) + ":" + timestampStr.substring(17));
		} else {
			return null;
		}
	}
	
	/**
	 * 处理传入格式为2009-3-1的数据
	 * @param dateStr
	 * @return
	 */
	public static Timestamp getTimestamp2(String dateStr) {
		if (dateStr.indexOf("-") >= 0) {
			String[] dtAry = dateStr.split("-");
			return Timestamp.valueOf(dtAry[0] + "-" + getFmtTimeStr(dtAry[1]) + "-" + getFmtTimeStr(dtAry[2]) + " 00:00:00.000");
		} else if (dateStr.indexOf("/") >= 0) {
			String[] dtAry = dateStr.split("/");
			return Timestamp.valueOf(dtAry[2] + "-" + getFmtTimeStr(dtAry[1]) + "-" + getFmtTimeStr(dtAry[0]) + " 00:00:00.000");
		}
		return null;
	}
	
	/**
	 * 对于类似于3这类的数据变为03,如果是10,则还是10
	 * @return
	 */
	private static String getFmtTimeStr(String str) {
		String tmpStr = "00" + str;
		return tmpStr.substring(tmpStr.length() -2);
	}
	
	/**
	 * 取得当前的日期,不包含时间:2010-12-10 00:00:00
	 * @param day 0 当前日期;－1则为当前date的前1天;1则为当前date的后1天
	 * @return
	 */
	public static Timestamp getCurTimestampNoTime(int day){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		if(day != 0){
			c.add(Calendar.DATE, day);
		}
		return new Timestamp(c.getTimeInMillis());
	}
	
	public static String getSQLTimeOfOracle(Date date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "to_date('".concat(fmt.format(date)).concat("','YYYY-MM-DD HH24:MI:SS')");
	}
	
	public static String getSQLQLmeOfDb2(Date date) {
		return "'".concat(date.toString()).concat("'");
	}
	
	//add by chen.biao begin
	/**
	 * 查询条件开始日期
	 * @Title: getStartTime 
	 * @param startTime
	 * @throws ParseException
	 * @return Timestamp
	 */
	public static Timestamp getStartTime(String startTime){
		if (startTime != null && !startTime.equals("")) {
			if( startTime.indexOf("/") >=  0 )
			{
				startTime = startTime.replaceAll("/", "-");
			}
			startTime = startTime.concat(" 00:00:00.0");
			Timestamp timestamp = Timestamp.valueOf(startTime);
			return timestamp;
		} else {
			return null;
		}
	}
	
	/**
	 * 查询条件结束日期
	 * @Title: getEndTime 
	 * @param endTime
	 * @return Timestamp
	 */
	public static Timestamp getEndTime(String endTime){
		if (endTime != null && !endTime.equals("")) {
			if( endTime.indexOf("/") >=  0 )
			{
				endTime = endTime.replaceAll("/", "-");
			}
			endTime = endTime.concat(" 23:59:59.999999");
			Timestamp timestamp = Timestamp.valueOf(endTime);
			return timestamp;
		} else {
			return null;
		}
	}
	
	/**
	 * 查询条件开始日期
	 * @Title: getStartTime
	 * @param paramMap
	 * @param key
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> getStartTime(Map<String, Object> paramMap, String key){
		Object obj = paramMap.get(key);
		if(obj != null && obj instanceof String){
			String startTime = (String) paramMap.get(key);
			if (StringUtils.isNotBlank(startTime)) {
				startTime = startTime.replaceAll("/", "-");
				startTime = startTime.concat(" 00:00:00.0");
				Timestamp timestamp = Timestamp.valueOf(startTime);
				paramMap.put(key, timestamp);
			}
		}
		return paramMap;
	}
	
	/**
	 * 查询条件结束日期
	 * @Title: getEndTime
	 * @param paramMap
	 * @param key
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> getEndTime(Map<String, Object> paramMap, String key){
		Object obj = paramMap.get(key);
		if(obj != null && obj instanceof String){
			String endTime = obj.toString();
			if (StringUtils.isNotBlank(endTime)) {
				endTime = endTime.replaceAll("/", "-");
				endTime = endTime.concat(" 23:59:59.999999");
				Timestamp timestamp = Timestamp.valueOf(endTime);
				paramMap.put(key, timestamp);
			}
		}
		return paramMap;
	}
	
	/**
	 * 2013-7-9格式   转换成   2013-07-09格式
	 */
	public static String getNewFormatStr(String str){
		if (str != null && !str.equals("")) {
			if( str.indexOf("/") >=  0 )
			{
				str = str.replaceAll("/", "-");
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			try {
				date = df.parse(str);
				str = df.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return str;
		} else {
			return null;
		}
	}
	//add by chen.biao end
	
	
	/**
	 * String转换为Timestamp
	 */
	public static Timestamp toTimestamp(String time) {
		if (StringUtils.isNotBlank(time)) {
			//DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return new Timestamp(fmt.parse(time).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 获取当前日期   多少月后的日期
	 * @Title: getAfertMonth
	 * @param month
	 * @return Timestamp
	 */
	public static Timestamp getAfertMonth(Integer month){
		Calendar cal = Calendar.getInstance();
		// 下面的就是把当前日期加一个月
		cal.add(Calendar.MONTH, month);
		return new Timestamp(cal.getTimeInMillis());
	}
	/**
     * 字符串转时间    例："20151213"转"2015-12-13"
     *解释客户端字符串时间不规则  需要转成指定的规则再通过"yyyy-MM-dd "转换时间
     */
    public static String ConverToDate(String time) throws ParseException{
    		if(time==""||null==time)
    			return null;
    		if(time.length()==8){
    			return time.substring(0, 4)+"-"+time.substring(4, 6)+"-"+time.substring(6, 8);
    		}else{
    			return "";
    		}
    }
    
	/**
	 * 获取当前日期   多少月后的或者前日期
	 * @Title: getMonth
	 * @param month 正数为month后的,负数为month前的
	 * @return date
	 */
	public static Date getMonth(Integer month){
		Calendar cal = Calendar.getInstance();
		// 下面的就是把当前日期加一个月
		cal.add(Calendar.MONTH, month);
		return new Date(cal.getTimeInMillis());
	}
	
	

	/**
	 * 取得当前的日期   多少天后的或者前日期
	 * @Title: getDayTime
	 * @param day 0 当前日期;－1则为当前date的前1天;1则为当前date的后1天
	 * @return
	 */
	public static Date getDayTime(int day){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		if(day != 0){
			c.add(Calendar.DATE, day);
		}
		return new Date(c.getTimeInMillis());
	}
    
	/**
	 * 获取当前日期多少天后/前的字符串表示
	 * @param number 表示天数（正数往后推，负数往前推） 
	 * @return
	 */
	public static String getDayStr(int number){
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, number);
		return yyyyMMdd.format(now.getTime());
	}
	
    /**
	 * 两个字符串时间的时间差
	 * 
	 * @param yyyyMMddHHmmssByStart
	 * @param yyyyMMddHHmmssByEnd
	 * @return 毫秒结果
	 * @throws ParseException
	 */
	public static Long timeDifference(String yyyyMMddHHmmssByStart, String yyyyMMddHHmmssByEnd) throws ParseException {
		Date d1 = yyyyMMddHHmmss.parse(yyyyMMddHHmmssByStart);
		Date d2 = yyyyMMddHHmmss.parse(yyyyMMddHHmmssByEnd);

		return d1.getTime() - d2.getTime();
	}
	
	/**
	 * 验证日期格式(yyyyMMdd)
	 * @Title: main 
	 * @Description: 
	 * @param @param args 
	 * @return void
	 * @author JWT
	 * @throws
	 */
	public static boolean validateDate(String date){
		String eL = "(\\d{4})(0\\d{1}|1[0-2])(0\\d{1}|[12]\\d{1}|3[01])";
		Pattern p = Pattern.compile(eL);
		Matcher matcher = p.matcher(date);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}
	
	public static boolean compareDate(String beginDate,String endDate) throws ParseException{
		//时间验证
		SimpleDateFormat sdf=new  SimpleDateFormat("yyyyMMdd");
		Long long1=sdf.parse(endDate).getTime();
		Long long2=sdf.parse(beginDate).getTime();

		if(long1<long2){//开始时间不能超过结束时间
			return false;
		}
		int day=(int) ((long1-long2)/(24*60*60*1000));
		if(day>31){//查询时间间隔不能大于一个月
			return false;
		}
		return  true;
	}
	
	public static boolean checkRequestTime(String timestamp,String nowDate) throws ParseException{
		boolean flag=false;
		if(timestamp != null){
			SimpleDateFormat sdf=new  SimpleDateFormat("yyyyMMddHHmmss");
			try {
				//渠道调用接口时间毫秒数
				long sendTime=sdf.parse(timestamp).getTime();
				//嘉福平台接收时间毫秒数
				long recieveTime=sdf.parse(nowDate).getTime();
				if(sendTime<=recieveTime&&(recieveTime-sendTime)<=1000*60*5){
					flag=true;
				}else if(sendTime>recieveTime&&(sendTime-recieveTime)<=1000*60*5){
					flag=true;
				}else{
					flag=false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	private static final String date_format = "yyyyMMddHHmmss";
	private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();

	/**
	 * 使用ThreadLocal, 也是将共享变量变为独享，线程独享肯定能比方法独享在并发环境中能减少不少创建对象的开销。
	 * 如果对性能要求比较高的情况下，一般推荐使用这种方法。
	 * @return
	 */
	public static DateFormat getDateFormat()
	{
		DateFormat df = threadLocal.get();
		if(df==null){
			df = new SimpleDateFormat(date_format);
			threadLocal.set(df);
		}
		return df;
	}

	public static String formatDate(Date date) throws ParseException {
		return getDateFormat().format(date);
	}

	public static Date parse(String strDate) throws ParseException {
		return getDateFormat().parse(strDate);
	}



	public static void main(String[] args) {
		//			System.out.println(getyyyyMMDate(getMonth(-1)));
		try {
			System.out.println(getyyyyMMdd(getDayTime(31)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
