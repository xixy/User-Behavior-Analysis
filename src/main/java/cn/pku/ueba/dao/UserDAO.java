/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao;

import cn.pku.ueba.model.User;

/**
 * 用户对象的持久化
 * 
 * @author xixy10@foxmail.com
 */
public interface UserDAO {
	/**
	 * 从数据库中提取用户
	 * 
	 * @param name
	 *            用户名称
	 * @return 用户对象
	 */
	public User getUser(String name);

	/**
	 * 从数据库中删除用户
	 * 
	 * @param name
	 *            用户名称
	 */
	public void deleteUser(String name);

	/**
	 * 将user持久化到graylog中
	 * 
	 * @param user
	 *            用户对象
	 */
	public void indexUser(User user);

}
