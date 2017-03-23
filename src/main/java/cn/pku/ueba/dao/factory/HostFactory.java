/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao.factory;

import cn.pku.ueba.model.Host;

public class HostFactory {
	static int hostid = 0;

	public static Host getHost(String name) {
		Host host = new Host();
		host.setId(++hostid);
		host.setName(name);
		host.setRiskscore((double) 60);
		return host;
	}

}
