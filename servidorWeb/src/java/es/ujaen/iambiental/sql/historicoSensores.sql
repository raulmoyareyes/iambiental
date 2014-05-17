CREATE TABLE historicosensores (
    id      INT,
    dato    DOUBLE,
    estado  INT,
    fecha   DATE
);

DELIMITER |

CREATE TRIGGER historico_sensores BEFORE UPDATE ON sensores
  FOR EACH ROW BEGIN
    INSERT INTO historicosensores (id, dato, estado, fecha) 
	VALUES (NEW.id, NEW.dato, NEW.estado, NEW.fecha);
  END
|

DELIMITER ;
