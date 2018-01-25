/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.list;

import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Florian
 */

/*
    Alle Fehlern und Warnungen in lokale Varbialen speichern welche von einem Worker in die Liste aufgenommen werden wenn der String nicht leer ( != "" ) ist
    und speichert die Fehler und Warnungen in die Datenbank und gibt sie an den Webserver weiter

*/

public class ErrorAndWarningModel extends AbstractListModel
{
    private final List<String> errorAndWarning; 

    public ErrorAndWarningModel(List<String> infoPanelList)
    {
        this.errorAndWarning = infoPanelList;
    }
    
    @Override
    public int getSize()
    {
        return errorAndWarning.size();
    }

    @Override
    public Object getElementAt(int index)
    {
        return errorAndWarning.get(index);
    }
    
}
