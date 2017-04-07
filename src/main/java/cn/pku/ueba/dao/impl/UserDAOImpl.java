/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao.impl;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import cn.pku.ueba.configure.Configure;
import cn.pku.ueba.dao.UserDAO;
import cn.pku.ueba.dao.factory.UserFactory;
import cn.pku.ueba.model.User;
import cn.pku.ueba.util.GrayLogUtil;

public class UserDAOImpl implements UserDAO {

	private static UserDAOImpl instance;

	public static UserDAOImpl getInstance() {
		if (instance == null)
			instance = new UserDAOImpl();
		return instance;

	}

	public void indexUser(User user) {
		// 插入到graylog中
		Map<String, Object> json = UserFactory.getInstance().getJsonFromEntity(user);

		try {
			GrayLogUtil.index(Configure.getIndex(), Configure.getUsertype(), json);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public User getUser(String name) {
		User user = null;
		QueryBuilder queryterm = QueryBuilders.termQuery("name", name);
		List<SearchResponse> responseList = null;
		try {
			responseList = GrayLogUtil.search(Configure.getIndex(), Configure.getUsertype(),
					SearchType.DFS_QUERY_THEN_FETCH, queryterm, null, 1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 然后生成User对象
		for (SearchResponse response : responseList)
			if (response.getHits().getHits().length > 0)
				for (SearchHit hit : response.getHits().getHits()) {
					Map<String, Object> fields = hit.getSource();
					user = (User) UserFactory.getInstance().getEntityFromJson(fields);
					break;
				}

		return user;
	}

	public void deleteUser(String name) {
		QueryBuilder queryterm = QueryBuilders.termQuery("name", name);
		List<SearchResponse> responseList = null;
		try {
			responseList = GrayLogUtil.search(Configure.getIndex(), Configure.getUsertype(),
					SearchType.DFS_QUERY_THEN_FETCH, queryterm, null, 1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 然后生成User对象
		for (SearchResponse response : responseList)
			if (response.getHits().getHits().length > 0)
				for (SearchHit hit : response.getHits().getHits()) {
					String id = hit.getId();
					GrayLogUtil.delete(Configure.getIndex(), Configure.getUsertype(), id);
				}
	}

}
