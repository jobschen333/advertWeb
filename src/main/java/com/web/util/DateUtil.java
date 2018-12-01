package com.web.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换
 *
 * @author gql
 */
public class DateUtil {

    /**
     * 时间字符串格式：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String dateFormat1 = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 时间字符串格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String dateFormat2 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间字符串格式：yyyy-MM-dd HH:mm
     */
    public static final String dateFormat3 = "yyyy-MM-dd HH:mm";

    /**
     * 时间字符串格式：yyyy-MM-dd
     */
    public static final String dateFormat4 = "yyyy-MM-dd";

    /**
     * 时间字符串格式：HH:mm:ss
     */
    public static final String dateFormat5 = "HH:mm:ss";

    /**
     * 时间字符串格式：yyyyMMddHHmmss
     */
    public static final String dateFormat6 = "yyyyMMddHHmmss";

    /**
     * 时间字符串格式：yyyyMMddHHmm
     */
    public static final String dateFormat7 = "yyyyMMddHHmm";

    /**
     * 时间字符串格式：yyMMddHHmmss
     */
    public static final String dateFormat8 = "yyMMddHHmmss";

    /**
     * 时间字符串格式：yyMMddHHmm
     */
    public static final String dateFormat9 = "yyMMddHHmm";

    /**
     * 时间字符串格式：yyMMdd
     */
    public static final String dateFormat10 = "yyMMdd";

    /**
     * 时间字符串格式：yyyyMMdd
     */
    public static final String dateFormat11 = "yyyyMMdd";


    /**
     * 时间字符串格式：yyyy-MM-dd HH:mm:00.0
     */
    public static final String dateFormat12 = "yyyy-MM-dd HH:mm:00.0";

    /**
     * 时间字符串格式：yyyy-MM-dd HH:00:00.0
     */
    public static final String dateFormat13 = "yyyy-MM-dd HH:00:00.0";

    /**
     * 时间字符串格式：yyyy-MM-dd 00:00:00.0
     */
    public static final String dateFormat14 = "yyyy-MM-dd 00:00:00.0";


    /**
     * 时间字符串格式：HHmmss
     */
    public static final String dateFormat15 = "HHmmss";

	/** 时间字符串格式 yyyy-MM*/
	public static final String dateFormat16 = "yyyy-MM";

	/**
	 * 获取当前时间的时间戳毫秒数
	 * 推荐此种方法，执行速度快
	 * @return 时间毫秒数
	 */
	public static long getCurrentTimeMillis(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 获取当前时间的时间戳
	 * 推荐此种方法，执行速度快
	 * @return 时间戳
	 */
	public static Timestamp getCurrentTime(){
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 将时间字符串转换时间格式
	 * @param timeStr 时间字符串
	 * @return 时间戳
	 */
	public static Timestamp stringToTimestamp(String timeStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(timeStr);
			Timestamp timestamp = new Timestamp(date.getTime());
			return timestamp;
		} catch (ParseException e) {
			LogUtil.printErrorLog(e);
		}
		
		return null;
	}

    /**
     * 获取当天凌晨的时间戳毫秒数
     *
     * @return 时间毫秒数
     */
    public static long lingchenLong() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String dataStr = formatter.format(date);

        long dateLong = 0;
        try {
            Date newDate = formatter.parse(dataStr);
            dateLong = newDate.getTime();
        } catch (ParseException e) {
            LogUtil.printErrorLog(e);
        }

        return dateLong;
    }


    /**
     * 将时间戳毫秒数转换字符串
     *
     * @param timeLong   时间戳毫秒数
     * @param dateFormat 时间字符串格式，如：yyyy-MM-dd HH:mm:ss.SSS
     * @return 时间字符串格式的文本
     */
    public static String longToTimeStr(long timeLong, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Date date = new Date(timeLong);
        String timeStr = formatter.format(date);

        return timeStr;
    }



    /**
     * 将时间戳毫秒数转换时间戳格式
     *
     * @param timeLong  时间戳毫秒数
     * @param formatStr 时间字符串，例如格式：yyyy-MM-dd HH:mm:ss
     * @return 时间戳
     */
    public static Timestamp longToTimestampByFormat(long timeLong, String formatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        Date date = new Date(timeLong);
        String dataStr = formatter.format(date);
        Timestamp timestamp = Timestamp.valueOf(dataStr);

        return timestamp;
    }


    /**
     * 将时间字符串转换时间格式
     *
     * @param timeStr 时间字符串
     * @return 时间戳
     */
    public static Timestamp stringToTimestamp2(String timeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(timeStr);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;
        } catch (ParseException e) {
            LogUtil.printErrorLog(e);
        }

        return null;
    }

    /**
     * 将时间戳毫秒数转换时间戳格式
     *
     * @param timeLong 时间戳毫秒数
     * @return 时间戳
     */
    public static Timestamp longToTimestamp(long timeLong) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date(timeLong);
        String dataStr = formatter.format(date);
        Timestamp timestamp = Timestamp.valueOf(dataStr);
        return timestamp;
    }

    /**
     * 将时间戳毫秒数转换时间戳格式
     *
     * @param timeLong 时间戳毫秒数
     * @return 时间戳
     */
    public static Timestamp longToTimestamp2(long timeLong) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timeLong);
        String dataStr = formatter.format(date);
        Timestamp timestamp = Timestamp.valueOf(dataStr);
        return timestamp;
    }

    /**
     * 将数字转换成时间格式显示
     *
     * @param time 数字
     * @return 返回时间格式字符串
     */
    public static String NumberToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0) {
            return "00:00:00";
        } else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = "00:" + NumberUtil.unitFormat(minute) + ":" + NumberUtil.unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99) {
                    return "99:59:59";
                }
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = NumberUtil.unitFormat(hour) + ":" + NumberUtil.unitFormat(minute) + ":" + NumberUtil.unitFormat(second);
            }
        }
        return timeStr;
    }



    /**
     * 判断结束时间是否比开始时间大
     *
     * @param endTime   结束时间
     * @param startTime 开始时间
     * @return
     */
    public static boolean endTimeLaterThanStartTime(String endTime, String startTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            Date endDate = sdf.parse(endTime);
            Date startDate = sdf.parse(startTime);
            if (endDate.after(startDate)) {
                return true;
            }
            return false;
        } catch (ParseException e) {
            LogUtil.printErrorLog(e);
            return false;
        }
    }


	/**
	 * date转行成 yyyy-MM
	 * @param date
	 * @return
     */
	public static String dateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat16);
		return sdf.format(date);
	}

    /**
     * 判断yyyy-MM的string字符串的时间格式是否大于当前时间
     *
     * @param time
     * @return
     */
	public static boolean timeLaterThanNow(String time) {
	    try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat16);
            Date date = sdf.parse(time);
            return date.getTime() > getCurrentTimeMillis();
        } catch (Exception e) {
	        LogUtil.printErrorLog(e);
	        return true;
        }
    }

}

