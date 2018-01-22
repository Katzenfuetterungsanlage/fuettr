/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.methods;

import com.mongodb.*;
import com.mongodb.util.JSON;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Florian et Florian
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
      DB database = mongodb.getDB("fuettr");

      DBCollection collTimes = database.getCollection("data_times");
      DBCollection collUser = database.getCollection("data_user");
      DBCollection collHardware = database.getCollection("data_hardware");
      DBCollection collInfo = database.getCollection("data_info");

      // collection.drop(); // löschen
      // Create Document and insert it into the collection
// =============================================================================                       
      DBObject timeDoc = new BasicDBObject("identifier", "Times")
              .append("time1", "12:20")
              .append("time1_active", true)
              .append("time2", "13:20")
              .append("time2_active", true)
              .append("time3", "14:20")
              .append("time3_active", true)
              .append("time4", "15:20")
              .append("time4_active", true);

      collTimes.insert(timeDoc);

      DBObject userDoc = new BasicDBObject("identifier", "User")
              .append("user_name", "katze")
              .append("user_password", "1234");

      collUser.insert(userDoc);

      DBObject infoDoc = new BasicDBObject("identifier", "Info")
              .append("serialnumber", "0001 0002 0003 0004")
              .append("internal", "Raspberry Pi 3 Model B")
              .append("version", "1.1 beta")
              .append("wlanState", "not connected");

      collInfo.insert(infoDoc);

      DBObject infoStatusDoc = new BasicDBObject("identifier", "Status")
              .append("nextFeeding", "10:10")
              .append("lastFeeding", "ausstehend")
              .append("nextFeedingIn", "10min")
              .append("machineState", "AUS");

      collInfo.insert(infoStatusDoc);

      DBObject hardwareDoc = new BasicDBObject("identifier", "Hardware")
              .append("sensor1", "true")
              .append("sensor2", "false")
              .append("engine1", "moving")
              .append("engine2", "not moving");

      collHardware.insert(hardwareDoc);
//==============================================================================

      System.out.println(collTimes.count()); //number of documents

      //Read from a Collection
// =============================================================================
      // read one more time from MongoDB
//            timeDoc = collTimes.find(eq("identifier", "Times")).first();
      timeDoc = collTimes.find(new BasicDBObject("identifier", "Times")).next();

      System.out.println(JSON.serialize(timeDoc));

//            userDoc = collUser.find(eq("identifier", "User")).first();
      System.out.println(JSON.serialize(userDoc));

//            infoDoc = collInfo.find(eq("identifier", "Info")).first();
      System.out.println(JSON.serialize(infoDoc));

//            hardwareDoc = collHardware.find(eq("identifier", "Hardware")).first();
      System.out.println(JSON.serialize(hardwareDoc));
// =============================================================================

      // Document to JsonObject 
// =============================================================================    
      String test = JSON.serialize(infoDoc);

      JsonReader jsonReader = Json.createReader(new StringReader(test));
      JsonObject object = jsonReader.readObject();
      jsonReader.close();

      System.out.println("JsonObject: " + object);

      System.out.println("interner Prozessor  : " + object.getString("internal"));

      String test2 = JSON.serialize(timeDoc);

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
