/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.beans;

import es.ujaen.iambiental.modelos.Actuador;
import es.ujaen.iambiental.modelos.Sensor;
import java.util.Map;

/**
 *
 * @author raulm
 */
public class ClienteUsoBEAN {
    
    /**
     * Devuelve un mapa con la lista de sensores de la habitación indicada.
     *
     * @param idHabitacion
     * @return Devuelve un mapa con la lista de sensores de la habitación indicada
     */
    public Map<String, Sensor> listarSensores(String idHabitacion) {
        return null;
    }
    
    /**
     * Devuelve un mapa con la lista de actuadores de la habitación indicada.
     *
     * @param idHabitacion
     * @return Devuelve un mapa con la lista de actuadores de la habitación indicada
     */
    public Map<String, Actuador> listarActuadores(String idHabitacion) {
        return null;
    }
    
    /**
     * Devuelve el estado del sensor indicado.
     *
     * @param idSensor
     * @return Devuelve el estado del sensor indicado
     */
    public Double obtenerEstadoSensor(String idSensor){
        return null;
    }
    
    /**
     * Devuelve el estado del actuador indicado.
     *
     * @param idActuador
     * @return Devuelve el estado del actuador indicado
     */
    public Double obtenerEstadoActuador(String idActuador){
        return null;
    }
    
    /**
     * Modifica el estado del actuador indicado con el valor indicado.
     *
     * @param idActuador
     * @param valor
     */
    public void modificarEstadoActuador(String idActuador, Double valor) {
        
    }
    
}
