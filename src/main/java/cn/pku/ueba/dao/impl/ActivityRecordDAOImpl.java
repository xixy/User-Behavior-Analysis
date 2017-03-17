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
import cn.pku.ueba.dao.factory.ActivityRecordFactory;
import cn.pku.ueba.model.ActivityRecord;
import cn.pku.ueba.model.User;
import cn.pku.ueba.util.DateUtil;
import cn.pku.ueba.util.GrayLogUtil;

public class ActivityRecordDAOImpl implements ActivityRecordDAO {

	static String index = "GrayLog_0";
	static String type = "message";

	// 将活动存储到GrayLog中
	public void index(ActivityRecord activityrecord) {
		Map<String, Object> json = ActivityRecordFactory.getJSONFromActivityRecord(activityrecord);
		GrayLogUtil.index(index, type, json);

	}

	/*
	 * 获取用户过去几天的活动 interval = -1表示所有活动；否则就是过去inteval天的活动 (non-Javadoc)
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 将response转换为ActivityRecord
		for (SearchHit hit : response.getHits()) {

		}

		return ars;
	}

}
