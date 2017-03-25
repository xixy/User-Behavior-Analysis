/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.model.activity;

import java.util.Date;

import cn.pku.ueba.model.Host;
import cn.pku.ueba.model.User;

/* 活动记录基类，主要是谁干了什么，类型由ActivityType指定
 * @author xixiangyu
 * @Version 0.1
 */
public abstract class ActivityRecord {
	private User user;// 表示活动的主题user
	private Host host;// 表示user的活动发生的机器，这个看怎么定义了
	private Date date;// 时间
	private ActivityType type;// 活动类型，可以直接存到
	private String sourceIp;// 源ip
	private String sourcePort;// 源port
	private String desIp;// 目的ip
	private String desPort;// 目的port

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

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String source_ip) {
		this.sourceIp = source_ip;
	}

	public String getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(String source_port) {
		this.sourcePort = source_port;
	}

	public String getDesIp() {
		return desIp;
	}

	public void setDesIp(String des_ip) {
		this.desIp = des_ip;
	}

	public String getDesPort() {
		return desPort;
	}

	public void setDesPort(String des_port) {
		this.desPort = des_port;
	}
}
