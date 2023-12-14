--1
CREATE OR REPLACE TYPE cubo AS OBJECT (
    largo INTEGER,
    ancho INTEGER,
    alto INTEGER,
    MEMBER FUNCTION superficie RETURN NUMBER,
    MEMBER FUNCTION volumen RETURN NUMBER,
    MEMBER PROCEDURE mostrar,
    STATIC PROCEDURE nuevoCubo(V_largo INTEGER, V_ancho INTEGER, V_alto INTEGER)
) NOT FINAL;
/

create table cubos of cubo;
drop table cubos;


--2
CREATE OR REPLACE TYPE BODY cubo AS
    MEMBER FUNCTION superficie RETURN NUMBER IS
    BEGIN
        RETURN 2 * (largo * ancho + largo * alto + ancho * alto);
    END superficie;

    MEMBER FUNCTION volumen RETURN NUMBER IS
    BEGIN
        RETURN largo * ancho * alto;
    END volumen;

    MEMBER PROCEDURE mostrar IS
    BEGIN
        DBMS_OUTPUT.PUT_LINE('Largo: ' || largo);
        DBMS_OUTPUT.PUT_LINE('Ancho: ' || ancho);
        DBMS_OUTPUT.PUT_LINE('Alto: ' || alto);
        DBMS_OUTPUT.PUT_LINE('Superficie: ' || superficie);
        DBMS_OUTPUT.PUT_LINE('Volumen: ' || volumen);
    END mostrar;
    
    STATIC PROCEDURE nuevoCubo(v_largo INTEGER, v_ancho INTEGER, v_alto INTEGER) IS
        micubo cubo;
    BEGIN
        micubo := cubo(v_largo, v_ancho, v_alto);
        insert into cubos values(micubo);
        DBMS_OUTPUT.PUT_LINE('Creating a new cube with dimensions: Largo=' || v_largo || ', Ancho=' || v_ancho || ', Alto=' || v_alto);
    END nuevoCubo;
END;
/

--3
BEGIN
    cubo.nuevoCubo(1, 8, 1);
END;
/