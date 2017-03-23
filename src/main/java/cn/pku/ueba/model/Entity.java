/**
 * 实体的抽象类，继承的类包括 User Host 以后慢慢加 
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.model;

public abstract class Entity {
	private Integer id;// 实体id
	private Double riskscore;// risk score
	private String name;// 名称，例如张三或计算机1
	private String department;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getRiskscore() {
		return riskscore;
	}

	public void setRiskscore(Double riskscore) {
		this.riskscore = riskscore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
