package cn.pku.ueba.dao.impl;

import static org.junit.Assert.fail;

import org.junit.Test;

import cn.pku.ueba.dao.factory.ADActivityRecordFactory;
import cn.pku.ueba.dao.factory.ActivityRecordFactory;
import cn.pku.ueba.model.activity.ActivityType;

public class ActivityRecordDAOImplTest {

	@Test
	public void testIndex() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetActivityRecordByUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetActivityRecord() {
		ActivityRecordDAOImpl ardi = new ActivityRecordDAOImpl();
		ardi.getActivityRecord(30);
	}

}
