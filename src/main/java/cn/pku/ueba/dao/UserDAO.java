package cn.pku.ueba.dao;

import cn.pku.ueba.model.User;

public interface UserDAO {
	// 从数据库中提取用户
	public User getUser(String name);

	// 创建用户并存储到数据库中
	public User createUser(String name);

}
