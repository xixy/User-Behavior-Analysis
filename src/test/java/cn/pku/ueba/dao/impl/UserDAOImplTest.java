/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月28日 下午4:49:00
 */
package cn.pku.ueba.dao.impl;

import static org.junit.Assert.fail;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.pku.ueba.dao.factory.UserFactory;
import cn.pku.ueba.model.User;

/**
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class UserDAOImplTest {
	UserDAOImpl ud = new UserDAOImpl();

//	@Test
//	public void test() {
//		QueryBuilder queryterm = QueryBuilders.termQuery("name", "xixy");
//		System.out.println(queryterm.toString());
//	}

	/**
	 * Test method for {@link cn.pku.ueba.dao.impl.UserDAOImpl#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.impl.UserDAOImpl#indexUser(cn.pku.ueba.model.User)}
	 * .
	 */
	@Test
	public void testIndexUser() {
		User user = UserFactory.getUser("习翔宇");
		user.setAge(23);
		user.setDepartment("研发中心");
		user.setSex("男");
		user.setJob("博士生");
		ud.indexUser(user);
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.impl.UserDAOImpl#getUser(java.lang.String)}.
	 */
	@Test
	public void testGetUser() {
		User user = ud.getUser("习翔宇");
		if (user == null)
			fail("UserDAO cannot getUser");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.impl.UserDAOImpl#deleteUser(java.lang.String)}.
	 */
	@Test
	public void testDeleteUser() {
		ud.deleteUser("习翔宇");
		User user = ud.getUser("test");
		if (user != null)
			fail("UserDAO cannot getUser");
	}

}
