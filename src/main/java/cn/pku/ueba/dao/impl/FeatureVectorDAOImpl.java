/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月4日 下午4:20:35
 */
package cn.pku.ueba.dao.impl;

import java.net.UnknownHostException;
import java.util.Map;

import cn.pku.ueba.configure.Configure;
import cn.pku.ueba.dao.FeatureVectorDAO;
import cn.pku.ueba.dao.factory.FeatureVectorFactory;
import cn.pku.ueba.model.feature.FeatureVector;
import cn.pku.ueba.util.GrayLogUtil;

/**
 *
 */
public class FeatureVectorDAOImpl implements FeatureVectorDAO {

	private static FeatureVectorDAOImpl instance;

	/**
	 * 获取instance
	 * 
	 * @return 实例
	 */
	public static FeatureVectorDAOImpl getInstance() {
		if (instance == null)
			instance = new FeatureVectorDAOImpl();
		return instance;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see cn.pku.ueba.dao.FeatureVectorDAO#index(cn.pku.ueba.model.feature.
	 *      FeatureVector)
	 */
	public void index(FeatureVector fv) {
		// 转化为json对象
		Map<String, Object> json = FeatureVectorFactory.getJsonFromFeatureVector(fv);
		// 插入到graylog中
		try {
			GrayLogUtil.index(Configure.getIndex(), Configure.getFeaturetype(), json);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
