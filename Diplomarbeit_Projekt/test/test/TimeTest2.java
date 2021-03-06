/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingWorker;

/**
 *
 * @author Florian
 */
public class TimeTest2 extends javax.swing.JFrame
{
    String date, timeOfDay;
    
    /**
     * Creates new form TimeTest2
     */
    public TimeTest2()
    {
        initComponents();
        
        jPanel1.remove(jLabel1);
        
        TimeOfDayAndDateWorker worker = new TimeOfDayAndDateWorker();
        worker.execute();
        
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
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout());

        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3);

        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);

        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(TimeTest2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(TimeTest2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(TimeTest2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(TimeTest2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new TimeTest2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

private class TimeOfDayAndDateWorker extends SwingWorker<Object, String[]>
    {

        String[] timeAndDate = new String[3];

        @Override
        protected Object doInBackground() throws Exception
        {
            try
            {
                while (!isCancelled())
                {
                    System.out.println("woker doinbackground");

                    timeAndDate[0] = String.format("%1$tH:%1$tM", new Date(System.currentTimeMillis()));
                    timeAndDate[1] = String.format("%1$td.%1$tm.%1$tY", new Date(System.currentTimeMillis()));

                    publish(timeAndDate);
                    System.out.println("publish aufgerufen");

                    TimeUnit.MILLISECONDS.sleep(500);
                }

            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            return 1;
        }

        @Override
        protected void process(List<String[]> chunks)
        {
            try
            {
                System.out.println("process");

                String chunk[] = chunks.get((chunks.size() - 1));

                timeOfDay = chunk[0];
                date = chunk[1];

                jLabel1.setText(timeOfDay);
                jLabel2.setText(chunk[1]);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

        }
    }
    
}
