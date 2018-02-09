/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import static java.util.logging.Level.*;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */

public class LoggerTest
{
    static private FileHandler fh;
    static private Logger logger = Logger.getLogger("main");
    static Filter filter;
    
    public static void main(String[] args) throws IOException
    {
//        Logger.getLogger("test").log(Level.FINEST, "test");
//        Logger.getLogger("test").log(Level.FINER, "test");
//        Logger.getLogger("test").log(Level.FINE, "test");
//        Logger.getLogger("test").log(Level.CONFIG, "test");
//        Logger.getLogger("test").log(Level.INFO, "test");
//        Logger.getLogger("test").log(Level.WARNING, "test");
//        Logger.getLogger("test").log(Level.SEVERE, "test");

        fh = new FileHandler("C:\\Users\\Florian\\Desktop\\MyLogFile.log", true);
        logger.addHandler(fh);
        
        logger.setLevel(FINEST);
                
        logger.info("test 3");
        logger.severe("Test 4");

    }

}
