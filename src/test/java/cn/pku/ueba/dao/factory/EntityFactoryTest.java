/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月7日 上午9:53:57
 */
package cn.pku.ueba.dao.factory;

import static org.junit.Assert.fail;

import org.junit.Test;

/**
 *
 */
public class EntityFactoryTest {
	
	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.factory.EntityFactory#getJsonFromEntity(cn.pku.ueba.model.Entity)}
	 * .
	 */
	@Test
	public void testGetJsonFromEntity() {

		if (UserFactoryTest.json.size() != 7)
			fail("getJsonFromEntity failed");
		if (HostFactoryTest.json.size() != 7)
			fail("getJsonFromEntity failed");
	}

}
