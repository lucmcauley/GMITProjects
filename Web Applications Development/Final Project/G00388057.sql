-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 19, 2021 at 09:48 PM
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
-- Database: `G00388057`
--
CREATE DATABASE IF NOT EXISTS `G00388057` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `G00388057`;

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `PID` int(11) NOT NULL,
  `PRODUCT` text NOT NULL,
  `PRICE` int(11) NOT NULL,
  `AVAILABLE_STOCK` int(11) NOT NULL,
  `IMAGE_SOURCE` text NOT NULL,
  `TRAILER_SRC` text NOT NULL,
  `PRODUCT_DESCRIPTION` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`PID`, `PRODUCT`, `PRICE`, `AVAILABLE_STOCK`, `IMAGE_SOURCE`, `TRAILER_SRC`, `PRODUCT_DESCRIPTION`) VALUES
(1, 'Carto', 20, 20, 'https://images.igdb.com/igdb/image/upload/t_cover_big/co2kf2.jpg', 'https://youtu.be/EdY9t3-q5qA', 'Carto is a tile placement adventure game where you explore the lands you placed and discover the secrets while exploring. What will happen to the world and the characters when you rearrange the tiles of the map?'),
(2, 'Super Mario 3D', 60, 500, 'https://images.igdb.com/igdb/image/upload/t_cover_big/co21vd.jpg', 'https://youtu.be/PwwjcfgTksU', 'Pounce and climb through dozens of colorful courses! Mario (and his friends) can use a variety of power-ups like the Super Bell, which grants catlike abilities like climbing and scratching.'),
(3, 'Doom Eternal', 30, 2, 'https://images.igdb.com/igdb/image/upload/t_cover_big/co1lvj.jpg', 'https://youtu.be/_UuktemkCFI', 'Hell’s armies have invaded Earth. Become the Slayer in an epic single-player campaign to conquer demons across dimensions and stop the final destruction of humanity. The only thing they fear... is you.'),
(4, 'Legend of Zelda Links Awakening 2019', 45, 30, 'https://images.igdb.com/igdb/image/upload/t_cover_big/co1qve.jpg', 'https://youtu.be/PtC6U8hOZTk', 'Link has washed ashore on a mysterious island with strange and colorful inhabitants. To escape the island, Link must collect magical instruments across the land and awaken the Wind Fish. Explore a reimagined Koholint Island that’s been faithfully rebuilt in a brand-new art style that will entice fans and newcomers alike.'),
(5, 'Halo The Master Chief Collection', 35, 15, 'https://images.igdb.com/igdb/image/upload/t_cover_big/co1t95.jpg', 'https://youtu.be/ZDvYJGquXgE', 'The improved and enhanced Halo: The Master Chief Collection is included with Xbox Game Pass. Featuring visual enhancements with up to 4K UHD resolution on Xbox One X, improved matchmaking, offline LAN, customizable installation options, and faster load times, it’s the definitive way to experience the ultimate Halo saga.'),
(6, 'Prison Architect', 25, 5, 'https://images.igdb.com/igdb/image/upload/t_cover_big/co2h9k.jpg', 'https://youtu.be/fBCW8svKOgE', 'The game is a top-down 2D construction and management simulation where the player takes control of building and running a prison. The player is responsible for managing various aspects of their prison including building cells and facilities, planning and connecting utilities, hiring and assigning staff, including a warden, guards, workers, and more.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`PID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `PID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
