/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月30日 上午9:21:41
 */
package cn.pku.ueba.service;

import java.net.UnknownHostException;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import cn.pku.ueba.configure.Configure;
import cn.pku.ueba.dao.impl.ActivityRecordDAOImpl;
import cn.pku.ueba.model.activity.ActivityRecord;
import cn.pku.ueba.model.activity.ActivityType;
import cn.pku.ueba.service.factory.ActivityRecordProducerFactory;
import cn.pku.ueba.util.DateUtil;
import cn.pku.ueba.util.GrayLogUtil;
import cn.pku.ueba.util.RawLogUtil;

/**
 * 包含了main函数的用来生成ActivityRecord的类，用来长时间运行来产生用户活动，包括了rawlog的处理、json的生成、model的生成、
 * 持久化等
 * 
 * @author xixy10@foxmail.com
 */
public class ActivityRecordGenerator implements Runnable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// QueryBuilder queryterm = QueryBuilders.termQuery("winlogbeat_task",
		// "Kerberos 身份验证服务");
		QueryBuilder queryterm = QueryBuilders.matchAllQuery();
		QueryBuilder filter = QueryBuilders.rangeQuery("timestamp").format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
				.gt(DateUtil.getLastDayESDate(3));// 过去一小时吧还是todo
		List<SearchResponse> responseList = null;
		try {
			responseList = GrayLogUtil.search(Configure.getIndex(), Configure.getRawlogtype(),
					SearchType.DFS_QUERY_THEN_FETCH, queryterm, filter, 10000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		// 将response转换为ActivityRecord
		for (SearchResponse response : responseList)
			for (SearchHit hit : response.getHits().hits()) {
				// 获取到活动类型
				ActivityType aType = RawLogUtil.getActivityTypeFromRawLogItem(hit.getSource());
				// 根据活动类型获取相应的ActivityRecordProducer，然后从rawlog中获取到活动记录
				ActivityRecord activityRecord = ActivityRecordProducerFactory
						.getActivityRecordProducerByActivityType(aType).getActivityRecordFromRawLog(hit.getSource());
				// 对生成的活动记录进行持久化
				ActivityRecordDAOImpl.getInstance().index(activityRecord);

			}

	}
}
