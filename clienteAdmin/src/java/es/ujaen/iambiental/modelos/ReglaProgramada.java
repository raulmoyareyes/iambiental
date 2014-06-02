package es.ujaen.iambiental.modelos;

import java.io.Serializable;

/**
 *
 * @author Gabriel
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
public class ReglaProgramada implements Serializable {

    private int id;
    private String descripcion;
    private Sensor sensor;
    private Actuador actuador;
    float valorMin;
    float valorMax;
    float margenRuido;
    int estadoActuador;
    

    public ReglaProgramada() {

    }

    public ReglaProgramada(String descripcion, Sensor sensor, Actuador actuador, float valorMin, float valorMax, float margenRuido, int estadoActuador) {
        this.descripcion = descripcion;
        this.sensor = sensor;
        this.actuador = actuador;
        this.valorMin = valorMin;
        this.valorMax = valorMax;
        this.margenRuido = margenRuido;
        this.estadoActuador = estadoActuador;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcionRegla) {
        this.descripcion = descripcionRegla;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Actuador getActuador() {
        return actuador;
    }

    public void setActuador(Actuador actuador) {
        this.actuador = actuador;
    }

    public float getValorMin() {
        return valorMin;
    }

    public void setValorMin(float valorMin) {
        this.valorMin = valorMin;
        //comprobarValores();
        //fixMargenRuido();
    }

    public float getValorMax() {
        return valorMax;
    }

    public void setValorMax(float valorMax) {
        this.valorMax = valorMax;
        //comprobarValores();
        //fixMargenRuido();
    }

    public float getMargenRuido() {
        return margenRuido;
    }

    public void setMargenRuido(float margenRuido) {
        this.margenRuido = margenRuido;
        //comprobarValores();
        //fixMargenRuido();
    }

    public int getEstadoActuador() {
        return estadoActuador;
    }

    public void setEstadoActuador(int estadoActuador) {
        this.estadoActuador = estadoActuador;
    }
    
    /**
     * Corrige el margen de ruido si fuese necesario para que cumpla la regla
     * margenRuido < ( valorMax - valorMin ) / 2
     */
    private void fixMargenRuido() {
        if (this.margenRuido > (this.valorMax - this.valorMin) / 2) {
            this.margenRuido = (this.valorMax - this.valorMin) / 2;
        }
    }
    
    /**
     * Comprueba los valores de la tarea (valorMin < valorMax)
     */
    private void comprobarValores(){
        if(this.valorMin>this.valorMax){
            float aux = this.valorMin;
            this.valorMin = this.valorMax;
            this.valorMax = aux;
        }
    }
        
}
