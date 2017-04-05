/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月5日 下午3:09:26
 */
package cn.pku.ueba.service.generator;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import cn.pku.ueba.configure.Configure;
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
		// 得到该时刻的分钟数
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
	@Override
	public void analysis(List<SearchResponse> responseList) {

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
