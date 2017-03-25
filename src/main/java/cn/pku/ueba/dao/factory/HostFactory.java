/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao.factory;

import cn.pku.ueba.model.Host;

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
	public static Host getHost(String name) {
		Host host = new Host();
		host.setId(++hostid);
		host.setName(name);
		host.setRiskscore((double) 60);
		return host;
	}

}
