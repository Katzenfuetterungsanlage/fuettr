/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.gui.workers;

import diplomarbeit_projekt.gui.MainWindow;
import diplomarbeit_projekt.singleton.pi4j.Pi4j_Singleton;
import static diplomarbeit_projekt.utils.NextFeeding.next;
import java.util.concurrent.TimeUnit;
import javax.json.JsonObject;
import javax.swing.SwingWorker;

/**
 *
 * @author Florian
 */
public abstract class AbstractFeedingWorker extends SwingWorker<Object, String>
{
    // pi4j
    private Pi4j_Singleton pi4j_instance;

    private String string, lastFeedingTime = "", time1, time2, time3, time4, timeOfDay;
    private boolean machineState;
    JsonObject obj;

    @Override
    protected Object doInBackground() throws Exception
    {
        // pi4j instance
        if (!"Windows 10".equals(System.getProperty("os.name"))) // change to equals to Raspberry 
        {
            pi4j_instance = Pi4j_Singleton.getInstance();

            System.out.println("pi4j instance created");
        }

        while (!isCancelled()) 
        {
            string = null;
            
            // test
            System.out.println("while vom AbstractFeedingWorker");
            
            machineState = MainWindow.getInstace().isMachineStateOn();
            
            // test
            System.out.println("machineState: " + machineState);
            
            if (machineState) 
            {
                // test
                System.out.println("if vom AbstractFeedingWorker");
                
                // next feeding
                obj = MainWindow.getInstace().getTimes();
                string = next(obj);

                if  ("".equals(lastFeedingTime))
                    lastFeedingTime = "ausstehend";
                
                // feedingcycle
                timeOfDay = MainWindow.getInstace().getTimeOfDay();
                time1 = MainWindow.getInstace().getTime1();
                time2 = MainWindow.getInstace().getTime2();
                time3 = MainWindow.getInstace().getTime3();
                time4 = MainWindow.getInstace().getTime4();
                
                if (time1.equals(timeOfDay))
                {
                    pi4j_instance.feed();
                    lastFeedingTime = MainWindow.getInstace().getTime1();
                    string = string + ";" + lastFeedingTime;
                    publish(string);
                }
                
                if (time2.equals(timeOfDay))
                {
                    pi4j_instance.feed();
                    lastFeedingTime = MainWindow.getInstace().getTime2();
                    string = string + ";" + lastFeedingTime;
                    publish(string);
                }

                if (time3.equals(timeOfDay))
                {
                    pi4j_instance.feed();
                    lastFeedingTime = MainWindow.getInstace().getTime3();
                    string = string + ";" + lastFeedingTime;
                    publish(string);
                }

                if (time4.equals(timeOfDay))
                {
                    pi4j_instance.feed();
                    lastFeedingTime = MainWindow.getInstace().getTime4();
                    string = string + ";" + lastFeedingTime;
                    publish(string);
                }
                               
                string = string + ";" + lastFeedingTime;
                
                // test
                System.out.println(string);
                
                publish(string);

                TimeUnit.MILLISECONDS.sleep(500);
            }
        }
        return 1;
    }
}
