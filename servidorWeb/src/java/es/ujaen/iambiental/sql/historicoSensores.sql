CREATE TABLE historicosensores (
    id      INT,
    dato    DOUBLE,
    fecha   DATE
);

CREATE OR REPLACE TRIGGER historico_sensores
    BEFORE UPDATE ON sensores
DECLARE
    id_a    sensores.id%TYPE;
    dato_a  sensores.dato%TYPE;
    fecha   sensores.fecha%TYPE;
BEGIN
    SELECT id, dato, fecha INTO id_a, dato_a, fecha_a
    FROM    sensores
    INSERT INTO historicosensores (id, dato, fecha)
        VALUES (id_a, dato_a, fecha_a);
END;
/

