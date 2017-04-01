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
}
