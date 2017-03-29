/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.service.impl;

import java.util.Date;
import java.util.Map;

import cn.pku.ueba.dao.factory.ARFFactory;
import cn.pku.ueba.dao.impl.HostDAOImpl;
import cn.pku.ueba.dao.impl.UserDAOImpl;
import cn.pku.ueba.model.Host;
import cn.pku.ueba.model.User;
import cn.pku.ueba.model.activity.ActivityRecord;
import cn.pku.ueba.model.activity.ActivityType;
import cn.pku.ueba.resource.ADLogField;
import cn.pku.ueba.service.ActivityRecordProducer;

/**
 * AD用户活动的产生类
 * 
 * @author xixy10@foxmail.com
 */
public class ADActivityRecordProducer extends ActivityRecordProducer {
	private static ADActivityRecordProducer instance = new ADActivityRecordProducer();

	/**
	 * 返回静态实例
	 * 
	 * @return 静态实例
	 */
	public static ADActivityRecordProducer getInstance() {
		if (instance == null)
			instance = new ADActivityRecordProducer();
		return instance;
	}

	public ActivityType aType = ActivityType.ad;

	public ActivityRecord getActivityRecordFromRawLog(Map<String, Object> source) {
		// 获取相应的活动记录对象
		ActivityRecord ar = ARFFactory.getActivityRecordFactoryInstance(aType).getActivityRecord();

		// 填充字段
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
		// 将活动记录持久化到graylog中
		// new ActivityRecordDAOImpl().index(ar);
		return ar;

	}

}
