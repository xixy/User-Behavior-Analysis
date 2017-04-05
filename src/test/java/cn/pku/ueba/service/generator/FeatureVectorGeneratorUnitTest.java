/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月5日 下午5:16:54
 */
package cn.pku.ueba.service.generator;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

/**
 *
 */
public class FeatureVectorGeneratorUnitTest {

	FeatureVectorGeneratorUnit unit = new FeatureVectorGeneratorUnit();

	/**
	 * Test method for
	 * {@link cn.pku.ueba.service.generator.FeatureVectorGeneratorUnit#generateMaterialForAnalysis()}
	 * .
	 */
	@Test
	public void testGenerateMaterialForAnalysis() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.service.generator.FeatureVectorGeneratorUnit#analysis(java.util.List)}
	 * .
	 */
	@Test
	public void testAnalysis() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.service.generator.FeatureVectorGeneratorUnit#generateFilter()}
	 * .
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGenerateFilter() {
		unit.date = new Date("Wed Apr 05 17:10:01 CST 2017");
		unit.generateFilter();
		if (!unit.filter.toString().contains("2017-04-06 06:00:00.000"))
			fail("GenerateFilter failed");
		if (!unit.filter.toString().contains("2017-04-06 07:00:00.000"))
			fail("GenerateFilter failed");

	}

}
