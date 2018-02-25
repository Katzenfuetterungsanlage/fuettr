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
    private boolean machineStateOn;
    private JsonObject timesJsonObject;

    @Override
    protected Object doInBackground() throws Exception
    {
        try
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

                // nicht mehr im EDT Thread -> Exception wird geworten -> mit trycatch abfangen
                // behoben durch eine neue Mathode im MainWindow: createInstance
                machineStateOn = MainWindow.getInstance().isMachineStateOn();

                // test
                System.out.println("machineState: " + machineStateOn);
                
                if (machineStateOn)
                {
                    // test
                    System.out.println("if vom AbstractFeedingWorker");

                    // next feeding
                    timesJsonObject = MainWindow.getInstance().getTimes();
                    string = next(timesJsonObject);

                    if ("".equals(lastFeedingTime))
                    {
                        lastFeedingTime = "ausstehend";
                    }

                    // feedingcycle
                    timeOfDay = MainWindow.getInstance().getTimeOfDay();
                    time1 = timesJsonObject.getString("time1");
                    time2 = timesJsonObject.getString("time2");
                    time3 = timesJsonObject.getString("time3");
                    time4 = timesJsonObject.getString("time4");

                    if (time1.equals(timeOfDay))
                    {
                        pi4j_instance.feed();
                        lastFeedingTime = time1;
                        string = string + ";" + lastFeedingTime;
                        publish(string);
                    }

                    else
                    {
                        if (time2.equals(timeOfDay))
                        {
                            pi4j_instance.feed();
                            lastFeedingTime = time2;
                            string = string + ";" + lastFeedingTime;
                            publish(string);
                        }
                        else
                        {
                            if (time3.equals(timeOfDay))
                            {
                                pi4j_instance.feed();
                                lastFeedingTime = time3;
                                string = string + ";" + lastFeedingTime;
                                publish(string);
                            }

                            else

                            {
                                if (time4.equals(timeOfDay))
                                {
                                    pi4j_instance.feed();
                                    lastFeedingTime = time4;
                                    string = string + ";" + lastFeedingTime;
                                    publish(string);
                                }
                            }
                        }
                    }

                    string = string + ";" + lastFeedingTime;

                    publish(string);

                    TimeUnit.MILLISECONDS.sleep(500);
                }
            }

            pi4j_instance.closeController();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return 1;
    }
}
