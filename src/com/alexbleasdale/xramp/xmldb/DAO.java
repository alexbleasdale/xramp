package com.alexbleasdale.xramp.xmldb;

public interface DAO {

	// public abstract DAO(String url, String username, String password);

	public abstract boolean storeXMLDocument(String xmlDocument)
			throws Exception;

}
