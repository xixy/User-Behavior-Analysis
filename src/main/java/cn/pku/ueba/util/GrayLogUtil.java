package cn.pku.ueba.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
import org.elasticsearch.search.SearchHit;

public class GrayLogUtil {
	static String es_index = "graylog_0";
	static String es_type = "message";
	static String ip_address = "192.168.200.160";
	static int port = 9300;

	static Settings settings = Settings.settingsBuilder().put("cluster.name", "graylog-es").build();
	static Client client = null;

	public static Client getClient() throws UnknownHostException {
		if (client == null) {
			client = TransportClient.builder().settings(settings).build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip_address), port));
		}
		return client;

	}

	/*
	 * 查询语句
	 */
	public static SearchResponse search(String index, String type, SearchType searchtype, QueryBuilder queryterm,
			QueryBuilder filter, int size) throws UnknownHostException {
		SearchResponse response = null;
		if (filter != null)
			response = getClient().prepareSearch(index).setTypes(type).setSearchType(searchtype).setQuery(queryterm)
					.setPostFilter(filter).setFrom(0).setSize(size).setExplain(true).execute().actionGet();
		else
			response = getClient().prepareSearch(index).setTypes(type).setSearchType(searchtype).setQuery(queryterm)
					.setFrom(0).setSize(size).setExplain(true).execute().actionGet();
		return response;
	}

	/*
	 * 存储语句
	 */
	public static IndexResponse index(String index, String type, Map<String, Object> json) throws UnknownHostException {

		IndexResponse response = getClient().prepareIndex(index, type).setSource(json).get();
		return response;

	}

	public static DeleteResponse delete(String index, String type, String id) {
		DeleteResponse response = client.prepareDelete(index, type, id).get();
		return response;

	}

	/*
	 * 打印查询结果
	 */
	public static void printSearchResponse(SearchResponse response) {
		for (SearchHit hit : response.getHits().hits()) {
			Map<String, Object> source = hit.getSource();
			for (String str : source.keySet()) {
				System.out.println(str + source.get(str));
			}
		}

	}

	public static String getEs_index() {
		return es_index;
	}

	public static void setEs_index(String es_index) {
		GrayLogUtil.es_index = es_index;
	}

	public static String getEs_type() {
		return es_type;
	}

	public static void setEs_type(String es_type) {
		GrayLogUtil.es_type = es_type;
	}

	public static String getIp_address() {
		return ip_address;
	}

	public static void setIp_address(String ip_address) {
		GrayLogUtil.ip_address = ip_address;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		GrayLogUtil.port = port;
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
