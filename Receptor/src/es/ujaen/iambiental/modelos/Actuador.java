package es.ujaen.iambiental.modelos;

/**
 *
 * @author Gabriel
 * @author Raúl Moya Reyes <www.raulmoya.es>
 * @author Vicente
 */
public class Actuador {
    private int id;
    private String descripcion;
    private int dependencia;
    private String fecha;
    private float dato;
    private String ip;
    private String puerto;
    private int estado;
    private int tipo;
    private int dispositivo_id;

    public Actuador(){
        
    }

    public Actuador(int dispositivo_id, float dato, int estado, String fecha) {
        this.dispositivo_id = dispositivo_id;
        this.dato = dato;
        this.estado = estado;
        this.fecha = fecha;        
    }

    public Actuador(int id, float dato, String descripcion, int estado, String fecha, String ip, String puerto, int dependencia, int tipo, int dispositivo_id) {
        this.id = id;
        this.descripcion = descripcion;
        this.dependencia = dependencia;
        this.fecha = fecha;
        this.dato = dato;
        this.ip = ip;
        this.puerto = puerto;
        this.estado = estado;
        this.tipo = tipo;
        this.dispositivo_id = dispositivo_id;
    }    
    
    public int getTipo() {
        return tipo;
    }
    
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public int getID(){
        return id;
    }
    
    public int getDispositivoId(){
        return dispositivo_id;
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
    
}