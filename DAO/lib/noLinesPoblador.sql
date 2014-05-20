-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 20, 2014 at 04:15 AM
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

INSERT INTO `local` (`name`, `logo`, `description`, `horario`, `location`) VALUES
('Mc Donalds', NULL, 'Restaurante de comida rapida', '8:30 - 23:00', 'Primer piso de la plaza de comidas'),
('Taco Bell', NULL, 'Ven a buscar tu taco!', '11:00 a 01:00', 'Primer nivel en plaza de comidas!');


--
-- Dumping data for table `user`
--

INSERT INTO `user` (`name`, `email`, `password`, `create_time`) VALUES
('Juan', 'jt.tejeria@gmail.com', '*7D32D8F67BAEFCC3686C09F7900103702AE511EF', '2014-05-17 12:25:23');

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`name`) VALUES
('Mc Donalds'),
('Taco Bell');

--
-- Dumping data for table `plate`
--

INSERT INTO `plate` (`plate_id`, `price`, `description`, `foto`, `cook_time`, `name`, `restaurant_name`) VALUES
(1, 220, 'Hamburguesa con papas fritas', NULL, 20, 'McNifica', 'Mc Donalds');

--
-- Dumping data for table `type`
--

INSERT INTO `type` (`type_id`, `description`) VALUES
(1, 'Fast Food'),
(2, 'Mexican Food');

--
-- Dumping data for table `plate_has_type`
--

INSERT INTO `plate_has_type` (`plate_id`, `type_id`) VALUES
(1, 1);


--
-- Dumping data for table `taste`
--

INSERT INTO `taste` (`taste_id`, `description`) VALUES
(1, 'Exotica'),
(2, 'Rapida'),
(3, 'Musulman'),
(4, 'Arabe'),
(5, 'Pasta'),
(6, 'Vegetariana');

--
-- Dumping data for table `user_has_taste`
--

INSERT INTO `user_has_taste` (`email`, `taste_id`) VALUES
('jt.tejeria@gmail.com', 2),
('jt.tejeria@gmail.com', 3);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
