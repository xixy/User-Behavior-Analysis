package cn.pku.ueba.dao.factory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.pku.ueba.dao.impl.UserDAOImpl;
import cn.pku.ueba.model.ActivityRecord;
import cn.pku.ueba.model.ActivityType;
import cn.pku.ueba.model.Host;
import cn.pku.ueba.model.User;

public class ActivityRecordFactory {
	// 创建一个活动记录
	public static ActivityRecord getActivityRecord(User user, Host host, Date date, ActivityType type) {
		ActivityRecord ar = new ActivityRecord();
		ar.setUser(user);
		ar.setHost(host);
		ar.setDate(date);
		ar.setType(type);
		return ar;
	}

	// 将ActivityRecord转换为JSON
	public static Map<String, Object> getJSONFromActivityRecord(ActivityRecord activityrecord) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("user", activityrecord.getUser().getName());
		json.put("host", activityrecord.getHost().getName());
		json.put("date", activityrecord.getDate());
		json.put("type", activityrecord.getType());
		return json;
	}

	// 将ActivityRecord转换为JSON
	public static ActivityRecord getActivityRecordFromJSON(Map<String, Object> json) {
		String username = (String) json.get("user");
		User user = new UserDAOImpl().getUser(username);
		String hostname = (String) json.get("host");
		Host host = null;// todo
		String date_es = (String) json.get("user");
		Date date = null;
		String type = (String) json.get("type");
		ActivityType atype = ActivityType.valueOf(type);
		ActivityRecord ar = getActivityRecord(user, host, date, atype);
		return ar;

	}

}
