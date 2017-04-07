/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月7日 上午10:47:35
 */
package cn.pku.ueba.dao.factory;

import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Test;

import cn.pku.ueba.model.Entity;
import cn.pku.ueba.model.Host;
import cn.pku.ueba.model.HostType;

/**
 *
 */
public class HostFactoryTest {

	public static Host host = (Host) HostFactory.getInstance().getEntity();
	public static Map<String, Object> json = null;

	static {
		host.setName("服务器1");
		host.setDepartment("北京大学");
		host.setIp("192.168.200.160");
		host.setMacaddress("6e.2ra.5c.1b.5a.8e");
		host.setType(HostType.application_server);
		json = HostFactory.getInstance().getJsonFromEntity(host);

	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.factory.HostFactory#getEntityFromJson(java.util.Map, cn.pku.ueba.model.EntityType)}
	 * .
	 */
	@Test
	public void testGetEntityFromJson() {
		Entity entity = HostFactory.getInstance().getEntityFromJson(json);
		if (!(entity instanceof Host))
			fail("getEntityFromJson failed");
	}

}
