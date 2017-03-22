package cn.pku.ueba.dao.factory;

import java.util.Map;

import cn.pku.ueba.model.activity.ActivityRecord;

public abstract class ActivityRecordFactory {

	// 创建一个活动记录
	public abstract ActivityRecord getActivityRecord();

	// 将ActivityRecord转换为JSON
	public abstract Map<String, Object> getJsonFromActivityRecord(ActivityRecord activityrecord);

	// 将ActivityRecord转换为JSON
	public abstract ActivityRecord getActivityRecordFromJson(Map<String, Object> json);

}
