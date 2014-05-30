package es.ujaen.iambiental.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
@Entity
@Table(name = "historicoactuadores")
public class HistoricoActuadores implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    private float dato;
    private int estado;
    private int actuador_id;

    public HistoricoActuadores() {
    }

    public HistoricoActuadores(Date fecha, float dato, int estado) {
        this.fecha = fecha;
        this.dato = dato;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getDato() {
        return dato;
    }

    public void setDato(float dato) {
        this.dato = dato;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getSensor() {
        return actuador_id;
    }

    public void setSensor(int sensor) {
        this.actuador_id = sensor;
    }
    
}
