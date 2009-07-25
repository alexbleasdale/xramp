package com.alexbleasdale.xramp.xmldb.xhive;

import org.apache.log4j.Logger;

import com.alexbleasdale.xramp.xmldb.DAO;
import com.xhive.XhiveDriverFactory;
import com.xhive.core.interfaces.XhiveDriverIf;
import com.xhive.core.interfaces.XhiveFederationIf;
import com.xhive.core.interfaces.XhiveSessionIf;

/**
 * XHive is a proprietary product and is NOT open source. Nor is it free to use.
 * This project does not bundle the jars required for this class to work.
 * 
 * They can be downloaded from here (registration required for developers):
 * 
 * https://community.emc.com/docs/DOC-3139
 * 
 * (c) EMC Corporation (www.emc.com).
 * 
 * This portion is NOT covered by any open source licence (including the MIT
 * licence).
 * 
 * See: http://www.emc.com
 * 
 */
public class XHiveDAO implements DAO {

	private final Logger LOG = Logger.getLogger(XHiveDAO.class);

	private XhiveDriverIf driver;

	@Override
	public boolean storeXMLDocument(String xmlDocument) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteDatabase(String databaseName) {

		XhiveSessionIf session = getDriver().createSession();

		try {
			session.connect("superuser", "admin", null);
			session.begin();
			XhiveFederationIf federation = session.getFederation();

			if (federation.hasDatabase(databaseName)) {
				LOG.info("Deleting database with name: " + databaseName);
				federation.deleteDatabase(databaseName);
			} else {
				LOG.warn("Database does not exist: " + databaseName);
			}
			session.commit();
		} catch (Exception e) {
			LOG.error("DeleteDatabase failed: ");
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.rollback();
			}
			if (session.isConnected()) {
				session.disconnect();
			}
			driver.close();
		}
		return true;
	}

	public boolean createDatabase(String databaseName) {

		XhiveSessionIf session = getDriver().createSession();

		try {
			session.connect("superuser", "admin", null);
			session.begin();
			XhiveFederationIf federation = session.getFederation();

			if (federation.hasDatabase(databaseName)) {
				LOG.warn("Database already exists: " + databaseName);
			} else {
				LOG.info("Creating new database with name: " + databaseName);

				federation.createDatabase(databaseName, "admin", null,
						System.out);
			}
			session.commit();
		} catch (Exception e) {
			LOG.error("CreateDatabase failed: ");
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.rollback();
			}
			if (session.isConnected()) {
				session.disconnect();
			}
			driver.close();
		}
		return true;
	}

	/**
	 * 
	 * @return
	 */
	private XhiveDriverIf getDriver() {

		// TODO - pass in driver from config
		if (driver == null) {
			driver = XhiveDriverFactory.getDriver("xhive://localhost:1235");
		}

		if (!driver.isInitialized()) {
			driver.init(1024);
		}
		return driver;
	}
}
