package es.ujaen.iambiental.clienteuso.modelos;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Gabriel
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
public class TareaProgramada implements Serializable {

    private int id;
    private String descripcion;
    private Map<Integer, ReglaProgramada> reglasProgramadas;

    public TareaProgramada() {

    }

    public TareaProgramada(String descripcion, Map<Integer, ReglaProgramada> reglasProgramadas) {
        this.descripcion = descripcion;
        this.reglasProgramadas = reglasProgramadas;
    }

    public int getId(){
        return id;
    }
    
    public Map<Integer, ReglaProgramada> getReglasProgramadas() {
        return reglasProgramadas;
    }

    public void setReglasProgramadas(Map<Integer, ReglaProgramada> reglasProgramadas) {
        this.reglasProgramadas = reglasProgramadas;
    }

    
    public void addReglaProgramada(ReglaProgramada reglaProgramada) {
        reglasProgramadas.put(reglaProgramada.getId(), reglaProgramada);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
