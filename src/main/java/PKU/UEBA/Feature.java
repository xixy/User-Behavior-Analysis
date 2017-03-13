package PKU.UEBA;

import java.util.Date;

public class Feature implements Comparable{

	public Date date;//时间，应该只取某一天的特征
	public String key;//key用来索引，例如是下载流量大小、登陆时长等
	public float value;//对应的值，例如下载量100M这样

	public String toString() {
		return date.toString() + " " + key + " " + value;
	}
	
	public Feature(String key,float value){
		this.key = key;
		this.value = value;
		this.date = new Date();
	}

	public static void main(String[] args) {

	}

	public int compareTo(Object o) {
		Feature f = (Feature)o;
		return this.key.compareTo(f.key);
	}

}
