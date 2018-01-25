/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.singleton.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import diplomarbeit_projekt.gui.MainWindow;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
public class Mongodb_Singleton
{
    private MongoClient mongodb;
    private final DB database;
    private final DBCollection collTimes;
    private final DBCollection collStatus;
    private final DBCollection collUser;
    private final DBCollection collHardware;
    
    private static Mongodb_Singleton instance = null;
    
    protected Mongodb_Singleton()
    {
        try
        {
            mongodb = new MongoClient();
        }
        catch (UnknownHostException ex)
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        database = mongodb.getDB("fuettr");
        collTimes = database.getCollection("data_times");
        collStatus = database.getCollection("data_status");
        collUser = database.getCollection("data_user");
        collHardware = database.getCollection("data_hardware");
    }
    
    public static Mongodb_Singleton getInstance()
    {
        if (instance == null)
        {
            instance = new Mongodb_Singleton();
        }
        return instance;
    }
    
    public DBObject getTimeDoc ()
    {
        return collTimes.find(new BasicDBObject("identifier", "Times")).next();
    }
    
    public DBObject getUserDoc ()
    {
        return collTimes.find(new BasicDBObject("identifier", "User")).next();
    }
    
    public void setTimeDoc (BasicDBObject obj)
    {
        collTimes.update(new BasicDBObject("identifier", "Times"), obj);
    }
    
    public void setUserDoc (BasicDBObject obj)
    {
        collUser.update(new BasicDBObject("identifier", "User"), obj);
    }
    
    public void setHardwareDoc (BasicDBObject obj)
    {
        collHardware.update(new BasicDBObject("identifier", "User"), obj);
    }
       
}
