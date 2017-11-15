/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.methods;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Florian
 */
public class StartupStreamReader
{
    public String einlesen(InputStream datei, Boolean nachricht)
    {
        String text = null;

        try (final BufferedReader reader = new BufferedReader( //fasst in blöcke zusammen
            new InputStreamReader(datei)))
            //in doInBackground kann nur auf final Objekte zugegriffen werden - file2
        {
            text = reader.readLine();
            if (nachricht == true)
                System.out.println("StartupStreamReader: Streams wurden erfolgreich gelesen!");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        return text;
    }
}

