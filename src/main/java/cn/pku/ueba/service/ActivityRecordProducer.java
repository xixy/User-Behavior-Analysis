/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.service;

import java.util.List;

import cn.pku.ueba.model.activity.ActivityRecord;

/**
 * 从原生日志中提取活动记录的基类
 * 
 * @author xixy10@foxmail.com
 * @deprecated
 */
public abstract class ActivityRecordProducer {

	/**
	 * 从原生日志中提取活动记录
	 * 
	 * @return 活动记录对象
	 */
	public abstract List<ActivityRecord> getActivityRecordFromRawLog();

	/**
	 * 原生日志的index
	 */
	public String index;
	/**
	 * 原生日志的type
	 */
	public String type;

}
