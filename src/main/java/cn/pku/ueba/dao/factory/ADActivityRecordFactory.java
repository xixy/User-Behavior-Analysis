/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao.factory;

import cn.pku.ueba.model.activity.ADActivityRecord;
import cn.pku.ueba.model.activity.ActivityRecord;

/**
 * 用来处理ADActivityRecord对象的持久化
 * 
 * @author xixy10@foxmal.com
 */
public class ADActivityRecordFactory extends ActivityRecordFactory {

	private static ADActivityRecordFactory adARF;

	/**
	 * 单例模式用来获取一个ADARF对象
	 * 
	 * @return ADActivityRecordFactory对象
	 */
	public static ActivityRecordFactory getInstance() {
		if (adARF == null)
			adARF = new ADActivityRecordFactory();
		return adARF;
	}

	/**
	 * 返回一个ADActivityRecord对象
	 * 
	 * @return 返回一个ADActivityRecord对象
	 */
	public ActivityRecord getActivityRecord() {
		ADActivityRecord ar = new ADActivityRecord();
		return ar;
	}

	// /**
	// * 从ES中的JSON转化为活动记录对象
	// *
	// * @return 活动记录对象
	// */
	// public ActivityRecord getActivityRecordFromJson(Map<String, Object> json)
	// {
	// ADActivityRecord ar = new ADActivityRecord();
	// String ipaddress = (String) json.get(ADLogField.ipaddress);
	// // 提取端口
	// String port = json.get(ADLogField.port).toString();
	// // 提取账户名称
	// String account = json.get(ADLogField.user).toString();
	// // 提取服务名称
	// // String service = json.get(ADLogField.service).toString();
	// // 提取用户ID
	// // String userid = json.get(ADLogField.targetsid).toString();
	// // 提取serviceID
	// // String serviceid = json.get(ADLogField.servicesid).toString();
	// // 获取用户，这里可能获取到为null，我们假设所有的user都存进去了
	// User user = UserDAOImpl.getInstance().getUser(account);
	// // 获取主机
	// Host host = new HostDAOImpl().getHost(ipaddress);
	// // 产生日期
	// Date date = null;
	// // 活动类型
	// ActivityType type = ActivityType.ad;
	// // todo 生成ar
	// ar.setHost(host);
	// ar.setUser(user);
	// ar.setType(type);
	// ar.setDate(date);
	// ar.setSourceIp(ipaddress);
	// ar.setSourcePort(port);
	// ar.setDesIp(ipaddress);
	// ar.setSourcePort(port);
	//
	// return ar;
	// }

}
