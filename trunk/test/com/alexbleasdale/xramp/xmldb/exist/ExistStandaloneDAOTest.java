package com.alexbleasdale.xramp.xmldb.exist;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alexbleasdale.config.TestConfig;

public class ExistStandaloneDAOTest {

	@BeforeClass
	public void setUp() throws Exception {
		ExistStandaloneDAO esd = new ExistStandaloneDAO(TestConfig
				.getTestExistDbCredentials());
	}

	@Test
	public void testCreateCollection() throws Exception {
		Assert.assertTrue(true);
	}

}
