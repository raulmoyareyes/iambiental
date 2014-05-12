package es.ujaen.iambiental.daos;

import es.ujaen.iambiental.excepciones.DispositivoErrorActualizar;
import es.ujaen.iambiental.excepciones.DispositivoErrorEliminar;
import es.ujaen.iambiental.excepciones.DispositivoErrorPersistir;
import es.ujaen.iambiental.excepciones.SensorErrorCambiarDependencia;
import es.ujaen.iambiental.modelos.Dependencia;
import es.ujaen.iambiental.modelos.Dispositivo;
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
public class DispositivoDAO {

    @PersistenceContext
    private EntityManager em;

    public DispositivoDAO() {

    }

    /**
     * Busca un dispositivo en la BD.
     *
     * @param id
     * @return Dispositivo con el id indicado, null si no existe.
     */
    public Dispositivo buscar(int id) {
        return em.find(Dispositivo.class, id);
    }

    /**
     * Inserta un dispositivo en la BD.
     *
     * @param dispositivo
     * @throws DispositivoErrorPersistir
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.DispositivoErrorPersistir.class)
    public void insertar(Dispositivo dispositivo) throws DispositivoErrorPersistir {
        try {
            em.persist(dispositivo);
            em.flush();
        } catch (Exception e) {
            throw new DispositivoErrorPersistir();
        }
    }

    /**
     * Actualiza un dispositivo en la BD.
     *
     * @param dispositivo
     * @throws DispositivoErrorActualizar
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.DispositivoErrorActualizar.class)
    public void actualizar(Dispositivo dispositivo) throws DispositivoErrorActualizar {
        try {
            em.merge(dispositivo);
            em.flush();
        } catch (Exception e) {
            throw new DispositivoErrorActualizar();
        }
    }

    /**
     * Elimina un dispositivo de la BD.
     *
     * @param dispositivo
     * @throws DispositivoErrorEliminar
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.DispositivoErrorEliminar.class)
    public void eliminar(Dispositivo dispositivo) throws DispositivoErrorEliminar {
        try {
            em.remove(em.contains(dispositivo) ? dispositivo : em.merge(dispositivo));
            em.flush();
        } catch (Exception e) {
            throw new DispositivoErrorEliminar();
        }
    }

    /**
     * Devuelve un mapa con el listado de todos los dispositivos.
     *
     * @return Mapa de dispositivoa si existen dispositivos, mapa vacío si no existen.
     */
    public Map<Integer, Dispositivo> listar() {
        Map<Integer, Dispositivo> dispositivos = new HashMap();
        List<Dispositivo> lista = em.createQuery("Select d from Dispositivo d").getResultList();

        for (Dispositivo dispositivo : lista) {
            dispositivos.put(dispositivo.getId(), dispositivo);
        }
        return dispositivos;
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
    
    //Capullo necesito el histórico de los sensores YAAAA!!!!!
    
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
