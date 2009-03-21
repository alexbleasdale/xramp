package com.alexbleasdale.xramp; /**
 * Created by IntelliJ IDEA.
 * User: alexbleasdale
 * Date: Nov 16, 2008
 * Time: 10:10:38 AM
 * To change this template use File | Settings | File Templates.
 */
import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;
import com.alexbleasdale.xramp.xmldb.ExistDBTools;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.UriBuilder;


public class LaunchApp {


    private static int getPort(int defaultPort) {
        String port = System.getenv("JERSEY_HTTP_PORT");
        if (null != port) {
            try {
                return Integer.parseInt(port);
            } catch (NumberFormatException e) {
            }
        }
        return defaultPort;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(getPort(9998)).build();
    }

    public static final URI BASE_URI = getBaseURI();

    protected static SelectorThread startServer() throws IOException {
        final Map<String, String> initParams = new HashMap<String, String>();

        initParams.put("com.sun.jersey.config.property.packages",
                "com.netrii.starfruit.resources");

        System.out.println("Starting grizzly...");
        SelectorThread threadSelector = GrizzlyWebContainerFactory.create(BASE_URI, initParams);
        return threadSelector;
    }

    public static void main(String[] args) throws IOException {
        // start embedded eXist instance
    	System.out.println("starting embedded eXist..");
    	ExistDBTools tdb = new ExistDBTools();
    	try {
			tdb.startEmbeddedExist();
			
			//tdb.getNewCollection("docs");
			//tdb.getResources();
			//tdb.storeXMLDoc("/path/to/my/doc.xml");
			//tdb.getResources();
			tdb.createCollection("docs4");
			tdb.shutdownExist();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// start the REST router
		// TODO - commented out while testing exist - this will start Jersey & Grizzly
		// TODO - helloworld doesn't display in FF3 - fine in Safari
        SelectorThread threadSelector = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nTry out %shelloworld\nHit enter to stop it...",
                BASE_URI, BASE_URI));
        System.in.read();
        threadSelector.stopEndpoint();
        System.exit(0);
    }
}
