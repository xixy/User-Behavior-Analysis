/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月1日 下午1:48:56
 */
package cn.pku.ueba.dao.factory;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.pku.ueba.model.User;

/**
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class UserFactoryTest {

	public static User user = UserFactory.getUser("张通");
	public static Map<String, Object> json = null;

	static {
		user = UserFactory.getUser("张通");
		user.setDepartment("北京大学软件工程中心");
		user.setAge(22);
		user.setSex("男");
		user.setJob("学生");
		json = UserFactory.getJsonFromUser(user);
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.factory.UserFactory#getUser(java.lang.String)}.
	 */
	@Test
	public void testGetUser() {
		if (user == null)
			fail("getUser failed");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.factory.UserFactory#getJsonFromUser(cn.pku.ueba.model.User)}
	 * .
	 */
	@Test
	public void testGetJsonFromUser() {
		if (json == null)
			fail("GetJsonFromUser failed");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.factory.UserFactory#getUserFromJson(java.util.Map)}
	 * .
	 */
	@Test
	public void testGetUserFromJson() {
		User user1 = UserFactory.getUserFromJson(json);
		if (!user1.getName().equals(user.getName()))
			fail("GetUserFromJson failed");
		if (user1.getId() != 2)
			fail("GetUserFromJson failed");
	}

}
