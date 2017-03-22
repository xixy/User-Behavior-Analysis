package cn.pku.ueba.service.impl;

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

import cn.pku.ueba.dao.factory.ARFFactory;
import cn.pku.ueba.dao.impl.ActivityRecordDAOImpl;
import cn.pku.ueba.dao.impl.HostDAOImpl;
import cn.pku.ueba.dao.impl.UserDAOImpl;
import cn.pku.ueba.model.Host;
import cn.pku.ueba.model.User;
import cn.pku.ueba.model.activity.ActivityRecord;
import cn.pku.ueba.model.activity.ActivityType;
import cn.pku.ueba.resource.ADLogField;
import cn.pku.ueba.service.ActivityRecordProducer;
import cn.pku.ueba.util.DateUtil;
import cn.pku.ueba.util.GrayLogUtil;

@Deprecated
public class ADActivityRecordProducer extends ActivityRecordProducer {
	public String index = "graylog_0";
	public String type = "message";

	public static void main(String[] args) {
		ADActivityRecordProducer arp = new ADActivityRecordProducer();
		System.out.println(arp.index);
	}

	/*
	 * 从原生日志中提取用户活动记录 (non-Javadoc)
	 * 
	 * @see
	 * cn.pku.ueba.service.ActivityRecordProducer#getActivityRecordFromRawLog()
	 */
	public List<ActivityRecord> getActivityRecordFromRawLog() {
		List<ActivityRecord> records = new ArrayList<ActivityRecord>();

		QueryBuilder queryterm = QueryBuilders.termQuery("winlogbeat_task", "Kerberos 身份验证服务");
		QueryBuilder filter = QueryBuilders.rangeQuery("timestamp").format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
				.gt(DateUtil.getLastDayESDate(3));// 过去一小时吧还是todo
		SearchResponse response = null;
		try {
			response = GrayLogUtil.search(index, type, SearchType.DFS_QUERY_THEN_FETCH, queryterm, filter, 60);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		// 将response转换为ActivityRecord
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

				ActivityType type = ActivityType.hostlogin;
				// 生成ar
				ActivityRecord ar = ARFFactory.getInstance(type).getActivityRecord();
				// 填充字段
				// 将活动记录持久化到graylog中
				new ActivityRecordDAOImpl().index(ar);
				records.add(ar);
			}
		}

		return records;

	}

	public static String getValueByTypeFromADLog(String message, String item) {
		int index1 = message.indexOf(item);
		index1 += item.length();
		int index2 = message.indexOf("\n", index1);
		return message.substring(index1, index2);
	}

}
