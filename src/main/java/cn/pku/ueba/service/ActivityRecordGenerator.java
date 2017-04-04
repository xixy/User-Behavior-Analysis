/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月30日 上午9:21:41
 */
package cn.pku.ueba.service;

import java.io.FileInputStream;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import cn.pku.ueba.configure.Configure;
import cn.pku.ueba.util.DateUtil;
import cn.pku.ueba.util.GrayLogUtil;

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

			}

	}
}
