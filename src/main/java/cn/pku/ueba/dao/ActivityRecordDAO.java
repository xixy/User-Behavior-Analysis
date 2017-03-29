/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao;

import java.util.List;

import cn.pku.ueba.model.User;
import cn.pku.ueba.model.activity.ActivityRecord;

/**
 * ActivityRecordDAO
 * 
 * @author xixy10@foxmail.com
 */
public interface ActivityRecordDAO {
	/**
	 * 用来将活动记录对象持久化
	 * 
	 * @param activityrecord
	 *            活动记录对象
	 */
	public void index(ActivityRecord activityrecord);

	/**
	 * 获取某个用户或者所有用户过去时间段的所有记录
	 * 
	 * @param user
	 *            用户对象 如果是null，则表示所有用户；如果非null，则表示该用户
	 * @param interval
	 *            时间间隔，单位是天
	 * @return 活动记录对象的列表
	 */
	public List<ActivityRecord> getActivityRecordByUser(User user, int interval);

}
