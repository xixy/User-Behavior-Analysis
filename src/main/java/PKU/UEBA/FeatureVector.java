package PKU.UEBA;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;

/*
 * 特征向量，由一组特征构成，必然要属于一个User或者Host
 * @author xixiangyu
 * @Version 0.1
 */
public class FeatureVector {
	public TreeSet<Feature> features;

	public Entity entity;

	public FeatureVector() {
		features = new TreeSet<Feature>();
	}

	public void addFeature(Feature feature) {
		features.add(feature);
	}

	public String toString() {
		String res = entity.name+",";
		for (Feature feature : features)
			res += feature.value + ",";
		return res;
	}

	public void outPutFeatureVector(BufferedWriter bf) {
		try {
			bf.append(this.toString());
			System.out.println("over");
			bf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FeatureVector fv = new FeatureVector();
		fv.entity = new User("Alicia");
		fv.addFeature(new Feature("1", 100));
		fv.addFeature(new Feature("2", 100));
		fv.addFeature(new Feature("3", 100));
		File file = new File("./file.txt");
		BufferedWriter bf = null;
		try {
			bf = new BufferedWriter(new PrintWriter(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		fv.outPutFeatureVector(bf);

	}

}
