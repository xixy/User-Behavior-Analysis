/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年4月5日 下午4:58:55
 */
package cn.pku.ueba.service.producer;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.pku.ueba.model.activity.ActivityType;

/**
 *
 */
public class ActivityRecordProducerFactoryTest {

	/**
	 * Test method for
	 * {@link cn.pku.ueba.service.producer.ActivityRecordProducerFactory#getActivityRecordProducerByActivityType(cn.pku.ueba.model.activity.ActivityType)}
	 * .
	 */
	@Test
	public void testGetActivityRecordProducerByActivityType() {
		ActivityRecordProducer arp = ActivityRecordProducerFactory
				.getActivityRecordProducerByActivityType(ActivityType.ad);
		if (!(arp instanceof ADActivityRecordProducer))
			fail("testGetActivityRecordProducerByActivityType failed");
	}

}
