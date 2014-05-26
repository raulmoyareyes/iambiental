-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 19-05-2014 a las 17:12:43
-- Versión del servidor: 5.5.37
-- Versión de PHP: 5.3.10-1ubuntu3.11

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `iAmbiental`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actuadores`
--

CREATE TABLE IF NOT EXISTS `actuadores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dato` float NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `idFisico` int(11) NOT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `puerto` varchar(255) DEFAULT NULL,
  `tipo` int(11) NOT NULL,
  `dependencia_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sfkh6tahq38tdh7iidwyqkrfd` (`dependencia_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Disparadores `actuadores`
--
DROP TRIGGER IF EXISTS `historico_actuadores`;
DELIMITER //
CREATE TRIGGER `historico_actuadores` BEFORE UPDATE ON `actuadores`
 FOR EACH ROW BEGIN
    INSERT INTO historicoactuadores (id, dato, estado, fecha) 
	VALUES (NEW.id, NEW.dato, NEW.estado, NEW.fecha);
  END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dependencias`
--

CREATE TABLE IF NOT EXISTS `dependencias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historicoactuadores`
--

CREATE TABLE IF NOT EXISTS `historicoactuadores` (
  `id` int(11) DEFAULT NULL,
  `dato` double DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historicosensores`
--

CREATE TABLE IF NOT EXISTS `historicosensores` (
  `id` int(11) DEFAULT NULL,
  `dato` double DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reglasprogramadas`
--

CREATE TABLE IF NOT EXISTS `reglasprogramadas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `condicion` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `actuador_id` int(11) DEFAULT NULL,
  `sensor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gum4pd8nwic2utgtwwwmr85yd` (`actuador_id`),
  KEY `FK_8iu165qjak4jwamyl0pwbnyqc` (`sensor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reglassensoractuador`
--

CREATE TABLE IF NOT EXISTS `reglassensoractuador` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `actuador_id` int(11) DEFAULT NULL,
  `sensor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_l70njrbpvcawivkwdmom2ptkq` (`actuador_id`),
  KEY `FK_a9vhp74g1ls1kllb44xi61pxc` (`sensor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sensores`
--

CREATE TABLE IF NOT EXISTS `sensores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dato` float NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `idFisico` int(11) NOT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `puerto` varchar(255) DEFAULT NULL,
  `tipo` int(11) NOT NULL,
  `dependencia_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_75y3rt1yf948qlmm6ybx0n05m` (`dependencia_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Disparadores `sensores`
--
DROP TRIGGER IF EXISTS `historico_sensores`;
DELIMITER //
CREATE TRIGGER `historico_sensores` BEFORE UPDATE ON `sensores`
 FOR EACH ROW BEGIN
    INSERT INTO historicosensores (id, dato, estado, fecha) 
	VALUES (NEW.id, NEW.dato, NEW.estado, NEW.fecha);
  END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareasprogramadas`
--

CREATE TABLE IF NOT EXISTS `tareasprogramadas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareasprogramadas_reglasprogramadas`
--

CREATE TABLE IF NOT EXISTS `tareasprogramadas_reglasprogramadas` (
  `tareasprogramadas_id` int(11) NOT NULL,
  `reglasProgramadas_id` int(11) NOT NULL,
  PRIMARY KEY (`tareasprogramadas_id`,`reglasProgramadas_id`),
  UNIQUE KEY `UK_4hiboxrny8jfk7p2c8rlumj37` (`reglasProgramadas_id`),
  KEY `FK_4hiboxrny8jfk7p2c8rlumj37` (`reglasProgramadas_id`),
  KEY `FK_1hgrpi7jcjj3d4a378babxpp2` (`tareasprogramadas_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actuadores`
--
ALTER TABLE `actuadores`
  ADD CONSTRAINT `FK_sfkh6tahq38tdh7iidwyqkrfd` FOREIGN KEY (`dependencia_id`) REFERENCES `dependencias` (`id`);

--
-- Filtros para la tabla `reglasprogramadas`
--
ALTER TABLE `reglasprogramadas`
  ADD CONSTRAINT `FK_8iu165qjak4jwamyl0pwbnyqc` FOREIGN KEY (`sensor_id`) REFERENCES `sensores` (`id`),
  ADD CONSTRAINT `FK_gum4pd8nwic2utgtwwwmr85yd` FOREIGN KEY (`actuador_id`) REFERENCES `actuadores` (`id`);

--
-- Filtros para la tabla `reglassensoractuador`
--
ALTER TABLE `reglassensoractuador`
  ADD CONSTRAINT `FK_a9vhp74g1ls1kllb44xi61pxc` FOREIGN KEY (`sensor_id`) REFERENCES `sensores` (`id`),
  ADD CONSTRAINT `FK_l70njrbpvcawivkwdmom2ptkq` FOREIGN KEY (`actuador_id`) REFERENCES `actuadores` (`id`);

--
-- Filtros para la tabla `sensores`
--
ALTER TABLE `sensores`
  ADD CONSTRAINT `FK_75y3rt1yf948qlmm6ybx0n05m` FOREIGN KEY (`dependencia_id`) REFERENCES `dependencias` (`id`);

--
-- Filtros para la tabla `tareasprogramadas_reglasprogramadas`
--
ALTER TABLE `tareasprogramadas_reglasprogramadas`
  ADD CONSTRAINT `FK_1hgrpi7jcjj3d4a378babxpp2` FOREIGN KEY (`tareasprogramadas_id`) REFERENCES `tareasprogramadas` (`id`),
  ADD CONSTRAINT `FK_4hiboxrny8jfk7p2c8rlumj37` FOREIGN KEY (`reglasProgramadas_id`) REFERENCES `reglasprogramadas` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
