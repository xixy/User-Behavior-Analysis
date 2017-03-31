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
import cn.pku.ueba.resource.activitylogfield.ADActivityLogField;
import cn.pku.ueba.util.DateUtil;
import cn.pku.ueba.util.GrayLogUtil;

/**
 * 用户活动DAO的实现
 */
public class ActivityRecordDAOImpl implements ActivityRecordDAO {
	/**
	 * 用户活动记录存储的index
	 */
	static String index = "graylog_0";
	/**
	 * 用户活动记录存储的type
	 */
	static String type = "record";

	/**
	 * 将活动存储到GrayLog中，这个方法基本上不需要修改
	 * 
	 * @param activityRecord
	 *            用户活动记录对象
	 * @see cn.pku.ueba.dao.ActivityRecordDAO#index(cn.pku.ueba.model.activity.ActivityRecord)
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

	public List<ActivityRecord> getActivityRecordByUser(User user, int interval) {
		List<ActivityRecord> ars = new ArrayList<ActivityRecord>();
		// 默认查询所有user
		QueryBuilder queryterm = QueryBuilders.matchAllQuery();
		// 如果user非空，那么就返回该user的所有活动记录
		if (user != null)
			queryterm = QueryBuilders.termQuery("user", user.getName());
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
			// 首先获取活动类型
			ActivityType type = (ActivityType) json.get(ADActivityLogField.activityType);
			// 然后根据用户活动调用相应的ActivityRecordFactory实例来进行处理，返回活动记录对象
			ActivityRecord ar = ARFFactory.getActivityRecordFactoryInstance(type).getActivityRecordFromJson(json);
			ars.add(ar);
		}
		return ars;
	}

}
