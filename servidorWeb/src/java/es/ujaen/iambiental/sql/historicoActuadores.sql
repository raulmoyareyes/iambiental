CREATE TABLE historicoactuadores (
    id      INT,
    dato    DOUBLE,
    estado  INT,
    fecha   DATE
);

DELIMITER |

CREATE TRIGGER historico_actuadores BEFORE UPDATE ON actuadores
  FOR EACH ROW BEGIN
    INSERT INTO historicoactuadores (id, dato, estado, fecha) 
	VALUES (NEW.id, NEW.dato, NEW.estado, NEW.fecha);
  END
|

DELIMITER ;
