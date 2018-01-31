/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.utils;


/**
 *
 * @author user
 */
public class SystemPropertiesReader
{

  public static void main (String[] args)
  {
    System.getProperties().list(System.out);
  }
}
