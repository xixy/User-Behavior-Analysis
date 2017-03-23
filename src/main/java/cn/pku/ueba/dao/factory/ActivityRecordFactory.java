/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
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
