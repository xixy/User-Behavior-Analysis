package PKU.UEBA;

public class Host extends Entity{
	public static Integer hostId = 0;
	public Host(){
		this.type = EntityType.host;
		this.id= ++hostId;
		this.riskscore = 0;
	}
	public Host(String name){
		this.type = EntityType.user;
		this.id = ++hostId;
		this.name = name;
		this.riskscore = 0;
	}
}
