/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 处理ES和标准date的类
 * 
 * @author xixy10@foxmail.com
 */
public class DateUtil {

	/**
	 * iso8601 时间格式
	 */
	public static SimpleDateFormat dateiso8601 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	/**
	 * 标准date格式
	 */
	public static SimpleDateFormat standarddate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * 得到现在的时刻点，转换为ES时间格式的输出
	 * 
	 * @return iso8601时间格式
	 */
	public static String getESDate() {
		return dateiso8601.format(new Date());
	}

	/*
	 * 
	 */
	/**
	 * 将标准时间格式转化为ES时间格式的输出
	 * 
	 * @param date
	 *            标准时间对象
	 * @return ES时间对象
	 */
	public static String toESDate(Date date) {
		return dateiso8601.format(date);
	}

	/**
	 * 得到过去若干天的时刻点，并以iso格式返回
	 * 
	 * @param interval
	 *            天数
	 * @return isodate格式对象
	 */
	public static String getLastDayESDate(int interval) {
		return toESDate(getLastDate(interval));
	}

	/**
	 * 得到过去若干天的时刻点
	 * 
	 * @param interval
	 *            天数
	 * @return 标准date对象
	 */
	public static Date getLastDate(int interval) {
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
		return c.getTime();
	}

	/**
	 * 将ESDate转换为Date对象
	 * 
	 * @param esDate
	 *            ESDate格式的字符串
	 * @return Date对象
	 */
	public static Date getDateFromESDate(String esDate) {
		Date date = null;
		try {
			date = dateiso8601.parse(esDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
