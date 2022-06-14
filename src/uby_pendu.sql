CREATE DATABASE IF NOT EXISTS `uby_pendu`;
USE `uby_pendu`;

DROP TABLE IF EXISTS `partie`;
CREATE TABLE IF NOT EXISTS `partie` (
    `id_partie` int(11) NOT NULL AUTO_INCREMENT,
    `mot_a_trouve` varchar(50),
    `mot_troue` varchar(50),
    `mot_test` varchar(50),
    `lettre_test` varchar(50),
    `coup` int(5),
    `ref_joueur1` int(11),
    `ref_joueur2` int(11),
    PRIMARY KEY (`id_partie`)
);

DROP TABLE IF EXISTS `joueur`;
CREATE TABLE IF NOT EXISTS `joueur` (
    `id_joueur` int(11) NOT NULL AUTO_INCREMENT,
    `nom` varchar(50),
    `mail` varchar(50),
    `mdp` varchar(50),
    PRIMARY KEY (`id_joueur`)
);

ALTER TABLE `partie`
    ADD CONSTRAINT `fk_partie_joueur1` FOREIGN KEY (`ref_joueur1`) REFERENCES `joueur`(`id_joueur`),
    ADD CONSTRAINT `fk_partie_joueur1` FOREIGN KEY (`ref_joueur1`) REFERENCES `joueur`(`id_joueur`);