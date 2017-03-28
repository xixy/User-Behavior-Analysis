/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月23日 下午7:10:24
 */
package cn.pku.ueba.model.feature;

import cn.pku.ueba.resource.featurefield.FeatureField;

/**
 * 特征模型
 */
public class Feature {
	private FeatureField key;
	private float value;

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public FeatureField getKey() {
		return key;
	}

	public void setKey(FeatureField key) {
		this.key = key;
	}
}
