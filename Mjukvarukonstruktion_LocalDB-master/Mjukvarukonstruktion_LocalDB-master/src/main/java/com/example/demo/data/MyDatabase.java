package com.example.demo.data;

import com.example.demo.DbInterface;
import com.example.demo.data.mySQL;
import com.mongodb.Mongo;

/**
* Class to setup database instance
*/
public class MyDatabase {
    private static DbInterface db;
    public static void DatabaseConfig(int dbType, int hostPort, String username, String password){
        if (dbType == 1)
            db = new mySQL(username, password, hostPort);
        else if (dbType == 2)
            db = new MongoDb(hostPort);
    }

    public static DbInterface getinstance(){
        return db;
    }
}
