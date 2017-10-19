-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 20, 2016 at 11:03 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `springmybatis`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productCode` varchar(20) COLLATE utf8_unicode_ci NOT NULL PRIMARY KEY,
  `branchName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `price` tinyint(4) NOT NULL,
  `saleRank` tinyint(10) NOT NULL,
  `image1` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image2` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productCode`, `branchName`, `title`, `price`, `saleRank`, `image1`, `image2`) VALUES
('ACCP0101', 'Nguyen', 'Dai Toan', 1, '20-12-2000', '0905785651', '100 Nui Thanh'),
('ACCP0102', 'Nguyen', 'Van A', 1, '20-12-2000', '0905785651', '100 Nui Thanh'),
('ACCP0103', 'Nguyen', 'Dai B', 1, '20-12-2000', '0905785651', '100 Nui Thanh'),
('ACCP0104', 'Nguyen', 'Dai C', 1, '20-12-2000', '0905785651', '100 Nui Thanh'),
('ACCP0105', 'Nguyen', 'Dai E', 1, '20-12-2000', '0905785651', '100 Nui Thanh'),
('ACCP0106', 'Nguyen', 'Dai F', 1, '20-12-2000', '0905785651', '100 Nui Thanh'),
('ACCP0107', 'Nguyen', 'Dai G', 1, '20-12-2000', '0905785651', '100 Nui Thanh'),
('ACCP0108', 'Nguyen', 'Dai H', 1, '20-12-2000', '0905785651', '100 Nui Thanh'),
('ACCP0109', 'Nguyen', 'Dai I', 1, '20-12-2000', '0905785651', '100 Nui Thanh'),
('ACCP0110', 'Nguyen', 'Dai J', 1, '20-12-2000', '0905785651', '100 Nui Thanh'),
('ACCP0111', 'Nguyen', 'Dai L', 1, '20-12-2000', '0905785651', '100 Nui Thanh'),
('ACCP0112', 'Nguyen', 'Dai L', 1, '20-12-2000', '0905785651', '100 Nui Thanh'),
('ACCP0113', 'Nguyen', 'Dai M', 1, '20-12-2000', '0905785651', '100 Nui Thanh'),
('ACCP0114', 'Nguyen', 'Dai N', 1, '20-12-2000', '0905785651', '100 Nui Thanh');

