/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.utils;

import java.util.Date;

/**
 *
 * @author Florian
 */
public class DatePlusOneDay
{
    public static Date addOneDay(Date date)
    {
        long millis = date.getTime();
        millis = millis + (24*1000*3600);
        date = new Date(millis);

        return date;
    }
}
