/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.model.activity;

/**
 * AD日志相关的用户活动记录
 * 
 * @author xixy10@foxmail.com
 */
public class ADActivityRecord extends ActivityRecord {

	public ADActivityRecord() {
		this.setType(ActivityType.ad);
	}

}
