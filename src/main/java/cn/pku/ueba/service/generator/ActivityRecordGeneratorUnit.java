/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月5日 下午3:01:50
 */
package cn.pku.ueba.service.generator;

import java.net.UnknownHostException;
import java.util.Date;
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
import cn.pku.ueba.service.producer.ActivityRecordProducerFactory;
import cn.pku.ueba.util.DateUtil;
import cn.pku.ueba.util.GrayLogUtil;
import cn.pku.ueba.util.RawLogUtil;

/**
 * 用来生成用户活动的工作单元
 * 
 * @author xixy10@foxmail.com
 */
public class ActivityRecordGeneratorUnit extends GeneratorUnit {

	/**
	 * 一个无限循环进行分析，生成活动记录
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@SuppressWarnings("deprecation")
	public void run() {
		// 得到该时刻的分钟数
		int minute = date.getMinutes();
		while (true) {
			date = new Date();
			if (date.getMinutes() != minute) {
				minute = date.getMinutes();
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
			responseList = GrayLogUtil.search(Configure.getIndex(), Configure.getRawlogtype(),
					SearchType.DFS_QUERY_THEN_FETCH, queryterm, this.generateFilter(), 10000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return responseList;
	}

	/**
	 * 分析并索引
	 * 
	 * @see cn.pku.ueba.service.generator.GeneratorUnit#analysis(java.util.List)
	 */
	@Override
	public void analysis(List<SearchResponse> responseList) {
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

	/**
	 * 生成检索的Filter
	 * 
	 * @see cn.pku.ueba.service.generator.GeneratorUnit#generateFilter()
	 */
	@Override
	public QueryBuilder generateFilter() {
		// 得到sharp时刻点
		Date sharpDate = DateUtil.getSharpTimeInMinute(date);
		// 计算时间段后时刻点
		timeAfter = DateUtil.toESDate(sharpDate);
		// 计算时间段前时刻点
		timeBefore = DateUtil.toESDate(DateUtil.getLastMinuteDate(sharpDate, 1));
		// 创建时间间隔的filter，这里是一分钟的
		filter = QueryBuilders.rangeQuery("timestamp").format(DateUtil.dateiso8601.toPattern()).gte(timeBefore)
				.lt(timeAfter);
		return filter;
	}

}
