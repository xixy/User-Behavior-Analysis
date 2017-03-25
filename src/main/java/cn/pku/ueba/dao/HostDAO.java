/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao;

import cn.pku.ueba.model.Host;

/**
 * 用来处理Host对象的持久化
 * 
 * @author xixy10@foxmail.com
 */
public interface HostDAO {
	/**
	 * 从数据库中提取host
	 * 
	 * @param name
	 *            主机名称
	 * @return Host对象
	 */
	public Host getHost(String name);

	/**
	 * 从数据库中删除host
	 * 
	 * @param name
	 *            主机名称
	 */
	public void deleteHost(String name);

	/**
	 * 将host持久化
	 * 
	 * @param host
	 *            主机名称
	 */
	public void indexHost(Host host);

}
