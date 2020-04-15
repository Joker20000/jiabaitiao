package com.bootdo.Utils;


import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;


/**
 * @FileName: BigDecimal.java
 * @Description: 数字计算工具类
 */
public class BigDecimalUtil {

    /**
     * BigDecimal加法
     *
     * @param o1
     * @param o2
     * @return
     */
    public static String BigDecimalAdd(String o1, String o2) {
        BigDecimal bigDecimal = new BigDecimal(StringUtils.isBlank(o1) ? "0" : o1);
        BigDecimal bigDecimalNew = new BigDecimal(StringUtils.isBlank(o2) ? "0" : o2);
        return bigDecimal.add(bigDecimalNew).toString();
    }

    /**
     * BigDecimal减法
     *
     * @param o1
     * @param o2
     * @return String
     */
    public static String BigDecimalSubtract(String o1, String o2) {
        BigDecimal bigDecimal = new BigDecimal(StringUtils.isBlank(o1) ? "0" : o1);
        BigDecimal bigDecimalNew = new BigDecimal(StringUtils.isBlank(o2) ? "0" : o2);
        return bigDecimal.subtract(bigDecimalNew).toString();
    }

    /**
     * BigDecimal减法
     *
     * @param o1
     * @param o2
     * @return Int
     */
    public static int BigDecimalSubtractForInt(String o1, String o2) {
        BigDecimal bigDecimal = new BigDecimal(StringUtils.isBlank(o1) ? "0" : o1);
        BigDecimal bigDecimalNew = new BigDecimal(StringUtils.isBlank(o2) ? "0" : o2);
        return bigDecimal.subtract(bigDecimalNew).intValue();
    }


    /**
     * BigDecimal大小比较,前者与后者比较
     *
     * @param o1
     * @param o2
     * @return Int -1表示小于,0是等于,1是大于.允许参数null,"","  "时会转译为"0"
     */
    public static int BigDecimalCompareTo(String o1, String o2) {
        BigDecimal bigDecimal = new BigDecimal(StringUtils.isBlank(o1) ? "0" : o1);
        BigDecimal bigDecimalNew = new BigDecimal(StringUtils.isBlank(o2) ? "0" : o2);
        return bigDecimal.compareTo(bigDecimalNew);
    }


    /**
     * BigDecimal乘法
     *
     * @param o1
     * @param o2
     * @return
     */
    public static String BigDecimalMultiply(String o1, String o2) {
        BigDecimal bigDecimal = new BigDecimal(StringUtils.isBlank(o1) ? "0" : o1);
        BigDecimal bigDecimalNew = new BigDecimal(StringUtils.isBlank(o2) ? "0" : o2);
        return bigDecimal.multiply(bigDecimalNew).toString();
    }

    /**
     * BigDecimal除法
     *
     * @param o1
     * @param o2
     * @param o3
     * @return
     */
    public static String BigDecimalDivide(String o1, String o2, int o3) {
        BigDecimal bigDecimal = new BigDecimal(StringUtils.isBlank(o1) ? "0" : o1);
        BigDecimal bigDecimalNew = new BigDecimal(StringUtils.isBlank(o2) ? "0" : o2);
        return bigDecimal.divide(bigDecimalNew, o3, BigDecimal.ROUND_DOWN).toString();
    }

    /**
     * BigDecimal除法 四舍五入
     *
     * @param o1
     * @param o2
     * @param o3
     * @return
     */
    public static String BigDecimalDivideAdd(String o1, String o2, int o3) {
        BigDecimal bigDecimal = new BigDecimal(StringUtils.isBlank(o1) ? "0" : o1);
        BigDecimal bigDecimalNew = new BigDecimal(StringUtils.isBlank(o2) ? "0" : o2);
        return bigDecimal.divide(bigDecimalNew, o3, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * BigDecimal除法
     *
     * @param o1
     * @param o2
     * @return
     */
    public static String BigDecimalDivide(String o1, String o2) {
        BigDecimal bigDecimal = new BigDecimal(StringUtils.isBlank(o1) ? "0" : o1);
        BigDecimal bigDecimalNew = new BigDecimal(StringUtils.isBlank(o2) ? "0" : o2);
        return bigDecimal.divide(bigDecimalNew, 0, BigDecimal.ROUND_DOWN) + "";
    }

    /**
     * BigDecimal 获取小数点o2位 之前
     *
     * @param o1
     * @param o2
     * @return
     */
    public static String Scale(String o1, int o2) {
        BigDecimal bigDecimal = new BigDecimal(StringUtils.isBlank(o1) ? "0" : o1);
        return bigDecimal.setScale(o2, BigDecimal.ROUND_DOWN).toString();
    }

    /**
     * 提供精确的除法运算方法div
     *
     * @param value1 被除数
     * @param value2 除数
     * @param scale  精确范围
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static double bigDev(double value1, double value2, int scale) throws IllegalAccessException {
        //如果精确范围小于0，抛出异常信息
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.divide(b2, scale).doubleValue();
    }

}
