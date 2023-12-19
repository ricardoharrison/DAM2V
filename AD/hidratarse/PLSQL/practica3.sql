DROP TABLE TRIANGULOS;
CREATE OR REPLACE TYPE TIPO_TRIANGULO AS OBJECT (
	T_BASE NUMBER, 
	T_ALTURA NUMBER, 
	MEMBER FUNCTION AREA RETURN NUMBER);
/

CREATE OR REPLACE TYPE BODY TIPO_TRIANGULO 
AS 
	MEMBER FUNCTION AREA RETURN NUMBER
	IS 
	BEGIN 
		RETURN (T_BASE*T_ALTURA)/2; 
	END; 
END;
/	

CREATE TABLE TRIANGULOS (id NUMBER, triangulo TIPO_TRIANGULO);
INSERT INTO TRIANGULOS VALUES (1,TIPO_TRIANGULO(5, 6)); 
INSERT INTO TRIANGULOS VALUES (2,TIPO_TRIANGULO(10, 10));  

DECLARE
	T TIPO_TRIANGULO;
BEGIN
	FOR I IN (SELECT * FROM TRIANGULOS) LOOP
		T:=I.TRIANGULO;
		DBMS_OUTPUT.PUT_LINE('EL TRIANGULO CON ID: '||I.ID);
		DBMS_OUTPUT.PUT_LINE('CON BASE'||I.BASE);
		DBMS_OUTPUT.PUT_LINE('CON ALTURA'||I.ALTURA);
		DBMS_OUTPUT.PUT_LINE('CON AREA'||I.AREA);
	END LOOP;
END;
/

DECLARE 
	CURSOR C_CUR IS 
		SELECT ID, T.TRIANGULO.BASE B, T.TRIANGULO.ALTURA A, T.TRIANGULO.AREA SUPERF FROM TRIANGULOS T;
	V_CUR C_CUR %ROWTYPE;
BEGIN
	OPEN C_CUR;
	FETCH C_CUR INTO V_CUR;
	WHILE C_CUR%FOUND LOOP
		T:=I.TRIANGULO;
		DBMS_OUTPUT.PUT_LINE('EL TRIANGULO CON ID: '||V_CUR.ID);
		DBMS_OUTPUT.PUT_LINE('CON BASE'||V_CUR.B);
		DBMS_OUTPUT.PUT_LINE('CON ALTURA'||V_CUR.A);
		DBMS_OUTPUT.PUT_LINE('CON AREA'||V_CUR.SUPERF);
		FETCH C_CUR INTO V_CUR;
		CLOSE C_CUR;
	END LOOP;
END;
/

DECLARE
	T TIPO_TRIANGULO;
BEGIN
	FOR I IN (SELECT * FROM TRIANGULOS) LOOP
		T:=I.TRIANGULO;
		DBMS_OUTPUT.PUT_LINE('EL TRIANGULO CON ID: '||I.ID);
		DBMS_OUTPUT.PUT_LINE('CON BASE'||I.BASE);
		DBMS_OUTPUT.PUT_LINE('CON ALTURA'||I.ALTURA);
		DBMS_OUTPUT.PUT_LINE('CON AREA'||I.AREA);
	END LOOP;
END;
/

DECLARE 
	CURSOR C_CUR IS 
		SELECT ID, T.TRIANGULO.BASE B, T.TRIANGULO.ALTURA A, T.TRIANGULO.AREA() SUPERF FROM TRIANGULOS T;
	V_CUR C_CUR %ROWTYPE;
BEGIN
	FOR I IN C_CUR IS LOOP 
		DBMS_OUTPUT.PUT_LINE('ID: '||I.ID);
		DBMS_OUTPUT.PUT_LINE('BASE'||I.B);
		DBMS_OUTPUT.PUT_LINE('ALTURA'||I.A);
		DBMS_OUTPUT.PUT_LINE('AREA'||I.SUPERF);
		CLOSE C_CUR;
	END LOOP;
END;
/