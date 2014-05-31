DELIMITER |

CREATE TRIGGER historico_sensores BEFORE UPDATE ON sensores
  FOR EACH ROW BEGIN
    INSERT INTO historicosensores (sensor_id, dato, estado, fecha) 
	VALUES (NEW.id, NEW.dato, NEW.estado, NEW.fecha);
  END
|

DELIMITER ;
