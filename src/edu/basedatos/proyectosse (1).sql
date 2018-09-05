-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-09-2018 a las 23:15:19
-- Versión del servidor: 10.1.31-MariaDB
-- Versión de PHP: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectosse`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `login` (IN `nombre` VARCHAR(45), IN `pass` VARCHAR(100))  begin 
	select idRol as tipo from usuario where estado=1 and usuario=nombre and contrasenia=pass;
    end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad`
--

CREATE TABLE `actividad` (
  `idActividad` int(11) NOT NULL,
  `idSolicitudSSE` int(11) DEFAULT NULL,
  `labor` varchar(45) DEFAULT NULL,
  `objetivos` varchar(50) DEFAULT NULL,
  `metas` varchar(50) DEFAULT NULL,
  `duracion` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `idAlumno` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `carnet` int(11) DEFAULT NULL,
  `carrera` varchar(45) DEFAULT NULL,
  `escuela` varchar(45) DEFAULT NULL,
  `grupo` varchar(15) DEFAULT NULL,
  `cursaMaterias` varchar(20) DEFAULT NULL,
  `tipoCarrera` varchar(10) DEFAULT NULL,
  `ciclo` int(11) DEFAULT NULL,
  `horasActuales` int(11) DEFAULT NULL,
  `estadoSSE` int(11) DEFAULT NULL,
  `usuario_idUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrera`
--

CREATE TABLE `carrera` (
  `idCarrera` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `escuela_idEscuela` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `carrera`
--

INSERT INTO `carrera` (`idCarrera`, `nombre`, `escuela_idEscuela`) VALUES
(1, 'Ingeniería de Sistemas Informáticos', 1),
(2, 'Ingeniería en Redes Informáticas', 1),
(3, 'Hardware Computacional', 1),
(4, 'Administración de empresas gastronómicas', 3),
(5, 'Gastronomia', 3),
(6, 'Ingenieria Civil', 2),
(7, 'Arquitectura', 2),
(8, 'Ingenieria Electrica', 4),
(9, 'Ingenieria Mecanica Opcion Mantenimiento', 5),
(10, 'Ingenieria Mecanica Opcion CNC', 5),
(11, 'Ingeniería Eléctrica Industrial', 4),
(12, 'Laboratorio Quimico', 6),
(13, 'Quimica Industrial', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controlhoras`
--

CREATE TABLE `controlhoras` (
  `idControlH` int(11) NOT NULL,
  `idSolicitudSSE` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `actividad` varchar(45) DEFAULT NULL,
  `horasDiarias` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `coordinadorsse`
--

CREATE TABLE `coordinadorsse` (
  `idCoordinador` int(11) NOT NULL,
  `nombre` varchar(25) DEFAULT NULL,
  `correo` varchar(25) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `usuario_idUsuario` int(11) NOT NULL,
  `fechaRegistro` date DEFAULT NULL,
  `fechaModificacion` date DEFAULT NULL,
  `carrera_idCarrera` int(11) NOT NULL,
  `fechaEliminacion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `coordinadorsse`
--

INSERT INTO `coordinadorsse` (`idCoordinador`, `nombre`, `correo`, `estado`, `usuario_idUsuario`, `fechaRegistro`, `fechaModificacion`, `carrera_idCarrera`, `fechaEliminacion`) VALUES
(2, 'CoordinadorN', 'hola@gmail.com', 1, 1, NULL, '2018-09-05', 2, NULL),
(5, 'Nancy Lopez', 'Nancy@gmail.com', 0, 5, '2018-09-01', '2018-09-01', 1, '2018-09-05'),
(7, 'Reniery Garcia', 'Reniery@yahoo.com', 0, 7, '2018-09-01', '2018-09-01', 6, '2018-09-05'),
(8, 'Roxana Menjivar', 'Roxana@itca.edu.sv', 0, 8, '2018-09-01', NULL, 3, '2018-09-05'),
(9, 'Prueba', 'prueba@gmail.com', 0, 9, '2018-09-01', NULL, 4, '2018-09-01'),
(10, 'PruebaDos', 'prueba2@gmail.com', 0, 10, '2018-09-01', NULL, 4, '2018-09-01'),
(11, 'asasas', 'asas@gmail.com', 0, 11, '2018-09-05', NULL, 1, '2018-09-05'),
(12, 'qwqwq', 'qwqw@gmail.com', 0, 12, '2018-09-05', NULL, 1, '2018-09-05'),
(13, 'prueba', 'prueba@gmail.com', 1, 13, '2018-09-05', NULL, 3, NULL),
(14, 'Ernesto', 'ernesto@gmail.com', 1, 16, '2018-09-05', NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `escuela`
--

CREATE TABLE `escuela` (
  `idEscuela` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `escuela`
--

INSERT INTO `escuela` (`idEscuela`, `nombre`) VALUES
(1, 'Computacion'),
(2, 'Civil y Arquitectura'),
(3, 'Alimentos'),
(4, 'Electrica'),
(5, 'Automotriz'),
(6, 'Quimica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadosolicitudsse`
--

CREATE TABLE `estadosolicitudsse` (
  `idEstado` int(11) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadosse`
--

CREATE TABLE `estadosse` (
  `idEstado` int(10) UNSIGNED ZEROFILL NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hojafinal`
--

CREATE TABLE `hojafinal` (
  `idHoja` int(11) NOT NULL,
  `idSolicitudSSE` int(11) DEFAULT NULL,
  `hojaFinalCol` varchar(45) DEFAULT NULL,
  `fechaRegistro` date DEFAULT NULL,
  `fechaModificacion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE `horario` (
  `idHorario` int(11) NOT NULL,
  `idSolicitudSSE` int(11) DEFAULT NULL,
  `dia` varchar(15) DEFAULT NULL,
  `horaDesde` time DEFAULT NULL,
  `horaHasta` time DEFAULT NULL,
  `fechaRegistro` date DEFAULT NULL,
  `fechaModificacion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horarioatencion`
--

CREATE TABLE `horarioatencion` (
  `idHorarioA` int(11) NOT NULL,
  `idCoordinador` int(11) DEFAULT NULL,
  `dia` varchar(10) DEFAULT NULL,
  `horaDesde` int(11) DEFAULT NULL,
  `minutosDesde` int(11) NOT NULL,
  `horaHasta` int(11) DEFAULT NULL,
  `minutosHasta` int(11) NOT NULL,
  `lugar` varchar(45) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fechaRegistro` date DEFAULT NULL,
  `fechaModificacion` date DEFAULT NULL,
  `fechaEliminacion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `horarioatencion`
--

INSERT INTO `horarioatencion` (`idHorarioA`, `idCoordinador`, `dia`, `horaDesde`, `minutosDesde`, `horaHasta`, `minutosHasta`, `lugar`, `estado`, `fechaRegistro`, `fechaModificacion`, `fechaEliminacion`) VALUES
(10, 2, 'Lunes', 8, 30, 9, 40, 'Edificio a', 0, '2018-09-03', '2018-09-03', '2018-09-04'),
(11, 2, 'Lunes', 9, 50, 11, 0, 'Edificio E', 0, '2018-09-03', '2018-09-03', '2018-09-04'),
(12, 2, 'Lunes', 11, 10, 12, 0, 'sdsd', 0, '2018-09-03', NULL, '2018-09-04'),
(13, 2, 'Miercoles', 10, 10, 11, 5, 'sdsd', 1, '2018-09-03', '2018-09-04', NULL),
(14, 2, 'Miercoles', 11, 0, 11, 5, 'ok', 0, '2018-09-03', '2018-09-04', '2018-09-04'),
(15, 2, 'Lunes', 8, 0, 10, 9, 'sds', 1, '2018-09-04', '2018-09-04', NULL),
(16, 2, 'Lunes', 10, 10, 11, 0, 'Edificioc', 1, '2018-09-04', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materia`
--

CREATE TABLE `materia` (
  `idMateria` int(11) NOT NULL,
  `idAlumno` int(11) DEFAULT NULL,
  `nombreMat` varchar(45) DEFAULT NULL,
  `promedioMat` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notificacion`
--

CREATE TABLE `notificacion` (
  `idNotificacion` int(11) NOT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `emisor` int(11) DEFAULT NULL,
  `receptor` int(11) DEFAULT NULL,
  `asunto` varchar(20) DEFAULT NULL,
  `mensaje` varchar(250) DEFAULT NULL,
  `tipoNoti` varchar(15) DEFAULT NULL,
  `fechaNoti` date DEFAULT NULL,
  `estadoNoti` int(11) DEFAULT NULL,
  `fechaRegistro` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `observacion`
--

CREATE TABLE `observacion` (
  `idObservacion` int(11) NOT NULL,
  `idSolicitudSSE` int(11) DEFAULT NULL,
  `fechaObservacion` date DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `mensajeOb` varchar(250) DEFAULT NULL,
  `asuntoOb` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idRol` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idRol`, `nombre`) VALUES
(1, 'Administrador'),
(2, 'Coordinador'),
(3, 'CoordinadorPrincipal'),
(4, 'Estudiante');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitudsse`
--

CREATE TABLE `solicitudsse` (
  `idSolicitudSSE` int(11) NOT NULL,
  `idAlumno` int(11) DEFAULT NULL,
  `sedeITCA` varchar(45) DEFAULT NULL,
  `fechaSolicitud` date DEFAULT NULL,
  `institucion` varchar(45) DEFAULT NULL,
  `encargado` varchar(45) DEFAULT NULL,
  `comentarios` varchar(50) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fechaModificacion` date DEFAULT NULL,
  `fechaRegistro` date DEFAULT NULL,
  `estadoSSE_idEstado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sse`
--

CREATE TABLE `sse` (
  `idSSE` int(11) NOT NULL,
  `idAlumno` int(11) DEFAULT NULL,
  `totalHoras` int(11) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `estadoSSE_idEstado` int(10) UNSIGNED ZEROFILL NOT NULL,
  `fechaModificacion` date DEFAULT NULL,
  `fechaRegistro` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `idRol` int(11) DEFAULT NULL,
  `usuario` varchar(30) DEFAULT NULL,
  `contrasenia` text,
  `fechaRegistro` date DEFAULT NULL,
  `fechaModifica` date DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fechaEliminacion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `idRol`, `usuario`, `contrasenia`, `fechaRegistro`, `fechaModifica`, `estado`, `fechaEliminacion`) VALUES
(1, 2, 'Usuario1', '123', NULL, NULL, 1, NULL),
(2, 2, 'usuario2', '123', NULL, NULL, 1, NULL),
(3, 2, 'Elmer Mejia', 'd41d8cd98f00b204e9800998ecf8427e', '2018-09-01', '2018-09-05', 1, NULL),
(4, 2, 'otro', 'Itca123!', '2018-09-01', NULL, 1, NULL),
(5, 2, 'Nancy Lop', '202cb962ac59075b964b07152d234b70', '2018-09-01', '2018-09-05', 2, '2018-09-05'),
(6, 2, 'Ernesto', 'Itca123!', '2018-09-01', NULL, 2, '2018-09-05'),
(7, 2, 'Reniery', 'Itca123!', '2018-09-01', NULL, 1, NULL),
(8, 2, 'Roxana', 'Itca123!', '2018-09-01', NULL, 1, NULL),
(9, 2, 'Prueba', 'Itca123!', '2018-09-01', NULL, 1, NULL),
(10, 2, 'PruebaDos', 'Itca123!', '2018-09-01', NULL, 1, NULL),
(11, 2, 'asasas', 'Itca123!', '2018-09-05', NULL, 1, NULL),
(12, 2, 'qwqwq', 'Itca123!', '2018-09-05', NULL, 0, '2018-09-05'),
(13, 2, 'prueba2', 'Itca123!', '2018-09-05', NULL, 1, NULL),
(14, 1, 'Veronica', '202cb962ac59075b964b07152d234b70', '2018-09-05', NULL, 2, '2018-09-05'),
(15, 1, 'Nancy Lopez', 'd41d8cd98f00b204e9800998ecf8427e', '2018-09-05', NULL, 1, NULL),
(16, 2, 'Ernesto13', 'Itca123!', '2018-09-05', NULL, 1, NULL),
(17, 2, 'Ernesto Avilez', 'd41d8cd98f00b204e9800998ecf8427e', '2018-09-05', '2018-09-05', 2, '2018-09-05'),
(18, 2, 'Raquel', 'Itca123!', '2018-09-05', NULL, 2, '2018-09-05');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD PRIMARY KEY (`idActividad`),
  ADD KEY `fk_actividad_solicitudSSE` (`idSolicitudSSE`);

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`idAlumno`),
  ADD KEY `fk_alumno_usuario1_idx` (`usuario_idUsuario`);

--
-- Indices de la tabla `carrera`
--
ALTER TABLE `carrera`
  ADD PRIMARY KEY (`idCarrera`),
  ADD KEY `fk_carrera_escuela_idx` (`escuela_idEscuela`);

--
-- Indices de la tabla `controlhoras`
--
ALTER TABLE `controlhoras`
  ADD PRIMARY KEY (`idControlH`),
  ADD KEY `fk_controlHoras_solicitudSSE` (`idSolicitudSSE`);

--
-- Indices de la tabla `coordinadorsse`
--
ALTER TABLE `coordinadorsse`
  ADD PRIMARY KEY (`idCoordinador`,`carrera_idCarrera`),
  ADD KEY `fk_coordinadorsse_usuario1_idx` (`usuario_idUsuario`),
  ADD KEY `fk_coordinadorsse_carrera1_idx` (`carrera_idCarrera`);

--
-- Indices de la tabla `escuela`
--
ALTER TABLE `escuela`
  ADD PRIMARY KEY (`idEscuela`);

--
-- Indices de la tabla `estadosolicitudsse`
--
ALTER TABLE `estadosolicitudsse`
  ADD PRIMARY KEY (`idEstado`);

--
-- Indices de la tabla `estadosse`
--
ALTER TABLE `estadosse`
  ADD PRIMARY KEY (`idEstado`);

--
-- Indices de la tabla `hojafinal`
--
ALTER TABLE `hojafinal`
  ADD PRIMARY KEY (`idHoja`),
  ADD KEY `fk_hojaFinal_solicitudSSE` (`idSolicitudSSE`);

--
-- Indices de la tabla `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`idHorario`),
  ADD KEY `fk_horario_solicitudSSE` (`idSolicitudSSE`);

--
-- Indices de la tabla `horarioatencion`
--
ALTER TABLE `horarioatencion`
  ADD PRIMARY KEY (`idHorarioA`),
  ADD KEY `fk_horarioAtencion_coordinadorSSE` (`idCoordinador`);

--
-- Indices de la tabla `materia`
--
ALTER TABLE `materia`
  ADD PRIMARY KEY (`idMateria`),
  ADD KEY `fk_materia_alumno` (`idAlumno`);

--
-- Indices de la tabla `notificacion`
--
ALTER TABLE `notificacion`
  ADD PRIMARY KEY (`idNotificacion`),
  ADD KEY `fk_notificacion_usuario` (`idUsuario`);

--
-- Indices de la tabla `observacion`
--
ALTER TABLE `observacion`
  ADD PRIMARY KEY (`idObservacion`),
  ADD KEY `fk_observacion_solicitudSSE` (`idSolicitudSSE`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idRol`);

--
-- Indices de la tabla `solicitudsse`
--
ALTER TABLE `solicitudsse`
  ADD PRIMARY KEY (`idSolicitudSSE`),
  ADD KEY `fk_solicitudSSE_alumno` (`idAlumno`),
  ADD KEY `fk_solicitudSSE_estadoSSE1_idx` (`estadoSSE_idEstado`);

--
-- Indices de la tabla `sse`
--
ALTER TABLE `sse`
  ADD PRIMARY KEY (`idSSE`),
  ADD KEY `fk_SSE_alumno` (`idAlumno`),
  ADD KEY `fk_sse_estadoSSE1_idx` (`estadoSSE_idEstado`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`),
  ADD KEY `fk_usuario_rol` (`idRol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actividad`
--
ALTER TABLE `actividad`
  MODIFY `idActividad` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `alumno`
--
ALTER TABLE `alumno`
  MODIFY `idAlumno` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `carrera`
--
ALTER TABLE `carrera`
  MODIFY `idCarrera` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `controlhoras`
--
ALTER TABLE `controlhoras`
  MODIFY `idControlH` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `coordinadorsse`
--
ALTER TABLE `coordinadorsse`
  MODIFY `idCoordinador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `escuela`
--
ALTER TABLE `escuela`
  MODIFY `idEscuela` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `estadosolicitudsse`
--
ALTER TABLE `estadosolicitudsse`
  MODIFY `idEstado` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `hojafinal`
--
ALTER TABLE `hojafinal`
  MODIFY `idHoja` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `horario`
--
ALTER TABLE `horario`
  MODIFY `idHorario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `horarioatencion`
--
ALTER TABLE `horarioatencion`
  MODIFY `idHorarioA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `materia`
--
ALTER TABLE `materia`
  MODIFY `idMateria` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `notificacion`
--
ALTER TABLE `notificacion`
  MODIFY `idNotificacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `observacion`
--
ALTER TABLE `observacion`
  MODIFY `idObservacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idRol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `solicitudsse`
--
ALTER TABLE `solicitudsse`
  MODIFY `idSolicitudSSE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sse`
--
ALTER TABLE `sse`
  MODIFY `idSSE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD CONSTRAINT `fk_actividad_solicitudSSE` FOREIGN KEY (`idSolicitudSSE`) REFERENCES `solicitudsse` (`idSolicitudSSE`);

--
-- Filtros para la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD CONSTRAINT `fk_alumno_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `carrera`
--
ALTER TABLE `carrera`
  ADD CONSTRAINT `fk_carrera_escuela` FOREIGN KEY (`escuela_idEscuela`) REFERENCES `escuela` (`idEscuela`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `controlhoras`
--
ALTER TABLE `controlhoras`
  ADD CONSTRAINT `fk_controlHoras_solicitudSSE` FOREIGN KEY (`idSolicitudSSE`) REFERENCES `solicitudsse` (`idSolicitudSSE`);

--
-- Filtros para la tabla `coordinadorsse`
--
ALTER TABLE `coordinadorsse`
  ADD CONSTRAINT `fk_coordinadorsse_carrera1` FOREIGN KEY (`carrera_idCarrera`) REFERENCES `carrera` (`idCarrera`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_coordinadorsse_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `hojafinal`
--
ALTER TABLE `hojafinal`
  ADD CONSTRAINT `fk_hojaFinal_solicitudSSE` FOREIGN KEY (`idSolicitudSSE`) REFERENCES `solicitudsse` (`idSolicitudSSE`);

--
-- Filtros para la tabla `horario`
--
ALTER TABLE `horario`
  ADD CONSTRAINT `fk_horario_solicitudSSE` FOREIGN KEY (`idSolicitudSSE`) REFERENCES `solicitudsse` (`idSolicitudSSE`);

--
-- Filtros para la tabla `horarioatencion`
--
ALTER TABLE `horarioatencion`
  ADD CONSTRAINT `fk_horarioAtencion_coordinadorSSE` FOREIGN KEY (`idCoordinador`) REFERENCES `coordinadorsse` (`idCoordinador`);

--
-- Filtros para la tabla `materia`
--
ALTER TABLE `materia`
  ADD CONSTRAINT `fk_materia_alumno` FOREIGN KEY (`idAlumno`) REFERENCES `alumno` (`idAlumno`);

--
-- Filtros para la tabla `notificacion`
--
ALTER TABLE `notificacion`
  ADD CONSTRAINT `fk_notificacion_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`);

--
-- Filtros para la tabla `observacion`
--
ALTER TABLE `observacion`
  ADD CONSTRAINT `fk_observacion_solicitudSSE` FOREIGN KEY (`idSolicitudSSE`) REFERENCES `solicitudsse` (`idSolicitudSSE`);

--
-- Filtros para la tabla `solicitudsse`
--
ALTER TABLE `solicitudsse`
  ADD CONSTRAINT `fk_solicitudSSE_alumno` FOREIGN KEY (`idAlumno`) REFERENCES `alumno` (`idAlumno`),
  ADD CONSTRAINT `fk_solicitudSSE_estadoSSE1` FOREIGN KEY (`estadoSSE_idEstado`) REFERENCES `estadosolicitudsse` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sse`
--
ALTER TABLE `sse`
  ADD CONSTRAINT `fk_SSE_alumno` FOREIGN KEY (`idAlumno`) REFERENCES `alumno` (`idAlumno`),
  ADD CONSTRAINT `fk_sse_estadoSSE1` FOREIGN KEY (`estadoSSE_idEstado`) REFERENCES `estadosse` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
