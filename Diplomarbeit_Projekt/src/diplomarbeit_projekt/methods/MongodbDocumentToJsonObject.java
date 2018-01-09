/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.methods;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.bson.Document;

/**
 *
 * @author Florian
 */
public class MongodbDocumentToJsonObject
{
    public static JsonObject DocToJsonObject (Document doc)
    {
        String str = doc.toJson();
            
        JsonReader jsonReader = Json.createReader(new StringReader(str));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        
        return object;
    }
}
