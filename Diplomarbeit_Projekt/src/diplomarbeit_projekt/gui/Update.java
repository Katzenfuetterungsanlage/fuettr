/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.gui;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;



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
            
    }//GEN-LAST:event_onUpdate

    private void onUeberpruefen(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onUeberpruefen
    {//GEN-HEADEREND:event_onUeberpruefen
        UpdateWorker updateWorker = new UpdateWorker();
        updateWorker.execute();
        
        
        if (updateVerfuegbar == true)
        {
            lbUpdateVerfuegbar.setText("Update: Verfügbar");
            btUpdate.setEnabled(true);
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
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
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

public class UpdateWorker extends SwingWorker
{   
    @Override
    protected Object doInBackground() 
            throws Exception
    {
        Socket socket = null; 
        
        try
        {
            URL url = new URL("https://raw.githubusercontent.com/Katzenfuetterungsanlage/fuettr_prototype/master/version.json");

            URLConnection con = url.openConnection();
            //InputStream is = con.getInputStream();
            
            BufferedReader bReader = new BufferedReader(new InputStreamReader(con.getInputStream())); 
            
            System.out.println(bReader.readLine()); //json Datei einlesen 
            
//            Gson g = new Gson();
//
//            Person person = g.fromJson("{\"name\": \"John\"}", Person.class);
//            System.out.println(person.name); //John
//
//            System.out.println(g.toJson(person)); // {"name":"John"}
        }
        catch (Exception ex)
        {
            Logger.getLogger(UpdateWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //TODO: lokale Versionsdatei einlesen und mit der online Version vergleichen
        
        return null; 
    }

    @Override
    protected void done()
    {
        updateVerfuegbar = true;
    }
    
    
}

}
