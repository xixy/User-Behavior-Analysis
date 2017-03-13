package PKU.UEBA;
/*
 * show 实体的抽象类，集成的类包括 User Host 以后慢慢加 
 * @author xixiangyu
 * @Time 2017-3-11
 * @Version 0.1
 */
public abstract class Entity {
	public Integer id;//id
	public EntityType type;//Entity type
	public float riskscore;//risk score
	public String name;//名称，例如张三或计算机1
}

