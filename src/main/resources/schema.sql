CREATE TABLE IF NOT EXISTS tipo_documento(id INT NOT NULL AUTO_INCREMENT,
tipo_documento VARCHAR2(20),descripcion VARCHAR2(200),PRIMARY KEY(id));

INSERT into tipo_documento (tipo_documento,descripcion) values('cedula','cedula ciudadania');
INSERT into tipo_documento (tipo_documento,descripcion) values('cedula extranjeria','cedula extranjeria');

CREATE TABLE IF NOT EXISTS cliente(id INT NOT NULL AUTO_INCREMENT,
id_tipo_documento INT,
numero_identificacion VARCHAR2(15),
nombre VARCHAR2(50),
apellido VARCHAR2(50),
telefono VARCHAR2(10),
correo VARCHAR2(50),
activo BOOLEAN default true,
PRIMARY KEY(id));

CREATE TABLE IF NOT EXISTS vehiculo(id INT NOT NULL AUTO_INCREMENT,
id_cliente INT,
marca VARCHAR2(50),
placa VARCHAR2(10),
modelo VARCHAR2(4),
linea VARCHAR2(50),
PRIMARY KEY(id));

CREATE TABLE IF NOT EXISTS mantenimiento(id INT NOT NULL AUTO_INCREMENT,
id_vehiculo INT,
codigo_revision NUMERIC,
cambio_aceite BOOLEAN default false,
cambio_filtros BOOLEAN default false,
revision_frenos BOOLEAN default false,
precio NUMERIC(8,2),
otros VARCHAR2(200),
PRIMARY KEY(id));








