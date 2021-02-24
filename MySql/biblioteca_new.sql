-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `autore`
--

DROP TABLE IF EXISTS `autore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autore` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL DEFAULT '',
  `cognome` varchar(50) NOT NULL DEFAULT '',
  `dataNascita` date DEFAULT NULL,
  `dataMorte` date DEFAULT NULL,
  `nazionalità` varchar(20) NOT NULL DEFAULT '',
  `biografia` varchar(256) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `autore_libro`
--

DROP TABLE IF EXISTS `autore_libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autore_libro` (
  `idAutore` int DEFAULT NULL,
  `idLibro` int DEFAULT NULL,
  KEY `fk_idAutore_idx` (`idAutore`),
  KEY `fk_idLibro_idx` (`idLibro`),
  CONSTRAINT `fk_idAutore` FOREIGN KEY (`idAutore`) REFERENCES `autore` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_idLibro` FOREIGN KEY (`idLibro`) REFERENCES `libro` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `casa_editrice`
--

DROP TABLE IF EXISTS `casa_editrice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `casa_editrice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ragioneSociale` varchar(100) NOT NULL DEFAULT '',
  `indirizzo` varchar(100) NOT NULL DEFAULT '',
  `pIva` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `isbn` varchar(20) NOT NULL,
  `titolo` varchar(100) NOT NULL DEFAULT '',
  `genere` varchar(50) NOT NULL DEFAULT '',
  `pagine` int NOT NULL DEFAULT '-1',
  `ristampa` int DEFAULT '-1',
  `descrizione` varchar(256) NOT NULL DEFAULT '',
  `lingua` varchar(50) NOT NULL DEFAULT '',
  `primaEdizione` date DEFAULT NULL,
  `ultimaRistampa` date DEFAULT NULL,
  `casaEditrice` int DEFAULT NULL,
  `posizioneBiblioteca` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_casaEditrice_idx` (`casaEditrice`),
  KEY `fk_posizione_idx` (`posizioneBiblioteca`),
  CONSTRAINT `fk_casaEditrice` FOREIGN KEY (`casaEditrice`) REFERENCES `casa_editrice` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_posizione` FOREIGN KEY (`posizioneBiblioteca`) REFERENCES `scaffale` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `scaffale`
--

DROP TABLE IF EXISTS `scaffale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scaffale` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero` int NOT NULL DEFAULT '-1',
  `ripiano` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-24  9:16:57
