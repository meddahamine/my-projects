-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 23 Juin 2017 à 02:47
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `projetweb`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL DEFAULT '0',
  `nom` varchar(250) NOT NULL,
  `prenom` varchar(250) NOT NULL,
  `username` varchar(250) NOT NULL,
  `mdp` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `admin`
--

INSERT INTO `admin` (`id`, `nom`, `prenom`, `username`, `mdp`) VALUES
(1, 'admin', 'admin', 'admin', '1234');

-- --------------------------------------------------------

--
-- Structure de la table `cat`
--

CREATE TABLE IF NOT EXISTS `cat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `cat`
--

INSERT INTO `cat` (`id`, `name`) VALUES
(6, 'mathematique'),
(7, 'informatique'),
(11, 'science');

-- --------------------------------------------------------

--
-- Structure de la table `d`
--

CREATE TABLE IF NOT EXISTS `d` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` text CHARACTER SET utf8 NOT NULL,
  `contenu` text CHARACTER SET utf8 NOT NULL,
  `iduser` int(11) NOT NULL,
  `idcat` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Contenu de la table `d`
--

INSERT INTO `d` (`id`, `titre`, `contenu`, `iduser`, `idcat`) VALUES
(9, 'd1', '', 39, 0),
(10, 'd2', '', 39, 0),
(11, 'lff', 'lflfrl', 39, 0),
(12, 'doc1', 'abs1', 38, 0),
(13, 'dlfkdlfk', 'lsfkddslfk', 38, 0),
(14, 'WikiLeaks', '<span style="color: rgb(34, 34, 34); font-family: arial, sans-serif; font-size: 13px;">WikiLeaks est une organisation non-gouvernementale fondÃ©e par Julian Assange en 2006</span>', 38, 0);

-- --------------------------------------------------------

--
-- Structure de la table `s`
--

CREATE TABLE IF NOT EXISTS `s` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(250) CHARACTER SET utf8 NOT NULL,
  `contenu` text CHARACTER SET utf8 NOT NULL,
  `iddoc` int(11) NOT NULL,
  `idsec` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `idssec` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `iddoc` (`iddoc`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Contenu de la table `s`
--

INSERT INTO `s` (`id`, `titre`, `contenu`, `iddoc`, `idsec`, `idssec`) VALUES
(6, 'sec1', '', 9, 'text_section0', NULL),
(7, 'sec1', '', 10, 'text_section0', NULL),
(8, 'section 1', '', 11, 'text_section0', NULL),
(9, 'sec11', '', 11, 'text_section0', 'div_ssection0'),
(10, 'section 2', '', 11, 'text_section1', NULL),
(11, 'sous_section 2.1', '', 11, 'text_section1', 'div_ssection1'),
(12, 'section 1', 'contenu section 1&nbsp;', 12, 'text_section0', NULL),
(13, 'sous section 11', 'contenu ssec11', 12, 'text_section0', 'div_ssection0'),
(14, 'soussection 12', 'contenu ssec 12', 12, 'text_section0', 'div_ssection1'),
(15, 'section 2', 'contenu section 2', 12, 'text_section1', NULL),
(16, 'sous section 21', 'sontenu ssec 21', 12, 'text_section1', 'div_ssection2'),
(17, 'section 3', 'contenu section 3', 12, 'text_section2', NULL),
(18, 'sous section 31', 'contenu ssec 31', 12, 'text_section2', 'div_ssection3'),
(19, 'sous section 32', 'contenu ssec 3.2', 12, 'text_section2', 'div_ssection4'),
(20, 'sexxxxx1', 'dekfkedfkpekfpekfep<div>sexxxxx1<br></div>', 13, 'text_section0', NULL),
(21, 'sssexxxx1', 'dfokdlfkdlfkdlfk<div>ssssexxxxx1<br></div>', 13, 'text_section0', 'div_ssection0'),
(22, 'sexxxx2', 'dlfkdlfkldfkld<div>sexxxxx2<br></div>', 13, 'text_section1', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=50 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `name`, `username`, `password`, `email`) VALUES
(38, 'amine meddah', 'a', 'a', 'a@a.a'),
(39, 'b', 'b', 'b', 'b@b.b'),
(49, 'meddah amine', 'amine', 'moi', 'moi@gmail.com');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
