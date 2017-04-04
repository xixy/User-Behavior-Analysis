/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月31日 下午2:58:19
 */
package cn.pku.ueba.configure;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 关于Graylog的配置，从配置文件rawlog.property读取
 * 
 * @author xixy10@foxmail.com
 */
public class Configure {
	private static Properties pro = new Properties();
	private static String index;
	private static String rawlogtype;
	private static String usertype;
	private static String hosttype;
	private static String activitytype;
	private static String ip;
	private static int port;
	private static String clustername;
	private static String featuretype;

	static {
		try {
			// 通过配置文件来实现载入不同的类，实现不同的功能
			pro.load(new FileInputStream("src/main/resources/graylog.property"));
			// 配置index和type
			index = pro.getProperty("index");
			rawlogtype = pro.getProperty("rawlogtype");
			usertype = pro.getProperty("usertype");
			hosttype = pro.getProperty("hosttype");
			activitytype = pro.getProperty("activitytype");
			ip = pro.getProperty("ip");
			port = Integer.parseInt(pro.getProperty("port"));
			clustername = pro.getProperty("clustername");
			featuretype = pro.getProperty("featuretype");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Properties getPro() {
		return pro;
	}

	public static String getIndex() {
		return index;
	}

	public static String getRawlogtype() {
		return rawlogtype;
	}

	public static String getUsertype() {
		return usertype;
	}

	public static String getHosttype() {
		return hosttype;
	}

	public static String getActivitytype() {
		return activitytype;
	}

	public static String getIp() {
		return ip;
	}

	public static int getPort() {
		return port;
	}

	public static String getClustername() {
		return clustername;
	}

	public static String getFeaturetype() {
		return featuretype;
	}
}
