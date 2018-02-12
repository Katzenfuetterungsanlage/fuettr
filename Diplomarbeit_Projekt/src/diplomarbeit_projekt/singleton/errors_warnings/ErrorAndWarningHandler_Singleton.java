/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.singleton.errors_warnings;

import java.util.ArrayList;
import java.util.List;
import javax.json.*;

/**
 *
 * @author Florian
 */

/*
    structure
    index[0]: error: no feeding package
    index[1]: ...
 */
public class ErrorAndWarningHandler_Singleton
{

    private static ErrorAndWarningHandler_Singleton instance = null;
    List<String> list = null;

    protected ErrorAndWarningHandler_Singleton()
    {
        list = new ArrayList();
    }

    public static ErrorAndWarningHandler_Singleton getInstance()
    {
        if (instance == null)
        {
            instance = new ErrorAndWarningHandler_Singleton();
        }
        return instance;
    }

    public int listSize()
    {
        return list.size();
    }

    public void addElement(int index, String element)
    {
        list.add(index, element);
    }

    public void removeElement(int index)
    {
        list.remove(index);
    }

    public JsonObject toJson()
    {
        JsonObjectBuilder obj = Json.createObjectBuilder();
//        String string;
//        for (int i = 0;i <= (list.size() - 1);i++)
//        {
//            string = list.get(i);
//
//            obj.add("Error",string);
//        }

        // test
        obj.add("Error", "I bims a Error!");
        obj.add("Warning", "I bims a Warnung!");
        
        JsonObject listJsonObject =  obj.build();
        
        return listJsonObject;
    }

    // Getter
    public List<String> getList()
    {
        return list;
    }
}
