/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月4日 下午1:45:32
 */
package cn.pku.ueba.service;

import java.util.Date;

/**
 * 用来生成特征向量的Runnable接口的类
 * 
 * @author xixy10@foxmail.com
 */
public class FeatureVectorGenerator implements Runnable {

	private Date date;

	/**
	 * 调用generate函数
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@SuppressWarnings("deprecation")
	public void run() {
		date = new Date();
		// 得到该时刻的小时数
		int hour = date.getHours();
		while (true) {
			date = new Date();
			if (date.getHours() != hour) {
				hour = date.getMinutes();
				generate(hour);
			}

		}
	}

	/**
	 * 对用户活动进行读取，并生成特征向量，并进行持久化
	 * <p>
	 * 如果hour是0，那么就要读取23小时+23:00到0:00之间的数据进行生成
	 * </p>
	 * <p>
	 * 如果hour不是0，那么就读取过去一小时的数据来生成特征
	 * </p>
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void generate(int hour) {

	}

}
