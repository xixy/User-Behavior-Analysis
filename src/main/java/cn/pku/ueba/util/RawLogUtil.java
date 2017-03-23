package cn.pku.ueba.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pku.ueba.model.activity.ActivityType;

/**
 * 用来处理日志的类
 */
public class RawLogUtil {
	/**
	 * 用来判断原始日志的类型，还没有做完
	 */
	public static ActivityType getActivityTypeFromRawLogItem(Map<String, Object> json) {
		return ActivityType.ad;

	}

	/**
	 * 判断是否为合法IP
	 * 
	 * @return the ip
	 */
	public static boolean isBoolIp(String ipAddress) {
		String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}

}
