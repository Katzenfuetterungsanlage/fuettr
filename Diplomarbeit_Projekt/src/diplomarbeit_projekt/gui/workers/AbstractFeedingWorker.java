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
import javax.swing.SwingWorker;

/**
 *
 * @author Florian
 */
public abstract class AbstractFeedingWorker extends SwingWorker<Object, String>
{

    // pi4j
    private Pi4j_Singleton pi4j_instance;

    private String string, lastFeedingTime = "";
    private boolean test;

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
            System.out.println("while vom AbstractFeedingWorker");
            
            test = MainWindow.getInstace().isMachineStateOn();
            System.out.println(test);
            
            if (test) // MainWindow.getInstace().isMachineStateOn()
            {
                // test
                System.out.println("if vom AbstractFeedingWorker");
                
                // next feeding
                string = next(MainWindow.getInstace().getTimes());

                // feedingcycle
                if (MainWindow.getInstace().getTime1().equals(MainWindow.getInstace().getTimeOfDay()))
                {
                    pi4j_instance.feed();
                    lastFeedingTime = MainWindow.getInstace().getTime1();
                    string = string + ";" + lastFeedingTime;
                    publish(string);
                }

                if (MainWindow.getInstace().getTime2().equals(MainWindow.getInstace().getTimeOfDay()))
                {
                    pi4j_instance.feed();
                    lastFeedingTime = MainWindow.getInstace().getTime2();
                    string = string + ";" + lastFeedingTime;
                    publish(string);
                }

                if (MainWindow.getInstace().getTime3().equals(MainWindow.getInstace().getTimeOfDay()))
                {
                    pi4j_instance.feed();
                    lastFeedingTime = MainWindow.getInstace().getTime3();
                    string = string + ";" + lastFeedingTime;
                    publish(string);
                }

                if (MainWindow.getInstace().getTime4().equals(MainWindow.getInstace().getTimeOfDay()))
                {
                    pi4j_instance.feed();
                    lastFeedingTime = MainWindow.getInstace().getTime4();
                    string = string + ";" + lastFeedingTime;
                    publish(string);
                }

                if  ("".equals(lastFeedingTime))
                    lastFeedingTime = "ausstehend";
                               
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
