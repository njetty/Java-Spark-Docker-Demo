package com.omega.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


/**
 * Created by NaveenJetty on 9/19/2016.
 */
@Entity
public class Log {
    @Id
    @Getter @Setter private ObjectId id;
    private String username;
    private String timestamp;
    private String description;

    public Log() {
    }

    public Log(String username, String timestamp, String description) {
        this.username = username;
        this.timestamp = timestamp;
        this.description = description;
    }

    public String toString(){
        return "Username: "+username+" timestamp: "+timestamp+" description: "+description;
    }
}
