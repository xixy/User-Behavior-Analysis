/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao.factory;

import java.util.Map;

import cn.pku.ueba.model.Host;
import cn.pku.ueba.util.JsonUtil;

/**
 * HostFactory
 */
public class HostFactory {
	/**
	 * 主机id自增
	 */
	static int hostid = 0;

	/**
	 * 新建主机对象
	 * 
	 * @param name
	 *            主机名称
	 * @return 主机对象
	 */
	public synchronized static Host getHost(String name) {
		Host host = new Host();
		host.setId(++hostid);
		host.setName(name);
		host.setRiskscore((double) 60);
		return host;
	}

	/**
	 * 将Host实例转化为Json
	 * 
	 * @param host
	 *            Host实例
	 * @return Json格式数据
	 */
	public static Map<String, Object> getJsonFromHost(Host host) {
		return JsonUtil.getJsonFromModelInstance(host);

	}

	/**
	 * 将Json转化为Host实例对象
	 * 
	 * @param json
	 *            Json格式数据
	 * @return Host实例对象
	 */
	public static Host getHostFromJson(Map<String, Object> json) {
		Object obj = JsonUtil.getModelInstanceFromJson(json, Host.class);
		return (Host) obj;

	}

}
