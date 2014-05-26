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
import javax.persistence.OneToOne;
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
    @OneToOne
    private Sensor sensor;
    @OneToOne
    private Actuador actuador;
    float valorMin;
    float valorMax;
    float margenRuido;
    int estadoActuador;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReglaProgramada> reglasProgramadas;
    private String cron;

    public TareaProgramada() {

    }

    public TareaProgramada(String descripcion, Sensor sensor, Actuador actuador, float valorMin, float valorMax, float margenRuido, int estadoActuador, List<ReglaProgramada> reglasProgramadas, String cron) {
        this.descripcion = descripcion;
        this.sensor = sensor;
        this.actuador = actuador;
        this.valorMin = valorMin;
        this.valorMax = valorMax;
        this.margenRuido = margenRuido;
        this.estadoActuador = estadoActuador;
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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Actuador getActuador() {
        return actuador;
    }

    public void setActuador(Actuador actuador) {
        this.actuador = actuador;
    }

    public float getValorMin() {
        return valorMin;
    }

    public void setValorMin(float valorMin) {
        this.valorMin = valorMin;
        comprobarValores();
        fixMargenRuido();
    }

    public float getValorMax() {
        return valorMax;
    }

    public void setValorMax(float valorMax) {
        this.valorMax = valorMax;
        comprobarValores();
        fixMargenRuido();
    }

    public float getMargenRuido() {
        return margenRuido;
    }

    public void setMargenRuido(float margenRuido) {
        this.margenRuido = margenRuido;
        comprobarValores();
        fixMargenRuido();
    }

    public int getEstadoActuador() {
        return estadoActuador;
    }

    public void setEstadoActuador(int estadoActuador) {
        this.estadoActuador = estadoActuador;
    }
    
    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
    
    /**
     * Corrige el margen de ruido si fuese necesario para que cumpla la regla
     * margenRuido < ( valorMax - valorMin ) / 2
     */
    private void fixMargenRuido() {
        if (this.margenRuido > (this.valorMax - this.valorMin) / 2) {
            this.margenRuido = (this.valorMax - this.valorMin) / 2;
        }
    }
    
    /**
     * Comprueba los valores de la tarea (valorMin < valorMax)
     */
    private void comprobarValores(){
        if(this.valorMin>this.valorMax){
            float aux = this.valorMin;
            this.valorMin = this.valorMax;
            this.valorMax = aux;
        }
    }

}
