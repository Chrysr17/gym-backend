CREATE DATABASE gimnasio_db;
USE gimnasio_db;
-- ==========================
-- TABLA USUARIO (login y roles)
-- ==========================
CREATE TABLE usuario (
    usuario_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol ENUM('ADMIN', 'RECEPCIONISTA','CLIENTE') NOT NULL
);

INSERT INTO usuario (nombre_usuario, password, rol) VALUES
('admin', '1234', 'ADMIN'),
('recep1', '1234', 'RECEPCIONISTA'),
('cliente1', '1234', 'CLIENTE');

-- ==========================
-- TABLA SEDE
-- ==========================
CREATE TABLE sede (
    sede_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200) NOT NULL
);

INSERT INTO sede (nombre, direccion) VALUES
('Sede Central', 'Av. Principal 123 - Lima'),
('Sede Norte', 'Av. Los Olivos 456 - Lima'),
('Sede Sur', 'Av. Primavera 789 - Lima');

-- ==========================
-- TABLA CLIENTE
-- ==========================
CREATE TABLE cliente (
    cliente_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(100),
    direccion VARCHAR(200),
    sede_id INT NOT NULL,
    fecha_pago DATE,
    mensualidad DECIMAL(10,2),
    descripcion TEXT,
    CONSTRAINT fk_cliente_sede FOREIGN KEY (sede_id) REFERENCES sede(sede_id)
);

INSERT INTO cliente (nombre, dni, telefono, correo, direccion, sede_id, fecha_pago, mensualidad, descripcion) VALUES
('Carlos Pérez', '12345678', '987654321', 'carlos@gmail.com', 'Calle A 101', 1, '2025-09-01', 150.00, 'Cliente nuevo, inscrito en musculación.'),
('María Gómez', '87654321', '912345678', 'maria@gmail.com', 'Calle B 202', 2, '2025-09-05', 200.00, 'Cliente interesada en clases de spinning.'),
('Luis Torres', '11223344', '998877665', 'luis@gmail.com', 'Calle C 303', 1, '2025-09-03', 180.00, 'Cliente regular, plan musculación + cardio.'),
('Ana Rodríguez', '55667788', '987123456', 'ana@gmail.com', 'Calle D 404', 3, '2025-09-02', 220.00, 'Cliente premium con entrenador personal.');

SELECT * FROM cliente;

-- ==========================
-- TABLA PAGO
-- ==========================
CREATE TABLE pago (
    pago_id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    fecha DATE NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    estado ENUM('Pagado','Pendiente') DEFAULT 'Pendiente',
    CONSTRAINT fk_pago_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id)
);

INSERT INTO pago (cliente_id, fecha, monto, estado) VALUES
(1, '2025-09-01', 150.00, 'Pagado'),
(2, '2025-09-05', 200.00, 'Pendiente'),
(3, '2025-09-03', 180.00, 'Pagado'),
(4, '2025-09-02', 220.00, 'Pagado');


-- ==========================
-- TABLA MAQUINA
-- ==========================
CREATE TABLE maquina (
    maquina_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    imagen VARCHAR(255), -- puede ser URL o path local
    estado ENUM('Operativa','Mantenimiento','Dañada') DEFAULT 'Operativa',
    sede_id INT NOT NULL,
    CONSTRAINT fk_maquina_sede FOREIGN KEY (sede_id) REFERENCES sede(sede_id)
);
INSERT INTO maquina (nombre, descripcion, imagen, estado, sede_id) VALUES
('Caminadora', 'Cinta de correr eléctrica', 'caminadora.jpg', 'Operativa', 1),
('Bicicleta Estática', 'Bicicleta fija para cardio', 'bicicleta.jpg', 'Operativa', 2),
('Máquina de Pesas', 'Equipo multifunción de pesas', 'pesas.jpg', 'Mantenimiento', 1),
('Remadora', 'Máquina de remo para entrenamiento', 'remadora.jpg', 'Operativa', 3),
('Elíptica', 'Máquina para cardio de bajo impacto', 'eliptica.jpg', 'Dañada', 2);


-- ==========================
-- TABLA EMPLEADO
-- ==========================
CREATE TABLE empleado (
    empleado_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    cargo ENUM('Recepcionista','Entrenador','Limpieza') NOT NULL,
    sede_id INT NOT NULL,
    CONSTRAINT fk_empleado_sede FOREIGN KEY (sede_id) REFERENCES sede(sede_id)
);
	
INSERT INTO empleado (nombre, dni, telefono, cargo, sede_id) VALUES
('Pedro López', '44556677', '999111222', 'Recepcionista', 1),
('Sofía Martínez', '77889900', '988222333', 'Entrenador', 1),
('José Ramírez', '33445566', '977333444', 'Limpieza', 2),
('Lucía Fernández', '88990011', '966444555', 'Entrenador', 3),
('Raúl Castro', '22334455', '955555666', 'Recepcionista', 2);
