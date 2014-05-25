package es.ujaen.iambiental.modelos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Gabriel
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
@Entity
@Table(name = "tareasprogramadas")
public class TareaProgramada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String descripcion;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReglaProgramada> reglasProgramadas;
    private String cron;

    public TareaProgramada() {

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
        reglasProgramadas.add(reglaProgramada.getId(), reglaProgramada);
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
