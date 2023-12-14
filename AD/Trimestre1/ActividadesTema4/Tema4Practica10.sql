CREATE OR REPLACE TYPE telefono AS OBJECT (
    Tipo VARCHAR2(30),
    Numero NUMBER
);
/

CREATE OR REPLACE TYPE listin AS TABLE OF telefono;
/

CREATE TABLE clientes (
    Id_cli NUMBER,
    Nombre VARCHAR2(30),
    Apellido VARCHAR2(30),
    Direccion VARCHAR2(30),
    Poblacion VARCHAR2(30),
    Provincia VARCHAR2(30),
    Telefonos listin) NESTED TABLE Telefonos STORE AS t_telefonos_tabla;

-- 4
INSERT INTO clientes (Id_cli, Nombre, Apellido, Direccion, Poblacion, Provincia, Telefonos)
VALUES (
    1, 'Francisco', 'Pérez', 'Sol', 'Madrid', 'Madrid',
    listin(
        telefono('Trabajo', 91345655),
        telefono('Personal', 6564433),
        telefono('Otra compañía', 654555555)
    )
);

-- 6
SELECT * FROM clientes;

-- 7
SELECT segment_name, segment_type FROM user_segments;

--8
SELECT object_name, object_type FROM user_objects;

-- 9
SELECT * FROM user_nested_tables;

-- 10
SELECT * FROM TABLE(SELECT c.Telefonos FROM clientes c WHERE c.Id_cli = 1);

-- 11
UPDATE clientes c
SET c.Telefonos = listin(
    telefono('Fijo', 934444444),
    telefono('Movil Personal', 65555555),
    telefono('Movil Empresa', 644444444)
)
WHERE c.Id_cli = 1;

-- 12
SELECT Id_cli, COLUMN_VALUE.Tipo, COLUMN_VALUE.Numero
FROM clientes, TABLE(clientes.Telefonos) COLUMN_VALUE;

-- 13
SELECT c.Nombre, c.Id_cli, t.Tipo, t.Numero
FROM clientes c, TABLE(c.Telefonos) t;
