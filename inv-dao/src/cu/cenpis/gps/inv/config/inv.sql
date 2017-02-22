# SQL Manager 2007 for MySQL 4.1.2.1
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : inv


SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE `inv`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_spanish_ci';

USE `inv`;

#
# Structure for the `estado` table : 
#

CREATE TABLE `estado` (
  `id_estado` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci,
  PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Structure for the `local` table : 
#

CREATE TABLE `local` (
  `id_local` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci,
  PRIMARY KEY (`id_local`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Structure for the `responsable` table : 
#

CREATE TABLE `responsable` (
  `id_responsable` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) COLLATE latin1_spanish_ci NOT NULL,
  `email` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci,
  PRIMARY KEY (`id_responsable`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Structure for the `revision` table : 
#

CREATE TABLE `revision` (
  `id_revision` bigint(20) NOT NULL AUTO_INCREMENT,
  `latest` tinyint(4) NOT NULL,
  `fecha` date NOT NULL,
  `excel` varchar(500) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id_revision`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Structure for the `activo_fijo` table : 
#

CREATE TABLE `activo_fijo` (
  `id_activo_fijo` bigint(20) NOT NULL AUTO_INCREMENT,
  `rotulo` bigint(20) NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci NOT NULL,
  `valor_cuc` float(8,0) NOT NULL,
  `valor_mn` float(8,0) NOT NULL,
  `tasa` float(8,0) NOT NULL,
  `dep_acu_cuc` float(8,0) NOT NULL,
  `dep_acu_mn` float(8,0) NOT NULL,
  `valor_actual_cuc` float(8,0) NOT NULL,
  `valor_actual_mn` float(8,0) NOT NULL,
  `responsable_text` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `estado_text` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `id_revision` bigint(20) NOT NULL,
  `id_estado` bigint(20) NOT NULL,
  `id_responsable` bigint(20) NOT NULL,
  `id_local` bigint(20) NOT NULL,
  PRIMARY KEY (`id_activo_fijo`),
  KEY `Refrevision2` (`id_revision`),
  KEY `Refestado12` (`id_estado`),
  KEY `Refresponsable14` (`id_responsable`),
  KEY `Reflocal15` (`id_local`),
  CONSTRAINT `Refestado12` FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id_estado`),
  CONSTRAINT `Reflocal15` FOREIGN KEY (`id_local`) REFERENCES `local` (`id_local`),
  CONSTRAINT `Refresponsable14` FOREIGN KEY (`id_responsable`) REFERENCES `responsable` (`id_responsable`),
  CONSTRAINT `Refrevision2` FOREIGN KEY (`id_revision`) REFERENCES `revision` (`id_revision`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Structure for the `usuario` table : 
#

CREATE TABLE `usuario` (
  `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `apellidos` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `email` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `contrasenna` varchar(200) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Structure for the `auditoria` table : 
#

CREATE TABLE `auditoria` (
  `id_auditoria` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `hora` datetime NOT NULL,
  `rotulo` bigint(20) NOT NULL,
  `activo_antes` text COLLATE latin1_spanish_ci NOT NULL,
  `activo_despues` text COLLATE latin1_spanish_ci NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  PRIMARY KEY (`id_auditoria`),
  KEY `Refusuario16` (`id_usuario`),
  CONSTRAINT `Refusuario16` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Structure for the `metadata` table : 
#

CREATE TABLE `metadata` (
  `id_metadata` bigint(20) NOT NULL AUTO_INCREMENT,
  `total_activos` int(11) NOT NULL,
  `valor_total` float(8,0) NOT NULL,
  `valor_total_mc` float(8,0) NOT NULL,
  `dep_acu_total` float(8,0) NOT NULL,
  `dep_acu_total_mc` float(8,0) NOT NULL,
  `elaborado` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  `responsable` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  `revisado` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_revision` bigint(20) NOT NULL,
  PRIMARY KEY (`id_metadata`),
  KEY `Refrevision3` (`id_revision`),
  CONSTRAINT `Refrevision3` FOREIGN KEY (`id_revision`) REFERENCES `revision` (`id_revision`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Structure for the `rol` table : 
#

CREATE TABLE `rol` (
  `id_rol` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Structure for the `usuario_rol` table : 
#

CREATE TABLE `usuario_rol` (
  `id_usuario` bigint(20) NOT NULL,
  `id_rol` bigint(20) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_rol`),
  KEY `Refrol20` (`id_rol`),
  CONSTRAINT `Refrol20` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`),
  CONSTRAINT `Refusuario19` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Data for the `usuario` table  (LIMIT 0,500)
#

INSERT INTO `usuario` (`id_usuario`, `nombre`, `apellidos`, `email`, `contrasenna`) VALUES 
  (1,'123','123','123','123');

COMMIT;

