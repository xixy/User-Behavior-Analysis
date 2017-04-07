/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月7日 上午10:52:12
 */
package cn.pku.ueba.dao.factory;

import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Test;

import cn.pku.ueba.model.Entity;
import cn.pku.ueba.model.User;

/**
 *
 */
public class UserFactoryTest {
	
	public static User user = (User) UserFactory.getInstance().getEntity();
	public static Map<String, Object> json = null;
	static {
		user.setName("习翔宇");
		user.setDepartment("北京大学软件工程中心");
		user.setAge(24);
		user.setSex("男");
		user.setJob("学生");
		json = UserFactory.getInstance().getJsonFromEntity(user);
	}

	/**
	 * Test method for {@link cn.pku.ueba.dao.factory.UserFactory#getEntityFromJson(java.util.Map)}.
	 */
	@Test
	public void testGetEntityFromJson() {
		Entity entity = UserFactory.getInstance().getEntityFromJson(json);
		if (!(entity instanceof User))
			fail("getEntityFromJson failed");
	}

}
