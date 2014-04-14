package es.ujaen.iambiental.daos;

import es.ujaen.iambiental.excepciones.SensorErrorActualizar;
import es.ujaen.iambiental.excepciones.SensorErrorEliminar;
import es.ujaen.iambiental.excepciones.SensorErrorPersistir;
import es.ujaen.iambiental.modelos.Sensor;
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
     * 
     * @return 
     */
    public Map<Integer, Sensor> listar() {
        Map<Integer, Sensor> sensores = new HashMap();
        List<Sensor> lista = em.createQuery("Select s from Sensor s").getResultList();

        for (Sensor sensor : lista) {
            sensores.put(sensor.getId(), sensor);
        }
        return sensores;
    }

    public Map<Integer, Sensor> consultarDependencia(Integer dependencia) {
        Map<Integer, Sensor> sensores = new HashMap();
        List<Sensor> lista = em.createQuery("Select s from Sensor s WHERE s.dependencia = ?1", Sensor.class).setParameter(1, dependencia).getResultList();

        for (Sensor sensor : lista) {
            sensores.put(sensor.getId(), sensor);
        }
        return sensores;
    }
}
