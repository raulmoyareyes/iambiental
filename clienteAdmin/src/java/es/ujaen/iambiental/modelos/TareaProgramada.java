package es.ujaen.iambiental.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
public class TareaProgramada implements Serializable {

    private int id;
    private String descripcion;
    private List<ReglaProgramada> reglasProgramadas;
    private String cron;

    public TareaProgramada() {

    }

    public TareaProgramada(String descripcion) {
        this.descripcion = descripcion;
        this.reglasProgramadas = new ArrayList();
    }

    public TareaProgramada(String descripcion, List<ReglaProgramada> reglasProgramadas, String cron) {
        this.descripcion = descripcion;
        this.reglasProgramadas = reglasProgramadas;
        this.cron = cron;
    }

    public int getId() {
        return id;
    }

    public List<ReglaProgramada> getReglasProgramadas() {
        return reglasProgramadas;
    }

    public void setReglasProgramadas(List<ReglaProgramada> reglasProgramadas) {
        this.reglasProgramadas = reglasProgramadas;
    }

    public void addReglaProgramada(ReglaProgramada reglaProgramada) {
        reglasProgramadas.add(reglaProgramada);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

}
