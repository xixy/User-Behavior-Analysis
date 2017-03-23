/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import cn.pku.ueba.dao.ActivityRecordDAO;
import cn.pku.ueba.dao.factory.ARFFactory;
import cn.pku.ueba.model.User;
import cn.pku.ueba.model.activity.ActivityRecord;
import cn.pku.ueba.model.activity.ActivityType;
import cn.pku.ueba.resource.ADLogField;
import cn.pku.ueba.util.DateUtil;
import cn.pku.ueba.util.GrayLogUtil;
import cn.pku.ueba.util.RawLogUtil;

public class ActivityRecordDAOImpl implements ActivityRecordDAO {

	static String index = "graylog_0";
	static String type = "message";

	/*
	 * 将活动存储到GrayLog中，这个方法基本上不需要修改(non-Javadoc)
	 * 
	 * @see cn.pku.ueba.dao.ActivityRecordDAO#index(cn.pku.ueba.model.activity.
	 * ActivityRecord)
	 */
	public void index(ActivityRecord activityRecord) {
		Map<String, Object> json = ARFFactory.getActivityRecordFactoryInstance(activityRecord.getType())
				.getJsonFromActivityRecord(activityRecord);
		try {
			GrayLogUtil.index(index, type, json);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	/*
	 * 获取用户过去几天的活动 interval = -1表示所有活动；否则就是过去inteval天的活动 用户可能有多种活动 (non-Javadoc)
	 * 
	 * @see
	 * cn.pku.ueba.dao.ActivityRecordDAO#getActivityRecordByUser(cn.pku.ueba.
	 * model.User, int)
	 */
	public List<ActivityRecord> getActivityRecordByUser(User user, int interval) {
		List<ActivityRecord> ars = new ArrayList<ActivityRecord>();
		QueryBuilder queryterm = QueryBuilders.termQuery("user", user.getName());
		SearchResponse response = null;
		QueryBuilder filter = null;
		if (interval != -1)
			filter = QueryBuilders.rangeQuery("timestamp").format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
					.gt(DateUtil.getLastDayESDate(interval));
		try {
			response = GrayLogUtil.search(index, type, SearchType.DFS_QUERY_THEN_FETCH, queryterm, filter, 60);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 将response转换为ActivityRecord，这里也需要一个方法吧，比较底层的方法
		for (SearchHit hit : response.getHits()) {
			// 首先得到一个JSON
			Map<String, Object> json = hit.getSource();
			// 然后将JSON转化为活动
			// 这里需要把日志对应到活动类型
			ActivityType type = ActivityType.ad;
			ActivityRecord ar = ARFFactory.getActivityRecordFactoryInstance(type).getActivityRecordFromJson(json);
			// todo

			ars.add(ar);

		}

		return ars;
	}

	public List<ActivityRecord> getActivityRecord(int interval) {
		List<ActivityRecord> ars = new ArrayList<ActivityRecord>();
		QueryBuilder queryterm = QueryBuilders.matchAllQuery();
		SearchResponse response = null;
		QueryBuilder filter = null;
		if (interval != -1)
			filter = QueryBuilders.rangeQuery("timestamp").format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
					.gt(DateUtil.getLastDayESDate(interval));
		try {
			response = GrayLogUtil.search(index, type, SearchType.DFS_QUERY_THEN_FETCH, queryterm, filter, 10000);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 将response转换为ActivityRecord，这里也需要一个方法吧，比较底层的方法
		int count = 0;
		for (SearchHit hit : response.getHits()) {
			count++;
			// 首先得到一个JSON
			Map<String, Object> json = hit.getSource();
			// for (String str : json.keySet()) {
			// System.out.println(str + "|" + json.get(str).toString());
			// }
			// System.out.println("************************************************");
			// 判断ip是否合法
			if (!json.keySet().contains(ADLogField.ipaddress))
				continue;
			if (!RawLogUtil.isBoolIp(json.get(ADLogField.ipaddress).toString()))
				continue;
			System.out.println(json.get(ADLogField.ipaddress).toString());
			// for (String str : json.keySet()) {
			// System.out.println(str + "|" + json.get(str).toString());
			// }

			// 然后将JSON转化为活动
			// 这里需要把日志对应到活动类型
			ActivityType type = RawLogUtil.getActivityTypeFromRawLogItem(json);
			ActivityRecord ar = ARFFactory.getActivityRecordFactoryInstance(type).getActivityRecordFromJson(json);
			// todo

			ars.add(ar);

		}
		System.out.println(count);

		return ars;
	}

}
