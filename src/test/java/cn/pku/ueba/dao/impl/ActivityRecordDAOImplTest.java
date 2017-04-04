/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月28日 下午4:48:15
 */
package cn.pku.ueba.dao.impl;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import cn.pku.ueba.dao.factory.ActivityRecordFactoryTest;
import cn.pku.ueba.model.activity.ADActivityRecord;
import cn.pku.ueba.model.activity.ActivityRecord;

/**
 *
 */
public class ActivityRecordDAOImplTest {

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.impl.ActivityRecordDAOImpl#index(cn.pku.ueba.model.activity.ActivityRecord)}
	 * .
	 */
	@Test
	public void testIndex() {
		ActivityRecordDAOImpl.getInstance().index(ActivityRecordFactoryTest.ar);
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.impl.ActivityRecordDAOImpl#getActivityRecordByUser(cn.pku.ueba.model.User, int)}
	 * .
	 */
	@Test
	public void testGetActivityRecordByUser() {
		List<ActivityRecord> result = ActivityRecordDAOImpl.getInstance().getActivityRecordByUser(null, 30);
		for (ActivityRecord ar : result) {
			if (!(ar instanceof ADActivityRecord))
				fail("getActivityRecordByUser failed");

		}
	}

}
