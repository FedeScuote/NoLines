-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 15, 2014 at 08:31 PM
-- Server version: 5.5.31
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `noLines`
--
CREATE DATABASE IF NOT EXISTS `noLines` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `noLines`;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id_category` int(11) NOT NULL,
  `id_restaurant` int(11) NOT NULL,
  KEY `id_restaurant` (`id_restaurant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `local`
--

CREATE TABLE IF NOT EXISTS `local` (
  `name` varchar(50) NOT NULL,
  `logo` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `horario` varchar(20) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `id_local` varchar(45) NOT NULL,
  PRIMARY KEY (`id_local`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE IF NOT EXISTS `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(255) NOT NULL,
  `paying_account_number` varchar(255) NOT NULL,
  `restaurant_name` varchar(50) NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_order_user1_idx` (`user_email`),
  KEY `fk_order_paying_account1_idx` (`paying_account_number`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=74 ;

-- --------------------------------------------------------

--
-- Table structure for table `order_has_plate`
--

CREATE TABLE IF NOT EXISTS `order_has_plate` (
  `order_id` int(11) NOT NULL,
  `plate_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`order_id`,`plate_id`),
  KEY `fk_order_has_plate_plate1_idx` (`plate_id`),
  KEY `fk_order_has_plate_order1_idx` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `paying_account`
--

CREATE TABLE IF NOT EXISTS `paying_account` (
  `number` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`number`),
  KEY `fk_paying_account_user1_idx` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `plate`
--

CREATE TABLE IF NOT EXISTS `plate` (
  `plate_id` int(11) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `foto` varchar(200) DEFAULT NULL,
  `cook_time` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `id_restaurant` varchar(45) NOT NULL,
  PRIMARY KEY (`plate_id`,`id_restaurant`),
  KEY `fk_plate_restaurant1_idx` (`id_restaurant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `plate_has_type`
--

CREATE TABLE IF NOT EXISTS `plate_has_type` (
  `plate_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`plate_id`,`type_id`),
  KEY `fk_plate_has_type_type1_idx` (`type_id`),
  KEY `fk_plate_has_type_plate1_idx` (`plate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `restaurant`
--

CREATE TABLE IF NOT EXISTS `restaurant` (
  `id_restaurant` varchar(45) NOT NULL,
  PRIMARY KEY (`id_restaurant`),
  KEY `fk_restaurant_local1_idx` (`id_restaurant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop`
--

CREATE TABLE IF NOT EXISTS `shop` (
  `id_shop` varchar(45) NOT NULL,
  PRIMARY KEY (`id_shop`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `taste`
--

CREATE TABLE IF NOT EXISTS `taste` (
  `taste_id` int(11) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`taste_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `type`
--

CREATE TABLE IF NOT EXISTS `type` (
  `type_id` int(11) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `name` varchar(16) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_has_taste`
--

CREATE TABLE IF NOT EXISTS `user_has_taste` (
  `email` varchar(255) NOT NULL,
  `taste_id` int(11) NOT NULL,
  PRIMARY KEY (`email`,`taste_id`),
  KEY `fk_user_has_taste_taste1_idx` (`taste_id`),
  KEY `fk_user_has_taste_user1_idx` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `voucher`
--

CREATE TABLE IF NOT EXISTS `voucher` (
  `voucher_id` int(11) NOT NULL AUTO_INCREMENT,
  `discount` double DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `used_time` datetime DEFAULT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `id_local` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`voucher_id`,`id_local`),
  KEY `fk_voucher_local1_idx` (`id_local`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=154 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `fk_order_paying_account1` FOREIGN KEY (`paying_account_number`) REFERENCES `paying_account` (`number`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `order_has_plate`
--
ALTER TABLE `order_has_plate`
  ADD CONSTRAINT `fk_order_has_plate_order1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_order_has_plate_plate1` FOREIGN KEY (`plate_id`) REFERENCES `plate` (`plate_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `paying_account`
--
ALTER TABLE `paying_account`
  ADD CONSTRAINT `fk_paying_account_user1` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `plate`
--
ALTER TABLE `plate`
  ADD CONSTRAINT `fk_plate_restaurant1` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurant` (`id_restaurant`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `plate_has_type`
--
ALTER TABLE `plate_has_type`
  ADD CONSTRAINT `fk_plate_has_type_plate1` FOREIGN KEY (`plate_id`) REFERENCES `plate` (`plate_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_plate_has_type_type1` FOREIGN KEY (`type_id`) REFERENCES `type` (`type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `restaurant`
--
ALTER TABLE `restaurant`
  ADD CONSTRAINT `fk_restaurant_local1` FOREIGN KEY (`id_restaurant`) REFERENCES `local` (`id_local`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `shop`
--
ALTER TABLE `shop`
  ADD CONSTRAINT `fk_shop_local1` FOREIGN KEY (`id_shop`) REFERENCES `local` (`id_local`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_has_taste`
--
ALTER TABLE `user_has_taste`
  ADD CONSTRAINT `fk_user_has_taste_taste1` FOREIGN KEY (`taste_id`) REFERENCES `taste` (`taste_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_user_has_taste_user1` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `voucher`
--
ALTER TABLE `voucher`
  ADD CONSTRAINT `fk_voucher_local1` FOREIGN KEY (`id_local`) REFERENCES `local` (`id_local`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
