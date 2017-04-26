/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月5日 下午3:09:26
 */
package cn.pku.ueba.service.generator;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import cn.pku.ueba.configure.Configure;
import cn.pku.ueba.dao.factory.UserFactory;
import cn.pku.ueba.model.User;
import cn.pku.ueba.model.activity.ActivityRecord;
import cn.pku.ueba.model.activity.ActivityType;
import cn.pku.ueba.util.DateUtil;
import cn.pku.ueba.util.GrayLogUtil;

/**
 * 用来生成特征向量的Runnable接口的类
 * 
 * @author xixy10@foxmail.com
 */
public class FeatureVectorGeneratorUnit extends GeneratorUnit {

	/**
	 * 一个无限循环进行分析
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@SuppressWarnings("deprecation")
	public void run() {
		// 得到该时刻的小时数
		int hour = date.getHours();
		while (true) {
			date = new Date();
			if (date.getHours() != hour) {
				hour = date.getHours();
				// 生成filter
				generateFilter();
				// 调用生成分析材料
				List<SearchResponse> materials = generateMaterialForAnalysis();
				// 进行分析
				analysis(materials);

			}

		}
	}

	/**
	 * 得到分析材料
	 * 
	 * @see cn.pku.ueba.service.generator.GeneratorUnit#generateMaterialForAnalysis()
	 */
	@Override
	public List<SearchResponse> generateMaterialForAnalysis() {

		List<SearchResponse> responseList = null;
		try {
			responseList = GrayLogUtil.search(Configure.getIndex(), Configure.getActivitytype(),
					SearchType.DFS_QUERY_THEN_FETCH, queryterm, this.generateFilter(), 10000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return responseList;
	}

	/**
	 * 对用户活动进行读取，并生成特征向量，并进行持久化
	 * <p>
	 * 如果hour是0，那么就要读取23小时+23:00到0:00之间的数据进行生成
	 * </p>
	 * <p>
	 * 如果hour不是0，那么就读取过去一小时的数据来生成特征
	 * </p>
	 * 
	 * @see cn.pku.ueba.service.generator.GeneratorUnit#analysis(java.util.List)
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public void analysis(List<SearchResponse> responseList) {
		Map<User, HashMap<ActivityType, List<ActivityRecord>>> userStatistic = new HashMap<User, HashMap<ActivityType, List<ActivityRecord>>>();
		// 1 遍历过去一个小时的用户活动，生成用户为key、该用户活动为value的一个结构
		for (SearchResponse response : responseList) {
			for (SearchHit hit : response.getHits().hits()) {
				Map<String, Object> source = hit.getSource();
				Map<String, Object> userJson = (Map<String, Object>) source.get("user");
				User user = (User) UserFactory.getInstance().getEntityFromJson(userJson);
				// 分类存储该user的所有活动
				/***
				 * 提取活动、活动类型todo
				 */
				ActivityType type = null;
				ActivityRecord ar = null;
				// 如果不包含这个user
				if (!userStatistic.containsKey(user)) {
					userStatistic.put(user, new HashMap<ActivityType, List<ActivityRecord>>());
				}
				HashMap<ActivityType, List<ActivityRecord>> activityRecordMap = userStatistic.get(user);
				// 如果这个user没有这种活动
				if (!activityRecordMap.containsKey(type)) {
					activityRecordMap.put(type, new ArrayList<ActivityRecord>());
				}
				// 插入活动
				activityRecordMap.get(type).add(ar);
			}

		}
		// 2 生成过去一个小时的feature
		// 3 根据时间来确定是否需要合并
		// 如果是0点的时间，就需要统计过去23小时+60分钟
		if (date.getHours() == 0) {

		}

	}

	/**
	 * 生成Filter
	 * 
	 * @see cn.pku.ueba.service.generator.GeneratorUnit#generateFilter()
	 */
	@Override
	public QueryBuilder generateFilter() {
		// 得到sharp时刻点
		Date sharpDate = DateUtil.getSharpTimeInHour(date);
		// 计算时间段后时刻点
		timeAfter = DateUtil.toESDate(sharpDate);
		// 计算时间段前时刻点
		timeBefore = DateUtil.toESDate(DateUtil.getLastMinuteDate(sharpDate, 60));
		// 创建时间间隔的filter，这里是一分钟的
		filter = QueryBuilders.rangeQuery("timestamp").format(DateUtil.dateiso8601.toPattern()).gte(timeBefore)
				.lt(timeAfter);
		return filter;
	}

}
