/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.modelos;

import java.io.Serializable;

/**
 *
 * @author Raúl Moya Reyes <raulmoya.es> // Modificado por Vicente para el Receptor
 */
public class Dependencia implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;

    public Dependencia(){
        
    }
            
    public Dependencia(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
