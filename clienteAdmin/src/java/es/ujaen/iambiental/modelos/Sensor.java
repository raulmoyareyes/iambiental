package es.ujaen.iambiental.modelos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
public class Sensor implements Serializable {

    private int id;
    private String descripcion;
    private Dependencia dependencia;
    private Date fecha;
    private float dato;
    private String ip;
    private String puerto;
    private int estado; //para que era??
    private int tipo; // 1 si es temperatura, 0 si es de otra cosa. Esto hay que ver como hacerlo bien
    private int dispositivo_id;

    public Sensor() {
        dependencia = new Dependencia();
    }

    public Sensor(String descripcion, Dependencia dependencia, Date fecha, float dato, String ip, String puerto, int estado, int tipo, int dispositivo_id) {
        this.descripcion = descripcion;
        this.dependencia = dependencia;
        this.fecha = fecha;
        this.dato = dato;
        this.ip = ip;
        this.puerto = puerto;
        this.estado = estado;
        this.tipo = tipo;
        this.dispositivo_id = dispositivo_id;
    }

    public Integer getId() {
        return id;
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

    public void setDispositivo_id(int dispositivo_id) {
        this.dispositivo_id = dispositivo_id;
    }
    
}
