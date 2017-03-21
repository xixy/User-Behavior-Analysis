package cn.pku.ueba.model;

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
