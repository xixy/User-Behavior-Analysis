package cn.pku.ueba.dao.impl;

import cn.pku.ueba.dao.UserDAO;
import cn.pku.ueba.dao.factory.UserFactory;
import cn.pku.ueba.model.User;

public class UserDAOImpl implements UserDAO {
	static String index = "GrayLog_0";
	static String type = "message";

	public User createUser(String name) {
		User user = UserFactory.getUser(name);
		// 插入到graylog中

		return user;
	}

	public User getUser(String name) {

		return null;
	}

}
