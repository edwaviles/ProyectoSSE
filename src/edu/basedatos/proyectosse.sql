CREATE SCHEMA IF NOT EXISTS `proyectosse` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema proyectosse
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema proyectosse
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `proyectosse` DEFAULT CHARACTER SET utf8mb4 ;
USE `proyectosse` ;

-- -----------------------------------------------------
-- Table `proyectosse`.`estadoSolicitudSSE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`estadoSolicitudSSE` (
  `idEstado` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`idEstado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proyectosse`.`estadoSSE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`estadoSSE` (
  `idEstado` INT ZEROFILL NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`idEstado`))
ENGINE = InnoDB;

USE `proyectosse` ;

-- -----------------------------------------------------
-- Table `proyectosse`.`rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`rol` (
  `idRol` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`idRol`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyectosse`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`usuario` (
  `idUsuario` INT(11) NOT NULL AUTO_INCREMENT,
  `idRol` INT(11) NULL DEFAULT NULL,
  `cod_usuario` INT(11) NULL DEFAULT NULL,
  `usuario` VARCHAR(30) NULL DEFAULT NULL,
  `contrasenia` TEXT NULL DEFAULT NULL,
  `fecha_Registro` DATE NULL DEFAULT NULL,
  `fecha_Modifica` DATE NULL DEFAULT NULL,
  `estado` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  INDEX `fk_usuario_rol` (`idRol` ASC),
  CONSTRAINT `fk_usuario_rol`
    FOREIGN KEY (`idRol`)
    REFERENCES `proyectosse`.`rol` (`idRol`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyectosse`.`alumno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`alumno` (
  `idAlumno` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `carnet` INT(11) NULL DEFAULT NULL,
  `carrera` VARCHAR(45) NULL DEFAULT NULL,
  `escuela` VARCHAR(45) NULL DEFAULT NULL,
  `grupo` VARCHAR(15) NULL DEFAULT NULL,
  `cursaMaterias` VARCHAR(20) NULL DEFAULT NULL,
  `tipoCarrera` VARCHAR(10) NULL DEFAULT NULL,
  `ciclo` INT(11) NULL DEFAULT NULL,
  `horasActuales` INT(11) NULL DEFAULT NULL,
  `estadoSSE` INT(11) NULL DEFAULT NULL,
  `usuario_idUsuario` INT(11) NOT NULL,
  PRIMARY KEY (`idAlumno`),
  INDEX `fk_alumno_usuario1_idx` (`usuario_idUsuario` ASC),
  CONSTRAINT `fk_alumno_usuario1`
    FOREIGN KEY (`usuario_idUsuario`)
    REFERENCES `proyectosse`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyectosse`.`solicitudSSE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`solicitudSSE` (
  `idSolicitudSSE` INT(11) NOT NULL AUTO_INCREMENT,
  `idAlumno` INT(11) NULL DEFAULT NULL,
  `sedeITCA` VARCHAR(45) NULL DEFAULT NULL,
  `fechaSolicitud` DATE NULL DEFAULT NULL,
  `institucion` VARCHAR(45) NULL DEFAULT NULL,
  `encargado` VARCHAR(45) NULL DEFAULT NULL,
  `comentarios` VARCHAR(50) NULL DEFAULT NULL,
  `estado` INT(11) NULL DEFAULT NULL,
  `fechaModificacion` DATE NULL,
  `fechaRegistro` DATE NULL,
  `estadoSSE_idEstado` INT NOT NULL,
  PRIMARY KEY (`idSolicitudSSE`),
  INDEX `fk_solicitudSSE_alumno` (`idAlumno` ASC),
  INDEX `fk_solicitudSSE_estadoSSE1_idx` (`estadoSSE_idEstado` ASC),
  CONSTRAINT `fk_solicitudSSE_alumno`
    FOREIGN KEY (`idAlumno`)
    REFERENCES `proyectosse`.`alumno` (`idAlumno`),
  CONSTRAINT `fk_solicitudSSE_estadoSSE1`
    FOREIGN KEY (`estadoSSE_idEstado`)
    REFERENCES `proyectosse`.`estadoSolicitudSSE` (`idEstado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyectosse`.`actividad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`actividad` (
  `idActividad` INT(11) NOT NULL AUTO_INCREMENT,
  `idSolicitudSSE` INT(11) NULL DEFAULT NULL,
  `labor` VARCHAR(45) NULL DEFAULT NULL,
  `objetivos` VARCHAR(50) NULL DEFAULT NULL,
  `metas` VARCHAR(50) NULL DEFAULT NULL,
  `duracion` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idActividad`),
  INDEX `fk_actividad_solicitudSSE` (`idSolicitudSSE` ASC),
  CONSTRAINT `fk_actividad_solicitudSSE`
    FOREIGN KEY (`idSolicitudSSE`)
    REFERENCES `proyectosse`.`solicitudSSE` (`idSolicitudSSE`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyectosse`.`controlhoras`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`controlhoras` (
  `idControlH` INT(11) NOT NULL AUTO_INCREMENT,
  `idSolicitudSSE` INT(11) NULL DEFAULT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `actividad` VARCHAR(45) NULL DEFAULT NULL,
  `horasDiarias` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idControlH`),
  INDEX `fk_controlHoras_solicitudSSE` (`idSolicitudSSE` ASC),
  CONSTRAINT `fk_controlHoras_solicitudSSE`
    FOREIGN KEY (`idSolicitudSSE`)
    REFERENCES `proyectosse`.`solicitudSSE` (`idSolicitudSSE`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyectosse`.`coordinadorsse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`coordinadorsse` (
  `idCoordinador` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(25) NULL DEFAULT NULL,
  `carreraAsignada` VARCHAR(40) NULL DEFAULT NULL,
  `correo` VARCHAR(25) NULL DEFAULT NULL,
  `usuario_idUsuario` INT(11) NOT NULL,
  `fechaRegistro` DATE NULL,
  `fechaModificacion` DATE NULL,
  PRIMARY KEY (`idCoordinador`),
  INDEX `fk_coordinadorsse_usuario1_idx` (`usuario_idUsuario` ASC),
  CONSTRAINT `fk_coordinadorsse_usuario1`
    FOREIGN KEY (`usuario_idUsuario`)
    REFERENCES `proyectosse`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyectosse`.`hojafinal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`hojafinal` (
  `idHoja` INT(11) NOT NULL AUTO_INCREMENT,
  `idSolicitudSSE` INT(11) NULL DEFAULT NULL,
  `hojaFinalCol` VARCHAR(45) NULL DEFAULT NULL,
  `fechaRegistro` DATE NULL,
  `fechaModificacion` DATE NULL,
  PRIMARY KEY (`idHoja`),
  INDEX `fk_hojaFinal_solicitudSSE` (`idSolicitudSSE` ASC),
  CONSTRAINT `fk_hojaFinal_solicitudSSE`
    FOREIGN KEY (`idSolicitudSSE`)
    REFERENCES `proyectosse`.`solicitudSSE` (`idSolicitudSSE`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyectosse`.`horario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`horario` (
  `idHorario` INT(11) NOT NULL AUTO_INCREMENT,
  `idSolicitudSSE` INT(11) NULL DEFAULT NULL,
  `dia` VARCHAR(15) NULL DEFAULT NULL,
  `horaDesde` TIME NULL DEFAULT NULL,
  `horaHasta` TIME NULL DEFAULT NULL,
  `fechaRegistro` DATE NULL,
  `fechaModificacion` DATE NULL,
  PRIMARY KEY (`idHorario`),
  INDEX `fk_horario_solicitudSSE` (`idSolicitudSSE` ASC),
  CONSTRAINT `fk_horario_solicitudSSE`
    FOREIGN KEY (`idSolicitudSSE`)
    REFERENCES `proyectosse`.`solicitudSSE` (`idSolicitudSSE`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyectosse`.`horarioatencion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`horarioatencion` (
  `idHorarioA` INT(11) NOT NULL AUTO_INCREMENT,
  `idCoordinador` INT(11) NULL DEFAULT NULL,
  `dia` VARCHAR(10) NULL DEFAULT NULL,
  `horaDesde` TIME NULL DEFAULT NULL,
  `horaHasta` TIME NULL DEFAULT NULL,
  `lugar` VARCHAR(45) NULL DEFAULT NULL,
  `fechaRegistro` DATE NULL,
  `fechaModificacion` DATE NULL,
  PRIMARY KEY (`idHorarioA`),
  INDEX `fk_horarioAtencion_coordinadorSSE` (`idCoordinador` ASC),
  CONSTRAINT `fk_horarioAtencion_coordinadorSSE`
    FOREIGN KEY (`idCoordinador`)
    REFERENCES `proyectosse`.`coordinadorsse` (`idCoordinador`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyectosse`.`materia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`materia` (
  `idMateria` INT(11) NOT NULL AUTO_INCREMENT,
  `idAlumno` INT(11) NULL DEFAULT NULL,
  `nombreMat` VARCHAR(45) NULL DEFAULT NULL,
  `promedioMat` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`idMateria`),
  INDEX `fk_materia_alumno` (`idAlumno` ASC),
  CONSTRAINT `fk_materia_alumno`
    FOREIGN KEY (`idAlumno`)
    REFERENCES `proyectosse`.`alumno` (`idAlumno`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyectosse`.`notificacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`notificacion` (
  `idNotificacion` INT(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` INT(11) NULL DEFAULT NULL,
  `emisor` INT(11) NULL DEFAULT NULL,
  `receptor` INT(11) NULL DEFAULT NULL,
  `asunto` VARCHAR(20) NULL DEFAULT NULL,
  `mensaje` VARCHAR(250) NULL DEFAULT NULL,
  `tipoNoti` VARCHAR(15) NULL DEFAULT NULL,
  `fechaNoti` DATE NULL DEFAULT NULL,
  `estadoNoti` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idNotificacion`),
  INDEX `fk_notificacion_usuario` (`idUsuario` ASC),
  CONSTRAINT `fk_notificacion_usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `proyectosse`.`usuario` (`idUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyectosse`.`observacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`observacion` (
  `idObservacion` INT(11) NOT NULL AUTO_INCREMENT,
  `idSolicitudSSE` INT(11) NULL DEFAULT NULL,
  `fechaObservacion` DATE NULL DEFAULT NULL,
  `estado` INT(11) NULL DEFAULT NULL,
  `mensajeOb` VARCHAR(250) NULL DEFAULT NULL,
  `asuntoOb` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`idObservacion`),
  INDEX `fk_observacion_solicitudSSE` (`idSolicitudSSE` ASC),
  CONSTRAINT `fk_observacion_solicitudSSE`
    FOREIGN KEY (`idSolicitudSSE`)
    REFERENCES `proyectosse`.`solicitudSSE` (`idSolicitudSSE`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyectosse`.`sse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyectosse`.`sse` (
  `idSSE` INT(11) NOT NULL AUTO_INCREMENT,
  `idAlumno` INT(11) NULL DEFAULT NULL,
  `totalHoras` INT(11) NULL DEFAULT NULL,
  `estado` INT(11) NULL DEFAULT NULL,
  `estadoSSE_idEstado` INT ZEROFILL NOT NULL,
  `fechaModificacion` DATE NULL,
  `fechaRegistro` DATE NULL,
  PRIMARY KEY (`idSSE`),
  INDEX `fk_SSE_alumno` (`idAlumno` ASC),
  INDEX `fk_sse_estadoSSE1_idx` (`estadoSSE_idEstado` ASC),
  CONSTRAINT `fk_SSE_alumno`
    FOREIGN KEY (`idAlumno`)
    REFERENCES `proyectosse`.`alumno` (`idAlumno`),
  CONSTRAINT `fk_sse_estadoSSE1`
    FOREIGN KEY (`estadoSSE_idEstado`)
    REFERENCES `proyectosse`.`estadoSSE` (`idEstado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;