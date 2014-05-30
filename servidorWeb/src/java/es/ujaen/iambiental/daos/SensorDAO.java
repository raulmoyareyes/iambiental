package es.ujaen.iambiental.daos;

import es.ujaen.iambiental.excepciones.SensorErrorActualizar;
import es.ujaen.iambiental.excepciones.SensorErrorCambiarDependencia;
import es.ujaen.iambiental.excepciones.SensorErrorEliminar;
import es.ujaen.iambiental.excepciones.SensorErrorPersistir;
import es.ujaen.iambiental.modelos.Dependencia;
import es.ujaen.iambiental.modelos.Sensor;
import es.ujaen.iambiental.modelos.HistoricoSensor;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Raúl Moya Reyes <raulmoya.es>
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SensorDAO {

    @PersistenceContext
    private EntityManager em;

    public SensorDAO() {

    }

    /**
     * Busca un sensor en la BD.
     *
     * @param id
     * @return Sensor con el id indicado, null si no existe.
     */
    public Sensor buscar(int id) {
        return em.find(Sensor.class, id);
    }

    /**
     * Inserta un sensor en la BD.
     *
     * @param sensor
     * @throws SensorErrorPersistir
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.SensorErrorPersistir.class)
    public void insertar(Sensor sensor) throws SensorErrorPersistir {
        try {
            em.persist(sensor);
            em.flush();
        } catch (Exception e) {
            throw new SensorErrorPersistir();
        }
    }

    /**
     * Actualiza un sensor en la BD.
     *
     * @param sensor
     * @throws SensorErrorActualizar
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.SensorErrorActualizar.class)
    public void actualizar(Sensor sensor) throws SensorErrorActualizar {
        try {
            em.merge(sensor);
            em.flush();
        } catch (Exception e) {
            throw new SensorErrorActualizar();
        }
    }

    /**
     * Elimina un sensor de la BD.
     *
     * @param sensor
     * @throws SensorErrorEliminar
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.SensorErrorEliminar.class)
    public void eliminar(Sensor sensor) throws SensorErrorEliminar {
        try {
            em.remove(em.contains(sensor) ? sensor : em.merge(sensor));
            em.flush();
        } catch (Exception e) {
            throw new SensorErrorEliminar();
        }
    }

    /**
     * Devuelve un mapa con el listado de todos los sensores.
     *
     * @return Mapa de sensores si existen sensores, mapa vacío si no existen.
     */
    public Map<Integer, Sensor> listar() {
        Map<Integer, Sensor> sensores = new HashMap();
        List<Sensor> lista = em.createQuery("Select s from Sensor s").getResultList();

        for (Sensor sensor : lista) {
            sensores.put(sensor.getId(), sensor);
        }
        return sensores;
    }

    /**
     * Devuelve un mapa con el listado de todos los sensores que están en una
     * dependencia.
     *
     * @param dependencia
     * @return Mapa de sensores si existen sensores en esa dependencia, mapa
     * vacío si no existen.
     */
    public Map<Integer, Sensor> consultarDependencia(int dependencia) {
        Map<Integer, Sensor> sensores = new HashMap();
        List<Sensor> lista = em.createQuery("Select s from Sensor s WHERE s.dependencia.id = ?1", Sensor.class).setParameter(1, dependencia).getResultList();

        for (Sensor sensor : lista) {
            sensores.put(sensor.getId(), sensor);
        }
        return sensores;
    }
    
    /**
     * Devuelve un mapa con el listado de lecturas de un sensor
     * @param id Id del sensor
     * @param inicio Fecha y hora inicial
     * @param fin Fecha y hora final
     * @return Mapa de lecturas
     */
    public Map<Date, Float> consultarHistorico(int id, Date inicio, Date fin){
        
        Map<Date, Float> historico = new HashMap();
        
        List<HistoricoSensor> lista =
            em.createQuery("Select h from HistoricoSensor h WHERE h.id = ?1 AND h.fecha BETWEEN ?2  AND ?3", HistoricoSensor.class)
                    .setParameter(1, id)
                    .setParameter(2, inicio)
                    .setParameter(3, fin)
                    .getResultList();
        
        for (HistoricoSensor h : lista) {
            historico.put(h.getFecha(), h.getDato());
        }
        
        return historico;
    }
    
    /**
     * Cambia la dependencia a null porque esta dependencia va a ser eliminada
     *
     * @param de
     * @param d
     * @throws es.ujaen.iambiental.excepciones.SensorErrorCambiarDependencia
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.SensorErrorActualizar.class)
    public void cambiarDependencia(Dependencia de, Dependencia d) throws SensorErrorCambiarDependencia {
        List<Sensor> lista = em.createQuery("Select s from Sensor s Where s.dependencia = ?1").setParameter(1, d).getResultList();
        for (Sensor sensor : lista) {
            sensor.setDependencia(de);
            em.merge(sensor);
            em.flush();
        }
    }
}
