/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.gui;

import com.mongodb.*;
import com.mongodb.util.JSON;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import diplomarbeit_projekt.methods.NextFeeding;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonReader;
import java.util.concurrent.CountDownLatch;
import diplomarbeit_projekt.pi4j.pi4j_Singleton;
import java.net.UnknownHostException;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author Florian
 */
public class MainWindow extends javax.swing.JFrame
{

    boolean machineState = false;
    boolean timesChanged = true;
    String timeOfDay, date, time1, time2, time3, time4;
    String time1_active_str, time2_active_str, time3_active_str, time4_active_str;
    Boolean time1_active, time2_active, time3_active, time4_active;
    int lastFeeding = 0;
    String nextFeedingAt, nextFeedingIn, lastFeedingTime;
    JsonObject times;

    // pi4j
    pi4j_Singleton pi4j_instance;

    // create object
    MongoClient mongodb;
    DB database;
    DBCollection collTimes;

    //Workers
    TimeOfDayAndDateWorker timeAndDateWorker;
    FeedingWorker feedingWorker;
    ImportAndShowTimesWorker timesWorker;

    CountDownLatch latch = new CountDownLatch(1);

    /**
     * Creates new form Hauptfenster
     */
    public MainWindow()
    {
        //GraphicsDevice d = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//        if (d.isFullScreenSupported())
//        {
//            this.setUndecorated(true);
//            this.setResizable(false);
//            d.setFullScreenWindow(this);
//        } else
//        {
        this.setSize(800, 480);
        this.setVisible(true);
//        }
        initComponents();

        if (machineState == false)
        {
            lbState.setText("Aus");
        }

        // pi4j instance
        if (!"Windows 10".equals(System.getProperty("os.name")))
        {
            pi4j_instance = pi4j_Singleton.getInstance();
        }

        // connect to Database
        try
        {
            mongodb = new MongoClient();
        }
        catch (UnknownHostException ex)
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        database = mongodb.getDB("katzenfuetterungsanlage");
        collTimes = database.getCollection("data_times");
        //======================================================================

        // Worker 
        timeAndDateWorker = new TimeOfDayAndDateWorker();
        timeAndDateWorker.execute();
        Logger.getLogger("TimeOfDayAndDateWorker started").log(Level.FINE, "TimeOfDayAndDateWorker started");

        if (!"Windows 10".equals(System.getProperty("os.name")))
        {
            feedingWorker = new FeedingWorker();
            feedingWorker.execute();
            Logger.getLogger("FeedingWorker started").log(Level.FINE, "FeedingWorker started");
        }

        timesWorker = new ImportAndShowTimesWorker();
        timesWorker.execute();
        Logger.getLogger("ImportTimeWorker started").log(Level.FINE, "ImportTimeWorker started");

        lbLastFeeding.setText("ausstehend");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel15 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pEast = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbTime1Description = new javax.swing.JLabel();
        lbTime2Description = new javax.swing.JLabel();
        lbTime3Description = new javax.swing.JLabel();
        lbTime4Description = new javax.swing.JLabel();
        lbTime1 = new javax.swing.JLabel();
        lbTime2 = new javax.swing.JLabel();
        lbTime3 = new javax.swing.JLabel();
        lbTime4 = new javax.swing.JLabel();
        pCenter = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        CenterSouth = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        CenterNorth = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lbLastFeeding = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lbNextFeedingAt = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        lbNextFeedingIn = new javax.swing.JLabel();
        pSouth = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbState = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbTimeOfDay = new javax.swing.JLabel();
        lbDate = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        raspberry = new javax.swing.JMenu();
        neustarten = new javax.swing.JMenuItem();
        herunterfahren = new javax.swing.JMenuItem();
        fuetterung = new javax.swing.JMenu();
        ein_aus = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        fuetterungszeiten_verwalten = new javax.swing.JMenuItem();
        steuerung = new javax.swing.JMenu();
        manuelleSteuerung = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        positionsinformationen = new javax.swing.JMenuItem();
        einstellungen = new javax.swing.JMenu();
        update = new javax.swing.JMenuItem();
        benutzer_anlegen = new javax.swing.JMenuItem();
        wlan = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        geraeteinformation = new javax.swing.JMenuItem();

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Letzte erfolgte Fütterung: ");
        jPanel15.add(jLabel10);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("<Uhrzeit>");
        jPanel15.add(jLabel11);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Nächste Fütterung erfolgt in: ");
        jPanel16.add(jLabel12);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("<Stunden:Minuten>");
        jPanel16.add(jLabel13);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Katzenfütterungsanlage");
        getContentPane().setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jPanel1.setLayout(new java.awt.BorderLayout());

        pEast.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 4, 0, 0));
        pEast.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(0));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Fütterungszeiten");
        jPanel8.add(jLabel5, java.awt.BorderLayout.PAGE_START);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        lbTime1Description.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTime1Description.setText("Zeit 1:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel9.add(lbTime1Description, gridBagConstraints);

        lbTime2Description.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTime2Description.setText("Zeit 2:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel9.add(lbTime2Description, gridBagConstraints);

        lbTime3Description.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTime3Description.setText("Zeit 3:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel9.add(lbTime3Description, gridBagConstraints);

        lbTime4Description.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTime4Description.setText("Zeit 4:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel9.add(lbTime4Description, gridBagConstraints);

        lbTime1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTime1.setText("<Zeit>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel9.add(lbTime1, gridBagConstraints);

        lbTime2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTime2.setText("<Zeit>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel9.add(lbTime2, gridBagConstraints);

        lbTime3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTime3.setText("<Zeit>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel9.add(lbTime3, gridBagConstraints);

        lbTime4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTime4.setText("<Zeit>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel9.add(lbTime4, gridBagConstraints);

        jPanel8.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel7, java.awt.BorderLayout.CENTER);

        pEast.add(jPanel6, java.awt.BorderLayout.LINE_END);

        jPanel1.add(pEast, java.awt.BorderLayout.EAST);

        pCenter.setLayout(new java.awt.BorderLayout());

        jPanel10.setLayout(new java.awt.BorderLayout());

        CenterSouth.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 0, 0));
        CenterSouth.setLayout(new java.awt.BorderLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(0));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel14.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Fehler und Warnungen");
        jPanel14.add(jLabel14, java.awt.BorderLayout.NORTH);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Fehler 1", "Fehler 2", "Warnung 1", "Warnung 2" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jPanel14.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel14, java.awt.BorderLayout.CENTER);

        CenterSouth.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel10.add(CenterSouth, java.awt.BorderLayout.SOUTH);

        CenterNorth.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 4, 1));
        CenterNorth.setLayout(new java.awt.BorderLayout());

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder(0));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel13.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 2, 0));
        jPanel13.setLayout(new java.awt.GridLayout(0, 1, 0, 8));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Letzte erfolgte Fütterung: ");
        jPanel17.add(jLabel15);

        lbLastFeeding.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbLastFeeding.setText("<Uhrzeit>");
        jPanel17.add(lbLastFeeding);

        jPanel13.add(jPanel17);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Nächste Fütterung erfolgt um: ");
        jPanel18.add(jLabel17);

        lbNextFeedingAt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNextFeedingAt.setText("<Uhrzeit>");
        jPanel18.add(lbNextFeedingAt);

        jPanel13.add(jPanel18);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Nächste Fütterung erfolgt in: ");
        jPanel19.add(jLabel18);

        lbNextFeedingIn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNextFeedingIn.setText("<Stunden:Minuten>");
        jPanel19.add(lbNextFeedingIn);

        jPanel13.add(jPanel19);

        jPanel12.add(jPanel13, java.awt.BorderLayout.CENTER);

        CenterNorth.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel10.add(CenterNorth, java.awt.BorderLayout.CENTER);

        pCenter.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel1.add(pCenter, java.awt.BorderLayout.CENTER);

        pSouth.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 0, 0));
        pSouth.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(0));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Maschine: ");
        jPanel5.add(jLabel1);

        lbState.setText("Ein/Aus");
        jPanel5.add(lbState);

        jPanel3.add(jPanel5, java.awt.BorderLayout.WEST);

        jPanel4.setLayout(new java.awt.FlowLayout(1, 10, 5));

        lbTimeOfDay.setText("Uhrzeit");
        jPanel4.add(lbTimeOfDay);

        lbDate.setText("Datum");
        jPanel4.add(lbDate);

        jPanel3.add(jPanel4, java.awt.BorderLayout.EAST);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        pSouth.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.add(pSouth, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        raspberry.setText("Raspberry");

        neustarten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diplomarbeit_projekt/icons/restartIcon16x16.png"))); // NOI18N
        neustarten.setText("Neustarten");
        neustarten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onNeustarten(evt);
            }
        });
        raspberry.add(neustarten);

        herunterfahren.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diplomarbeit_projekt/icons/powerIcon16x16.png"))); // NOI18N
        herunterfahren.setText("Herunterfahren");
        herunterfahren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onHerunterfahren(evt);
            }
        });
        raspberry.add(herunterfahren);

        jMenuBar1.add(raspberry);

        fuetterung.setText("Fütterung");
        fuetterung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onFütterungszeitenVerwalten(evt);
            }
        });

        ein_aus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diplomarbeit_projekt/icons/switchIcon16x16.png"))); // NOI18N
        ein_aus.setText("Ein-/Ausschalten");
        ein_aus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onEinAusSchalten(evt);
            }
        });
        fuetterung.add(ein_aus);
        fuetterung.add(jSeparator1);

        fuetterungszeiten_verwalten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diplomarbeit_projekt/icons/timeIcon16x16.png"))); // NOI18N
        fuetterungszeiten_verwalten.setText("Fütterungszeiten verwalten");
        fuetterungszeiten_verwalten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onFuetterungszeitenVerwalten(evt);
            }
        });
        fuetterung.add(fuetterungszeiten_verwalten);

        jMenuBar1.add(fuetterung);

        steuerung.setText("Steuerung");

        manuelleSteuerung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diplomarbeit_projekt/icons/controlpanelIcon16x16.png"))); // NOI18N
        manuelleSteuerung.setText("manuelle Steuerung");
        manuelleSteuerung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onManuelleSteuerung(evt);
            }
        });
        steuerung.add(manuelleSteuerung);
        steuerung.add(jSeparator3);

        positionsinformationen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diplomarbeit_projekt/icons/infoIcon16x16.png"))); // NOI18N
        positionsinformationen.setText("Positionsinformationen");
        positionsinformationen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onPositionsinformation(evt);
            }
        });
        steuerung.add(positionsinformationen);

        jMenuBar1.add(steuerung);

        einstellungen.setText("Einstellungen");

        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diplomarbeit_projekt/icons/downloadIcon16x16.png"))); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onUpdate(evt);
            }
        });
        einstellungen.add(update);

        benutzer_anlegen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diplomarbeit_projekt/icons/userIcon16x16.png"))); // NOI18N
        benutzer_anlegen.setText("Benutzer anlegen");
        benutzer_anlegen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onBenutzerAnlegen(evt);
            }
        });
        einstellungen.add(benutzer_anlegen);

        wlan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diplomarbeit_projekt/icons/wifi.png"))); // NOI18N
        wlan.setText("WLAN");
        wlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onWlan(evt);
            }
        });
        einstellungen.add(wlan);
        einstellungen.add(jSeparator2);

        geraeteinformation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diplomarbeit_projekt/icons/info_aboutIcon16x16.png"))); // NOI18N
        geraeteinformation.setText("Geräteinformation");
        geraeteinformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onGeraeteinformation(evt);
            }
        });
        einstellungen.add(geraeteinformation);

        jMenuBar1.add(einstellungen);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onEinAusSchalten(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onEinAusSchalten
    {//GEN-HEADEREND:event_onEinAusSchalten
        if (machineState != true)
        {
            machineState = true;
            lbState.setText("Ein");
        }
        else
        {
            machineState = false;
            lbState.setText("Aus");
        }

        // write machineState to mongodb

    }//GEN-LAST:event_onEinAusSchalten

    private void onFütterungszeitenVerwalten(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onFütterungszeitenVerwalten
    {//GEN-HEADEREND:event_onFütterungszeitenVerwalten
        //Delete
    }//GEN-LAST:event_onFütterungszeitenVerwalten

    private void onManuelleSteuerung(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onManuelleSteuerung
    {//GEN-HEADEREND:event_onManuelleSteuerung
        if (machineState == true)
        {
            if (JOptionPane.showConfirmDialog(this, "Um fortzufahren müssen Sie die automatische Fütterung deaktivieren. \n Wollen sie diese deaktivieren? ",
                    "Hinweis", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                machineState = false;
                lbState.setText("Aus");

                final ManuelleSteuerung strDlg = new ManuelleSteuerung(this, true);
                strDlg.setVisible(true);
            }
        }
        else
        {
            final ManuelleSteuerung strDlg = new ManuelleSteuerung(this, true);
            strDlg.setVisible(true);
        }
    }//GEN-LAST:event_onManuelleSteuerung

    private void onPositionsinformation(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onPositionsinformation
    {//GEN-HEADEREND:event_onPositionsinformation
        final Positionsinformation posDlg = new Positionsinformation(this, true);
        posDlg.setVisible(true);
    }//GEN-LAST:event_onPositionsinformation

    private void onUpdate(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onUpdate
    {//GEN-HEADEREND:event_onUpdate
        final Update infoDlg = new Update(this, true);
        infoDlg.setVisible(true);
    }//GEN-LAST:event_onUpdate

    private void onFuetterungszeitenVerwalten(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onFuetterungszeitenVerwalten
    {//GEN-HEADEREND:event_onFuetterungszeitenVerwalten
        //Objektobjekt erzeugen ==> Dialog ist MODAL! (modal ... blockieren des Elternfensters) 
        final TimeManagement zeitenDlg = new TimeManagement(this, true); // true = modal (blockiert das Hauptfenster) , false = nicht modal 
        zeitenDlg.setVisible(true); //Dialog sichtbar setzen
        //An dieser Stelle "blockiert" das Programm, solange der Dialog geöffnet ist!   

//        if (zeitenDlg.zeitenVeraendert())
//        {
//            timesChanged = true;
//            ZeitenAnzeigenWorker zaWorker = new ZeitenAnzeigenWorker();
//            zaWorker.execute();
//        }
    }//GEN-LAST:event_onFuetterungszeitenVerwalten

    private void onGeraeteinformation(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onGeraeteinformation
    {//GEN-HEADEREND:event_onGeraeteinformation
        final SystemInfo infoDlg = new SystemInfo(this, true);
        infoDlg.setVisible(true);
    }//GEN-LAST:event_onGeraeteinformation

    private void onBenutzerAnlegen(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onBenutzerAnlegen
    {//GEN-HEADEREND:event_onBenutzerAnlegen
        final CreateUser infoDlg = new CreateUser(this, true);
        infoDlg.setVisible(true);
    }//GEN-LAST:event_onBenutzerAnlegen

    private void onNeustarten(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onNeustarten
    {//GEN-HEADEREND:event_onNeustarten
        try
            {
                timeAndDateWorker.cancel(true);
                feedingWorker.cancel(true);
                timesWorker.cancel(true); 
            }
            catch (Exception ex)
            {
                Logger.getLogger("TimeUnit Error").log(Level.INFO, "TimeUnit Error");
            }

        JOptionPane.showMessageDialog(this, "worker shut down because restart is not implemented", "Fehler",ERROR_MESSAGE);
        
        // TODO

    }//GEN-LAST:event_onNeustarten

    private void onHerunterfahren(java.awt.event.ActionEvent evt)//GEN-FIRST:event_onHerunterfahren
    {//GEN-HEADEREND:event_onHerunterfahren
        if (JOptionPane.showConfirmDialog(this, "Raspberry wirklick herunterfahren?",
                "Hinweis", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            try
            {
                timeAndDateWorker.cancel(true);
                feedingWorker.cancel(true);
                timesWorker.cancel(true); 
            }
            catch (Exception ex)
            {
                Logger.getLogger("TimeUnit Error").log(Level.INFO, "TimeUnit Error");
            }
            
            System.exit(0);
        }
    }//GEN-LAST:event_onHerunterfahren

    private void onWlan(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onWlan
        final ConnectToWlan wlan = new ConnectToWlan(this, true);
        wlan.setVisible(true);
    }//GEN-LAST:event_onWlan

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                MainWindow frame = new MainWindow();

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CenterNorth;
    private javax.swing.JPanel CenterSouth;
    private javax.swing.JMenuItem benutzer_anlegen;
    private javax.swing.JMenuItem ein_aus;
    private javax.swing.JMenu einstellungen;
    private javax.swing.JMenu fuetterung;
    private javax.swing.JMenuItem fuetterungszeiten_verwalten;
    private javax.swing.JMenuItem geraeteinformation;
    private javax.swing.JMenuItem herunterfahren;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbLastFeeding;
    private javax.swing.JLabel lbNextFeedingAt;
    private javax.swing.JLabel lbNextFeedingIn;
    private javax.swing.JLabel lbState;
    private javax.swing.JLabel lbTime1;
    private javax.swing.JLabel lbTime1Description;
    private javax.swing.JLabel lbTime2;
    private javax.swing.JLabel lbTime2Description;
    private javax.swing.JLabel lbTime3;
    private javax.swing.JLabel lbTime3Description;
    private javax.swing.JLabel lbTime4;
    private javax.swing.JLabel lbTime4Description;
    private javax.swing.JLabel lbTimeOfDay;
    private javax.swing.JMenuItem manuelleSteuerung;
    private javax.swing.JMenuItem neustarten;
    private javax.swing.JPanel pCenter;
    private javax.swing.JPanel pEast;
    private javax.swing.JPanel pSouth;
    private javax.swing.JMenuItem positionsinformationen;
    private javax.swing.JMenu raspberry;
    private javax.swing.JMenu steuerung;
    private javax.swing.JMenuItem update;
    private javax.swing.JMenuItem wlan;
    // End of variables declaration//GEN-END:variables

    // gets the current time and date and displays it in the gui MainWindow
    private class TimeOfDayAndDateWorker extends SwingWorker<Object, String>
    {

        @Override
        protected Object doInBackground() throws Exception
        {
            while (!isCancelled())
            {
                timeOfDay = String.format("%1$tH:%1$tM", new Date(System.currentTimeMillis()));
                date = String.format("%1$td.%1$tm.%1$tY", new Date(System.currentTimeMillis()));

                publish();

                TimeUnit.MILLISECONDS.sleep(500); 
            }
            return 1;
        }

        @Override
        protected void process(List<String> chunks)
        {
            lbTimeOfDay.setText(timeOfDay);
            lbDate.setText(date);
        }
    }

    // calculates the nextFeedingAt and NextFeedingIn & executes feedingCycle and updates gui
    private class FeedingWorker extends SwingWorker<Object, String>
    {
        String string1, strLog;

        @Override
        protected Object doInBackground() throws Exception
        {
            while (!isCancelled())
            {

                if (machineState == true)
                {
                    // next feeding
                    NextFeeding nextFeeding = new NextFeeding();
                    string1 = nextFeeding.next(times);

                    // feedingcycle
                    if (time1.equals(timeOfDay))
                    {
                        pi4j_instance.feed();
                        lastFeeding = 1;
                        lastFeedingTime = time1;
                        publish();
                    }
                    else
                    {
                        if (time2.equals(timeOfDay))
                        {
                            pi4j_instance.feed();
                            lastFeeding = 2;
                            lastFeedingTime = time2;
                            publish();
                        }
                        else
                        {
                            if (time3.equals(timeOfDay))
                            {
                                pi4j_instance.feed();
                                lastFeeding = 3;
                                lastFeedingTime = time3;
                                publish();
                            }
                            else
                            {
                                if (time4.equals(timeOfDay))
                                {
                                    pi4j_instance.feed();
                                    lastFeeding = 4;
                                    lastFeedingTime = time4;
                                    publish();
                                }
                            }
                        }
                    }
                }
                else
                {
                    string1 = "-;-";
                }

                publish();
                
                TimeUnit.MILLISECONDS.sleep(500); 
            }
            return 1;
        }

        @Override
        protected void process(List<String> chunks)
        {
            // next feeding
            String[] token = string1.split(";");
            nextFeedingAt = token[0];
            nextFeedingIn = token[1];

            strLog = "nextFeedingAt: " + nextFeedingAt + " || " + "NextFeedingIn: " + nextFeedingIn;
            Logger.getLogger(strLog).log(Level.FINE, strLog);

            lbNextFeedingAt.setText(nextFeedingAt);
            lbNextFeedingIn.setText(nextFeedingIn);

            // feedingcycle
            lbLastFeeding.setText(lastFeedingTime);
        }

    }

    // Import times from mongodb and show them on gui
    private class ImportAndShowTimesWorker extends SwingWorker<Object, String>
    {

        String strTimes;
        String str;

        @Override
        protected Object doInBackground() throws Exception
        {
            String strCnt = "cnt: " + collTimes.count();
            Logger.getLogger(strCnt).log(Level.FINE, strCnt);
            if (collTimes.count() < 4)
            {
                // collection drop
                // Collection mit Standard-Werten erstellen
            }

            while (!isCancelled())
            {
                // import times
                DBObject doc = collTimes.find(new BasicDBObject("identifier", "Times")).next();

                strTimes = JSON.serialize(doc);

                // show times
                if (!"".equals(time1) || !"".equals(time2) || !"".equals(time3) || !"".equals(time4))
                {
                    str = "true";
                }
                else
                {
                    str = "false";
                }

                TimeUnit.MILLISECONDS.sleep(250); 

                publish();
            }
            return 1;
        }

        @Override
        protected void process(List<String> chunks)
        {
            // import times
            JsonReader jsonReader = Json.createReader(new StringReader(strTimes));
            JsonObject obj = jsonReader.readObject();
            jsonReader.close();

            times = obj;

            String str2 = "importet doc: " + strTimes;
            Logger.getLogger(str2).log(Level.FINEST, str2);

            time1 = obj.getString("time1");
            time2 = obj.getString("time2");
            time3 = obj.getString("time3");
            time4 = obj.getString("time4");

            time1_active = obj.getBoolean("time1_active");
            time2_active = obj.getBoolean("time2_active");
            time3_active = obj.getBoolean("time3_active");
            time4_active = obj.getBoolean("time4_active");

            // show times
            // show times if ("true".equals(str))
            if ("true".equals(str))
            {
                if (time1_active != true)
                {
                    lbTime1.setVisible(false);
                    lbTime1Description.setVisible(false);
                }
                else
                {
                    lbTime1.setVisible(true);
                    lbTime1Description.setVisible(true);
                    lbTime1.setText(time1);
                }

                if (time2_active != true)
                {
                    lbTime2.setVisible(false);
                    lbTime2Description.setVisible(false);
                }
                else
                {
                    lbTime2.setVisible(true);
                    lbTime2Description.setVisible(true);
                    lbTime2.setText(time2);
                }

                if (time3_active != true)
                {
                    lbTime3.setVisible(false);
                    lbTime3Description.setVisible(false);
                }
                else
                {
                    lbTime3.setVisible(true);
                    lbTime3Description.setVisible(true);
                    lbTime3.setText(time3);
                }

                if (time4_active != true)
                {
                    lbTime4.setVisible(false);
                    lbTime4Description.setVisible(false);
                }
                else
                {
                    lbTime4.setVisible(true);
                    lbTime4Description.setVisible(true);
                    lbTime4.setText(time4);
                }
            }
            else
            {
                // Error: One of the times is empty
                Logger.getLogger("Error: One of the times is empty").log(Level.SEVERE, "Error: One of the times is empty");
            }

        }

    }
}
