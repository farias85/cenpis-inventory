-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-06-2017 a las 18:13:38
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `inv`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `activo_fijo`
--

CREATE TABLE IF NOT EXISTS `activo_fijo` (
`id_activo_fijo` bigint(20) NOT NULL,
  `rotulo` varchar(18) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci NOT NULL,
  `valor_cuc` float(9,3) NOT NULL,
  `valor_mn` float(9,3) NOT NULL,
  `tasa` float(9,3) NOT NULL,
  `dep_acu_cuc` float(9,3) NOT NULL,
  `dep_acu_mn` float(9,3) NOT NULL,
  `valor_actual_cuc` float(9,3) NOT NULL,
  `valor_actual_mn` float(9,3) NOT NULL,
  `responsable_text` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `estado_text` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_estado_actual` date NOT NULL,
  `revision` bigint(20) NOT NULL,
  `estado` bigint(20) NOT NULL,
  `responsable` bigint(20) NOT NULL,
  `local` bigint(20) NOT NULL,
  `tipo_activo` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5633 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apunte`
--

CREATE TABLE IF NOT EXISTS `apunte` (
`id_apunte` int(11) NOT NULL,
  `rotulo` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `fecha` date NOT NULL,
  `asunto` char(100) COLLATE latin1_spanish_ci NOT NULL,
  `observacion` text COLLATE latin1_spanish_ci NOT NULL,
  `usuario` bigint(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auditoria`
--

CREATE TABLE IF NOT EXISTS `auditoria` (
`id_auditoria` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `hora` datetime NOT NULL,
  `rotulo` varchar(18) COLLATE latin1_spanish_ci NOT NULL,
  `activo_antes` text COLLATE latin1_spanish_ci NOT NULL,
  `activo_despues` text COLLATE latin1_spanish_ci NOT NULL,
  `usuario` bigint(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `chequeo`
--

CREATE TABLE IF NOT EXISTS `chequeo` (
`id_chequeo` int(11) NOT NULL,
  `apunte` int(11) NOT NULL,
  `tipo_resultado` int(11) NOT NULL,
  `informe` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE IF NOT EXISTS `estado` (
`id_estado` bigint(20) NOT NULL,
  `nombre` varchar(200) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `informe`
--

CREATE TABLE IF NOT EXISTS `informe` (
`id_informe` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `completado` tinyint(1) NOT NULL,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `local`
--

CREATE TABLE IF NOT EXISTS `local` (
`id_local` bigint(20) NOT NULL,
  `nombre` varchar(200) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metadata`
--

CREATE TABLE IF NOT EXISTS `metadata` (
`id_metadata` bigint(20) NOT NULL,
  `total_activos` int(11) NOT NULL,
  `valor_total` float(9,3) NOT NULL,
  `valor_total_mc` float(9,3) NOT NULL,
  `dep_acu_total` float(9,3) NOT NULL,
  `dep_acu_total_mc` float(9,3) NOT NULL,
  `elaborado` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  `responsable` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  `revisado` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  `revision` bigint(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `responsable`
--

CREATE TABLE IF NOT EXISTS `responsable` (
`id_responsable` bigint(20) NOT NULL,
  `nombre` varchar(200) COLLATE latin1_spanish_ci NOT NULL,
  `email` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `revision`
--

CREATE TABLE IF NOT EXISTS `revision` (
`id_revision` bigint(20) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `fecha_en_sistema` date NOT NULL,
  `fecha_excel` date NOT NULL,
  `excel_url` varchar(500) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE IF NOT EXISTS `rol` (
`id_rol` bigint(20) NOT NULL,
  `nombre` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sobrante`
--

CREATE TABLE IF NOT EXISTS `sobrante` (
`id_sobrante` bigint(20) NOT NULL,
  `rotulo` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `descripcion` text COLLATE latin1_spanish_ci NOT NULL,
  `responsable` bigint(20) NOT NULL,
  `local` bigint(20) NOT NULL,
  `estado` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_activo`
--

CREATE TABLE IF NOT EXISTS `tipo_activo` (
`id_tipo_activo` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_resultado`
--

CREATE TABLE IF NOT EXISTS `tipo_resultado` (
`id_tipo_resultado` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
`id_usuario` bigint(20) NOT NULL,
  `nombre` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `apellidos` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `email` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `contrasenna` varchar(200) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_rol`
--

CREATE TABLE IF NOT EXISTS `usuario_rol` (
  `id_usuario` bigint(20) NOT NULL,
  `id_rol` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `activo_fijo`
--
ALTER TABLE `activo_fijo`
 ADD PRIMARY KEY (`id_activo_fijo`), ADD KEY `Refrevision2` (`revision`), ADD KEY `Refestado12` (`estado`), ADD KEY `Refresponsable14` (`responsable`), ADD KEY `Reflocal15` (`local`), ADD KEY `tipo_activo` (`tipo_activo`);

--
-- Indices de la tabla `apunte`
--
ALTER TABLE `apunte`
 ADD PRIMARY KEY (`id_apunte`), ADD KEY `Refusuario27` (`usuario`);

--
-- Indices de la tabla `auditoria`
--
ALTER TABLE `auditoria`
 ADD PRIMARY KEY (`id_auditoria`), ADD KEY `Refusuario16` (`usuario`);

--
-- Indices de la tabla `chequeo`
--
ALTER TABLE `chequeo`
 ADD PRIMARY KEY (`id_chequeo`), ADD KEY `Refapunte24` (`apunte`), ADD KEY `Reftipo_resultado25` (`tipo_resultado`), ADD KEY `Refinforme26` (`informe`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
 ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `informe`
--
ALTER TABLE `informe`
 ADD PRIMARY KEY (`id_informe`);

--
-- Indices de la tabla `local`
--
ALTER TABLE `local`
 ADD PRIMARY KEY (`id_local`);

--
-- Indices de la tabla `metadata`
--
ALTER TABLE `metadata`
 ADD PRIMARY KEY (`id_metadata`), ADD KEY `Refrevision3` (`revision`);

--
-- Indices de la tabla `responsable`
--
ALTER TABLE `responsable`
 ADD PRIMARY KEY (`id_responsable`);

--
-- Indices de la tabla `revision`
--
ALTER TABLE `revision`
 ADD PRIMARY KEY (`id_revision`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
 ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `sobrante`
--
ALTER TABLE `sobrante`
 ADD PRIMARY KEY (`id_sobrante`), ADD KEY `Refresponsable31` (`responsable`), ADD KEY `Reflocal32` (`local`), ADD KEY `Refestado33` (`estado`);

--
-- Indices de la tabla `tipo_activo`
--
ALTER TABLE `tipo_activo`
 ADD PRIMARY KEY (`id_tipo_activo`);

--
-- Indices de la tabla `tipo_resultado`
--
ALTER TABLE `tipo_resultado`
 ADD PRIMARY KEY (`id_tipo_resultado`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
 ADD PRIMARY KEY (`id_usuario`);

--
-- Indices de la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
 ADD PRIMARY KEY (`id_usuario`,`id_rol`), ADD KEY `Refrol22` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `activo_fijo`
--
ALTER TABLE `activo_fijo`
MODIFY `id_activo_fijo` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5633;
--
-- AUTO_INCREMENT de la tabla `apunte`
--
ALTER TABLE `apunte`
MODIFY `id_apunte` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `auditoria`
--
ALTER TABLE `auditoria`
MODIFY `id_auditoria` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `chequeo`
--
ALTER TABLE `chequeo`
MODIFY `id_chequeo` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
MODIFY `id_estado` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `informe`
--
ALTER TABLE `informe`
MODIFY `id_informe` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `local`
--
ALTER TABLE `local`
MODIFY `id_local` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `metadata`
--
ALTER TABLE `metadata`
MODIFY `id_metadata` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=48;
--
-- AUTO_INCREMENT de la tabla `responsable`
--
ALTER TABLE `responsable`
MODIFY `id_responsable` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT de la tabla `revision`
--
ALTER TABLE `revision`
MODIFY `id_revision` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=50;
--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
MODIFY `id_rol` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `sobrante`
--
ALTER TABLE `sobrante`
MODIFY `id_sobrante` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tipo_activo`
--
ALTER TABLE `tipo_activo`
MODIFY `id_tipo_activo` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT de la tabla `tipo_resultado`
--
ALTER TABLE `tipo_resultado`
MODIFY `id_tipo_resultado` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
MODIFY `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `activo_fijo`
--
ALTER TABLE `activo_fijo`
ADD CONSTRAINT `Refestado12` FOREIGN KEY (`estado`) REFERENCES `estado` (`id_estado`),
ADD CONSTRAINT `Reflocal15` FOREIGN KEY (`local`) REFERENCES `local` (`id_local`),
ADD CONSTRAINT `Refresponsable14` FOREIGN KEY (`responsable`) REFERENCES `responsable` (`id_responsable`),
ADD CONSTRAINT `Refrevision2` FOREIGN KEY (`revision`) REFERENCES `revision` (`id_revision`),
ADD CONSTRAINT `activo_fijo_fk` FOREIGN KEY (`tipo_activo`) REFERENCES `tipo_activo` (`id_tipo_activo`);

--
-- Filtros para la tabla `apunte`
--
ALTER TABLE `apunte`
ADD CONSTRAINT `Refusuario27` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `auditoria`
--
ALTER TABLE `auditoria`
ADD CONSTRAINT `Refusuario16` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `chequeo`
--
ALTER TABLE `chequeo`
ADD CONSTRAINT `Refapunte24` FOREIGN KEY (`apunte`) REFERENCES `apunte` (`id_apunte`),
ADD CONSTRAINT `Refinforme26` FOREIGN KEY (`informe`) REFERENCES `informe` (`id_informe`),
ADD CONSTRAINT `Reftipo_resultado25` FOREIGN KEY (`tipo_resultado`) REFERENCES `tipo_resultado` (`id_tipo_resultado`);

--
-- Filtros para la tabla `metadata`
--
ALTER TABLE `metadata`
ADD CONSTRAINT `Refrevision3` FOREIGN KEY (`revision`) REFERENCES `revision` (`id_revision`);

--
-- Filtros para la tabla `sobrante`
--
ALTER TABLE `sobrante`
ADD CONSTRAINT `Refestado33` FOREIGN KEY (`estado`) REFERENCES `estado` (`id_estado`),
ADD CONSTRAINT `Reflocal32` FOREIGN KEY (`local`) REFERENCES `local` (`id_local`),
ADD CONSTRAINT `Refresponsable31` FOREIGN KEY (`responsable`) REFERENCES `responsable` (`id_responsable`);

--
-- Filtros para la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
ADD CONSTRAINT `Refrol22` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`),
ADD CONSTRAINT `Refusuario21` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
