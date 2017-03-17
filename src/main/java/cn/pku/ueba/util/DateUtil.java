package cn.pku.ueba.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	static SimpleDateFormat dateiso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	static SimpleDateFormat standarddate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/*
	 * 得到现在的时刻点，转换为ES时间格式的输出
	 */
	public static String getESDate() {
		return dateiso8601.format(new Date());
	}

	/*
	 * 将标准时间格式转化为ES时间格式的输出
	 */
	public static String toESDate(Date date) {
		return dateiso8601.format(date);
	}

	/*
	 * 得到过去24小时的时刻点，并转换为ES时间格式的输出
	 */
	public static String getLastDayESDate(int interval) {
		return toESDate(getYesterdayDate(interval));
	}

	/*
	 * 得到过去24小时的时刻点
	 */
	public static Date getYesterdayDate(int interval) {
		String now = standarddate.format(new Date());
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = standarddate.parse(now);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - interval);
		System.out.println(c.getTime());
		return c.getTime();
	}
}
