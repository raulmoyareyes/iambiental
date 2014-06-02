DELIMITER |

CREATE TRIGGER historico_actuadores BEFORE UPDATE ON actuadores
  FOR EACH ROW BEGIN
    INSERT INTO historicoactuadores (actuador_id, dato, estado, fecha) 
	VALUES (NEW.id, NEW.dato, NEW.estado, NEW.fecha);
  END
|

DELIMITER ;
