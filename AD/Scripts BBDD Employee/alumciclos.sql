CREATE TABLE ciclo (
 codciclo              varCHAR(3)  NOT NULL,
 denciclo               varCHAR(70),
 grado                 varCHAR(28),
 PRIMARY KEY (codciclo));

INSERT INTO ciclo VALUES ('DAI','Desarrollo de aplicaciones informaticas','superior');
INSERT INTO ciclo VALUES ('ASI','Administracion aplicaciones informaticas','superior');
INSERT INTO ciclo  VALUES ('ESI','Explotacion de sistemas informaticos','medio');
 
 
CREATE TABLE alumno (
 numexpdte varchar(4), 
 nombre varchar(25),
 dni varchar(9),
 ciclo varchar(3)
);

INSERT INTO alumno VALUES ('1', 'Maria Gomez', '22222222I', 'DAI');
INSERT INTO alumno VALUES ('2', 'Juan', '999999999T', 'ASI');
INSERT INTO alumno VALUES ('3', 'Antonio Gomez', '444444444U', 'DAI');
INSERT INTO alumno VALUES ('4', 'Ana Lopez', '888888888P', 'ASI');
INSERT INTO alumno VALUES ('5', 'Juan', '34444444W', 'ASI');
  
