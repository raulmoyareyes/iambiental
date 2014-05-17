package es.ujaen.iambiental.beans;

import es.ujaen.iambiental.daos.ActuadorDAO;
import es.ujaen.iambiental.daos.SensorDAO;
import es.ujaen.iambiental.modelos.Actuador;
import es.ujaen.iambiental.modelos.Sensor;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
@Component(value = "beanClienteUso")
public class ClienteUsoBean {
    
    @Resource
    SensorDAO sensorDAO;
    
    @Resource
    ActuadorDAO actuadorDAO;
    
    /**
     * Devuelve un mapa con la lista de sensores de la habitación indicada.
     *
     * @param idDependencia
     * @return Devuelve un mapa con la lista de sensores de la habitación indicada
     */
    public Map<Integer, Sensor> listarSensores(int idDependencia) {
        return sensorDAO.consultarDependencia(idDependencia);
    }
        
    /**
     * Devuelve un mapa con la lista de actuadores de la habitación indicada.
     *
     * @param idDependencia
     * @return Devuelve un mapa con la lista de actuadores de la habitación indicada
     */
    public Map<Integer, Actuador> listarActuadores(Integer idDependencia) {
        return actuadorDAO.consultarDependencia(idDependencia);
    }
    
}
