-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 27 Cze 2024, 22:05
-- Wersja serwera: 10.4.11-MariaDB
-- Wersja PHP: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `parking`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `logi`
--

CREATE TABLE `logi` (
  `Rejestracja` varchar(60) NOT NULL,
  `Typ` varchar(60) NOT NULL,
  `Przyjechal` tinyint(1) NOT NULL,
  `Rzad` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `logi`
--

INSERT INTO `logi` (`Rejestracja`, `Typ`, `Przyjechal`, `Rzad`) VALUES
('afaf', 'Car', 1, 'Miejsca: rząd [0, 0] Kolumna [0, 1] '),
('afafg', 'Motorcycle', 1, 'Miejsca: rząd [0, 0] '),
('aga', 'Motorcycle', 1, 'Miejsca: rząd [0, 0] '),
('agad', 'Motorcycle', 1, 'Miejsca: rząd [0, 0] '),
('cc', 'Motorcycle', 1, 'Miejsca: rząd [0, 0] '),
('XD', 'Motorcycle', 1, 'Miejsca: rząd [0, 0] '),
('XDD', 'Motorcycle', 1, 'Miejsca: rząd [0, 0] ');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `logi`
--
ALTER TABLE `logi`
  ADD UNIQUE KEY `Rejestracja_2` (`Rejestracja`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
