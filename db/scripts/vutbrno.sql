-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Sob 10. dub 2021, 18:12
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
-- Databáze: `vutbrno`
--
DROP SCHEMA if EXISTS `vutbrno`;
Create schema if not exists `vutbrno` default character set utf8;
use `vutbrno`;
-- --------------------------------------------------------

--
-- Struktura tabulky `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE IF NOT EXISTS `students` (
  `ID` int(16) NOT NULL,
  `Name` varchar(256) COLLATE utf8_czech_ci NOT NULL,
  `Surname` varchar(256) COLLATE utf8_czech_ci NOT NULL,
  `Birth` date NOT NULL,
  `Schoolership` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `students_&_teachers_relationship`
--

DROP TABLE IF EXISTS `students_&_teachers_relationship`;
CREATE TABLE IF NOT EXISTS `students_&_teachers_relationship` (
  `ID` int(11) NOT NULL,
  `studentsID` int(11) NOT NULL,
  `teachersID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `studentsID` (`studentsID`,`teachersID`),
  KEY `teachersID` (`teachersID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `students_score`
--

DROP TABLE IF EXISTS `students_score`;
CREATE TABLE IF NOT EXISTS `students_score` (
  `ID` int(11) NOT NULL,
  `stuentsID` int(11) NOT NULL,
  `Grade` int(11) NOT NULL,
  `Subject` varchar(256) COLLATE utf8_czech_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `stuentsID` (`stuentsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `teachers`
--

DROP TABLE IF EXISTS `teachers`;
CREATE TABLE IF NOT EXISTS `teachers` (
  `ID` int(11) NOT NULL,
  `Name` varchar(256) COLLATE utf8_czech_ci NOT NULL,
  `Surname` varchar(256) COLLATE utf8_czech_ci NOT NULL,
  `Birth` date NOT NULL,
  `FinancialFunds` int(255) NOT NULL,
  `Bonus` int(255) NOT NULL,
  `NoOfStudents` int(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Omezení pro exportované tabulky
--

--
-- Omezení pro tabulku `students_&_teachers_relationship`
--
ALTER TABLE `students_&_teachers_relationship`
  ADD CONSTRAINT `students_&_teachers_relationship_ibfk_1` FOREIGN KEY (`studentsID`) REFERENCES `students` (`ID`),
  ADD CONSTRAINT `students_&_teachers_relationship_ibfk_2` FOREIGN KEY (`teachersID`) REFERENCES `teachers` (`ID`);

--
-- Omezení pro tabulku `students_score`
--
ALTER TABLE `students_score`
  ADD CONSTRAINT `students_score_ibfk_1` FOREIGN KEY (`stuentsID`) REFERENCES `students` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
