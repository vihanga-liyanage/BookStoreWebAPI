package com.mycompany.bookstorewebapi.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("services") //removed * wildcard to make this more compatible with tomcat
public class Services {

    private static final String api_version = "1.1.03"; //version of the api

    @GET
    @Path("/status")
    @Produces(MediaType.TEXT_HTML)
    public String getStatus() {
        return "<p>Java Web Service</p>";
    }

    @GET
    @Path("/version")
    @Produces(MediaType.TEXT_HTML)
    public String getVersion() {
        return "<p>Version:</p>" + api_version;
    }

    @GET
    @Path("/displayName/{name}")
    @Produces(MediaType.TEXT_HTML)
    public String displayName(@PathParam("name") String name) {
        return "Your name is " + name;
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
}
