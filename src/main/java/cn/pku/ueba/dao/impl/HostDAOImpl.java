package cn.pku.ueba.dao.impl;

/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
import java.net.UnknownHostException;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import cn.pku.ueba.configure.Configure;
import cn.pku.ueba.dao.HostDAO;
import cn.pku.ueba.dao.factory.HostFactory;
import cn.pku.ueba.model.Host;
import cn.pku.ueba.resource.HostLogField;
import cn.pku.ueba.util.GrayLogUtil;

/**
 * HostDAO的实现
 */
public class HostDAOImpl implements HostDAO {

	private static HostDAOImpl instance;

	/**
	 * 获取该对象
	 * 
	 * @return HostDAOImpl对象
	 */
	public static HostDAOImpl getInstance() {
		if (instance == null)
			instance = new HostDAOImpl();
		return instance;
	}

	/**
	 * @see cn.pku.ueba.dao.HostDAO#indexHost(cn.pku.ueba.model.Host)
	 */
	public void indexHost(Host host) {
		// 转化为json对象
		Map<String, Object> json = HostFactory.getJsonFromHost(host);
		// 插入到graylog中
		try {
			GrayLogUtil.index(Configure.getIndex(), Configure.getHosttype(), json);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see cn.pku.ueba.dao.HostDAO#getHost(java.lang.String)
	 */
	public Host getHost(String ip) {

		Host host = null;
		QueryBuilder queryterm = QueryBuilders.termQuery(HostLogField.ip, ip);
		SearchResponse response = null;
		try {
			response = GrayLogUtil.search(Configure.getIndex(), Configure.getHosttype(),
					SearchType.DFS_QUERY_THEN_FETCH, queryterm, null, 1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 然后生成Host对象
		if (response.getHits().getHits().length > 0)
			for (SearchHit hit : response.getHits().getHits()) {
				Map<String, Object> fields = hit.getSource();
				host = HostFactory.getHostFromJson(fields);
				break;
			}

		return host;
	}

	/**
	 * 
	 * @see cn.pku.ueba.dao.HostDAO#deleteHost(java.lang.String)
	 */
	public void deleteHost(String ip) {
		QueryBuilder queryterm = QueryBuilders.termQuery(HostLogField.ip, ip);
		SearchResponse response = null;
		try {
			response = GrayLogUtil.search(Configure.getIndex(), Configure.getHosttype(),
					SearchType.DFS_QUERY_THEN_FETCH, queryterm, null, 1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		if (response.getHits().getHits().length > 0)
			for (SearchHit hit : response.getHits().getHits()) {
				String id = hit.getId();
				GrayLogUtil.delete(Configure.getIndex(), Configure.getHosttype(), id);
			}

	}

}
