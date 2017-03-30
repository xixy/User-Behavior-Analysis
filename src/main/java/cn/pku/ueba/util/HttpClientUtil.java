/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月30日 上午9:43:25
 */
package cn.pku.ueba.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 */
public class HttpClientUtil {

	public static String httpclientGet(String URL) throws Exception {

		// 创建HttpClient对象
		HttpClient client = HttpClients.createDefault();

		// 创建GET请求（在构造器中传入URL字符串即可）
		HttpGet get = new HttpGet(URL);
		// HttpGet get = new
		// HttpGet("http://192.168.200.160:9200/graylog_0/message/_count");

		// 调用HttpClient对象的execute方法获得响应
		HttpResponse response = client.execute(get);

		// 调用HttpResponse对象的getEntity方法得到响应实体
		HttpEntity httpEntity = response.getEntity();

		// 使用EntityUtils工具类得到响应的字符串表示
		String result = EntityUtils.toString(httpEntity, "utf-8");
		return result;
	}

}
