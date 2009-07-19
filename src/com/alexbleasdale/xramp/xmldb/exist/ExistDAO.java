package com.alexbleasdale.xramp.xmldb.exist;

import org.apache.log4j.Logger;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

import com.alexbleasdale.xramp.bean.Credentials;
import com.alexbleasdale.xramp.xmldb.DAO;

public class ExistDAO implements DAO {

	private static final String DRIVER = "org.exist.xmldb.DatabaseImpl";

	private final Logger LOG = Logger.getLogger(ExistDAO.class);
	private final Credentials credentials;
	private Database database;

	/**
	 * Constructor
	 * 
	 * @param c
	 */
	public ExistDAO(Credentials c) {
		LOG.info("eXist DAO - connecting to database: " + c.getUrl());
		this.credentials = c;
	}

	@Override
	public boolean storeXMLDocument(String xmlDocument) throws Exception {
		Database db = getConnection();

		return false;
	}

	private Database getConnection() throws XMLDBException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		if (this.database == null) {
			// initialize database driver
			Class<?> cl = Class.forName(DRIVER);
			Database database = (Database) cl.newInstance();
			// database.setProperty("create-database", "true");
			DatabaseManager.registerDatabase(database);
		}
		return this.database;
	}

	/**
	 * Returns the root context of the database
	 * 
	 * @return Collection
	 * @throws XMLDBException
	 */
	private Collection getRoot() throws XMLDBException {
		LOG.debug("Got Root Collection");
		return DatabaseManager.getCollection(credentials.getUrl(), credentials
				.getUsername(), credentials.getPassword());
	}

	/**
	 * Returns a specific eXist Collection
	 * 
	 * @return Collection
	 * @throws XMLDBException
	 */
	public Collection getCollection(String collectionName)
			throws XMLDBException {
		Collection col = DatabaseManager.getCollection(credentials.getUrl()
				+ "/" + collectionName, credentials.getUsername(), credentials
				.getPassword());

		if (col != null) {
			LOG.info("Collection is now: " + col.getName());
		} else {
			LOG.warn("Unable to get collection - getting root");
			return getRoot();
		}
		return col;
	}

}
