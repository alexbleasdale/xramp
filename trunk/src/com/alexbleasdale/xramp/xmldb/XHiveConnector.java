package com.alexbleasdale.xramp.xmldb;

import java.io.IOException;

import org.xml.sax.SAXException;

import com.xhive.XhiveDriverFactory;
import com.xhive.core.interfaces.XhiveDriverIf;
import com.xhive.core.interfaces.XhiveFederationIf;
import com.xhive.core.interfaces.XhiveSessionIf;

public class XHiveConnector {

	public static void main(String[] args) {
		// create a db
		XhiveDriverIf driver = XhiveDriverFactory
				.getDriver("xhive://localhost:1235");
		if (!driver.isInitialized()) {
			driver.init(1024);
		}
		XhiveSessionIf session = driver.createSession();
		session.connect("superuser", "admin", null);
		XhiveFederationIf federation = session.getFederation();
		try {
			federation.createDatabase("newdb", "admin", null, System.out);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
