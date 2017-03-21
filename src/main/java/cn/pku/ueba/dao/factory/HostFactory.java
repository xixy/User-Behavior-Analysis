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
