

/** DEPENDENCIAS */
INSERT INTO iambiental.dependencias (descripcion, nombre) 
	VALUES ('Salón de la planta de arriba', 'Salón');
INSERT INTO iambiental.dependencias (descripcion, nombre) 
	VALUES ('Dormitorio de Manuel', 'Dormitorio 1');
INSERT INTO iambiental.dependencias (descripcion, nombre) 
	VALUES ('Cocina pequeña', 'Cocina');


/** ACTUADORES */
INSERT INTO iambiental.actuadores (dato, descripcion, estado, fecha, `idFisico`, ip, puerto, tipo, dependencia_id) 
	VALUES (5.0, 'Interruptor de la luz del salón', 1, '2014-04-29 12:20:16.0', 10020, '192.168.9.250', '8888', 1, 1);
INSERT INTO iambiental.actuadores (dato, descripcion, estado, fecha, `idFisico`, ip, puerto, tipo, dependencia_id) 
	VALUES (22.0, 'Termostato del salón', 1, '2014-04-30 12:12:21.0', 10024, '192.168.9.250', '8888', 0, 1);
INSERT INTO iambiental.actuadores (dato, descripcion, estado, fecha, `idFisico`, ip, puerto, tipo, dependencia_id) 
	VALUES (0.0, 'Interruptor puerta cocina', 0, '2014-04-30 12:15:03.0', 10021, '192.168.9.250', '8888', 1, 3);
INSERT INTO iambiental.actuadores (dato, descripcion, estado, fecha, `idFisico`, ip, puerto, tipo, dependencia_id) 
	VALUES (0.0, 'Interruptor de la luz de la lampara del dormitorio 1', 0, '2014-05-03 00:23:29.0', 10022, '192.168.9.250', '8888', 1, 2);
INSERT INTO iambiental.actuadores (dato, descripcion, estado, fecha, `idFisico`, ip, puerto, tipo, dependencia_id) 
	VALUES (21.0, 'Termostato dormitorio 1', 0, '2014-05-03 00:26:49.0', 10023, '192.168.9.250', '8888', 0, 2);


/** SENSORES */
INSERT INTO iambiental.sensores (dato, descripcion, estado, fecha, `idFisico`, ip, puerto, tipo, dependencia_id) 
	VALUES (23.0, 'Sensor de temperatura del salón', 0, '2014-04-29 12:39:03.0', 10010, '192.168.9.250', '8888', 1, 1);
INSERT INTO iambiental.sensores (dato, descripcion, estado, fecha, `idFisico`, ip, puerto, tipo, dependencia_id) 
	VALUES (28.0, 'Sensor de luz del salón', 1, '2014-04-29 13:14:53.0', 10011, '192.168.9.250', '8888', 0, 1);
INSERT INTO iambiental.sensores (dato, descripcion, estado, fecha, `idFisico`, ip, puerto, tipo, dependencia_id) 
	VALUES (21.0, 'Sensor de temperatura dormitorio 1', 0, '2014-05-03 00:27:58.0', 10012, '192.168.9.250', '8888', 1, 2);


/** TAREAS PROGRAMADAS */
INSERT INTO iambiental.tareasprogramadas (cron, descripcion) 
	VALUES ('0/20 * * * * ?', 'Depuradora');
INSERT INTO iambiental.tareasprogramadas (cron, descripcion) 
	VALUES ('0/50 * * * * ?', 'Aspersores');

/** REGLAS PROGRAMADAS */
INSERT INTO iambiental.reglasprogramadas (`descripcion`, `estadoActuador`, `margenRuido`, `valorMax`, `valorMin`, actuador_id, sensor_id) 
	VALUES ('Encender motor', 1, 5.0, 50.0, 10.0, 1, 1);
INSERT INTO iambiental.tareasprogramadas_reglasprogramadas (tareasprogramadas_id, `reglasProgramadas_id`) 
	VALUES (1, 1);
INSERT INTO iambiental.reglasprogramadas (`descripcion`, `estadoActuador`, `margenRuido`, `valorMax`, `valorMin`, actuador_id, sensor_id) 
	VALUES ('Encender aspersores', 1, 5.0, 50.0, 10.0, 3, 2);
INSERT INTO iambiental.tareasprogramadas_reglasprogramadas (tareasprogramadas_id, `reglasProgramadas_id`) 
	VALUES (2, 2);


/** REGLAS SENSOR-ACTUADOR */
INSERT INTO iambiental.reglassensoractuador (descripcion, actuador_id, sensor_id, `estadoActuador`, `margenRuido`, `valorMax`, `valorMin`) 
	VALUES ('Regla sensora', 1, 1, 0, 50.0, 800.0, 400.0);
