/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klinik;

import java.awt.event.KeyEvent;
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
public class Pasien extends javax.swing.JFrame {
    String nmKamar, trf;
    
     private Connection conn;
    private DefaultTableModel tabmode;
  
    /**
     * Creates new form Pasien
     */
    public Pasien() throws SQLException {
        this.conn = new koneksi().getkoneksi();
        initComponents();
        kosong();
        aktif();
        autonumber();
        datatable();
     
        Date now;
        now = new Date();

        SimpleDateFormat tgl1,tgl2;
        tgl1 = new SimpleDateFormat("EEEE, dd MMM yyyy");
        tgl2 = new SimpleDateFormat("HH:mm:ss");

        Tanggal.setText(tgl1.format(now));
        Waktu.setText(tgl2.format(now));
    }
     protected void aktif(){
        Object[] Baris = {"Kode Pasien","Nama Kamar","Tarif"};
        tabmode = new DefaultTableModel(null, Baris);
        tablepasien.setModel(tabmode);
    }

     protected void kosong(){
        txtpekerjaan.setText("");
        txtalamat.setText("");
        txtnama.setText("");
        txttempat.setText("");
        txtcari.setText("");
        txttempat.setText("");
        
        txthp.setText("");
        txtusia.setText("");
        
        poli.setSelectedItem(null);
        txtnamakamar.setText("");
        txttarif.setText("");
    }
    
     protected void autonumber(){
        try{
            String sql = "SELECT ID_Pasien FROM Pasien order by ID_Pasien asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            txtid.setText("P0001");
            while(rs.next()){
                String idp = rs.getString("ID_Pasien").substring(2);
                int AN = Integer.parseInt(idp)+1;
                String Nol = "";
                
                if (AN < 10){
                    Nol = "000";
                }
                else if (AN < 100){
                    Nol = "00";
                }
                else if (AN < 1000){
                    Nol = "0";
                }
                else if (AN < 10000){
                    Nol = "";
                }
                txtid.setText("P"+Nol+AN);
            }
        }
            catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Auto number gagal"+e);
            }
    }
     
     protected void datatable(){
        Object[] Baris = {"ID Pasien","Nama Pasien","Tempat Lahir","Tgl Lahir","Usia","Jenis Kelamin","Alamat","Pekerjaan","No. HP","Poli","Tgl Masuk", "Kamar", "Tarif"};
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem = txtcari.getText();

        try {
            String sql = "SELECT * FROM pasien where id_pasien like '%"+cariitem+"%' or Nama_pasien like '%"+cariitem+"%' or poli like '%"+cariitem+"%' order by id_pasien asc";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
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
                hasil.getString(13)
            });
        }
        tablepasien.setModel(tabmode);
     } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"data gagal dipanggil"+e);
     }
}
     public void itemTerpilih()throws SQLException{
         Popupkamar Po = new Popupkamar();
         Po.psn = this;
         txtnamakamar.setText(nmKamar);
         txttarif.setText(trf);
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Tanggal = new javax.swing.JLabel();
        Waktu = new javax.swing.JLabel();
        pasien = new javax.swing.JButton();
        dokter = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        diagnosis = new javax.swing.JButton();
        obat = new javax.swing.JButton();
        kamar = new javax.swing.JButton();
        kasir = new javax.swing.JButton();
        user = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablepasien = new javax.swing.JTable();
        tambah = new javax.swing.JButton();
        ubah = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        kategori = new javax.swing.JComboBox<>();
        cari = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        cetak = new javax.swing.JButton();
        txtid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        txttempat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        laki = new javax.swing.JRadioButton();
        perempuan = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtalamat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtusia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtpekerjaan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txthp = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tanggalmasuk = new com.toedter.calendar.JDateChooser();
        tanggallahir = new com.toedter.calendar.JDateChooser();
        poli = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txttarif = new javax.swing.JTextField();
        txtnamakamar = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cek = new javax.swing.JButton();
        txtpilihkamar = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(900, 707));
        jPanel1.setLayout(null);

        Tanggal.setText("jLabel2");
        jPanel1.add(Tanggal);
        Tanggal.setBounds(220, 50, 120, 20);

        Waktu.setText("jLabel2");
        jPanel1.add(Waktu);
        Waktu.setBounds(220, 80, 120, 20);

        pasien.setBackground(new java.awt.Color(51, 51, 255));
        pasien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pasien.setText("Pasien");
        pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasienActionPerformed(evt);
            }
        });
        jPanel1.add(pasien);
        pasien.setBounds(50, 300, 210, 40);

        dokter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dokter.setText("Dokter");
        dokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dokterActionPerformed(evt);
            }
        });
        jPanel1.add(dokter);
        dokter.setBounds(50, 260, 210, 40);

        keluar.setBackground(new java.awt.Color(255, 0, 0));
        keluar.setText("Keluar");
        keluar.setBorderPainted(false);
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });
        jPanel1.add(keluar);
        keluar.setBounds(100, 520, 102, 40);

        diagnosis.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        diagnosis.setText("Diagnosis");
        diagnosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagnosisActionPerformed(evt);
            }
        });
        jPanel1.add(diagnosis);
        diagnosis.setBounds(50, 340, 210, 40);

        obat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        obat.setText("Obat");
        obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obatActionPerformed(evt);
            }
        });
        jPanel1.add(obat);
        obat.setBounds(50, 380, 210, 40);

        kamar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kamar.setText("Kamar");
        kamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kamarActionPerformed(evt);
            }
        });
        jPanel1.add(kamar);
        kamar.setBounds(50, 420, 210, 40);

        kasir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kasir.setText("Kasir");
        kasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kasirActionPerformed(evt);
            }
        });
        jPanel1.add(kasir);
        kasir.setBounds(50, 460, 210, 40);

        user.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        user.setText("User");
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jPanel1.add(user);
        user.setBounds(50, 220, 210, 40);

        tablepasien.setModel(new javax.swing.table.DefaultTableModel(
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
        tablepasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablepasienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablepasien);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(340, 80, 790, 160);

        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        jPanel1.add(tambah);
        tambah.setBounds(350, 260, 91, 29);

        ubah.setText("Edit");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });
        jPanel1.add(ubah);
        ubah.setBounds(460, 260, 59, 29);

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        jPanel1.add(hapus);
        hapus.setBounds(540, 260, 77, 29);

        batal.setText("Batal");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });
        jPanel1.add(batal);
        batal.setBounds(720, 260, 67, 29);

        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Kategori");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(830, 260, 58, 20);

        kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori", "ID Pasien", "Nama Pasien", "Poli" }));
        kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kategoriActionPerformed(evt);
            }
        });
        jPanel1.add(kategori);
        kategori.setBounds(910, 260, 210, 26);

        cari.setText("Cari");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        jPanel1.add(cari);
        cari.setBounds(830, 300, 61, 29);

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        jPanel1.add(txtcari);
        txtcari.setBounds(920, 300, 210, 26);

        cetak.setText("Cetak");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });
        jPanel1.add(cetak);
        cetak.setBounds(630, 260, 71, 29);

        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });
        jPanel1.add(txtid);
        txtid.setBounds(450, 350, 240, 26);

        jLabel3.setText("ID Pasien");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(340, 350, 67, 20);

        jLabel4.setText("Nama Pasien");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(340, 380, 91, 20);
        jPanel1.add(txtnama);
        txtnama.setBounds(450, 380, 240, 26);
        jPanel1.add(txttempat);
        txttempat.setBounds(450, 410, 240, 26);

        jLabel5.setText("Tempat Lahir");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(340, 410, 94, 20);

        jLabel6.setText("Tanggal Lahir");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(340, 440, 97, 20);

        laki.setText("Laki-Laki");
        jPanel1.add(laki);
        laki.setBounds(450, 470, 95, 29);

        perempuan.setText("Perempuan");
        jPanel1.add(perempuan);
        perempuan.setBounds(580, 470, 113, 29);

        jLabel10.setText("Jenis Kelamin");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(340, 470, 96, 20);

        jLabel8.setText("Alamat");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(340, 540, 50, 20);

        txtalamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtalamatActionPerformed(evt);
            }
        });
        jPanel1.add(txtalamat);
        txtalamat.setBounds(450, 530, 240, 26);

        jLabel7.setText("Usia");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(340, 500, 30, 20);

        txtusia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusiaActionPerformed(evt);
            }
        });
        txtusia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtusiaKeyTyped(evt);
            }
        });
        jPanel1.add(txtusia);
        txtusia.setBounds(450, 500, 240, 26);

        jLabel11.setText("No.Hp");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(780, 390, 45, 20);
        jPanel1.add(txtpekerjaan);
        txtpekerjaan.setBounds(890, 360, 226, 26);

        jLabel9.setText("Pekerjaan");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(780, 360, 69, 20);

        txthp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txthpKeyTyped(evt);
            }
        });
        jPanel1.add(txthp);
        txthp.setBounds(890, 390, 226, 26);

        jLabel12.setText("Poli");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(780, 420, 26, 20);
        jPanel1.add(tanggalmasuk);
        tanggalmasuk.setBounds(890, 460, 230, 30);
        jPanel1.add(tanggallahir);
        tanggallahir.setBounds(450, 440, 240, 26);

        poli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Poli", "Umum", "Gizi", "Ibu dan Anak" }));
        jPanel1.add(poli);
        poli.setBounds(890, 420, 226, 30);

        jLabel13.setText("Tanggal Masuk");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(780, 460, 106, 20);

        jLabel16.setText("Pilih Kamar");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(360, 310, 90, 20);

        jLabel17.setText("Nama Kamar");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(780, 500, 100, 20);
        jPanel1.add(txttarif);
        txttarif.setBounds(890, 530, 230, 26);
        jPanel1.add(txtnamakamar);
        txtnamakamar.setBounds(890, 500, 230, 26);

        jLabel18.setText("Tarif");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(780, 530, 60, 20);

        cek.setText("Cek");
        cek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekActionPerformed(evt);
            }
        });
        jPanel1.add(cek);
        cek.setBounds(640, 310, 60, 29);
        jPanel1.add(txtpilihkamar);
        txtpilihkamar.setBounds(460, 310, 170, 26);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FiksHome.png"))); // NOI18N
        jPanel1.add(jLabel15);
        jLabel15.setBounds(0, -100, 1200, 820);

        jLabel14.setText("jLabel14");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(360, 310, 60, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtalamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtalamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtalamatActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        if (evt.hashCode()== KeyEvent.VK_ENTER){
        datatable();
        }
    }//GEN-LAST:event_txtcariActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
      int dialogBtn =JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Anda Yakin Akan Keluar?", "Peringatan", dialogBtn);
        if (dialogResult == 0){
            Login frmU = null;
        frmU = new Login();
        frmU.setVisible(true);
        this.dispose();
        }else{
        }
    }//GEN-LAST:event_keluarActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        String jenis = null;
       if (laki.isSelected()){
            jenis = "Laki-Laki";
       } else if (perempuan.isSelected()){
            jenis = "Perempuan";
       }
       String sql = "insert into pasien values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
       
       try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txtid.getText());
            stat.setString(2,txtnama.getText());
            stat.setString(3,txttempat.getText());
            SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
            String date = dt.format(tanggallahir.getDate());
            stat.setString(4,date);
            stat.setString(5,txtusia.getText());
            stat.setString(6,jenis);
            stat.setString(7,txtalamat.getText());
            stat.setString(8,txtpekerjaan.getText());
            stat.setString(9,txthp.getText());
            stat.setString(10,poli.getSelectedItem().toString());
            String tanggal = dt.format(tanggalmasuk.getDate());
            stat.setString(11,tanggal);
            stat.setString(12,txtnamakamar.getText());
            stat.setString(13, txttarif.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil disimpan");
            kosong();
       }
       catch (SQLException e){
            JOptionPane.showMessageDialog(null, "data gagal disimpan" +e);
       }
       datatable();
    }//GEN-LAST:event_tambahActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
         datatable();
    }//GEN-LAST:event_cariActionPerformed

    private void kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kategoriActionPerformed

    private void tablepasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablepasienMouseClicked
        int bar = tablepasien.getSelectedRow();
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
        
        txtid.setText(a);
        txtnama.setText(b);
        txttempat.setText(c);
        
            Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse((String )tabmode.getValueAt(bar,3));
        } catch (ParseException ex) {
            Logger.getLogger(Pasien.class.getName()).log(Level.SEVERE, null, ex);
        }
            tanggalmasuk.setDate(date);
        
        txtusia.setText(e);
        if("Laki-Laki".equals(f)){
                laki.setSelected(true);
                }else{
                perempuan.setSelected(true);
        }
        txtalamat.setText(g);
        txtpekerjaan.setText(h);
        txthp.setText(i);
        poli.setSelectedItem(j);
         Date tanggal = null;
        try {
            tanggal = new SimpleDateFormat("dd-MM-yyyy").parse((String)tabmode.getValueAt(bar,10));
        } catch (ParseException ex) {
            Logger.getLogger(Pasien.class.getName()).log(Level.SEVERE, null, ex);
        }
            tanggalmasuk.setDate(tanggal);
        txtnamakamar.setText(l);
        txttarif.setText(m);
    }//GEN-LAST:event_tablepasienMouseClicked

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        String jenis = null;
       if (laki.isSelected()){
            jenis = "Laki-Laki";
       } else if (perempuan.isSelected()){
            jenis = "Perempuan";
       }
       String sql = "update pasien set id_pasien=?, nama_pasien=?, tempat_lahir=?, "
               + "tanggal_lahir=?, usia=?, jenis_kelamin=?, alamat=?, pekerjaan=?, "
               + "NO_hp=?, poli=?, tanggal_masuk=?, nama_kamar=?, tarif=? where id_pasien='"+txtid.getText()+"'";
       
       try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txtid.getText());
            stat.setString(2,txtnama.getText());
            stat.setString(3,txttempat.getText());
            SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
            String date = dt.format(tanggallahir.getDate());
            stat.setString(4,date);
            stat.setString(5,txtusia.getText());
            stat.setString(6,jenis);
            stat.setString(7,txtalamat.getText());
            stat.setString(8,txtpekerjaan.getText());
            stat.setString(9,txthp.getText());
            stat.setString(10,poli.getSelectedItem().toString());
            String tanggal = dt.format(tanggallahir.getDate());
            stat.setString(11,date);
            stat.setString(12, txtnamakamar.getText());
            stat.setString(13, txttarif.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil diubah");
            kosong();
       }
       catch (SQLException e){
            JOptionPane.showMessageDialog(null, "data gagal diubah" +e);
       }
       datatable();
    }//GEN-LAST:event_ubahActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null,"hapus","konfirmasi dialog", JOptionPane.YES_NO_OPTION);
        String sql = "delete from Pasien where Nama_pasien = '"+txtnama.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "data berhasil dihapus");
                kosong();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, "data gagal dihapus" +e);
            }
            datatable();
    }//GEN-LAST:event_hapusActionPerformed

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        User frmU = null;
         try {
             frmU = new User();
         } catch (SQLException ex) {
             Logger.getLogger(Pasien.class.getName()).log(Level.SEVERE, null, ex);
         }
        frmU.setVisible(true);
    }//GEN-LAST:event_userActionPerformed

    private void pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pasienActionPerformed

    private void dokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dokterActionPerformed
        Dokter frmD = null;
         try {
             frmD = new Dokter();
         } catch (SQLException ex) {
             Logger.getLogger(Pasien.class.getName()).log(Level.SEVERE, null, ex);
         }
        frmD.setVisible(true);
    }//GEN-LAST:event_dokterActionPerformed

    private void diagnosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diagnosisActionPerformed
         Diagnosa frmDi = null;
         try {
             frmDi = new Diagnosa();
         } catch (SQLException ex) {
             Logger.getLogger(Pasien.class.getName()).log(Level.SEVERE, null, ex);
         }
        frmDi.setVisible(true);
    }//GEN-LAST:event_diagnosisActionPerformed

    private void obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obatActionPerformed
        Obat frmO = null;
         try {
             frmO = new Obat();
         } catch (SQLException ex) {
             Logger.getLogger(Pasien.class.getName()).log(Level.SEVERE, null, ex);
         }
        frmO.setVisible(true);
    }//GEN-LAST:event_obatActionPerformed

    private void kasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kasirActionPerformed
        Kasir frmK = null;
        try {
            frmK = new Kasir();
        } catch (SQLException ex) {
            Logger.getLogger(Pasien.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmK.setVisible(true);
    }//GEN-LAST:event_kasirActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        try{
        HashMap parameter = new HashMap();
            File file = new File ("src/Report/Pasien.jasper");
            JasperReport jr = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameter, conn);
            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Data Tidak Dapat di Cetak !!!" + "\n" + e.getMessage(), 
                    "Cetak Data", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cetakActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
         kosong();
        aktif();
        autonumber();
        datatable();
    }//GEN-LAST:event_batalActionPerformed

    private void txtusiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusiaActionPerformed

    private void kamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kamarActionPerformed
        Kamar frmK = null;
        try {
            frmK = new Kamar();
        } catch (SQLException ex) {
            Logger.getLogger(Kamar.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmK.setVisible(true);
    }//GEN-LAST:event_kamarActionPerformed

    private void cekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekActionPerformed
        Popupkamar Po = null;
        try{
            Po = new Popupkamar();
        }catch(SQLException ex){
            Logger.getLogger(Kamar.class.getName()).log(Level.SEVERE,null,ex);
        }
        Po.psn = this;
        Po.setVisible(true);
        Po.setResizable(false);
    }//GEN-LAST:event_cekActionPerformed

    private void txtusiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusiaKeyTyped
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
    }//GEN-LAST:event_txtusiaKeyTyped

    private void txthpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthpKeyTyped
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
    }//GEN-LAST:event_txthpKeyTyped

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
            java.util.logging.Logger.getLogger(Pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Pasien().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Pasien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Tanggal;
    private javax.swing.JLabel Waktu;
    private javax.swing.JButton batal;
    private javax.swing.JButton cari;
    private javax.swing.JButton cek;
    private javax.swing.JButton cetak;
    private javax.swing.JButton diagnosis;
    private javax.swing.JButton dokter;
    private javax.swing.JButton hapus;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kamar;
    private javax.swing.JButton kasir;
    private javax.swing.JComboBox<String> kategori;
    private javax.swing.JButton keluar;
    private javax.swing.JRadioButton laki;
    private javax.swing.JButton obat;
    private javax.swing.JButton pasien;
    private javax.swing.JRadioButton perempuan;
    private javax.swing.JComboBox<String> poli;
    private javax.swing.JTable tablepasien;
    private javax.swing.JButton tambah;
    private com.toedter.calendar.JDateChooser tanggallahir;
    private com.toedter.calendar.JDateChooser tanggalmasuk;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txthp;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnamakamar;
    private javax.swing.JTextField txtpekerjaan;
    private javax.swing.JTextField txtpilihkamar;
    private javax.swing.JTextField txttarif;
    private javax.swing.JTextField txttempat;
    private javax.swing.JTextField txtusia;
    private javax.swing.JButton ubah;
    private javax.swing.JButton user;
    // End of variables declaration//GEN-END:variables
}
