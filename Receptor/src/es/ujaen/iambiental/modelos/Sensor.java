/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.modelos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Vicente_2
 */  

public class Sensor implements Serializable {
    private int id;
    private String descripcion;
    private Dependencia dependencia; //No es necesario tener un objeto dependencia.
    private Date fecha;
    private float dato;
    private String ip;
    private String puerto;
    private int estado;

    public Sensor() {

}

    public Sensor(int id, String descripcion, Dependencia dependencia, Date fecha, float dato, String ip, String puerto, int estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.dependencia = dependencia;
        this.fecha = fecha;
        this.dato = dato;
        this.ip = ip;
        this.puerto = puerto;
        this.estado = estado;
    }

    public Integer getID() {
        return id;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public float getDato() {
        return dato;
    }

    public void setDato(float dato) {
        this.dato = dato;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
