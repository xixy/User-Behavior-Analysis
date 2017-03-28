/**
 * @author xixy10@foxmail.com
 * @version V0.1 2017年3月28日 下午4:48:15
 */
package cn.pku.ueba.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 */
public class ActivityRecordDAOImplTest {

	/**
	 * Test method for {@link cn.pku.ueba.dao.impl.ActivityRecordDAOImpl#index(cn.pku.ueba.model.activity.ActivityRecord)}.
	 */
	@Test
	public void testIndex() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link cn.pku.ueba.dao.impl.ActivityRecordDAOImpl#getActivityRecordByUser(cn.pku.ueba.model.User, int)}.
	 */
	@Test
	public void testGetActivityRecordByUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link cn.pku.ueba.dao.impl.ActivityRecordDAOImpl#getActivityRecord(int)}.
	 */
	@Test
	public void testGetActivityRecord() {
		ActivityRecordDAOImpl ardi = new ActivityRecordDAOImpl();
		ardi.getActivityRecord(30);
	}

}
