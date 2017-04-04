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
import cn.pku.ueba.resource.featurefield.FeatureField;

/**
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class FeatureVectorFactoryTest {
	public static Feature f = FeatureFactory.getFeature();
	public static FeatureVector fv = FeatureVectorFactory.getFeatureVector();

	static {
		f.setKey(FeatureField.count);
		f.setValue(100);
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.factory.FeatureVectorFactory#getFeatureVector()}.
	 */
	@Test
	public void testGetFeatureVector() {

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
		FeatureVectorFactory.addFeature(f, fv);
		if (fv.getFeatures().size() != 1)
			fail("AddFeatur failed");
	}

}
