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
    static public JsonObject toJson()
    {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        
        JsonArrayBuilder errors = Json.createArrayBuilder();
        JsonArrayBuilder warnings = Json.createArrayBuilder();
        
        // add Errors        
//        if (error_hasFeedingFailed)
//            errors.add(String.format("Die letzte Fütterung um %s war nicht erfolgreich", failedFeedingTime));
//        
//        if (error_hasLoadingIpOrVersionFailed)
//            errors.add("Lader der Ip-Adresse oder der Version ist fehlgeschlagen!");
        
        // test
        errors.add("Error");
        errors.add("Error2");
        
        obj.add("Errors", errors);

        // add warnings
//        if (warning_isPackageEmpty)
//            warnings.add("Es wurden alle Futtersackerl verbraucht! Bitte nachfüllen!");
//        
//        if (warning_isUserCreated)
//            warnings.add("Bitte legen Sie einen Benutzer an!");
//        
        // test
        warnings.add("Warning");
        warnings.add("Warning2");
        
        obj.add("Warnings", warnings);
         
        JsonObject listJsonObject =  obj.build();
        
        return listJsonObject;
    }
    
    public static void main(String[] args)
    {
        System.out.println(toJson());
    }
}
