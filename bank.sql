-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sty 22, 2025 at 01:56 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank`
--
CREATE DATABASE IF NOT EXISTS `bank` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bank`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `karty`
--

DROP TABLE IF EXISTS `karty`;
CREATE TABLE `karty` (
  `id_karty` int(11) NOT NULL,
  `id_konta` int(11) NOT NULL,
  `numer_karty` varchar(16) NOT NULL,
  `termin_waznosci` date NOT NULL,
  `cvv` char(3) NOT NULL,
  `limit_karty` double DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klienci`
--

DROP TABLE IF EXISTS `klienci`;
CREATE TABLE `klienci` (
  `id_klienta` int(11) NOT NULL,
  `imie` varchar(50) NOT NULL,
  `nazwisko` varchar(50) NOT NULL,
  `adres` text NOT NULL,
  `telefon` varchar(9) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `data_urodzenia` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `konta`
--

DROP TABLE IF EXISTS `konta`;
CREATE TABLE `konta` (
  `id_konta` int(11) NOT NULL,
  `id_klienta` int(11) NOT NULL,
  `numer_konta` varchar(20) NOT NULL,
  `typ_konta` varchar(100) NOT NULL,
  `saldo` double DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `transakcje`
--

DROP TABLE IF EXISTS `transakcje`;
CREATE TABLE `transakcje` (
  `id_transakcji` int(11) NOT NULL,
  `id_karty` int(11) NOT NULL,
  `data` timestamp NOT NULL DEFAULT current_timestamp(),
  `kwota` double NOT NULL,
  `typ_transakcji` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `karty`
--
ALTER TABLE `karty`
  ADD PRIMARY KEY (`id_karty`),
  ADD UNIQUE KEY `numer_karty` (`numer_karty`),
  ADD KEY `id_konta` (`id_konta`);

--
-- Indeksy dla tabeli `klienci`
--
ALTER TABLE `klienci`
  ADD PRIMARY KEY (`id_klienta`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indeksy dla tabeli `konta`
--
ALTER TABLE `konta`
  ADD PRIMARY KEY (`id_konta`),
  ADD UNIQUE KEY `numer_konta` (`numer_konta`),
  ADD KEY `id_klienta` (`id_klienta`);

--
-- Indeksy dla tabeli `transakcje`
--
ALTER TABLE `transakcje`
  ADD PRIMARY KEY (`id_transakcji`),
  ADD KEY `id_karty` (`id_karty`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `karty`
--
ALTER TABLE `karty`
  MODIFY `id_karty` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `klienci`
--
ALTER TABLE `klienci`
  MODIFY `id_klienta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `konta`
--
ALTER TABLE `konta`
  MODIFY `id_konta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transakcje`
--
ALTER TABLE `transakcje`
  MODIFY `id_transakcji` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `karty`
--
ALTER TABLE `karty`
  ADD CONSTRAINT `karty_ibfk_1` FOREIGN KEY (`id_konta`) REFERENCES `konta` (`id_konta`) ON DELETE CASCADE;

--
-- Constraints for table `konta`
--
ALTER TABLE `konta`
  ADD CONSTRAINT `konta_ibfk_1` FOREIGN KEY (`id_klienta`) REFERENCES `klienci` (`id_klienta`) ON DELETE CASCADE;

--
-- Constraints for table `transakcje`
--
ALTER TABLE `transakcje`
  ADD CONSTRAINT `transakcje_ibfk_1` FOREIGN KEY (`id_karty`) REFERENCES `karty` (`id_karty`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
