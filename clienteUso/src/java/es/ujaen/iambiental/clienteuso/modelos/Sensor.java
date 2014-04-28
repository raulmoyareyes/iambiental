package es.ujaen.iambiental.clienteuso.modelos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
public class Sensor implements Serializable {

    private int id;
    private String descripcion;
    private int dependencia;
    private Date fecha;
    private float dato;
    private String ip;
    private String puerto;
    private int estado;
    private int tipo; // 1 si es temperatura, 0 si es de otra cosa. Esto hay que ver como hacerlo bien 

    public Sensor() {

    }

    public Sensor(String descripcion, int dependencia, Date fecha, float dato, String ip, String puerto, int estado) {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
}
