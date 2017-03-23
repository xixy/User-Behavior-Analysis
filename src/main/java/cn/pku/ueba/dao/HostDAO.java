/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao;

import cn.pku.ueba.model.Host;

public interface HostDAO {
	// 从数据库中提取host
	public Host getHost(String name);

	// 从数据库中删除host
	public void deleteHost(String name);

	// 将host持久化
	public void indexHost(Host host);

}
