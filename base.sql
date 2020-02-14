DROP DATABASE IF EXISTS seguros_android;
CREATE DATABASE IF NOT EXISTS seguros_android;
USE seguros_android;

CREATE TABLE cliente (
    dni VARCHAR(9) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(9) NOT NULL,
    contrasena VARCHAR(200) NOT NULL,
    borrado INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY (dni)
);

CREATE TABLE tipo_seguro (
    id INTEGER AUTO_INCREMENT NOT NULL,
    tipo VARCHAR(255) NOT NULL,
    borrado INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE seguro (
    id INTEGER AUTO_INCREMENT NOT NULL,
    fecha_alta TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_baja TIMESTAMP,
    dni_cliente VARCHAR(9) NOT NULL,
    id_tipo_seguro INTEGER NOT NULL,
    borrado INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_cliente_seguro FOREIGN KEY (dni_cliente) REFERENCES cliente (dni)
        ON DELETE CASCADE,
    CONSTRAINT fk_tipo_seguro FOREIGN KEY (id_tipo_seguro) REFERENCES tipo_seguro (id),
    PRIMARY KEY (id)
);

INSERT INTO tipo_seguro (tipo) VALUES ("Vida"), ("Vivienda"), ("Coche");