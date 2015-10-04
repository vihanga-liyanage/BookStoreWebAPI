package com.bookstorewebapi.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("services") //removed * wildcard to make this more compatible with tomcat
public class Services {

    private static final String api_version = "1.1.03"; //version of the api
    private static BookStore b;
    
    public Services() {
        b = new BookStore("localhost", 27017, "BookStore");
    }

    @GET
    @Path("/status")
    @Produces(MediaType.TEXT_HTML)
    public String getStatus() {
        return "<p>API available for services.</p>";
    }

    @GET
    @Path("/version")
    @Produces(MediaType.TEXT_HTML)
    public String getVersion() {
        return "<p>Version:</p>" + api_version;
    }

    @GET
    @Path("/saveInvoices")
    @Produces(MediaType.TEXT_HTML)
    public String saveInvoices() {
        String csvFile = "D:/Developer/My Projects/BookStoreWebAPI/src/test/resources/Data/small.csv";
        b = new BookStore("localhost", 27017, "BookStore");
        return b.mongoWriter(csvFile);
    }
    
    @GET
    @Path("/getRecommends/{cus-id}")
    @Produces(MediaType.TEXT_HTML)
    public String getRecommends(@PathParam ("cus-id")String id) {
        
        return b.getRecommends(id);
    }
    
    @GET
    @Path("/help")
    @Produces(MediaType.TEXT_HTML)
    public String getReadme() {
        return 
"<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n" +
"        <title>Readme</title>\n" +
"    </head>\n" +
"    <body>\n" +
"        <h1>Read Me</h1>\n" +
"        <p>\n" +
"            This is the user manual for ABC Book Store Web API. <br> \n" +
"            Type in the follow URLs to get results.\n" +
"        </p>\n" +
"        <h3>\n" +
"            <a href=\"http://localhost:8080/BookStoreWebAPI/services/help\">\n" +
"                http://localhost:8080/BookStoreWebAPI/services/help\n" +
"            </a>\n" +
"        </h3>\n" +
"        <p>Returns help page.</p>\n" +
"        \n" +
"        <h3>\n" +
"            <a href=\"http://localhost:8080/BookStoreWebAPI/services/saveInvoices\">\n" +
"                http://localhost:8080/BookStoreWebAPI/services/saveInvoices\n" +
"            </a>\n" +
"        </h3>\n" +
"        <p>Save the invoices in the database.</p>\n" +
"        \n" +
"        <h3>\n" +
"            <a href=\"http://localhost:8080/BookStoreWebAPI/services/version\">\n" +
"                http://localhost:8080/BookStoreWebAPI/services/version\n" +
"            </a>\n" +
"        </h3>\n" +
"        <p>Returns version number of the API.</p>\n" +
"        \n" +
"        <h3>\n" +
"            <a href=\"http://localhost:8080/BookStoreWebAPI/services/status\">\n" +
"                http://localhost:8080/BookStoreWebAPI/services/status\n" +
"            </a>\n" +
"        </h3>\n" +
"        <p>Returns the API status.</p>\n" +
"        \n" +
"        <h3>\n" +
"            <a href=\"http://localhost:8080/BookStoreWebAPI/services/getRecommends/C0001\">\n" +
"                http://localhost:8080/BookStoreWebAPI/services/status/getRecommends/{cutomer id}\n" +
"            </a>\n" +
"        </h3>\n" +
"        <p>Returns the recommended books for a given customer. Customer id should be provided in the url.</p>\n" +
"        \n" +
"    </body>\n" +
"</html>";
    }
}
