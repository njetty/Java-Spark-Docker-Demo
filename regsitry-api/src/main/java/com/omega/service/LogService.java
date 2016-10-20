package com.omega.service;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.omega.model.Log;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NaveenJetty on 9/19/2016.
 */
public class LogService {

    MongoClient client = new MongoClient("52.42.119.121",27017);
    Datastore datastore = new Morphia().createDatastore(client,"omega");

    public String addData(Log log){
        datastore.save(log);
        System.out.println("Adding Log: "+log.toString());
        return "Added log";
    }

    public List<Log> getAllLogs(){
        List<Log> list = datastore.find(Log.class).asList();
        if (list != null){
            return list;
        }
        return null;
    }
}
