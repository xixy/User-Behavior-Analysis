/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao.factory;

import java.util.Map;

import cn.pku.ueba.model.User;
import cn.pku.ueba.util.JsonUtil;

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

	/**
	 * 将User实例转化为Json
	 * 
	 * @param user
	 *            User实例
	 * @return Json格式数据
	 */
	public static Map<String, Object> getJsonFromUser(User user) {
		return JsonUtil.getJsonFromModelInstance(user);

	}

	/**
	 * 将Json转化为User实例对象
	 * 
	 * @param json
	 *            Json格式数据
	 * @return User实例对象
	 */
	public static User getUserFromJson(Map<String, Object> json) {
		Object obj = JsonUtil.getModelInstanceFromJson(json, User.class);
		return (User) obj;

	}

}
