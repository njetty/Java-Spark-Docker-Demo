package com.omega;

import com.google.gson.Gson;
import com.omega.model.Log;
import com.omega.service.LogService;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by NaveenJetty on 9/19/2016.
 */
public class Api {
    static LogService logservice = new LogService();

    public static void main(String[] args) {

        Gson gson = new Gson();
        post("/add-log",(req,res)->{
            res.type("application/json");
            Log log = gson.fromJson(req.body(), Log.class);
            return logservice.addData(log);
        },gson::toJson);

        get("/",(req,res)->{
            res.type("application/json");
            return logservice.getAllLogs();
        },gson::toJson);

    }
}
