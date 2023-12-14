
DELIMITER $

CREATE PROCEDURE InsertarCliente(
    IN p_paterno VARCHAR(10),
    IN p_materno VARCHAR(10),
    IN p_nombre VARCHAR(10),
    IN p_calle VARCHAR(20),
    IN p_colonia VARCHAR(15),
    IN p_localidad VARCHAR(15),
    IN p_telefono VARCHAR(10)
)
BEGIN
    DECLARE nombre_completo VARCHAR(30);
    SET nombre_completo = CONCAT(p_paterno," ", p_materno," ", p_nombre);

    IF NOT EXISTS (SELECT 1 FROM Clientes WHERE CONCAT(paterno, materno, nombre) = nombre_completo) THEN
        INSERT INTO Clientes (paterno, materno, nombre, calle, colonia, localidad, telefono)
        VALUES (p_paterno, p_materno, p_nombre, p_calle, p_colonia, p_localidad, p_telefono);

        INSERT INTO UpdateLog (operacion, nombre_completo, fecha_hora)
        VALUES ('Insercion', nombre_completo, CURRENT_TIMESTAMP);
    END IF;
END $

DELIMITER ;