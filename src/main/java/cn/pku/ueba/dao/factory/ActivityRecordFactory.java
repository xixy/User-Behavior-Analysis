/**
 * @author xixy10@foxmail.com zhangtong13@mails.tsinghua.edu.cn
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.dao.factory;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import cn.pku.ueba.model.activity.ActivityRecord;
import cn.pku.ueba.model.activity.ActivityType;

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
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	public static Map<String, Object> getJsonFromActivityRecord(ActivityRecord activityrecord) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(activityrecord);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> result = null;
		try {
			result = mapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

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
		ObjectMapper mapper = new ObjectMapper();
		String s_json = null;
		try {
			s_json = mapper.writeValueAsString(json);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ActivityType type = ActivityType.valueOf((String) json.get("type"));
		ActivityRecord ar = null;
		try {
			ar = mapper.readValue(s_json,
					ARFFactory.getActivityRecordFactoryInstance(type).getActivityRecord().getClass());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ar;
	}

}
