package es.ujaen.iambiental.disparadorTarea;

import es.ujaen.iambiental.beans.AdminBean;
import es.ujaen.iambiental.excepciones.ActuadorErrorActualizar;
import es.ujaen.iambiental.modelos.Actuador;
import es.ujaen.iambiental.modelos.ReglaProgramada;
import es.ujaen.iambiental.modelos.TareaProgramada;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author Raúl
 */
@Component
public class Tarea implements Job {

    @Autowired
    AdminBean administrador;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        int id = Integer.parseInt(context.getJobDetail().getKey().getName());
//        System.out.println(administrador.obtenerTareaProgramada(id).getDescripcion());
        TareaProgramada tarea = administrador.obtenerTareaProgramada(id);

        boolean reglaSeCumple = false;
        for (ReglaProgramada r : tarea.getReglasProgramadas()) {
            if (r.getSensor().getDato() >= r.getValorMax() + r.getMargenRuido() || r.getSensor().getDato() <= r.getValorMin() - r.getMargenRuido()) {
                //Fuera de la regla
                //ACTUALIZAR A VALOR 0
                reglaSeCumple = false;
            } else if (r.getSensor().getDato() <= r.getValorMax() - r.getMargenRuido() && r.getSensor().getDato() >= r.getValorMin() + r.getMargenRuido()) {
                //En la regla plena
                //ACTUALIZAR A VALOR DEL ESTADO
                reglaSeCumple = true;
            }
            
            if(reglaSeCumple){
                try {
                    System.out.println("Regla " +r.getId()+ " se cumple");
                    Actuador a = r.getActuador();
                    a.setEstado(r.getEstadoActuador());
                    a.setFecha(new Date());
                    administrador.modificarActuador(a);
                } catch (ActuadorErrorActualizar ex) {
                    Logger.getLogger(Tarea.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
//                System.out.println("Regla " +r.getId()+ " NO se cumple");
            }
        }
    }

}
