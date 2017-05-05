package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

	/**
	 * @param 毫秒值
	 * 
	 * @return 时:分:秒
	 * 
	 * @author wjh
	 */
	public static String getHours(long time) {
		long second = time / 1000;
		long hour = second / 60 / 60;
		long minute = (second - hour * 60 * 60) / 60;
		long sec = (second - hour * 60 * 60) - minute * 60;

		String rHour = "";
		String rMin = "";
		String rSs = "";
		// 时
		if (hour < 10) {
			rHour = "0" + hour;
		} else {
			rHour = hour + "";
		}
		// 分
		if (minute < 10) {
			rMin = "0" + minute;
		} else {
			rMin = minute + "";
		}
		// 秒
		if (sec < 10) {
			rSs = "0" + sec;
		} else {
			rSs = sec + "";
		}

		// return hour + "小时" + minute + "分钟" + sec + "秒";
		return rHour + ":" + rMin + ":" + rSs;

	}

	/**
	 * @param time
	 *            时间戳
	 * @return 小时
	 */
	public static String getOnlyHours(long time) {
		long second = time / 1000;
		long hour = second / 60 / 60;
		String rHour = "";
		// 时
		if (time > 0 && hour == 0) {
			rHour = (hour + 1) + "";
		} else {
			rHour = hour + "";
		}

		// return hour + "小时" + minute + "分钟" + sec + "秒";
		return rHour + "小时";

	}

	/**
	 * 返回指定时间字符串。
	 * <p>
	 * 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String 指定格式的日期字符串.
	 */
	public static String getDateTime(long microsecond) {
		return getFormatDateTime(new Date(microsecond), "yyyy-MM-dd");
	}

	/**
	 * 返回指定时间字符串。
	 * <p>
	 * 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String 指定格式的日期字符串.
	 */
	public static String getDateTime(long microsecond, String format) {
		return getFormatDateTime(new Date(microsecond), format);
	}

	/**
	 * 根据给定的格式与时间(Date类型的)，返回时间字符串<br>
	 * 
	 * @param date
	 *            指定的日期
	 * @param format
	 *            日期格式字符串
	 * @return String 指定格式的日期字符串.
	 */
	public static String getFormatDateTime(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
