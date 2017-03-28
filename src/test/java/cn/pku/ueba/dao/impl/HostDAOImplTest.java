/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月28日 下午4:48:41
 */
package cn.pku.ueba.dao.impl;

import static org.junit.Assert.fail;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.pku.ueba.dao.factory.HostFactory;
import cn.pku.ueba.model.Host;
import cn.pku.ueba.model.HostType;

/**
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class HostDAOImplTest {
	HostDAOImpl hd = new HostDAOImpl();
	String ip = "192.168.200.110";

	/**
	 * Test method for {@link cn.pku.ueba.dao.impl.HostDAOImpl#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.impl.HostDAOImpl#indexHost(cn.pku.ueba.model.Host)}
	 * .
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testIndexHost() throws InterruptedException {
		Host host = HostFactory.getHost("server_1");
		host.setDepartment("研发中心");
		host.setIp(ip);
		host.setMacaddress("f4:0f:24:35:0f:55");
		host.setType(HostType.application_server);
		hd.indexHost(host);
		Thread.sleep(1000);
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.impl.HostDAOImpl#getHost(java.lang.String)}.
	 */
	@Test
	public void testGetHost() {
		Host host = hd.getHost(ip);
		if (host == null)
			fail("HostDAO cannot getHost");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.impl.HostDAOImpl#deleteHost(java.lang.String)}.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testDeleteHost() throws InterruptedException {
		hd.deleteHost(ip);
		Thread.sleep(1000);
		Host host = hd.getHost(ip);
		if (host != null) {
			fail("HostDAO cannot deleteHost");
		}
	}

}
