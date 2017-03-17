package cn.pku.ueba.model;

/*
 * show 实体的抽象类，集成的类包括 User Host 以后慢慢加 
 * @author xixiangyu
 * @Time 2017-3-11
 * @Version 0.1
 */
public abstract class Entity {
	private Integer id;// 实体id
	private EntityType type;// Entity type
	private float riskscore;// risk score
	private String name;// 名称，例如张三或计算机1

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EntityType getType() {
		return type;
	}

	public void setType(EntityType type) {
		this.type = type;
	}

	public float getRiskscore() {
		return riskscore;
	}

	public void setRiskscore(float riskscore) {
		this.riskscore = riskscore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
