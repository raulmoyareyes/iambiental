package es.ujaen.iambiental.modelos;

import java.sql.Timestamp;

/**
 *
 * @author Vicente Plaza
 */
public class Sensor {
    private int id;
    private String descripcion;
    private int dependencia; //No es necesario tener un objeto dependencia.
    private Timestamp fecha;
    private float dato;
    private String ip;
    private String puerto;
    private int estado;

    public Sensor() {

    }

    public Sensor(int id, float dato, String descripcion, int estado, Timestamp fecha, String ip, String puerto, int dependencia) {
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

    public int getDependencia() {
        return dependencia;
    }

    public void setDependencia(int dependencia) {
        this.dependencia = dependencia;
    }

    public float getDato() {
        return dato;
    }

    public void setDato(float dato) {
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
