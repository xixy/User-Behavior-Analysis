/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.model.feature;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeSet;

import cn.pku.ueba.model.Entity;

/**
 * 特征向量对象
 */
public class FeatureVector {
	public TreeSet<Feature> features;
	public Entity entity;

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

	public TreeSet<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(TreeSet<Feature> features) {
		this.features = features;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}