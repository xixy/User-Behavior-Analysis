/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月28日 下午4:53:41
 */
package cn.pku.ueba.util;

import static org.junit.Assert.fail;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;

/**
 *
 */
public class GrayLogUtilTest {
	public static String index = "graylog_0";
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
	 * {@link cn.pku.ueba.util.GrayLogUtil#search(java.lang.String, java.lang.String, org.elasticsearch.action.search.SearchType, org.elasticsearch.index.query.QueryBuilder, org.elasticsearch.index.query.QueryBuilder, int)}
	 * .
	 * 
	 * @throws UnknownHostException
	 */
	@Test
	public void testSearch() throws UnknownHostException {
		QueryBuilder queryterm = QueryBuilders.termQuery("test", "test");
		SearchResponse response = GrayLogUtil.getClient().prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(queryterm).setFrom(0).setSize(1)
				.setExplain(true).execute().actionGet();
		if (response.getHits().getHits().length != 1)
			fail("GrayLogUtil cannont search");

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
		GrayLogUtil.index(index, type, json);
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.util.GrayLogUtil#delete(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testDelete() {
		QueryBuilder queryterm = QueryBuilders.termQuery("test", "test");
		SearchResponse response = null;
		try {
			response = GrayLogUtil.search(index, type, SearchType.DFS_QUERY_THEN_FETCH, queryterm, null, 1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		if (response.getHits().getHits().length > 0)
			for (SearchHit hit : response.getHits().getHits()) {
				String id = hit.getId();
				GrayLogUtil.delete(index, type, id);
			}
		else
			fail("GrayLogUtil cannot delete");
	}

}
