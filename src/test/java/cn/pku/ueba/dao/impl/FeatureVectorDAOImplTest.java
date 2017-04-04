/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月4日 下午4:27:33
 */
package cn.pku.ueba.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.pku.ueba.dao.factory.FeatureVectorFactory;
import cn.pku.ueba.dao.factory.FeatureVectorFactoryTest;
import cn.pku.ueba.model.feature.FeatureVector;
import cn.pku.ueba.util.DateUtil;

/**
 *
 */
public class FeatureVectorDAOImplTest {

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.impl.FeatureVectorDAOImpl#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		if (FeatureVectorDAOImpl.getInstance() == null)
			fail("FeatureVectorDAOImpl#getInstance() failed");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.impl.FeatureVectorDAOImpl#index(cn.pku.ueba.model.feature.FeatureVector)}
	 * .
	 */
	@Test
	public void testIndex() {
		FeatureVector fv = FeatureVectorFactoryTest.fv;
		fv.setTimestamp(DateUtil.getESDate());
		FeatureVectorFactory.addFeature(FeatureVectorFactoryTest.f, fv);
		FeatureVectorDAOImpl.getInstance().index(fv);

	}

}
