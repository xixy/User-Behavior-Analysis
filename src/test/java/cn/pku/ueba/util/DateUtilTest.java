/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月4日 下午2:09:51
 */
package cn.pku.ueba.util;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

/**
 * DateUtil的测试文件
 * 
 * @author xixy10@foxmail.com
 */
public class DateUtilTest {

	private static String esDate = DateUtil.getESDate();

	/**
	 * Test method for {@link cn.pku.ueba.util.DateUtil#getESDate()}.
	 */
	@Test
	public void testGetESDate() {
		String date = DateUtil.getESDate();
		if (date == null) {
			fail("getESDate failed");
		}
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.util.DateUtil#toESDate(java.util.Date)}.
	 */
	@Test
	public void testToESDate() {
		String date = DateUtil.toESDate(new Date());
		if (date == null)
			fail("toESDate failed");
	}

	/**
	 * Test method for {@link cn.pku.ueba.util.DateUtil#getLastDayESDate(int)}.
	 */
	@Test
	public void testGetLastDayESDate() {
		String date = DateUtil.getLastDayESDate(5);
		if (date == null)
			fail("GetLastDayESDate");
	}

	/**
	 * Test method for {@link cn.pku.ueba.util.DateUtil#getLastDate(int)}.
	 */
	@Test
	public void testGetYesterdayDate() {
		Date date = DateUtil.getLastDate(2);
		if (date == null)
			fail("getYesterdayDate failed");

	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.util.DateUtil#getDateFromESDate(java.lang.String)}.
	 */
	@Test
	public void testGetDateFromESDate() {
		Date date = DateUtil.getDateFromESDate(esDate);
		if (!esDate.equals(DateUtil.toESDate(date)))
			fail("getDateFromESDate failed");
	}

}
