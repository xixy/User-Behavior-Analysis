package cn.pku.ueba.dao;

import java.util.List;

import cn.pku.ueba.model.ActivityRecord;
import cn.pku.ueba.model.User;

public interface ActivityRecordDAO {
	// 生成活动记录并存储
	public void index(ActivityRecord activityrecord);

	// 获取过去几天用户的所有行为
	public List<ActivityRecord> getActivityRecordByUser(User user, int interval);

}
