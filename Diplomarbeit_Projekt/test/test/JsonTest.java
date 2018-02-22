/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Florian
 */
public class JsonTest
{
    // errors
    static private Boolean error_hasFeedingFailed = false, error_hasLoadingIpOrVersionFailed = false;

    // warnings
    static private Boolean warning_isPackageEmpty = false, warning_isUserCreated = false;

    static private String failedFeedingTime;
    
    static public JsonObject toJson()
    {
        JsonObjectBuilder obj = Json.createObjectBuilder();

        JsonArrayBuilder errors = Json.createArrayBuilder();
        JsonArrayBuilder warnings = Json.createArrayBuilder();

        // add Errors        
        if (error_hasFeedingFailed)
            errors.add(createJsonObject(String.format("Die letzte Fütterung um %s war nicht erfolgreich", failedFeedingTime)));
        
        if (error_hasLoadingIpOrVersionFailed)
            errors.add(createJsonObject("Laden der Ip-Adresse oder der Version ist fehlgeschlagen!"));
        // test
        errors.add(createJsonObject("Error"));
        errors.add(createJsonObject("Error2"));

        obj.add("Errors", errors);

        // add warnings
        if (warning_isPackageEmpty)
            warnings.add(createJsonObject("Es wurden alle Futtersackerl verbraucht! Bitte nachfüllen!"));
        
        if (warning_isUserCreated)
            warnings.add(createJsonObject("Bitte legen Sie einen Benutzer an!"));
//        
        // test
        warnings.add(createJsonObject("Warning"));
        warnings.add(createJsonObject("Warning2"));

        obj.add("Warnings", warnings);

        JsonObject listJsonObject = obj.build();

        return listJsonObject;
    }
    
    static private JsonObject createJsonObject(String message)
    {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        
        obj.add("message",message);
        obj.add("hidden",false);
        
        JsonObject jsonObject =  obj.build();
        
        return jsonObject;
    }

    public static void main(String[] args)
    {
        System.out.println(toJson());
    }
    
    
}
