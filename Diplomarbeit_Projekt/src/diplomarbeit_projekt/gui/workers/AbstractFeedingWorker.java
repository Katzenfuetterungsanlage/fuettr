/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.gui.workers;

import diplomarbeit_projekt.gui.MainWindow;
import diplomarbeit_projekt.utils.NextFeeding;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingWorker;

/**
 *
 * @author Florian
 */
public abstract class AbstractFeedingWorker extends SwingWorker<Object, String>
{
    private String string1, strLog;
    
     @Override
        protected Object doInBackground() throws Exception
        {
            while (!isCancelled())
            {
                if (MainWindow.getInstace().isMachineStateOn())
                {
                    // next feeding
                    NextFeeding nextFeeding = new NextFeeding();
                    string1 = nextFeeding.next(times);

                    // feedingcycle
                    if (time1.equals(timeOfDay))
                    {
                        pi4j_instance.feed();
                        lastFeeding = 1;
                        lastFeedingTime = time1;
                        publish();
                    }
                    else
                    {
                        if (time2.equals(timeOfDay))
                        {
                            pi4j_instance.feed();
                            lastFeeding = 2;
                            lastFeedingTime = time2;
                            publish();
                        }
                        else
                        {
                            if (time3.equals(timeOfDay))
                            {
                                pi4j_instance.feed();
                                lastFeeding = 3;
                                lastFeedingTime = time3;
                                publish();
                            }
                            else
                            {
                                if (time4.equals(timeOfDay))
                                {
                                pi4j_instance.feed();
                                    lastFeeding = 4;
                                    lastFeedingTime = time4;
                                    publish();
                                }
                            }
                        }
                    }
                }
                else
                {
                    string1 = "-;-";
                }

                publish();

                TimeUnit.MILLISECONDS.sleep(500);
            }
            return 1;
        }
}
