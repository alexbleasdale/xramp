package com.alexbleasdale.xramp.xmldb;

/**
 * A very basic set of tools for launching an embedded eXist DB and performing CRUD operations.
 */

// TODO - need to add some update handling

import java.io.File;
import java.util.Date;

import javax.xml.transform.OutputKeys;

import org.apache.log4j.Logger;
import org.exist.xmldb.DatabaseInstanceManager;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

public class ExistDBTools {

	// TODO - Refactor out username and password so they are retrieved from a
	// config file
	private final Logger LOG = Logger.getLogger(ExistDBTools.class);
	public final static String BASE_URI = "xmldb:exist://";

	public String currentCollection;

	// Accessor Methods
	public String getCurrentCollection() {
		return this.currentCollection;
	}

	// Mutator Methods
	private void setCurrentCollection(String c) {
		this.currentCollection = c;
	}

	/**
	 * Starts the embedded eXist engine
	 * 
	 * @throws Exception
	 */
	public void startEmbeddedExist() throws Exception {
		LOG.info("Starting embedded eXist instance");
		// initialize driver
		Class cl = Class.forName("org.exist.xmldb.DatabaseImpl");
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(database);
	}

	/**
	 * Returns an integer value for the number of individual records in a given
	 * collection
	 * 
	 * @return String
	 * @throws Exception
	 */
	public int getResourceCount() throws Exception {
		Collection col = getCollection(getCurrentCollection());
		String resources[] = col.listResources();
		return resources.length;
	}

	/**
	 * Gets a list of the current stored resources TODO - maybe return list as
	 * array.
	 * 
	 * @throws Exception
	 */
	public void getResources() throws Exception {
		System.out.println("ExistDBTools :: getResources - called at: "
				+ new Date());
		// TODO col = getCurrentCollection()
		// Collection col = getRoot();
		Collection col = getCollection(getCurrentCollection());
		String resources[] = col.listResources();
		System.out.println("Resources [" + resources.length + "]:");
		for (int i = 0; i < resources.length; i++) {
			System.out.println(resources[i] + "\t");
		}
	}

	/**
	 * Creates a collection at the specified location
	 * 
	 * @param collection
	 * @throws Exception
	 */
	public void createCollection(String collection) throws Exception {

		// try to get collection first
		Collection col = getCollection(collection);

		// if that collection exists, do nothing. Otherwise:
		if (getCurrentCollection() != "") {
			System.out.println("Looks like your collection already exists...");
		} else {
			// getCollection returned the root context - create the collection.

			Collection root = DatabaseManager.getCollection(BASE_URI + "/db");
			CollectionManagementService mgtService = (CollectionManagementService) root
					.getService("CollectionManagementService", "1.0");
			col = mgtService.createCollection(collection);

			setCurrentCollection(collection);
			// CollectionManagementService mgtService =
			// (CollectionManagementService)
			// col.getService("CollectionManagementService", "1.0");
			// col = mgtService.createCollection(collection);
			System.out.println("Created collection " + collection
					+ " current context is: " + getCurrentCollection());
		}
	}

	public void storeXMLDoc(String fileName) throws Exception {

		Collection col = getCollection(this.currentCollection);

		if (col != null) {
			XMLResource document = (XMLResource) col.createResource(null,
					"XMLResource");
			File f = new File(fileName);
			if (!f.canRead()) {
				System.out.println("cannot read file " + f);
				return;
			}
			document.setContent(f);
			System.out.print("storing document " + document.getId() + " ... ");
			col.storeResource(document);
			System.out.println("ok.");
		} else {
			System.out.println("Sorry - Unable to find a valid collection at /"
					+ currentCollection);
			// TODO - throw error
		}
	}

	/**
	 * Return a specified XML doc
	 */
	public String returnXMLDocAtIndex(int idx) throws Exception {

		Collection col = getCollection(this.currentCollection);
		col.setProperty(OutputKeys.INDENT, "no");
		// TODO - this is hard coded - so unless the resource exists, error
		// FIXME
		XMLResource res = (XMLResource) col.getResource("accdd798.xml");

		if (res == null) {
			System.out.println("document not found!");
		} else {
			System.out.println(res.getContent());
		}
		// TODO serialise response as stream
		return "res.getContent()";
	}

	/**
	 * Return an XML doc by name
	 * 
	 */
	public String getXMLDocByName(String name) throws Exception {

		Collection col = getCollection(this.currentCollection);
		col.setProperty(OutputKeys.INDENT, "no");
		XMLResource res = (XMLResource) col.getResource(name);

		if (res == null) {
			System.out.println("document not found!");
		} else {
			System.out.println(res.getContent());
		}

		return "fixme - return the string";
	}

	/**
	 * Hook to safely close the embedded eXist instance
	 * 
	 * @throws Exception
	 */

	public void shutdownExist() throws Exception {
		System.out.println("Shutting eXist down");
		Collection col = getRoot();
		// shut down the database
		DatabaseInstanceManager manager = (DatabaseInstanceManager) col
				.getService("DatabaseInstanceManager", "1.0");
		manager.shutdown();
	}

	/**
	 * Returns the root context of the database
	 * 
	 * @return Collection
	 * @throws XMLDBException
	 */
	private Collection getRoot() throws XMLDBException {
		setCurrentCollection("");
		Collection col = DatabaseManager.getCollection(BASE_URI + "/db",
				"admin", "admin");
		return col;
	}

	/**
	 * Returns a specific eXist Collection
	 * 
	 * @return Collection
	 * @throws XMLDBException
	 */
	public Collection getCollection(String collectionName)
			throws XMLDBException {
		Collection col = DatabaseManager.getCollection(BASE_URI + "/db/"
				+ collectionName, "admin", "admin");
		if (col != null) {
			setCurrentCollection(collectionName);
			LOG.info("Collection is now: " + col);
		} else {
			LOG.warn("Unable to get collection - getting root");
			getRoot();
		}
		return col;
	}
}
