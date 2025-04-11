-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 09, 2025 at 02:49 PM
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
DROP DATABASE IF EXISTS `bank`;
CREATE DATABASE IF NOT EXISTS `bank` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bank`;

-- --------------------------------------------------------

-- Tabela klienci
CREATE TABLE klienci (
    id_klienta INT PRIMARY KEY AUTO_INCREMENT,
    imie VARCHAR(50) NOT NULL,
    nazwisko VARCHAR(50) NOT NULL,
    adres TEXT NOT NULL,
    telefon VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    data_urodzenia DATE NOT NULL
);

-- Wstawianie klientów
INSERT INTO klienci (imie, nazwisko, adres, telefon, email, data_urodzenia) VALUES
('Jan', 'Kowalski', 'ul. Główna 10, Warszawa', '500123456', 'jan.kowalski@example.com', '1985-05-15'),
('Anna', 'Nowak', 'ul. Zielona 5, Kraków', '511654789', 'anna.nowak@example.com', '1990-07-20'),
('Marek', 'Wiśniewski', 'ul. Jasna 8, Wrocław', '600987123', 'marek.wisniewski@example.com', '1982-03-10'),
('Ewa', 'Zielińska', 'ul. Kwiatowa 12, Gdańsk', '601345678', 'ewa.zielinska@example.com', '1995-11-05'),
('Tomasz', 'Kamiński', 'ul. Szkolna 15, Poznań', '502876543', 'tomasz.kaminski@example.com', '1988-02-25');

-- Tabela konta
CREATE TABLE konta (
    id_konta INT PRIMARY KEY AUTO_INCREMENT,
    id_klienta INT NOT NULL,
    numer_konta VARCHAR(20) UNIQUE NOT NULL,
    typ_konta VARCHAR(20) NOT NULL,
    saldo DECIMAL(15, 2) DEFAULT 0.00,
    FOREIGN KEY (id_klienta) REFERENCES klienci(id_klienta) ON DELETE CASCADE
);

-- Wstawianie kont
INSERT INTO konta (id_klienta, numer_konta, typ_konta, saldo) VALUES
(1, 'PL12102010203040506012345678', 'Oszczędnościowe', 5000.00),
(2, 'PL78102010203040506087654321', 'Bieżące', 3000.00),
(3, 'PL90102010203040506011223344', 'Oszczędnościowe', 7000.00),
(4, 'PL34102010203040506044556677', 'Bieżące', 1500.00),
(5, 'PL45102010203040506077889900', 'Firmowe', 10000.00);

-- Tabela karty
CREATE TABLE karty (
    id_karty INT PRIMARY KEY AUTO_INCREMENT,
    id_konta INT NOT NULL,
    numer_karty VARCHAR(16) UNIQUE NOT NULL,
    termin_waznosci DATE NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    limit_karty DECIMAL(10, 2) DEFAULT 0.00,
    FOREIGN KEY (id_konta) REFERENCES konta (id_konta) ON DELETE CASCADE
);

-- Wstawianie kart
INSERT INTO karty (id_konta, numer_karty, termin_waznosci, cvv, limit_karty) VALUES
(1, '1234567812345678', '2027-12-31', '123', 2000.00),
(2, '2345678923456789', '2026-11-30', '456', 1500.00),
(3, '3456789034567890', '2028-10-31', '789', 3000.00),
(4, '4567890145678901', '2025-09-30', '012', 1000.00),
(5, '5678901256789012', '2029-08-31', '345', 5000.00);

-- Tabela transakcje
CREATE TABLE transakcje (
    id_transakcji INT PRIMARY KEY AUTO_INCREMENT,
    id_karty INT NOT NULL,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    kwota DECIMAL(15, 2) NOT NULL,
    typ_transakcji VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_karty) REFERENCES karty (id_karty) ON DELETE CASCADE
);

-- Wstawianie transakcji
INSERT INTO transakcje (id_karty, data, kwota, typ_transakcji) VALUES
(1, '2025-01-15 10:30:00', 250.00, 'Zakup w sklepie'),
(1, '2025-01-16 14:45:00', 100.00, 'Płatność online'),
(2, '2025-01-14 08:20:00', 300.00, 'Wypłata z bankomatu'),
(3, '2025-01-18 12:00:00', 500.00, 'Zakup w restauracji'),
(4, '2025-01-20 16:00:00', 200.00, 'Płatność online'),
(5, '2025-01-21 19:30:00', 1000.00, 'Zakup sprzętu komputerowego');

-- Struktura tabeli dla tabeli `blik`
CREATE TABLE blik (
  id_blik INT PRIMARY KEY AUTO_INCREMENT,
  kod_blik varchar(6) DEFAULT NULL,
  id_konta int DEFAULT NULL,
  FOREIGN KEY (id_konta) REFERENCES konta (id_konta) ON DELETE CASCADE
);

--
-- Dumping data for table `blik`
--

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `blik`
--

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `blik`
--

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
