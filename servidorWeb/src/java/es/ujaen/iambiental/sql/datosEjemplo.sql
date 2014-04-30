
/** DEPENDENCIAS */
INSERT INTO iambiental.dependencias (descripcion, nombre) 
	VALUES ('Salón de la planta de arriba', 'Salón');
INSERT INTO iambiental.dependencias (descripcion, nombre) 
	VALUES ('Dormitorio de Manuel', 'Dormitorio 1');

/** ACTUADORES */
INSERT INTO iambiental.actuadores (dato, descripcion, estado, fecha, ip, puerto, dependencia_id, tipo) 
	VALUES (5.0, 'Interruptor de la luz', 0, '2014-04-29 12:20:16.0', '', NULL, 1, 1);
INSERT INTO iambiental.actuadores (dato, descripcion, estado, fecha, ip, puerto, dependencia_id, tipo) 
	VALUES (22.0, 'Termostato', 1, '2014-04-30 12:12:21.0', NULL, NULL, 1, 0);
INSERT INTO iambiental.actuadores (dato, descripcion, estado, fecha, ip, puerto, dependencia_id, tipo) 
	VALUES (0.0, 'Interruptor puerta cocina', 1, '2014-04-30 12:15:03.0', NULL, NULL, 1, 1);



/** SENSORES */
INSERT INTO iambiental.sensores (dato, descripcion, estado, fecha, ip, puerto, tipo, dependencia_id) 
	VALUES (32.0, 'Sensor de temperatura de habitación', 0, '2014-04-29 12:39:03.0', NULL, NULL, 1, 1);
INSERT INTO iambiental.sensores (dato, descripcion, estado, fecha, ip, puerto, tipo, dependencia_id) 
	VALUES (28.0, 'Sensor de luz del salón', 1, '2014-04-29 13:14:53.0', NULL, NULL, 0, 1);
