--ejercicio1

DROP TABLE temp;
CREATE TABLE temp (
  employee_id NUMBER(4, 0),
  salary NUMBER(7, 2),
  last_name VARCHAR2(50),
  message VARCHAR2(50)
);

DECLARE
  v_employee_id   employee.employee_id%TYPE;
  v_salary       employee.salary%TYPE;
  v_last_name    employee.last_name%TYPE;
  
BEGIN
  FOR emp_record IN (SELECT employee_id, salary, last_name FROM employee where salary >= 3000) LOOP
    v_employee_id := emp_record.employee_id;
    v_salary := emp_record.salary;
    v_last_name := emp_record.last_name;    

    INSERT INTO temp (employee_id, salary, last_name, message)
    VALUES (v_employee_id, v_salary, v_last_name, 'salary changes');
  END LOOP;
END;
/


--ejercicio2
DROP TABLE temp;
CREATE TABLE temp (
    id_cliente NUMBER,
    nombre VARCHAR2(50),
    pedidos NUMBER,
    total NUMBER
);
DECLARE
    codigo customer.customer_id%TYPE := '&codigo';
    nombre customer.name%TYPE;
    pedidos NUMBER;
    total sales_order.total%TYPE;
    existe NUMBER;
BEGIN
    SELECT COUNT(*) INTO existe FROM customer WHERE customer_id = codigo;
    IF existe != 1 THEN
        dbms_output.put_line('No existe cliente con ese ID');
    ELSE
        SELECT customer_id, name INTO codigo, nombre FROM customer WHERE customer_id = codigo;
        SELECT COUNT(*) INTO pedidos FROM sales_order WHERE customer_id = codigo;
        SELECT SUM(total) INTO total FROM sales_order WHERE customer_id = codigo;
        INSERT INTO temp VALUES(codigo, nombre, pedidos, total);
    end if;
end;
/

--ejercicio3
DROP TABLE temp;
CREATE TABLE temp (
    codigo_empleado NUMBER,
    nombre_empleado VARCHAR2(50),
    job NUMBER
);
DECLARE
    TYPE t_reg_emple IS RECORD (
        codigo_empleado NUMBER,
        nombre_empleado VARCHAR2(50),
        job NUMBER
    );
    
    v_empleado t_reg_emple;
    v_codigo_empleado NUMBER := 7782;
BEGIN
    SELECT e.employee_id, e.last_name, e.job_id
    INTO v_empleado
    FROM employee e
    WHERE e.employee_id = v_codigo_empleado;
    
    INSERT INTO temp (codigo_empleado, nombre_empleado, job)
    VALUES (v_empleado.codigo_empleado, v_empleado.nombre_empleado, v_empleado.job);
END;
/

--ejercicio4
DROP TABLE temp;
CREATE TABLE temp (
    campo1 NUMBER,
    campo2 DATE,
    campo3 DATE,
    campo4 NUMBER,
    campo5 NUMBER
);
DECLARE
    contador NUMBER := 0;
BEGIN
    FOR i IN 1..50 LOOP
        INSERT INTO temp (campo1, campo2, campo3, campo4, campo5)
        VALUES (i, SYSDATE, SYSDATE, i * 2, i * 3);
        
        contador := contador + 1;
    END LOOP;
END;
/
DROP TABLE students;
CREATE TABLE students(
    nombre VARCHAR2(50),
    centro VARCHAR2(50),
    fin_beca DATE
);
INSERT INTO students VALUES('JOSELE', 'IES JUAN DE LA CIERVA', SYSDATE+90);
INSERT INTO students VALUES('JUANILLO', 'IES SANTA TERESA', SYSDATE+180);

--ejercicio5
CREATE OR REPLACE TYPE cliente_type AS OBJECT (
    nombre VARCHAR2(50)
);

CREATE OR REPLACE TYPE cliente_table AS TABLE OF cliente_type;

CREATE TABLE temp_cliente (
        indice NUMBER,
        nombre_cliente VARCHAR2(50)
    );
DECLARE
    clientes cliente_table := cliente_table(
        cliente_type('Cliente 1'),
        cliente_type('Cliente 2'),
        cliente_type('Cliente 3'),
        cliente_type('Cliente 4'),
        cliente_type('Cliente 5')
    );
BEGIN
    FOR i IN 1..clientes.COUNT LOOP
        INSERT INTO temp_cliente (indice, nombre_cliente)
        VALUES (i, clientes(i).nombre);
    END LOOP;

    SELECT * FROM temp_cliente;
END;
/

--ejercicio6
DROP TABLE temp;
CREATE TABLE temp (
    customer_id NUMBER,
    customer_name VARCHAR2(50),
    salesperson_id NUMBER
);

DECLARE
    v_customer_id customer.customer_id%TYPE;
    v_customer_name customer.name%TYPE;
    v_salesperson_id customer.salesperson_id%TYPE;
BEGIN
    FOR c IN (SELECT customer_id, name, salesperson_id FROM customer) LOOP
        v_customer_id := c.customer_id;
        v_customer_name := c.name;
        v_salesperson_id := c.salesperson_id;
        
        INSERT INTO temp (customer_id, customer_name, salesperson_id)
        VALUES (v_customer_id, v_customer_name, v_salesperson_id);
    END LOOP;
END;
/

DECLARE
    v_customer_id customer.customer_id%TYPE;
    v_customer_name customer.name%TYPE;
    v_salesperson_id customer.salesperson_id%TYPE;
    v_rowcount NUMBER := 0;
BEGIN
    WHILE v_rowcount < 3 LOOP
        SELECT customer_id, name, salesperson_id
        INTO v_customer_id, v_customer_name, v_salesperson_id
        FROM customer
        WHERE ROWNUM = v_rowcount + 1;
        
        INSERT INTO temp (customer_id, customer_name, salesperson_id)
        VALUES (v_customer_id, v_customer_name, v_salesperson_id);
        
        v_rowcount := v_rowcount + 1;
    END LOOP;
END;
/

DECLARE
    v_customer_id customer.customer_id%TYPE;
    v_customer_name customer.name%TYPE;
    v_salesperson_id customer.salesperson_id%TYPE;
BEGIN
    LOOP
        SELECT customer_id, name, salesperson_id
        INTO v_customer_id, v_customer_name, v_salesperson_id
        FROM customer
        WHERE ROWNUM = 1; 

        INSERT INTO temp (customer_id, customer_name, salesperson_id)
        VALUES (v_customer_id, v_customer_name, v_salesperson_id);

        EXIT WHEN NO_DATA_FOUND;
    END LOOP;
END;
/

--ejercicio8
CREATE OR REPLACE PROCEDURE insert_order(
    p_order_id NUMBER,
    p_order_date DATE,
    p_customer_id NUMBER,
    p_ship_date DATE,
    p_total NUMBER
)
IS
BEGIN
    INSERT INTO sales_order (ORDER_ID, ORDER_DATE, CUSTOMER_ID, SHIP_DATE, TOTAL)
    VALUES (p_order_id, p_order_date, p_customer_id, p_ship_date, p_total);
    
    DBMS_OUTPUT.PUT_LINE('Pedido insertado correctamente.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error al insertar el pedido: ' || SQLERRM);
END;
/

--ejercicio10
DROP TABLE temp;
CREATE TABLE temp (
    employee_id NUMBER,
    old_job_id NUMBER,
    new_job_id NUMBER
);
CREATE PROCEDURE CambiarOficio(in_employee_id NUMBER, in_new_job_id NUMBER)
IS
old_job_id NUMBER;
BEGIN
    SELECT job_id INTO old_job_id
    FROM employee
    WHERE employee_id = in_employee_id;

    INSERT INTO temp (employee_id, old_job_id, new_job_id)
    VALUES (in_employee_id, old_job_id, in_new_job_id);

    UPDATE employee
    SET job_id = in_new_job_id
    WHERE employee_id = in_employee_id;
END;
/

BEGIN
    CambiarOficio(7369, 670);
END;
/

--ejercicio11
create or replace function calcSalarioAnual(salarioMensual number, comision number)
return number
is
salarioAnual number;
begin
   salarioAnual := (nvl(salarioMensual, 0) * 12) + (nvl(comision, 0) * 12);
   return salarioAnual;
end;
/

--ejercicio13
create table funciones (
    funcion varchar2(50)
);
insert into funciones values('Enfermeras');
insert into funciones values('Médicos');
insert into funciones values('Conserjes');
insert into funciones values('Limpiadores');
select * from funciones;
create or replace trigger funciones_trigger
after update on funciones
begin
  dbms_output.put_line('Se ha hecho un cambio en la tabla FUNCIONES');
end;
/

--ejercicio14
CREATE TABLE cambios_detalles (
  usuario VARCHAR2(50),
  fecha_modificacion DATE,
  codigo_pedido NUMBER,
  mensaje VARCHAR2(100)
);

CREATE OR REPLACE TRIGGER detalles_trigger
AFTER INSERT OR UPDATE OR DELETE ON detalles
FOR EACH ROW
DECLARE
  v_mensaje VARCHAR2(100);
BEGIN
  IF INSERTING THEN
    v_mensaje := 'Detalle dado de alta';
  ELSIF UPDATING THEN
    v_mensaje := 'Detalle modificado';
  ELSIF DELETING THEN
    v_mensaje := 'Detalle borrado';
  END IF;

  INSERT INTO cambios_detalles (usuario, fecha_modificacion, codigo_pedido, mensaje)
  VALUES (USER, SYSDATE, :OLD.codigo_pedido, v_mensaje);
END;
/


--ejercicio15
drop table temp;
create table temp(
  emp_id number,
  emp_name varchar2(50),
  descrip varchar2(50)
);
create or replace trigger subida_salario after update on employee
for each row
begin
    if :old.salary < :new.salary then
        insert into temp values (:new.employee_id, :new.last_name, 'Subida de salario');
    end if;
end;
/

--ejercicio16
create or replace trigger borrado_empleado after delete on employee
for each row
begin
    insert into temp values (:old.employee_id, :old.last_name, :old.department_id);
end;
/

--ejercicio18
DROP TABLE temp;
CREATE TABLE temp(cod NUMBER, msg VARCHAR2(50));
DECLARE
    cod_cliente customer.customer_id%type;
    existe number;
BEGIN
cod_cliente :=  '&cod_cliente';
select count(*) into existe from customer where customer_id = to_number(cod_cliente);
    IF existe = 1 THEN
        INSERT INTO temp VALUES (TO_NUMBER(cod_cliente), 'EXISTE');
    ELSE
        INSERT INTO temp VALUES (TO_NUMBER(cod_cliente), 'NO EXISTE');
    END IF;    
EXCEPTION
 WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

--ejercicio19
--este no se puede hacer con la BBDD que manejamos

--ejercicio20
DROP TABLE temp;
CREATE TABLE temp (
    id_cliente NUMBER,
    nombre VARCHAR2(50),
    pedidos NUMBER,
    total NUMBER
);
DECLARE
    codigo customer.customer_id%TYPE := '&codigo';
    nombre customer.name%TYPE;
    pedidos NUMBER;
    total sales_order.total%TYPE;
    existe_cliente NUMBER;
BEGIN
    SELECT COUNT(*) INTO existe_cliente FROM customer WHERE customer_id = codigo;
    SELECT COUNT(*) INTO existe_pedido FROM sales_order WHERE customer_id = codigo;
    IF existe_cliente != 1 THEN
        RAISE_APPLICATION_ERROR(-200001, 'No existe ese cliente');
    ELSIF existe_pedido < 1 THEN
        RAISE_APPLICATION_ERROR(-200002, 'No existen pedidos de ese cliente');
    ELSE
        SELECT customer_id, name INTO codigo, nombre FROM customer WHERE customer_id = codigo;
        SELECT COUNT(*) INTO pedidos FROM sales_order WHERE customer_id = codigo;
        SELECT SUM(total) INTO total FROM sales_order WHERE customer_id = codigo;
        INSERT INTO temp VALUES(codigo, nombre, pedidos, total);
    end if;
end;
/

--ejercicio21
create or replace trigger borrado_empleado after delete on employee
for each row
begin
    dbms_output.put_line('Empleado ' || :old.last_name || ' con número ' || :old.employee_id || ' eliminado');
end;
/

--ejercicio22
CREATE OR REPLACE PROCEDURE modificar_salario(p_employee_id IN employee.employee_id%TYPE) IS
    v_employee_count NUMBER;
    v_job_id employee.job_id%TYPE;
    v_function job.function%TYPE;
    v_salary_increase NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_employee_count
    FROM employee
    WHERE manager_id = p_employee_id;
    
    SELECT function
    INTO v_function
    FROM job
    WHERE job_id = (SELECT job_id FROM employee WHERE employee_id = p_employee_id);   

    IF v_function = 'PRESIDENT' THEN
        v_salary_increase := 30;
    ELSIF v_employee_count = 0 THEN
        v_salary_increase := 50;
    ELSIF v_employee_count = 1 THEN
        v_salary_increase := 80;
    ELSIF v_employee_count = 2 THEN
        v_salary_increase := 100;
    ELSE
        v_salary_increase := 110;
    END IF;

    UPDATE employee
    SET salary = salary + v_salary_increase
    WHERE employee_id = p_employee_id;

END;
/

--ejercicio23
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hola mundo');
END;
/
DECLARE
    texto VARCHAR2(20) := 'Hola mundo';
    texto_al_reves VARCHAR2(20);
BEGIN
    FOR i IN REVERSE 1..LENGTH(texto)
    LOOP
        texto_al_reves := texto_al_reves || SUBSTR(texto, i, 1);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE(texto_al_reves);
END;
/

--ejercicio24
DECLARE
    emp_name VARCHAR2(50);
    manager_name VARCHAR2(50);
    
    CURSOR emp_cursor IS
        SELECT e.last_name AS emp, m.last_name AS mng
        FROM employee e
        JOIN employee m ON m.employee_id = e.manager_id;
BEGIN
    OPEN emp_cursor;
    FETCH emp_cursor INTO emp_name, manager_name;
    
    WHILE emp_cursor%FOUND LOOP
        dbms_output.put_line(emp_name || ' | ' || manager_name);
        FETCH emp_cursor INTO emp_name, manager_name;
    END LOOP;
    
    CLOSE emp_cursor;
END;
/


--ejercicio25
declare
    dpt_id number;
    counter number := 1;
begin
    dpt_id := &department_id;
    for i in (select * from employee where department_id = dpt_id) loop
        dbms_output.put_line(counter || ': ' || i.last_name);
        counter := counter + 1;
    end loop;
end;
/

--ejercicio27










select * from temp;








select * from temp;






