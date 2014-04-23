/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.modelos;

/**
 * Modelo de sensor (clase provisional, para pruebas)
 * @author Agustín Ruiz Linares <www.agustruiz.es>
 */
public class Sensor_provisional {
    static int contador = 0;
    private Integer id;
    private String descripcion;
    private Integer dependencia; //No es necesario tener un objeto dependencia.
    private Float dato;
    private String ip;
    private String puerto;
    private Integer estado;

    public Sensor_provisional(String descripcion, Integer dependencia, Float dato, String ip, String puerto, Integer estado) {
        this.id = contador++;
        this.descripcion = descripcion;
        this.dependencia = dependencia;
        this.dato = dato;
        this.ip = ip;
        this.puerto = puerto;
        this.estado = estado;
    }

    public Sensor_provisional() {
        this.id = contador++;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDependencia() {
        return dependencia;
    }

    public void setDependencia(Integer dependencia) {
        this.dependencia = dependencia;
    }

    public Float getDato() {
        return dato;
    }

    public void setDato(Float dato) {
        this.dato = dato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    
    public static void reset(){
        Sensor_provisional.contador = 0;
    }
    
}
