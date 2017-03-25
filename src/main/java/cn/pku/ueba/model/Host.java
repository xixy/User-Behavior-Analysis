/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.model;

/**
 * 主机模型
 */
public class Host extends Entity {
	public static Integer hostId = 0;
	private String macaddress;
	private String ip;
	private HostType type;// 主机类型

	public String getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public HostType getType() {
		return type;
	}

	public void setType(HostType type) {
		this.type = type;
	}
}
