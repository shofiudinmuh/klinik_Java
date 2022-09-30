-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 05, 2022 at 02:44 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_klinik`
--

-- --------------------------------------------------------

--
-- Table structure for table `diagnosa`
--

CREATE TABLE `diagnosa` (
  `no` int(11) NOT NULL,
  `kode_diagnosa` varchar(20) NOT NULL,
  `poli` varchar(20) NOT NULL,
  `nama_pasien` varchar(20) NOT NULL,
  `nama_dokter` varchar(20) NOT NULL,
  `diagnosa` varchar(20) NOT NULL,
  `jenis_penyakit` varchar(30) NOT NULL,
  `jenis_obat` varchar(50) NOT NULL,
  `tanggal` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `diagnosa`
--

INSERT INTO `diagnosa` (`no`, `kode_diagnosa`, `poli`, `nama_pasien`, `nama_dokter`, `diagnosa`, `jenis_penyakit`, `jenis_obat`, `tanggal`) VALUES
(1, 'KD0001', 'Gizi', 'Retno', 'Dr. Shofiuddin', 'Ringan', 'Flu', 'Influenza', '22 Desember 2021'),
(2, 'KD0002', 'Gizi', 'Nia N', 'Dr. Shofiuddin', 'Berat', 'Jantung', 'Parasettamol', '01-01-2022'),
(3, 'KD0003', 'Gizi', 'Retno S', 'Dr. Shofiuddin', 'Ringan', 'Kurang asupan', 'Vitamin', '04-01-2022');

-- --------------------------------------------------------

--
-- Table structure for table `dokter`
--

CREATE TABLE `dokter` (
  `id_dokter` varchar(20) NOT NULL,
  `nama_dokter` varchar(25) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `telepon` varchar(15) NOT NULL,
  `poli` varchar(20) NOT NULL,
  `Hari` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dokter`
--

INSERT INTO `dokter` (`id_dokter`, `nama_dokter`, `jenis_kelamin`, `alamat`, `telepon`, `poli`, `Hari`) VALUES
('D0001', 'Dr. Shofiuddin', 'Laki-Laki', 'Surabaya', '02147483647', 'Umum', 'Senin'),
('D0002', 'Dr Risma', 'Perempuan', 'Sidoarjo', '0892923674', 'Umum', 'Jumat'),
('D0003', 'Dr Indah Permata', 'Perempuan', 'Gresik', '082333811672', 'Gizi', 'Selasa');

-- --------------------------------------------------------

--
-- Table structure for table `kamar`
--

CREATE TABLE `kamar` (
  `Kode_Kamar` varchar(5) NOT NULL,
  `Jenis_Kamar` varchar(50) DEFAULT NULL,
  `Nama_Kamar` varchar(30) NOT NULL,
  `Tarif` int(11) DEFAULT NULL,
  `FASILITAS` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kamar`
--

INSERT INTO `kamar` (`Kode_Kamar`, `Jenis_Kamar`, `Nama_Kamar`, `Tarif`, `FASILITAS`) VALUES
('K0001', 'Ekonomi', 'Mawar 1', 50000, 'Standart'),
('K0002', 'Ekonomi', 'Mawar II', 50000, 'Standart'),
('K0003', 'Ekonomi', 'Cempaka I', 50000, 'Standart'),
('K0004', 'Ekonomi', 'Cempaka II', 50000, 'Standart'),
('K0005', 'Suite', 'Melati I', 150000, 'Kulkas, TV'),
('K0006', 'Suite', 'Melati II', 150000, 'Kulkas, TV'),
('K0007', 'VIP', 'Anggrek I', 250000, 'Ruang VIP, Kulkas'),
('K0008', 'VIP', 'Anggrek II', 250000, 'Ruang VIP, Kulkas, TV');

-- --------------------------------------------------------

--
-- Table structure for table `kartu`
--

CREATE TABLE `kartu` (
  `no` int(11) NOT NULL,
  `id_pasien` varchar(20) NOT NULL,
  `nama_pasien` varchar(25) NOT NULL,
  `tempat_lahir` varchar(10) NOT NULL,
  `tanggal_lahir` varchar(20) NOT NULL,
  `usia` int(3) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `telepon` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kartu`
--

INSERT INTO `kartu` (`no`, `id_pasien`, `nama_pasien`, `tempat_lahir`, `tanggal_lahir`, `usia`, `jenis_kelamin`, `alamat`, `telepon`) VALUES
(1, 'P0001', 'Muhamad Fikri', 'Jakarta', '12 November 1993', 23, 'Perempuan', 'Jakarta', '089512072222'),
(2, 'P0002', 'Indah Dewilestari', 'Depok', '13 November 1995', 21, 'Perempuan', 'Depok', '089512072223'),
(3, 'P0003', 'Rasia Wicaksono', 'Bogor', '14 November 1994', 25, 'Perempuan', 'Bogor', '089512072224');

-- --------------------------------------------------------

--
-- Table structure for table `kasir`
--

CREATE TABLE `kasir` (
  `kode_transaksi` varchar(10) NOT NULL,
  `nama_pasien` varchar(50) NOT NULL,
  `kode_obat` varchar(20) NOT NULL,
  `nama_obat` varchar(50) NOT NULL,
  `merek_obat` varchar(50) NOT NULL,
  `harga_jual` int(11) NOT NULL,
  `jumlah_beli` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL,
  `biaya_perawatan` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `kembalian` int(11) NOT NULL,
  `Nama_Kamar` varchar(25) DEFAULT NULL,
  `Tarif` int(19) DEFAULT NULL,
  `Hari` int(3) NOT NULL,
  `Total_Tarif` int(11) NOT NULL,
  `Total_Biaya` int(25) NOT NULL,
  `tanggal` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kasir`
--

INSERT INTO `kasir` (`kode_transaksi`, `nama_pasien`, `kode_obat`, `nama_obat`, `merek_obat`, `harga_jual`, `jumlah_beli`, `total_harga`, `biaya_perawatan`, `bayar`, `kembalian`, `Nama_Kamar`, `Tarif`, `Hari`, `Total_Tarif`, `Total_Biaya`, `tanggal`) VALUES
('T0001', 'Retno', 'SF04', 'Flutamol', 'Paraxetamol PCG', 6500, 3, 19500, 50000, 270000, 500, 'Mawar 1', 50000, 3, 150000, 269500, NULL),
('T0002', 'Naya S', 'SF05', 'Sana Flu', 'Paracetamol P HCI', 6500, 3, 19500, 50000, 220000, 500, 'Mawar II', 50000, 2, 100000, 219500, NULL),
('T0003', 'Retno S', 'SF05', 'Sana Flu', 'Paracetamol P HCI', 6500, 3, 19500, 30000, 230000, 500, 'Mawar 1', 50000, 3, 150000, 229500, NULL),
('T0004', 'Nia N', 'SF06', 'Pi Kang Shuang 10gr', 'Pi Kang Shuang', 9000, 2, 18000, 50000, 220000, 2000, 'Mawar II', 50000, 2, 100000, 218000, '01-04-2022'),
('T0005', 'Naya S', 'SF03', 'Broadamx 500 mg', 'Amoksisilina Trihidrat 500mg', 15000, 2, 30000, 100000, 350000, 20000, 'Cempaka I', 50000, 2, 100000, 330000, '01-04-2022');

-- --------------------------------------------------------

--
-- Table structure for table `obat`
--

CREATE TABLE `obat` (
  `kode_obat` varchar(20) NOT NULL,
  `nama_obat` varchar(50) NOT NULL,
  `kategori_obat` varchar(15) NOT NULL,
  `jenis_obat` varchar(20) NOT NULL,
  `merek_obat` varchar(50) NOT NULL,
  `harga_beli_obat` decimal(10,0) NOT NULL,
  `harga_jual_obat` decimal(10,0) NOT NULL,
  `jumlah_obat` int(3) NOT NULL,
  `tanggal_masuk` text NOT NULL,
  `expired` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `obat`
--

INSERT INTO `obat` (`kode_obat`, `nama_obat`, `kategori_obat`, `jenis_obat`, `merek_obat`, `harga_beli_obat`, `harga_jual_obat`, `jumlah_obat`, `tanggal_masuk`, `expired`) VALUES
('SF01', 'Amoxcilin 100gr Generik', 'Obat Dalam', 'Kapsul', 'Amoxcilin 100gr', '8000', '10000', 100, '13 Desember 2021', '23 Maret 2025'),
('SF02', 'Farsien 50 gr', 'Obat Luar', 'Kapsul', 'Farsien 50 gr', '6000', '8000', 100, '13 Desember 2021', '23 Maret 2025'),
('SF03', 'Broadamx 500 mg', 'Obat Dalam', 'Kaplet', 'Amoksisilina Trihidrat 500mg', '12500', '15000', 99, '13 Desember 2021', '23 Maret 2025'),
('SF04', 'Flutamol', 'Obat Dalam', 'Kaplet', 'Paraxetamol PCG', '5000', '6500', 90, '13 Desember 2021', '23 Maret 2025'),
('SF05', 'Sana Flu', 'Obat Dalam', 'Kaplet', 'Paracetamol P HCI', '5000', '6500', 97, '13 Desember 2021', '23 Maret 2025'),
('SF06', 'Pi Kang Shuang 10gr', 'Obat Luar', 'Salep', 'Pi Kang Shuang', '5000', '9000', 100, '13 Desember 2021', '23 Maret 2025'),
('SF08', 'Sana Flu10gr', 'Obat Dalam', 'Kaplet', 'Sana Flu', '15000', '20000', 100, '13 Desember 2021', '23 Maret 2025'),
('SF10', 'Flutamol 110gr', 'Obat Dalam', 'Kaplet', 'Flutamol ', '5000', '7000', 99, '13 Desember 2021', '23 Maret 2025'),
('SF12', 'Flutamol 100gr', 'Obat Dalam', 'Kaplet', 'Flutamol ', '5000', '7000', 100, '13 Desember 2021', '23 Maret 2025');

-- --------------------------------------------------------

--
-- Table structure for table `pasien`
--

CREATE TABLE `pasien` (
  `id_pasien` varchar(20) NOT NULL,
  `nama_pasien` varchar(20) NOT NULL,
  `tempat_lahir` varchar(10) NOT NULL,
  `tanggal_lahir` varchar(20) NOT NULL,
  `usia` int(3) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `pekerjaan` varchar(30) NOT NULL,
  `No_Hp` varchar(15) NOT NULL,
  `poli` varchar(20) NOT NULL,
  `tanggal_masuk` text NOT NULL,
  `Nama_Kamar` varchar(25) DEFAULT NULL,
  `Tarif` decimal(25,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pasien`
--

INSERT INTO `pasien` (`id_pasien`, `nama_pasien`, `tempat_lahir`, `tanggal_lahir`, `usia`, `jenis_kelamin`, `alamat`, `pekerjaan`, `No_Hp`, `poli`, `tanggal_masuk`, `Nama_Kamar`, `Tarif`) VALUES
('P0001', 'Retno S', 'Bojonegoro', '23 Maret 2002', 19, 'Perempuan', 'Surabaya', 'Mahasiswa', '08928263738', 'Umum', '22 Desember 2021', 'Cempaka I', '50000'),
('P0002', 'Naya S', 'Gresik', '26 Oktober 2003', 19, 'Perempuan', 'Gresik', 'Mahasiswa', '08928263728', 'Umum', '22 Desember 2021', 'Anggrek I', '250000'),
('P0003', 'Nia N', 'Bojonegoro', '01-01-2022', 18, 'Perempuan', 'Bojonegoro', 'Pelajar', '08929283727', 'Umum', '01-01-2022', 'Mawar', '50000');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` varchar(11) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `jenis_kelamin` varchar(15) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `akses` varchar(15) NOT NULL,
  `alamat` text NOT NULL,
  `email` varchar(30) NOT NULL,
  `no_hp` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nama`, `jenis_kelamin`, `username`, `password`, `akses`, `alamat`, `email`, `no_hp`) VALUES
('U0001', 'Shofiuddin', 'Laki-Laki', 'Apoteker', 'apoteker', 'Apoteker', 'Blora', 'shofi@gmail.com', '082230883728'),
('U0003', 'Paijo Suwito', 'Laki-Laki', 'paijo', 'paijo', 'Admin', 'Sby', 'paijo@gmail.com', '0898283722');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `diagnosa`
--
ALTER TABLE `diagnosa`
  ADD PRIMARY KEY (`kode_diagnosa`);

--
-- Indexes for table `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`id_dokter`);

--
-- Indexes for table `kamar`
--
ALTER TABLE `kamar`
  ADD PRIMARY KEY (`Kode_Kamar`);

--
-- Indexes for table `kartu`
--
ALTER TABLE `kartu`
  ADD PRIMARY KEY (`id_pasien`),
  ADD KEY `id_pasien` (`id_pasien`),
  ADD KEY `no` (`no`);

--
-- Indexes for table `kasir`
--
ALTER TABLE `kasir`
  ADD PRIMARY KEY (`kode_transaksi`);

--
-- Indexes for table `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`kode_obat`),
  ADD UNIQUE KEY `kode_obat_2` (`kode_obat`),
  ADD UNIQUE KEY `kode_obat_3` (`kode_obat`),
  ADD UNIQUE KEY `kode_obat_4` (`kode_obat`),
  ADD UNIQUE KEY `kode_obat_5` (`kode_obat`),
  ADD UNIQUE KEY `kode_obat_7` (`kode_obat`),
  ADD UNIQUE KEY `kode_obat_8` (`kode_obat`),
  ADD KEY `kode_obat_6` (`kode_obat`);

--
-- Indexes for table `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`id_pasien`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
