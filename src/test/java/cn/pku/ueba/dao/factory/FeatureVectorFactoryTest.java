/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月28日 下午2:25:00
 */
package cn.pku.ueba.dao.factory;

import static org.junit.Assert.fail;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.pku.ueba.model.feature.Feature;
import cn.pku.ueba.model.feature.FeatureVector;

/**
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class FeatureVectorFactoryTest {

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.factory.FeatureVectorFactory#getFeatureVector()}.
	 */
	@Test
	public void testGetFeatureVector() {
		FeatureVector fv = FeatureVectorFactory.getFeatureVector();
		if (fv == null)
			fail("GetFeatureVector failed");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.factory.FeatureVectorFactory#addFeature(cn.pku.ueba.model.feature.Feature, cn.pku.ueba.model.feature.FeatureVector)}
	 * .
	 */
	@Test
	public void testAddFeature() {

		FeatureVector fv = FeatureVectorFactory.getFeatureVector();
		Feature f = FeatureFactory.getFeature();
		FeatureVectorFactory.addFeature(f, fv);
		if (fv.getFeatures().size() != 1)
			fail("AddFeatur failed");
	}

}
