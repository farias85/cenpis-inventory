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
  `activo` tinyint(1) NOT NULL,
  `fecha_en_sistema` date NOT NULL,
  `fecha_excel` date NOT NULL,
  `excel_url` varchar(500) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id_revision`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Structure for the `activo_fijo` table : 
#

CREATE TABLE `activo_fijo` (
  `id_activo_fijo` bigint(20) NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`id_activo_fijo`),
  KEY `Refrevision2` (`revision`),
  KEY `Refestado12` (`estado`),
  KEY `Refresponsable14` (`responsable`),
  KEY `Reflocal15` (`local`),
  CONSTRAINT `Refestado12` FOREIGN KEY (`estado`) REFERENCES `estado` (`id_estado`),
  CONSTRAINT `Reflocal15` FOREIGN KEY (`local`) REFERENCES `local` (`id_local`),
  CONSTRAINT `Refresponsable14` FOREIGN KEY (`responsable`) REFERENCES `responsable` (`id_responsable`),
  CONSTRAINT `Refrevision2` FOREIGN KEY (`revision`) REFERENCES `revision` (`id_revision`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Structure for the `auditoria` table : 
#

CREATE TABLE `auditoria` (
  `id_auditoria` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `hora` datetime NOT NULL,
  `rotulo` varchar(18) COLLATE latin1_spanish_ci NOT NULL,
  `activo_antes` text COLLATE latin1_spanish_ci NOT NULL,
  `activo_despues` text COLLATE latin1_spanish_ci NOT NULL,
  `usuario` bigint(20) NOT NULL,
  PRIMARY KEY (`id_auditoria`),
  KEY `Refusuario16` (`usuario`),
  CONSTRAINT `Refusuario16` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Structure for the `metadata` table : 
#

CREATE TABLE `metadata` (
  `id_metadata` bigint(20) NOT NULL AUTO_INCREMENT,
  `total_activos` int(11) NOT NULL,
  `valor_total` float(9,3) NOT NULL,
  `valor_total_mc` float(9,3) NOT NULL,
  `dep_acu_total` float(9,3) NOT NULL,
  `dep_acu_total_mc` float(9,3) NOT NULL,
  `elaborado` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  `responsable` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  `revisado` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  `revision` bigint(20) NOT NULL,
  PRIMARY KEY (`id_metadata`),
  KEY `Refrevision3` (`revision`),
  CONSTRAINT `Refrevision3` FOREIGN KEY (`revision`) REFERENCES `revision` (`id_revision`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Structure for the `rol` table : 
#

CREATE TABLE `rol` (
  `id_rol` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` text COLLATE latin1_spanish_ci,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Structure for the `usuario_rol` table : 
#

CREATE TABLE `usuario_rol` (
  `id_usuario` bigint(20) NOT NULL,
  `id_rol` bigint(20) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_rol`),
  KEY `Refrol22` (`id_rol`),
  CONSTRAINT `Refrol22` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`),
  CONSTRAINT `Refusuario21` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

#
# Data for the `estado` table  (LIMIT 0,500)
#

INSERT INTO `estado` (`id_estado`, `nombre`, `descripcion`) VALUES 
  (0,'EN_REVISION','EN_REVISION');
UPDATE `estado` SET `id_estado`=0 WHERE `id_estado`=LAST_INSERT_ID();
COMMIT;

#
# Data for the `local` table  (LIMIT 0,500)
#

INSERT INTO `local` (`id_local`, `nombre`, `descripcion`) VALUES 
  (0,'EN_REVISION','EN_REVISION');
UPDATE `local` SET `id_local`=0 WHERE `id_local`=LAST_INSERT_ID();
COMMIT;

#
# Data for the `responsable` table  (LIMIT 0,500)
#

INSERT INTO `responsable` (`id_responsable`, `nombre`, `email`, `descripcion`) VALUES 
  (0,'EN_REVISION','EN_REVISION','EN_REVISION');
UPDATE `responsable` SET `id_responsable`=0 WHERE `id_responsable`=LAST_INSERT_ID();
COMMIT;

#
# Data for the `usuario` table  (LIMIT 0,500)
#

INSERT INTO `usuario` (`id_usuario`, `nombre`, `apellidos`, `email`, `contrasenna`) VALUES 
  (1,'user','apellido','user@user.com','1000:5b42403130376634393830:016a2770480bc0cdd3cfece16142ef1c92df8228686531b9cd863ba73c7bdcebf36abeb79d4ea8d5619a683ab7c9a0051a94e11d979c8dff236dca3c27fe356f'),
  (2,'editor','apellido','editor@editor.com','1000:5b42403165343365333233:aae66d71923b595c7abba9cb334b7ee2a26f845fbc3196dd068fef64631199edf528d346c08a3ec0abb137f3b44998bfcb61fe7973c67c1403f163a5c5c81aa5'),
  (3,'admin','apellido','admin@admin.com','1000:5b42403665643136363537:d17d4d779945e396a0c8ec7fac32d2ec20009ef22c36c97ce816c22022520c6e287bc4f4ddf590905e239cb24764e4fe077ad8c27743224c09b1980d92b173ea');

COMMIT;

#
# Data for the `auditoria` table  (LIMIT 0,500)
#

INSERT INTO `auditoria` (`id_auditoria`, `fecha`, `hora`, `rotulo`, `activo_antes`, `activo_despues`, `usuario`) VALUES 
  (1,'2017-03-01','2017-03-01','12121','1212','1212',1);

COMMIT;

#
# Data for the `rol` table  (LIMIT 0,500)
#

INSERT INTO `rol` (`id_rol`, `nombre`, `descripcion`) VALUES 
  (1,'ROLE_USER','ROLE_USER'),
  (2,'ROLE_EDITOR','ROLE_EDITOR'),
  (3,'ROLE_ADMIN','ROLE_ADMIN');

COMMIT;

#
# Data for the `usuario_rol` table  (LIMIT 0,500)
#

INSERT INTO `usuario_rol` (`id_usuario`, `id_rol`) VALUES 
  (1,1),
  (2,2),
  (3,3);

COMMIT;

