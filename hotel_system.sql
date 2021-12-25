-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 25, 2021 at 03:37 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_system`
--
CREATE DATABASE IF NOT EXISTS `hotel_system` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `hotel_system`;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--
-- Creation: Dec 25, 2021 at 11:50 AM
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `CID` int(11) NOT NULL,
  `CNAME` varchar(32) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `password` varchar(30) NOT NULL,
  `CNATIONALITY` varchar(32) DEFAULT NULL,
  `CGENDER` varchar(32) DEFAULT NULL,
  `CBIRTH` date DEFAULT NULL,
  `CPHONE` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `client`:
--

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`CID`, `CNAME`, `email`, `password`, `CNATIONALITY`, `CGENDER`, `CBIRTH`, `CPHONE`) VALUES
(211864444, 'Jamal Mohammad', 'jamal20@gmail.com', '123', 'Egyptian', 'male', '2000-11-20', '0247168346'),
(211867844, 'Sara Murad', 'saram@gmail.com', '1234', 'Egyptian', 'female', '1997-11-20', '0247168346'),
(211869008, 'Rana Issa', 'rana@gmail.com', '123', 'Egyptian', 'female', '2002-11-20', '0247168346'),
(211869202, 'Ahmad Ali', 'ahmadali@gmail.com', '123', 'Palestinian', 'male', '1996-10-10', '0569794857');

-- --------------------------------------------------------

--
-- Table structure for table `client_bookhall`
--
-- Creation: Dec 25, 2021 at 11:50 AM
--

DROP TABLE IF EXISTS `client_bookhall`;
CREATE TABLE `client_bookhall` (
  `BookNO` int(11) NOT NULL,
  `ARRIVALDATE` date DEFAULT NULL,
  `DEPARTMENTDATE` date DEFAULT NULL,
  `HNO` int(11) DEFAULT NULL,
  `ClientID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `client_bookhall`:
--   `ClientID`
--       `client` -> `CID`
--   `HNO`
--       `hall` -> `HNUM`
--

-- --------------------------------------------------------

--
-- Table structure for table `client_bookroom`
--
-- Creation: Dec 25, 2021 at 11:50 AM
--

DROP TABLE IF EXISTS `client_bookroom`;
CREATE TABLE `client_bookroom` (
  `BookNO` int(11) NOT NULL,
  `ARRIVALDATE` date DEFAULT NULL,
  `DEPARTMENTDATE` date DEFAULT NULL,
  `RNO` int(11) DEFAULT NULL,
  `ClientID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `client_bookroom`:
--   `RNO`
--       `room` -> `RNO`
--   `ClientID`
--       `client` -> `CID`
--

--
-- Dumping data for table `client_bookroom`
--

INSERT INTO `client_bookroom` (`BookNO`, `ARRIVALDATE`, `DEPARTMENTDATE`, `RNO`, `ClientID`) VALUES
(1, '2021-12-31', '2022-01-12', 1, 211864444),
(2, '2021-12-29', '2021-12-31', 3, 211867844);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--
-- Creation: Dec 25, 2021 at 11:50 AM
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `EID` int(11) NOT NULL,
  `EPHONENO` varchar(40) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `eage` date DEFAULT NULL,
  `ENamE` varchar(40) DEFAULT NULL,
  `EGENDER` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `employee`:
--

-- --------------------------------------------------------

--
-- Table structure for table `hall`
--
-- Creation: Dec 25, 2021 at 12:23 PM
--

DROP TABLE IF EXISTS `hall`;
CREATE TABLE `hall` (
  `HNUM` int(11) NOT NULL,
  `isOccupied` varchar(10) DEFAULT NULL,
  `HNAME` varchar(32) DEFAULT NULL,
  `HPRICE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `hall`:
--

-- --------------------------------------------------------

--
-- Table structure for table `reciept`
--
-- Creation: Dec 25, 2021 at 12:22 PM
--

DROP TABLE IF EXISTS `reciept`;
CREATE TABLE `reciept` (
  `cid` int(11) NOT NULL,
  `hallTotal` double DEFAULT NULL,
  `roomTotal` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `hasPaid` varchar(10) DEFAULT NULL,
  `paymentDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `reciept`:
--   `cid`
--       `client` -> `CID`
--

-- --------------------------------------------------------

--
-- Table structure for table `room`
--
-- Creation: Dec 25, 2021 at 11:50 AM
--

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `RNO` int(11) NOT NULL,
  `rprice` double DEFAULT NULL,
  `isOccupied` varchar(1) DEFAULT NULL,
  `RFLOOR` int(11) DEFAULT NULL,
  `RTYPE` varchar(10) DEFAULT NULL,
  `isClean` varchar(1) DEFAULT NULL,
  `bedsNum` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `room`:
--

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`RNO`, `rprice`, `isOccupied`, `RFLOOR`, `RTYPE`, `isClean`, `bedsNum`) VALUES
(1, 100, 'y', 2, 'single', 'y', 1),
(2, 100, 'n', 2, 'single', 'n', 1),
(3, 250, 'y', 3, 'double', 'y', 2),
(4, 500, 'n', 5, 'suite', 'y', 3),
(5, 500, 'n', 5, 'false', 'y', 3),
(6, 100, 'n', 3, 'true', 'n', 1),
(7, 250, 'y', 2, 'double', 'y', 3),
(8, 250, 'n', 3, 'double', 'n', 2);

-- --------------------------------------------------------

--
-- Table structure for table `room_amenties`
--
-- Creation: Dec 25, 2021 at 12:47 PM
-- Last update: Dec 25, 2021 at 12:48 PM
--

DROP TABLE IF EXISTS `room_amenties`;
CREATE TABLE `room_amenties` (
  `RA_ID` int(11) NOT NULL,
  `RNO` int(11) DEFAULT NULL,
  `hasWifi` varchar(1) DEFAULT NULL,
  `hasFreeBreakfast` varchar(1) DEFAULT NULL,
  `hasAC` varchar(1) DEFAULT NULL,
  `hasTV` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `room_amenties`:
--   `RNO`
--       `room` -> `RNO`
--

--
-- Dumping data for table `room_amenties`
--

INSERT INTO `room_amenties` (`RA_ID`, `RNO`, `hasWifi`, `hasFreeBreakfast`, `hasAC`, `hasTV`) VALUES
(1, 1, 'y', 'y', 'y', 'y'),
(2, 2, 'y', 'n', 'y', ''),
(3, 3, 'y', 'y', 'y', 'y'),
(4, 4, 'n', 'n', 'n', 'n');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`CID`);

--
-- Indexes for table `client_bookhall`
--
ALTER TABLE `client_bookhall`
  ADD PRIMARY KEY (`BookNO`),
  ADD KEY `ClientID` (`ClientID`),
  ADD KEY `HNO` (`HNO`);

--
-- Indexes for table `client_bookroom`
--
ALTER TABLE `client_bookroom`
  ADD PRIMARY KEY (`BookNO`),
  ADD KEY `RNO` (`RNO`),
  ADD KEY `ClientID` (`ClientID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`EID`);

--
-- Indexes for table `hall`
--
ALTER TABLE `hall`
  ADD PRIMARY KEY (`HNUM`);

--
-- Indexes for table `reciept`
--
ALTER TABLE `reciept`
  ADD PRIMARY KEY (`cid`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`RNO`);

--
-- Indexes for table `room_amenties`
--
ALTER TABLE `room_amenties`
  ADD PRIMARY KEY (`RA_ID`),
  ADD KEY `RNO` (`RNO`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `client_bookhall`
--
ALTER TABLE `client_bookhall`
  ADD CONSTRAINT `client_bookhall_ibfk_1` FOREIGN KEY (`ClientID`) REFERENCES `client` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `client_bookhall_ibfk_2` FOREIGN KEY (`HNO`) REFERENCES `hall` (`HNUM`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `client_bookroom`
--
ALTER TABLE `client_bookroom`
  ADD CONSTRAINT `client_bookroom_ibfk_1` FOREIGN KEY (`RNO`) REFERENCES `room` (`RNO`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `client_bookroom_ibfk_2` FOREIGN KEY (`ClientID`) REFERENCES `client` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reciept`
--
ALTER TABLE `reciept`
  ADD CONSTRAINT `reciept_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `client` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `room_amenties`
--
ALTER TABLE `room_amenties`
  ADD CONSTRAINT `room_amenties_ibfk_1` FOREIGN KEY (`RNO`) REFERENCES `room` (`RNO`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
