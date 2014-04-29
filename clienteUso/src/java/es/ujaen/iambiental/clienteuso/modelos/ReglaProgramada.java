package es.ujaen.iambiental.clienteuso.modelos;

import java.io.Serializable;

/**
 *
 * @author Gabriel
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
public class ReglaProgramada implements Serializable {

    private int id;
    private String descripcion;
    private String condicion;
    private Sensor sensor;
    private Actuador actuador;
    

    public ReglaProgramada() {

    }

    public ReglaProgramada(String descripcion, String condicion, Sensor sensor, Actuador actuador) {
        this.descripcion = descripcion;
        this.condicion = condicion;
        this.sensor = sensor;
        this.actuador = actuador;
    }

    public int getID() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Actuador getActuador() {
        return actuador;
    }

    public void setActuador(Actuador actuador) {
        this.actuador = actuador;
    }
        
}
