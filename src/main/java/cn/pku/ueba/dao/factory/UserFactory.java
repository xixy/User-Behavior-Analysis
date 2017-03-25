/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao.factory;

import cn.pku.ueba.model.User;

/**
 * 用户Factory
 */
public class UserFactory {
	/**
	 * 用户id自增
	 */
	static int userid = 0;

	/**
	 * 返回一个用户对象
	 * 
	 * @param name
	 *            用户名称
	 * @return 信息不完整、需要填充的用户对象
	 */
	public static User getUser(String name) {
		User user = new User();
		user.setId(++userid);
		user.setName(name);
		user.setRiskscore((double) 60);
		return user;
	}

}
