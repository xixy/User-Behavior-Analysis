package cn.pku.ueba.service;

import java.util.List;

import cn.pku.ueba.model.ActivityRecord;

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
