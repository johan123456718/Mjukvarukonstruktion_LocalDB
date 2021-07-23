package com.example.demo;

import com.example.demo.model.JournalRecord;

import java.util.List;
/**
 * Interface over the functionality required for database queries.
 */
public interface DbInterface {
    /**
    * Save a JournalRecord to database
    * @param record is the record to be saved
    */
    public void saveToDB(JournalRecord record);
    /**
    * Get a specific record for the database
    * @param id is the id of the record to be fetched
    * @returns a record
    */
    public JournalRecord getJournalRecordById(int id);
    /**
    * Get all the records in the database
    * @param record is the record to be saved
    * @returns a list containing all the records
    */
    public List<JournalRecord> getAllJournalRecords();
}

