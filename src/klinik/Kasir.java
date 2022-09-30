/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klinik;

import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author USER
 */
public class Kasir extends javax.swing.JFrame {

    String kdobat, nmobat, merek, hj, trf, tgl;
    int prwtn = 0;
    int tunai = 0;
    int kembali = 0;
    private Connection conn = new koneksi().getkoneksi();
    private DefaultTableModel tabmode;

    /**
     * Creates new form Kasir
     */
    public Kasir() throws SQLException {

        initComponents();
        aktif();
        buatnomor();
        datatable();
        tampil_comPasien();
        tampil_comKamar();
        Date now;
        now = new Date();
        waktu();

        SimpleDateFormat tgl1, tgl2;
        tgl1 = new SimpleDateFormat("EEEE, dd MMM yyyy");
        tgl2 = new SimpleDateFormat("HH:mm:ss");

        Tanggal.setText(tgl1.format(now));
        Waktu.setText(tgl2.format(now));

    }

    protected void kosong() {
        txtkode.setText("");
        comboPasien.setSelectedItem("null");
        txtnamaobat.setText("");
        txtmerek.setText("");
        txtjumlah.setText("");
        txttotal.setText("");
        txtbiaya.setText("");
        txtbayar.setText("");
        txtkembali.setText("");
        comboKamar.setSelectedItem("null");
        txttarif.setText("");
        txthari.setText("");
        txtobat.setText("");
        txtharga.setText("");
        totalkamar.setText("");

    }

    public void tampil_comPasien() {
        try {
            String sql = "select*from Pasien ";
            java.sql.Connection conn = (Connection) koneksi.getkoneksi();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                comboPasien.addItem(rs.getString("nama_pasien"));

            }
            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();

        } catch (SQLException e) {

        }
    }

    public void tampil_comKamar() {
        try {
            String sql = "select*from Kamar ";
            java.sql.Connection conn = (Connection) koneksi.getkoneksi();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                comboKamar.addItem(rs.getString("nama_kamar"));

            }
            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();

        } catch (SQLException e) {

        }
    }

    public void itemKamar() throws SQLException {
        popupobat Po = new popupobat();
        Po.obt = this;
        txtkode.setText(kdobat);
    }

    protected void aktif() {
        Object[] Baris = {"Kode Transaksi", "Nama Paien", "Kode Obat", "Nama Obat", "Merek Obat", "Harga Obat", "Qty", "Total"};
        tabmode = new DefaultTableModel(null, Baris);
        tabeltransaksi.setModel(tabmode);
    }

    protected void datatable() {
        Object[] Baris = {"Kode Transaksi", "Nama Pasien", "Kode Obat", "Nama Obat", "Merek Obat", "Harga Obat", "Qty", "Total Obat", "Biaya Perawatan", "Bayar", "Kembali", "Kamar", "Tarif", "Hari", "Total Kamar", "Total Bayar","Tanggal"};
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem = txtcariitem.getText();
        try {
            String sql = "SELECT * FROM kasir where kode_transaksi like '%" + cariitem + "%' or nama_pasien like '%" + cariitem + "%' order by kode_transaksi asc";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tabmode.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),
                    hasil.getString(4),
                    hasil.getString(5),
                    hasil.getString(6),
                    hasil.getString(7),
                    hasil.getString(8),
                    hasil.getString(9),
                    hasil.getString(10),
                    hasil.getString(11),
                    hasil.getString(12),
                    hasil.getString(13),
                    hasil.getString(14),
                    hasil.getString(15),
                    hasil.getString(16),
                    hasil.getString(17)

                });
            }
            tabeltransaksi.setModel(tabmode);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil" + e);
        }
    }

    public void buatnomor() {
        try {
            String sql = "Select kode_transaksi from kasir ORDER by kode_transaksi asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            txttransaksi.setText("T0001");
            while (rs.next()) {
                String id = rs.getString("kode_transaksi").substring(2);
                int AN = Integer.parseInt(id) + 1;
                String Nol = "";

                if (AN < 10) {
                    Nol = "000";
                } else if (AN < 100) {
                    Nol = "00";
                } else if (AN < 1000) {
                    Nol = "0";
                } else if (AN < 10000) {
                    Nol = "";
                }
                txttransaksi.setText("T" + Nol + AN);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Auto number gagal" + e);
        }
    }

    public void itemTerpilih() throws SQLException {
        popupobat Po = new popupobat();
        Po.obt = this;
        txtkode.setText(kdobat);
        txtnamaobat.setText(nmobat);
        txtmerek.setText(merek);
        txtharga.setText(hj);
        txtjumlah.requestFocus();
    }

    public void hitung() {
        int total = 0;
        for (int i = 0; i < tabeltransaksi.getRowCount(); i++) {
            int amount = Integer.valueOf(tabeltransaksi.getValueAt(i, 6).toString());
            total += amount;
        }
        txtobat.setText(Integer.toString(total));
    }

    public void bayar() {
        int total = 0;
        for (int j = 0; j < tabeltransaksi.getRowCount(); j++) {
            int amount = Integer.valueOf(tabeltransaksi.getValueAt(j, 7).toString());
            total += amount;
        }
        txttotal.setText(Integer.toString(total));
    }public void waktu(){
        String jam;
        Date tgl= new Date();
        tanggal.setDate(tgl);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        user = new javax.swing.JButton();
        pasien = new javax.swing.JButton();
        dokter = new javax.swing.JButton();
        diagnosa = new javax.swing.JButton();
        Obat = new javax.swing.JButton();
        kasir = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        Tanggal = new javax.swing.JLabel();
        Waktu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabeltransaksi = new javax.swing.JTable();
        Cetak = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        txttotal = new javax.swing.JTextField();
        txttambah = new javax.swing.JButton();
        txtobat = new javax.swing.JTextField();
        txttransaksi = new javax.swing.JTextField();
        txtkode = new javax.swing.JTextField();
        txtnamaobat = new javax.swing.JTextField();
        txtmerek = new javax.swing.JTextField();
        txtharga = new javax.swing.JTextField();
        txtjumlah = new javax.swing.JTextField();
        txtbiaya = new javax.swing.JTextField();
        txtbayar = new javax.swing.JTextField();
        txtkembali = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        comboPasien = new javax.swing.JComboBox<>();
        txttarif = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        totalkamar = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txthari = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        comboKamar = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        tanggal = new com.toedter.calendar.JDateChooser();
        txtcariitem = new javax.swing.JTextField();
        carit = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(900, 550));
        jPanel1.setLayout(null);

        user.setText("User");
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jPanel1.add(user);
        user.setBounds(80, 200, 210, 40);

        pasien.setText("Pasien");
        pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasienActionPerformed(evt);
            }
        });
        jPanel1.add(pasien);
        pasien.setBounds(80, 280, 210, 40);

        dokter.setText("Dokter");
        dokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dokterActionPerformed(evt);
            }
        });
        jPanel1.add(dokter);
        dokter.setBounds(80, 240, 210, 40);

        diagnosa.setText("Diagnosa");
        diagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagnosaActionPerformed(evt);
            }
        });
        jPanel1.add(diagnosa);
        diagnosa.setBounds(80, 320, 210, 40);

        Obat.setText("Obat");
        Obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ObatActionPerformed(evt);
            }
        });
        jPanel1.add(Obat);
        Obat.setBounds(80, 360, 210, 40);

        kasir.setBackground(new java.awt.Color(0, 51, 255));
        kasir.setText("Kasir");
        kasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kasirActionPerformed(evt);
            }
        });
        jPanel1.add(kasir);
        kasir.setBounds(80, 440, 210, 40);

        keluar.setBackground(new java.awt.Color(51, 51, 255));
        keluar.setText("Keluar");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });
        jPanel1.add(keluar);
        keluar.setBounds(950, 870, 77, 29);

        Tanggal.setText("jLabel13");
        jPanel1.add(Tanggal);
        Tanggal.setBounds(870, 70, 160, 20);

        Waktu.setText("jLabel13");
        jPanel1.add(Waktu);
        Waktu.setBounds(870, 110, 160, 20);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("DAFTAR PEMBELIAN OBAT");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(350, 60, 440, 50);

        cari.setText("Cari");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        jPanel1.add(cari);
        cari.setBounds(300, 720, 61, 29);

        tabeltransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabeltransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeltransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabeltransaksi);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(330, 210, 680, 290);

        Cetak.setText("Cetak");
        Cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CetakActionPerformed(evt);
            }
        });
        jPanel1.add(Cetak);
        Cetak.setBounds(690, 820, 71, 29);

        batal.setText("Batal");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });
        jPanel1.add(batal);
        batal.setBounds(770, 820, 67, 29);

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        jPanel1.add(hapus);
        hapus.setBounds(840, 820, 77, 29);

        txttotal.setText("Rp. ");
        txttotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttotalMouseClicked(evt);
            }
        });
        txttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalActionPerformed(evt);
            }
        });
        jPanel1.add(txttotal);
        txttotal.setBounds(780, 620, 188, 26);

        txttambah.setText("Tambah");
        txttambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttambahActionPerformed(evt);
            }
        });
        jPanel1.add(txttambah);
        txttambah.setBounds(890, 780, 91, 29);

        txtobat.setText("Rp.");
        txtobat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtobatActionPerformed(evt);
            }
        });
        jPanel1.add(txtobat);
        txtobat.setBounds(780, 560, 190, 26);
        jPanel1.add(txttransaksi);
        txttransaksi.setBounds(210, 610, 148, 26);
        jPanel1.add(txtkode);
        txtkode.setBounds(210, 690, 148, 26);
        jPanel1.add(txtnamaobat);
        txtnamaobat.setBounds(210, 750, 148, 26);
        jPanel1.add(txtmerek);
        txtmerek.setBounds(210, 800, 148, 26);
        jPanel1.add(txtharga);
        txtharga.setBounds(510, 570, 148, 26);

        txtjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjumlahActionPerformed(evt);
            }
        });
        txtjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtjumlahKeyTyped(evt);
            }
        });
        jPanel1.add(txtjumlah);
        txtjumlah.setBounds(510, 610, 148, 26);

        txtbiaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbiayaKeyTyped(evt);
            }
        });
        jPanel1.add(txtbiaya);
        txtbiaya.setBounds(510, 650, 148, 26);

        txtbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbayarActionPerformed(evt);
            }
        });
        jPanel1.add(txtbayar);
        txtbayar.setBounds(820, 710, 148, 26);

        txtkembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtkembaliMouseClicked(evt);
            }
        });
        jPanel1.add(txtkembali);
        txtkembali.setBounds(820, 740, 148, 26);

        jLabel3.setText("Kode Transaksi");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(80, 610, 107, 20);

        jLabel4.setText("Kode Obat");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(80, 690, 74, 20);

        jLabel5.setText("Nama Obat");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(80, 750, 80, 20);

        jLabel6.setText("Merek Obat");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(80, 800, 81, 20);

        jLabel7.setText("Harga Obat");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(400, 570, 81, 20);

        jButton1.setText("Kamar");
        jPanel1.add(jButton1);
        jButton1.setBounds(80, 400, 210, 40);

        jLabel12.setText("Nama Pasien");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(80, 650, 100, 20);

        comboPasien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Select---" }));
        jPanel1.add(comboPasien);
        comboPasien.setBounds(210, 650, 150, 26);
        jPanel1.add(txttarif);
        txttarif.setBounds(510, 750, 150, 26);

        jLabel15.setText("Tarif");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(400, 750, 33, 20);

        jLabel8.setText("Qty");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(400, 610, 25, 20);

        jLabel14.setText("Nama Kamar");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(400, 700, 80, 20);

        jLabel9.setText("Biaya Perawatan");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(400, 650, 115, 20);

        jLabel16.setText("Hari");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(400, 800, 60, 20);

        jLabel17.setText("Total");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(720, 620, 36, 20);

        jLabel19.setText("Obat");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(720, 560, 34, 20);

        totalkamar.setText("Rp. ");
        jPanel1.add(totalkamar);
        totalkamar.setBounds(780, 590, 190, 26);

        jLabel18.setText("Kamar");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(720, 590, 45, 20);

        txthari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txthariKeyTyped(evt);
            }
        });
        jPanel1.add(txthari);
        txthari.setBounds(510, 800, 150, 26);

        jButton2.setText("Report");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(920, 820, 80, 29);

        jLabel10.setText("Bayar");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(720, 710, 39, 20);

        jLabel11.setText("Kembali");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(720, 740, 56, 20);

        comboKamar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---- Select ----" }));
        comboKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboKamarActionPerformed(evt);
            }
        });
        jPanel1.add(comboKamar);
        comboKamar.setBounds(510, 700, 150, 26);

        jLabel20.setText("Tanggal");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(80, 570, 57, 20);

        tanggal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tanggalPropertyChange(evt);
            }
        });
        jPanel1.add(tanggal);
        tanggal.setBounds(210, 570, 150, 26);
        jPanel1.add(txtcariitem);
        txtcariitem.setBounds(820, 470, 160, 26);

        carit.setText("Cari");
        jPanel1.add(carit);
        carit.setBounds(740, 470, 61, 29);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Kasir.png"))); // NOI18N
        jPanel1.add(jLabel13);
        jLabel13.setBounds(10, -100, 1170, 1140);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        
        int dialogBtn =JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Anda Yakin Akan Keluar?", "Peringatan", dialogBtn);
        if (dialogResult == 0){
            Login frmU = null;
            this.dispose();
        frmU = new Login();
        frmU.setVisible(true);
        
        }else{
        }
    }//GEN-LAST:event_keluarActionPerformed

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        User frmU = null;
        try {
            frmU = new User();
        } catch (SQLException ex) {
            Logger.getLogger(Kasir.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmU.setVisible(true);
    }//GEN-LAST:event_userActionPerformed

    private void pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasienActionPerformed
        Pasien frmP = null;
        try {
            frmP = new Pasien();
        } catch (SQLException ex) {
            Logger.getLogger(Kasir.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmP.setVisible(true);
    }//GEN-LAST:event_pasienActionPerformed

    private void dokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dokterActionPerformed
        Dokter frmD = null;
        try {
            frmD = new Dokter();
        } catch (SQLException ex) {
            Logger.getLogger(Kasir.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmD.setVisible(true);
    }//GEN-LAST:event_dokterActionPerformed

    private void diagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diagnosaActionPerformed
        Diagnosa frmDi = null;
        try {
            frmDi = new Diagnosa();
        } catch (SQLException ex) {
            Logger.getLogger(Kasir.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmDi.setVisible(true);
    }//GEN-LAST:event_diagnosaActionPerformed

    private void ObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ObatActionPerformed
        Obat frmO = null;
        try {
            frmO = new Obat();
        } catch (SQLException ex) {
            Logger.getLogger(Kasir.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmO.setVisible(true);
    }//GEN-LAST:event_ObatActionPerformed

    private void kasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kasirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kasirActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        popupobat Po = null;
        try {
            Po = new popupobat();
        } catch (SQLException ex) {
            Logger.getLogger(Kasir.class.getName()).log(Level.SEVERE, null, ex);
        }
        Po.obt = this;
        Po.setVisible(true);
        Po.setResizable(false);
    }//GEN-LAST:event_cariActionPerformed

    private void txtjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjumlahActionPerformed

    }//GEN-LAST:event_txtjumlahActionPerformed

    private void txttambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttambahActionPerformed
        String sql = "insert into kasir values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txttransaksi.getText());
            stat.setString(2, comboPasien.getSelectedItem().toString());
            stat.setString(3, txtkode.getText());
            stat.setString(4, txtnamaobat.getText());
            stat.setString(5, txtmerek.getText());
            stat.setString(6, txtharga.getText());
            stat.setString(7, txtjumlah.getText());
            stat.setString(8, txtobat.getText());
            stat.setString(9, txtbiaya.getText());
            stat.setString(10, txtbayar.getText());
            stat.setString(11, txtkembali.getText());
            stat.setString(12, comboKamar.getSelectedItem().toString());
            stat.setString(13, txttarif.getText());
            stat.setString(14, txthari.getText());
            stat.setString(15, totalkamar.getText());
            stat.setString(16, txttotal.getText());
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            String date = dt.format(tanggal.getDate());
            stat.setString(17,date);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil disimpan");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan" + e);
        }
        datatable();
        kosong();
        hitung();
    }//GEN-LAST:event_txttambahActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        aktif();
        buatnomor();
        datatable();
        kosong();
    }//GEN-LAST:event_batalActionPerformed

    private void CetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CetakActionPerformed
        try {
            HashMap parameter = new HashMap();
            parameter.put("kode_transaksi", txttransaksi.getText());
            File file = new File("src/Report/reportTransaksi.jasper");
            JasperReport jr = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameter, conn);
            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Data Tidak Dapat di Cetak !!!" + "\n" + e.getMessage(),
                    "Cetak Data", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_CetakActionPerformed

    private void txtbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbayarActionPerformed

    }//GEN-LAST:event_txtbayarActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "hapus", "konfirmasi dialog", JOptionPane.YES_NO_OPTION);
        String sql = "delete from kasir where kode_transaksi = '" + txttransaksi.getText() + "'";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil dihapus");
            kosong();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal dihapus" + e);
        }
        datatable();
    }//GEN-LAST:event_hapusActionPerformed

    private void txttotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttotalMouseClicked
        int xhrgj = Integer.parseInt(txtharga.getText());
        int xqty = Integer.parseInt(txtjumlah.getText());
        int xo = Integer.parseInt(txtbiaya.getText());
        int xi = Integer.parseInt(txttarif.getText());
        int xa = Integer.parseInt(txthari.getText());
        int xk = Integer.parseInt(txtbiaya.getText());
        int xt = (xi * xa);
        int xj = (xhrgj * xqty);
        int xjml = (xj + xo + xt + xk);
        totalkamar.setText(String.valueOf(xt));
        txttotal.setText(String.valueOf(xjml));
        txtobat.setText(String.valueOf(xj));
    }//GEN-LAST:event_txttotalMouseClicked

    private void txttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalActionPerformed

    private void txtkembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtkembaliMouseClicked
        int xo = Integer.parseInt(txttotal.getText());

        int xt = Integer.parseInt(txtbayar.getText());

        int xk = (xt - xo);
        txtkembali.setText(String.valueOf(xk));

    }//GEN-LAST:event_txtkembaliMouseClicked

    private void txtobatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtobatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtobatActionPerformed

    private void kamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kamarActionPerformed

    }//GEN-LAST:event_kamarActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void tabeltransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeltransaksiMouseClicked
        int bar = tabeltransaksi.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        String h = tabmode.getValueAt(bar, 7).toString();
        String i = tabmode.getValueAt(bar, 8).toString();
        String j = tabmode.getValueAt(bar, 9).toString();
        String k = tabmode.getValueAt(bar, 10).toString();
        String l = tabmode.getValueAt(bar, 11).toString();
        String m = tabmode.getValueAt(bar, 12).toString();
        String n = tabmode.getValueAt(bar, 13).toString();
        String o = tabmode.getValueAt(bar, 14).toString();
        String p = tabmode.getValueAt(bar, 15).toString();
        String q = tabmode.getValueAt(bar, 16).toString();
        txttransaksi.setText(a);
        comboPasien.setSelectedItem(b);
        txtkode.setText(c);
        txtnamaobat.setText(d);
        txtmerek.setText(e);
        txtharga.setText(f);
        txtjumlah.setText(g);
        txtobat.setText(h);
        txtbiaya.setText(i);
        txtbayar.setText(j);
        txtkembali.setText(k);
        comboKamar.setSelectedItem(c);
        txttarif.setText(m);
        txthari.setText(n);
        totalkamar.setText(o);
        txttotal.setText(p);
        Date tgl = null;
        try {
            tgl = new SimpleDateFormat("yyyy-MM-dd").parse((String)tabmode.getValueAt(bar,16));
        } catch (ParseException ex) {
            Logger.getLogger(Pasien.class.getName()).log(Level.SEVERE, null, ex);
        }
            tanggal.setDate(tgl);

    }//GEN-LAST:event_tabeltransaksiMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        popupcetak frmPC = null;
        try {
            frmPC = new popupcetak();
        } catch (SQLException ex) {
            Logger.getLogger(Kasir.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmPC.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void comboKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboKamarActionPerformed
        String kamar;
        int tarif = 0;
        kamar = String.valueOf(comboKamar.getSelectedItem());
        if (kamar.equals("Mawar 1")) {
            tarif = 50000;
        } else if (kamar.equals("Mawar II")) {
            tarif = 50000;
        }
        if (kamar.equals("Melati II")) {
            tarif = 150000;
        } else if (kamar.equals("Cempaka I")) {
            tarif = 50000;
        } else if (kamar.equals("Cempaka II")) {
            tarif = 50000;
        }
        if (kamar.equals("Melati I")) {
            tarif = 150000;
        } else if (kamar.equals("Anggrek I")) {
            tarif = 250000;
        }
        if (kamar.equals("Anggrek II")) {
            tarif = 250000;
        }
        txttarif.setText("" + tarif);
    }//GEN-LAST:event_comboKamarActionPerformed

    private void tanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tanggalPropertyChange
      
    }//GEN-LAST:event_tanggalPropertyChange

    private void txtjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtjumlahKeyTyped
     char[] x = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',',','.'};
        boolean boleh = false;
        for (int i=0; i < x.length; i++) {
        if (x[i] == evt.getKeyChar()) {
        boleh = true;
            break;
            }
            }
        if (!boleh) {
        evt.setKeyChar((char) 0);
        }
    }//GEN-LAST:event_txtjumlahKeyTyped

    private void txthariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthariKeyTyped
        char[] x = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',',','.'};
        boolean boleh = false;
        for (int i=0; i < x.length; i++) {
        if (x[i] == evt.getKeyChar()) {
        boleh = true;
            break;
            }
            }
        if (!boleh) {
        evt.setKeyChar((char) 0);
        }
    }//GEN-LAST:event_txthariKeyTyped

    private void txtbiayaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbiayaKeyTyped
        char[] x = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',',','.'};
        boolean boleh = false;
        for (int i=0; i < x.length; i++) {
        if (x[i] == evt.getKeyChar()) {
        boleh = true;
            break;
            }
            }
        if (!boleh) {
        evt.setKeyChar((char) 0);
        }
    }//GEN-LAST:event_txtbiayaKeyTyped

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
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Kasir().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Kasir.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cetak;
    private javax.swing.JButton Obat;
    private javax.swing.JLabel Tanggal;
    private javax.swing.JLabel Waktu;
    private javax.swing.JButton batal;
    private javax.swing.JButton cari;
    private javax.swing.JButton carit;
    private javax.swing.JComboBox<String> comboKamar;
    private javax.swing.JComboBox<String> comboPasien;
    private javax.swing.JButton diagnosa;
    private javax.swing.JButton dokter;
    private javax.swing.JButton hapus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kasir;
    private javax.swing.JButton keluar;
    private javax.swing.JButton pasien;
    private javax.swing.JTable tabeltransaksi;
    private com.toedter.calendar.JDateChooser tanggal;
    private javax.swing.JTextField totalkamar;
    private javax.swing.JTextField txtbayar;
    private javax.swing.JTextField txtbiaya;
    private javax.swing.JTextField txtcariitem;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txthari;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtkembali;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtmerek;
    private javax.swing.JTextField txtnamaobat;
    private javax.swing.JTextField txtobat;
    private javax.swing.JButton txttambah;
    private javax.swing.JTextField txttarif;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttransaksi;
    private javax.swing.JButton user;
    // End of variables declaration//GEN-END:variables
}
