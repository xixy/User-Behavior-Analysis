/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月29日 下午6:11:36
 */
package cn.pku.ueba.service.impl;

import static org.junit.Assert.fail;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Ignore;
import org.junit.Test;

import cn.pku.ueba.dao.factory.ARFFactory;
import cn.pku.ueba.dao.impl.ActivityRecordDAOImpl;
import cn.pku.ueba.dao.impl.HostDAOImpl;
import cn.pku.ueba.dao.impl.UserDAOImpl;
import cn.pku.ueba.model.Host;
import cn.pku.ueba.model.User;
import cn.pku.ueba.model.activity.ActivityRecord;
import cn.pku.ueba.model.activity.ActivityType;
import cn.pku.ueba.resource.rawlogfield.ADLogField;
import cn.pku.ueba.service.producer.ADActivityRecordProducer;
import cn.pku.ueba.util.DateUtil;
import cn.pku.ueba.util.GrayLogUtil;

/**
 *
 */
public class ADActivityRecordProducerTest {
	ADActivityRecordProducer adarProducer = new ADActivityRecordProducer();

	/**
	 * Test method for
	 * {@link cn.pku.ueba.service.producer.ADActivityRecordProducer#getActivityRecordFromRawLog(java.util.Map)}
	 * .
	 */
	@Test
	@Ignore
	public void testGetActivityRecordFromRawLog() {
		List<ActivityRecord> records = new ArrayList<ActivityRecord>();

		QueryBuilder queryterm = QueryBuilders.termQuery("winlogbeat_task", "Kerberos 身份验证服务");
		QueryBuilder filter = QueryBuilders.rangeQuery("timestamp").format(DateUtil.dateiso8601.toPattern())
				.gt(DateUtil.getLastDayESDate(3));// 过去一小时吧还是todo
		List<SearchResponse> responseList = null;
		try {
			responseList = GrayLogUtil.search(adarProducer.getIndex(), adarProducer.getType(),
					SearchType.DFS_QUERY_THEN_FETCH, queryterm, filter, 60);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		// 将response转换为ActivityRecord
		for (SearchResponse response : responseList)
			for (SearchHit hit : response.getHits().hits()) {
				Map<String, Object> source = hit.getSource();
				// 如果ip合法，端口不为0，这里两个效果等价
				if (!source.get(ADLogField.port).toString().equals(new String("0"))) {
					// 提取出ip地址
					String ipaddress = source.get(ADLogField.ipaddress).toString().substring(7);
					// 提取端口
					String port = source.get(ADLogField.port).toString();
					// 提取账户名称
					String account = source.get(ADLogField.user).toString();
					// 提取服务名称
					String service = source.get(ADLogField.service).toString();
					// 提取用户ID
					String userid = source.get(ADLogField.targetsid).toString();
					// 提取serviceID
					String serviceid = source.get(ADLogField.servicesid).toString();
					// 获取用户，这里可能获取到为null，我们假设所有的user都存进去了
					User user = UserDAOImpl.getInstance().getUser(account);
					// 获取主机
					Host host = new HostDAOImpl().getHost(ipaddress);
					// 产生日期
					Date date = null;
					// 活动类型

					// 从rawlog中获取到活动类型的机制需要建立
					ActivityType type = ActivityType.hostlogin;
					// 生成ar
					ActivityRecord ar = ARFFactory.getActivityRecordFactoryInstance(type).getActivityRecord();
					// 填充字段
					// 将活动记录持久化到graylog中
					new ActivityRecordDAOImpl().index(ar);
					records.add(ar);
				}
			}

	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.service.producer.ADActivityRecordProducer#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		ADActivityRecordProducer ad = ADActivityRecordProducer.getInstance();
		if (ad != null) {
			if (ad.getaType().equals(ActivityType.ad))
				if (ad.getIndex().equals("graylog_0"))
					if (ad.getType().equals("message"))
						return;
		}
		fail("GetInstance failed");

	}

}
