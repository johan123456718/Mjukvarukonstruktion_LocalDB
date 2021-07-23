package com.example.demo.data;

import com.example.demo.DbInterface;
import com.example.demo.model.JournalRecord;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
/**
* MongoDB implementation
*/
public class MongoDb implements DbInterface {
    private MongoClientURI connectionString;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private Document document;

    public MongoDb(int hostPort){
        connectionString = new MongoClientURI("mongodb://localhost:"+hostPort);
        mongoClient = new MongoClient(connectionString);
    }

    @Override
    public void saveToDB(JournalRecord record) {
        document = new Document();
        database = mongoClient.getDatabase("patientjournal");
        collection = database.getCollection("t_record");

        document.append("content", record.getContent());
        document.append("category", record.getCategory());
        document.append("date", record.getDate());
        collection.insertOne(document);
        System.out.println("Success");
    }

    @Override
    public JournalRecord getJournalRecordById(int id) {
        return null;
    }

    @Override
    public List<JournalRecord> getAllJournalRecords() {
       List<JournalRecord> jounrnals = new ArrayList<>();
        database = mongoClient.getDatabase("patientjournal");       //make singleton?
        collection = database.getCollection("t_record");

        for(Document document: collection.find()){
            JournalRecord record = new JournalRecord(document.getString("category"),document.getString("content"), document.getString("date"));
            jounrnals.add(record);
        }
        return jounrnals;
    }
}
