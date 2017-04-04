/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月4日 下午1:43:08
 */
package cn.pku.ueba.service;

/**
 * 系统运行的类，包含了main函数
 * 
 * @author xixy10@foxmail.com
 */
public class SystemRun {

	/**
	 * 用来启动两个线程，一个用来生成用户活动，一个用来生成特征
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 创建生成活动的线程
		Thread t1 = new Thread(new ActivityRecordGenerator());
		// 创建提取特征的线程
		Thread t2 = new Thread(new FeatureVectorGenerator());

		// 线程开始执行
		t1.start();
		t2.start();

	}

}
