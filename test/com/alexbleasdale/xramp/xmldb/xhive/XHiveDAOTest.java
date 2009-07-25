package com.alexbleasdale.xramp.xmldb.xhive;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class XHiveDAOTest {

	private XHiveDAO xhd;

	@BeforeClass
	public void setUp() throws Exception {
		xhd = new XHiveDAO();
		// ExistDAO esd = new ExistDAO(TestConfig
		// .getTestExistDbCredentials());
	}

	@Test
	public void testCreateAndDeleteDatabase() throws Exception {
		boolean b = xhd.deleteDatabase("foo");
		b = xhd.createDatabase("foo");
		b = xhd.deleteDatabase("foo");
		b = xhd.deleteDatabase("foo");

	}

}
