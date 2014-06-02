/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.iambiental.daos;

import es.ujaen.iambiental.excepciones.TareaProgramadaErrorActualizar;
import es.ujaen.iambiental.excepciones.TareaProgramadaErrorEliminar;
import es.ujaen.iambiental.excepciones.TareaProgramadaErrorPersistir;
import es.ujaen.iambiental.modelos.TareaProgramada;
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
public class TareaProgramadaDAO {

    @PersistenceContext
    private EntityManager em;

    public TareaProgramadaDAO() {

    }

    /**
     * Busca una tarea programada en la BD.
     *
     * @param id
     * @return tarea programada con el id indicado, null si no existe.
     */
    public TareaProgramada buscar(int id) {
        return em.find(TareaProgramada.class, id);
    }

    /**
     * Inserta un tarea programada en la BD.
     *
     * @param tareaProgramada
     * @throws TareaProgramadaErrorPersistir
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.TareaProgramadaErrorPersistir.class)
    public void insertar(TareaProgramada tareaProgramada) throws TareaProgramadaErrorPersistir {
        try {
            em.persist(tareaProgramada);
            em.flush();
        } catch (Exception e) {
            throw new TareaProgramadaErrorPersistir();
        }
    }

    /**
     * Actualiza un tarea programada en la BD.
     *
     * @param tareaProgramada
     * @throws TareaProgramadaErrorActualizar
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.TareaProgramadaErrorActualizar.class)
    public void actualizar(TareaProgramada tareaProgramada) throws TareaProgramadaErrorActualizar {
        try {
            em.merge(tareaProgramada);
            em.flush();
        } catch (Exception e) {
            throw new TareaProgramadaErrorActualizar();
        }
    }

    /**
     * Elimina una tarea programada de la BD.
     *
     * @param tareaProgramada
     * @throws TareaProgramadaErrorEliminar
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.SensorErrorEliminar.class)
    public void eliminar(TareaProgramada tareaProgramada) throws TareaProgramadaErrorEliminar {
        try {
            em.remove(em.contains(tareaProgramada) ? tareaProgramada : em.merge(tareaProgramada));
            em.flush();
        } catch (Exception e) {
            throw new TareaProgramadaErrorEliminar();
        }
    }

    /**
     * Devuelve un mapa con el listado de todas las tareas programadas.
     *
     * @return Mapa de tareas programadas si existen, mapa vacío si no existen.
     */
    public Map<Integer, TareaProgramada> listar() {
        Map<Integer, TareaProgramada> tareasProgramadas = new HashMap();
        List<TareaProgramada> lista = em.createQuery("Select t from TareaProgramada t").getResultList();

        for (TareaProgramada tareaProgramada : lista) {
            tareasProgramadas.put(tareaProgramada.getId(), tareaProgramada);
        }
        return tareasProgramadas;
    }

    /**
     * Devuelve un mapa con el listado de todas las tareas programadas que están
     * en una dependencia.
     *
     * @param dependencia
     * @return Mapa de tareas programadas si existen en esa dependencia, mapa
     * vacío si no existen.
     */
    public Map<Integer, TareaProgramada> consultarDependencia(int dependencia) {
        Map<Integer, TareaProgramada> tareasProgramadas = new HashMap();
        List<TareaProgramada> lista = em.createQuery("Select t from TareaProgramada t WHERE t.dependencia = ?1", TareaProgramada.class).setParameter(1, dependencia).getResultList();

        for (TareaProgramada tareaProgramada : lista) {
            tareasProgramadas.put(tareaProgramada.getId(), tareaProgramada);
        }
        return tareasProgramadas;
    }
}
