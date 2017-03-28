/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月27日 下午4:38:37
 */
package cn.pku.ueba.dao.factory;

import cn.pku.ueba.model.feature.Feature;
import cn.pku.ueba.model.feature.FeatureVector;

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

}
