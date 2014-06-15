-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 15, 2014 at 08:32 PM
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
('Mc Donalds', 'WebServices/image/local/mcdonalds.png', 'Comida rapida', '8:00 a 00:00', 'Primer piso', '1'),
('Burger King', 'WebServices/image/local/burgerking.png', 'Comida Rapida', '8:15 a 00:00', 'Segundo piso', '2'),
('Subway', 'WebServices/image/local/subway.png', 'Cualquier variedad de sandwich!', '10:00 a 23:00', 'Primer piso', '3'),
('La Pasiva', 'WebServices/image/local/lapasiva.png', 'Panchos, Chivitos y mucho mas!', '12:00 a 00:00', 'Segundo piso', '4'),
('Taco Bell', 'WebServices/image/local/tacobell.png', 'Todo tipo de tacos!', '19:00 a 00:00', 'Segundo piso', '5'),
('Starbucks', 'WebServices/image/local/starbucks.png', 'Cafe, cafe!', '7:00 a 20:00', 'Primer piso', '6'),
('Lolita', 'WebServices/image/local/mcdonalds.png', 'Compra toda la ropa que quieras!', '11:00 a 22:00', 'Primer piso', '9');



--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`id_restaurant`) VALUES
('1'),
('2'),
('3'),
('4'),
('5'),
('6');


--
-- Dumping data for table `plate`
--

INSERT INTO `plate` (`plate_id`, `price`, `description`, `foto`, `cook_time`, `name`, `id_restaurant`) VALUES
(1, 195, 'Incluye hamburguesa, bebida y papas.', 'http://localhost/image/plate/mcdonalds1.png', 4, 'Combo Big Mc', '1'),
(2, 205, 'Incluye hamburguesa, bebida y papas.', 'http://localhost/image/plate/mcdonalds2.png', 5, 'Combo Cuarto de Libra', '1'),
(3, 45, 'La tradicional hamburguesa con queso que siempre te gusto!', 'http://localhost/image/plate/mcdonalds3.png', 3, 'Hamburguesa con queso', '1'),
(4, 45, 'Hamburguesa de pollo, lechuga y la mayonesa!', 'http://localhost/image/plate/mcdonalds4.png', 5, 'Mc Pollo', '1'),
(5, 210, 'Hamburguesa, bebida y papas!', 'http://localhost/image/plate/mcdonalds5.png', 6, 'Combo Doble Cuarto', '1'),
(6, 190, 'Incluye hamburguesa whopper jr, bebida y papas!', 'http://localhost/image/plate/burgerking1.png', 6, 'Combo Whopper Jr', '2'),
(7, 100, 'Hamburguesa Whopper', 'http://localhost/image/plate/burgerking2.png', 5, 'Whopper Jr', '2'),
(8, 90, 'Milkshake de dulce de leche', 'http://localhost/image/plate/starbucks1.png', 7, 'MilkShake', '6'),
(9, 50, 'Cafe caliente con vainilla, canela o chocolate a eleccion.', 'http://localhost/image/plate/starbucks2.png', 4, 'Cafe', '6'),
(11, 170, 'Sandwich con filete de carne cocido en horno con guacamole.', 'http://localhost/image/plate/subway1.png', 6, 'Guacamole Sandwich', '3'),
(12, 85, 'Un taco con gustos a eleccion', 'http://localhost/image/plate/tacobell1.png', 6, 'Taco', '5');

--
-- Dumping data for table `shop`
--

INSERT INTO `shop` (`id_shop`) VALUES
('9');

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`name`, `email`, `password`, `create_time`) VALUES
('nombre', 'algo@gmail.com', '*23AE809DDACAF96AF0FD78ED04B6A265E05AA257', '2014-06-09 09:05:39'),
('Martin', 'existe@gmail.com', '*23AE809DDACAF96AF0FD78ED04B6A265E05AA257', '2014-06-09 09:18:10'),
('hola', 'hola', '*AA803D048B666A933E512AA53B36C70174A37D1E', '2014-06-11 22:47:29'),
('jtejeria', 'jt.tejeria@gmail.com', '*23AE809DDACAF96AF0FD78ED04B6A265E05AA257', '2014-05-30 18:28:45');



--
-- Dumping data for table `paying_account`
--

INSERT INTO `paying_account` (`number`, `email`) VALUES
('123456', 'jt.tejeria@gmail.com');


--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id_category`, `id_restaurant`) VALUES
(1, 1),
(1, 2),
(1, 3),
(3, 4),
(4, 4),
(1, 5),
(1, 6);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
