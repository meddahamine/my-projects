create database sigmadb;

use sigmadb;
/*La tale des utilisateurs du système*/
CREATE TABLE `utilisateur` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `type` varchar(45),
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ;

INSERT INTO `utilisateur` (`user_id`, `username`, `password`, `type`) VALUES
(1, 'admin', 'admin@sigma', 'AdminSigma'),
(2, 'entite', 'entite@sigma', 'AdminEntite'),
(3, 'respachat', 'respachat@sigma', 'ResponsableAchat'),
(4, 'acheteur', 'acheteur@sigma', 'Acheteur'),
(5, 'contenu', 'contenu@sigma', 'AdminContenu'),
(6, 'Armani', 'Armani@sigma', 'Fournisseur'),
(7, 'Morgan', 'Morgan@sigma', 'Fournisseur'),
(8, 'DG', 'DG@sigma', 'Fournisseur'),
(9, 'Adidas', 'Adidas@sigma', 'Fournisseur'),
(10, 'Fila', 'Fila@sigma', 'Fournisseur'),
(11, 'Chanel', 'Chanel@sigma', 'Fournisseur'),
(12, 'Lacoste', 'Lacoste@sigma', 'Fournisseur'),
(13, 'LV', 'LV@sigma', 'Fournisseur'),
(14, 'PUMA', 'PUMA@sigma', 'Fournisseur'),
(15, 'Nike', 'Nike@sigma', 'Fournisseur'),
(16, 'SA', 'SA@sigma', 'Fournisseur'),
(17, 'Levis', 'Levis@sigma', 'Fournisseur'),
(18, 'DR', 'DR@sigma', 'Fournisseur'),
(19, 'BL', 'BL@sigma', 'Fournisseur'),
(20, 'BoyDavid', 'BoyDavid@sigma', 'Fournisseur'),
(21, 'GlobalOrganic', 'GlobalOrganic@sigma', 'Fournisseur'),
(22, 'Kiabi', 'Kiabi@sigma', 'Fournisseur'),
(23, 'Chantel', 'Chantel@sigma', 'Fournisseur'),
(24, 'LeCoq', 'LeCoq@sigma', 'Fournisseur'),
(25, 'Asos', 'Asos@sigma', 'Fournisseur'),
(26, 'WN', 'WN@sigma', 'Fournisseur'),
(27, 'Rad', 'Rad@sigma', 'Fournisseur'),
(28, 'Team', 'Team@sigma', 'Fournisseur'),
(29, 'NorthFrance', 'NorthFrance@sigma', 'Fournisseur');

/*La tale des admins*/
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(80) NOT NULL,
  `telephone` varchar(45) NOT NULL,
  PRIMARY KEY (`admin_id`),
  FOREIGN KEY (`admin_id`) REFERENCES utilisateur(`user_id`) 
		ON DELETE CASCADE
		ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ;

INSERT INTO `admin` (`admin_id`, `name`, `email`, `address`, `telephone`) VALUES
(1, 'Admin SIGMA', 'sigma@gmail.com', 'AdminSigma 76000', '075825852'),
(2, 'Admin Entite', 'entite@gmail.com', 'AdminEntite 76000', '075825852'),
(3, 'Responsable Achat', 'respachat@gmail.com', 'respachat 76000', '077485852'),
(4, 'Acheteur', 'acheteur@gmail.com', 'acheteur 76000', '077489992'),
(5, 'Admin Contenu', 'contenu@gmail.com', 'contenu 76000', '077431254');

/*La tale des founisseur*/
CREATE TABLE `fournisseur` (
  `fournisseur_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(80) NOT NULL,
  `telephone` varchar(45) NOT NULL,
  `num_siret` int(45) DEFAULT null,
  `code_ape` varchar(45) DEFAULT null,
  `libelle_code_ape` varchar(80) DEFAULT null,
  `raison_social` varchar(45) DEFAULT null,
  `nom_societe` varchar(45) DEFAULT null,
  `date_creation` int(11) DEFAULT null,
  `type_achat` varchar(45) DEFAULT null,
  `code_famille` int(45) DEFAULT null,
  `libelle_famille` varchar(80) DEFAULT null,
  `site_web` varchar(45) DEFAULT null,
  `adresse_societe` varchar(80) DEFAULT null,
  `code_postal` varchar(45) DEFAULT null,
  `ville_societe` varchar(45) DEFAULT null,
  `logo` varchar(45) DEFAULT 'nouveau.jpg',
  `score` int(2) DEFAULT 1,
  PRIMARY KEY (`fournisseur_id`),
  FOREIGN KEY (`fournisseur_id`) REFERENCES utilisateur(`user_id`) 
		ON DELETE CASCADE
		ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `fournisseur` (`fournisseur_id`, `name`, `email`, `address`, `telephone`, `logo`, `score`) VALUES
(6, 'Armani', 'Armani@armani.com', 'Rouen, France', '0 805 10 00 93', '1.jpg', 5),
(7, 'Morgan', 'Morgan@Morgan.com', 'Morgan', '02 35 07 67 65', '2.jpg',5),
(8, 'DG', 'DG@DG.com', 'Route de Ondelles, 76240 Belbeuf', '02 35 80 26 18', '3.jpg',4),
(9, 'Adidas', 'Adidas@Adidas.com', 'Docks 76, 76000 Rouen', '02 78 80 26 18', '4.jpg',7),
(10, 'Fila', 'Fila@Fila.com', '5 Avenue Lionel Terray, 69330 Meyzieu', '04 72 46 01 74', '5.jpg',6),
(11, 'Chanel', 'Chanel@Chanel.com', '95700 Roissy-en-France', '01 48 16 01 00', '6.jpg',4),
(12, 'Lacoste', 'Lacoste@Lacoste.com', '68 Rue Saint-Nicolas, 76000 Rouen', '02 35 98 67 51', '7.jpg',6),
(13, 'Louis Vuitton', 'LV@LV.com', '03 Rue Eugène Colas, 14800 Deauville', '02 31 88 65 88', '8.jpg',3),
(14, 'PUMA', 'PUMA@PUMA.com', 'ZAC du Trait d\'Union Marques, 78410 Aubergenville', '01 34 75 51 97', '9.jpg',7),
(15, 'Nike', 'Nike@Nike.com', '2 Rue de l\'Équerre, 95310 Saint-Ouen-l\'Aumône', '01 34 30 10 00', '10.jpg',7),
(16, 'sport akileine', 'SA@SA.com', 'Rouen, France', '02 05 10 00 93', '11.jpg',3),
(17, 'Levi\'s', 'Levis@Levis.com', 'Aéroville, Rue des Buissons, 95700 Roissy-en-France', '01 74 25 78 25', '12.jpg',6),
(18, 'DRAG RACING', 'DR@DR.com', '48 Avenue de la Dame Blanche, 33320 Le Taillan-Médoc', '06 07 37 48 48', '13.jpg',2),
(19, 'BL', 'BL@BL.com', 'Rouen, France', '01 05 10 00 93', '14.jpg',3),
(20, 'Boy David', 'BoyDavid@BoyDavid.com', 'Rouen, France', '01 22 10 00 93', '15.jpg',4),
(21, 'Global Organic', 'GlobalOrganic@GlobalOrganic.com', 'Rouen, France', '01 23 10 00 93', '16.jpg',4),
(22, 'Kiabi', 'Kiabi@Kiabi.com', 'Rouen, France', '01 24 10 00 93', '17.jpg',5),
(23, 'Chantel', 'Chantel@Chantel.com', 'Rouen, France', '01 25 10 00 93', '18.jpg',6),
(24, 'Le Coq', 'LeCoq@LeCoq.com', 'Rouen, France', '01 26 10 00 93', '19.jpg',6),
(25, 'Asos', 'Asos@Asos.com', 'Rouen, France', '01 27 10 00 93', '20.jpg',4),
(26, 'WN', 'WN@WN.com', 'Rouen, France', '01 28 10 00 93', '21.jpg',3),
(27, 'Rad', 'Rad@Rad.com', 'Rouen, France', '01 29 10 00 93', '22.jpg',3),
(28, 'Team', 'Team@Team.com', 'Rouen, France', '01 30 10 00 93', '23.jpg',4),
(29, 'The North France', 'NorthFrance@NorthFrance.com', 'Rouen, France', '01 31 10 00 93', '24.jpg',3);

/*La tale des files*/
CREATE TABLE `files` (
  `id_file` int(11) unsigned NOT NULL auto_increment,
  `filename` varchar(100) NOT NULL,
  `notes` varchar(100) default NULL,
  `type` varchar(40) default NULL,
  `file` longblob default NULL,
  `id_fournisseur` int(11) default NULL,
  PRIMARY KEY  (`id_file`),
  FOREIGN KEY (`id_fournisseur`) REFERENCES fournisseur(`fournisseur_id`) 
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

/*La tale des archive*/
CREATE TABLE `archive` (
  `archive_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(80) NOT NULL,
  `telephone` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`archive_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ;

/*La tale des entites*/
CREATE TABLE `entite` (
  `entite_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(80) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `metric` int(2) DEFAULT 5,
  PRIMARY KEY (`entite_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ;

INSERT INTO entite (`name`, `email`, `address`, `telephone`) VALUES ('SIGMA', 'sigma@rouen.fr', 'Rouen, France', '0255225486');
