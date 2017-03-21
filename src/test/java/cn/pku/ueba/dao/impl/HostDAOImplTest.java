package cn.pku.ueba.dao.impl;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.pku.ueba.dao.factory.HostFactory;
import cn.pku.ueba.model.Host;
import cn.pku.ueba.model.HostType;

@FixMethodOrder(MethodSorters.JVM)
public class HostDAOImplTest {
	HostDAOImpl hd = new HostDAOImpl();
	String ip = "192.168.200.110";

	@Before
	public void setup() {

	}

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

	@Test
	public void testGetHost() {
		Host host = hd.getHost(ip);
		if (host == null)
			fail("HostDAO cannot getHost");
	}

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
