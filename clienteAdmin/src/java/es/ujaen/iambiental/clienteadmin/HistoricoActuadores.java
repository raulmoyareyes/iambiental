package es.ujaen.iambiental.clienteadmin;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
public class HistoricoActuadores implements Serializable {
    
    private int id;
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
