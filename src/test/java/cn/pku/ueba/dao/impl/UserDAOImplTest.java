/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月28日 下午4:49:00
 */
package cn.pku.ueba.dao.impl;

import static org.junit.Assert.fail;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.pku.ueba.dao.factory.UserFactoryTest;
import cn.pku.ueba.model.User;

/**
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class UserDAOImplTest {
	UserDAOImpl ud = new UserDAOImpl();

	/**
	 * Test method for {@link cn.pku.ueba.dao.impl.UserDAOImpl#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		if (UserDAOImpl.getInstance() == null)
			fail("GetInstance failed");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.impl.UserDAOImpl#indexUser(cn.pku.ueba.model.User)}
	 * .
	 */
	@Test
	public void testIndexUser() {
		ud.indexUser(UserFactoryTest.user);
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.impl.UserDAOImpl#getUser(java.lang.String)}.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testGetUser() throws InterruptedException {
		Thread.sleep(1000);
		User user = ud.getUser(UserFactoryTest.user.getName());
		if (user == null)
			fail("UserDAO cannot getUser");

		if (!user.getName().equals(UserFactoryTest.user.getName()))
			fail("UserDAO cannot getUser");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.impl.UserDAOImpl#deleteUser(java.lang.String)}.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testDeleteUser() throws InterruptedException {
		ud.deleteUser(UserFactoryTest.user.getName());
		Thread.sleep(1000);
		User user = ud.getUser(UserFactoryTest.user.getName());
		if (user != null)
			fail("UserDAO cannot deleteuser");
	}

}
