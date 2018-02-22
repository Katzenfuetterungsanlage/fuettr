/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.singleton.errors_warnings;

import java.util.ArrayList;
import java.util.List;
import javax.json.*;

/**
 *
 * @author Florian
 */

/*
    structure
       error: no feeding package
       error: feeding not succesful
 */
public class ErrorAndWarningHandler_Singleton
{
    // errors
    private Boolean error_hasFeedingFailed = false, error_hasLoadingIpOrVersionFailed = false;
    
    // warnings
    private Boolean warning_isPackageEmpty = false, warning_isUserCreated = false;
    
    private String failedFeedingTime;

    private static ErrorAndWarningHandler_Singleton instance = null;
    List<String> list = null;

    protected ErrorAndWarningHandler_Singleton()
    {
        list = new ArrayList();
    }

    public static ErrorAndWarningHandler_Singleton getInstance()
    {
        if (instance == null)
        {
            instance = new ErrorAndWarningHandler_Singleton();
        }
        return instance;
    }

    public int listSize()
    {
        return list.size();
    }
    
    public JsonObject toJson()
    {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        
        JsonArrayBuilder errors = Json.createArrayBuilder();
        JsonArrayBuilder warnings = Json.createArrayBuilder();
        
        // add Errors        
        if (error_hasFeedingFailed)
            errors.add(String.format("Die letzte Fütterung um %s war nicht erfolgreich", failedFeedingTime));
        
        if (error_hasLoadingIpOrVersionFailed)
            errors.add("Lader der Ip-Adresse oder der Version ist fehlgeschlagen!");
        
        // test
        errors.add("Error");
        errors.add("Error2");
        
        obj.add("Errors", errors);

        // add warnings
        if (warning_isPackageEmpty)
            warnings.add("Es wurden alle Futtersackerl verbraucht! Bitte nachfüllen!");
        
        if (warning_isUserCreated)
            warnings.add("Bitte legen Sie einen Benutzer an!");
        
        // test
        warnings.add("Warning");
        warnings.add("Warning2");
        
        obj.add("Warnings", warnings);
         
        JsonObject listJsonObject =  obj.build();
        
        return listJsonObject;
    }

    // Getter
    public List<String> getList()
    {
        list.clear();
        
        // add errors
        if (error_hasFeedingFailed)
            list.add(String.format("Die letzte Fütterung um %s war nicht erfolgreich", failedFeedingTime));
        
        if (error_hasLoadingIpOrVersionFailed)
            list.add("Lader der Ip-Adresse oder der Version ist fehlgeschlagen!");
        
        // add warnings
        if (warning_isPackageEmpty)
            list.add("Es wurden alle Futtersackerl verbraucht! Bitte nachfüllen!");
        
        if (warning_isUserCreated)
            list.add("Bitte legen Sie einen Benutzer an!");
        
        return list;
    }
    
    public void setPackageEmptyWarning(Boolean warningOn)
    {
        warning_isPackageEmpty = warningOn;
    }
    
    public void setFeedingHasFailedError (Boolean errorOn, String errorTime)
    {
        error_hasFeedingFailed = errorOn;
        failedFeedingTime = errorTime;
    }
            
}
