/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;


public abstract class AbstractEntity implements JsonItem,Serializable {
    
    @Override
    public JsonObject toJson(){
    
    JsonObjectBuilder builder=Json.createObjectBuilder();
    addJson(builder);
    return builder.build();
    }
    
}
