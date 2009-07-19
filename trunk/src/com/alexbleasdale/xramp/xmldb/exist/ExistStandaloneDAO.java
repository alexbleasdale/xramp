package com.alexbleasdale.xramp.xmldb.exist;

import org.apache.log4j.Logger;

import com.alexbleasdale.xramp.bean.Credentials;
import com.alexbleasdale.xramp.xmldb.DAO;

public class ExistStandaloneDAO implements DAO {

	private final Logger LOG = Logger.getLogger(ExistStandaloneDAO.class);

	/**
	 * Constructor
	 * 
	 * @param c
	 */
	public ExistStandaloneDAO(Credentials c) {
		LOG.info("Using the standalone eXist DAO:: connecting to database at "
				+ c.getUrl());

	}

	@Override
	public boolean storeXMLDocument(String xmlDocument) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
