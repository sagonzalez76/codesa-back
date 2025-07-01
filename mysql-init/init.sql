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

-- ==========================
-- Personas (40 en total)
-- ==========================
INSERT INTO persona (nombre, apellido, fecha_nacimiento, email, telefono) VALUES
-- Estudiantes (20)
('Juan', 'Pérez', '2005-04-12', 'juan.perez@example.com', '3123456789'),
('María', 'López', '2006-07-19', 'maria.lopez@example.com', '3101122334'),
('Andrés', 'Ramírez', '2007-02-28', 'andres.ramirez@example.com', '3012233445'),
('Camila', 'Torres', '2005-11-23', 'camila.torres@example.com', '3204455667'),
('Diego', 'Morales', '2006-09-14', 'diego.morales@example.com', '3223344556'),
('Sofía', 'González', '2005-01-01', 'sofia.gonzalez@example.com', '3132221111'),
('Mateo', 'Córdoba', '2006-03-22', 'mateo.cordoba@example.com', '3147896541'),
('Valentina', 'Martínez', '2007-08-14', 'valentina.martinez@example.com', '3151112222'),
('Santiago', 'Vargas', '2004-12-05', 'santiago.vargas@example.com', '3182345678'),
('Isabella', 'Moreno', '2005-10-09', 'isabella.moreno@example.com', '3163456789'),
('Emilio', 'Navarro', '2006-06-18', 'emilio.navarro@example.com', '3179876543'),
('Daniela', 'Gómez', '2007-01-25', 'daniela.gomez@example.com', '3134567890'),
('Samuel', 'Acosta', '2006-11-30', 'samuel.acosta@example.com', '3124567891'),
('Luciana', 'Rojas', '2005-07-11', 'luciana.rojas@example.com', '3114567892'),
('Esteban', 'Mejía', '2007-05-06', 'esteban.mejia@example.com', '3191234567'),
('Antonella', 'Cano', '2006-04-17', 'antonella.cano@example.com', '3201234567'),
('Simón', 'Herrera', '2005-03-13', 'simon.herrera@example.com', '3219876543'),
('Julieta', 'Salazar', '2006-09-21', 'julieta.salazar@example.com', '3227654321'),
('Thiago', 'Peña', '2007-02-01', 'thiago.pena@example.com', '3116547890'),
('Regina', 'Zamora', '2006-06-06', 'regina.zamora@example.com', '3101234567'),

-- Profesores (10)
('Laura', 'Gómez', '1980-03-21', 'laura.gomez@example.com', '3011122333'),
('Ricardo', 'Castro', '1975-10-10', 'ricardo.castro@example.com', '3009988776'),
('Paula', 'Mendoza', '1982-06-05', 'paula.mendoza@example.com', '3156677889'),
('Andrés', 'Giraldo', '1978-08-19', 'andres.giraldo@example.com', '3198765432'),
('Mariana', 'Osorio', '1983-02-10', 'mariana.osorio@example.com', '3172345678'),
('David', 'Reyes', '1974-11-22', 'david.reyes@example.com', '3163459876'),
('Verónica', 'Pineda', '1985-09-01', 'veronica.pineda@example.com', '3124563214'),
('Jorge', 'Serrano', '1980-07-07', 'jorge.serrano@example.com', '3142349876'),
('Elena', 'Cárdenas', '1979-03-15', 'elena.cardenas@example.com', '3189988776'),
('Felipe', 'Durán', '1981-12-12', 'felipe.duran@example.com', '3191112223'),

-- Administrativos (10)
('Carlos', 'Ruiz', '1990-08-15', 'carlos.ruiz@example.com', '3109988776'),
('Sandra', 'Martínez', '1985-12-02', 'sandra.martinez@example.com', '3191122334'),
('Lina', 'Castillo', '1986-01-10', 'lina.castillo@example.com', '3175432100'),
('Oscar', 'Valle', '1987-05-19', 'oscar.valle@example.com', '3162345678'),
('Nadia', 'Nieto', '1988-10-03', 'nadia.nieto@example.com', '3153456789'),
('Fernando', 'Cruz', '1991-03-11', 'fernando.cruz@example.com', '3134567890'),
('Diana', 'Bravo', '1992-07-30', 'diana.bravo@example.com', '3123456789'),
('Lucía', 'Camacho', '1989-09-21', 'lucia.camacho@example.com', '3112345678'),
('Manuel', 'Rincón', '1990-06-17', 'manuel.rincon@example.com', '3109876543'),
('Jazmín', 'Ferrer', '1993-04-26', 'jazmin.ferrer@example.com', '3191231234');

-- ==========================
-- Estudiantes (20)
-- ==========================
INSERT INTO estudiante (id_persona, numero_matricula, grado) VALUES
(1, 'ESTU1001', '10A'),
(2, 'ESTU1002', '9B'),
(3, 'ESTU1003', '10B'),
(4, 'ESTU1004', '11A'),
(5, 'ESTU1005', '9A'),
(6, 'ESTU1006', '11B'),
(7, 'ESTU1007', '10A'),
(8, 'ESTU1008', '8A'),
(9, 'ESTU1009', '11A'),
(10, 'ESTU1010', '9B'),
(11, 'ESTU1011', '10B'),
(12, 'ESTU1012', '11A'),
(13, 'ESTU1013', '9A'),
(14, 'ESTU1014', '8A'),
(15, 'ESTU1015', '10A'),
(16, 'ESTU1016', '11B'),
(17, 'ESTU1017', '10B'),
(18, 'ESTU1018', '9B'),
(19, 'ESTU1019', '10A'),
(20, 'ESTU1020', '8B');

-- ==========================
-- Profesores (10)
-- ==========================
INSERT INTO profesor (id_persona, especialidad, fecha_contratacion) VALUES
(21, 'Matemáticas', '2010-01-15'),
(22, 'Ciencias Naturales', '2005-03-22'),
(23, 'Lengua Castellana', '2012-07-09'),
(24, 'Física', '2011-08-10'),
(25, 'Química', '2008-02-20'),
(26, 'Sociales', '2009-11-01'),
(27, 'Biología', '2014-04-30'),
(28, 'Educación Física', '2015-09-25'),
(29, 'Informática', '2013-06-18'),
(30, 'Filosofía', '2007-12-12');

-- ==========================
-- Administrativos (10)
-- ==========================
INSERT INTO administrativo (id_persona, cargo, departamento) VALUES
(31, 'Secretario Académico', 'Administración'),
(32, 'Jefe de Registro', 'Admisiones'),
(33, 'Auxiliar Contable', 'Finanzas'),
(34, 'Coordinador', 'Disciplinario'),
(35, 'Asistente General', 'Servicios'),
(36, 'Tesorero', 'Finanzas'),
(37, 'Director TI', 'Tecnología'),
(38, 'Gestor Humano', 'Recursos Humanos'),
(39, 'Jefe de Compras', 'Logística'),
(40, 'Recepcionista', 'Atención al Público');

-- ==========================
-- Cursos (15 asignados a profesores id_persona 21-30)
-- ==========================
INSERT INTO curso (nombre, descripcion, creditos, id_profesor) VALUES
('Álgebra Avanzada', 'Curso intensivo de álgebra para grado 10.', 3, 21),
('Biología Molecular', 'Estudio de la célula y sus funciones.', 4, 27),
('Historia Universal', 'Desde la antigüedad hasta la actualidad.', 2, 26),
('Educación Física', 'Desarrollo físico y bienestar.', 2, 28),
('Química General', 'Conceptos básicos de química.', 3, 25),
('Física I', 'Introducción a la física mecánica.', 4, 24),
('Lengua y Literatura', 'Análisis y comprensión textual.', 3, 23),
('Informática Básica', 'Manejo de herramientas ofimáticas.', 2, 29),
('Filosofía Moderna', 'Corrientes filosóficas desde el siglo XVII.', 3, 30),
('Ciencias Naturales', 'Fenómenos naturales y medio ambiente.', 3, 22),
('Geografía', 'Estudio de la Tierra y su división.', 2, 26),
('Matemáticas Discretas', 'Lógica y conjuntos aplicados.', 3, 21),
('Química Orgánica', 'Estudio de compuestos orgánicos.', 3, 25),
('Sistemas y Redes', 'Fundamentos de redes y arquitectura.', 4, 29),
('Lectura Crítica', 'Técnicas de comprensión lectora.', 2, 23);


-- ==========================
-- Inscripciones (cada estudiante inscrito en 2 cursos diferentes)
-- ==========================
INSERT INTO inscripcion (id_estudiante, id_curso, fecha_inscripcion) VALUES
(1, 1, '2024-01-15'),  (1, 2, '2024-01-15'),
(2, 3, '2024-01-16'),  (2, 4, '2024-01-16'),
(3, 5, '2024-01-17'),  (3, 6, '2024-01-17'),
(4, 7, '2024-01-18'),  (4, 8, '2024-01-18'),
(5, 9, '2024-01-19'),  (5, 10, '2024-01-19'),
(6, 11, '2024-01-20'), (6, 12, '2024-01-20'),
(7, 13, '2024-01-21'), (7, 14, '2024-01-21'),
(8, 15, '2024-01-22'), (8, 1, '2024-01-22'),
(9, 2, '2024-01-23'),  (9, 3, '2024-01-23'),
(10, 4, '2024-01-24'), (10, 5, '2024-01-24'),
(11, 6, '2024-01-25'), (11, 7, '2024-01-25'),
(12, 8, '2024-01-26'), (12, 9, '2024-01-26'),
(13, 10, '2024-01-27'),(13, 11, '2024-01-27'),
(14, 12, '2024-01-28'),(14, 13, '2024-01-28'),
(15, 14, '2024-01-29'),(15, 15, '2024-01-29'),
(16, 1, '2024-01-30'), (16, 2, '2024-01-30'),
(17, 3, '2024-01-31'), (17, 4, '2024-01-31'),
(18, 5, '2024-02-01'), (18, 6, '2024-02-01'),
(19, 7, '2024-02-02'), (19, 8, '2024-02-02'),
(20, 9, '2024-02-03'), (20, 10, '2024-02-03');
