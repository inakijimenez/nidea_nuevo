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

-- Volcando datos para la tabla nidea.material: 16 rows
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT IGNORE INTO `material` (`id`, `nombre`, `precio`) VALUES
	(1, 'madera', 3.56),
	(2, 'acero', 12.90),
	(4, 'titanio', 27.00),
	(5, 'carton piedra', 6.00),
	(6, 'cristal', 6.00),
	(7, 'platino', 27.00),
	(21, 'yagermaist', 65.00),
	(9, 'plastilina', 24.00),
	(10, 'melamina', 5.20),
	(11, 'Tungsteno', 180.00),
	(12, 'Redstone', 6.00),
	(13, 'formica', 8.00),
	(14, 'hierro', 7.00),
	(15, 'polietileno', 80.00),
	(16, 'materiaDefectuoso', 1.25),
	(17, 'fibra de carbono', 90.00);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
