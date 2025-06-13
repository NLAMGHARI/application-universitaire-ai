-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 13 juin 2025 à 17:40
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `dbensa1`
--

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `id` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id`, `password`, `username`) VALUES
(1, 'Dounia', 'Dounia@2023'),
(2, '123', 'ali'),
(3, '1234', 'rachid'),
(4, '12', 'nisrine');

-- --------------------------------------------------------

--
-- Structure de la table `element`
--

CREATE TABLE `element` (
  `id` bigint(20) NOT NULL,
  `coefficient` double DEFAULT NULL,
  `labelle` varchar(255) DEFAULT NULL,
  `module_id` bigint(20) NOT NULL,
  `is_valide` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `element`
--

INSERT INTO `element` (`id`, `coefficient`, `labelle`, `module_id`, `is_valide`) VALUES
(2, 0.5, 'spring', 1, b'0'),
(7, 0.5, 'merise', 2, b'1'),
(8, 0.5, 'oracle', 2, b'0'),
(9, 0.5, 'php', 2, b'0'),
(10, 0.5, 'javascript', 2, b'1'),
(11, 0.5, 'electronique', 1, b'0'),
(15, 0.5, 'electronique', 7, b'0'),
(16, 0.5, 'oracle', 8, b'0'),
(17, 2.5, 'merise', 3, b'0'),
(18, 2.5, 'merise', 3, b'0'),
(19, 2.5, 'merise', 4, b'0'),
(20, 2.5, 'merise', 4, b'0'),
(21, 2.5, 'spring', 6, b'0'),
(22, 2.5, 'oracle', 9, b'0'),
(23, 2.5, 'oracle', 9, b'0'),
(24, 89, 'kkkk', 37, b'0'),
(25, 90, 'hhhh', 40, b'0'),
(26, 2.5, 'oracle', 10, b'0'),
(27, 5.8, 'jjj', 37, b'0'),
(28, 3, 'springdata', 39, b'0'),
(29, 0.5, 'springsecurity', 39, b'0'),
(30, 9, 'jjjj', 40, b'0'),
(31, 3, 'optimisation', 38, b'0'),
(32, 0.5, 'springmvc', 42, b'0'),
(33, 0.5, 'springboot', 42, b'0'),
(34, 0.5, 'javaee', 41, b'0'),
(35, 0.5, 'jjjj', 41, b'0'),
(36, 0.5, 'elcronique', 43, b'0'),
(37, 0.5, 'poo', 43, b'0'),
(38, 0.5, 'electromagnetisme', 44, b'0'),
(39, 0.5, 'spring', 46, b'0'),
(40, 0.5, 'spring', 46, b'0'),
(41, 0.5, 'spring', 45, b'0'),
(42, 0.5, 'spring', 45, b'0'),
(43, 0.5, 'elfathi', 47, b'0'),
(44, 0.5, 'jvee', 48, b'0'),
(45, 0.5, 'spring', 48, b'0'),
(46, 0.5, 'mvc', 49, b'0'),
(47, 0.5, 'javafx', 49, b'0'),
(48, 0.5, 'JAVAEE', 50, b'0'),
(49, 0.5, 'Jva', 50, b'0');

-- --------------------------------------------------------

--
-- Structure de la table `element_etudiant`
--

CREATE TABLE `element_etudiant` (
  `is_absent` bit(1) DEFAULT NULL,
  `is_valide` bit(1) DEFAULT NULL,
  `note` double DEFAULT NULL,
  `element_id` bigint(20) NOT NULL,
  `etudiant_id` bigint(20) NOT NULL,
  `note_examen` double DEFAULT NULL,
  `note_projet` double DEFAULT NULL,
  `note_tp` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `element_etudiant`
--

INSERT INTO `element_etudiant` (`is_absent`, `is_valide`, `note`, `element_id`, `etudiant_id`, `note_examen`, `note_projet`, `note_tp`) VALUES
(b'0', b'1', 12, 2, 210001, NULL, NULL, NULL),
(b'0', b'0', 0, 2, 210002, NULL, NULL, NULL),
(b'0', b'1', 10, 2, 210003, NULL, NULL, NULL),
(b'0', b'0', 8, 2, 210004, NULL, NULL, NULL),
(b'0', b'1', 14, 2, 210005, NULL, NULL, NULL),
(b'0', b'1', 16, 2, 210006, NULL, NULL, NULL),
(b'0', b'0', 9, 2, 210007, NULL, NULL, NULL),
(b'0', b'1', 13, 2, 210008, NULL, NULL, NULL),
(b'0', b'1', 17, 2, 210009, NULL, NULL, NULL),
(b'0', b'0', 0, 2, 210010, NULL, NULL, NULL),
(b'0', b'0', 7, 2, 210011, NULL, NULL, NULL),
(b'0', b'0', 9.5, 2, 210012, NULL, NULL, NULL),
(b'0', b'1', 18, 2, 210013, NULL, NULL, NULL),
(b'0', b'1', 13, 2, 210014, NULL, NULL, NULL),
(b'0', b'1', 16, 2, 210015, NULL, NULL, NULL),
(b'0', b'1', 12, 2, 210016, NULL, NULL, NULL),
(b'0', b'1', 14, 2, 210017, NULL, NULL, NULL),
(b'0', b'1', 19, 2, 210018, NULL, NULL, NULL),
(b'0', b'1', 10.5, 2, 210019, NULL, NULL, NULL),
(b'0', b'0', 8.5, 2, 210020, NULL, NULL, NULL),
(b'0', b'1', 11.5, 2, 210021, NULL, NULL, NULL),
(b'0', b'0', 7.5, 2, 210022, NULL, NULL, NULL),
(b'0', b'1', 16, 2, 210023, NULL, NULL, NULL),
(b'0', b'1', 17.5, 2, 210024, NULL, NULL, NULL),
(b'0', b'1', 14.5, 2, 210025, NULL, NULL, NULL),
(b'0', b'1', 20, 7, 210001, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210002, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210003, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210004, NULL, NULL, NULL),
(b'0', b'0', 0, 7, 210005, NULL, NULL, NULL),
(b'0', b'0', 0, 7, 210006, NULL, NULL, NULL),
(b'0', b'1', 17, 7, 210007, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210008, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210009, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210010, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210011, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210012, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210013, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210014, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210015, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210016, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210017, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210018, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210019, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210020, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210021, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210022, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210023, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210024, NULL, NULL, NULL),
(b'0', b'1', 12, 7, 210025, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210001, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210002, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210003, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210004, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210005, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210006, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210007, NULL, NULL, NULL),
(b'0', b'0', 8, 8, 210008, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210009, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210010, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210011, NULL, NULL, NULL),
(b'0', b'0', 8, 8, 210012, NULL, NULL, NULL),
(b'0', b'1', 10, 8, 210013, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210014, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210015, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210016, NULL, NULL, NULL),
(b'0', b'0', 9, 8, 210017, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210018, NULL, NULL, NULL),
(b'0', b'0', 1, 8, 210019, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210020, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210021, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210022, NULL, NULL, NULL),
(b'0', b'0', 9, 8, 210023, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210024, NULL, NULL, NULL),
(b'0', b'1', 13, 8, 210025, NULL, NULL, NULL),
(b'0', b'0', NULL, 9, 210001, 7.8999999999999995, 18, 12.5),
(b'0', b'0', NULL, 9, 210002, 7.1, 17, 10),
(b'0', b'0', NULL, 9, 210003, 7.6, 16, 14),
(b'0', b'0', NULL, 9, 210004, 7.8, 16, 15),
(b'0', b'0', NULL, 9, 210005, 4.9, 9, 11);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `cne` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`cne`, `nom`, `prenom`) VALUES
(210001, 'El Idrissi', 'Ahmed'),
(210002, 'Benjelloun', 'Fatima'),
(210003, 'Alaoui', 'Hicham'),
(210004, 'Fassi', 'Khadija'),
(210005, 'Bennis', 'Youssef'),
(210006, 'Boukhriss', 'Sofia'),
(210007, 'Mahfoud', 'Rachid'),
(210008, 'Zouhri', 'Meryem'),
(210009, 'Omar', 'Yassine'),
(210010, 'Slaoui', 'Leila'),
(210011, 'Benali', 'Mohamed'),
(210012, 'Raji', 'Sara'),
(210013, 'El Fassi', 'Hassan'),
(210014, 'Bouazza', 'Samira'),
(210015, 'Haddad', 'Imane'),
(210016, 'Bentaleb', 'Ahmed'),
(210017, 'Mimoun', 'Zineb'),
(210018, 'Chbihi', 'Reda'),
(210019, 'Ait Brahim', 'Karim'),
(210020, 'Karim', 'Mouna'),
(210021, 'Jabri', 'Rachida'),
(210022, 'Berrada', 'Mohammed'),
(210023, 'Amrani', 'Fadwa'),
(210024, 'Sefiani', 'Marouane'),
(210025, 'El Hachimi', 'Khalil'),
(210026, 'Marrakchi', 'Ismail'),
(210027, 'Roudani', 'Souad'),
(210028, 'Kabbaj', 'Adil'),
(210029, 'Chakir', 'Zakia'),
(210030, 'Zahir', 'Omar'),
(210031, 'Boukili', 'Mouad'),
(210032, 'Karrouch', 'Mounia'),
(210033, 'El Hadi', 'Othmane'),
(210034, 'Tazi', 'Jamal'),
(210035, 'Mansouri', 'Kenza'),
(210036, 'Benkirane', 'Khalil'),
(210037, 'Azzouz', 'Fatimazahra'),
(210038, 'Salhi', 'Tariq'),
(210039, 'Dahbi', 'Sanae'),
(210040, 'Akhrif', 'Ilyas'),
(210041, 'El Ghazi', 'Nadia'),
(210042, 'Najim', 'Omar'),
(210043, 'Idrissi', 'Sana'),
(210044, 'El Kabbaj', 'Brahim'),
(210045, 'Sassi', 'Imane'),
(210046, 'Ghazali', 'Karima'),
(210047, 'Oumoussa', 'Ali'),
(210048, 'Bouikhlal', 'Samira'),
(210049, 'Mekouar', 'Zoulikha'),
(210050, 'Maalouf', 'Amine'),
(210051, 'Soudani', 'Rachid'),
(210052, 'Fahim', 'Anas'),
(210053, 'Benkiran', 'Siham'),
(210054, 'Ibrik', 'Chafik'),
(210055, 'Chouiter', 'Rania'),
(210056, 'Nadji', 'Mohammed'),
(210057, 'Douiri', 'Adil'),
(210058, 'El Beze', 'Soukaina'),
(210059, 'Zemmouri', 'Mohamed'),
(210060, 'Larbi', 'Badr'),
(210061, 'Ait Ouahi', 'Imane'),
(210062, 'Benchekroun', 'Mouad'),
(210063, 'Hakim', 'Rachida'),
(210064, 'Lahlou', 'Imane'),
(210065, 'Oulad Ziane', 'Hassan'),
(210066, 'Chabane', 'Fouad'),
(210067, 'Taybi', 'Yassir'),
(210068, 'Boukhlouf', 'Rachid'),
(210069, 'Foulane', 'Nassira'),
(210070, 'Ait Messaoud', 'Marwa'),
(210071, 'Douik', 'Rania'),
(210072, 'Sahbi', 'Ayman'),
(210073, 'El Khatib', 'Laila'),
(210074, 'Lakhdar', 'Azzedine'),
(210075, 'Slioua', 'Mouhssine'),
(210076, 'Ait Mouden', 'Mounir'),
(210077, 'Jellal', 'Fayçal'),
(210078, 'Baida', 'Hassan'),
(210079, 'Saidani', 'Khaled'),
(210080, 'Hassan', 'Samir'),
(210081, 'Chami', 'Mouad'),
(210082, 'Ghayt', 'Fahd'),
(210083, 'Mezouar', 'Rachid'),
(210084, 'El Fassi', 'Tayeb'),
(210085, 'Ahmad', 'Saïd'),
(210086, 'Naciri', 'Jamal'),
(210087, 'Ababou', 'Yassine'),
(210088, 'Ouadoudi', 'Ismail'),
(210089, 'Lahbabi', 'Samira'),
(210090, 'El Hachim', 'Rachida'),
(210091, 'Azouzi', 'Amine'),
(210092, 'Benouda', 'Ismail'),
(210093, 'Sefrioui', 'Karim'),
(210094, 'Fakih', 'Adil'),
(210095, 'Soufiane', 'Imane'),
(210096, 'Abdellah', 'Samia'),
(210097, 'Ait Mouloud', 'Hicham');

-- --------------------------------------------------------

--
-- Structure de la table `filiere`
--

CREATE TABLE `filiere` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `responsable` varchar(255) DEFAULT NULL,
  `sigle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `filiere`
--

INSERT INTO `filiere` (`id`, `nom`, `responsable`, `sigle`) VALUES
(14, 'Genie electrique', 'Maaider', 'GI'),
(15, 'Genie electrique', 'Maaider', 'GE'),
(16, 'Genie info et ingenierie des donnes', 'Nidal', 'IID');

-- --------------------------------------------------------

--
-- Structure de la table `filiere_module`
--

CREATE TABLE `filiere_module` (
  `num_semestre` int(11) DEFAULT NULL,
  `filiere_id` bigint(20) NOT NULL,
  `module_id` bigint(20) NOT NULL,
  `classe` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `filiere_module`
--

INSERT INTO `filiere_module` (`num_semestre`, `filiere_id`, `module_id`, `classe`) VALUES
(2, 14, 45, '2'),
(3, 14, 46, '3'),
(2, 14, 48, '2'),
(2, 14, 50, '2'),
(2, 15, 47, '2'),
(2, 16, 49, '2');

-- --------------------------------------------------------

--
-- Structure de la table `messages`
--

CREATE TABLE `messages` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `messages`
--

INSERT INTO `messages` (`id`, `description`, `nom`, `prenom`, `titre`) VALUES
(1, 'Ceci est un message d\'exemple.', 'Dupont', 'Jean', 'Bonjour');

-- --------------------------------------------------------

--
-- Structure de la table `modalite`
--

CREATE TABLE `modalite` (
  `id` int(11) NOT NULL,
  `examen` float NOT NULL,
  `projet` float NOT NULL,
  `tp` float NOT NULL,
  `element_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `modalite`
--

INSERT INTO `modalite` (`id`, `examen`, `projet`, `tp`, `element_id`) VALUES
(1, 50, 30, 20, 9),
(2, 9, 87, 78, 24),
(3, 8, 8, 7, 25),
(4, 50, 30, 20, 26),
(5, 9, 78, 78, 27),
(6, 8, 8, 4, 28),
(7, 50, 20, 30, 29),
(8, 9, 9, 9, 30),
(9, 9, 9, 88, 31),
(10, 25, 50, 25, 32),
(11, 50, 25, 25, 33),
(12, 50, 25, 2, 34),
(13, 25, 50, 25, 35),
(14, 25, 50, 25, 36),
(15, 50, 25, 25, 37),
(16, 50, 30, 20, 38),
(17, 50, 20, 30, 39),
(18, 50, 20, 30, 40),
(19, 50, 20, 30, 41),
(20, 50, 20, 30, 42),
(21, 25, 50, 25, 43),
(22, 50, 20, 30, 44),
(23, 50, 20, 30, 45),
(24, 50, 20, 30, 46),
(25, 50, 20, 30, 47),
(26, 50, 20, 30, 48),
(27, 50, 20, 30, 49);

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

CREATE TABLE `module` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `module`
--

INSERT INTO `module` (`id`, `code`, `nom`) VALUES
(1, 'INF101', 'Programmation Java'),
(2, 'info111', 'oracle'),
(3, 'info111', 'bigdata'),
(4, 'INF104', 'Développement mobile'),
(5, 'INF105', 'Architecture des systèmes'),
(6, 'INF106', 'Systèmes d’exploitation'),
(7, 'IID101', 'Introduction à l’intelligence artificielle'),
(8, 'IID102', 'Apprentissage automatique'),
(9, 'IID103', 'Big Data et Analyse des données'),
(10, 'IID104', 'Vision par ordinateur'),
(11, 'IID105', 'Traitement du langage naturel'),
(12, 'IID106', 'Systèmes experts et raisonnement automatisé'),
(13, 'GI101', 'Gestion de projet informatique'),
(14, 'GI102', 'Management des systèmes d’information'),
(15, 'GI103', 'Sécurité des systèmes informatiques'),
(16, 'GI104', 'Gestion de la qualité logicielle'),
(17, 'GI105', 'Planification de projet informatique'),
(18, 'GI106', 'Gestion des ressources informatiques'),
(19, 'GP101', 'Introduction au génie des projets'),
(20, 'GP102', 'Gestion des risques dans les projets'),
(21, 'GP103', 'Méthodologies de gestion de projet'),
(22, 'GP104', 'Outils et techniques de gestion de projet'),
(23, 'GP105', 'Suivi et contrôle de projet'),
(24, 'GP106', 'Évaluation des performances de projet'),
(25, 'IRIC101', 'Réseaux informatiques et sécurité'),
(26, 'IRIC102', 'Communication et réseaux sans fil'),
(27, 'IRIC103', 'Architecture des réseaux'),
(28, 'IRIC104', 'Réseaux distribués et cloud computing'),
(29, 'IRIC105', 'Protocoles de communication et TCP/IP'),
(30, 'IRIC106', 'Virtualisation et gestion des serveurs'),
(31, 'GE101', 'Introduction au génie électronique'),
(32, 'GE102', 'Systèmes embarqués'),
(33, 'GE103', 'Traitement du signal analogique et numérique'),
(34, 'GE104', 'Conception de circuits électroniques'),
(35, 'GE105', 'Microélectronique'),
(36, 'GE106', 'Systèmes de communication électronique'),
(37, NULL, 'javaee'),
(38, '', 'electr'),
(39, NULL, 'javaee'),
(40, '', 'jjjj'),
(41, '', 'jjjjjjjjjj'),
(42, '', 'kk'),
(43, '', 'numerique'),
(44, '', 'elcronique'),
(45, '', 'BI'),
(46, '', 'JEE'),
(47, '', 'electronique'),
(48, '', 'java'),
(49, '', 'javaee'),
(50, '', 'JAVA');

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

CREATE TABLE `professeur` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `specialite` varchar(255) DEFAULT NULL,
  `id_compte` int(11) DEFAULT NULL,
  `adressemail` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `professeur`
--

INSERT INTO `professeur` (`id`, `nom`, `prenom`, `specialite`, `id_compte`, `adressemail`) VALUES
(9, 'Nisrine', 'Essaidi', 'java', 4, NULL),
(10, 'essaidi', 'dounia', 'admin', 2, 'essaidi@gmail.com'),
(14, 'Gherrabi', 'Nouredine', 'java', NULL, 'gherrabi@gmail.com'),
(15, 'essaidi ', 'ali', 'data', NULL, 'ali@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `professeur_element`
--

CREATE TABLE `professeur_element` (
  `professeur_id` bigint(20) NOT NULL,
  `element_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `professeur_element`
--

INSERT INTO `professeur_element` (`professeur_id`, `element_id`) VALUES
(10, 31),
(9, 2),
(9, 7),
(9, 9),
(9, 10),
(9, 30),
(9, 33),
(9, 34),
(9, 37),
(9, 38),
(14, 43),
(14, 44),
(14, 46),
(14, 47),
(14, 49);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `element`
--
ALTER TABLE `element`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKd2r9fuwbev53lphktmq4qkcsx` (`module_id`);

--
-- Index pour la table `element_etudiant`
--
ALTER TABLE `element_etudiant`
  ADD PRIMARY KEY (`element_id`,`etudiant_id`),
  ADD KEY `FKqptdybw6q85yidiiyf3w30vap` (`etudiant_id`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`cne`);

--
-- Index pour la table `filiere`
--
ALTER TABLE `filiere`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `filiere_module`
--
ALTER TABLE `filiere_module`
  ADD PRIMARY KEY (`filiere_id`,`module_id`),
  ADD KEY `FKo3d42dow398o2ga7aetyjblnj` (`module_id`);

--
-- Index pour la table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `modalite`
--
ALTER TABLE `modalite`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2vcvcr6ql4nv4q5rraiqcjupc` (`element_id`);

--
-- Index pour la table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `professeur`
--
ALTER TABLE `professeur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKnjftj7x5p93e5tnss4p274cl3` (`id_compte`);

--
-- Index pour la table `professeur_element`
--
ALTER TABLE `professeur_element`
  ADD KEY `FKalh60qhbvqpkfua9su2214c9n` (`element_id`),
  ADD KEY `FK9jnmnfhvrp7wvmm96qne6ouku` (`professeur_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `element`
--
ALTER TABLE `element`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `cne` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=210098;

--
-- AUTO_INCREMENT pour la table `filiere`
--
ALTER TABLE `filiere`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `modalite`
--
ALTER TABLE `modalite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `module`
--
ALTER TABLE `module`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT pour la table `professeur`
--
ALTER TABLE `professeur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `element`
--
ALTER TABLE `element`
  ADD CONSTRAINT `FKd2r9fuwbev53lphktmq4qkcsx` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`);

--
-- Contraintes pour la table `element_etudiant`
--
ALTER TABLE `element_etudiant`
  ADD CONSTRAINT `FKqptdybw6q85yidiiyf3w30vap` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`cne`),
  ADD CONSTRAINT `FKtpljmmpe5c7wdvkw36u05o2pi` FOREIGN KEY (`element_id`) REFERENCES `element` (`id`);

--
-- Contraintes pour la table `filiere_module`
--
ALTER TABLE `filiere_module`
  ADD CONSTRAINT `FKeuyfuauyviayx0k0930mwd9pp` FOREIGN KEY (`filiere_id`) REFERENCES `filiere` (`id`),
  ADD CONSTRAINT `FKo3d42dow398o2ga7aetyjblnj` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`);

--
-- Contraintes pour la table `modalite`
--
ALTER TABLE `modalite`
  ADD CONSTRAINT `FK2vcvcr6ql4nv4q5rraiqcjupc` FOREIGN KEY (`element_id`) REFERENCES `element` (`id`);

--
-- Contraintes pour la table `professeur`
--
ALTER TABLE `professeur`
  ADD CONSTRAINT `FK8i7yotiry00smigy6a4nr24q7` FOREIGN KEY (`id_compte`) REFERENCES `compte` (`id`);

--
-- Contraintes pour la table `professeur_element`
--
ALTER TABLE `professeur_element`
  ADD CONSTRAINT `FK9jnmnfhvrp7wvmm96qne6ouku` FOREIGN KEY (`professeur_id`) REFERENCES `professeur` (`id`),
  ADD CONSTRAINT `FKalh60qhbvqpkfua9su2214c9n` FOREIGN KEY (`element_id`) REFERENCES `element` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
