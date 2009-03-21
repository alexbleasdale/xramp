package com.alexbleasdale.xramp.xmldb;

/**
 *
 * This will evaluate an XPath over a collection and return the nodes back (as text) to stdout
 * Example param:
 * //guid
 *
 * Created by IntelliJ IDEA.
 * User: alexbleasdale
 * Date: Nov 16, 2008
 * Time: 4:10:34 PM
 * To change this template use File | Settings | File Templates.
 */
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;

public class QueryXpathExample {
    public static void main(String args[]) throws Exception {
        String driver = "org.exist.xmldb.DatabaseImpl";
        Class cl = Class.forName(driver);
        Database database = (Database)cl.newInstance();
        DatabaseManager.registerDatabase(database);

        Collection col =
            DatabaseManager.getCollection(
                "xmldb:exist://localhost:8080/exist/xmlrpc/db/perfreport"
            );
        XPathQueryService service =
            (XPathQueryService) col.getService("XPathQueryService", "1.0");
        service.setProperty("indent", "yes");

        ResourceSet result = service.query(args[0]);
        ResourceIterator i = result.getIterator();
        while(i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String)r.getContent());
        }
    }
}
        
