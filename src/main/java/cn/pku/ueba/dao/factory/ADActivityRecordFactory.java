package cn.pku.ueba.dao.factory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.pku.ueba.dao.impl.HostDAOImpl;
import cn.pku.ueba.dao.impl.UserDAOImpl;
import cn.pku.ueba.model.Host;
import cn.pku.ueba.model.User;
import cn.pku.ueba.model.activity.ADActivityRecord;
import cn.pku.ueba.model.activity.ActivityRecord;
import cn.pku.ueba.model.activity.ActivityType;
import cn.pku.ueba.resource.ADLogField;
import cn.pku.ueba.resource.activitylogfield.ADActivityLogField;

public class ADActivityRecordFactory extends ActivityRecordFactory {

	private static ADActivityRecordFactory adARF;

	// 获取实例
	public static ActivityRecordFactory getInstance() {
		if (adARF == null)
			adARF = new ADActivityRecordFactory();
		return adARF;
	}

	/*
	 * 创建一个用户活动(non-Javadoc)
	 * 
	 * @see cn.pku.ueba.dao.factory.ActivityRecordFactory#getActivityRecord()
	 */
	public ActivityRecord getActivityRecord() {
		ADActivityRecord ar = new ADActivityRecord();
		return ar;
	}

	/*
	 * 将用户活动转换为JSON(non-Javadoc)
	 * 
	 * @see
	 * cn.pku.ueba.dao.factory.ActivityRecordFactory#getJSONFromActivityRecord(
	 * cn.pku.ueba.model.activity.ActivityRecord)
	 */
	public Map<String, Object> getJsonFromActivityRecord(ActivityRecord activityrecord) {
		ADActivityRecord adAR = (ADActivityRecord) activityrecord;
		Map<String, Object> json = new HashMap<String, Object>();
		// 用户名称
		json.put(ADActivityLogField.user, adAR.getUser().getName());
		// 主机名称
		json.put(ADActivityLogField.host, adAR.getHost().getName());
		// 目的ip
		json.put(ADActivityLogField.desIp, adAR.getDesIp());
		// 目的端口
		json.put(ADActivityLogField.desPort, adAR.getDesPort());
		// 源ip
		json.put(ADActivityLogField.sourceIp, adAR.getSourceIp());
		// 源port
		json.put(ADActivityLogField.sourcePort, adAR.getSourcePort());
		// 时间戳
		json.put(ADActivityLogField.date, adAR.getDate());
		// 活动类型
		json.put(ADActivityLogField.activityType, adAR.getType());
		return json;
	}

	/*
	 * 从数据库中提取的JSON格式的元日志中提取用户活动(non-Javadoc)
	 * 
	 * @see
	 * cn.pku.ueba.dao.factory.ActivityRecordFactory#getActivityRecordFromJSON(
	 * java.util.Map)
	 */
	public ActivityRecord getActivityRecordFromJson(Map<String, Object> json) {
		ADActivityRecord ar = new ADActivityRecord();
		String ipaddress = (String) json.get(ADLogField.ipaddress);
		// 提取端口
		String port = json.get(ADLogField.port).toString();
		// 提取账户名称
		String account = json.get(ADLogField.user).toString();
		// 提取服务名称
		// String service = json.get(ADLogField.service).toString();
		// 提取用户ID
		// String userid = json.get(ADLogField.targetsid).toString();
		// 提取serviceID
		// String serviceid = json.get(ADLogField.servicesid).toString();
		// 获取用户，这里可能获取到为null，我们假设所有的user都存进去了
		User user = UserDAOImpl.getInstance().getUser(account);
		// 获取主机
		Host host = new HostDAOImpl().getHost(ipaddress);
		// 产生日期
		Date date = null;
		// 活动类型
		ActivityType type = ActivityType.ad;
		// todo 生成ar
		ar.setHost(host);
		ar.setUser(user);
		ar.setType(type);
		ar.setDate(date);
		ar.setSourceIp(ipaddress);
		ar.setSourcePort(port);
		ar.setDesIp(ipaddress);
		ar.setSourcePort(port);

		return ar;
	}

}
