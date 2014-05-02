package es.ujaen.iambiental.daos;

import es.ujaen.iambiental.excepciones.ActuadorErrorActualizar;
import es.ujaen.iambiental.excepciones.ActuadorErrorEliminar;
import es.ujaen.iambiental.excepciones.ActuadorErrorPersistir;
import es.ujaen.iambiental.modelos.Actuador;
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
public class ActuadorDAO {

    @PersistenceContext
    private EntityManager em;

    public ActuadorDAO() {

    }

    /**
     * Busca un actuador en la BD.
     *
     * @param id
     * @return Actuador con el id indicado, null si no existe.
     */
    public Actuador buscar(int id) {
        return em.find(Actuador.class, id);
    }

    /**
     * Inserta un actuador en la BD.
     *
     * @param actuador
     * @throws ActuadorErrorPersistir
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.SensorErrorPersistir.class)
    public void insertar(Actuador actuador) throws ActuadorErrorPersistir {
        try {
            em.persist(actuador);
            em.flush();
        } catch (Exception e) {
            throw new ActuadorErrorPersistir();
        }
    }

    /**
     * Actualiza un actuador en la BD.
     *
     * @param actuador
     * @throws ActuadorErrorActualizar
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.SensorErrorActualizar.class)
    public void actualizar(Actuador actuador) throws ActuadorErrorActualizar {
        try {
            em.merge(actuador);
            em.flush();
        } catch (Exception e) {
            throw new ActuadorErrorActualizar();
        }
    }

    /**
     * Elimina un actuador de la BD.
     *
     * @param actuador
     * @throws ActuadorErrorEliminar
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.SensorErrorEliminar.class)
    public void eliminar(Actuador actuador) throws ActuadorErrorEliminar {
        try {
            em.remove(em.contains(actuador) ? actuador : em.merge(actuador));
            em.flush();
        } catch (Exception e) {
            throw new ActuadorErrorEliminar();
        }
    }

    /**
     * Devuelve un mapa con el listado de todos los actuadores.
     *
     * @return Mapa de actuadores si existen actuadores, mapa vacío si no existen.
     */
    public Map<Integer, Actuador> listar() {
        Map<Integer, Actuador> actuadores = new HashMap();
        List<Actuador> lista = em.createQuery("Select a from Actuador a").getResultList();

        for (Actuador actuador : lista) {
            actuadores.put(actuador.getId(), actuador);
        }
        return actuadores;
    }

    /**
     * Devuelve un mapa con el listado de todos los actuadores que están en una
     * dependencia.
     *
     * @param dependencia
     * @return Mapa de actuadores si existen actuadores en esa dependencia, mapa
     * vacío si no existen.
     */
    public Map<Integer, Actuador> consultarDependencia(int dependencia) {
        Map<Integer, Actuador> actuadores = new HashMap();
        List<Actuador> lista = em.createQuery("Select a from Actuador a WHERE a.dependencia.id = ?1", Actuador.class).setParameter(1, dependencia).getResultList();

        for (Actuador actuador : lista) {
            actuadores.put(actuador.getId(), actuador);
        }
        return actuadores;
    }
    
    //Capullo necesito el histórico de los actuadores YAAAA!!!!!
}
