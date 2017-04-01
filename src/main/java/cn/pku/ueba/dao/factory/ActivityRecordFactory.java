/**
 * @author xixy10@foxmail.com zhangtong13@mails.tsinghua.edu.cn
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao.factory;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import cn.pku.ueba.model.activity.ActivityRecord;
import cn.pku.ueba.model.activity.ActivityType;
import cn.pku.ueba.util.JsonUtil;

/**
 * 用来处理ActivityRecord对象
 * 
 * @author xixy10@foxmal.com
 */
public abstract class ActivityRecordFactory {

	/**
	 * 获取到一个活动记录对象，各个ARF的派生类自行实现
	 * 
	 * @return 活动记录对象
	 */
	public abstract ActivityRecord getActivityRecord();

	/**
	 * 将ActivityRecord转换为JSON
	 * 
	 * @param activityrecord
	 *            活动记录对象
	 * @return json对象可以直接用于存储到ES中
	 */
	public static Map<String, Object> getJsonFromActivityRecord(ActivityRecord activityrecord) {
		return JsonUtil.getJsonFromModelInstance(activityrecord);
	}

	/**
	 * 将数据库中JSON对象转化为ActivityRecord对象
	 * 
	 * @param json
	 *            ES中存储的活动对象
	 * @return 活动记录对象
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	public static ActivityRecord getActivityRecordFromJson(Map<String, Object> json) {
		ActivityType type = ActivityType.valueOf((String) json.get("type"));
		Object obj = JsonUtil.getModelInstanceFromJson(json,
				ARFFactory.getActivityRecordFactoryInstance(type).getActivityRecord().getClass());
		return (ActivityRecord) obj;
	}

}
