/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.modelos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private int dependencia; //No es necesario tener un objeto dependencia.
    private float dato;
    private String ip;
    private String puerto;
    private int estado;

    public Actuador(){
        
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
    
}
