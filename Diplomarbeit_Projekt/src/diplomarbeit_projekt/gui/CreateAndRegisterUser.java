/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.gui;

import com.mongodb.*;
import com.mongodb.util.JSON;
import static diplomarbeit_projekt.utils.HashPassword.hash;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import java.io.StringReader;
import static java.lang.String.valueOf;
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
public class CreateAndRegisterUser extends javax.swing.JDialog
{
    private boolean saved = true; 
    private BasicDBObject newUserDoc;
        
    /**
     * Creates new form BenutzerAnlegen
     */
    public CreateAndRegisterUser(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
               
        initComponents();
        
        setValue();
        
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
        pBenutzer = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfUserName = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        pwtfUserPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        pwtfUserPasswordConfirm = new javax.swing.JPasswordField();
        pButton = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btSpeichern = new javax.swing.JButton();
        btSchließen1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Benutzer anlegen");

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jPanel1.setLayout(new java.awt.BorderLayout());

        pBenutzer.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pBenutzer.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Der Benutzer");
        jPanel5.add(jLabel2);

        lbUser.setText("<Benutzer>");
        jPanel5.add(lbUser);

        jLabel6.setText("ist angelegt.");
        jPanel5.add(jLabel6);

        jPanel4.add(jPanel5, new java.awt.GridBagConstraints());

        jLabel4.setText("Wenn ein neuer Benutzer angelegt wird, wird der Andere gelöscht.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 4, 2);
        jPanel4.add(jLabel4, gridBagConstraints);

        pBenutzer.add(jPanel4, java.awt.BorderLayout.SOUTH);

        jPanel2.setLayout(new java.awt.GridLayout(0, 2, 0, 4));

        jLabel1.setText("Benutzername:");
        jPanel2.add(jLabel1);

        tfUserName.setColumns(16);
        tfUserName.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                tfUserNameKeyPressed(evt);
            }
        });
        jPanel2.add(tfUserName);

        jLabel3.setText("Passwort:");
        jPanel2.add(jLabel3);

        pwtfUserPassword.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                pwtfUserPasswordKeyPressed(evt);
            }
        });
        jPanel2.add(pwtfUserPassword);

        jLabel5.setText("Passwort wiederholen:");
        jPanel2.add(jLabel5);

        pwtfUserPasswordConfirm.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                pwtfUserPasswordConfirmKeyPressed(evt);
            }
        });
        jPanel2.add(pwtfUserPasswordConfirm);

        jPanel3.add(jPanel2);

        pBenutzer.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel1.add(pBenutzer, java.awt.BorderLayout.CENTER);

        pButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 0, 0));
        pButton.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel7.setLayout(new java.awt.GridLayout(1, 0, 4, 0));

        btSpeichern.setText("Ok");
        btSpeichern.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onOk(evt);
            }
        });
        jPanel7.add(btSpeichern);

        btSchließen1.setText("Abbrechen");
        btSchließen1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                onCancel(evt);
            }
        });
        jPanel7.add(btSchließen1);

        jPanel6.add(jPanel7);

        pButton.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel1.add(pButton, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onCancel(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onCancel
    {//GEN-HEADEREND:event_onCancel
        if (saved == false)
       {
           if (JOptionPane.showConfirmDialog(this, "Fenster wirklich schließen? Nicht gespeicherte Inhalte gehen verloren!",
                 "Hinweis", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
           {
               dispose();
           }
       }
       else
       {          
           dispose();
       }
    }//GEN-LAST:event_onCancel

    private void onOk(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onOk
    {//GEN-HEADEREND:event_onOk
        getValue();
        
        dispose();    
    }//GEN-LAST:event_onOk

    private void tfUserNameKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_tfUserNameKeyPressed
    {//GEN-HEADEREND:event_tfUserNameKeyPressed
        saved = false;
    }//GEN-LAST:event_tfUserNameKeyPressed

    private void pwtfUserPasswordKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_pwtfUserPasswordKeyPressed
    {//GEN-HEADEREND:event_pwtfUserPasswordKeyPressed
        saved = false;
    }//GEN-LAST:event_pwtfUserPasswordKeyPressed

    private void pwtfUserPasswordConfirmKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_pwtfUserPasswordConfirmKeyPressed
    {//GEN-HEADEREND:event_pwtfUserPasswordConfirmKeyPressed
        saved = false;
    }//GEN-LAST:event_pwtfUserPasswordConfirmKeyPressed

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
            java.util.logging.Logger.getLogger(CreateAndRegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(CreateAndRegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(CreateAndRegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(CreateAndRegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                CreateAndRegisterUser dialog = new CreateAndRegisterUser(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lbUser;
    private javax.swing.JPanel pBenutzer;
    private javax.swing.JPanel pButton;
    private javax.swing.JPasswordField pwtfUserPassword;
    private javax.swing.JPasswordField pwtfUserPasswordConfirm;
    private javax.swing.JFormattedTextField tfUserName;
    // End of variables declaration//GEN-END:variables
 
    private void setValue ()
    {
        DBObject userDoc = MainWindow.getInstance().getUserDoc();
               
        String strUser = JSON.serialize(userDoc);
        
        Logger.getLogger("User imported").log(Level.FINE, "User imported");
        
        JsonReader jsonReader = Json.createReader(new StringReader(strUser));
        JsonObject obj = jsonReader.readObject();
        jsonReader.close();

        String user_name = obj.getString("user_name");
        lbUser.setText(user_name);
    }
    
    private void getValue ()
    {
        String user_name = tfUserName.getText();
        char[] user_password = pwtfUserPassword.getPassword();
        char[] user_passwordConfirm = pwtfUserPasswordConfirm.getPassword();

        String strUser_password = valueOf(user_password);
        String strUser_passwordConfirm = valueOf(user_passwordConfirm);
        
        if ("".equals(strUser_passwordConfirm) || "".equals(user_name) || "".equals(strUser_password))
        {
            JOptionPane.showMessageDialog(this, "Benutzername und Passwort dürfen nicht leer sein!", "Fehler",ERROR_MESSAGE);
        }
        else
        {
            if (!strUser_password.equals(strUser_passwordConfirm))
            {
                JOptionPane.showMessageDialog(this, "Die Passwörter stimmen nicht überein!", "Fehler",ERROR_MESSAGE);
                pwtfUserPassword.setText("");
                pwtfUserPasswordConfirm.setText("");
            }
            else
            {
                String hashedPassword = hash(strUser_password, "");
                
                newUserDoc = new BasicDBObject("identifier", "User").append("user_name", user_name).append("user_password", hashedPassword);
                
                Logger.getLogger("User saved").log(Level.FINE, "User saved");  
      
                lbUser.setText(user_name);
                
                JOptionPane.showMessageDialog(this, String.format("Der Benutzer %s wurde erfolgreich angelegt!",user_name), "Hinweis",INFORMATION_MESSAGE);
            
                saved = true; 
            
                tfUserName.setText(""); 
                pwtfUserPassword.setText("");
                pwtfUserPasswordConfirm.setText("");
            }
        }
    }

    public boolean isSaved()
    {
        return saved;
    }

    public BasicDBObject getNewUserDoc()
    {
        return newUserDoc;
    }

    

}
