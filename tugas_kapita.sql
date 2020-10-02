-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 02, 2020 at 09:26 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tugas_kapita`
--

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `Id` varchar(50) NOT NULL,
  `Nama` varchar(50) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `Stock` int(11) DEFAULT NULL,
  `Supplier_Id` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`Id`, `Nama`, `Price`, `Stock`, `Supplier_Id`) VALUES
('B01', 'Developer .Net', 90000, 11, 'SP03'),
('B02', 'Developer Java', 6000000, 10, 'SP02'),
('B03', 'Developer C#', 5500000, 10, 'SP04'),
('B04', 'Developer Python', 5500000, 10, 'SP02'),
('B05', 'Software Tester', 2000000, 20, 'SP02'),
('B06', 'Tester', 90000, 20, 'SP04'),
('B07', 'Dev Test', 90000, 40, 'SP04');

--
-- Triggers `item`
--
DELIMITER $$
CREATE TRIGGER `before_update_data` BEFORE UPDATE ON `item` FOR EACH ROW BEGIN
	INSERT INTO log_data_baru
		SET Nama=old.Nama,
		Price=old.Price,
		Stock=old.Stock;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `log_data_baru`
--

CREATE TABLE `log_data_baru` (
  `Nama` varchar(20) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `log_data_baru`
--

INSERT INTO `log_data_baru` (`Nama`, `Price`, `stock`) VALUES
(NULL, NULL, NULL),
('Developer .Net', 6000000, 11);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `Id` varchar(50) NOT NULL,
  `Nama` varchar(50) DEFAULT NULL,
  `JoinDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`Id`, `Nama`, `JoinDate`) VALUES
('SP01', 'PT. Synnex Metrodata Indonesia', '2018-01-15'),
('SP02', 'PT. Metrodata Indonesia', '2018-01-16'),
('SP03', 'PT. Mitra Informatika Indonesia', '2018-01-16'),
('SP04', 'PT. Metro Mobile', '2018-01-15');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `Id` varchar(50) NOT NULL,
  `OrderDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`Id`, `OrderDate`) VALUES
('101', '2012-10-10'),
('102', '2012-10-15'),
('103', '2012-11-05'),
('104', '2012-11-20'),
('105', '2012-11-30');

-- --------------------------------------------------------

--
-- Table structure for table `transaction_item`
--

CREATE TABLE `transaction_item` (
  `Id` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `Transaction_Id` varchar(50) DEFAULT NULL,
  `Item_Id` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction_item`
--

INSERT INTO `transaction_item` (`Id`, `Quantity`, `Transaction_Id`, `Item_Id`) VALUES
(1, 10, '101', 'B01'),
(2, 5, '101', 'B02'),
(3, 4, '102', 'B04'),
(4, 5, '103', 'B03'),
(5, 10, '104', 'B04'),
(6, 3, '105', 'B02');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Supplier_Id` (`Supplier_Id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `transaction_item`
--
ALTER TABLE `transaction_item`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Transaction_Id` (`Transaction_Id`),
  ADD KEY `Item_Id` (`Item_Id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`Supplier_Id`) REFERENCES `supplier` (`Id`);

--
-- Constraints for table `transaction_item`
--
ALTER TABLE `transaction_item`
  ADD CONSTRAINT `transaction_item_ibfk_1` FOREIGN KEY (`Transaction_Id`) REFERENCES `transaction` (`Id`),
  ADD CONSTRAINT `transaction_item_ibfk_2` FOREIGN KEY (`Item_Id`) REFERENCES `item` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
