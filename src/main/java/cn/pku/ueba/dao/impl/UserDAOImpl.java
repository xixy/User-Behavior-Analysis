package cn.pku.ueba.dao.impl;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import cn.pku.ueba.dao.UserDAO;
import cn.pku.ueba.dao.factory.UserFactory;
import cn.pku.ueba.model.User;
import cn.pku.ueba.resource.UserLogField;
import cn.pku.ueba.util.GrayLogUtil;

public class UserDAOImpl implements UserDAO {
	static String index = "graylog_0";
	static String type = "user";

	/*
	 * 创建用户并且存入到graylog中(non-Javadoc)
	 * 
	 * @see cn.pku.ueba.dao.UserDAO#createUser(java.lang.String)
	 */
	public void indexUser(User user) {
		// 插入到graylog中
		Map<String, Object> json = new HashMap<String, Object>();
		json.put(UserLogField.name, user.getName());
		json.put(UserLogField.age, user.getAge());
		json.put(UserLogField.sex, user.getSex());
		json.put(UserLogField.department, user.getDepartment());
		json.put(UserLogField.job, user.getJob());
		json.put(UserLogField.id, user.getId());
		json.put(UserLogField.risk, user.getRiskscore());

		try {
			GrayLogUtil.index(index, type, json);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User getUser(String name) {
		User user = null;
		QueryBuilder queryterm = QueryBuilders.termQuery("name", name);
		SearchResponse response = null;
		try {
			response = GrayLogUtil.search(index, type, SearchType.DFS_QUERY_THEN_FETCH, queryterm, null, 1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 然后生成User对象
		if (response.getHits().getHits().length > 0)
			for (SearchHit hit : response.getHits().getHits()) {
				Map<String, Object> fields = hit.getSource();
				// 名字
				String username = (String) fields.get(UserLogField.name);
				user = UserFactory.getUser(username);
				// 性别
				String sex = (String) fields.get(UserLogField.sex);
				user.setSex(sex);
				// 部门
				String department = (String) fields.get(UserLogField.department);
				user.setDepartment(department);
				// job
				String job = (String) fields.get(UserLogField.job);
				user.setJob(job);
				// age
				Integer age = (Integer) fields.get(UserLogField.age);
				user.setAge(age);
				// 风险值
				Double risk = (Double) fields.get(UserLogField.risk);
				user.setRiskscore(risk);
				// id
				Integer id = (Integer) fields.get(UserLogField.id);
				user.setId(id);
				break;

			}

		return user;
	}

	/*
	 * 删除用户(non-Javadoc)
	 * 
	 * @see cn.pku.ueba.dao.UserDAO#deleteUser(java.lang.String)
	 */
	public void deleteUser(String name) {
		QueryBuilder queryterm = QueryBuilders.termQuery("name", name);
		SearchResponse response = null;
		try {
			response = GrayLogUtil.search(index, type, SearchType.DFS_QUERY_THEN_FETCH, queryterm, null, 1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 然后生成User对象
		if (response.getHits().getHits().length > 0)
			for (SearchHit hit : response.getHits().getHits()) {
				String id = hit.getId();
				GrayLogUtil.delete(index, type, id);
			}
	}

}
