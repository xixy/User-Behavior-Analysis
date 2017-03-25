/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.model;

/**
 * 实体的抽象类
 */
public abstract class Entity {
	/**
	 * 实体id
	 */
	private Integer id;
	/**
	 * 实体风险得分
	 */
	private Double riskscore;
	/**
	 * 实体名称，例如张三、server1024
	 */
	private String name;
	/**
	 * 所属部门
	 */
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
