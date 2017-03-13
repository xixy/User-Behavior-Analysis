package PKU.UEBA;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/* 从ElasticSearch中读取数据并生成ActivityRecord的集合
 * @author xixiangyu
 * @Version 0.1
 */
public class ActivityRecordGenerator {

	public static Set<ActivityRecord> getActivityRecordFromES() {
		Set<ActivityRecord> res = new HashSet<ActivityRecord>();

		return res;
	}

	public static void main(String[] args) {
		try {
			/* 创建客户端 */
			// client startup
			@SuppressWarnings({ "resource", "unchecked" })
			Settings settings = Settings.builder().put("cluster.name", "UBA").put("client.transport.sniff", true).build();
			TransportClient client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
			GetResponse response = client.prepareGet("pkulab", "stu", "AVrBY_Biq_5jx39izzQQ").get();
			System.out.println(response.getSourceAsString());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
