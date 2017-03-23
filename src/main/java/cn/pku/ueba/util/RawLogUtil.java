/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pku.ueba.model.activity.ActivityType;

/**
 * 用来处理日志的类
 * 
 * @author xixy
 * @version 0.1
 */
public class RawLogUtil {
	/**
	 * 用来判断原始日志的类型，还没有做完
	 * 
	 * @param json
	 *            原始日志的json格式
	 * @return 返回该日志对应的活动类型
	 */
	public static ActivityType getActivityTypeFromRawLogItem(Map<String, Object> json) {
		return ActivityType.ad;

	}

	/**
	 * 判断是否为合法IP
	 * 
	 * @param ipAddress
	 *            ip地址字符串
	 * @return 字符串是否为ip
	 */
	public static boolean isBoolIp(String ipAddress) {
		String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}

}
