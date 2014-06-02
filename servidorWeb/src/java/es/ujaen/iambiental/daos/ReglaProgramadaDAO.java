/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.iambiental.daos;

import es.ujaen.iambiental.excepciones.ReglaProgramadaErrorActualizar;
import es.ujaen.iambiental.excepciones.ReglaProgramadaErrorEliminar;
import es.ujaen.iambiental.excepciones.ReglaProgramadaErrorPersistir;
import es.ujaen.iambiental.modelos.ReglaProgramada;
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
public class ReglaProgramadaDAO {

    @PersistenceContext
    private EntityManager em;

    public ReglaProgramadaDAO() {

    }

    /**
     * Busca una regla programada en la BD.
     *
     * @param id
     * @return regla programada con el id indicado, null si no existe.
     */
    public ReglaProgramada buscar(int id) {
        return em.find(ReglaProgramada.class, id);
    }

    /**
     * Inserta un regla programada en la BD.
     *
     * @param reglaProgramada
     * @throws ReglaProgramadaErrorPersistir
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.ReglaProgramadaErrorPersistir.class)
    public void insertar(ReglaProgramada reglaProgramada) throws ReglaProgramadaErrorPersistir {
        try {
            em.persist(reglaProgramada);
            em.flush();
        } catch (Exception e) {
            throw new ReglaProgramadaErrorPersistir();
        }
    }

    /**
     * Actualiza un regla programada en la BD.
     *
     * @param reglaProgramada
     * @throws ReglaProgramadaErrorActualizar
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.ReglaProgramadaErrorActualizar.class)
    public void actualizar(ReglaProgramada reglaProgramada) throws ReglaProgramadaErrorActualizar {
        try {
            em.merge(reglaProgramada);
            em.flush();
        } catch (Exception e) {
            throw new ReglaProgramadaErrorActualizar();
        }
    }

    /**
     * Elimina una regla programada de la BD.
     *
     * @param reglaProgramada
     * @throws ReglaProgramadaErrorEliminar
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.SensorErrorEliminar.class)
    public void eliminar(ReglaProgramada reglaProgramada) throws ReglaProgramadaErrorEliminar {
        try {
            em.remove(em.contains(reglaProgramada) ? reglaProgramada : em.merge(reglaProgramada));
            em.flush();
        } catch (Exception e) {
            throw new ReglaProgramadaErrorEliminar();
        }
    }

    /**
     * Devuelve un mapa con el listado de todas las reglas programadas.
     *
     * @return Mapa de reglas programadas si existen, mapa vacío si no existen.
     */
    public Map<Integer, ReglaProgramada> listar() {
        Map<Integer, ReglaProgramada> reglasProgramadas = new HashMap();
        List<ReglaProgramada> lista = em.createQuery("Select r from ReglaProgramada r").getResultList();

        for (ReglaProgramada reglaProgramada : lista) {
            reglasProgramadas.put(reglaProgramada.getId(), reglaProgramada);
        }
        return reglasProgramadas;
    }

    /**
     * Devuelve un mapa con el listado de todas las reglas programadas que están
     * en una dependencia.
     *
     * @param dependencia
     * @return Mapa de reglas programadas si existen en esa dependencia, mapa
     * vacío si no existen.
     */
    public Map<Integer, ReglaProgramada> consultarDependencia(int dependencia) {
        Map<Integer, ReglaProgramada> reglasProgramadas = new HashMap();
        List<ReglaProgramada> lista = em.createQuery("Select r from ReglaProgramada r WHERE r.dependencia = ?1", ReglaProgramada.class).setParameter(1, dependencia).getResultList();

        for (ReglaProgramada reglaProgramada : lista) {
            reglasProgramadas.put(reglaProgramada.getId(), reglaProgramada);
        }
        return reglasProgramadas;
    }
}
