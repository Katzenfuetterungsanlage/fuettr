/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.server;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Florian 
 */
public class Request
{

  private final String method;
  private final String url;
  private final String protocoll;
  private final HashMap<String, String> attributes;

  public Request(List<String> body)
  {
    attributes = new HashMap<>();
    String[] f = body.get(0).split("\\s+");
    if (f.length != 3)
    {
      throw new IllegalArgumentException("Invalid request");
    }
    method = f[0];
    url = f[1];
    protocoll = f[2];
    for (int i = 1; i < body.size(); i++)
    {
      String line = body.get(i);
      int j = line.indexOf(':');
      if (j > 0)
      {
        attributes.put(line.substring(0, j), line.substring(j + 1).trim());
      }
      else
      {
        System.out.println("Error: wrong request " + line);
      }
    }
  }

  public String getMethod()
  {
    return method;
  }

  public String getUrl()
  {
    return url;
  }

  public String getProtocoll()
  {
    return protocoll;
  }

  public HashMap<String, String> getAttributes()
  {
    return attributes;
  }

}
