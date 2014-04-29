package es.ujaen.iambiental.clienteuso.modelos;

import java.io.Serializable;

/**
 *
 * @author Gabriel
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
public class ReglaSensorActuador implements Serializable {

    private int id;
    private String descripcion;
    private Sensor sensor;
    private Actuador actuador;

    public ReglaSensorActuador() {
        
    }

    public ReglaSensorActuador(String descripcion, Sensor sensor, Actuador actuador) {
        this.descripcion = descripcion;
        this.sensor = sensor;
        this.actuador = actuador;
    }

    public int getID(){
        return id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
