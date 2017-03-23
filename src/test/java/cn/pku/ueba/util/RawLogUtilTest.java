package cn.pku.ueba.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class RawLogUtilTest {

	@Test
	public void testGetActivityTypeFromRawLogItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsBoolIp() {
		if (!RawLogUtil.isBoolIp("192.168.100.2"))
			fail("isBoolIP wrong");

	}

}
