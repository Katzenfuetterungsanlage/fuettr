/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Florian
 */
public class Update extends javax.swing.JDialog
{

    boolean updateVerfuegbar = false;

    /**
     * Creates new form Update
     */
    public Update(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);

        initComponents();

        pTextUpdateErfolgreich.setVisible(false);
        lbUpdateVerfuegbar.setText("Update: -");
        btUpdate.setEnabled(false);

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

        jPanel1 = new javax.swing.JPanel();
        pButton = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btSchließen = new javax.swing.JButton();
        pUpdate = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pUeberpruefen = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btUeberpruefen = new javax.swing.JButton();
        lbUpdateVerfuegbar = new javax.swing.JLabel();
        pStarten = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btUpdate = new javax.swing.JButton();
        pTextUpdateErfolgreich = new javax.swing.JPanel();
        pUpdateErfolgreich = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jPanel1.setLayout(new java.awt.BorderLayout());

        pButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 0, 0));
        pButton.setLayout(new java.awt.BorderLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        btSchließen.setText("Schließen");
        btSchließen.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onSchließen(evt);
            }
        });
        jPanel5.add(btSchließen);

        jPanel4.add(jPanel5);

        pButton.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel1.add(pButton, java.awt.BorderLayout.SOUTH);

        pUpdate.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pUpdate.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 16, 32));

        jPanel2.setLayout(new java.awt.GridLayout(0, 1));

        jPanel8.setLayout(new java.awt.GridBagLayout());

        btUeberpruefen.setText("Auf Updates überprüfen");
        btUeberpruefen.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onUeberpruefen(evt);
            }
        });
        jPanel8.add(btUeberpruefen, new java.awt.GridBagConstraints());

        pUeberpruefen.add(jPanel8);

        jPanel2.add(pUeberpruefen);

        lbUpdateVerfuegbar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUpdateVerfuegbar.setText("Update ist verfügbar / nicht verfügbar ");
        lbUpdateVerfuegbar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lbUpdateVerfuegbar);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        btUpdate.setText("Update starten");
        btUpdate.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onUpdate(evt);
            }
        });
        jPanel6.add(btUpdate, new java.awt.GridBagConstraints());

        pStarten.add(jPanel6);

        jPanel2.add(pStarten);

        pTextUpdateErfolgreich.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pTextUpdateErfolgreich.setLayout(new java.awt.BorderLayout());

        pUpdateErfolgreich.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));

        jLabel2.setText("Update erfolgreich!");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pUpdateErfolgreich.add(jLabel2);

        pTextUpdateErfolgreich.add(pUpdateErfolgreich, java.awt.BorderLayout.CENTER);

        jPanel2.add(pTextUpdateErfolgreich);

        jPanel3.add(jPanel2);

        pUpdate.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel1.add(pUpdate, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onSchließen(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onSchließen
    {//GEN-HEADEREND:event_onSchließen
        dispose();
    }//GEN-LAST:event_onSchließen

    private void onUpdate(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onUpdate
    {//GEN-HEADEREND:event_onUpdate
        try
        {
            Process process = Runtime.getRuntime().exec("git pull");
            process.waitFor();

            // TODO: ng build --prod | npm restart | ant jar 
            // process = Runtime.getRuntime().exec("ng build --prod");
            // process.waitFor();
            
            // process = Runtime.getRuntime().exec("npm restart");
            // process.waitFor();
            
            // process = Runtime.getRuntime().exec("ant jar");
            // process.waitFor();
            
            // oder Anfrage an Server um ein Update
            
            // Raspberry macht  folgendes beim neustarten: ng build --prod | npm restart | ant jar
            // dafür wird in einer Datei ein bestimmer text gepeichert
            
            write(String.format("/home/"+System.getProperty("user.name")+"/git/fuettr/build"));
            
            pTextUpdateErfolgreich.setVisible(true);
            
            Runtime.getRuntime().exec("sudo reboot");

        }
        catch (IOException ex)
        {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, "WaitFor ist interrupted");
        }        
    }//GEN-LAST:event_onUpdate

    private void onUeberpruefen(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onUeberpruefen
    {//GEN-HEADEREND:event_onUeberpruefen
        final URL globalUrl, localUrl;
        String globalVersionJson, localVersionJson, globalVersion = "1", localVersion = "0";

        try
        {
            globalUrl = new URL("https://raw.githubusercontent.com/Katzenfuetterungsanlage/fuettr_prototype/master/version.json");
            localUrl = new URL("http://localhost:17325/api/version");

            URLConnection globalCon = globalUrl.openConnection();
            URLConnection localCon = localUrl.openConnection();

            BufferedReader globalReader = new BufferedReader(new InputStreamReader(globalCon.getInputStream()));
            BufferedReader localReader = new BufferedReader(new InputStreamReader(localCon.getInputStream()));

            final StringBuilder sbVersionGlobal = new StringBuilder();
            final StringBuilder sbVersionLocal = new StringBuilder();
            String line;

            while ((line = globalReader.readLine()) != null)
            {
                sbVersionGlobal.append(line);
            }

            while ((line = localReader.readLine()) != null)
            {
                sbVersionLocal.append(line);
            }

            globalVersionJson = sbVersionGlobal.toString();
            localVersionJson = sbVersionLocal.toString();

            JsonReader jsonReader = Json.createReader(new StringReader(globalVersionJson));
            JsonObject globalVersionJsonObject = jsonReader.readObject();
            jsonReader.close();

            globalVersion = globalVersionJsonObject.getString("version");

            jsonReader = Json.createReader(new StringReader(localVersionJson));
            JsonObject localVersionJsonObject = jsonReader.readObject();
            jsonReader.close();

            localVersion = localVersionJsonObject.getString("version");
        }
        catch (MalformedURLException ex)
        {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (globalVersion.equals(localVersion))
        {
            updateVerfuegbar = false;
            updateGUIElements();
        }
        else
        {
            updateVerfuegbar = true;
            updateGUIElements();
        }

    }//GEN-LAST:event_onUeberpruefen

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
            for (javax.swing.UIManager.LookAndFeelInfo info
                    : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                Update dialog = new Update(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btSchließen;
    private javax.swing.JButton btUeberpruefen;
    private javax.swing.JButton btUpdate;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lbUpdateVerfuegbar;
    private javax.swing.JPanel pButton;
    private javax.swing.JPanel pStarten;
    private javax.swing.JPanel pTextUpdateErfolgreich;
    private javax.swing.JPanel pUeberpruefen;
    private javax.swing.JPanel pUpdate;
    private javax.swing.JPanel pUpdateErfolgreich;
    // End of variables declaration//GEN-END:variables

    private void updateGUIElements()
    {
        if (updateVerfuegbar)
        {
            lbUpdateVerfuegbar.setText("Update: Verfügbar");
            btUpdate.setEnabled(true);
        }
        else
        {
            lbUpdateVerfuegbar.setText("Kein Update verfügbar");
            btUpdate.setEnabled(false);
        }
    }
    
    private void write(String path)
    {
        try (final BufferedWriter writer = 
            new BufferedWriter(
            new OutputStreamWriter(  
            new FileOutputStream(path), "utf8"));) // "AutoCloseable"
        {
            writer.write(String.format("true"));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }  
    }

}
