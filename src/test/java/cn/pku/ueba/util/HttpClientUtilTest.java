/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月30日 上午9:44:33
 */
package cn.pku.ueba.util;

import static org.junit.Assert.fail;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 */
@FixMethodOrder(MethodSorters.JVM) // 指定测试方法按定义的顺序执行
public class HttpClientUtilTest {
	String URLGet = "http://192.168.200.160:9200/graylog_0/message/_count";

	/**
	 * Test method for {@link cn.pku.ueba.util.HttpClientUtil#httpclientGet()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHttpclientGet() throws Exception {
		@SuppressWarnings("deprecation")
		String json = HttpClientUtil.httpclientGet(URLGet);
		if (json == null)
			fail("HttpClient failed");

	}

}
