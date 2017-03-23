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
	 * @return void
	 */
	public void index(ActivityRecord activityrecord);

	/**
	 * 获取某个用户过去时间段的所有记录
	 * 
	 * @param user
	 * @param interval
	 * @return
	 */
	public List<ActivityRecord> getActivityRecordByUser(User user, int interval);

	/**
	 * 获取过去时间段的所有日志并将其转化为活动记录对象
	 * 
	 * @param interval
	 * @return
	 */
	public List<ActivityRecord> getActivityRecord(int interval);

}
