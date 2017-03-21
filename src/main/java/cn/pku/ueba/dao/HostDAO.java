package cn.pku.ueba.dao;

import cn.pku.ueba.model.Host;

public interface HostDAO {
	// 从数据库中提取用户
	public Host getHost(String name);

	// 从数据库中删除用户
	public void deleteHost(String name);
	
	public void indexHost(Host host);

}
