/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月5日 下午2:49:28
 */
package cn.pku.ueba.service.generator;

import java.util.Date;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 *
 */
public abstract class GeneratorUnit implements Runnable {

	public static final QueryBuilder queryterm = QueryBuilders.matchAllQuery();

	public QueryBuilder filter = null;

	public String timeBefore = null;
	public String timeAfter = null;
	public Date date;

	/**
	 * 根据检索的设置来生成检索到的结果
	 * 
	 * @return 返回搜索到的结果
	 */
	public abstract List<SearchResponse> generateMaterialForAnalysis();

	/**
	 * 根据检索到的信息进行分析，并将分析结果进行持久化
	 * 
	 * @param responseList
	 *            检索到的结果
	 */
	public abstract void analysis(List<SearchResponse> responseList);

	/**
	 * 设置filter并返回
	 * 
	 * @return filter
	 */

	public abstract QueryBuilder generateFilter();

}
