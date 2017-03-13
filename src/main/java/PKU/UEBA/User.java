package PKU.UEBA;

public class User extends Entity{
	public static Integer userId = 0;
//	public User(){
//		this.type = EntityType.user;
//		this.id= ++userId;
//		this.riskscore = 0;
//	}
	public User(String name){
		this.type = EntityType.user;
		this.id = ++userId;
		this.name = name;
		this.riskscore = 0;
	}

}
