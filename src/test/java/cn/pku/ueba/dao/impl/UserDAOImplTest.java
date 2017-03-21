package cn.pku.ueba.dao.impl;

import static org.junit.Assert.fail;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.pku.ueba.dao.factory.UserFactory;
import cn.pku.ueba.model.User;

@FixMethodOrder(MethodSorters.JVM)
public class UserDAOImplTest {
	UserDAOImpl ud = new UserDAOImpl();

	@Before
	public void setup() {

	}

	@Test
	public void testIndexUser() {
		User user = UserFactory.getUser("习翔宇");
		user.setAge(23);
		user.setDepartment("研发中心");
		user.setSex("男");
		user.setJob("博士生");
		ud.indexUser(user);
	}

	@Test
	public void testGetUser() throws UnknownHostException {
		User user = ud.getUser("习翔宇");
		if (user == null)
			fail("UserDAO cannot getUser");
	}

	@Test
	public void testDeleteUser() throws UnknownHostException {
		ud.deleteUser("习翔宇");
		User user = ud.getUser("test");
		if (user != null)
			fail("UserDAO cannot getUser");
	}

}
