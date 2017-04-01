/**
 * @author xixy10@foxmail.com zhangtong13@mails.tsinghua.edu.cn
 * @version V0.1 2017年3月31日 下午1:44:10
 */
package cn.pku.ueba.dao.factory;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.pku.ueba.model.activity.ADActivityRecord;
import cn.pku.ueba.model.activity.ActivityRecord;

/**
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class ActivityRecordFactoryTest {

	public static Map<String, Object> result = null;

	static {
		ActivityRecord ar = new ADActivityRecord();
		ar.setUser(UserFactoryTest.user);
		ar.setHost(HostFactoryTest.host);
		ar.setDate(new Date());
		ar.setDesIp("192.168.200.160");
		ar.setDesPort("80");
		ar.setSourceIp("192.168.100.160");
		ar.setSourcePort("100");
		result = ActivityRecordFactory.getJsonFromActivityRecord(ar);

	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.factory.ActivityRecordFactory#getJsonFromActivityRecord(cn.pku.ueba.model.activity.ActivityRecord)}
	 * .
	 */
	@Test
	public void testGetJsonFromActivityRecord() {
		if (result == null)
			fail("GetJsonFromActivityRecord failed");
	}

	/**
	 * Test method for
	 * {@link cn.pku.ueba.dao.factory.ActivityRecordFactory#getActivityRecordFromJson(java.util.Map)}
	 * .
	 */
	@Test
	public void testGetActivityRecordFromJson() {
		ActivityRecord ar = ActivityRecordFactory.getActivityRecordFromJson(result);
		if (ar == null)
			fail("ar==null");
		if (!ar.getUser().getName().equals("张通"))
			fail("GetActivityRedordFromJson failed");
		if (!ar.getHost().getDepartment().equals(HostFactoryTest.host.getDepartment()))
			fail("GetActivityRedordFromJson failed");
	}

}
