/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import cn.pku.ueba.configure.Configure;

/**
 * 封装的用于进行ES检索、索引、删除、修改等操作的类 <br>
 * 因为我们的操作都比较固化，所以为了减少代码量<br>
 * 
 * @author xixy10@foxmail.com
 */
public class GrayLogUtil {
	/**
	 * 最基础的setting对象
	 */
	static Settings settings = Settings.settingsBuilder().put("cluster.name", "graylog-es").build();
	/**
	 * 创建的client对象
	 */
	static Client client = null;

	/**
	 * 返回client对象
	 * 
	 * @return client
	 * @throws UnknownHostException
	 *             主机超找失败
	 */
	public static Client getClient() throws UnknownHostException {
		if (client == null) {
			client = TransportClient.builder().settings(settings).build().addTransportAddress(
					new InetSocketTransportAddress(InetAddress.getByName(Configure.getIp()), Configure.getPort()));
		}
		return client;

	}

	/*
	 * 查询语句
	 */
	/**
	 * 查询语句
	 * 
	 * @param index
	 *            index
	 * @param type
	 *            type
	 * @param searchtype
	 *            searchtype
	 * @param queryterm
	 *            搜索语句
	 * @param filter
	 *            过滤器
	 * @param size
	 *            一次返回的数目，最多10000
	 * @return 返回搜索结果，作为一个SearchResponse的List，因为可能结果超过10000，这时候就要搜索多次
	 * @throws UnknownHostException
	 *             主机查找失败
	 */
	public static List<SearchResponse> search(String index, String type, SearchType searchtype, QueryBuilder queryterm,
			QueryBuilder filter, int size) throws UnknownHostException {
		List<SearchResponse> result = new ArrayList<SearchResponse>();
		QueryBuilder filter1 = filter;

		Boolean searchagain = true;
		while (searchagain) {
			searchagain = false;

			SearchResponse response = null;
			if (filter != null)
				response = getClient().prepareSearch(index).setTypes(type).setSearchType(searchtype).setQuery(queryterm)
						.setPostFilter(filter).setPostFilter(filter1).setFrom(0).setSize(size).setExplain(true)
						.execute().actionGet();
			else if (filter1 != null)
				response = getClient().prepareSearch(index).setTypes(type).setSearchType(searchtype).setQuery(queryterm)
						.setPostFilter(filter1).setFrom(0).setSize(size).setExplain(true).execute().actionGet();
			else
				response = getClient().prepareSearch(index).setTypes(type).setSearchType(searchtype).setQuery(queryterm)
						.setFrom(0).setSize(size).setExplain(true).execute().actionGet();

			if (response.getHits().getHits().length == 10000) {

				// 如果需要继续fetch，那么就设置新的filter，然后构建新的search
				SearchHit hit = response.getHits().getHits()[9999];
				String timestamp = (String) hit.getSource().get("timestamp");
				System.out.println(DateUtil.getDateFromESDate(timestamp));
				// 设置新的时间filter
				filter1 = QueryBuilders.rangeQuery("timestamp").from(timestamp);
				searchagain = true;
			}

			result.add(response);
		}
		return result;
	}

	/**
	 * 索引语句
	 * 
	 * @param index
	 *            index
	 * @param type
	 *            type
	 * @param json
	 *            构造的需要存储的json
	 * @return 索引结果
	 * @throws UnknownHostException
	 *             主机查找失败
	 */
	public static IndexResponse index(String index, String type, Map<String, Object> json) throws UnknownHostException {

		IndexResponse response = getClient().prepareIndex(index, type).setSource(json).get();
		return response;

	}

	/**
	 * 删除语句
	 * 
	 * @param index
	 *            index
	 * @param type
	 *            type
	 * @param id
	 *            删除的id
	 * @return 删除结果
	 */
	public static DeleteResponse delete(String index, String type, String id) {
		DeleteResponse response = client.prepareDelete(index, type, id).get();
		return response;

	}

	/**
	 * 打印查询结果，用于调试
	 * 
	 * @param response
	 *            查询结果
	 */
	public static void printSearchResponse(SearchResponse response) {
		for (SearchHit hit : response.getHits().hits()) {
			Map<String, Object> source = hit.getSource();
			for (String str : source.keySet()) {
				System.out.println(str + source.get(str));
			}
		}

	}

	public static Settings getSettings() {
		return settings;
	}

	public static void setSettings(Settings settings) {
		GrayLogUtil.settings = settings;
	}

	public static void setClient(Client client) {
		GrayLogUtil.client = client;
	}

}
