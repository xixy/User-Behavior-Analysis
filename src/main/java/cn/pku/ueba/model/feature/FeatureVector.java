/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.model.feature;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.pku.ueba.model.Entity;

/**
 * 特征向量对象
 */
public class FeatureVector {
	/**
	 * 特征向量
	 */
	private List<Feature> features = new ArrayList<Feature>();
	/**
	 * 该特征向量对应的实体
	 */
	private Entity entity;

	/**
	 * 该特征向量对应的时间戳
	 */
	private String timestamp;

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

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}