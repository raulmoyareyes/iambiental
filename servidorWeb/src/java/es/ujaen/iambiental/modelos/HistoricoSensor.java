package es.ujaen.iambiental.modelos;

import java.util.Date;

/**
 *
 * @author Agustin Ruiz Linares <www.agustruiz.es>
 */
public class HistoricoSensor {
    private int id;
    private float dato;
    private int estado;
    private Date fecha;

    /**
     * Constructor por defecto
     */
    public HistoricoSensor() {
    }

    /**
     * Devuelve el Id del sensor de la lectura
     * @return Id del sensor de la lectura
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el Id del sensor de la lectura
     * @param id Id del sensor de la lectura
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el dato del sensor en la lectura
     * @return Dato en la lectura
     */
    public float getDato() {
        return dato;
    }

    /**
     * Establece el dato del sensor en la lectura
     * @param dato Dato del sensor en la lectura
     */
    public void setDato(float dato) {
        this.dato = dato;
    }

    /**
     * Devuelve el estado del sensor en la lectura
     * @return Estado del sensor en la lectura
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Establece el estado del sensor en la lectura
     * @param estado Estado del sensor en la lectura
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * Devuelve la fecha y hora de la lectura
     * @return Fecha y hora de la lectura
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha y hora de la lectura
     * @param fecha Fecha y hora de la lectura
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
