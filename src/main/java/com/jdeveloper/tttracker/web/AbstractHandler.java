/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.web;

import com.jdeveloper.tttracker.domain.JsonItem;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author goudjanou
 */
public abstract class AbstractHandler {    
    
    protected  final Logger logger=LoggerFactory.getLogger(this.getClass());
    
    public static String getJsonSuccessData(List<? extends JsonItem> results){
        
        final JsonObjectBuilder builder=Json.createObjectBuilder();
        builder.add("success", true);
        final JsonArrayBuilder arrayBuilder=Json.createArrayBuilder();
        
        for(JsonItem ji:results){
            arrayBuilder.add(ji.toJson());
        }
        
        builder.add("data", arrayBuilder);
        
        return toJsonString(builder.build());
    
    }//end getJsonSuccessData
    
    
    public static String getJsonSuccessData(JsonItem jsonItem){
        
        final JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("success", true);
        builder.add("data", jsonItem.toJson());
        
        return toJsonString(builder.build());
    }
    
    public static String getJsonSuccessData(JsonItem jsonItem,int totalCount){
    
        final JsonObjectBuilder builder=Json.createObjectBuilder();
        builder.add("success",true);
        builder.add("total", totalCount);
        builder.add("data", jsonItem.toJson());
        
        return toJsonString(builder.build());
    }
    
    public static String getJsonSuccessMsg(String msg){
    
        return getJsonMsg(msg,true);
    }
    
    public static String getJsonErrorMsg(String theErrorMessage){
    
        return getJsonMsg(theErrorMessage,false);
    }
    
    
    
    private static String getJsonMsg(String msg, boolean b) {
        
        final JsonObjectBuilder builder=Json.createObjectBuilder();
        builder.add("success", b);
        builder.add("msg", msg);
        
        return toJsonString(builder.build());
    }
    
    
    
    
    
    private static String toJsonString(JsonObject model) {
        
        final StringWriter stWriter = new StringWriter();
        try(JsonWriter jsonWriter = Json.createWriter(stWriter)){
            jsonWriter.writeObject(model);
        }
        
        return stWriter.toString();
    }
    
    protected JsonObject parseJsonObject(String jsonString){
        
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        return reader.readObject();
    
    }
    
    protected Integer getIntegerValue(JsonValue jsonValue){
        
        Integer value = null;
        switch(jsonValue.getValueType()){
            
            case NUMBER:
                JsonNumber num=(JsonNumber)jsonValue;
                value=num.intValue();
                break;
                
            case NULL:
                break;
        
        }
        
        return value;
    
    }
    
}
