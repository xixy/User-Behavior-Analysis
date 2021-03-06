/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月28日 下午4:53:41
 */
package cn.pku.ueba.util;

import static org.junit.Assert.fail;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.pku.ueba.configure.Configure;

/**
 *
 */
@FixMethodOrder(MethodSorters.JVM) // 指定测试方法按定义的顺序执行
public class GrayLogUtilTest {
	public static String type = "test";

	/**
	 * Test method for {@link cn.pku.ueba.util.GrayLogUtil#getClient()}.
	 * 
	 * @throws UnknownHostException
	 */
	@Test
	public void testGetClient() throws UnknownHostException {
		if (GrayLogUtil.getClient() == null)
			fail("无法生成client");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.util.GrayLogUtil#index(java.lang.String, java.lang.String, java.util.Map)}
	 * .
	 * 
	 * @throws UnknownHostException
	 */
	@Test
	public void testIndex() throws UnknownHostException {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("test", "test");
		GrayLogUtil.index(Configure.getIndex(), type, json);
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.util.GrayLogUtil#search(java.lang.String, java.lang.String, org.elasticsearch.action.search.SearchType, org.elasticsearch.index.query.QueryBuilder, org.elasticsearch.index.query.QueryBuilder, int)}
	 * .
	 * 
	 * @throws UnknownHostException
	 */
	@Test
	public void testSearch1() throws UnknownHostException {
		QueryBuilder queryterm = QueryBuilders.matchAllQuery();
		QueryBuilder filter = null;
		List<SearchResponse> responseList = GrayLogUtil.search(Configure.getIndex(), type,
				SearchType.DFS_QUERY_THEN_FETCH, queryterm, filter, 10000);
		for (SearchResponse response : responseList) {
			System.out.println(response.getHits().getHits().length);
			if (response.getHits().getHits().length == 0)
				fail("GrayLogUtil cannont search");

		}
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.util.GrayLogUtil#search(java.lang.String, java.lang.String, org.elasticsearch.action.search.SearchType, org.elasticsearch.index.query.QueryBuilder, org.elasticsearch.index.query.QueryBuilder, int)}
	 * .
	 * 
	 * @throws UnknownHostException
	 */
	@Test
	public void testSearch2() throws UnknownHostException {
		QueryBuilder queryterm = QueryBuilders.matchAllQuery();
		QueryBuilder filter = QueryBuilders.rangeQuery("timestamp").format(DateUtil.dateiso8601.toPattern())
				.gt(DateUtil.getLastDayESDate(100));
		List<SearchResponse> responseList = GrayLogUtil.search(Configure.getIndex(), Configure.getRawlogtype(),
				SearchType.DFS_QUERY_THEN_FETCH, queryterm, filter, 10000);
		for (SearchResponse response : responseList) {
			System.out.println(response.getHits().getHits().length);
			if (response.getHits().getHits().length == 0)
				fail("GrayLogUtil cannont search");

		}
	}
	
	/**
	 * Test method for
	 * {@link cn.pku.ueba.util.GrayLogUtil#searchByScroll(java.lang.String, java.lang.String, org.elasticsearch.action.search.SearchType, org.elasticsearch.index.query.QueryBuilder, org.elasticsearch.index.query.QueryBuilder)}
	 * .
	 * 
	 * @throws UnknownHostException
	 */
	@Test
	public void testSearchByScroll() throws UnknownHostException {
		QueryBuilder queryterm = QueryBuilders.matchAllQuery();
		QueryBuilder filter = QueryBuilders.rangeQuery("timestamp").format(DateUtil.dateiso8601.toPattern())
				.gt(DateUtil.getLastDayESDate(100));
		List<SearchResponse> responseList = GrayLogUtil.searchByScroll(Configure.getIndex(), 
				Configure.getRawlogtype(),SearchType.DFS_QUERY_THEN_FETCH, queryterm, filter);
		int i=0;
		for (SearchResponse response : responseList) {
			System.out.println(response.getHits().getHits().length+" "+(++i));
			if (response.getHits().getHits().length == 0)
				fail("GrayLogUtil cannont search");

		}
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.util.GrayLogUtil#delete(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testDelete() {
		QueryBuilder queryterm = QueryBuilders.termQuery("test", "test");
		List<SearchResponse> responseList = null;
		try {
			responseList = GrayLogUtil.search(Configure.getIndex(), type, SearchType.DFS_QUERY_THEN_FETCH, queryterm,
					null, 1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		for (SearchResponse response : responseList) {
			if (response.getHits().getHits().length > 0)
				for (SearchHit hit : response.getHits().getHits()) {
					String id = hit.getId();
					GrayLogUtil.delete(Configure.getIndex(), type, id);
				}
			else
				fail("GrayLogUtil cannot delete");
		}
	}
	
	@Test
	public void testCount() {
		QueryBuilder queryterm = QueryBuilders.matchAllQuery();
		try{
			CountResponse count = GrayLogUtil.count(Configure.getIndex(), Configure.getRawlogtype(),queryterm);
			System.out.println(count.getCount());
		}catch (UnknownHostException e) {
			fail("GrayLogUtil cannot count");
			e.printStackTrace();
		}
			
	}

}
