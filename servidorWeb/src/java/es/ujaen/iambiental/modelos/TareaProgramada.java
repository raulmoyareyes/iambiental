package es.ujaen.iambiental.modelos;

import java.io.Serializable;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapKey;
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
    private int id;
    private String descripcion;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey
    private Map<Integer, ReglaProgramada> reglasProgramadas;

    public TareaProgramada() {

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

}
