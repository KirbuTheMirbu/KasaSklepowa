-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Maj 30, 2025 at 01:23 PM
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
-- Struktura tabeli dla tabeli `blik`
--

CREATE TABLE `blik` (
  `id_blik` int(11) NOT NULL,
  `kod_blik` varchar(6) DEFAULT NULL,
  `id_konta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `karty`
--

CREATE TABLE `karty` (
  `id_karty` int(11) NOT NULL,
  `id_konta` int(11) NOT NULL,
  `numer_karty` varchar(16) NOT NULL,
  `termin_waznosci` date NOT NULL,
  `cvv` varchar(3) NOT NULL,
  `limit_karty` decimal(10,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `karty`
--

INSERT INTO `karty` (`id_karty`, `id_konta`, `numer_karty`, `termin_waznosci`, `cvv`, `limit_karty`) VALUES
(1, 1, '1234567812345678', '2027-12-31', '123', 2000.00),
(2, 2, '2345678923456789', '2026-11-30', '456', 1500.00),
(3, 3, '3456789034567890', '2028-10-31', '789', 3000.00),
(4, 4, '4567890145678901', '2025-09-30', '012', 1000.00),
(5, 5, '5678901256789012', '2029-08-31', '345', 5000.00);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klienci`
--

CREATE TABLE `klienci` (
  `id_klienta` int(11) NOT NULL,
  `imie` varchar(50) NOT NULL,
  `nazwisko` varchar(50) NOT NULL,
  `adres` text NOT NULL,
  `telefon` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `data_urodzenia` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `klienci`
--

INSERT INTO `klienci` (`id_klienta`, `imie`, `nazwisko`, `adres`, `telefon`, `email`, `data_urodzenia`) VALUES
(1, 'Jan', 'Kowalski', 'ul. Główna 10, Warszawa', '500123456', 'jan.kowalski@example.com', '1985-05-15'),
(2, 'Anna', 'Nowak', 'ul. Zielona 5, Kraków', '511654789', 'anna.nowak@example.com', '1990-07-20'),
(3, 'Marek', 'Wiśniewski', 'ul. Jasna 8, Wrocław', '600987123', 'marek.wisniewski@example.com', '1982-03-10'),
(4, 'Ewa', 'Zielińska', 'ul. Kwiatowa 12, Gdańsk', '601345678', 'ewa.zielinska@example.com', '1995-11-05'),
(5, 'Tomasz', 'Kamiński', 'ul. Szkolna 15, Poznań', '502876543', 'tomasz.kaminski@example.com', '1988-02-25');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `konta`
--

CREATE TABLE `konta` (
  `id_konta` int(11) NOT NULL,
  `id_klienta` int(11) NOT NULL,
  `numer_konta` varchar(20) NOT NULL,
  `typ_konta` varchar(20) NOT NULL,
  `saldo` decimal(15,2) DEFAULT 0.00,
  `login` varchar(255) DEFAULT NULL,
  `haslo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `konta`
--

INSERT INTO `konta` (`id_konta`, `id_klienta`, `numer_konta`, `typ_konta`, `saldo`, `login`, `haslo`) VALUES
(1, 1, 'PL121020102030405060', 'Oszczędnościowe', 9978.60, 'jan', 'jan123'),
(2, 2, 'PL781020102030405060', 'Bieżące', 3000.00, 'ania', 'anna345'),
(3, 3, 'PL901020102030405060', 'Oszczędnościowe', 7000.00, 'marek', 'marek567'),
(4, 4, 'PL341020102030405060', 'Bieżące', 1500.00, 'ewa', 'ewa789'),
(5, 5, 'PL451020102030405060', 'Firmowe', 10000.00, 'tomasz', 'tomek901');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `transakcje`
--

CREATE TABLE `transakcje` (
  `id_transakcji` int(11) NOT NULL,
  `id_karty` int(11) NOT NULL,
  `data` timestamp NOT NULL DEFAULT current_timestamp(),
  `kwota` decimal(15,2) NOT NULL,
  `typ_transakcji` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transakcje`
--

INSERT INTO `transakcje` (`id_transakcji`, `id_karty`, `data`, `kwota`, `typ_transakcji`) VALUES
(1, 1, '2025-01-15 10:30:00', 250.00, 'Zakup w sklepie'),
(2, 1, '2025-01-16 14:45:00', 100.00, 'Płatność online'),
(3, 2, '2025-01-14 08:20:00', 300.00, 'Wypłata z bankomatu'),
(4, 3, '2025-01-18 12:00:00', 500.00, 'Zakup w restauracji'),
(5, 4, '2025-01-20 16:00:00', 200.00, 'Płatność online'),
(6, 5, '2025-01-21 19:30:00', 1000.00, 'Zakup sprzętu komputerowego');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `blik`
--
ALTER TABLE `blik`
  ADD PRIMARY KEY (`id_blik`),
  ADD KEY `id_konta` (`id_konta`);

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
-- AUTO_INCREMENT for table `blik`
--
ALTER TABLE `blik`
  MODIFY `id_blik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `karty`
--
ALTER TABLE `karty`
  MODIFY `id_karty` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `klienci`
--
ALTER TABLE `klienci`
  MODIFY `id_klienta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `konta`
--
ALTER TABLE `konta`
  MODIFY `id_konta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `transakcje`
--
ALTER TABLE `transakcje`
  MODIFY `id_transakcji` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `blik`
--
ALTER TABLE `blik`
  ADD CONSTRAINT `blik_ibfk_1` FOREIGN KEY (`id_konta`) REFERENCES `konta` (`id_konta`) ON DELETE CASCADE;

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
--
-- Database: `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;
