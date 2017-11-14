/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.methods;

import java.util.Date;

/**
 *
 * @author Florian
 */
public class DatumPlusEinTag
{
    public Date rechnen(Date datum)
    {
        long millis = datum.getTime();
        millis = millis + (24*1000*3600);
        datum = new Date(millis);

        return datum;
    }
}
