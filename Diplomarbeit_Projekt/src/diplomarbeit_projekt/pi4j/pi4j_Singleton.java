/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.pi4j;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */

/*
    Notification:
    sensor1: bowl
        GPIO_00
    sensor2: conveyor belt - feed bag
        GPIO_01
    engine1: bowl
        Transistor_1.1 GPIO_02
        Transistor_1.2 GPIO_03
        Transistor_1.3 GPIO_04
        Transistor_1.4 GPIO_05
    engine2: conveyor belt - feed bag
        Transistor_2.1 GPIO_06
        Transistor_2.2 GPIO_10
        Transistor_2.3 GPIO_08
        Transistor_2.4 GPIO_09

    1-4 -> clockwise
    2-3 -> counterclockwise

    transistor is active when voltage is supplied => pin state = high
    sensor return high when the object is in front of it
 */
public class pi4j_Singleton
{
    // 1. check if a feed bag is available
    // 2. check the position of the bowl
    // 3. move bowl to the filling location
    // 4. check the position of the bowl again
    // 5. check position of the feed  bag -> wrong position -> move conveyor belt
    // 6. move conveyor belt until the next feed bag reaches the sensor = filling the bowl
    // 7. move bowl to the feeding location
    
    final GpioController gpio;

    final GpioPinDigitalInput pin00;
    final GpioPinDigitalInput pin01;
    final GpioPinDigitalOutput pin02;
    final GpioPinDigitalOutput pin03;
    final GpioPinDigitalOutput pin04;
    final GpioPinDigitalOutput pin05;
    final GpioPinDigitalOutput pin06;
    final GpioPinDigitalOutput pin07;
    final GpioPinDigitalOutput pin08;
    final GpioPinDigitalOutput pin09;

    private static pi4j_Singleton instance = null;

    protected pi4j_Singleton()
    {
        gpio = GpioFactory.getInstance();

        // sensor1: bowl -> GPIO_00
        pin00 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_DOWN);
        pin00.setShutdownOptions(true);

        // sensor2: conveyor belt - feed bag -> GPIO_01
        pin01 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_01, PinPullResistance.PULL_DOWN);
        pin01.setShutdownOptions(true);

        // engine1: bowl -> Transistor_1.1 -> GPIO_02
        pin02 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, PinState.LOW);
        pin02.setShutdownOptions(true, PinState.LOW);

        // engine1: bowl -> Transistor_1.2 -> GPIO_03
        pin03 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, PinState.LOW);
        pin03.setShutdownOptions(true, PinState.LOW);

        // engine1: bowl -> Transistor_1.3 -> GPIO_04
        pin04 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, PinState.LOW);
        pin04.setShutdownOptions(true, PinState.LOW);

        // engine1: bowl -> Transistor_1.4 -> GPIO_05
        pin05 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, PinState.LOW);
        pin05.setShutdownOptions(true, PinState.LOW);

        // engine2: conveyor belt - feed bag -> Transistor_2.1 -> GPIO_06
        pin06 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, PinState.LOW);
        pin06.setShutdownOptions(true, PinState.LOW);

        // engine2: conveyor belt - feed bag -> Transistor_2.2 -> GPIO_07
        pin07 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, PinState.LOW); // use GPIO_10 instead of GPIO_7 because of error
        pin07.setShutdownOptions(true, PinState.LOW);

        // engine2: conveyor belt - feed bag -> Transistor_2.3 -> GPIO_08
        pin08 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, PinState.LOW);
        pin08.setShutdownOptions(true, PinState.LOW);

        // engine2: conveyor belt - feed bag -> Transistor_2.4 -> GPIO_09
        pin09 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, PinState.LOW);
        pin09.setShutdownOptions(true, PinState.LOW);
    }

    public static pi4j_Singleton getInstance()
    {
        if (instance == null)
        {
            instance = new pi4j_Singleton();
        }
        return instance;
    }

    public void feed()
    {
        // number of max usages 
        int maxbowlUsageIndex = 4;
        // number of usages
        int bowlUsageIndex = 0;

        Boolean wait = false;

        Logger.getLogger("Start feeding").log(Level.INFO, "Start feeding");
        // 1. check if a feed bag is available
        if (pin01.getState() == PinState.HIGH)
        {
            String str = "cehck feed bag" + pin01.getPin() + "=" + pin01.getState();
            Logger.getLogger(str).log(Level.FINE, str);

            // 2. check the position of the bowl
            if (pin00.getState() != PinState.HIGH)
            {
                str = "check bowl position" + pin00.getPin() + "=" + pin00.getState();
                Logger.getLogger(str).log(Level.FINE, str);

                // 3. move bowl to the filling location
                pin02.high();
                pin05.high();

                // position reached
                while (wait != true)
                {
                    if (pin00.getState() == PinState.HIGH)
                    {
                        pin02.low();
                        pin05.low();

                        wait = true;
                    }
                }
                wait = false;

                Logger.getLogger("bowl position adjusted").log(Level.FINE, "bowl position adjusted");
            }

            // 4. check the position of the bowl again
            if (pin00.getState() == PinState.HIGH)
            {
                str = "check bowl position again" + pin00.getPin() + "=" + pin00.getState();
                Logger.getLogger(str).log(Level.FINE, str);

                // 5. check position of the feed  bag -> wrong position -> move conveyor belt
                // should be in position - according to the first check
                if (bowlUsageIndex < maxbowlUsageIndex)
                {
                    // move bowl back to the filling location
                    pin03.high();
                    pin04.high();

                    //position reached
                    while (wait != true)
                    {
                        if (pin01.getState() == PinState.HIGH)
                        {
                            pin06.low();
                            pin09.low();

                            wait = true;
                        }
                    }
                    wait = false;

                    Logger.getLogger("bowl moved back to feeding location").log(Level.FINE, "bowl moved back to feeding location");

                    // 6. move conveyor belt until the next feed bag reaches the sensor = filling the bowl
                    pin06.high();
                    pin09.high();

                    // position reached
                    while (wait != true)
                    {
                        if (pin01.getState() == PinState.HIGH)
                        {
                            pin06.low();
                            pin09.low();

                            wait = true;
                        }
                    }
                    wait = false;

                    // +1 to the number of usages
                    bowlUsageIndex++;
                    Logger.getLogger("feeding successful").log(Level.INFO, "feeding successful");
                }
                else
                {
                    // 6. move conveyor belt until the next feed bag reaches the sensor = filling the bowl
                    pin06.high();
                    pin09.high();

                    // position reached
                    while (wait != true)
                    {
                        if (pin01.getState() == PinState.HIGH)
                        {
                            pin06.low();
                            pin09.low();

                            wait = true;
                        }
                    }
                    wait = false;

                    // new bowl, number of usages = 0
                    bowlUsageIndex = 0;

                    Logger.getLogger("feeding successful").log(Level.INFO, "feeding successful");
                }
            }
            else
            {
                // Sollte nie vorkommen
            }
        }
        else
        {
            Logger.getLogger("No feeding bag left!").log(Level.SEVERE, "No feeding bag left!");
        }

        // shutdown gpio controller
        gpio.shutdown();
        Logger.getLogger("shutdown GPIO controller").log(Level.FINE, "shutdown GPIO controller");
    }

    public String statusEngine1()
    {
        String strEngine1;
        if (pin02.getState() == PinState.HIGH && pin05.getState() == PinState.HIGH)
        {
            strEngine1 = "Dreht im Uhrzeigersinn";
        }
        else
        {
            if (pin03.getState() == PinState.HIGH && pin04.getState() == PinState.HIGH)
            {
                strEngine1 = "Dreht gegen Uhrzeigersin";
            }
            else
            {
                strEngine1 = "Motor steht still";
            }
        }
        return strEngine1;
    }

    public String statusEngine2()
    {
        String strEngine2;
        if (pin06.getState() == PinState.HIGH && pin09.getState() == PinState.HIGH)
        {
            strEngine2 = "Dreht im Uhrzeigersinn";
        }
        else
        {
            if (pin07.getState() == PinState.HIGH && pin08.getState() == PinState.HIGH)
            {
                strEngine2 = "Dreht gegen Uhrzeigersin";
            }
            else
            {
                strEngine2 = "Motor steht still";
            }
        }
        return strEngine2;
    }

    public String statusSensor1()
    {
        String strSensor1;
        if (pin00.getState() == PinState.HIGH)
        {
            strSensor1 = "Betätigt";
        }
        else
        {
            strSensor1 = "Unbetätigt";
        }
        return strSensor1;
    }

    public String statusSensor2()
    {
        String strSensor2;
        if (pin01.getState() == PinState.HIGH)
        {
            strSensor2 = "Betätigt";
        }
        else
        {
            strSensor2 = "Unbetätigt";
        }

        return strSensor2;
    }
    
    public void moveEngine1Clockwise()
    {
        if (pin02.getState() == PinState.HIGH || pin05.getState() == PinState.HIGH || pin03.getState() == PinState.HIGH || pin04.getState() == PinState.HIGH)
        {
            stopEngine1();
        }
        
        pin02.high();
        pin05.high();
    }
    
    public void moveEngine1Counterclockwise()
    {
        if (pin02.getState() == PinState.HIGH || pin05.getState() == PinState.HIGH || pin03.getState() == PinState.HIGH || pin04.getState() == PinState.HIGH)
        {
            stopEngine1();
        }
        
        pin03.high();
        pin04.high();
    }
    
    public void stopEngine1()
    {
        pin02.low();
        pin05.low();
        
        pin03.low();
        pin04.low();
    }
    
    public void moveEngine2Clockwise()
    {
        if (pin06.getState() == PinState.HIGH || pin07.getState() == PinState.HIGH || pin08.getState() == PinState.HIGH || pin09.getState() == PinState.HIGH)
        {
            stopEngine2();
        }
        
        pin06.high();
        pin09.high();
    }
    
    public void moveEngine2Counterclockwise()
    {
        if (pin06.getState() == PinState.HIGH || pin07.getState() == PinState.HIGH || pin08.getState() == PinState.HIGH || pin09.getState() == PinState.HIGH)
        {
            stopEngine2();
        }
        
        pin07.high();
        pin08.high();
    }
    
    public void stopEngine2()
    {
        pin06.low();
        pin09.low();
        
        pin07.low();
        pin08.low();
    }
}
