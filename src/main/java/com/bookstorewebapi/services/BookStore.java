/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookstorewebapi.services;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

/**
 *
 * @author Vihanga Liyanage
 */
public class BookStore {

    private MongoClient mongo;
    private DB db;
    private DBCollection collection;
    private String collectionName = "invoice";
    private String neo4jDBPath = "~/src/test/resources/Data/Neo4jDBPath/default.graphdb";
    
    public BookStore(String host, int port, String dbName) {
        mongo = new MongoClient(host, port);
        db = mongo.getDB(dbName);
    }
    
    public String mongoWriter(String csvFile) {
        BufferedReader br = null;
        String line;
        String[] headLine = null, data;
        String csvSplitBy = ",";
        BasicDBObject document;
        collection = db.getCollection(collectionName);

        try {
            br = new BufferedReader(new FileReader(csvFile));
            if ((line = br.readLine()) != null) {
                headLine = line.split(csvSplitBy);
            }
            while ((line = br.readLine()) != null) {
                data = line.split(csvSplitBy);
                document = documentCreater(headLine, data);
                collection.insert(document);
            }
            return "Done.";

        } catch (FileNotFoundException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    return e.getMessage();
                }
            }
        }
    }

    private BasicDBObject documentCreater(String[] headLine, String[] data) {
        BasicDBObject document = new BasicDBObject();
        if (headLine.length == data.length) {
            for (int i = 0; i < headLine.length; i++) {
                document.put(headLine[i].trim(), data[i].trim());
            }
        }
        return document;
    }
    
    public String getRecommends(String id){
        /*
        GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
        GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase(neo4jDBPath);

        ExecutionEngine execEngine = new ExecutionEngine(graphDb);
        
        String cypher = "MATCH (java:JAVA) RETURN java";
        
        ExecutionResult execResult = execEngine.execute(cypher);
        String results = execResult.dumpToString();
        System.out.println(results);
                */
        return "Recommends for customer " + id;
    }
}
