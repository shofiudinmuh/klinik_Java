/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klinik;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author USER
 */
public class Home extends javax.swing.JFrame {
public Connection conn;
    public Statement cn;
    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        Date now;
        now = new Date();
        
        SimpleDateFormat tgl1, tgl2;
        tgl1 = new SimpleDateFormat("EEEE, dd MMM YYYY");
        tgl2 = new SimpleDateFormat ("HH:mm:ss");
        
        Tanggal.setText(tgl1.format(now));
        Waktu.setText(tgl2.format(now));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        kamar = new javax.swing.JButton();
        pasien = new javax.swing.JButton();
        dokter = new javax.swing.JButton();
        diagnosis = new javax.swing.JButton();
        obat = new javax.swing.JButton();
        kasir = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        user = new javax.swing.JButton();
        Tanggal = new javax.swing.JLabel();
        Waktu = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 550));
        jPanel1.setLayout(null);

        kamar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kamar.setText("Kamar");
        kamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kamarActionPerformed(evt);
            }
        });
        jPanel1.add(kamar);
        kamar.setBounds(50, 390, 200, 40);

        pasien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pasien.setText("Pasien");
        pasien.setPreferredSize(new java.awt.Dimension(75, 35));
        pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasienActionPerformed(evt);
            }
        });
        jPanel1.add(pasien);
        pasien.setBounds(50, 230, 200, 35);

        dokter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dokter.setText("Dokter");
        dokter.setPreferredSize(new java.awt.Dimension(75, 35));
        dokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dokterActionPerformed(evt);
            }
        });
        jPanel1.add(dokter);
        dokter.setBounds(50, 270, 200, 35);

        diagnosis.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        diagnosis.setText("Diagnosa");
        diagnosis.setPreferredSize(new java.awt.Dimension(75, 35));
        diagnosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagnosisActionPerformed(evt);
            }
        });
        jPanel1.add(diagnosis);
        diagnosis.setBounds(50, 310, 200, 35);

        obat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        obat.setText("Obat");
        obat.setPreferredSize(new java.awt.Dimension(75, 35));
        obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obatActionPerformed(evt);
            }
        });
        jPanel1.add(obat);
        obat.setBounds(50, 350, 200, 35);

        kasir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kasir.setText("Kasir");
        kasir.setPreferredSize(new java.awt.Dimension(75, 35));
        kasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kasirActionPerformed(evt);
            }
        });
        jPanel1.add(kasir);
        kasir.setBounds(50, 440, 200, 35);

        keluar.setBackground(new java.awt.Color(0, 51, 255));
        keluar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        keluar.setText("KELUAR");
        keluar.setBorderPainted(false);
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });
        jPanel1.add(keluar);
        keluar.setBounds(100, 570, 101, 36);

        user.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        user.setText("User");
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jPanel1.add(user);
        user.setBounds(50, 190, 200, 35);

        Tanggal.setText("jLabel2");
        jPanel1.add(Tanggal);
        Tanggal.setBounds(46, 152, 200, 20);

        Waktu.setText("jLabel2");
        jPanel1.add(Waktu);
        Waktu.setBounds(46, 123, 190, 20);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Home1.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 50, 1160, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        User frmU = null;
        try {
            frmU = new User();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmU.setVisible(true);
    }//GEN-LAST:event_userActionPerformed

    private void kasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kasirActionPerformed
        Kasir frmK = null;
    try {
        frmK = new Kasir();
    } catch (SQLException ex) {
        Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
    }
        frmK.setVisible(true);
    }//GEN-LAST:event_kasirActionPerformed

    private void obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obatActionPerformed
        Obat frmO = null;
        try {
            frmO = new Obat();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmO.setVisible(true);
    }//GEN-LAST:event_obatActionPerformed

    private void diagnosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diagnosisActionPerformed
        Diagnosa frmDi = null;
        try {
            frmDi = new Diagnosa();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmDi.setVisible(true);
    }//GEN-LAST:event_diagnosisActionPerformed

    private void dokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dokterActionPerformed
        Dokter frmD = null;
        try {
            frmD = new Dokter();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmD.setVisible(true);
    }//GEN-LAST:event_dokterActionPerformed

    private void pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasienActionPerformed
        Pasien frmP = null;
        try {
            frmP = new Pasien();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmP.setVisible(true);
    }//GEN-LAST:event_pasienActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        int dialogBtn =JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Anda Yakin Akan Keluar?", "Peringatan", dialogBtn);
        if (dialogResult == 0){
            System.exit(0);
        }else{
        }
    }//GEN-LAST:event_keluarActionPerformed

    private void kamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kamarActionPerformed
        Kamar frmK = null;
        try {
            frmK = new Kamar();
        } catch (SQLException ex) {
            Logger.getLogger(Kamar.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmK.setVisible(true);
    }//GEN-LAST:event_kamarActionPerformed
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Tanggal;
    private javax.swing.JLabel Waktu;
    private javax.swing.JButton diagnosis;
    private javax.swing.JButton dokter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton kamar;
    private javax.swing.JButton kasir;
    private javax.swing.JButton keluar;
    private javax.swing.JButton obat;
    private javax.swing.JButton pasien;
    private javax.swing.JButton user;
    // End of variables declaration//GEN-END:variables
}