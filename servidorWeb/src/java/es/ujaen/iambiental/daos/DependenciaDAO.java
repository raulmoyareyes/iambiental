/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ujaen.iambiental.daos;

import es.ujaen.iambiental.excepciones.DependenciaErrorActualizar;
import es.ujaen.iambiental.excepciones.DependenciaErrorEliminar;
import es.ujaen.iambiental.excepciones.DependenciaErrorPersistir;
import es.ujaen.iambiental.modelos.Dependencia;
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
public class DependenciaDAO {

    @PersistenceContext
    private EntityManager em;

    public DependenciaDAO() {

    }

    /**
     * Busca un dependencia en la BD.
     *
     * @param id
     * @return Dependencia con el id indicado, null si no existe.
     */
    public Dependencia buscar(int id) {
        return em.find(Dependencia.class, id);
    }

    /**
     * Inserta un dependencia en la BD.
     *
     * @param dependencia
     * @throws DependenciaErrorPersistir
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.SensorErrorPersistir.class)
    public void insertar(Dependencia dependencia) throws DependenciaErrorPersistir {
        try {
            em.persist(dependencia);
            em.flush();
        } catch (Exception e) {
            throw new DependenciaErrorPersistir();
        }
    }

    /**
     * Actualiza un dependencia en la BD.
     *
     * @param dependencia
     * @throws DependenciaErrorActualizar
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.SensorErrorActualizar.class)
    public void actualizar(Dependencia dependencia) throws DependenciaErrorActualizar {
        try {
            em.merge(dependencia);
            em.flush();
        } catch (Exception e) {
            throw new DependenciaErrorActualizar();
        }
    }

    /**
     * Elimina un dependencia de la BD.
     *
     * @param dependencia
     * @throws DependenciaErrorEliminar
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            rollbackFor = es.ujaen.iambiental.excepciones.SensorErrorEliminar.class)
    public void eliminar(Dependencia dependencia) throws DependenciaErrorEliminar {
        try {
            em.remove(em.contains(dependencia) ? dependencia : em.merge(dependencia));
            em.flush();
        } catch (Exception e) {
            throw new DependenciaErrorEliminar();
        }
    }

    /**
     * Devuelve un mapa con el listado de todos los dependenciaes.
     *
     * @return Mapa de dependenciaes si existen dependenciaes, mapa vacío si no existen.
     */
    public Map<Integer, Dependencia> listar() {
        Map<Integer, Dependencia> dependenciaes = new HashMap();
        List<Dependencia> lista = em.createQuery("Select a from Dependencia a").getResultList();

        for (Dependencia dependencia : lista) {
            dependenciaes.put(dependencia.getId(), dependencia);
        }
        return dependenciaes;
    }
}
