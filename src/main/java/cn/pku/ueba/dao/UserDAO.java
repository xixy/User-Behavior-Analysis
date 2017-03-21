package cn.pku.ueba.dao;

import cn.pku.ueba.model.User;

public interface UserDAO {
	// 从数据库中提取用户
	public User getUser(String name);

	// 创建用户并存储到数据库中
	// public User createUser(String name);

	// 从数据库中删除用户
	public void deleteUser(String name);

	// 将user存储到graylog中
	public void indexUser(User user);

}
