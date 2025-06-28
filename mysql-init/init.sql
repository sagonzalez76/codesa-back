-- Establece codificacion para conexion, evita problemas con caracteres especiales
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

-- Crea la base de datos
CREATE DATABASE IF NOT EXISTS escolar_db
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;
USE escolar_db;

-- Tabla base: Persona
CREATE TABLE persona (
  id_persona INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  fecha_nacimiento DATE NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  telefono VARCHAR(20) NOT NULL
);

-- Tabla estudiante (herencia de persona)
CREATE TABLE estudiante (
  id_persona INT PRIMARY KEY,
  numero_matricula VARCHAR(50) NOT NULL UNIQUE,
  grado VARCHAR(50) NOT NULL,
  FOREIGN KEY (id_persona) REFERENCES persona(id_persona) ON DELETE CASCADE
);

-- Tabla profesor (herencia de persona)
CREATE TABLE profesor (
  id_persona INT PRIMARY KEY,
  especialidad VARCHAR(100) NOT NULL,
  fecha_contratacion DATE NOT NULL,
  FOREIGN KEY (id_persona) REFERENCES persona(id_persona) ON DELETE CASCADE
);

-- Tabla administrativo (herencia de persona)
CREATE TABLE administrativo (
  id_persona INT PRIMARY KEY,
  cargo VARCHAR(100) NOT NULL,
  departamento VARCHAR(100) NOT NULL,
  FOREIGN KEY (id_persona) REFERENCES persona(id_persona) ON DELETE CASCADE
);

-- Tabla curso
CREATE TABLE curso (
  id_curso INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT,
  creditos INT NOT NULL,
  id_profesor INT,
  FOREIGN KEY (id_profesor) REFERENCES profesor(id_persona) ON DELETE SET NULL
);

-- Tabla inscripcion (relacion estudiante - curso)
CREATE TABLE inscripcion (
  id_inscripcion INT AUTO_INCREMENT PRIMARY KEY,
  id_estudiante INT NOT NULL,
  id_curso INT NOT NULL,
  fecha_inscripcion DATE NOT NULL,
  FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_persona) ON DELETE CASCADE,
  FOREIGN KEY (id_curso) REFERENCES curso(id_curso) ON DELETE CASCADE
);

-- =====================
-- Inserciones de datos de ejemplo
-- =====================

-- Personas
INSERT INTO persona (nombre, apellido, fecha_nacimiento, email, telefono) VALUES
('Juan', 'Pérez', '2005-04-12', 'juan.perez@example.com', '3123456789'),  -- Estudiante
('Laura', 'Gómez', '1980-03-21', 'laura.gomez@example.com', '3011122333'), -- Profesora
('Carlos', 'Ruiz', '1990-08-15', 'carlos.ruiz@example.com', '3109988776'); -- Administrativo

-- Estudiante (herencia de persona id=1)
INSERT INTO estudiante (id_persona, numero_matricula, grado) VALUES
(1, 'ESTU12345', '10A');

-- Profesor (herencia de persona id=2)
INSERT INTO profesor (id_persona, especialidad, fecha_contratacion) VALUES
(2, 'Matemáticas', '2010-01-15');

-- Administrativo (herencia de persona id=3)
INSERT INTO administrativo (id_persona, cargo, departamento) VALUES
(3, 'Secretario Académico', 'Administración');

-- Curso creado por el profesor
INSERT INTO curso (nombre, descripcion, creditos, id_profesor) VALUES
('Álgebra Avanzada', 'Curso intensivo de álgebra para grado 10.', 3, 2);

-- Inscripción del estudiante al curso
INSERT INTO inscripcion (id_estudiante, id_curso, fecha_inscripcion) VALUES
(1, 1, '2024-08-01');