package es.ujaen.iambiental.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gabriel
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
@Entity
@Table(name = "actuadores")
public class Actuador implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String descripcion;
    @OneToOne
    private Dependencia dependencia;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    private float dato;
    private String ip;
    private String puerto;
    private int estado;
    private int tipo; // 1 si es interruptor, 0 si es de otra cosa. Esto hay que ver como hacerlo bien
    @OneToOne
    private Dispositivo dispositivo;

    public Actuador(){
        
    }    

    public Actuador(String descripcion, Dependencia dependencia, Date fecha, float dato, String ip, String puerto, int estado, int tipo, Dispositivo dispositivo) {
        this.descripcion = descripcion;
        this.dependencia = dependencia;
        this.fecha = fecha;
        this.dato = dato;
        this.ip = ip;
        this.puerto = puerto;
        this.estado = estado;
        this.tipo = tipo;
        this.dispositivo = dispositivo;
    }
    
    public Actuador(String descripcion, Dependencia dependencia, String ip, String puerto, int tipo, Dispositivo dispositivo){
        this.descripcion = descripcion;
        this.dependencia = dependencia;
        this.ip = ip;
        this.puerto = puerto;
        this.estado = 0;
        this.tipo = tipo;
        this.dispositivo = dispositivo;
    }
    
    public int getId(){
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

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }
    
}
