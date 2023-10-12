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







select * from temp;






