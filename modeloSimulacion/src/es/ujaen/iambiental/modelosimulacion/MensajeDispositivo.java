/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.modelosimulacion;

/**
 * @author Agustín Ruiz Linares <www.agustruiz.es>
 */
public class MensajeDispositivo {
    /*
    Formato del paquete:
    tipoDispotivo;time_stamp;id_sensor;valor;checksum
    */
    
    ///Tipo de dispositivo: "a" para actuador o "s" para sensor
    private String tipoDispositivo;
    private static final String ACTUADOR = "a";
    private static final String SENSOR = "s";
    
    ///Timestamp de la lectura/actuación
    private int time_stamp;
    
    ///Id del dispositivo
    private int id;
    
    ///Valor leido/actuado
    private float valor;
    
    ///Checksum
    private String checksum;
    
    ///Paquete a enviar
    private String paquete;
    
    /**
     * Constructor por defecto
     */
    public MensajeDispositivo(){
        this.tipoDispositivo = null;
        this.time_stamp = -1;
        this.id = -1;
        this.valor = -1;
        this.checksum = null;
        this.paquete = null;
    }

    /**
     * Devuelve el tipo de dispositivo.
     * Para una mejor gestión se realiza con constantes de clase
     * @return Tipo de dispositivo
     */
    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    /**
     * Establece el tipo de dispositivo.
     * Para una mejor gestión se realiza con constantes de clase
     * @param tipoDispositivo Tipo de dispositivo
     */
    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    /**
     * Devuelve el timestamp con el momento de la lectura/actuación
     * @return timestamp con el momento de la lectura/actuación
     */
    public int getTime_stamp() {
        return time_stamp;
    }

    /**
     * Establece el timestamp con el momento de la lectura/actuación
     * @param time_stamp timestamp con el momento de la lectura/actuación
     */
    public void setTime_stamp(int time_stamp) {
        this.time_stamp = time_stamp;
    }

    /**
     * Devuelve el Id del dipositivo
     * @return Id del dispositivo
     */
    public int getId() {
        return id;
    }

    /**
     * Establede el Id del dispositivo
     * @param id Id del dispositivo
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el valor leido por el dispositivo
     * @return Valor leído por el dispositivo
     */
    public float getValor() {
        return valor;
    }

    /**
     * Establece el valor leido por el dispositivo
     * @param valor Valor leido por el dispositivo
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    /**
     * Calcula el valor del Checksum del paquete
     * @return Cadena con el checksum de
     */
    private String calcularChecksum() {
        return null;
    }
    
    /**
     * Construlle el mensaje UDP a enviar
     * @return Paquete a enviar por UDP al receptor
     */
    private String construirMensajeUDP(){
        return null;
    }
    
}
