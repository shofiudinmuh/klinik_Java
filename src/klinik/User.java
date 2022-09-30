/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klinik;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import java.util.regex.*;

/**
 *
 * @author USER
 */
public class User extends javax.swing.JFrame {
  private Connection conn;
    private DefaultTableModel tabmode;
    
    /**
     * Creates new form User
     */
    public User() throws SQLException {
       this.conn = new koneksi().getkoneksi();
        initComponents();
        autonumber();
        aktif();
        kosong();
        datatable();
        Date now;
        now = new Date();
        
        SimpleDateFormat tgl1, tgl2;
        tgl1 = new SimpleDateFormat ("EEEE, dd MM YYYY");
        tgl2 = new SimpleDateFormat("HH: mm:ss");
        
        Tanggal.setText(tgl1.format(now));
        Waktu.setText(tgl2.format(now));       
    }
    
    protected void autonumber(){
        try{
            String sql = "Select id_user from user ORDER by id_user asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            txtid.setText("U0001");
            while(rs.next()){
                String id = rs.getString("id_user").substring(2);
                int AN = Integer.parseInt(id)+1;
                String Nol = "";
                
                if(AN<10){
                    Nol = "000";
                }
                else if (AN<100){
                    Nol = "00";
                }
                else if (AN<1000){
                    Nol = "0";
                }
                else if(AN<10000){
                    Nol = "";
                }
                txtid.setText("U"+Nol+AN);
                
            }
    }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Auto number gagal" +e);
        }
    }
    protected void aktif(){
        Akses.setSelectedItem(null);
    }
    boolean StrCheck(String Exp, String str){
        
        return Pattern.matches(Exp,str);
    }
    protected void kosong(){
      
        txtnama.setText("");
        txtemail.setText("");
        txthp.setText("");
        txtalamat.setText("");
        txtusername.setText("");
        txtpass.setText("");
        txtcari.setText("");
        Akses.setSelectedItem(null);
    }
    
    protected void datatable(){
        Object[] Baris = {"ID User","Nama User","Jenis Kelamin","Username","Password","Akses","Alamat","Email","No. HP"};
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem = txtcari.getText();

        try {
            String sql = "SELECT * FROM user where id_user like '%"+cariitem+"%' or username like '%"+cariitem+"%' or akses like '%"+cariitem+"%' order by id_user asc";
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
                hasil.getString(9)
            });
        }
        tableuser.setModel(tabmode);
     } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"data gagal dipanggil"+e);
     }
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
        user = new javax.swing.JButton();
        pasien = new javax.swing.JButton();
        kamar = new javax.swing.JButton();
        dokter = new javax.swing.JButton();
        diagnosis = new javax.swing.JButton();
        obat = new javax.swing.JButton();
        kasir = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableuser = new javax.swing.JTable();
        tambah = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        kategori = new javax.swing.JComboBox<>();
        cari = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        laki = new javax.swing.JRadioButton();
        perempuan = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        Akses = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        txtpass = new javax.swing.JTextField();
        Email = new javax.swing.JLabel();
        errorText = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        txthp = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 623));
        jPanel1.setLayout(null);

        Tanggal.setText("jLabel1");
        jPanel1.add(Tanggal);
        Tanggal.setBounds(220, 60, 130, 20);

        Waktu.setText("jLabel2");
        jPanel1.add(Waktu);
        Waktu.setBounds(220, 90, 130, 20);

        user.setBackground(new java.awt.Color(51, 51, 255));
        user.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        user.setText("User");
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jPanel1.add(user);
        user.setBounds(50, 180, 210, 40);

        pasien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pasien.setText("Pasien");
        pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasienActionPerformed(evt);
            }
        });
        jPanel1.add(pasien);
        pasien.setBounds(50, 260, 210, 40);

        kamar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kamar.setText("Kamar");
        kamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kamarActionPerformed(evt);
            }
        });
        jPanel1.add(kamar);
        kamar.setBounds(50, 380, 210, 40);

        dokter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dokter.setText("Dokter");
        dokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dokterActionPerformed(evt);
            }
        });
        jPanel1.add(dokter);
        dokter.setBounds(50, 220, 210, 40);

        diagnosis.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        diagnosis.setText("Diagnosis");
        diagnosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagnosisActionPerformed(evt);
            }
        });
        jPanel1.add(diagnosis);
        diagnosis.setBounds(50, 300, 210, 40);

        obat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        obat.setText("Obat");
        obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obatActionPerformed(evt);
            }
        });
        jPanel1.add(obat);
        obat.setBounds(50, 340, 210, 40);

        kasir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kasir.setText("Kasir");
        kasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kasirActionPerformed(evt);
            }
        });
        jPanel1.add(kasir);
        kasir.setBounds(50, 420, 210, 40);

        keluar.setBackground(new java.awt.Color(51, 51, 255));
        keluar.setText("Keluar");
        keluar.setBorderPainted(false);
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });
        jPanel1.add(keluar);
        keluar.setBounds(110, 520, 96, 40);

        tableuser.setModel(new javax.swing.table.DefaultTableModel(
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
        tableuser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableuserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableuser);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(340, 70, 790, 160);

        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        jPanel1.add(tambah);
        tambah.setBounds(360, 260, 91, 29);

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        jPanel1.add(edit);
        edit.setBounds(470, 260, 59, 29);

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        jPanel1.add(hapus);
        hapus.setBounds(550, 260, 77, 29);

        batal.setText("Batal");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });
        jPanel1.add(batal);
        batal.setBounds(640, 260, 67, 29);

        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Kategori");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(810, 260, 70, 29);

        kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori-", "Username", "Akses" }));
        kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kategoriActionPerformed(evt);
            }
        });
        jPanel1.add(kategori);
        kategori.setBounds(910, 260, 186, 26);

        cari.setText("Cari");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        jPanel1.add(cari);
        cari.setBounds(810, 310, 61, 29);

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        jPanel1.add(txtcari);
        txtcari.setBounds(910, 310, 180, 26);

        jLabel3.setText("ID User");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(370, 360, 54, 20);

        txtid.setToolTipText("");
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });
        jPanel1.add(txtid);
        txtid.setBounds(480, 350, 260, 26);

        jLabel4.setText("Nama");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(370, 400, 41, 20);
        jPanel1.add(txtnama);
        txtnama.setBounds(480, 390, 260, 26);

        jLabel5.setText("Jenis Kelamin");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(370, 440, 96, 20);

        laki.setText("Laki-Laki");
        jPanel1.add(laki);
        laki.setBounds(480, 430, 95, 29);

        perempuan.setText("Perempuan");
        jPanel1.add(perempuan);
        perempuan.setBounds(620, 430, 113, 29);

        jLabel8.setText("Akses");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(810, 360, 41, 20);

        Akses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori", "Admin", "Dokter", "Apoteker", "Kasir" }));
        jPanel1.add(Akses);
        Akses.setBounds(890, 350, 218, 26);

        jLabel9.setText("Alamat");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(810, 390, 50, 20);

        txtalamat.setColumns(20);
        txtalamat.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtalamat.setRows(5);
        jScrollPane2.setViewportView(txtalamat);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(890, 390, 218, 80);

        jLabel6.setText("Username");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(370, 480, 71, 20);

        jLabel7.setText("Password");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(370, 530, 67, 20);
        jPanel1.add(txtusername);
        txtusername.setBounds(480, 480, 260, 26);
        jPanel1.add(txtpass);
        txtpass.setBounds(480, 520, 260, 26);

        Email.setText("Email");
        jPanel1.add(Email);
        Email.setBounds(810, 480, 39, 30);

        errorText.setBackground(new java.awt.Color(255, 0, 51));
        errorText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        errorText.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(errorText);
        errorText.setBounds(1110, 480, 30, 20);

        jLabel11.setText("No.Hp");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(810, 530, 45, 20);

        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtemailKeyTyped(evt);
            }
        });
        jPanel1.add(txtemail);
        txtemail.setBounds(890, 480, 220, 26);

        txthp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txthpKeyTyped(evt);
            }
        });
        jPanel1.add(txthp);
        txthp.setBounds(890, 520, 220, 26);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FiksHome.png"))); // NOI18N
        jPanel1.add(jLabel12);
        jLabel12.setBounds(0, -60, 1200, 730);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1204, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            jenis ="Laki-Laki";
        }else if (perempuan.isSelected()){
            jenis = "Perempuan";   
        }
        String sql = "insert into user values (?, ?, ?, ?, ?, ?, ?, ?,?)";
       
        try{
            PreparedStatement stat = conn.prepareStatement (sql);
            stat.setString(1, txtid.getText());
            stat.setString(2, txtnama.getText());
            stat.setString(3, jenis);
            stat.setString(4,txtusername.getText());
            stat.setString(5,txtpass.getText());
            stat.setString(6,Akses.getSelectedItem().toString());
            stat.setString(7,txtalamat.getText());
             if (!(Pattern.matches("^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$", txtemail.getText()))) 
{
            JOptionPane.showMessageDialog(null, "Please enter a valid email", "Error", JOptionPane.ERROR_MESSAGE);

            }
            else
            {
                stat.setString(8,txtemail.getText());
            }
           
            stat.setString(9,txthp.getText());
            
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

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
      if (evt.hashCode()== KeyEvent.VK_ENTER){
        datatable();
        }   
    }//GEN-LAST:event_txtcariActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        kosong();
        aktif();
        datatable();
        autonumber();
    }//GEN-LAST:event_batalActionPerformed

    private void kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategoriActionPerformed
        Akses.getSelectedItem().toString();
    }//GEN-LAST:event_kategoriActionPerformed

    private void tableuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableuserMouseClicked
        int bar = tableuser.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        String h = tabmode.getValueAt(bar, 7).toString();
        String i = tabmode.getValueAt(bar, 8).toString();
        
        txtid.setText(a);
        txtnama.setText(b);
        if("Laki-Laki".equals(c)){
            laki.setSelected(true);
        }else{
            perempuan.setSelected(true);
        }
        txtusername.setText(d);
        txtpass.setText(e);
        Akses.setSelectedItem(f);
        txtalamat.setText(g);
        txtemail.setText(h);
        txthp.setText(i);
    }//GEN-LAST:event_tableuserMouseClicked

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        String jenis = null;
       if (laki.isSelected()){
            jenis = "Laki-Laki";
       } else if (perempuan.isSelected()){
            jenis = "Perempuan";
       }
       String sql = "update user set id_user=?, nama=?, jenis_kelamin=?, username=?, password=?, akses=?, alamat=?, email=?, no_hp=? where username='"+txtusername.getText()+"'";
       
       try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txtid.getText());
            stat.setString(2,txtnama.getText());
            stat.setString(3,jenis);
            stat.setString(4,txtusername.getText());
            stat.setString(5,txtpass.getText());
            stat.setString(6,Akses.getSelectedItem().toString());
            stat.setString(7,txtalamat.getText());
            if (!(Pattern.matches("^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$", txtemail.getText()))) 
{
            JOptionPane.showMessageDialog(null, "Please enter a valid email", "Error", JOptionPane.ERROR_MESSAGE);

            }
            else
            {
                stat.setString(8,txtemail.getText());
            }
            stat.setString(9,txthp.getText());
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil diubah");
            kosong();
       }
       catch (SQLException e){
            JOptionPane.showMessageDialog(null, "data gagal diubah" +e);
       }
       datatable();
    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null,"hapus","konfirmasi dialog", JOptionPane.YES_NO_OPTION);
        String sql = "delete from user where username = '"+txtusername.getText()+"'";
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

    private void pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasienActionPerformed
        Pasien frmP = null;
      try {
          frmP = new Pasien();
      } catch (SQLException ex) {
          Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
      }
        frmP.setVisible(true);
    }//GEN-LAST:event_pasienActionPerformed

    private void dokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dokterActionPerformed
        Dokter frmD = null;
      try {
          frmD = new Dokter();
      } catch (SQLException ex) {
          Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
      }
        frmD.setVisible(true);
    }//GEN-LAST:event_dokterActionPerformed

    private void diagnosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diagnosisActionPerformed
        Diagnosa frmDi = null;
      try {
          frmDi = new Diagnosa();
      } catch (SQLException ex) {
          Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
      }
        frmDi.setVisible(true);
    }//GEN-LAST:event_diagnosisActionPerformed

    private void obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obatActionPerformed
        Obat frmO = null;
      try {
          frmO = new Obat();
      } catch (SQLException ex) {
          Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
      }
        frmO.setVisible(true);
    }//GEN-LAST:event_obatActionPerformed

    private void kasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kasirActionPerformed
        Kasir frmK = null;
      try {
          frmK = new Kasir();
      } catch (SQLException ex) {
          Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
      }
        frmK.setVisible(true);
    }//GEN-LAST:event_kasirActionPerformed

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userActionPerformed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void kamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kamarActionPerformed
        Kamar frmK = null;
        try {
            frmK = new Kamar();
        } catch (SQLException ex) {
            Logger.getLogger(Kamar.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmK.setVisible(true);
    }//GEN-LAST:event_kamarActionPerformed

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
      if (!(Pattern.matches("^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$", txtemail.getText()))) 
{
            JOptionPane.showMessageDialog(null, "Please enter a valid email", "Error", JOptionPane.ERROR_MESSAGE);

            }
            else
            {
                JOptionPane.showMessageDialog(null, "The email is valid", "Good!", JOptionPane.INFORMATION_MESSAGE);
            }
            
    }//GEN-LAST:event_txtemailActionPerformed

    private void txtemailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyTyped
            String Exp = "[\\w\\.]+@\\w+\\.[A-Za-z\\.]{2,5}";
            String str =txtemail.getText();
            char Ch=evt.getKeyChar();
            if(Ch>=33 && Ch <=126)
                str+=Ch;
            
            if(StrCheck(Exp, str))
                errorText.setText("");
            else
                errorText.setText("*");
            ;
    }//GEN-LAST:event_txtemailKeyTyped

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
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new User().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Akses;
    private javax.swing.JLabel Email;
    private javax.swing.JLabel Tanggal;
    private javax.swing.JLabel Waktu;
    private javax.swing.JButton batal;
    private javax.swing.JButton cari;
    private javax.swing.JButton diagnosis;
    private javax.swing.JButton dokter;
    private javax.swing.JButton edit;
    private javax.swing.JLabel errorText;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton kamar;
    private javax.swing.JButton kasir;
    private javax.swing.JComboBox<String> kategori;
    private javax.swing.JButton keluar;
    private javax.swing.JRadioButton laki;
    private javax.swing.JButton obat;
    private javax.swing.JButton pasien;
    private javax.swing.JRadioButton perempuan;
    private javax.swing.JTable tableuser;
    private javax.swing.JButton tambah;
    private javax.swing.JTextArea txtalamat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txthp;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtpass;
    private javax.swing.JTextField txtusername;
    private javax.swing.JButton user;
    // End of variables declaration//GEN-END:variables
}