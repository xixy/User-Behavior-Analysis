/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月29日 下午4:36:03
 */
package cn.pku.ueba.service.factory;

import static org.junit.Assert.fail;

import org.junit.Test;

import cn.pku.ueba.model.activity.ActivityType;
import cn.pku.ueba.service.ActivityRecordProducer;
import cn.pku.ueba.service.impl.ADActivityRecordProducer;

/**
 *
 */
public class ActivityRecordProducerFactoryTest {

	/**
	 * Test method for
	 * {@link cn.pku.ueba.service.factory.ActivityRecordProducerFactory#getActivityRecordProducerByActivityType(cn.pku.ueba.model.activity.ActivityType)}
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
