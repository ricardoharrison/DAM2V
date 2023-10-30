--1
CREATE OR REPLACE TYPE Direccion AS OBJECT (
    calle VARCHAR2(25),
    ciudad VARCHAR2(20),
    codigo_post NUMBER(5)
);
/

--2
CREATE OR REPLACE TYPE Persona AS OBJECT (
    codigo NUMBER,
    nombre VARCHAR2(35),
    direc Direccion,
    fecha_nac DATE
);
/

--3 & 4a
CREATE TABLE alumnos OF persona;

DECLARE
    dir direccion;
    pers persona;
    dir2 direccion;
    pers2 persona;
BEGIN
    dir := direccion('La Mina, 3', 'Guadalajara', 19001);
    pers := persona(1, 'Juan', dir, TO_DATE('1988-11-10', 'YYYY-MM-DD'));
    dir2 := direccion('Calle de la Caoba, 3', 'Madrid', 28005);
    pers2 := persona(2, 'Maria', dir2, TO_DATE('1994-04-01', 'YYYY-MM-DD'));


    INSERT INTO alumnos VALUES(pers);
    INSERT INTO alumnos VALUES(pers2);
END;
/


--4b
SELECT * FROM alumnos a where a.direc.ciudad = 'Guadalajara';

--4c
SELECT codigo, direc from alumnos;

--4d
UPDATE alumnos a SET a.direc.ciudad = LOWER(a.direc.ciudad)
WHERE a.direc.ciudad = 'Guadalajara';

--4e
select nombre, a.direc.calle from alumnos a;

--4f
DELETE FROM alumnos WHERE a.direc.ciudad = 'Guadalajara';