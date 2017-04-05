/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月5日 下午3:34:22
 */
package cn.pku.ueba.service.generator;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

/**
 *
 */
public class ActivityRecordGeneratorUnitTest {

	ActivityRecordGeneratorUnit unit = new ActivityRecordGeneratorUnit();

	/**
	 * Test method for
	 * {@link cn.pku.ueba.service.generator.ActivityRecordGeneratorUnit#generateMaterialForAnalysis()}
	 * .
	 */
	@Test
	@Ignore
	public void testGenerateMaterialForAnalysis() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.service.generator.ActivityRecordGeneratorUnit#analysis(java.util.List)}
	 * .
	 */
	@Test
	@Ignore
	public void testAnalysis() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.service.generator.ActivityRecordGeneratorUnit#generateFilter()}
	 * .
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGenerateFilter() {
		unit.date = new Date("Wed Apr 05 17:10:01 CST 2017");
		unit.generateFilter();
		if (!unit.filter.toString().contains("2017-04-06 07:09:00.000"))
			fail("GenerateFilter failed");
		if (!unit.filter.toString().contains("2017-04-06 07:10:00.000"))
			fail("GenerateFilter failed");

	}

}
