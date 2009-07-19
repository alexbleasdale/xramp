package com.alexbleasdale.config;

import com.alexbleasdale.xramp.bean.Credentials;

public class TestConfig {

	public static Credentials getTestExistDbCredentials() {
		Credentials c = new Credentials();
		c.setUrl("xmldb:exist://localhost:8080/exist/xmlrpc/db");
		c.setUsername("admin");
		c.setPassword("admin");
		return c;
	}
}
