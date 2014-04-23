/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.modelos;

/**
 *
 * @author Agustín Ruiz Linares <www.agustruiz.es>
 */
public class ReglaSensorActuador_provisional {
    private static Integer contador = 0;
    private Integer id;
    private String descripcion;
    private Sensor_provisional sensor;
    private Actuador_provisional actuador;
    
    public ReglaSensorActuador_provisional() {
        this.id = this.contador++;
        this.sensor = null;
        this.actuador = null;
    }
    
    public ReglaSensorActuador_provisional(String descripcion, Sensor_provisional sensor, Actuador_provisional actuador) {
        this.id = this.contador++;
        this.descripcion = descripcion;
        this.sensor = sensor;
        this.actuador = actuador;
    }
    
    public static void reset(){
        ReglaSensorActuador_provisional.contador = 0;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Sensor_provisional getSensor() {
        return sensor;
    }

    public void setSensor(Sensor_provisional sensor) {
        this.sensor = sensor;
    }

    public Actuador_provisional getActuador() {
        return actuador;
    }

    public void setActuador(Actuador_provisional actuador) {
        this.actuador = actuador;
    }
    
    public Integer getId() {
        return id;
    }
}
