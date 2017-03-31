/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月30日 上午9:44:33
 */
package cn.pku.ueba.util;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
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
		String json = HttpClientUtil.httpclientGet(URLGet);
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		// 将json格式的string转化为Map，然后进行处理
		Map m = mapper.readValue(json, Map.class);

	}

}
