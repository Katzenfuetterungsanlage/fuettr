/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.methods;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Florian
 */
public class MongodbCreateCollectionsAndDocuments
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            MongoClient mongodb = new MongoClient();
            MongoDatabase database = mongodb.getDatabase("katzenfuetterungsanlage");
            
            MongoCollection<Document> collTimes = database.getCollection("data_times");
            MongoCollection<Document> collUser = database.getCollection("data_user");
            MongoCollection<Document> collHardware = database.getCollection("data_hardware");
            MongoCollection<Document> collInfo = database.getCollection("data_info");
            
            //collection.drop(); // löschen
            
            //Create Document and insert it into the collection
// =============================================================================                       
            Document timeDoc = new Document ("identifier", "Times")
                    .append("time1", "12:20")
                    .append("time1_active", true)
                    .append("time2", "13:20")
                    .append("time2_active", true)
                    .append("time3", "14:20")
                    .append("time3_active", true)
                    .append("time4", "15:20")
                    .append("time4_active", true);
            
            collTimes.insertOne(timeDoc);
            
            Document userDoc = new Document ("identifier", "User")
                    .append("user_name", "katze")
                    .append("user_password", "1234");
            
            collUser.insertOne(userDoc);
            
            Document infoDoc = new Document ("identifier", "Info")
                    .append("serialnumber", "0001 0002 0003 0004")
                    .append("internal", "Raspberry Pi 3 Model B")
                    .append("version", "1.1 beta")
                    .append("wlanState", "not connected");
            
            collInfo.insertOne(infoDoc);
            
            Document infoStatusDoc = new Document ("identifier", "Status")
                    .append("nextFeeding", "0001 0002 0003 0004")
                    .append("lastFeedung", "Raspberry Pi 3 Model B")
                    .append("nextFeedingIn", "1.1 beta")
                    .append("machineState", "not connected");
            
            collInfo.insertOne(infoStatusDoc);
            
            Document hardwareDoc = new Document ("identifier", "Hardware")
                    .append("sensor1", "true")
                    .append("sensor2", "false")
                    .append("engine1", "moving")
                    .append("engine2", "not moving");
            
            collHardware.insertOne(hardwareDoc);           
//==============================================================================
        
            System.out.println(collTimes.count()); //number of documents

            //Read from a Collection
// =============================================================================
            // read one more time from MongoDB
            timeDoc = collTimes.find(eq("identifier", "Times")).first();
            
            System.out.println(timeDoc.toJson());
            
            userDoc = collUser.find(eq("identifier", "User")).first();
            
            System.out.println(userDoc.toJson());
            
            infoDoc = collInfo.find(eq("identifier", "Info")).first();
            
            System.out.println(infoDoc.toJson());
            
            hardwareDoc = collHardware.find(eq("identifier", "Hardware")).first();
            
            System.out.println(hardwareDoc.toJson());
// =============================================================================
            
            // Document to JsonObject 
// =============================================================================    
            String test = infoDoc.toJson();
            
            JsonReader jsonReader = Json.createReader(new StringReader(test));
            JsonObject object = jsonReader.readObject();
            jsonReader.close();
            
            System.out.println("JsonObject: " + object);
            
            System.out.println("interner Prozessor  : " + object.getString("internal"));
            
            String test2 = timeDoc.toJson();
            
            jsonReader = Json.createReader(new StringReader(test2));
            JsonObject object2 = jsonReader.readObject();
            jsonReader.close();
            
            System.out.println("JsonObject: " + object2);
            
            System.out.println("time 1: " + object2.getString("time1"));
            
// =============================================================================
            
            mongodb.close();
        
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}
