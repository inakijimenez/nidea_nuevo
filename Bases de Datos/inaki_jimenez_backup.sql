-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.7.21-log - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para ett
DROP DATABASE IF EXISTS `ett`;
CREATE DATABASE IF NOT EXISTS `ett` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ett`;

-- Volcando estructura para tabla ett.empresa
DROP TABLE IF EXISTS `empresa`;
CREATE TABLE IF NOT EXISTS `empresa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla ett.empresa: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT IGNORE INTO `empresa` (`id`, `nombre`) VALUES
	(3, 'Acme'),
	(1, 'Barrio Sesamo'),
	(2, 'Ipartek');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;

-- Volcando estructura para tabla ett.url
DROP TABLE IF EXISTS `url`;
CREATE TABLE IF NOT EXISTS `url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla ett.url: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `url` DISABLE KEYS */;
INSERT IGNORE INTO `url` (`id`, `nombre`) VALUES
	(1, 'abc'),
	(2, 'ipartek'),
	(4, 'marca'),
	(3, 'ssi');
/*!40000 ALTER TABLE `url` ENABLE KEYS */;

-- Volcando estructura para tabla ett.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla ett.usuario: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT IGNORE INTO `usuario` (`id`, `nombre`) VALUES
	(2, 'agapito'),
	(4, 'espinete'),
	(3, 'iñaki'),
	(1, 'pepe');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Volcando estructura para tabla ett.usuario_empresa
DROP TABLE IF EXISTS `usuario_empresa`;
CREATE TABLE IF NOT EXISTS `usuario_empresa` (
  `id_usuario` int(11) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date DEFAULT NULL,
  PRIMARY KEY (`id_usuario`,`id_empresa`,`fecha_inicio`),
  KEY `fk_usuario_empresa_usuario1_idx` (`id_usuario`),
  KEY `fk_usuario_empresa_empresa1_idx` (`id_empresa`),
  CONSTRAINT `fk_usuario_empresa_empresa1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_empresa_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla ett.usuario_empresa: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario_empresa` DISABLE KEYS */;
INSERT IGNORE INTO `usuario_empresa` (`id_usuario`, `id_empresa`, `fecha_inicio`, `fecha_fin`) VALUES
	(1, 1, '1995-04-18', '2000-04-18'),
	(1, 3, '2000-04-19', '2017-05-12'),
	(2, 2, '2014-01-15', '2017-03-23'),
	(3, 2, '2017-03-08', NULL),
	(4, 1, '1986-01-18', '1992-04-22'),
	(4, 1, '2017-02-23', NULL),
	(4, 2, '1992-04-23', '2007-06-18'),
	(4, 3, '2009-09-15', '2016-02-24');
/*!40000 ALTER TABLE `usuario_empresa` ENABLE KEYS */;

-- Volcando estructura para tabla ett.usuario_url
DROP TABLE IF EXISTS `usuario_url`;
CREATE TABLE IF NOT EXISTS `usuario_url` (
  `id_usuario` int(11) NOT NULL,
  `id_url` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_url`),
  KEY `fk_usuario_has_url_url1_idx` (`id_url`),
  KEY `fk_usuario_has_url_usuario_idx` (`id_usuario`),
  CONSTRAINT `fk_usuario_has_url_url1` FOREIGN KEY (`id_url`) REFERENCES `url` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_url_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla ett.usuario_url: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario_url` DISABLE KEYS */;
INSERT IGNORE INTO `usuario_url` (`id_usuario`, `id_url`) VALUES
	(1, 1),
	(3, 1),
	(2, 2),
	(3, 3);
/*!40000 ALTER TABLE `usuario_url` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
