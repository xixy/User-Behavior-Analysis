/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao.factory;

import cn.pku.ueba.model.activity.ActivityType;

/**
 * ActivityRecordFactory的工厂类
 * 
 * @author xixy10@foxmail.com
 */
public class ARFFactory {

	/**
	 * 根据活动类型返回得到相应活动类型的工厂类
	 * 
	 * @param type
	 *            活动类型
	 * @return 用户活动工厂类
	 */
	public static ActivityRecordFactory getActivityRecordFactoryInstance(ActivityType type) {

		ActivityRecordFactory arf = null;
		switch (type) {
		case hostlogin:
			break;
		case ad:
			arf = new ADActivityRecordFactory();
			break;
		}

		return arf;
	}

}
