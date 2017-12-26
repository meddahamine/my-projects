-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 13 Décembre 2017 à 23:08
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `tp3_jee`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE IF NOT EXISTS `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prix` double NOT NULL,
  `libelle` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `article`
--

INSERT INTO `article` (`id`, `prix`, `libelle`) VALUES
(1, 10, 'pc'),
(2, 100, 'test'),
(3, 25, 'album PNL'),
(4, 20, 'album BOOBA'),
(5, 23, 'album LACRIM');

-- --------------------------------------------------------

--
-- Structure de la table `contenu`
--

CREATE TABLE IF NOT EXISTS `contenu` (
  `id_panier` int(11) NOT NULL,
  `id_article` int(11) NOT NULL,
  KEY `FK38B7347A5AC96CE3` (`id_article`),
  KEY `FK38B7347A6E08FC5B` (`id_panier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `contenu`
--

INSERT INTO `contenu` (`id_panier`, `id_article`) VALUES
(1, 4);

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE IF NOT EXISTS `panier` (
  `id_panier` int(11) NOT NULL AUTO_INCREMENT,
  `nom_panier` varchar(50) NOT NULL,
  PRIMARY KEY (`id_panier`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `panier`
--

INSERT INTO `panier` (`id_panier`, `nom_panier`) VALUES
(1, 'p1'),
(2, 'p2');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `contenu`
--
ALTER TABLE `contenu`
  ADD CONSTRAINT `FKexco7mehu42mg8l3f3iapjnyl` FOREIGN KEY (`id_panier`) REFERENCES `panier` (`id_panier`),
  ADD CONSTRAINT `FK38B7347A5AC96CE3` FOREIGN KEY (`id_article`) REFERENCES `article` (`id`),
  ADD CONSTRAINT `FK38B7347A6E08FC5B` FOREIGN KEY (`id_panier`) REFERENCES `panier` (`id_panier`),
  ADD CONSTRAINT `FK7fa996nrrodxxup4p2k83xheo` FOREIGN KEY (`id_article`) REFERENCES `article` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
