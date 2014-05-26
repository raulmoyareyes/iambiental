package es.ujaen.iambiental.beans;

import es.ujaen.iambiental.daos.ActuadorDAO;
import es.ujaen.iambiental.daos.DependenciaDAO;
import es.ujaen.iambiental.daos.ReglaProgramadaDAO;
import es.ujaen.iambiental.daos.ReglaSensorActuadorDAO;
import es.ujaen.iambiental.daos.SensorDAO;
import es.ujaen.iambiental.daos.TareaProgramadaDAO;
import es.ujaen.iambiental.disparadorTarea.Tarea;
import es.ujaen.iambiental.emisorWeb.EmisorWeb;
import es.ujaen.iambiental.excepciones.ActuadorErrorActualizar;
import es.ujaen.iambiental.excepciones.ActuadorErrorCambiarDependencia;
import es.ujaen.iambiental.excepciones.ActuadorErrorDatos;
import es.ujaen.iambiental.excepciones.ActuadorErrorEliminar;
import es.ujaen.iambiental.excepciones.ActuadorErrorPersistir;
import es.ujaen.iambiental.excepciones.ActuadorNoEncontrado;
import es.ujaen.iambiental.excepciones.DependenciaErrorActualizar;
import es.ujaen.iambiental.excepciones.DependenciaErrorDatos;
import es.ujaen.iambiental.excepciones.DependenciaErrorEliminar;
import es.ujaen.iambiental.excepciones.DependenciaErrorPersistir;
import es.ujaen.iambiental.excepciones.DependenciaNoEncontrada;
import es.ujaen.iambiental.excepciones.ReglaProgramadaErrorActualizar;
import es.ujaen.iambiental.excepciones.ReglaProgramadaErrorDatos;
import es.ujaen.iambiental.excepciones.ReglaProgramadaErrorEliminar;
import es.ujaen.iambiental.excepciones.ReglaProgramadaErrorPersistir;
import es.ujaen.iambiental.excepciones.ReglaProgramadaNoEncontrada;
import es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorActualizar;
import es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorDatos;
import es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorEliminar;
import es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorPersistir;
import es.ujaen.iambiental.excepciones.ReglaSensorActuadorNoEncontrada;
import es.ujaen.iambiental.excepciones.SensorErrorActualizar;
import es.ujaen.iambiental.excepciones.SensorErrorCambiarDependencia;
import es.ujaen.iambiental.excepciones.SensorErrorDatos;
import es.ujaen.iambiental.excepciones.SensorErrorEliminar;
import es.ujaen.iambiental.excepciones.SensorErrorPersistir;
import es.ujaen.iambiental.excepciones.SensorNoEncontrado;
import es.ujaen.iambiental.excepciones.TareaProgramadaErrorActualizar;
import es.ujaen.iambiental.excepciones.TareaProgramadaErrorDatos;
import es.ujaen.iambiental.excepciones.TareaProgramadaErrorEliminar;
import es.ujaen.iambiental.excepciones.TareaProgramadaErrorPersistir;
import es.ujaen.iambiental.excepciones.TareaProgramadaNoEncontrada;
import es.ujaen.iambiental.modelos.Actuador;
import es.ujaen.iambiental.modelos.Dependencia;
import es.ujaen.iambiental.modelos.ReglaProgramada;
import es.ujaen.iambiental.modelos.ReglaSensorActuador;
import es.ujaen.iambiental.modelos.Sensor;
import es.ujaen.iambiental.modelos.TareaProgramada;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.UnableToInterruptJobException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Raúl Moya Reyes <www.raulmoya.es>
 */
@Component(value = "beanAdmin")
public class AdminBean {

    @Resource
    SensorDAO sensorDAO;

    @Resource
    ActuadorDAO actuadorDAO;

    @Resource
    TareaProgramadaDAO tareaProgramadaDAO;

    @Resource
    ReglaProgramadaDAO reglaProgramadaDAO;

    @Resource
    ReglaSensorActuadorDAO reglaSensorActuadorDAO;

    @Resource
    DependenciaDAO dependenciaDAO;

    Map<Integer, Scheduler> tareas;

    /**
     * Crear un sensor.
     *
     * @param sensor
     * @throws es.ujaen.iambiental.excepciones.SensorErrorDatos
     * @throws es.ujaen.iambiental.excepciones.SensorErrorPersistir
     */
    public void crearSensor(Sensor sensor) throws SensorErrorDatos, SensorErrorPersistir {

        try {
            sensorDAO.insertar(sensor);
        } catch (SensorErrorPersistir e) {
            throw new SensorErrorPersistir();
        }

    }

    /**
     * Devuelve el sensor con el id indicado
     *
     * @param idSensor
     * @return Devuelve el sensor, null si no es encontrado.
     */
    public Sensor obtenerSensor(Integer idSensor) {
        return sensorDAO.buscar(idSensor);
    }

    /**
     * Elimina un sensor del sistema.
     *
     * @param idSensor
     * @throws es.ujaen.iambiental.excepciones.SensorErrorEliminar
     * @throws es.ujaen.iambiental.excepciones.SensorNoEncontrado
     */
    public void eliminarSensor(Integer idSensor) throws SensorErrorEliminar, SensorNoEncontrado {
        Sensor s = sensorDAO.buscar(idSensor);
        if (s == null) {
            throw new SensorNoEncontrado();
        }
        sensorDAO.eliminar(s);
    }

    /**
     * Modifica un sensor del sistema.
     *
     * @param sensor
     * @throws es.ujaen.iambiental.excepciones.SensorErrorActualizar
     */
    public void modificarSensor(Sensor sensor) throws SensorErrorActualizar {
        sensorDAO.actualizar(sensor);
    }

    /**
     * Crear un actuador.
     *
     * @param actuador
     * @throws es.ujaen.iambiental.excepciones.ActuadorErrorDatos
     * @throws es.ujaen.iambiental.excepciones.ActuadorErrorPersistir
     */
    public void crearActuador(Actuador actuador) throws ActuadorErrorDatos, ActuadorErrorPersistir {
        try {
            actuadorDAO.insertar(actuador);
        } catch (ActuadorErrorPersistir e) {
            throw new ActuadorErrorPersistir();
        }
    }

    /**
     * Devuelve el actuador con el id indicado
     *
     * @param idActuador
     * @return Devuelve el actuador, null si no es encontrado.
     */
    public Actuador obtenerActuador(Integer idActuador) {
        return actuadorDAO.buscar(idActuador);
    }

    /**
     * Elimina un actuador del sistema.
     *
     * @param idActuador
     * @throws es.ujaen.iambiental.excepciones.ActuadorErrorEliminar
     * @throws es.ujaen.iambiental.excepciones.ActuadorNoEncontrado
     */
    public void eliminarActuador(Integer idActuador) throws ActuadorErrorEliminar, ActuadorNoEncontrado {
        Actuador a = actuadorDAO.buscar(idActuador);
        if (a == null) {
            throw new ActuadorNoEncontrado();
        }
        actuadorDAO.eliminar(a);
    }

    /**
     * Modifica un actuador del sistema.
     *
     * @param actuador
     * @throws es.ujaen.iambiental.excepciones.ActuadorErrorActualizar
     */
    public void modificarActuador(Actuador actuador) throws ActuadorErrorActualizar {
        actuadorDAO.actualizar(actuador);
        try {
            EmisorWeb.envioActuador(actuador);
        } catch (SocketException | UnknownHostException ex) {
            System.out.println("No se puede conectar con el emisor");
        } catch (IOException ex) {
            System.out.println("No se puede enviar el paquete");
        }
    }

    /**
     * Crear una tarea programada.
     *
     * @param tareaProgramada
     * @throws es.ujaen.iambiental.excepciones.TareaProgramadaErrorDatos
     * @throws es.ujaen.iambiental.excepciones.TareaProgramadaErrorPersistir
     */
    public void crearTareaProgramada(TareaProgramada tareaProgramada) throws TareaProgramadaErrorDatos, TareaProgramadaErrorPersistir {
        try {
            tareaProgramadaDAO.insertar(tareaProgramada);
        } catch (TareaProgramadaErrorPersistir e) {
            throw new TareaProgramadaErrorPersistir();
        }
    }

    /**
     * Devuelve la tarea programada con el id indicado
     *
     * @param idTareaProgramada
     * @return Devuelve la tarea programada, null si no es encontrada.
     */
    public TareaProgramada obtenerTareaProgramada(Integer idTareaProgramada) {
        TareaProgramada tarea = tareaProgramadaDAO.buscar(idTareaProgramada);
        lanzarTarea(tarea);
        return tarea;
    }

    /**
     * Elimina una tarea programada del sistema.
     *
     * @param idTareaProgramada
     * @throws es.ujaen.iambiental.excepciones.TareaProgramadaErrorEliminar
     * @throws es.ujaen.iambiental.excepciones.TareaProgramadaNoEncontrada
     */
    public void eliminarTareaProgramada(Integer idTareaProgramada) throws TareaProgramadaErrorEliminar, TareaProgramadaNoEncontrada {
        TareaProgramada tp = tareaProgramadaDAO.buscar(idTareaProgramada);
        if (tp == null) {
            throw new TareaProgramadaNoEncontrada();
        }
        Scheduler t = tareas.get(idTareaProgramada);
        try {
            t.interrupt(String.valueOf(idTareaProgramada));
            t.shutdown(true);
        } catch (SchedulerException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        tareas.remove(tp.getId());
        tareaProgramadaDAO.eliminar(tp);
    }

    /**
     * Modifica una tarea programada del sistema.
     *
     * @param tareaProgramada
     * @throws es.ujaen.iambiental.excepciones.TareaProgramadaErrorActualizar
     */
    public void modificarTareaProgramada(TareaProgramada tareaProgramada) throws TareaProgramadaErrorActualizar {
        tareaProgramadaDAO.actualizar(tareaProgramada);
        Scheduler t = tareas.get(tareaProgramada.getId());
        try {
            t.interrupt(String.valueOf(tareaProgramada.getId()));
            t.shutdown(true);
        } catch (SchedulerException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        tareas.remove(tareaProgramada.getId());
        lanzarTarea(tareaProgramada);
    }

    /**
     * Crear una regla programada y asociarla a una tarea programada.
     *
     * @param reglaProgramada
     * @throws es.ujaen.iambiental.excepciones.ReglaProgramadaErrorDatos
     * @throws es.ujaen.iambiental.excepciones.ReglaProgramadaErrorPersistir
     * @throws es.ujaen.iambiental.excepciones.SensorNoEncontrado
     * @throws es.ujaen.iambiental.excepciones.ActuadorNoEncontrado
     */
    public void crearReglaProgramada(ReglaProgramada reglaProgramada) throws ReglaProgramadaErrorDatos, ReglaProgramadaErrorPersistir, SensorNoEncontrado, ActuadorNoEncontrado {
        Sensor s = sensorDAO.buscar(reglaProgramada.getSensor().getId());
        if (s == null) {
            throw new SensorNoEncontrado();
        }
        Actuador a = actuadorDAO.buscar(reglaProgramada.getActuador().getId());
        if (a == null) {
            throw new ActuadorNoEncontrado();
        }
        ReglaProgramada r = new ReglaProgramada(reglaProgramada.getDescripcionRegla(), s, a, reglaProgramada.getValorMin(), reglaProgramada.getValorMax(), reglaProgramada.getMargenRuido(), reglaProgramada.getEstadoActuador());
        try {
            reglaProgramadaDAO.insertar(r);
        } catch (ReglaProgramadaErrorPersistir e) {
            throw new ReglaProgramadaErrorPersistir();
        }
    }

    /**
     * Devuelve la regla programada con el id indicado
     *
     * @param idReglaProgramada
     * @return Devuelve el actuador, null si no es encontrado.
     */
    public ReglaProgramada obtenerReglaProgramada(Integer idReglaProgramada) {
        return reglaProgramadaDAO.buscar(idReglaProgramada);
    }

    /**
     * Elimina una regla programada del sistema.
     *
     * @param idReglaProgramada
     * @throws es.ujaen.iambiental.excepciones.ReglaProgramadaErrorEliminar
     * @throws es.ujaen.iambiental.excepciones.ReglaProgramadaNoEncontrada
     */
    public void eliminarReglaProgramada(Integer idReglaProgramada) throws ReglaProgramadaErrorEliminar, ReglaProgramadaNoEncontrada {
        ReglaProgramada rp = reglaProgramadaDAO.buscar(idReglaProgramada);
        if (rp == null) {
            throw new ReglaProgramadaNoEncontrada();
        }
        reglaProgramadaDAO.eliminar(rp);
    }

    /**
     * Modifica una regla programada del sistema.
     *
     * @param reglaProgramada
     * @throws es.ujaen.iambiental.excepciones.ReglaProgramadaErrorActualizar
     */
    public void modificarReglaProgramada(ReglaProgramada reglaProgramada) throws ReglaProgramadaErrorActualizar {
        reglaProgramadaDAO.actualizar(reglaProgramada);
    }

    /**
     * Crear una regla sensor-actuador.
     *
     * @param reglaSensorActuador
     * @throws es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorDatos
     * @throws es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorPersistir
     */
    public void crearReglaSensorActuador(ReglaSensorActuador reglaSensorActuador) throws ReglaSensorActuadorErrorDatos, ReglaSensorActuadorErrorPersistir {
        try {
            reglaSensorActuadorDAO.insertar(reglaSensorActuador);
        } catch (ReglaSensorActuadorErrorPersistir e) {
            throw new ReglaSensorActuadorErrorPersistir();
        }
    }

    /**
     * Devuelve la regla sensor-actuador con el id indicado
     *
     * @param idReglaSensorActuador
     * @return Devuelve la regla sensor-actuador, null si no es encontrado.
     */
    public ReglaSensorActuador obtenerReglaSensorActuador(Integer idReglaSensorActuador) {
        return reglaSensorActuadorDAO.buscar(idReglaSensorActuador);
    }

    /**
     * Elimina una regla sensor-actuador del sistema.
     *
     * @param idReglaSensorActuador
     * @throws es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorEliminar
     * @throws es.ujaen.iambiental.excepciones.ReglaSensorActuadorNoEncontrada
     */
    public void eliminarReglaSensorActuador(Integer idReglaSensorActuador) throws ReglaSensorActuadorErrorEliminar, ReglaSensorActuadorNoEncontrada {
        ReglaSensorActuador rsa = reglaSensorActuadorDAO.buscar(idReglaSensorActuador);
        if (rsa == null) {
            throw new ReglaSensorActuadorNoEncontrada();
        }
        reglaSensorActuadorDAO.eliminar(rsa);
    }

    /**
     * Modifica una regla sensor-actuador del sistema.
     *
     * @param reglaSensorActuador
     * @throws
     * es.ujaen.iambiental.excepciones.ReglaSensorActuadorErrorActualizar
     */
    public void modificarReglaSensorActuador(ReglaSensorActuador reglaSensorActuador) throws ReglaSensorActuadorErrorActualizar {
        reglaSensorActuadorDAO.actualizar(reglaSensorActuador);
    }

    /**
     * Crear una dependencia.
     *
     * @param dependencia
     * @throws es.ujaen.iambiental.excepciones.DependenciaErrorDatos
     * @throws es.ujaen.iambiental.excepciones.DependenciaErrorPersistir
     */
    public void crearDependencia(Dependencia dependencia) throws DependenciaErrorDatos, DependenciaErrorPersistir {

        try {
            dependenciaDAO.insertar(dependencia);
        } catch (DependenciaErrorPersistir e) {
            throw new DependenciaErrorPersistir();
        }

    }

    /**
     * Devuelve la dependencia con el id indicado
     *
     * @param idDependencia
     * @return Devuelve la dependencia, null si no es encontrada.
     */
    public Dependencia obtenerDependencia(Integer idDependencia) {
        return dependenciaDAO.buscar(idDependencia);
    }

    /**
     * Elimina una dependencia del sistema.
     *
     * @param idDependencia
     * @throws es.ujaen.iambiental.excepciones.DependenciaErrorEliminar
     * @throws es.ujaen.iambiental.excepciones.DependenciaNoEncontrada
     * @throws es.ujaen.iambiental.excepciones.SensorErrorCambiarDependencia
     * @throws es.ujaen.iambiental.excepciones.ActuadorErrorCambiarDependencia
     */
    public void eliminarDependencia(Integer idDependencia) throws DependenciaErrorEliminar, DependenciaNoEncontrada, SensorErrorCambiarDependencia, ActuadorErrorCambiarDependencia {
        Dependencia d = dependenciaDAO.buscar(idDependencia);
        if (d == null) {
            throw new DependenciaNoEncontrada();
        }

        sensorDAO.cambiarDependencia(null, d);
        actuadorDAO.cambiarDependencia(null, d);
        dependenciaDAO.eliminar(d);
    }

    /**
     * Modifica una dependencia del sistema.
     *
     * @param dependencia
     * @throws es.ujaen.iambiental.excepciones.DependenciaErrorActualizar
     */
    public void modificarDependencia(Dependencia dependencia) throws DependenciaErrorActualizar {
        dependenciaDAO.actualizar(dependencia);
    }

    /**
     * Devuelve un mapa con la lista de sensores.
     *
     * @return Devuelve un mapa con la lista de sensores
     */
    public Map<Integer, Sensor> listarSensores() {
        return sensorDAO.listar();
    }

    /**
     * Devuelve un mapa con la lista de actuadores.
     *
     * @return Devuelve un mapa con la lista de actuadores
     */
    public Map<Integer, Actuador> listarActuadores() {
        return actuadorDAO.listar();
    }

    /**
     * Devuelve un mapa con la lista de tareas programadas.
     *
     * @return Devuelve un mapa con la lista de tareas programadas
     */
    public Map<Integer, TareaProgramada> listarTareasProgramadas() {
        Map<Integer, TareaProgramada> tareasP = tareaProgramadaDAO.listar();
        for (Map.Entry t : tareasP.entrySet()) {
            TareaProgramada tarea = (TareaProgramada) t.getValue();
            lanzarTarea(tarea);
        }
        return tareasP;
    }

    /**
     * Devuelve un mapa con la lista de reglas programadas.
     *
     * @return Devuelve un mapa con la lista de reglas programadas
     */
    public Map<Integer, ReglaProgramada> listarReglasProgramadas() {
        return reglaProgramadaDAO.listar();
    }

    /**
     * Devuelve un mapa con la lista de reglas sensor-actuador.
     *
     * @return Devuelve un mapa con la lista de reglas sensor-actuador
     */
    public Map<Integer, ReglaSensorActuador> listarReglasSensorActuador() {
        return reglaSensorActuadorDAO.listar();
    }

    /**
     * Devuelve un mapa con la lista de dependencias.
     *
     * @return Devuelve un mapa con la lista de dependencias
     */
    public Map<Integer, Dependencia> listarDependencias() {
        return dependenciaDAO.listar();
    }

    /**
     * Devuelve un mapa con el histórico de valores de un sensor.
     *
     * @param idSensor
     * @param fechaInicio
     * @param fechaFinal
     * @return Devuelve un mapa con el histórico de valores de un sensor
     */
    public Map<Date, Double> obtenerHistoricoSensor(Integer idSensor, Date fechaInicio, Date fechaFinal) {
        return null;
    }

    /**
     * Devuelve un mapa con el histórico de valores de un actuador.
     *
     * @param idActuador
     * @param fechaInicio
     * @param fechaFinal
     * @return Devuelve un mapa con el histórico de valores de un actuador
     */
    public Map<Date, Double> obtenerHistoricoActuador(Integer idActuador, Date fechaInicio, Date fechaFinal) {
        return null;
    }

    @PostConstruct
    public void lanzarTodasTareas() {
        // cargar todas las tareas programadas
        tareas = new HashMap();
        Map<Integer, TareaProgramada> tareasP = tareaProgramadaDAO.listar();
        for (Map.Entry t : tareasP.entrySet()) {
            TareaProgramada tarea = (TareaProgramada) t.getValue();
            lanzarTarea(tarea);
        }
    }

    private void lanzarTarea(TareaProgramada t) {
        if (tareas.get(t.getId()) == null) {
            JobKey tareaKey = new JobKey(String.valueOf(t.getId()), "group-" + t.getId());
            JobDetail tarea = JobBuilder.newJob(Tarea.class)
                    .withIdentity(tareaKey).build();

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("Trigger-" + t.getId(), "group-" + t.getId())
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule(t.getCron()))
                    .build(); // "0/20 * * * * ?"

            Scheduler scheduler = null;
            try {
                scheduler = new StdSchedulerFactory().getScheduler();
                scheduler.start();
                scheduler.scheduleJob(tarea, trigger);
            } catch (SchedulerException ex) {
                Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            tareas.put(t.getId(), scheduler);
        }
    }

}
