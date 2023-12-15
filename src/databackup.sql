-- SOURCE C:/Users/52228/Documents/ProjectoSQL/databackup.sql;

INSERT INTO Puestos (puesto, salario) VALUES
('Gerente', 50000.00),
('Asistente', 25000.00),
('Mostrador', 20000.00);

INSERT INTO Empleados (paterno, materno, nombre, idPuestos, direccion, telefono, contratacionFecha, turno) VALUES
('Garcia', 'Hernandez', 'Ana', 1, 'Calle 123', '555-1111', '2022-01-15', 'Matutino'),
('Lopez', 'Martinez', 'Carlos', 3, 'Calle 456', '555-2222', '2022-02-20', 'Vespertino'),
('Gómez', 'Rodriguez', 'Laura', 3, 'Calle 789', '555-3333', '2022-03-25', 'Nocturno'),
('Hernandez', 'Gonzolez', 'Miguel', 3, 'Calle 987', '555-4444', '2022-04-30', 'Matutino'),
('Martinez', 'Lopez', 'Sofia', 2, 'Calle 654', '555-5555', '2022-05-10', 'Vespertino');

INSERT INTO Usuarios (usuario, contrasenia) VALUES
('ana123', 'claveAna123'),
('carlos456', 'claveCarlos456'),
('laura789', 'claveLaura789'),
('miguel987', 'claveMiguel987'),
('sofia654', 'claveSofia654');


INSERT INTO Clientes (paterno, materno, nombre, calle, colonia, localidad, telefono)
VALUES ('Hernandez', 'Gomez', 'Carlos', 'Reforma', 'Juarez', 'CDMX', '5551234567');

INSERT INTO Clientes (paterno, materno, nombre, calle, colonia, localidad, telefono)
VALUES ('Gonzalez', 'Mendoza', 'Laura', 'Insurgentes', 'Condesa', 'CDMX', '5559876543');

INSERT INTO Clientes (paterno, materno, nombre, calle, colonia, localidad, telefono)
VALUES ('Torres', 'Lopez', 'Maria', 'Carne asada', 'Fundidora', 'Mty', '5555555555');

INSERT INTO Clientes (paterno, materno, nombre, calle, colonia, localidad, telefono)
VALUES ('Sanchez', 'Garcia', 'Luis', 'Zaragoza', 'Del Valle', 'GDL', '5556667777');

INSERT INTO Clientes (paterno, materno, nombre, calle, colonia, localidad, telefono)
VALUES ('Ramirez', 'Martinez', 'Carmen', 'Madero', 'Centro', 'Puebla', '5554443333');

INSERT INTO Prendas (prenda, costoPlancha, costoTinto) VALUES
('Camisa', 10.5, 15.0),
('Pantalón', 8.0, 12.0),
('Vestido', 15.0, 20.0),
('Traje de baño', 5.5, 8.0),
('Chamarra', 12.0, 18.0);

INSERT INTO Servicio (fecha, idCliente, noPersonal) VALUES
('2023-01-15', 3, 2),
('2023-02-10', 1, 2),
('2023-03-25', 4, 1),
('2023-04-05', 2, 3);


-- Servicio 1

INSERT INTO DetalleServicio (idPrenda, folio) VALUES
(1, 1),
(2, 1),
(3, 1);

-- Servicio 2

INSERT INTO DetalleServicio (idPrenda, folio) VALUES
(1, 2),
(2, 2);

-- Servicio 3

INSERT INTO DetalleServicio (idPrenda, folio) VALUES
(3, 3),
(4, 3);

-- Servicio 4

INSERT INTO DetalleServicio (idPrenda, folio) VALUES
(5, 4),
(1, 4);

