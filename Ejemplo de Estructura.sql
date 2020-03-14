DROP DATABASE IF EXISTS seguros_android;
CREATE DATABASE IF NOT EXISTS seguros_android;
USE seguros_android;

CREATE TABLE tipo_usuario (
    id INTEGER AUTO_INCREMENT NOT NULL,
    tipo VARCHAR(255) NOT NULL,
    borrado INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE usuario (
    dni VARCHAR(9) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(9) NOT NULL,
    contrasena VARCHAR(200) NOT NULL,
    borrado INTEGER NOT NULL DEFAULT 0,
    id_tipo_usuario INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_tipo_usuario FOREIGN KEY (id_tipo_usuario) REFERENCES tipo_usuario (id),
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
    dni_vendedor VARCHAR(9) NOT NULL,
    id_tipo_seguro INTEGER NOT NULL,
    borrado INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_cliente_seguro FOREIGN KEY (dni_cliente) REFERENCES usuario (dni),
    CONSTRAINT fk_vendedor_seguro FOREIGN KEY (dni_vendedor) REFERENCES usuario (dni),
    CONSTRAINT fk_tipo_seguro FOREIGN KEY (id_tipo_seguro) REFERENCES tipo_seguro (id),
    PRIMARY KEY (id)
);

INSERT INTO tipo_seguro (id, tipo) VALUES (1, "Vida"), (2, "Vivienda"), (3, "Coche");
INSERT INTO tipo_usuario (id, tipo) VALUES (1, "Cliente"), (2, "Vendedor"), (3, "Admin");