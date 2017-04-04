/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月27日 下午4:38:37
 */
package cn.pku.ueba.dao.factory;

import java.util.Map;

import cn.pku.ueba.model.feature.Feature;
import cn.pku.ueba.model.feature.FeatureVector;
import cn.pku.ueba.util.JsonUtil;

/**
 * 特征向量factory
 * 
 * @author xixy10@foxmail.com
 */
public class FeatureVectorFactory {
	/**
	 * 返回FeatureVector实例
	 * 
	 * @return FeatureVector实例
	 */
	public static FeatureVector getFeatureVector() {
		return new FeatureVector();
	}

	/**
	 * 向特征向量中添加特征
	 * 
	 * @param feature
	 *            特征
	 * @param fv
	 *            特征向量
	 */
	public static void addFeature(Feature feature, FeatureVector fv) {
		fv.getFeatures().add(feature);
	}

	/**
	 * 将FeatureVector实例转化为Json
	 * 
	 * @param fv
	 *            FeatureVector实例
	 * @return Json格式数据
	 */
	public static Map<String, Object> getJsonFromFeatureVector(FeatureVector fv) {
		return JsonUtil.getJsonFromModelInstance(fv);

	}

	/**
	 * 将Json转化为FeatureVector实例对象
	 * 
	 * @param json
	 *            Json格式数据
	 * @return FeatureVector实例对象
	 */
	public static FeatureVector getFeatureVectorFromJson(Map<String, Object> json) {
		Object obj = JsonUtil.getModelInstanceFromJson(json, FeatureVector.class);
		return (FeatureVector) obj;

	}

}
