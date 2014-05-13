CREATE TABLE historicoactuadores (
    id      INT,
    dato    DOUBLE,
    fecha   DATE
);

CREATE OR REPLACE TRIGGER historico_actuadores
    BEFORE UPDATE ON actuadores
DECLARE
    id_a    actuadores.id%TYPE;
    dato_a  actuadores.dato%TYPE;
    fecha   actuadores.fecha%TYPE;
BEGIN
    SELECT id, dato, fecha INTO id_a, dato_a, fecha_a
    FROM    actuadores
    INSERT INTO historicoactuadores (id, dato, fecha)
        VALUES (id_a, dato_a, fecha_a);
END;
/
