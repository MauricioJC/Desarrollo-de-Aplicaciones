/*
Navicat MySQL Data Transfer

Source Server         : MySQL Server
Source Server Version : 50718
Source Host           : 127.0.0.1:3306
Source Database       : tienda_de_smartphones

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-06-08 22:00:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for reservacion
-- ----------------------------
DROP TABLE IF EXISTS `reservacion`;
CREATE TABLE `reservacion` (
  `id_reservacion` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_smartphone` int(11) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id_reservacion`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_smartphone` (`id_smartphone`),
  CONSTRAINT `reservacion_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservacion_ibfk_2` FOREIGN KEY (`id_smartphone`) REFERENCES `smartphone` (`id_smartphone`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for smartphone
-- ----------------------------
DROP TABLE IF EXISTS `smartphone`;
CREATE TABLE `smartphone` (
  `id_smartphone` int(11) NOT NULL AUTO_INCREMENT,
  `marca` varchar(60) NOT NULL,
  `modelo` varchar(60) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `color` varchar(60) NOT NULL,
  `precio` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`id_smartphone`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for traspaso
-- ----------------------------
DROP TABLE IF EXISTS `traspaso`;
CREATE TABLE `traspaso` (
  `id_traspaso` int(11) NOT NULL AUTO_INCREMENT,
  `id_smartphone` int(11) NOT NULL,
  `tipo` varchar(40) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id_traspaso`),
  KEY `id_smartphone` (`id_smartphone`),
  CONSTRAINT `traspaso_ibfk_1` FOREIGN KEY (`id_smartphone`) REFERENCES `smartphone` (`id_smartphone`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for usuario
-- ----------------------------
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `correo` varchar(60) NOT NULL,
  `contraseña` varchar(60) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for venta
-- ----------------------------
DROP TABLE IF EXISTS `venta`;
CREATE TABLE `venta` (
  `id_venta` int(11) NOT NULL AUTO_INCREMENT,
  `id_smartphone` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `venta_ibfk_1` (`id_smartphone`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`id_smartphone`) REFERENCES `smartphone` (`id_smartphone`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
