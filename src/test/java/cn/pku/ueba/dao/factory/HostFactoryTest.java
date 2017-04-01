/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月1日 下午1:35:39
 */
package cn.pku.ueba.dao.factory;

import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.pku.ueba.model.Host;
import cn.pku.ueba.model.HostType;

/**
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class HostFactoryTest {
	public static Host host = HostFactory.getHost("服务器1");
	public static Map<String, Object> json = null;

	static {
		host.setDepartment("北京大学");
		host.setIp("192.168.200.160");
		host.setMacaddress("6e.2ra.5c.1b.5a.8e");
		host.setType(HostType.application_server);
		json = HostFactory.getJsonFromHost(host);

	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.factory.HostFactory#getHost(java.lang.String)}.
	 */
	@Test
	public void testGetHost() {

		if (host == null)
			fail("getHost failed");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.factory.HostFactory#getJsonFromHost(cn.pku.ueba.model.Host)}
	 * .
	 */
	@Test
	public void testGetJsonFromHost() {
		if (json.keySet().size() != 7)
			fail("getJsonFromHost fialed");

	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.factory.HostFactory#getHostFromJson(java.util.Map)}
	 * .
	 */
	@Test
	public void testGetHostFromJson() {
		Host host = HostFactory.getHostFromJson(json);
		if (host == null)
			fail("GetHostFromJson failed");
		// 类型也正确
		if (!host.getType().equals(HostType.application_server))
			fail("GetHostFromJson failed");
	}

}
