-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Sob 10. dub 2021, 10:55
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
create schema if not exists `vutbrno` default character set utf8;
-- --------------------------------------------------------

--
-- Struktura tabulky `students`
--

CREATE TABLE IF NOT EXISTS `vutbrno`.`students` (
  `ID` int(16) NOT NULL,
  `Name` varchar(256) COLLATE utf8_czech_ci NOT NULL,
  `Surname` varchar(256) COLLATE utf8_czech_ci NOT NULL,
  `Birth` date NOT NULL,
  `Schoolership` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `students_&_teachers_relationship`
--

CREATE TABLE IF NOT EXISTS `vutbrno`.`students_&_teachers_relationship` (
  `ID` int(11) NOT NULL,
  `studentsID` int(11) NOT NULL,
  `teachersID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `students_score`
--

CREATE TABLE IF NOT EXISTS `vutbrno`.`students_score` (
  `ID` int(11) NOT NULL,
  `stuentsID` int(11) NOT NULL,
  `Grade` int(11) NOT NULL,
  `Subject` varchar(256) COLLATE utf8_czech_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `teachers`
--

CREATE TABLE IF NOT EXISTS `vutbrno`.`teachers` (
  `ID` int(11) NOT NULL,
  `Name` varchar(256) COLLATE utf8_czech_ci NOT NULL,
  `Surname` varchar(256) COLLATE utf8_czech_ci NOT NULL,
  `Birth` date NOT NULL,
  `FinancialFunds` int(255) NOT NULL,
  `Bonus` int(255) NOT NULL,
  `NoOfStudents` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Indexy pro exportované tabulky
--

--
-- Indexy pro tabulku `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`);

--
-- Indexy pro tabulku `students_&_teachers_relationship`
--
ALTER TABLE `students_&_teachers_relationship`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD KEY `studentsID` (`studentsID`,`teachersID`),
  ADD KEY `teachersID` (`teachersID`);

--
-- Indexy pro tabulku `students_score`
--
ALTER TABLE `students_score`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD KEY `stuentsID` (`stuentsID`);

--
-- Indexy pro tabulku `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`);

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
