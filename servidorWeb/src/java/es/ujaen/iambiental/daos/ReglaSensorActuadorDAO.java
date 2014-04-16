/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.iambiental.daos;

import es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorActualizar;
import es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorEliminar;
import es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorPersistir;
import es.ujaen.iambiental.modelos.ReglaSensorActuador;
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
public class ReglaSensorActuadorDAO {

    @PersistenceContext
    private EntityManager em;

    public ReglaSensorActuadorDAO() {

    }

    /**
     * Busca una regla sensor-actuador en la BD.
     *
     * @param id
     * @return regla sensor-actuador con el id indicado, null si no existe.
     */
    public ReglaSensorActuador buscar(int id) {
        return em.find(ReglaSensorActuador.class, id);
    }

    /**
     * Inserta un regla sensor-actuador en la BD.
     *
     * @param reglaSensorActuador
     * @throws ReglaSensorActuadorErrorPersistir
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorPersistir.class)
    public void insertar(ReglaSensorActuador reglaSensorActuador) throws ReglaSensorActuadorErrorPersistir {
        try {
            em.persist(reglaSensorActuador);
            em.flush();
        } catch (Exception e) {
            throw new ReglaSensorActuadorErrorPersistir();
        }
    }

    /**
     * Actualiza un regla sensor-actuador en la BD.
     *
     * @param reglaSensorActuador
     * @throws ReglaSensorActuadorErrorActualizar
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorActualizar.class)
    public void actualizar(ReglaSensorActuador reglaSensorActuador) throws ReglaSensorActuadorErrorActualizar {
        try {
            em.merge(reglaSensorActuador);
            em.flush();
        } catch (Exception e) {
            throw new ReglaSensorActuadorErrorActualizar();
        }
    }

    /**
     * Elimina una regla sensor-actuador de la BD.
     *
     * @param reglaSensorActuador
     * @throws ReglaSensorActuadorErrorEliminar
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.SensorErrorEliminar.class)
    public void eliminar(ReglaSensorActuador reglaSensorActuador) throws ReglaSensorActuadorErrorEliminar {
        try {
            em.remove(em.contains(reglaSensorActuador) ? reglaSensorActuador : em.merge(reglaSensorActuador));
            em.flush();
        } catch (Exception e) {
            throw new ReglaSensorActuadorErrorEliminar();
        }
    }

    /**
     * Devuelve un mapa con el listado de todas las reglas sensor-actuador.
     *
     * @return Mapa de reglas sensor-actuador si existen, mapa vacío si no
     * existen.
     */
    public Map<Integer, ReglaSensorActuador> listar() {
        Map<Integer, ReglaSensorActuador> reglasProgramadas = new HashMap();
        List<ReglaSensorActuador> lista = em.createQuery("Select r from ReglaSensorActuador r").getResultList();

        for (ReglaSensorActuador reglaSensorActuador : lista) {
            reglasProgramadas.put(reglaSensorActuador.getID(), reglaSensorActuador);
        }
        return reglasProgramadas;
    }

    /**
     * Devuelve un mapa con el listado de todas las reglas sensor-actuador que
     * están en una dependencia.
     *
     * @param dependencia
     * @return Mapa de reglas sensor-actuador si existen en esa dependencia,
     * mapa vacío si no existen.
     */
    public Map<Integer, ReglaSensorActuador> consultarDependencia(int dependencia) {
        Map<Integer, ReglaSensorActuador> reglasProgramadas = new HashMap();
        List<ReglaSensorActuador> lista = em.createQuery("Select r from ReglaSensorActuador r WHERE r.dependencia = ?1", ReglaSensorActuador.class).setParameter(1, dependencia).getResultList();

        for (ReglaSensorActuador reglaSensorActuador : lista) {
            reglasProgramadas.put(reglaSensorActuador.getID(), reglaSensorActuador);
        }
        return reglasProgramadas;
    }
}
