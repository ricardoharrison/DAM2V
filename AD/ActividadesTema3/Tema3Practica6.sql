--1
CREATE OR REPLACE TYPE empleado AS OBJECT (
    rut VARCHAR2(10),
    nombre VARCHAR2(10),
    cargo VARCHAR2(9),
    fechaIng DATE,
    sueldo NUMBER(9),
    comision NUMBER(9),
    anticipo NUMBER(9),
    
    MEMBER FUNCTION sueldo_liquido RETURN NUMBER,
    
    MEMBER PROCEDURE aumento_sueldo(aumento NUMBER)
);
/

--2
CREATE OR REPLACE TYPE BODY empleado AS

    MEMBER FUNCTION sueldo_liquido RETURN NUMBER IS
    BEGIN
        RETURN sueldo + comision - anticipo;
    END sueldo_liquido;

    MEMBER PROCEDURE aumento_sueldo(aumento NUMBER) IS
    BEGIN
        sueldo := sueldo + aumento;
    END aumento_sueldo;
END;
/

--3
ALTER TYPE empleado ADD MEMBER PROCEDURE setAnticipo(anticipo NUMBER);
/

--4
CREATE OR REPLACE TYPE BODY empleado AS
    MEMBER FUNCTION sueldo_liquido RETURN NUMBER IS
    BEGIN
        RETURN sueldo + comision - anticipo;
    END sueldo_liquido;

    MEMBER PROCEDURE aumento_sueldo(aumento NUMBER) IS
    BEGIN
        sueldo := sueldo + aumento;
    END aumento_sueldo;

    MEMBER PROCEDURE setAnticipo(anticipo NUMBER) IS
    BEGIN
        self.anticipo := anticipo;
    END setAnticipo;
END;
/

--5
CREATE TABLE empleados of empleado;

--6
INSERT INTO empleados VALUES('1', 'Pepe', 'Director', sysdate, 2000, 500, 0);
INSERT INTO empleados VALUES('2', 'Juan', 'Vendedor', sysdate, 1000, 300, 0);
INSERT INTO empleados VALUES('3', 'Elena', 'Vendedor', sysdate, 1000, 400, 0);


--7
DECLARE
    dire empleado;
BEGIN
    select value(e) into dire from empleados e where e.rut = '1';
    dbms_output.put_line(dire.nombre || '   ' || dire.cargo || '   '  || 'sueldo: ' ||dire.sueldo || '   ' || 'sueldo líquido: ' || dire.sueldo_liquido());
    update empleados e set sueldo = sueldo + 400 where e.rut = '1';
    select value(e) into dire from empleados e where e.rut = '1';
    dbms_output.put_line(dire.nombre || '   ' || dire.cargo || '   '  || 'sueldo: ' ||dire.sueldo || '   ' || 'sueldo líquido: ' || dire.sueldo_liquido());

END;
/

--8
select sueldo, e.sueldo_liquido() from empleados e;