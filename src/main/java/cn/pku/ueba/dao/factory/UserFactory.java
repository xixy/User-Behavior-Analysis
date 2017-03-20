package cn.pku.ueba.dao.factory;

import cn.pku.ueba.model.EntityType;
import cn.pku.ueba.model.User;

public class UserFactory {
	static int userid = 0;

	public static User getUser(String name) {
		User user = new User();
		user.setId(++userid);
		user.setName(name);
		user.setType(EntityType.user);
		user.setRiskscore((double) 60);
		return user;
	}

}
