/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月27日 下午4:22:45
 */
package cn.pku.ueba.dao.factory;

import java.util.Date;
import java.util.List;

import cn.pku.ueba.model.activity.ActivityRecord;
import cn.pku.ueba.model.activity.ActivityType;
import cn.pku.ueba.model.feature.Feature;
import cn.pku.ueba.resource.featurefield.FeatureField;
import cn.pku.ueba.util.DateUtil;

/**
 * 特征Factory
 * 
 * @author xixy10@foxmail.com
 */
public class FeatureFactory {
	/**
	 * 返回特征对象
	 * 
	 * @return 特征对象
	 */
	public static Feature getFeature() {
		return new Feature();
	}

	/**
	 * 得到计数Feature,这里默认处理的是同一个用户的用户活动
	 * 
	 * @param ars
	 *            同一个用户活动记录list
	 * @param type
	 *            需要统计的活动类型 any则为所有，否则按照活动类型进行统计
	 * @return 特征
	 */
	public static Feature getCountFeature(List<ActivityRecord> ars, ActivityType type) {
		Feature feature = getFeature();
		feature.setKey(FeatureField.count);
		int count = 0;
		if (type == ActivityType.any) {
			count = ars.size();
		} else {
			for (ActivityRecord ar : ars) {
				if (ar.getType().equals(type)) {
					count++;
				}
			}
		}
		feature.setValue(count);
		return feature;
	}

	/**
	 * 得到频率Feature,这里默认处理的是同一个用户的用户活动
	 * 
	 * @param ars
	 *            同一个用户活动记录list
	 * @param type
	 *            需要统计的活动类型 any则为所有，否则按照活动类型进行统计
	 * @param interval
	 *            时间间隔
	 * @return 特征
	 */
	public static Feature getFrequencyFeature(List<ActivityRecord> ars, ActivityType type, int interval) {
		Feature feature = getCountFeature(ars, type);
		float count = feature.getValue();
		feature.setValue(count / interval);
		return feature;
	}

	/**
	 * 求取平均值
	 * 
	 * @param obj
	 *            float的list
	 * @return 平均值
	 */
	public static float getAverage(List<Float> obj) {
		float average = 0;
		int count = 0;
		for (float f : obj) {
			average += f;
			count++;
		}
		return average / count;
	}

	/**
	 * 求取标准差
	 * 
	 * @param obj
	 *            float的list
	 * @return 标准差
	 */
	public static float getStandardDeviation(List<Float> obj) {
		int count = 0;
		float sd = 0;
		float average = getAverage(obj);
		for (float f : obj) {
			count++;
			sd += Math.sqrt(((f - average) * (f - average)));
		}
		return sd / (count - 1);
	}

	/**
	 * 求取最大时间间隔
	 * 
	 * @param ars
	 *            活动list
	 * @return 最大时间间隔，单位是秒
	 */
	public static long getMaxInterval(List<ActivityRecord> ars) {
		long maxInterval = 0;
		Date old = DateUtil.getDateFromESDate(ars.get(0).getTimestamp());
		for (ActivityRecord ar : ars) {
			Date date = DateUtil.getDateFromESDate(ar.getTimestamp());
			long interval = date.getTime() - old.getTime();
			if (interval > maxInterval)
				maxInterval = interval;
			old = date;
		}
		return maxInterval / 1000;
	}

	/**
	 * 求取最小时间间隔
	 * 
	 * @param ars
	 *            活动list
	 * @return 最小时间间隔，单位是秒
	 */
	public static long getMinInterval(List<ActivityRecord> ars) {
		long minInterval = 24 * 60 * 60 * 1000;// 初始设置为1天
		Date old = DateUtil.getDateFromESDate(ars.get(0).getTimestamp());
		for (ActivityRecord ar : ars) {
			Date date = DateUtil.getDateFromESDate(ar.getTimestamp());
			long interval = date.getTime() - old.getTime();
			if (interval < minInterval)
				minInterval = interval;
			old = date;
		}
		return minInterval / 1000;
	}

}
