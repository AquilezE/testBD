/*
Tablas: aunque se guarden todo en minusculas,escribamos las clausulas en mayusculas al inicio de las palabras, osea PascalCase
    EJEMPLO: Puestos, Usuarios, DetalleServicio
Atributos: usaremos escribamos la primera palabra en minuscula, y las siguientes capitalizalas, osea camelCase
    EJEMPLO: materno, idCliente, horaEntrega, horaEntregaServicio (este atributo no existe)
*/


-- Crear base de datos;
CREATE DATABASE IF NOT EXISTS tintoreria;
USE tintoreria;

-- Crear tablas para otras entidades

CREATE TABLE Puestos (
    idPuestos INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    puesto VARCHAR(15) NOT NULL,
    salario DOUBLE NOT NULL
);

CREATE TABLE Empleados (
    noPersonal INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    paterno VARCHAR(40) NOT NULL,
    materno VARCHAR(40) NOT NULL,
    nombre VARCHAR(40) NOT NULL,
    idPuestos INT NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    contratacionFecha DATE NOT NULL,
    turno VARCHAR(20) NOT NULL
);

CREATE TABLE Clientes (
    idCliente INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    paterno VARCHAR(40) NOT NULL,
    materno VARCHAR(40) NOT NULL,
    nombre VARCHAR(40) NOT NULL,
    calle VARCHAR(40) NOT NULL,
    colonia VARCHAR(45) NOT NULL,
    localidad VARCHAR(45) NOT NULL,
    telefono VARCHAR(10) NOT NULL
);

CREATE TABLE Usuarios (
    usuario VARCHAR(20) PRIMARY KEY NOT NULL,
    contrasenia VARCHAR(15) NOT NULL
);

CREATE TABLE Entrega (
    idPuestos INT NOT NULL, 
    fechaEntrega DATE NOT NULL,
    horaEntrega DATE NOT NULL,
    entregado BOOLEAN NOT NULL,
    notas VARCHAR(40) NULL,
    folio INT NOT NULL 
);

CREATE TABLE Prendas (
    idPrenda INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    prenda VARCHAR(15) NOT NULL,
    costoPlancha DOUBLE NOT NULL,
    costoTinto DOUBLE NOT NULL
);

CREATE TABLE DetalleServicio (
    idPrenda INT NOT NULL, 
    folio INT NOT NULL 
);

CREATE TABLE Servicio (
    folio INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    fecha DATE NOT NULL,
    idCliente INT NOT NULL,
    noPersonal INT NOT NULL 
);



CREATE TABLE UpdateLog (
    idUpdateLog INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    operacion VARCHAR(20) NOT NULL,
    nombre_completo VARCHAR(120) NOT NULL,
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
    
-- Aniadir llave foranea a 'DetalleServicio'

ALTER TABLE Servicio
    ADD FOREIGN KEY (idCliente) REFERENCES Clientes(idCliente);

ALTER TABLE Servicio
    ADD FOREIGN KEY (noPersonal) REFERENCES Empleados(noPersonal);

-- Aniadir llave foranea a 'DetalleServicio'

ALTER TABLE DetalleServicio
    ADD FOREIGN KEY (idPrenda) REFERENCES Prendas(idPrenda);

ALTER TABLE DetalleServicio
    ADD FOREIGN KEY (folio) REFERENCES Servicio(folio);

-- Aniadir llave foranea a 'Empleados'

ALTER TABLE Empleados
    ADD FOREIGN KEY (idPuestos) REFERENCES Puestos(idPuestos);

-- Aniadir llave foranea a 'Entrega'

ALTER TABLE Entrega
    ADD FOREIGN KEY (idPuestos) REFERENCES Puestos(idPuestos);
    
ALTER TABLE Entrega
    ADD FOREIGN KEY (folio) REFERENCES Servicio(folio);
    


-- SE CREA LA VISTA DEL PUNTO 2;


CREATE VIEW VistaTotalServicio AS
SELECT
    s.folio AS FolioServicio,
    s.fecha AS FechaServicio,
    s.idCliente,
    s.noPersonal,
    SUM(p.costoPlancha + p.costoTinto) AS TotalServicio
FROM
    Servicio s
JOIN
    DetalleServicio ds ON s.folio = ds.folio
JOIN
    Prendas p ON ds.idPrenda = p.idPrenda
GROUP BY
    s.folio, s.fecha, s.idCliente, s.noPersonal;


