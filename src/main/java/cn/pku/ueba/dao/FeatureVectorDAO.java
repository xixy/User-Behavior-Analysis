/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月4日 下午4:15:09
 */
package cn.pku.ueba.dao;

import cn.pku.ueba.model.feature.FeatureVector;

/**
 * 用来处理特征向量的持久化
 * 
 * @author xixy10@foxmail.com
 */
public interface FeatureVectorDAO {
	/**
	 * 对特征向量进行持久化，存入type为feature
	 * 
	 * @param fv
	 *            特征向量
	 */
	public void index(FeatureVector fv);

}
