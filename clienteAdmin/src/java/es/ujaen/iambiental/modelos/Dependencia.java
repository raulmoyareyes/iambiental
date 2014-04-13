/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.modelos;

/**
 * Modelo de dependencias
 * @author Agustín Ruiz Linares <arl00029@red.ujaen.es>
 */
public class Dependencia {
    static int contador = 0;
    Integer id;
    String nombre;
    String descripcion;

    /**
     * Constructor por defecto
     * @author Agustín Ruiz Linares <www.agustruiz.es>
     */
    public Dependencia() {
        this.id = contador++;
        this.nombre = "";
        this.descripcion = "";
    }

    /**
     * Constructor inicializado
     * @param nombre Nombre de la dependencia
     * @param descripcion Descripción de la dependencia
     * @author Agustín Ruiz Linares <www.agustruiz.es>
     */
    public Dependencia(String nombre, String descripcion) {
        this.id = contador++;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Getter de id
     * @return Id de dependencia
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter de nombre
     * @return Nombre de la dependencia
     * @author Agustín Ruiz Linares <www.agustruiz.es>
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter de nombre
     * @param nombre Nombre de la dependencia
     * @author Agustín Ruiz Linares <www.agustruiz.es>
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter de descripción
     * @return Descripción de la dependencia
     * @author Agustín Ruiz Linares <www.agustruiz.es>
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Setter de descripción
     * @param descripcion Descripción de la dependencia
     * @author Agustín Ruiz Linares <www.agustruiz.es>
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * Reset de id (para pruebas)
     */
    public static void reset(){
        Dependencia.contador = 0;
    }
    
}
