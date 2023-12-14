--1
CREATE TYPE colec_hijos AS VARRAY(10) OF VARCHAR2(30);
/

--2
CREATE TABLE empleados (
    Idemp NUMBER,
    Nombre VARCHAR2(30),
    Apellidos VARCHAR2(30),
    Hijos colec_hijos
);
/

--3
DECLARE
    hijos_empleado_1 colec_hijos := colec_hijos('Luis', 'Ursula');
    hijos_empleado_2 colec_hijos := colec_hijos('José', 'Carlos', 'Pedro');
BEGIN
    INSERT INTO empleados (Idemp, Nombre, Apellidos, Hijos)
    VALUES (1, 'Francisco', 'Pérez', hijos_empleado_1);

    INSERT INTO empleados (Idemp, Nombre, Apellidos, Hijos)
    VALUES (2, 'Esperanza', 'Jiménez', hijos_empleado_2);
END;
/

--4
select * from empleados;

--5
SELECT e.Hijos
FROM empleados e
WHERE e.Idemp = 1;

--6
SELECT e.Hijos
FROM empleados e;

--7
DECLARE
    cantidad_hijos NUMBER;
BEGIN
    SELECT COUNT(column_value)
    INTO cantidad_hijos
    FROM TABLE((SELECT Hijos FROM empleados WHERE Idemp = 1));

    DBMS_OUTPUT.PUT_LINE('El empleado con idemp = 1 tiene ' || cantidad_hijos || ' hijos.');
END;
/

--8
DECLARE
    v_nombre_empleado empleados.Nombre%TYPE;
    v_hijos colec_hijos;
BEGIN
    SELECT Nombre, Hijos
    INTO v_nombre_empleado, v_hijos
    FROM empleados
    WHERE Idemp = 1;

    DBMS_OUTPUT.PUT_LINE('Nombre del empleado: ' || v_nombre_empleado);
    DBMS_OUTPUT.PUT_LINE('Nombre de los hijos:');

    FOR i IN 1..v_hijos.COUNT LOOP
        DBMS_OUTPUT.PUT_LINE(v_hijos(i));
    END LOOP;
END;
/

--9
DECLARE
    total_hijos NUMBER := 0;
BEGIN
    FOR emp IN (SELECT Hijos FROM empleados) LOOP
        total_hijos := total_hijos + emp.Hijos.COUNT;
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('El total de hijos de todos los empleados es: ' || total_hijos);
END;
/

--10
DECLARE
    v_hijos colec_hijos;
BEGIN
    SELECT Hijos INTO v_hijos
    FROM empleados
    WHERE Idemp = 1;

    v_hijos.EXTEND; 
    v_hijos(v_hijos.LAST) := 'Antonio';

    UPDATE empleados
    SET Hijos = v_hijos
    WHERE Idemp = 1;

    DBMS_OUTPUT.PUT_LINE('Se ha agregado el hijo "Antonio" al empleado con idemp=1.');
END;
/

--11
DECLARE
    v_hijos colec_hijos;
    i NUMBER := 1;
BEGIN
    SELECT Hijos INTO v_hijos
    FROM empleados
    WHERE Idemp = 1;

    FOR i IN 1..3 LOOP
        v_hijos.EXTEND;
        v_hijos(v_hijos.LAST) := 'Luis';
    END LOOP;

    UPDATE empleados
    SET Hijos = v_hijos
    WHERE Idemp = 1;

    DBMS_OUTPUT.PUT_LINE('Se han agregado 3 hijos más "Luis" al final de la colección del empleado con idemp=1.');
END;
/

--12
SELECT * FROM USER_VARRAYS;


--13
SELECT * FROM USER_TYPES WHERE TYPE_NAME = 'COLEC_HIJOS';