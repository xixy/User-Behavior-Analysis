/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月28日 下午4:56:19
 */
package cn.pku.ueba.util;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

/**
 *
 */
public class RawLogUtilTest {

	/**
	 * Test method for
	 * {@link cn.pku.ueba.util.RawLogUtil#getActivityTypeFromRawLogItem(java.util.Map)}
	 * .
	 */
	@Test
	@Ignore
	public void testGetActivityTypeFromRawLogItem() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.util.RawLogUtil#isBoolIp(java.lang.String)}.
	 */
	@Test
	public void testIsBoolIp() {
		if (!RawLogUtil.isBoolIp("192.168.100.2"))
			fail("isBoolIP wrong");
	}

}
