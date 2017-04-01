/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月28日 下午4:48:41
 */
package cn.pku.ueba.dao.impl;

import static org.junit.Assert.fail;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.pku.ueba.dao.factory.HostFactoryTest;
import cn.pku.ueba.model.Host;

/**
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class HostDAOImplTest {
	HostDAOImpl hd = new HostDAOImpl();

	/**
	 * Test method for {@link cn.pku.ueba.dao.impl.HostDAOImpl#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		if (HostDAOImpl.getInstance() == null)
			fail("GetInstance failed");
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
		hd.indexHost(HostFactoryTest.host);
		Thread.sleep(1000);
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.impl.HostDAOImpl#getHost(java.lang.String)}.
	 */
	@Test
	public void testGetHost() {
		Host host = hd.getHost(HostFactoryTest.host.getIp());
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
		hd.deleteHost(HostFactoryTest.host.getIp());
		Thread.sleep(1000);
		Host host = hd.getHost(HostFactoryTest.host.getIp());
		if (host != null) {
			fail("HostDAO cannot deleteHost");
		}
	}

}
