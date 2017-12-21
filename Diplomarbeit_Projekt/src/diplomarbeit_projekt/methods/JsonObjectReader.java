/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.methods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Florian
 */
public class JsonObjectReader
{
    public JsonObject read (String sourceUrl) throws FileNotFoundException
    {
        InputStream url = new FileInputStream(sourceUrl);

        javax.json.JsonReader jsonReader = Json.createReader(url);
        
        JsonObject JsonObject = jsonReader.readObject();
        
        jsonReader.close();
        
        return JsonObject;
    }
}
