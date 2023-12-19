CREATE TABLE
    HABILIDAD
    (
        NOMBRE      VARCHAR(50) NOT NULL,
        DESCRIPCION VARCHAR(50),
        PRIMARY KEY (NOMBRE)
    )
    COMMENT='Habilidades con su descripción.';
    
CREATE TABLE
    POKEMON
    (
        ENTRADA         INT(50) NOT NULL,
        NOMBRE          VARCHAR(50) NOT NULL,
        TIPO_PRINCIPAL  VARCHAR(50),
        TIPO_SECUNDARIO VARCHAR(50),
        HABILIDAD       VARCHAR(50),
        REGION          VARCHAR(50),
        ALTURA          FLOAT,
        PESO            FLOAT,
        CONSTRAINT PRIMARY KEY (ENTRADA),
        CONSTRAINT HAB_FOREIGN_KEY FOREIGN KEY (HABILIDAD) REFERENCES HABILIDAD (NOMBRE)
    )
    COMMENT = 'Pokemon con sus datos.';