/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月5日 下午3:34:22
 */
package cn.pku.ueba.service.generator;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 */
@SuppressWarnings("deprecation")
@FixMethodOrder(MethodSorters.JVM)
public class ActivityRecordGeneratorUnitTest {

	private static ActivityRecordGeneratorUnit unit = new ActivityRecordGeneratorUnit();
	private static List<SearchResponse> result = null;

	static {

		// Tue Mar 14 08:38:15 CST 2017
		unit.date = new Date("Tue Mar 12 19:49:15 CST 2017");
		unit.generateFilter();
		result = unit.generateMaterialForAnalysis();
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.service.generator.ActivityRecordGeneratorUnit#generateFilter()}
	 * .
	 */
	@Test
	public void testGenerateFilter() {

		if (!unit.filter.toString().contains("2017-03-13 09:48:00.000"))
			fail("GenerateFilter failed");
		if (!unit.filter.toString().contains("2017-03-13 09:49:00.000"))
			fail("GenerateFilter failed");

	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.service.generator.ActivityRecordGeneratorUnit#generateMaterialForAnalysis()}
	 * .
	 */
	@Test
	public void testGenerateMaterialForAnalysis() {

		if (result.isEmpty())
			fail("testGenerateMaterialForAnalysis failed");
		// System.out.println(result.size());
		// for (SearchResponse response : result) {
		// System.out.println(response.getHits().getHits().length);
		// for (SearchHit hit : response.getHits().getHits()) {
		// Map<String, Object> source = hit.getSource();
		// System.out.println(source.get("timestamp"));
		// }
		// }
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.service.generator.ActivityRecordGeneratorUnit#analysis(java.util.List)}
	 * .
	 */
	@Test
	@Ignore
	public void testAnalysis() {
		unit.analysis(result);
	}

}
