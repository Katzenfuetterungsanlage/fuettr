/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.gui.workers;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import diplomarbeit_projekt.singleton.mongodb.Mongodb_Singleton;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author Florian
 */
public class AbstractImportAndShowTimesWorker extends SwingWorker<Object, DBObject>
{
    // mongodb instance
    private Mongodb_Singleton mongodb_instance;
    
    private String strCnt;

    @Override
    protected Object doInBackground() throws Exception
    {
        mongodb_instance = Mongodb_Singleton.getInstance();
        
        strCnt = "cnt: " + mongodb_instance.countTimesColl();
        Logger.getLogger(strCnt).log(Level.FINE, strCnt);
        if (mongodb_instance.countTimesColl() < 1)
        {
            BasicDBObject timeDoc = new BasicDBObject("identifier", "Times")
              .append("time1", "06:00")
              .append("time1_active", true)
              .append("time2", "10:00")
              .append("time2_active", true)
              .append("time3", "14:00")
              .append("time3_active", true)
              .append("time4", "18:00")
              .append("time4_active", true);
            
            mongodb_instance.setTimeDoc(timeDoc);
        }

        while (!isCancelled())
        {
            // import times
            DBObject doc = mongodb_instance.getTimeDoc();

            publish(doc);
            
            TimeUnit.MILLISECONDS.sleep(250);
        }
        return 1;
    }
}
