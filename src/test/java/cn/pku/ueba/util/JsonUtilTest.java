/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月1日 下午3:24:03
 */
package cn.pku.ueba.util;

import org.junit.Test;

import cn.pku.ueba.dao.factory.HostFactoryTest;

/**
 *
 */
public class JsonUtilTest {

	/**
	 * Test method for
	 * {@link cn.pku.ueba.util.JsonUtil#getJsonFromModelInstance(java.lang.Object)}
	 * .
	 */
	@Test
	public void testGetJsonFromModelInstance() {
		new HostFactoryTest().testGetJsonFromHost();
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.util.JsonUtil#getModelInstanceFromJson(java.util.Map, java.lang.Class)}
	 * .
	 */
	@Test
	public void testGetModelInstanceFromJson() {
		new HostFactoryTest().testGetHostFromJson();
	}

}
