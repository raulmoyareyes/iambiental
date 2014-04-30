package es.ujaen.iambiental.clienteuso.modelos;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Gabriel
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
public class Actuador implements Serializable {
    
    private int id;
    private String descripcion;
    private Dependencia dependencia;
    private Date fecha;
    private float dato;
    private String ip;
    private String puerto;
    private int estado;
    private int tipo; // 1 si es interruptor, 0 si es potenciometro. Esto hay que ver como hacerlo bien 

    public Actuador(){
        
    }

    public Actuador(String descripcion, Dependencia dependencia, Date fecha, float dato, String ip, String puerto, int estado) {
        this.descripcion = descripcion;
        this.dependencia = dependencia;
        this.fecha = fecha;
        this.dato = dato;
        this.ip = ip;
        this.puerto = puerto;
        this.estado = estado;
    }    
    
    public int getID(){
        return id;
    }
    
    public Dependencia getDependencia() {
        return dependencia;
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
    
}
