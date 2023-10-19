-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generaciÃ³n: 19-10-2023 a las 19:35:17
-- VersiÃ³n del servidor: 10.4.28-MariaDB
-- VersiÃ³n de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de datos: `tesla`
--

USE `tesla`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `coches`
--

CREATE TABLE `coches` (
  `ID` int(11) NOT NULL,
  `MODELO` varchar(50) NOT NULL,
  `POTENCIA` int(11) NOT NULL,
  `AUTONOMIA` int(11) NOT NULL,
  `PRECIO` int(11) NOT NULL,
  `MALETERO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `coches`
--

INSERT INTO `coches` (`ID`, `MODELO`, `POTENCIA`, `AUTONOMIA`, `PRECIO`, `MALETERO`) VALUES
(1, 'Model 3', 513, 629, 41000, 425),
(3, 'Model S', 670, 600, 79999, 744),
(4, 'Model Z', 346, 505, 39999, 425),
(5, 'Model X', 605, 530, 89999, 2458),
(6, 'Model Y', 384, 525, 49999, 1927);

--
-- Ãndices para tablas volcadas
--

--
-- Indices de la tabla `coches`
--
ALTER TABLE `coches`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `coches`
--
ALTER TABLE `coches`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION