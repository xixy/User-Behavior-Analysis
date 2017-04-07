/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月7日 上午10:33:03
 */
package cn.pku.ueba.dao.factory;

import java.util.Map;

import cn.pku.ueba.model.Entity;
import cn.pku.ueba.model.User;
import cn.pku.ueba.util.JsonUtil;

/**
 * UserFactory
 * 
 * @author xixy10@foxmail.com
 */
public class UserFactory extends EntityFactory {

	/**
	 * 用户id自增
	 */
	static int userid = 0;
	
	private static UserFactory instance;

	public static UserFactory getInstance() {
		if (instance == null)
			instance = new UserFactory();
		return instance;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see cn.pku.ueba.dao.factory.EntityFactory#getEntity()
	 */
	@Override
	public Entity getEntity() {
		User user = new User();
		user.setId(++userid);
		user.setRiskscore((double) 60);
		return user;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see cn.pku.ueba.dao.factory.EntityFactory#getEntityFromJson(java.util.Map,
	 *      cn.pku.ueba.model.EntityType)
	 */
	@Override
	public Entity getEntityFromJson(Map<String, Object> json) {
		Object obj = JsonUtil.getModelInstanceFromJson(json, User.class);
		return (User) obj;
	}

}
