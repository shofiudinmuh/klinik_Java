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
public class Diagnosa extends javax.swing.JFrame {
 private Connection conn;
    private DefaultTableModel tabmode;
    String poli, diagnosis;
    /**
     * Creates new form Diagnosa
     */
    public Diagnosa() throws SQLException {
         this.conn = new koneksi().getkoneksi();
        initComponents();
        kosong();
        aktif();
        buatnomor();
        autonumber();
        datatable();
        tampil_comPasien();
        tampil_comDok();
        
        Date now;
        now = new Date();

        SimpleDateFormat tgl1,tgl2;
        tgl1 = new SimpleDateFormat("EEEE, dd MMM yyyy");
        tgl2 = new SimpleDateFormat("HH:mm:ss");

        Tanggal.setText(tgl1.format(now));
        Waktu.setText(tgl2.format(now));
    }
    
    public void tampil_comPasien(){
        try{
            String sql = "select*from Pasien ";
            java.sql.Connection conn = (Connection) koneksi.getkoneksi();
            java.sql.PreparedStatement pst =conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                comboNamaPas.addItem(rs.getString("nama_pasien"));
                
            }
            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();
            
            
        }catch(Exception e){
            
        }
    }
    
    public void tampil_comDok(){
        try{
            String sql = "select*from Dokter ";
            java.sql.Connection conn = (Connection) koneksi.getkoneksi();
            java.sql.PreparedStatement pst =conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                comboNamaDok.addItem(rs.getString("nama_dokter"));
                
            }
            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();
            
            
        }catch(Exception e){
            
        }
    }
    protected void aktif(){
        kategoripoli.setSelectedItem(null);
        kategoridiagnosa.setSelectedItem(null);
    }

    protected void kosong(){
        txtno.setText("");
       
        txtpenyakit.setText("");
        txtdiagnosa.setText("");
        comboNamaPas.setSelectedItem(null);
        comboNamaDok.setSelectedItem(null);
        txtpenyakit.setText("");
        txtobat.setText("");
        txtcari.setText("");
        kategoripoli.setSelectedItem(null);
        kategoridiagnosa.setSelectedItem(null);
    }
    
    public void buatnomor(){
        try{
            String sql = "Select no from diagnosa ORDER by no desc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                int no_t = Integer.parseInt(rs.getString("no")) + 1;
                txtno.setText(Integer.toString(no_t));
            }
            else{
                int no_t = 1;
                txtno.setText(Integer.toString(no_t));
            }
            rs.close();
        }catch (Exception e){
        }
    }
    
    protected void autonumber(){
        try{
            String sql = "SELECT kode_diagnosa FROM diagnosa order by kode_diagnosa asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            txtdiagnosa.setText("KD0001");
            while(rs.next()){
                String kd = rs.getString("kode_diagnosa").substring(2);
                int AN = Integer.parseInt(kd)+1;
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
                txtdiagnosa.setText("KD"+Nol+AN);
            }
        }
            catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Auto number gagal"+e);
            }
    }

    protected void datatable(){
        Object[] Baris = {"No.","Kode Diagnosa","Poli","Nama Pasien","Nama Dokter","Diagnosa","Jenis Penyakit","Jenis Obat","Tgl Berobat"};
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem = txtcari.getText();

        try {
            String sql = "SELECT * FROM diagnosa where kode_diagnosa like '%"+cariitem+"%' or nama_pasien like '%"+cariitem+"%' or nama_dokter like '%"+cariitem+"%' order by kode_diagnosa asc";
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
        tabeldiagnosa.setModel(tabmode);
     } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"data gagal dipanggil"+e);
     }
}

   
   
    // </editor-fold>                        

    private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {                                        
        int dialogBtn = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Anda Yakin Akan Keluar", "Peringatan", dialogBtn);
        if (dialogResult==0){
            System.exit(0);
        }else{
        }
    }                                       

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {                                        
       String sql = "insert into diagnosa values (?,?,?,?,?,?,?,?)";
       
       try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txtno.getText());
            stat.setString(2,txtdiagnosa.getText());
            stat.setString(3,kategoripoli.getSelectedItem().toString());
            stat.setString(4,comboNamaPas.getSelectedItem().toString());
            stat.setString(5,comboNamaDok.getSelectedItem().toString());
            stat.setString(6,kategoridiagnosa.getSelectedItem().toString());
            stat.setString(7,txtpenyakit.getText());
            stat.setString(8,txtobat.getText());
           SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
            String date = dt.format(tanggalberobat.getDate());
            stat.setString(9,date);
            
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil disimpan");
            kosong();
       }
       catch (SQLException e){
            JOptionPane.showMessageDialog(null, "data gagal disimpan" +e);
       }
       datatable();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabeldiagnosa = new javax.swing.JTable();
        user = new javax.swing.JButton();
        Pasien = new javax.swing.JButton();
        dokter = new javax.swing.JButton();
        diagnosa = new javax.swing.JButton();
        obat = new javax.swing.JButton();
        kamar = new javax.swing.JButton();
        kasir = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        tambah = new javax.swing.JButton();
        ubah = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        cetak = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cari = new javax.swing.JButton();
        kategori = new javax.swing.JComboBox<>();
        txtno = new javax.swing.JTextField();
        txtcari = new javax.swing.JTextField();
        txtdiagnosa = new javax.swing.JTextField();
        kategoripoli = new javax.swing.JComboBox<>();
        kategoridiagnosa = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtobat = new javax.swing.JTextArea();
        tanggalberobat = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Tanggal = new javax.swing.JLabel();
        Waktu = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtpenyakit = new javax.swing.JTextArea();
        comboNamaDok = new javax.swing.JComboBox<>();
        comboNamaPas = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtobat1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(900, 749));
        jPanel1.setLayout(null);

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tabeldiagnosa.setBackground(new java.awt.Color(153, 153, 153));
        tabeldiagnosa.setModel(new javax.swing.table.DefaultTableModel(
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
        tabeldiagnosa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeldiagnosaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabeldiagnosa);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(340, 70, 790, 160);

        user.setText("User");
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jPanel1.add(user);
        user.setBounds(50, 180, 220, 40);

        Pasien.setText("Pasien");
        Pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasienActionPerformed(evt);
            }
        });
        jPanel1.add(Pasien);
        Pasien.setBounds(50, 280, 220, 40);

        dokter.setText("Dokter");
        dokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dokterActionPerformed(evt);
            }
        });
        jPanel1.add(dokter);
        dokter.setBounds(50, 230, 220, 40);

        diagnosa.setBackground(new java.awt.Color(51, 51, 255));
        diagnosa.setText("Diagnosa");
        diagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagnosaActionPerformed(evt);
            }
        });
        jPanel1.add(diagnosa);
        diagnosa.setBounds(50, 330, 220, 40);

        obat.setText("Obat");
        obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obatActionPerformed(evt);
            }
        });
        jPanel1.add(obat);
        obat.setBounds(50, 380, 220, 40);

        kamar.setText("Kamar");
        kamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kamarActionPerformed(evt);
            }
        });
        jPanel1.add(kamar);
        kamar.setBounds(50, 430, 220, 40);

        kasir.setText("Kasir");
        kasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kasirActionPerformed(evt);
            }
        });
        jPanel1.add(kasir);
        kasir.setBounds(50, 480, 220, 40);

        keluar.setBackground(new java.awt.Color(51, 51, 255));
        keluar.setText("Keluar");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });
        jPanel1.add(keluar);
        keluar.setBounds(110, 530, 91, 30);

        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        jPanel1.add(tambah);
        tambah.setBounds(340, 260, 91, 29);

        ubah.setText("Edit");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });
        jPanel1.add(ubah);
        ubah.setBounds(450, 260, 59, 29);

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        jPanel1.add(hapus);
        hapus.setBounds(530, 260, 77, 29);

        cetak.setText("Cetak");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });
        jPanel1.add(cetak);
        cetak.setBounds(620, 260, 71, 29);

        batal.setText("Batal");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });
        jPanel1.add(batal);
        batal.setBounds(710, 260, 67, 29);

        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Kategori");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(890, 260, 58, 20);

        cari.setText("Cari");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        jPanel1.add(cari);
        cari.setBounds(890, 300, 61, 29);

        kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Katgeori", "Kode Diagnosa", "Nama Pasien", "Nama Dokter" }));
        kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kategoriActionPerformed(evt);
            }
        });
        jPanel1.add(kategori);
        kategori.setBounds(990, 260, 136, 26);
        jPanel1.add(txtno);
        txtno.setBounds(480, 360, 209, 26);

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        jPanel1.add(txtcari);
        txtcari.setBounds(990, 300, 140, 26);
        jPanel1.add(txtdiagnosa);
        txtdiagnosa.setBounds(480, 400, 210, 26);

        kategoripoli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori", "Gizi", "Ibu dan Anak", "Umum" }));
        kategoripoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kategoripoliActionPerformed(evt);
            }
        });
        jPanel1.add(kategoripoli);
        kategoripoli.setBounds(480, 440, 210, 26);

        kategoridiagnosa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori", "Berat", "Ringan" }));
        jPanel1.add(kategoridiagnosa);
        kategoridiagnosa.setBounds(910, 360, 210, 26);

        txtobat.setColumns(20);
        txtobat.setRows(5);
        jScrollPane2.setViewportView(txtobat);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(910, 460, 210, 50);
        jPanel1.add(tanggalberobat);
        tanggalberobat.setBounds(910, 520, 200, 26);

        jLabel6.setText("Jenis Penyakit");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(780, 410, 99, 20);

        jLabel7.setText("Diagnosa");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(780, 360, 65, 20);

        jLabel10.setText("Nama Dokter");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(360, 520, 93, 20);

        jLabel5.setText("Nama Pasien");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(360, 480, 91, 20);

        jLabel9.setText("Tanggal Berobat");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(780, 520, 116, 20);

        jLabel8.setText("Jenis Obat");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(780, 460, 74, 20);

        jLabel4.setText("Poli");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(360, 440, 26, 20);

        jLabel3.setText("Kode Diagnosa");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(360, 400, 105, 20);

        jLabel2.setText("No.");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(360, 360, 25, 20);

        jLabel12.setText("jLabel12");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(230, 110, 60, 20);

        Tanggal.setText("jLabel13");
        jPanel1.add(Tanggal);
        Tanggal.setBounds(230, 40, 60, 20);

        Waktu.setText("jLabel13");
        jPanel1.add(Waktu);
        Waktu.setBounds(230, 80, 60, 20);

        txtpenyakit.setColumns(20);
        txtpenyakit.setRows(5);
        jScrollPane4.setViewportView(txtpenyakit);

        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(910, 400, 210, 50);

        comboNamaDok.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Select ----" }));
        jPanel1.add(comboNamaDok);
        comboNamaDok.setBounds(480, 520, 210, 26);

        comboNamaPas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----Select----" }));
        jPanel1.add(comboNamaPas);
        comboNamaPas.setBounds(480, 480, 210, 26);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FiksHome.png"))); // NOI18N
        jPanel1.add(jLabel13);
        jLabel13.setBounds(0, -80, 1210, 770);

        txtobat1.setColumns(20);
        txtobat1.setRows(5);
        jScrollPane3.setViewportView(txtobat1);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(910, 440, 210, 70);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        if (evt.hashCode()== KeyEvent.VK_ENTER){
            datatable();
        }
    }//GEN-LAST:event_txtcariActionPerformed

    private void kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategoriActionPerformed
        txtdiagnosa.getText();
        comboNamaPas.getSelectedItem().toString();
        comboNamaDok.getSelectedItem().toString();
    }//GEN-LAST:event_kategoriActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        datatable();
    }//GEN-LAST:event_cariActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        kosong();
        aktif();
        buatnomor();
        autonumber();
        datatable();
    }//GEN-LAST:event_batalActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        // TODO add your handling code here:
         try{
            HashMap parameter = new HashMap();
            parameter.put("kode_diagnosa", txtdiagnosa.getText());
            File file = new File ("src/Report/reportDiagnosa.jasper");
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

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null,"hapus","konfirmasi dialog", JOptionPane.YES_NO_OPTION);
        String sql = "delete from diagnosa where kode_diagnosa = '"+txtdiagnosa.getText()+"'";
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

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        String sql = "update diagnosa set no=?, kode_diagnosa=?, poli=?, nama_pasien=?, nama_dokter=?, diagnosa=?, jenis_penyakit=?, jenis_obat=?, tanggal=? where kode_diagnosa='"+txtdiagnosa.getText()+"'";

        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txtno.getText());
            stat.setString(2,txtdiagnosa.getText());
            stat.setString(3,kategoripoli.getSelectedItem().toString());
            stat.setString(4,comboNamaPas.getSelectedItem().toString());
            stat.setString(5,comboNamaDok.getSelectedItem().toString());
            stat.setString(6,kategoridiagnosa.getSelectedItem().toString());
            stat.setString(7,txtpenyakit.getText());
            stat.setString(8,txtobat.getText());
            SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
            String date = dt.format(tanggalberobat.getDate());
            stat.setString(9,date);

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil diubah");
            kosong();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "data gagal diubah" +e);
        }
        datatable();
    }//GEN-LAST:event_ubahActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        String sql = "insert into diagnosa values (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txtno.getText());
            stat.setString(2,txtdiagnosa.getText());
            stat.setString(3,kategoripoli.getSelectedItem().toString());
            stat.setString(4,comboNamaPas.getSelectedItem().toString());
            stat.setString(5,comboNamaDok.getSelectedItem().toString());
            stat.setString(6,kategoridiagnosa.getSelectedItem().toString());
            stat.setString(7,txtpenyakit.getText());
            stat.setString(8,txtobat.getText());
            SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
            String date = dt.format(tanggalberobat.getDate());
            stat.setString(9,date);
            ;

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil disimpan");
            kosong();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "data gagal disimpan" +e);
        }
        datatable();
    }//GEN-LAST:event_tambahActionPerformed

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

    private void kasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kasirActionPerformed
        Kasir frmK = null;
     try {
         frmK = new Kasir();
     } catch (SQLException ex) {
         Logger.getLogger(Diagnosa.class.getName()).log(Level.SEVERE, null, ex);
     }
        frmK.setVisible(true);
    }//GEN-LAST:event_kasirActionPerformed

    private void obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obatActionPerformed
        Obat frmO = null;
        try {
            frmO = new Obat();
        } catch (SQLException ex) {
            Logger.getLogger(Diagnosa.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmO.setVisible(true);
    }//GEN-LAST:event_obatActionPerformed

    private void diagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diagnosaActionPerformed

    }//GEN-LAST:event_diagnosaActionPerformed

    private void dokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dokterActionPerformed
        Dokter frmD = null;
        try {
            frmD = new Dokter();
        } catch (SQLException ex) {
            Logger.getLogger(Diagnosa.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmD.setVisible(true);
    }//GEN-LAST:event_dokterActionPerformed

    private void PasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasienActionPerformed
        Pasien frmP = null;
        try {
            frmP = new Pasien();
        } catch (SQLException ex) {
            Logger.getLogger(Diagnosa.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmP.setVisible(true);
    }//GEN-LAST:event_PasienActionPerformed

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        User frmU = null;
        try {
            frmU = new User();
        } catch (SQLException ex) {
            Logger.getLogger(Diagnosa.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmU.setVisible(true);
    }//GEN-LAST:event_userActionPerformed

    private void kategoripoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategoripoliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kategoripoliActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tabeldiagnosaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeldiagnosaMouseClicked
        int bar = tabeldiagnosa.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        String h = tabmode.getValueAt(bar, 7).toString();
        String i = tabmode.getValueAt(bar, 8).toString();

        txtno.setText(a);
        txtdiagnosa.setText(b);
        kategoripoli.setSelectedItem(c);
        comboNamaPas.setSelectedItem(d);
        comboNamaDok.setSelectedItem(d);
        kategoridiagnosa.setSelectedItem(f);
        txtpenyakit.setText(g);
        txtobat.setText(h);
        Date tanggal = null;
        try {
            tanggal = new SimpleDateFormat("dd-MM-yyyy").parse((String)tabmode.getValueAt(bar,8));
        } catch (ParseException ex) {
            Logger.getLogger(Pasien.class.getName()).log(Level.SEVERE, null, ex);
        }
            tanggalberobat.setDate(tanggal);
    }//GEN-LAST:event_tabeldiagnosaMouseClicked

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
            java.util.logging.Logger.getLogger(Diagnosa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Diagnosa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Diagnosa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Diagnosa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Diagnosa().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Diagnosa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Pasien;
    private javax.swing.JLabel Tanggal;
    private javax.swing.JLabel Waktu;
    private javax.swing.JButton batal;
    private javax.swing.JButton cari;
    private javax.swing.JButton cetak;
    private javax.swing.JComboBox<String> comboNamaDok;
    private javax.swing.JComboBox<String> comboNamaPas;
    private javax.swing.JButton diagnosa;
    private javax.swing.JButton dokter;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton kamar;
    private javax.swing.JButton kasir;
    private javax.swing.JComboBox<String> kategori;
    private javax.swing.JComboBox<String> kategoridiagnosa;
    private javax.swing.JComboBox<String> kategoripoli;
    private javax.swing.JButton keluar;
    private javax.swing.JButton obat;
    private javax.swing.JTable tabeldiagnosa;
    private javax.swing.JButton tambah;
    private com.toedter.calendar.JDateChooser tanggalberobat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtdiagnosa;
    private javax.swing.JTextField txtno;
    private javax.swing.JTextArea txtobat;
    private javax.swing.JTextArea txtobat1;
    private javax.swing.JTextArea txtpenyakit;
    private javax.swing.JButton ubah;
    private javax.swing.JButton user;
    // End of variables declaration//GEN-END:variables
}
