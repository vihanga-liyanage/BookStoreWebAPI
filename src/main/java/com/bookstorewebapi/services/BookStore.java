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

/**
 *
 * @author Vihanga Liyanage
 */
public class BookStore {

    private MongoClient mongo;
    private DB db;
    private DBCollection collection;
    private String collectionName = "invoice";
    
    public BookStore(String host, int port, String dbName) {
        mongo = new MongoClient(host, port);
        db = mongo.getDB(dbName);
    }
    
    public int mongoWriter(String csvFile) {
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
            return 1;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
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
}
