/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月7日 上午9:48:27
 */
package cn.pku.ueba.dao.factory;

import java.util.Map;

import cn.pku.ueba.model.Entity;
import cn.pku.ueba.util.JsonUtil;

/**
 * EntityFactory作为基类
 * 
 * @author xixy10@foxmail.com
 */
public abstract class EntityFactory {

	/**
	 * 新建实体对象对象
	 * 
	 * @return 对应类型的实体对象
	 */
	public abstract Entity getEntity();

	/**
	 * 将Entity实例转化为Json
	 * 
	 * @param entity
	 *            entity实例
	 * @return Json格式数据
	 */
	public Map<String, Object> getJsonFromEntity(Entity entity) {
		return JsonUtil.getJsonFromModelInstance(entity);
	}

	/**
	 * 将Json转化为Entity实例对象
	 * 
	 * @param json
	 *            Json格式数据
	 * @return Entity实例对象
	 */
	public abstract Entity getEntityFromJson(Map<String, Object> json);

}
