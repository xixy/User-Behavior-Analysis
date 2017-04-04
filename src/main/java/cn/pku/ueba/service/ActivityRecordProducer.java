/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.service;

import java.util.Map;

import cn.pku.ueba.model.activity.ActivityRecord;
import cn.pku.ueba.model.activity.ActivityType;

/**
 * 从原生日志中提取活动记录的基类
 * 
 * @author xixy10@foxmail.com
 */
public abstract class ActivityRecordProducer {

	/**
	 * 从一条原生日志记录中提取活动记录
	 * 
	 * @param source
	 *            json格式对象
	 * @return 活动记录对象
	 */
	public abstract ActivityRecord getActivityRecordFromRawLog(Map<String, Object> source);

	/**
	 * 原生日志的index
	 */
	private String index = "graylog_0";
	/**
	 * 原生日志的type
	 */
	private String type = "message";
	/**
	 * 活动记录Producer所能处理的活动类型
	 */

	private ActivityType aType;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ActivityType getaType() {
		return aType;
	}

	public void setaType(ActivityType aType) {
		this.aType = aType;
	}

}
