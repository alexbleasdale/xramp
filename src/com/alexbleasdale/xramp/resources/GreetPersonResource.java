package com.alexbleasdale.xramp.resources;

/**
 * Created by IntelliJ IDEA.
 * User: alexbleasdale
 * Date: Nov 16, 2008
 * Time: 10:27:45 AM
 * To change this template use File | Settings | File Templates.
 */

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@Path("/greet/{username}")
public class GreetPersonResource {

    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    @Produces("text/plain")


    public String getUser(@PathParam("username") String userName) {
        return "Hello " + userName + "!";
    }
}
