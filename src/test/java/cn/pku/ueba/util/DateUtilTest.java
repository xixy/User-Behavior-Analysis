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

	/**
	 * Test method for {@link cn.pku.ueba.util.DateUtil#getSharpTimeInMinute()}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetSharpTimeInMinute() {
		Date date = new Date();
		Date date1 = DateUtil.getSharpTimeInMinute(date);
		if (date1.getMinutes() != date.getMinutes())
			fail("GetSharpTimeInHour() failed");
		if (date1.getSeconds() != 0)
			fail("GetSharpTimeInHour() failed");
		if (!String.valueOf(date1.getTime()).endsWith("0000"))
			fail("GetSharpTimeInHour() failed");
	}

	/**
	 * Test method for {@link cn.pku.ueba.util.DateUtil#getSharpTimeInHour()}.
	 */
	@Test
	public void testGetSharpTimeInHour() {
		@SuppressWarnings("deprecation")
		Date date = new Date("Tue Mar 14 08:38:15 CST 2017");
		Date date1 = DateUtil.getSharpTimeInHour(date);
		if (!String.valueOf(date1.getTime()).endsWith("00000000"))
			fail("GetSharpTimeInHour() failed");
	}

	/**
	 * Test method for {@link cn.pku.ueba.util.DateUtil#getLastMinuteDate(int)}.
	 */
	@Test
	public void testGetLastMinuteDate() {
		Date date = new Date();
		System.out.println(date);
		Date date1 = DateUtil.getLastMinuteDate(date, 60);
		System.out.println(date1);
		long interval = date.getTime() - date1.getTime();
		if (interval != 60 * 60 * 1000)
			fail("GetLastMinuteDate failed");

	}

}
