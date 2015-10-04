package com.bookstorewebapi.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("services") //removed * wildcard to make this more compatible with tomcat
public class Services {

    private static final String api_version = "1.1.03"; //version of the api

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
        String csvFile = "D:/Developer/My Projects/BookStoreAPI/input.csv";
        BookStore b = new BookStore("localhost", 27017, csvFile);
        int res = b.mongoWriter(csvFile);
        return "Done. Result = " + res;
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
            "        <h3>http://localhost:8080/BookStoreWebAPI/services/help</h3>\n" +
            "        <p>Returns this read me page.<br><br></p>\n" +
            "        <h3>http://localhost:8080/BookStoreWebAPI/services/saveInvoices</h3>\n" +
            "        <p>Save the invoices in the database.<br><br></p>\n" +
            "        <h3>http://localhost:8080/BookStoreWebAPI/services/version</h3>\n" +
            "        <p>Returns version number of the API.<br><br></p>\n" +
            "        <h3>http://localhost:8080/BookStoreWebAPI/services/status</h3>\n" +
            "        <p>Returns the API status.</p>\n" +
            "        \n" +
            "    </body>\n" +
            "</html>";
    }
}
