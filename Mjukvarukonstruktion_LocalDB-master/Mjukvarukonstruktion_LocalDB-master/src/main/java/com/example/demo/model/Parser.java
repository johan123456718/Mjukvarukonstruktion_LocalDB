package com.example.demo.model;

/**
 * Class for parsing the kafka messages. 
 */
public class Parser {

    /**
    * Parse Journalrecord into a string
    * @param journalRecord is the record to be parsed
    * @returns journalRecord as a string with breaking points.
    */
    public static String RecordToString(JournalRecord journalRecord){
        StringBuilder builder = new StringBuilder();
        builder.append(journalRecord.getDate()).append("<br>")
                .append(journalRecord.getCategory()).append("<br>")
                .append(journalRecord.getContent());
        return builder.toString();
    }

    /**
    * Parse String into a JournalRecord
    * @param string is the string to be parsed
    * @returns journalRecord as a an object.
    */
    public static JournalRecord StringToJournalRecord(String string){
        String[] parts = string.split("<br>");
        String date = parts[0];
        String category = parts[1];
        String content = parts[2];
        return new JournalRecord(category, content, date);
    }
}
