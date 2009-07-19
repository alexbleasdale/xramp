package com.alexbleasdale.xramp.xmldb;

/**
 * Created by IntelliJ IDEA.
 *
 * Example params:
 * /db/starfruit 98b006e3.xml
 *
 * User: alexbleasdale
 * Date: Nov 16, 2008
 * Time: 3:44:07 PM
 * To change this template use File | Settings | File Templates.
 */
import org.apache.log4j.Logger;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;
import javax.xml.transform.OutputKeys;

public class RetrieveExample {
	private Logger LOG = Logger.getLogger(RetrieveExample.class);
    protected static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";

    public static void main(String args[]) throws Exception {
        String driver = "org.exist.xmldb.DatabaseImpl";

        // initialize database driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        DatabaseManager.registerDatabase(database);

        // get the collection
        Collection col = DatabaseManager.getCollection(URI + args[0]);
       // Collection col = DatabaseManager.getCollection(URI + "/docs2");
        
        col.setProperty(OutputKeys.INDENT, "no");
        XMLResource res = (XMLResource)col.getResource(args[1]);
        if(res == null)
            System.out.println("document not found!");
        else
            System.out.println(res.getContent());   }
}
        
