/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月7日 上午10:31:10
 */
package cn.pku.ueba.dao.factory;

import java.util.Map;

import cn.pku.ueba.model.Entity;
import cn.pku.ueba.model.Host;
import cn.pku.ueba.util.JsonUtil;

/**
 * HostFactory
 * 
 * @author xixy10@foxmail.com
 */
public class HostFactory extends EntityFactory {

	/**
	 * 主机id自增
	 */
	static int hostid = 0;

	private static HostFactory instance;

	public static HostFactory getInstance() {
		if (instance == null)
			instance = new HostFactory();
		return instance;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see cn.pku.ueba.dao.factory.EntityFactory#getEntity()
	 */
	@Override
	public Entity getEntity() {
		Host host = new Host();
		synchronized (HostFactory.class) {
			host.setId(++hostid);
		}
		host.setRiskscore((double) 60);
		return host;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see cn.pku.ueba.dao.factory.EntityFactory#getEntityFromJson(java.util.Map,
	 *      cn.pku.ueba.model.EntityType)
	 */
	@Override
	public Entity getEntityFromJson(Map<String, Object> json) {
		Object obj = JsonUtil.getModelInstanceFromJson(json, Host.class);
		return (Host) obj;
	}

}
