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

import cn.pku.ueba.configure.Configure;
import cn.pku.ueba.dao.ActivityRecordDAO;
import cn.pku.ueba.dao.factory.ActivityRecordFactory;
import cn.pku.ueba.model.User;
import cn.pku.ueba.model.activity.ActivityRecord;
import cn.pku.ueba.util.DateUtil;
import cn.pku.ueba.util.GrayLogUtil;

/**
 * 用户活动DAO的实现
 */
public class ActivityRecordDAOImpl implements ActivityRecordDAO {

	private static ActivityRecordDAOImpl instance;

	/**
	 * 获取该对象
	 * 
	 * @return ActivityRecordDAOImpl对象
	 */
	public static ActivityRecordDAOImpl getInstance() {
		if (instance == null)
			instance = new ActivityRecordDAOImpl();
		return instance;
	}

	public void index(ActivityRecord activityRecord) {
		Map<String, Object> json = ActivityRecordFactory.getJsonFromActivityRecord(activityRecord);
		try {
			GrayLogUtil.index(Configure.getIndex(), Configure.getActivitytype(), json);
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
		List<SearchResponse> responseList = null;
		QueryBuilder filter = null;
		if (interval != -1)
			filter = QueryBuilders.rangeQuery("timestamp").format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
					.gt(DateUtil.getLastDayESDate(interval));
		try {
			responseList = GrayLogUtil.search(Configure.getIndex(), Configure.getActivitytype(),
					SearchType.DFS_QUERY_THEN_FETCH, queryterm, filter, 60);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 将response转换为ActivityRecord，这里也需要一个方法吧，比较底层的方法
		for (SearchResponse response : responseList)
			for (SearchHit hit : response.getHits()) {
				// 首先得到一个JSON
				Map<String, Object> json = hit.getSource();
				// 然后将JSON转化为活动
				// 首先获取活动类型
				// 然后根据用户活动调用相应的ActivityRecordFactory实例来进行处理，返回活动记录对象
				ActivityRecord ar = ActivityRecordFactory.getActivityRecordFromJson(json);
				ars.add(ar);
			}
		return ars;
	}

}
