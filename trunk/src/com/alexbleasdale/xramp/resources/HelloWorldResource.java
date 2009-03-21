package com.alexbleasdale.xramp.resources; /**
 * Created by IntelliJ IDEA.
 * User: alexbleasdale
 * Date: Nov 16, 2008
 * Time: 10:09:33 AM
 * To change this template use File | Settings | File Templates.
 */


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorldResource {

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World";
    }

}
