-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Stř 14. dub 2021, 14:48
-- Verze serveru: 10.4.18-MariaDB
-- Verze PHP: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `projektpc2t`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE IF NOT EXISTS `students` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(256) COLLATE utf8mb4_czech_ci NOT NULL,
  `Surname` varchar(256) COLLATE utf8mb4_czech_ci NOT NULL,
  `Birth` date NOT NULL,
  `Scholarship` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_czech_ci;

--
-- Vypisuji data pro tabulku `students`
--

INSERT INTO `students` (`ID`, `Name`, `Surname`, `Birth`, `Scholarship`) VALUES
(2, 'John', 'Cena', '2000-05-22', 0);

-- --------------------------------------------------------

--
-- Struktura tabulky `students_&_teachers`
--

DROP TABLE IF EXISTS `students_&_teachers`;
CREATE TABLE IF NOT EXISTS `students_&_teachers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `StudentsID` int(11) NOT NULL,
  `TeachersID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `StudentsID` (`StudentsID`,`TeachersID`),
  KEY `TeachersID` (`TeachersID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_czech_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `students_score`
--

DROP TABLE IF EXISTS `students_score`;
CREATE TABLE IF NOT EXISTS `students_score` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `StudentsID` int(11) NOT NULL,
  `Grade` int(10) NOT NULL,
  `Subject` varchar(16) COLLATE utf8mb4_czech_ci NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `StudentsID` (`StudentsID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_czech_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `teachers`
--

DROP TABLE IF EXISTS `teachers`;
CREATE TABLE IF NOT EXISTS `teachers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(256) COLLATE utf8mb4_czech_ci NOT NULL,
  `Surname` varchar(256) COLLATE utf8mb4_czech_ci NOT NULL,
  `Birth` date NOT NULL,
  `Bonus` int(255) NOT NULL,
  `Pay` int(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_czech_ci;

--
-- Vypisuji data pro tabulku `teachers`
--

INSERT INTO `teachers` (`ID`, `Name`, `Surname`, `Birth`, `Bonus`, `Pay`) VALUES
(1, 'Jan', 'Komensky', '0000-00-00', 0, 15000);

--
-- Omezení pro exportované tabulky
--

--
-- Omezení pro tabulku `students_&_teachers`
--
ALTER TABLE `students_&_teachers`
  ADD CONSTRAINT `students_&_teachers_ibfk_1` FOREIGN KEY (`StudentsID`) REFERENCES `students` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `students_&_teachers_ibfk_2` FOREIGN KEY (`TeachersID`) REFERENCES `teachers` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Omezení pro tabulku `students_score`
--
ALTER TABLE `students_score`
  ADD CONSTRAINT `students_score_ibfk_1` FOREIGN KEY (`StudentsID`) REFERENCES `students` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
