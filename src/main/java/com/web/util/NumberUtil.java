package com.web.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * 将数字工具类
 * @author gql
 *
 */
public class NumberUtil {


	/**
	 * 创建X位随机数
	 * @param digitNumber 要创建的随机数位数
	 * @return 生成的数字字符串
	 */
	public static String createNumberStr(int digitNumber) {
		// 生成随机类
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < digitNumber; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
		}
		return sRand;
	}

	/**
	 * 数字为个位数，数字前补0
	 * @param i
	 * @return
	 */
	public static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10) {
			retStr = "0" + Integer.toString(i);
		} else {
			retStr = "" + i;
		}
		return retStr;
	}

	/**
	 * double精度（直接截取，不舍入）
	 * @param doubleValue 要处理的值
	 * @param accuracy    保留的小数点位数
	 * @return 返回精度的double
	 */
	public static double doubleFormat(double doubleValue, int accuracy) {
		BigDecimal bigDecimal = new BigDecimal(doubleValue + "");
		BigDecimal resultValue = bigDecimal.setScale(accuracy, BigDecimal.ROUND_DOWN);
		return Double.valueOf(resultValue.toString());
	}

	/**
	 * double精度（直接截取，不舍入）
	 * @param doubleValue 要处理的值
	 * @param accuracy    保留的小数点位数
	 * @return 返回精度的double
	 */
	public static String doubleFormatWithoutZero(double doubleValue, int accuracy) {
		BigDecimal bigDecimal = new BigDecimal(doubleValue + "");
		BigDecimal resultValue = bigDecimal.setScale(accuracy, BigDecimal.ROUND_DOWN);
		String s = resultValue.toString();
		if (s.indexOf(".") > 0) {
			//去掉多余的0
			s = s.replaceAll("0+?$", "");
			//如最后一位是.则去掉
			s = s.replaceAll("[.]$", "");
		}
		return s;
	}


	/**
	 * double补位（小数点后不足4位补0）
	 * @param doubleValue 要处理的值
	 * @return 返回补位后的字符串
	 */
	public static String doubleFillStr(double doubleValue) {
		doubleValue = doubleFormat(doubleValue, 4);
		return new DecimalFormat("#,##0.0000").format(doubleValue);
	}

	/**
	 * double精度（向上取整）
	 * @param doubleValue 要处理的值
	 * @param accuracy    保留的小数点位数
	 * @return 返回精度的double
	 */
	public static double doubleUpFormat(double doubleValue, int accuracy) {
		BigDecimal bigDecimal = new BigDecimal(doubleValue + "");
		BigDecimal resultValue = bigDecimal.setScale(accuracy, BigDecimal.ROUND_UP);
		return Double.valueOf(resultValue.toString());
	}

	/**
	 * double精度（类fmt处理，四舍六入）
	 * @param doubleValue 要处理的值
	 * @param accuracy    保留的小数点位数
	 * @return 返回精度的double
	 */
	public static String doubleStringFormat(double doubleValue, int accuracy) {
		BigDecimal bigDecimal = new BigDecimal(doubleValue + "");
		BigDecimal resultValue = bigDecimal.setScale(accuracy, BigDecimal.ROUND_HALF_EVEN);
		return resultValue.stripTrailingZeros().toPlainString();
	}

	/**
	 * 提供精确的小数位处理。
	 * @param v 数字
	 * @param scale 小数点后保留几位
	 * @param roundingMode 进位类型
	 * @return 小数处理结果
	 */
	public static BigDecimal round(BigDecimal v, int scale, int roundingMode) {
		if (scale < 0 || roundingMode < 0) {
			throw new IllegalArgumentException(
					"The scale or roundingMode must be a positive integer or zero");
		}
		return v.divide(BigDecimal.ONE, scale, roundingMode);
	}

	/**
	 * 金额数字格式化：三位逗号分割，保留两位小数
	 * @param v
	 * @return
	 */
	public static String format(String v) {
		return format(new BigDecimal(v));
	}

	/**
	 * 金额数字格式化：三位逗号分割，保留两位小数
	 * @param v
	 * @return
	 */
	public static String format(double v) {
		return format(new BigDecimal(v));
	}

	/**
	 * 金额数字格式化：三位逗号分割，保留两位小数
	 * @param v
	 * @return
	 */
	private static String format(BigDecimal v) {
		//默认精度,即保留小数点2位
		return new DecimalFormat("#,##0.00").format(round(v, 2, BigDecimal.ROUND_HALF_UP));
	}
	/**
	 * 金额数字格式化：三位逗号分割，保留3位小数
	 * @param v
	 * @return
	 */
	public static String format2(String v) {
		return format2(new BigDecimal(v));
	}

	/**
	 * 金额数字格式化：三位逗号分割，保留3位小数
	 * @param v
	 * @return
	 */
	public static String format2(double v) {
		return format2(new BigDecimal(v));
	}

	/**
	 * 金额数字格式化：三位逗号分割，保留3位小数
	 * @param v
	 * @return
	 */
	public static String format2(BigDecimal v) {
		return new DecimalFormat("#,##0.000").format(round(v, 3, BigDecimal.ROUND_HALF_UP));
	}
}
