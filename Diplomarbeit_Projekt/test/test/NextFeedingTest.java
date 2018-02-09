/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import static diplomarbeit_projekt.utils.NextFeeding.next;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Florian
 */
public class NextFeedingTest
{
    public static void main(String[] args)
    {
        String time1, time2, time3, time4;
        boolean time1_active,  time2_active,  time3_active,  time4_active;
        
        
        BasicDBObject timeDoc = new BasicDBObject("identifier", "Times")
              .append("time1", "06:00")
              .append("time1_active", true)
              .append("time2", "10:00")
              .append("time2_active", true)
              .append("time3", "14:00")
              .append("time3_active", true)
              .append("time4", "18:00")
              .append("time4_active", true);
        
        
        
        String strTimes = JSON.serialize(timeDoc);

            // import times
            JsonReader jsonReader = Json.createReader(new StringReader(strTimes));
            JsonObject obj = jsonReader.readObject();
            jsonReader.close();

//            time1 = obj.getString("time1");
//            time2 = obj.getString("time2");
//            time3 = obj.getString("time3");
//            time4 = obj.getString("time4");
//
//            time1_active = obj.getBoolean("time1_active");
//            time2_active = obj.getBoolean("time2_active");
//            time3_active = obj.getBoolean("time3_active");
//            time4_active = obj.getBoolean("time4_active");

        String result = next(obj);
        
        System.out.println(result);
    }
}
