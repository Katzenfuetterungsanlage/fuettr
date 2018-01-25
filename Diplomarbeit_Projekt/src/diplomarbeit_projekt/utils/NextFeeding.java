/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;

/**
 *
 * @author Florian
 */
public class NextFeeding
{

    public String next(JsonObject timesObj)
    {
        Date date1 = null, date2 = null, date3 = null, date4 = null, dateUhrzeit = null;
        String time1, time2, time3, time4;
        String nextFeedingAt = null, nextFeedingIn = null;

        long diffInMillis;

        System.out.println("nextFeeding start");
        
        String d = String.format("%1$tH:%1$tM", new Date(System.currentTimeMillis()));

        time1 = timesObj.getString("time1");
        time2 = timesObj.getString("time2");
        time3 = timesObj.getString("time3");
        time4 = timesObj.getString("time4");
        
        System.out.println(time1 + " + " + time2 + " + " + time3  + " + " + time4 );

        DateFormat format = new SimpleDateFormat("HH:mm", Locale.GERMANY);
        try
        {
            date1 = format.parse(time1);
            date2 = format.parse(time2);
            date3 = format.parse(time3);
            date4 = format.parse(time4);
            dateUhrzeit = format.parse(d);
        }
        catch (ParseException ex)
        {
            Logger.getLogger(NextFeeding.class.getName()).log(Level.SEVERE, null, ex);
        }

        MilliZuStundenUndMinuten milliZuStdUndMin = new MilliZuStundenUndMinuten();
        DatumPlusEinTag datumPlusEinTag = new DatumPlusEinTag();

        if (dateUhrzeit.after(date1) == true && dateUhrzeit.before(date2) == true)
        {
            nextFeedingAt = time2;
            diffInMillis = date2.getTime() - dateUhrzeit.getTime();
            nextFeedingIn = milliZuStdUndMin.rechnen(diffInMillis);
        }

        if (dateUhrzeit.after(date2) == true && dateUhrzeit.before(date3) == true)
        {
            nextFeedingAt = time3;
            diffInMillis = date3.getTime() - dateUhrzeit.getTime();
            nextFeedingIn = milliZuStdUndMin.rechnen(diffInMillis);
        }

        if (dateUhrzeit.after(date3) == true && dateUhrzeit.before(date4) == true)
        {
            nextFeedingAt = time4;
            diffInMillis = date4.getTime() - dateUhrzeit.getTime();
            nextFeedingIn = milliZuStdUndMin.rechnen(diffInMillis);
        }

        if (dateUhrzeit.after(date4) == true || dateUhrzeit.before(date1) && datumPlusEinTag.rechnen(dateUhrzeit).before(datumPlusEinTag.rechnen(date1)) == true)
        {
            nextFeedingAt = time1;
            diffInMillis = date1.getTime() - dateUhrzeit.getTime();
            nextFeedingIn = milliZuStdUndMin.rechnen(diffInMillis);
        }

        String returnValue = nextFeedingAt + ";" + nextFeedingIn;

        return returnValue;
    }
}
