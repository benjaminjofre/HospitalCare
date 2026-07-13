CREATE TABLE Pacientes (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(100) NOT NULL,
                           rut VARCHAR(12) NOT NULL
);

INSERT INTO Pacientes (nombre, rut) VALUES ('Juan Pérez', '12.345.678-9');