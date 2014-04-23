/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.modelos;

/**
 * Modelo de actuador (clase provisional, para pruebas)
 * @author Agustín Ruiz Linares <www.agustruiz.es>
 */
public class Actuador_provisional {
    static private int contador = 0;
    private Integer id;
    private String descripcion;
    private Integer dependencia; //No es necesario tener un objeto dependencia.
    private Float dato;
    private String ip;
    private String puerto;
    private Integer estado;

    public Actuador_provisional(String descripcion, Integer dependencia, Float dato, String ip, String puerto, Integer estado){
        this.id = contador++;
        this.descripcion = descripcion;
        this.dependencia = dependencia;
        this.dato = dato;
        this.ip = ip;
        this.puerto = puerto;
        this.estado = estado;
    }

    public Actuador_provisional(){
        this.id = contador++;
    }
    
    public int getId(){
        return id;
    }
    
    public int getDependencia() {
        return dependencia;
    }

    public void setDependencia(int dependencia) {
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
    
    public static void reset(){
        Actuador_provisional.contador = 0;
    }
}
