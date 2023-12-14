
-- 1
CREATE OR REPLACE TYPE tabla_hijos AS TABLE OF VARCHAR2(30);
/

-- 2
CREATE TABLE empleado (
    Idemp NUMBER,
    Nombre VARCHAR2(30),
    Apellidos VARCHAR2(30),
    Direcciones tabla_anidada) NESTED TABLE Direcciones STORE AS t_direcciones_tabla;
/
-- 3
SELECT object_name, object_type FROM user_objects;

-- 4  
SELECT segment_name, segment_type FROM user_segments;

-- 5
INSERT INTO empleado (Idemp, Nombre, Apellidos, Hijos)
VALUES (1, 'Fernando', 'Moreno', tabla_hijos('Elena', 'Pablo'));

INSERT INTO empleado (Idemp, Nombre, Apellidos, Hijos)
VALUES (2, 'David', 'Sanchez', tabla_hijos('Carmen', 'Candela'));

-- 6
SELECT * FROM empleado;

-- 7
SELECT * FROM TABLE(SELECT e.Hijos FROM empleado e WHERE e.Idemp = 1);

-- 8
UPDATE empleado e
SET e.Hijos = tabla_hijos('Carmen', 'Candela', 'Cayetana')
WHERE e.Idemp = 1;

-- 9
SELECT Idemp, Hijos FROM empleado WHERE Idemp IN (1, 2);