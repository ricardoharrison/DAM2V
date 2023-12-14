--1
CREATE TYPE colec_tipo_nombres_dept AS VARRAY(7) OF VARCHAR2(30);
/

--2
CREATE TABLE departamentos (
    Region VARCHAR(25),
    Nombres_dept colec_tipo_nombres_dept
);
/

--3
INSERT INTO departamentos (Region, Nombres_dept)
VALUES ('Europa', colec_tipo_nombres_dept('shipping', 'sales', 'finances'));

INSERT INTO departamentos (Region, Nombres_dept)
VALUES ('America', colec_tipo_nombres_dept('sales', 'finances', 'shipping'));

INSERT INTO departamentos (Region, Nombres_dept)
VALUES ('Asia', colec_tipo_nombres_dept('finances', 'payroll', 'shipping', 'sales'));
/

--4
select * from departamentos;

--5
DECLARE
    v_dept colec_tipo_nombres_dept := colec_tipo_nombres_dept('benefits', 'advertising', 'contracting', 'executive', 'marketing');
BEGIN
    UPDATE departamentos
    SET Nombres_dept = v_dept
    WHERE Region = 'Europa';

    DBMS_OUTPUT.PUT_LINE('Los nombres actualizados de los departamentos en Europa son:');
    
    FOR i IN 1..v_dept.COUNT LOOP
        DBMS_OUTPUT.PUT_LINE(v_dept(i));
    END LOOP;
END;
/

--6
DECLARE
    CURSOR c_regiones IS
        SELECT Region, Nombres_dept FROM departamentos;
    v_dept colec_tipo_nombres_dept;
BEGIN
    FOR reg IN c_regiones LOOP
        DBMS_OUTPUT.PUT_LINE('Región: ' || reg.Region);
        v_dept := reg.Nombres_dept;

        FOR i IN 1..v_dept.COUNT LOOP
            DBMS_OUTPUT.PUT_LINE(' - ' || v_dept(i));
        END LOOP;
    END LOOP;
END;
/