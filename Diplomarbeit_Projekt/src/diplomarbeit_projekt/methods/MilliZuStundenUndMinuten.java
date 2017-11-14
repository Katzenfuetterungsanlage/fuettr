/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.methods;


/**
 *
 * @author florian
 */
public class MilliZuStundenUndMinuten
{
  public String rechnen(long millisekunden)
  {
    long minuten, stunden;
        
    stunden = millisekunden / 1000 / 3600;
    if (stunden != 0)
      minuten = millisekunden / 1000 / 60 % (60*stunden);
    else
      minuten = millisekunden / 1000 / 60 % 60;
    
    String std = Long.toString(stunden);
    String min = Long.toString(minuten);
    
//      System.out.println(min);
//      System.out.println(std);
    
    String string = String.format("%s:%s",std,min);
    
    return string;  
  }
  
//  public static void main (String[] args)
//  {
//    MilliZuStundenUndMinuten rechner = new MilliZuStundenUndMinuten();
//    String str = rechner.rechnen(24000000);
//    
//    System.out.format("HH:MM = %s%n",str);
//  }
}
