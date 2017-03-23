/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.service;

import java.util.List;

import cn.pku.ueba.model.activity.ActivityRecord;

public abstract class ActivityRecordProducer {
	/*
	 * 从原生日志中提取活动记录
	 */
	public abstract List<ActivityRecord> getActivityRecordFromRawLog();

	// 日志的index
	public String index;
	// 日志的type
	public String type;

}
