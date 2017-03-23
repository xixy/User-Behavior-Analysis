package cn.pku.ueba.dao.impl;

/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import cn.pku.ueba.dao.HostDAO;
import cn.pku.ueba.dao.factory.HostFactory;
import cn.pku.ueba.model.Host;
import cn.pku.ueba.model.HostType;
import cn.pku.ueba.resource.HostLogField;
import cn.pku.ueba.util.GrayLogUtil;

public class HostDAOImpl implements HostDAO {
	static String index = "graylog_0";
	static String type = "host";

	private static HostDAOImpl instance;

	public static HostDAOImpl getInstance() {
		if (instance == null)
			instance = new HostDAOImpl();
		return instance;
	}

	public void indexHost(Host host) {
		// 插入到graylog中
		Map<String, Object> json = new HashMap<String, Object>();
		json.put(HostLogField.name, host.getName());
		json.put(HostLogField.ip, host.getIp());
		json.put(HostLogField.macaddress, host.getMacaddress());
		json.put(HostLogField.department, host.getDepartment());
		json.put(HostLogField.id, host.getId());
		json.put(HostLogField.risk, host.getRiskscore());
		json.put(HostLogField.type, host.getType());

		try {
			GrayLogUtil.index(index, type, json);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 从数据库中获取Host(non-Javadoc)
	 * 
	 * @see cn.pku.ueba.dao.HostDAO#getHost(java.lang.String)
	 */
	public Host getHost(String ip) {

		Host host = null;
		QueryBuilder queryterm = QueryBuilders.termQuery(HostLogField.ip, ip);
		SearchResponse response = null;
		try {
			response = GrayLogUtil.search(index, type, SearchType.DFS_QUERY_THEN_FETCH, queryterm, null, 1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 然后生成Host对象
		if (response.getHits().getHits().length > 0)
			for (SearchHit hit : response.getHits().getHits()) {
				Map<String, Object> fields = hit.getSource();
				// name
				String name = (String) fields.get(HostLogField.name);
				host = HostFactory.getHost(name);

				// department
				String department = (String) fields.get(HostLogField.department);
				host.setDepartment(department);
				// risk
				Double risk = (Double) fields.get(HostLogField.risk);
				host.setRiskscore(risk);
				// id
				Integer id = (Integer) fields.get(HostLogField.id);
				host.setId(id);
				// ip
				host.setIp(ip);
				// mac address
				String macaddress = (String) fields.get(HostLogField.macaddress);
				host.setMacaddress(macaddress);
				// mac address
				HostType type = HostType.valueOf((String) fields.get(HostLogField.type));
				host.setType(type);
				break;

			}

		return host;
	}

	/*
	 * 从数据库中删除后身体(non-Javadoc)
	 * 
	 * @see cn.pku.ueba.dao.HostDAO#deleteHost(java.lang.String)
	 */
	public void deleteHost(String ip) {
		QueryBuilder queryterm = QueryBuilders.termQuery(HostLogField.ip, ip);
		SearchResponse response = null;
		try {
			response = GrayLogUtil.search(index, type, SearchType.DFS_QUERY_THEN_FETCH, queryterm, null, 1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		if (response.getHits().getHits().length > 0)
			for (SearchHit hit : response.getHits().getHits()) {
				String id = hit.getId();
				GrayLogUtil.delete(index, type, id);
			}

	}

}
