-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 24, 2014 at 04:48 PM
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

--
-- Dumping data for table `local`
--

INSERT INTO `local` (`name`, `logo`, `description`, `horario`, `location`, `id_local`) VALUES
('Mc Donalds', 'http://localhost/image/local/mcdonalds.png', 'Comida rapida', '8:00 a 00:00', 'Primer piso', '1'),
('Burger King', 'http://localhost/image/local/burgerking.png', 'Comida Rapida', '8:15 a 00:00', 'Segundo piso', '2');

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`id_restaurant`) VALUES
('1'),
('2');


--
-- Dumping data for table `plate`
--

INSERT INTO `plate` (`plate_id`, `price`, `description`, `foto`, `cook_time`, `name`, `id_restaurant`) VALUES
(1, 195, 'Big Mc con papas y bebida.', 'http://localhost/image/plate/mcdonalds1.png', 4, 'Combo Big Mc', '1'),
(2, 205, 'Cuarto de libra con papas y bebida.', 'http://localhost/image/plate/mcdonalds2.png', 5, 'Combo Cuarto de Libra', '1');



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
