package es.ujaen.iambiental.modelos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Gabriel
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
@Entity
@Table(name = "reglasprogramadas")
public class ReglaProgramada implements Serializable {

    @Id
    private int id;
    private String descripcion;
    private String condicion;
    @OneToOne
    private Sensor sensor;
    @OneToOne
    private Actuador actuador;
    

    public ReglaProgramada() {

    }

    Integer getId() {
        return id;
    }
}
