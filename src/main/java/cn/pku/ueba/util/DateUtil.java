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

	private static long second = 1000;
	private static long minute = 60 * second;
	private static long hour = 60 * minute;
	private static long day = 24 * hour;

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
		Date date = new Date();
		Date result = new Date(date.getTime() - day * interval);
		return result;
	}

	/**
	 * 得到过去若干分钟的时刻点
	 * <p>
	 * 例如2017-04-05 10:03:30.123，应该得到的是2017-04-05 10:02:30.123
	 * </p>
	 * 
	 * @param interval
	 *            分钟数
	 * @return 标准date对象
	 */
	public static Date getLastMinuteDate(Date date, int interval) {
		Date date1 = new Date(date.getTime());
		date1.setTime(date1.getTime() - interval * minute);
		return date1;
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

	/**
	 * 获取到当前时刻的分钟整点时间，
	 * <p>
	 * 例如2017-04-05 10:03:30.123，应该得到的是2017-04-05 10:03:00.000
	 * </p>
	 * 
	 * @param date
	 *            时刻点
	 * @return 返回该时刻点的分钟整点时间
	 */
	public static Date getSharpTimeInMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取到当前时刻的分钟整点时间，
	 * <p>
	 * 例如2017-04-05 10:03:30.123，应该得到的是2017-04-05 10:00:00.000
	 * </p>
	 * 
	 * @param date
	 *            时刻点
	 * @return 返回该时刻点的分钟整点时间
	 */
	public static Date getSharpTimeInHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

}
