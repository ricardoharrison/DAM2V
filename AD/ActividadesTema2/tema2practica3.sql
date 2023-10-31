CREATE OR REPLACE TYPE cubo AS OBJECT (
    largo INTEGER,
    ancho INTEGER,
    alto INTEGER,
    MEMBER FUNCTION superficie RETURN NUMBER,
    MEMBER FUNCTION volumen RETURN NUMBER,
    MEMBER PROCEDURE mostrar
) NOT FINAL;
/

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
END;
/

--3
CREATE TABLE cubos OF cubo;

--4
INSERT INTO cubos VALUES (cubo(10, 10, 10));
INSERT INTO cubos VALUES (cubo(3, 4, 5));

--5
select * from cubos;

--6
select c.volumen(), c.superficie() from cubos c where largo = 10;

--7
DECLARE
    micubo cubo;
BEGIN
    SELECT value(c) INTO micubo FROM cubos c WHERE largo = 10;
    micubo.mostrar;
END;
/