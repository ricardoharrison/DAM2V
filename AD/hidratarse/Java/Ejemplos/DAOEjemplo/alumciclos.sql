DROP TABLE alumno;
DROP TABLE ciclo;
 
CREATE TABLE `ciclo` (
 codciclo              varCHAR(4)  NOT NULL,
 denciclo               varCHAR(70),
 grado                 varCHAR(28),
 PRIMARY KEY (codciclo));

INSERT INTO `ciclo`  VALUES ('DAM','Desarrollo de aplicaciones multiplataformas','superior');
INSERT INTO `ciclo`  VALUES ('ASIR','Administración Sistemas Infromaticos y Redes','superior');
INSERT INTO `ciclo`  VALUES ('DAW','Desarrollo de aplicaciones Web','superior');

 
 
CREATE TABLE `alumno` (
 numexpdte varchar(4)  , 
 nombre varchar(25),
 dni varchar(9),
 ciclo varchar(3),
 PRIMARY KEY (numexpdte),
 CONSTRAINT ciclo_FOREIGN_KEY FOREIGN KEY (ciclo) REFERENCES ciclo (codciclo)
);