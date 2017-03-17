package cn.pku.ueba.model;

import java.util.Date;

/* 一条活动记录，主要是谁干了什么，类型由ActivityType指定
 * @author xixiangyu
 * @Version 0.1
 */
public class ActivityRecord {
	private User user;
	private Host host;
	private Date date;
	private ActivityType type;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ActivityType getType() {
		return type;
	}

	public void setType(ActivityType type) {
		this.type = type;
	}
}
