package com.alexbleasdale.xramp.resources;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import java.io.IOException;

/**
 * Executes an XQuery resource on a collection / db - this is still largely untested.
 */
@Path("/xquery/{xq}")
public class ExecuteXQueryResource {
    byte[] responseBody;

    @SuppressWarnings("finally")
	@GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    @Produces("application/xml")


    public String getQuery(@PathParam("xq") String xQuery) {
        String url = "http://localhost:8080/exist/rest/listall.xq";
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        // Provide custom retry handler is necessary
           method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                   new DefaultHttpMethodRetryHandler(3, false));

           try {
             // Execute the method.
             int statusCode = client.executeMethod(method);

             if (statusCode != HttpStatus.SC_OK) {
               System.err.println("Method failed: " + method.getStatusLine());
             }

             // Read the response body.
             responseBody = method.getResponseBody();

             // Deal with the response.
             // Use caution: ensure correct character encoding and is not binary data
            //System.out.println(new String(responseBody));

           } catch (HttpException e) {
             System.err.println("Fatal protocol violation: " + e.getMessage());
             e.printStackTrace();
           } catch (IOException e) {


             System.err.println("Fatal transport error: " + e.getMessage());
             e.printStackTrace();
           } finally {
             // Release the connection.
             method.releaseConnection();

               return (new String(responseBody));
            
           }

    }
 }