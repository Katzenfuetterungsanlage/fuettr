/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.gui;

import com.mongodb.*;
import com.mongodb.util.JSON;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import java.io.StringReader;
import static java.lang.String.valueOf;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import static java.lang.String.valueOf;

/**
 *
 * @author Florian
 */

/*
    In Database:
    ===========
    "wlan_name" : "<name>"
    "wlan_password" : "<password>"

    if there is a name and a password stored, then the machine will connect to the
    wlan automatically, if the name or password is wrong a warning will be displayed on 
    the main window 

    password can be empty
    
*/

public class ConnectToWlan extends javax.swing.JDialog
{
    String wlan_name;
    String wlan_password;
    
    // create object
    MongoClient mongodb;  
    DB database;
    DBCollection collWlan;
    
    private int connect (String name, String password)
    {
        // TODO - implement connect
        
        return 1; //connected succesful
    }
    
    /**
     * Creates new form BenutzerAnlegen
     */
    public ConnectToWlan(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
               
        initComponents();
         
        // connect to Database
        try
        {
            mongodb = new MongoClient();
        }
        catch (UnknownHostException ex)
        {
            Logger.getLogger(ConnectToWlan.class.getName()).log(Level.SEVERE, null, ex);
        }
        database = mongodb.getDB("katzenfuetterungsanlage");  
        collWlan = database.getCollection("data_wlan");
        //======================================================================
        
        DBObject wlanDoc = collWlan.find(new BasicDBObject("identifier", "WLAN")).next();
        String strUser = JSON.serialize(wlanDoc);
        
        Logger.getLogger("wlan data imported").log(Level.FINE, "wlan data imported");
        
        JsonReader jsonReader = Json.createReader(new StringReader(strUser));
        JsonObject obj = jsonReader.readObject();
        jsonReader.close();

        wlan_name = obj.getString("wlan_name");
        wlan_password = obj.getString("wlan_password");
        
        if (!"".equals(wlan_name))
        {
            connect(wlan_name, wlan_password);
            lbConnectedWlan.setText("wlan_name");
        }
        
        // TODO - scan for wlan's and display them in cbWlanNames
        
        setLocationRelativeTo(parent);
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        pWlan = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbWlanNames = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        pwtfWlan_password = new javax.swing.JPasswordField();
        cbSavePassword = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbConnectedWlan = new javax.swing.JLabel();
        pButton = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btSpeichern = new javax.swing.JButton();
        btSchließen1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("WLAN-Verbindung");

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jPanel1.setLayout(new java.awt.BorderLayout());

        pWlan.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pWlan.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(0, 2, 0, 4));

        jLabel1.setText("Name");
        jPanel2.add(jLabel1);

        cbWlanNames.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cbWlanNames);

        jLabel3.setText("Passwort:");
        jPanel2.add(jLabel3);
        jPanel2.add(pwtfWlan_password);

        cbSavePassword.setText("Passwort speichern");
        jPanel2.add(cbSavePassword);

        jPanel3.add(jPanel2);

        pWlan.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Verbunden mit: ");
        jPanel5.add(jLabel2);

        lbConnectedWlan.setText("<Wlan>");
        jPanel5.add(lbConnectedWlan);

        jPanel4.add(jPanel5, new java.awt.GridBagConstraints());

        pWlan.add(jPanel4, java.awt.BorderLayout.SOUTH);

        jPanel1.add(pWlan, java.awt.BorderLayout.CENTER);

        pButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 0, 0));
        pButton.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        btSpeichern.setText("Verbinden");
        btSpeichern.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onVerbinden(evt);
            }
        });
        jPanel7.add(btSpeichern);

        btSchließen1.setText("Schließen");
        btSchließen1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onSchließen(evt);
            }
        });
        jPanel7.add(btSchließen1);

        jPanel6.add(jPanel7);

        pButton.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel1.add(pButton, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onSchließen(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onSchließen
    {//GEN-HEADEREND:event_onSchließen
        mongodb.close();
        dispose();
    }//GEN-LAST:event_onSchließen
  
    private void onVerbinden(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onVerbinden
    {//GEN-HEADEREND:event_onVerbinden
        char[] CharWlan_password = pwtfWlan_password.getPassword();
        wlan_password = valueOf(CharWlan_password);
        
        wlan_name = (String)cbWlanNames.getSelectedItem();
        
        pwtfWlan_password.setText("");
        
        connect(wlan_name, wlan_password);
        
        lbConnectedWlan.setText("wlan_name");
        
        if (cbSavePassword.isSelected() == true)
        {
            collWlan.update(new BasicDBObject("identifier", "WLAN"), new BasicDBObject("identifier", "WLAN").append("wlan_name", wlan_name).append("wlan_password", wlan_password));
        } 
    }//GEN-LAST:event_onVerbinden

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(ConnectToWlan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ConnectToWlan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ConnectToWlan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ConnectToWlan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                ConnectToWlan dialog = new ConnectToWlan(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter()
                {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e)
                    {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSchließen1;
    private javax.swing.JButton btSpeichern;
    private javax.swing.JCheckBox cbSavePassword;
    private javax.swing.JComboBox<String> cbWlanNames;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lbConnectedWlan;
    private javax.swing.JPanel pButton;
    private javax.swing.JPanel pWlan;
    private javax.swing.JPasswordField pwtfWlan_password;
    // End of variables declaration//GEN-END:variables
}