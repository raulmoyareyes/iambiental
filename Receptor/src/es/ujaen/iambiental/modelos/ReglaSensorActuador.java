package es.ujaen.iambiental.modelos;

import java.io.Serializable;

/**
 *
 * @author Gabriel
 * @author Raúl Moya Reyes <www.raulmoya.es>
 * @author Agustín Ruiz Linares <www.agustruiz.es>
 */
public class ReglaSensorActuador implements Serializable {

    private int id;
    private String descripcion;
    private Sensor sensor;
    private Actuador actuador;

    /**
     * Valor máximo del sensor
     */
    float valorMax;
    /**
     * Valor mínimo de la regla
     */
    float valorMin;
    /**
     * Valor de margen de ruido en la lectura del sensor. margenRuido < (
     * valorMax - valorMin ) / 2
     */
    float margenRuido;
    /**
     * Estado al que cambiar el actuador
     */
    int estadoActuador;

    public ReglaSensorActuador() {
    }

    /**
     * Constructor
     *
     * @param descripcion Descripción de la regla
     * @param sensor Sensor
     * @param actuador Actuador
     * @param valorMax Valor máximo del sensor
     * @param valorMin Valor mínimo del sensor
     * @param margenRuido Margen para el ruido de la lectura del sensor
     * @param estadoActuador Estado del actuador si se cumple la regla
     */
    public ReglaSensorActuador(String descripcion, Sensor sensor, Actuador actuador, float valorMax, float valorMin, float margenRuido, int estadoActuador) {
        this.descripcion = descripcion;
        this.sensor = sensor;
        this.actuador = actuador;
        this.valorMax = valorMax;
        this.valorMin = valorMin;
        this.margenRuido = margenRuido;
        this.estadoActuador = estadoActuador;
        fixMargenRuido();
    }

    /**
     * Obtener id de la regla
     *
     * @return id de la regla sensor-actuador
     */
    public int getId() {
        return id;
    }

    /**
     * Obtener el sensor de la regla
     *
     * @return Sensor
     */
    public Sensor getSensor() {
        return sensor;
    }

    /**
     * Establece el sensor de la regla
     *
     * @param sensor Sensor
     */
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    /**
     * Devuelve el actuador de la regla
     *
     * @return Actuador
     */
    public Actuador getActuador() {
        return actuador;
    }

    /**
     * Establece el actuador de la regla
     *
     * @param actuador Actuador
     */
    public void setActuador(Actuador actuador) {
        this.actuador = actuador;
    }

    /**
     * Devuelve la descripción de la regla
     *
     * @return Descripción de la regla
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la regla
     *
     * @param descripcion Descripción de la regla
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve el vamor máximo del sensor para ejecutar la regla
     *
     * @return Valor máximo del sensor para ejecutar la regla
     */
    public float getValorMax() {
        return valorMax;
    }

    /**
     * Establece el valor máximo del sensor para ejecutar la regla
     *
     * @param valorMax Valor máximo del sensor para ejecutar la regla
     */
    public void setValorMax(float valorMax) {
        this.valorMax = valorMax;
        fixMargenRuido();
    }

    /**
     * Devuelve el valor mínimo del sensor para ejecutar la regla
     *
     * @return Valor mínimo del sensor para ejecutar la regla
     */
    public float getValorMin() {
        return valorMin;
    }

    /**
     * Establece el valor mínimo del sensor para ejecutar la regla
     *
     * @param valorMin Valor mínimo del sensor para ejecutar la regla
     */
    public void setValorMin(float valorMin) {
        this.valorMin = valorMin;
        fixMargenRuido();
    }

    /**
     * Devuelve el margen de ruido permitido para la regla
     *
     * @return Margen de ruido permitido para la regla
     */
    public float getMargenRuido() {
        return margenRuido;
    }

    /**
     * Establece el margen de ruido permitido para la regla
     *
     * @param margenRuido Margen de ruido permitido para la regla
     */
    public void setMargenRuido(float margenRuido) {
        this.margenRuido = margenRuido;
        fixMargenRuido();
    }

    /**
     * Devuelve el estado al que se cambia el actuador si se cumple la regla
     *
     * @return Estado al que establecer el actuador cuando se cumple la regla
     */
    public int getEstadoActuador() {
        return estadoActuador;
    }

    /**
     * Establece el estado al que se cambia el actuador si se cumple la regla
     *
     * @param estadoActuador Estado al que se establece el actuador si se cumple
     * la regla
     */
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
     * Indica si se cumplen las condiciones de la regla sensor-actuador
     *
     * @return true si se cumplen las condiciones para aplicar el valor al
     * actuador o false en caso contrario
     */
    public boolean seCumple() {
        /*  Hay cinco franjas posibles divididas por cuatro separadores:
         +Infinito
         ························ FALSE
         valorMax + margenRuido
         ························ "MANTENER VALOR"
         valorMax - margenRuido
         ························ TRUE
         valorMin + margenRuido
         ························ "MANTENER VALOR"
         valorMin - margenRuido
         ························ FALSE
         -Infinito
         */
        //float valorMaxAlto = this.valorMax + this.margenRuido;
        float valorMaxBajo = this.valorMax - this.margenRuido;
        float valorMinAlto = this.valorMin + this.margenRuido;
        //float valorMinBajo = this.valorMin - this.margenRuido;
        if (this.sensor.getDato() <= valorMaxBajo && this.sensor.getDato() >= valorMinAlto) {
            //Por encima de valor máximo
            return true;
        } else {
            //Por debajo de valor mínimo
            return false;
        }
    }

    /**
     * Actualiza el valor del actuador en función del dato del sensor
     */
    public void updateActuador() {
        if (this.seCumple()) {
            this.actuador.setEstado(this.estadoActuador);
        } else {
            this.actuador.setEstado(0);  //Por defecto, si no se cumple ninguna regla, será 0
        }
    }
    
}
