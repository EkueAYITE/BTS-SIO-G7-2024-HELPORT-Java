-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : database:3306
-- Généré le : ven. 22 mars 2024 à 22:41
-- Version du serveur : 10.5.8-MariaDB-1:10.5.8+maria~focal
-- Version de PHP : 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bddHelpOrtJava`
--

-- --------------------------------------------------------

--
-- Structure de la table `competence`
--

CREATE TABLE `competence` (
  `id` int(11) NOT NULL,
  `id_matiere` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `sous_matiere` longtext NOT NULL,
  `statut` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `competence`
--

INSERT INTO `competence` (`id`, `id_matiere`, `id_user`, `sous_matiere`, `statut`) VALUES
(65, 23, 1, 'nombres', 1),
(66, 23, 1, 'gérondif', 1),
(67, 25, 7, 'pythagore', 1),
(68, 25, 7, 'vecteurs', 1),
(69, 25, 7, 'thalès', 1),
(70, 25, 7, 'integrales', 1),
(71, 26, 4, 'java', 1),
(72, 26, 4, 'python', 1),
(73, 24, 4, 'présent', 1),
(74, 24, 4, 'futur', 1),
(75, 24, 4, 'infinitif', 1),
(76, 24, 5, 'infinitif', 1),
(77, 26, 5, 'python', 1),
(78, 27, 6, 'nuageux', 1),
(79, 27, 1, 'nuageux', 1),
(80, 25, 1, 'pythagore', 1),
(81, 25, 1, 'thalès', 1),
(82, 23, 2, 'maison', 1),
(83, 26, 2, 'php', 1),
(84, 28, 1, 'opinel', 1),
(85, 28, 1, 'canif', 1),
(86, 28, 1, 'bowie ', 1),
(87, 28, 1, 'chasseur', 1),
(88, 28, 2, 'opinel', 1);

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

CREATE TABLE `demande` (
  `id` int(11) NOT NULL,
  `date_updated` date NOT NULL,
  `date_fin_demande` date NOT NULL,
  `sous_matiere` longtext NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_matiere` int(11) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `demande`
--

INSERT INTO `demande` (`id`, `date_updated`, `date_fin_demande`, `sous_matiere`, `id_user`, `id_matiere`, `status`) VALUES
(99, '2024-03-21', '2024-03-30', '[pythagore, vecteurs]', 1, 25, 1),
(100, '2024-03-21', '2024-03-27', '[java, python]', 1, 26, 1),
(101, '2024-03-21', '2024-04-07', '[présent, futur, infinitif]', 1, 24, 1),
(102, '2024-03-22', '2024-04-03', '[infinitif, pronoms]', 1, 24, 1),
(103, '2024-03-22', '2024-04-07', '[python, java, sql, php, php]', 1, 26, 1),
(104, '2024-03-22', '2024-04-07', '[ensoleillé, pluvieux, nuageux, venteux]', 9, 27, 1),
(105, '2024-03-22', '2024-03-27', '[pluvieux]', 3, 27, 1),
(106, '2024-03-22', '2024-04-06', '[pythagore, thalès]', 5, 25, 1),
(107, '2024-03-22', '2024-04-07', '[php]', 5, 26, 1),
(108, '2024-03-22', '2024-03-27', '[nourriture]', 1, 23, 1),
(109, '2024-03-22', '2024-03-26', '[passé, infinitif]', 2, 24, 1),
(110, '2024-03-22', '2024-04-03', '[nuageux]', 7, 27, 1),
(111, '2024-03-22', '2024-04-03', '[maison, nombres, gérondif]', 5, 23, 1),
(112, '2024-03-22', '2024-04-05', '[canif, survie]', 5, 28, 1);

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `id` int(11) NOT NULL,
  `designation` varchar(200) NOT NULL,
  `code` int(11) NOT NULL,
  `sous_matiere` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`id`, `designation`, `code`, `sous_matiere`) VALUES
(23, 'Anglais', 1, '#nombres#gérondif#maison#nourriture #test'),
(24, 'Français', 2, '#présent#futur#passé#infinitif#pronoms'),
(25, 'Mathématiques', 3, '#thalès#pythagore#vecteurs#integrales'),
(26, 'Informatique', 4, '#python#java#sql#php'),
(27, 'Météo', 5, '#ensoleillé#pluvieux#nuageux#venteux'),
(28, 'Couteau', 6, '#opinel#canif#athame#bowie #chasseur#survie');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

CREATE TABLE `salle` (
  `id` int(11) NOT NULL,
  `code_salle` varchar(10) NOT NULL,
  `etage` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`id`, `code_salle`, `etage`) VALUES
(105, 'Salle 105', 1),
(301, 'Salle 301', 3),
(506, 'Salle 506', 5),
(707, 'Salle 707', 7);

-- --------------------------------------------------------

--
-- Structure de la table `soutien`
--

CREATE TABLE `soutien` (
  `id` int(11) NOT NULL,
  `id_demande` int(11) NOT NULL,
  `id_competence` int(11) NOT NULL,
  `id_salle` int(11) NOT NULL,
  `date_du_soutien` date NOT NULL,
  `date_updated` date NOT NULL,
  `description` longtext NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `soutien`
--

INSERT INTO `soutien` (`id`, `id_demande`, `id_competence`, `id_salle`, `date_du_soutien`, `date_updated`, `description`, `status`) VALUES
(60, 99, 67, 301, '2024-03-30', '2024-03-21', '15h30', 3),
(63, 100, 71, 105, '2024-03-27', '2024-03-21', '12h30', 3),
(65, 101, 73, 301, '2024-04-07', '2024-03-21', '14h', 3),
(67, 102, 76, 105, '2024-04-03', '2024-03-22', '10h', 3),
(69, 103, 77, 506, '2024-04-07', '2024-03-22', 'qzsd', 3),
(71, 104, 78, 105, '2024-04-07', '2024-03-22', '10h', 3),
(73, 106, 67, 105, '2024-04-06', '2024-03-22', '10h', 3),
(75, 111, 82, 506, '2024-04-03', '2024-03-22', '19h', 3),
(77, 107, 83, 506, '2024-04-07', '2024-03-22', '19h', 3),
(79, 112, 88, 105, '2024-04-05', '2024-03-22', '15h', 3);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  `niveau` varchar(50) NOT NULL,
  `sexe` int(11) NOT NULL,
  `telephone` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `password`, `role`, `niveau`, `sexe`, `telephone`) VALUES
(1, 'Foujols', 'Benoit', 'benoit.foujols@ortmontreuil.fr', 'benfou', 'Etudiant', 'BTS 1', 1, '0661010101'),
(2, 'Pechberty', 'Julien', 'julien.pechberty@ortmontreuil.fr', 'julpec', 'Etudiant', 'BTS 2', 1, '0661020202'),
(3, 'Benyattou', 'Ounissa', 'ounissa.benyattou@ortmontreuil.fr', 'ounben', 'Etudiant', 'Bachelor', 2, '0661030303'),
(4, 'Hagege', 'Philippe', 'philippe.hagege@ortmontreuil.fr', 'phihag', 'Etudiant', 'Bachelor', 1, '0661040404'),
(5, 'Buffeteau', 'Jacques', 'jacques.buffeteau@ortmontreuil.fr', 'jacbuf', 'Etudiant', 'Terminale', 1, '0661050505'),
(6, 'Louis-Stokober', 'Laurence', 'laurence.louisstokober@ortmontreuil.fr', 'laulou', 'Etudiant', 'Master 1', 2, '0661060606'),
(7, 'Pastorelli', 'Justine', 'justine.pastorelli@ortmontreuil.fr', 'juspas', 'Etudiant', 'Master 2', 2, '0661070707'),
(8, 'Ronceux', 'Nicolas', 'nicolas.ronceux@ortmontreuil.fr', 'nicron', 'Etudiant', 'Bachelor', 1, '0661080808'),
(9, 'Attal', 'Jennifer', 'jennifer.attal@ortmontreuil.fr', 'jenatt', 'Etudiant', 'BTS 2', 2, '0661090909'),
(10, 'testAdmin', 'Admin', 'admin', 'admin', 'Admin', 'Master 1', 1, '0701020304');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `competence`
--
ALTER TABLE `competence`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user_competence` (`id_user`),
  ADD KEY `id_matiere_competence` (`id_matiere`);

--
-- Index pour la table `demande`
--
ALTER TABLE `demande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_matiere` (`id_matiere`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `salle`
--
ALTER TABLE `salle`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `soutien`
--
ALTER TABLE `soutien`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_competence` (`id_competence`),
  ADD KEY `id_demande` (`id_demande`),
  ADD KEY `id_salle_soutien` (`id_salle`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `competence`
--
ALTER TABLE `competence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- AUTO_INCREMENT pour la table `demande`
--
ALTER TABLE `demande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=113;

--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT pour la table `salle`
--
ALTER TABLE `salle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1000;

--
-- AUTO_INCREMENT pour la table `soutien`
--
ALTER TABLE `soutien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `competence`
--
ALTER TABLE `competence`
  ADD CONSTRAINT `id_matiere_competence` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id`),
  ADD CONSTRAINT `id_user_competence` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `id_matiere` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id`),
  ADD CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `soutien`
--
ALTER TABLE `soutien`
  ADD CONSTRAINT `id_competence` FOREIGN KEY (`id_competence`) REFERENCES `competence` (`id`),
  ADD CONSTRAINT `id_demande` FOREIGN KEY (`id_demande`) REFERENCES `demande` (`id`),
  ADD CONSTRAINT `id_salle_soutien` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
