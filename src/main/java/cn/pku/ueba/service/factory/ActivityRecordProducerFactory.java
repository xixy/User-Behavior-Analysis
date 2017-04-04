/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月29日 下午4:24:43
 */
package cn.pku.ueba.service.factory;

import cn.pku.ueba.model.activity.ActivityType;
import cn.pku.ueba.service.ActivityRecordProducer;
import cn.pku.ueba.service.impl.ADActivityRecordProducer;

/**
 * ActivityRecordProducer的工厂类，用来根据活动类型返回ActivityRecordProducer
 * 
 * @author xixy10@foxmail.com
 */
public class ActivityRecordProducerFactory {

	/**
	 * 通过活动类型获取到相应的ActivityRecordProducer，还没有完成，需要逐渐添加；采用了单例模式，开销较小
	 * 
	 * @param aType
	 *            活动类型
	 * @return 对应活动类型的ActivityRecordProducer实例
	 */
	public static ActivityRecordProducer getActivityRecordProducerByActivityType(ActivityType aType) {
		ActivityRecordProducer arp = null;
		switch (aType) {
		case ad:
			arp = ADActivityRecordProducer.getInstance();
		case any:
			break;
		case hostlogin:
			break;
		case hostlogout:
			break;
		case ssh_access:
			break;
		default:
			break;
		}
		return arp;
	}

}
