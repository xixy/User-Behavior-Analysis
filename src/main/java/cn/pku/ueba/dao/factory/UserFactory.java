/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao.factory;

import cn.pku.ueba.model.User;

public class UserFactory {
	static int userid = 0;

	public static User getUser(String name) {
		User user = new User();
		user.setId(++userid);
		user.setName(name);
		user.setRiskscore((double) 60);
		return user;
	}

}
